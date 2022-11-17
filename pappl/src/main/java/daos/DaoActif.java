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
import models.*;

/**
 *
 * @author 96441
 */
public class DaoActif {
    
    public ArrayList<DetteSimplifiee>  demandeListeActifs (){
       ArrayList<DetteSimplifiee> actifs = new ArrayList<>();
      
      try {
        Class.forName("org.postgresql.Driver");
     
         Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
         
         String requete1 =  "SELECT redevable.nom_redevable, redevable.adresse_mail_redevable, dette.libelle,"
                 + "dette.montant_dette, dette.dette_actuelle, dette.date_creation, agent_comptable.nom_agent,"
                 + " agent_comptable.adresse_mail_agent, agent_comptable.id_agent, dette.id_dette,"
                 + " dette.info_complementaire FROM dette JOIN agent_comptable ON "
                 + "(dette.id_agent = agent_comptable.id_agent) "
                 + "JOIN redevable ON (dette.adresse_mail_redevable = redevable.adresse_mail_redevable) "
                 + "WHERE dette.statut_dette=?";
         
        PreparedStatement  stmt = conn.prepareStatement(requete1) ;
        stmt.setBoolean(1, false);
        ResultSet res = stmt.executeQuery();
        
        if(res.next()){
        
         do{  
             AgentComptable agent = new AgentComptable();
             agent.setAdresseMail(res.getString("adresse_mail_agent"));
             agent.setNom(res.getString("nom_agent"));
             agent.setId("id_agent");
             
             Redevable redevable = new Redevable();
             redevable.setAdresseMail(res.getString("adresse_mail_agent"));
             redevable.setNom(res.getString("nom_redevable"));
             
             DetteSimplifiee detSimpli = new DetteSimplifiee();
             detSimpli.setAgent(agent);
             detSimpli.setDateCreation( res.getDate("date_creation").toLocalDate()); 
             detSimpli.setLibelle(res.getString("libelle"));
             detSimpli.setRedev(redevable);
             detSimpli.setMontant(res.getDouble("montant_dette"));
             detSimpli.setDetteActuelle(res.getDouble("dette_actuelle"));
             detSimpli.setIdDette(res.getString("id_dette"));
             actifs.add(detSimpli);
        }while (res.next()); 
            
        }
         stmt.close() ;
         conn.close() ; 
        
         }
    catch (SQLException e) {
             e.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
    }   
       return actifs; 
    }
    
    public DetteDetaillee voirDetailActif(String id){
        DetteDetaillee detDetail = new DetteDetaillee();
       try {
           Class.forName("org.postgresql.Driver");
           
           
           Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres",DaoHistorique.motDePass);
           
           String requete1 =  "SELECT redevable.nom_redevable, redevable.adresse_mail_redevable, dette.libelle, dette.montant_dette,"
                   + "agent_comptable.nom_agent, agent_comptable.adresse_mail_agent, agent_comptable.id_agent, dette.action_effectuee, dette.action_entreprendre,"
                   + "dette.info_complementaire, dette.dette_actuelle, dette.id_dette, echeance.date_deadline, echeance.montant_echeance, echeance.statut_paiement, echeance.statut_annulation,"
                   + "echeance.date_paiement, echeance.raison_annulation, echeance.id_echeance "
                   + "FROM dette JOIN agent_comptable ON (dette.id_agent = agent_comptable.id_agent) "
                   + "JOIN redevable ON (dette.adresse_mail_redevable = redevable.adresse_mail_redevable) "
                   +"JOIN echeance ON (echeance.id_dette = dette.id_dette)"
                   + "WHERE dette.id_dette=?";
           
           PreparedStatement  stmt = conn.prepareStatement(requete1) ;
           stmt.setString(1,id);
           ResultSet res = stmt.executeQuery();
           
           if(res.next()){
               
           AgentComptable agent = new AgentComptable();
           agent.setAdresseMail(res.getString("adresse_mail_agent"));
           agent.setNom(res.getString("nom_agent"));
           agent.setId(res.getString("id_agent"));
           Redevable redevable = new Redevable();
           redevable.setAdresseMail(res.getString("adresse_mail_redevable"));
           redevable.setNom(res.getString("nom_redevable"));
           detDetail.setAgent(agent);
           detDetail.setLibelle(res.getString("libelle"));
           detDetail.setRedev(redevable);
           detDetail.setMontant(res.getDouble("montant_dette"));
           detDetail.setDetteActuelle(res.getDouble("dette_actuelle"));
           detDetail.setIdDette(res.getString("id_dette"));
           detDetail.setActionEffectuee(res.getString("action_effectuee"));
           detDetail.setActionEntreprendre(res.getString("action_entreprendre"));
           detDetail.setInfoComplementaire(res.getString("info_complementaire"));
           ArrayList<EcheanceDetaillee> echeanceDetails=new ArrayList<>();
           do{  
              EcheanceDetaillee echeance=new EcheanceDetaillee();
              echeance.setDateDeadLine(res.getDate("date_deadline").toLocalDate());
              echeance.setMontant(res.getDouble("montant_echeance"));
              echeance.setStatutPaiement(res.getBoolean("statut_paiement"));
              echeance.setStatutAnnulation(res.getBoolean("statut_annulation"));
              echeance.setRaisonAnnulation(res.getString("raison_annulation"));
              echeance.setIdEcheance(res.getString("id_echeance"));
              if(res.getDate("date_paiement")!= null){
                echeance.setDatePaiement(res.getDate("date_paiement").toLocalDate());
              }
              echeanceDetails.add(echeance);

           }while (res.next()); 
           detDetail.setEd(echeanceDetails);
           }
           
       }  catch (SQLException e) {
             e.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
    }   
       return detDetail;
    }
    
 public DetteDetaillee voirDetailActifNoEcheances(String id){
        DetteDetaillee detDetail = new DetteDetaillee();
       try {
           Class.forName("org.postgresql.Driver");
           
           
           Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres",DaoHistorique.motDePass);
           
           String requete1 =  "SELECT redevable.nom_redevable, redevable.adresse_mail_redevable, dette.libelle, dette.montant_dette,"
                   + "agent_comptable.nom_agent, agent_comptable.adresse_mail_agent, agent_comptable.id_agent,  dette.action_effectuee, dette.action_entreprendre,"
                   + "dette.info_complementaire, dette.dette_actuelle, dette.id_dette "
                   + "FROM dette JOIN agent_comptable ON (dette.id_agent = agent_comptable.id_agent) "
                   + "JOIN redevable ON (dette.adresse_mail_redevable = redevable.adresse_mail_redevable) "
                   + "WHERE dette.id_dette=?";
           
           PreparedStatement  stmt = conn.prepareStatement(requete1) ;
           stmt.setString(1,id);
           ResultSet res = stmt.executeQuery();
           
           if(res.next()){
               
           AgentComptable agent = new AgentComptable();
           agent.setAdresseMail(res.getString("adresse_mail_agent"));
           agent.setNom(res.getString("nom_agent"));
           agent.setId(res.getString("id_agent"));
           Redevable redevable = new Redevable();
           redevable.setAdresseMail(res.getString("adresse_mail_redevable"));
           redevable.setNom(res.getString("nom_redevable"));
           detDetail.setAgent(agent);
           detDetail.setLibelle(res.getString("libelle"));
           detDetail.setRedev(redevable);
           detDetail.setMontant(res.getDouble("montant_dette"));
           detDetail.setDetteActuelle(res.getDouble("dette_actuelle"));
           detDetail.setIdDette(res.getString("id_dette"));
           }
           
       }  catch (SQLException e) {
             e.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
    }   
       return detDetail;
    }
}   
    


