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
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author Luz
 */
public class DaoHistorique {
    
     final static String url="jdbc:postgresql://localhost/PAPPL";
     final static String motDePass = "0idcili1";
     
     public ArrayList<DetteSimplifiee> demandeHistorique(String  nom, int annee, int moisDebut, int moisFin){
        
      ArrayList<DetteSimplifiee> historiques = new ArrayList<>();
      
      try {
        Class.forName("org.postgresql.Driver");
            
         Connection conn = DriverManager.getConnection(url,"postgres",motDePass);
         
         String requete1 =  "SELECT redevable.nom_redevable, redevable.adresse_mail_redevable, dette.libelle, "
                 + "dette.montant_dette, dette.date_creation, agent_comptable.nom_agent, "
                 + "agent_comptable.adresse_mail_agent, agent_comptable.id_agent, dette.id_dette, dette.info_complementaire "
                 + "FROM dette JOIN agent_comptable ON (dette.id_agent = agent_comptable.id_agent) "
                 + "JOIN redevable ON (dette.adresse_mail_redevable = redevable.adresse_mail_redevable) "
                 + "WHERE dette.statut_dette=?";
        PreparedStatement stmt;  
        String requete2;
        if(!nom.equals("")){
            if(annee == 0){ 
              requete2 = requete1+" AND redevable.nom_redevable =?";
              stmt = conn.prepareStatement(requete2) ;
              stmt.setBoolean(1,true);
              stmt.setString(2,nom);
            }else{
              requete2 = requete1 + " AND redevable.nom_redevable=? AND (dette.date_creation BETWEEN ? AND ?)"; 
              stmt = conn.prepareStatement(requete2) ;
              if(moisDebut != 0){
                LocalDate dateDebut = LocalDate.of(annee,moisDebut, 1);
                YearMonth moisAnnee = YearMonth.of(annee, moisFin);
                LocalDate dateFin = moisAnnee.atEndOfMonth(); 
                java.sql.Date sqlDateD = java.sql.Date.valueOf(dateDebut);
                java.sql.Date sqlDateF = java.sql.Date.valueOf(dateFin);
                
                stmt.setBoolean(1, true);
                stmt.setString(2,nom);
                stmt.setDate(3, sqlDateD);
                stmt.setDate(4, sqlDateF);
              }else{
                
                LocalDate dateDebut = LocalDate.of(annee,1, 1);
                LocalDate dateFin = LocalDate.of(annee,12, 31);
                java.sql.Date sqlDateD = java.sql.Date.valueOf(dateDebut);
                java.sql.Date sqlDateF = java.sql.Date.valueOf(dateFin);
                
                 stmt.setBoolean(1, true);
                stmt.setString(2,nom);
                stmt.setDate(3, sqlDateD);
                stmt.setDate(4, sqlDateF);
             
              }      
            }
        }else{
            requete2 = requete1 + " AND (dette.date_creation BETWEEN ? AND ?)"; 
            stmt = conn.prepareStatement(requete2) ;
            if(moisDebut != 0){
                LocalDate dateDebut = LocalDate.of(annee,moisDebut, 1);
                YearMonth moisAnnee = YearMonth.of(annee, moisFin);
                LocalDate dateFin = moisAnnee.atEndOfMonth(); 
                java.sql.Date sqlDateD = java.sql.Date.valueOf(dateDebut);
                java.sql.Date sqlDateF = java.sql.Date.valueOf(dateFin);
                
                stmt.setBoolean(1, true);
                stmt.setDate(2, sqlDateD);
                stmt.setDate(3, sqlDateF);
              }else{
                LocalDate dateDebut = LocalDate.of(annee,1, 1);
                LocalDate dateFin = LocalDate.of(annee,12, 31);
                java.sql.Date sqlDateD = java.sql.Date.valueOf(dateDebut);
                java.sql.Date sqlDateF = java.sql.Date.valueOf(dateFin);
                
                 stmt.setBoolean(1, true);
                stmt.setDate(2, sqlDateD);
                stmt.setDate(3, sqlDateF);
              }      
        }
      
         ResultSet res = stmt.executeQuery();
         
        
         while (res.next()){  
             AgentComptable agent = new AgentComptable();
             agent.setAdresseMail(res.getString("adresse_mail_agent"));
             agent.setNom(res.getString("nom_agent"));
             agent.setId("id_agent");
             
             Redevable redevable = new Redevable();
             redevable.setAdresseMail(res.getString("adresse_mail_redevable"));
             redevable.setNom(res.getString("nom_redevable"));
             
             DetteSimplifiee detSimpli = new DetteSimplifiee();
             detSimpli.setAgent(agent);
             detSimpli.setDateCreation( res.getDate("date_creation").toLocalDate()); 
             detSimpli.setLibelle(res.getString("libelle"));
             detSimpli.setRedev(redevable);
             detSimpli.setMontant(res.getDouble("montant_dette"));
             detSimpli.setIdDette(res.getString("id_dette"));
             historiques.add(detSimpli);
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
       return historiques; 
    }
      
}


