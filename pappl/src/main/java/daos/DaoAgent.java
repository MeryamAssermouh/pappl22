/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.AgentComptable;

/**
 *
 * @author Luz
 */
public class DaoAgent {
 

    public DaoAgent() {
  
    }
    
    
    
     public ArrayList<AgentComptable> obtenirAgents(){
          ArrayList<AgentComptable> agents = new ArrayList<>();
          try {
            Class.forName("org.postgresql.Driver");
            //System.out.println(motDePass);
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);

            String requete1 = "SELECT id_agent,nom_agent, adresse_mail_agent, statut_agent FROM agent_comptable ";
                          
            
            PreparedStatement  stmt=conn.prepareStatement(requete1);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                 AgentComptable agent = new AgentComptable();
                 agent.setNom(res.getString("nom_agent"));
                 agent.setAdresseMail(res.getString("adresse_mail_agent"));
                 agent.setStatut(res.getBoolean("statut_agent"));
                 agent.setId(res.getString("id_agent"));
                 agents.add(agent);
            }
            stmt.close() ;
            conn.close() ; 
            return agents;
        }
          
        catch (SQLException e) {
             e.printStackTrace();
        }
        catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
        }
        return agents;
   }
     
     public void ajouterAgent(AgentComptable agent){
         try {    
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        String requete1;
        PreparedStatement  stmt = null;
      
 
        requete1 = "INSERT INTO agent_comptable (adresse_mail_agent,nom_agent,id_agent) "
                   +"VALUES (?,?,?)";
        stmt=conn.prepareStatement(requete1);
        stmt.setString(1,agent.getAdresseMail());
        stmt.setString(2,agent.getNom());
        stmt.setString(3,agent.getId());
        stmt.executeUpdate();
        
        stmt.close() ;
        conn.close() ; 
         
        }
    catch (SQLException e) {
             e.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
    } 
    }
     
     public void mettreAJourAgents(ArrayList<AgentComptable> agents){
         try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
                
            for(AgentComptable agent: agents){
                String requete1 = "UPDATE agent_comptable SET nom_agent =? , statut_agent=? , adresse_mail_agent=?"
                        + "WHERE id_agent =? ";

                PreparedStatement  stmt=conn.prepareStatement(requete1);
                stmt.setString(1, agent.getNom());
                stmt.setBoolean(2, agent.isStatut());
                stmt.setString(3, agent.getAdresseMail());
                stmt.setString(4, agent.getId());
                stmt.executeUpdate();
                stmt.close() ;
            }
            
            conn.close() ; 
           
        }
          
        catch (SQLException e) {
             e.printStackTrace();
        }
        catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
        }
     }
     
     public void effacerAgent(AgentComptable agent) throws SQLException{
        try {    
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
            String requete1;
            PreparedStatement  stmt = null;


            requete1 = "DELETE FROM agent_comptable WHERE id_agent =?";
            stmt=conn.prepareStatement(requete1);
            stmt.setString(1,agent.getId());
            stmt.executeUpdate();

            stmt.close() ;
            conn.close() ; 

      
            
     
       }catch (java.lang.ClassNotFoundException e) {
             e.printStackTrace();
         }
        
      }
    
}