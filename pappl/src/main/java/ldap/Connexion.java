/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldap;

import daos.DaoAgent;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import models.AgentComptable;

/**
 *
 * @author Luz
 */
public class Connexion {
    
    private DaoAgent daoAgent;
  //  private static String login;
   // private static String motDePass;

    public Connexion() {
        this.daoAgent = new DaoAgent();
    }
    
    public boolean connexion(String login, String motDePasse){ 
    
    boolean identifier = false ;
    Properties env = new Properties() ;
  
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory") ;
    env.put(Context.PROVIDER_URL, "ldaps://ldaps.nomade.ec-nantes.fr:636") ;
    env.put(Context.SECURITY_PROTOCOL, "ssl") ;
    env.put(Context.SECURITY_AUTHENTICATION, "simple") ;
    env.put(Context.SECURITY_PRINCIPAL,"uid="+ login + ",ou=people,dc=ec-nantes,dc=fr") ;
    env.put(Context.SECURITY_CREDENTIALS, motDePasse) ;
    env.put("java.naming.ldap.factory.socket", "ldap.MySSLSocketFactory");
    if (!((login.equals("")) || (motDePasse.equals("")))) {
        try {
            DirContext ctx = new InitialDirContext(env) ;
            identifier = true ;
            ctx.close() ;
            System.out.println("connexion success !");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();// Si l’identification à échoué
        } catch (AuthenticationNotSupportedException ex) {
              ex.printStackTrace();// problème de version
        } catch (CommunicationException ex) {
            ex.printStackTrace(); // Problème de communication avec le serveur
        } catch (NamingException e) { 
                e.printStackTrace(); // Si l’identification à échoué
        } catch (Exception e) {
            
        }
        
        
     }
     if(identifier){         
         identifier = this.validation(login);
     }
   
    return identifier;
}
    public boolean validation(String login){
        boolean autorisation = false;
        ArrayList<AgentComptable> agents = new ArrayList<>();
        agents = daoAgent.obtenirAgents();
        for(AgentComptable agent: agents){
            if(login.equals(agent.getId())){
                autorisation = true;
                break;
            }
        }
        return autorisation;
        }
    
    public boolean gestionConnexion(JTextField login, JPasswordField motDePasse){
        motDePasse.setEchoChar('*');
        boolean autorisation;
        autorisation = this.connexion(login.getText(), String.valueOf(motDePasse.getPassword()));
        motDePasse.setText("");
        login.setText(""); 
       return autorisation;
    }
    
    
}
