/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.AgentComptable;
import models.DetteDetaillee;
import models.EcheanceDetaillee;
/**
 *
 * @author 96441
 */
public class DaoEdition {
    
    public void editionInfo(DetteDetaillee dette){
     try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        String requete1 = "BEGIN; "
                 +"UPDATE redevable "
                 +"SET adresse_mail_redevable=?, nom_redevable=?"
                 +"from dette where redevable.adresse_mail_redevable=dette.adresse_mail_redevable and dette.id_dette=?; "
                 +"update dette set id_agent=? where dette.id_dette=?; "
                 +"update dette " 
                 +"set libelle=?,montant_dette=?,info_complementaire=?,action_effectuee=?,action_entreprendre=? " 
                 +"where dette.id_dette=?; " 
                 +"End;";
         
        PreparedStatement  stmt=conn.prepareStatement(requete1);
        stmt.setString(1,dette.getRedev().getAdresseMail());
        stmt.setString(2,dette.getRedev().getNom());
        stmt.setString(3,dette.getIdDette());
        stmt.setString(4,dette.getAgent().getId());
        stmt.setString(5,dette.getIdDette());
        stmt.setString(6,dette.getLibelle());
        stmt.setDouble(7,dette.getMontant());
        stmt.setString(8,dette.getInfoComplementaire());
        stmt.setString(9,dette.getActionEffectuee());
        stmt.setString(10,dette.getActionEntreprendre());
        stmt.setString(11,dette.getIdDette());
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
     ArrayList<String> echeancesDB = this.obtenirEcheancesPrecedentes(dette.getIdDette());
     this.mettreAJourEcheances(dette.getEd(),echeancesDB, dette.getIdDette());
    }
   
    public void mettreAJourEcheances(ArrayList<EcheanceDetaillee> echeDetN, ArrayList<String> echeBD, String idDette){
        for(EcheanceDetaillee echeance : echeDetN){
            if(!(echeance.getIdEcheance() == null)){
           // if(echeBD.indexOf(echeance.getIdEcheance())!= -1){
                this.updateEcheance(echeance);
                
            }else{
                String idEcheanceN = this.ajouterEcheance(echeance, idDette);
                echeance.setIdEcheance(idEcheanceN);
            }
        }
        //si el iddette es "" entonces es porque es nuev
        //si el de la base de datos no esta en las nuevas
        ArrayList<String> echeDetNString = new ArrayList<>();
        for(EcheanceDetaillee echeance : echeDetN){
            if (echeance.getIdEcheance() != null){
                echeDetNString.add(echeance.getIdEcheance());
            }
        }
        
        for(String idEcheBD : echeBD){
            if(echeDetNString.indexOf(idEcheBD) == -1){
                this.deleteEcheance(idEcheBD);
            }
        }
        
    }
    
    
    public String ajouterEcheance(EcheanceDetaillee e, String idDette ){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
            String requete1, requete0;
            PreparedStatement  stmt = null;
            requete1 = "INSERT INTO echeance(id_echeance,date_deadline,montant_echeance,statut_paiement,statut_annulation,date_paiement,raison_annulation,id_dette) "
                       +"VALUES (nextval('echeance_sequence'),?,?,?,?,?,?,?) RETURNING id_echeance";
            stmt=conn.prepareStatement(requete1);
            stmt.setDate(1,Date.valueOf(e.getDateDeadLine()));
            stmt.setDouble(2,e.getMontant());
            stmt.setBoolean(3,e.getStatutPaiement());
            stmt.setBoolean(4,e.getStatutAnnulation());
            if(e.getDatePaiement() == null){
                stmt.setTimestamp(5,null);
            }else{
                stmt.setDate(5,Date.valueOf(e.getDatePaiement()));
            }
            stmt.setString(6,e.getRaisonAnnulation());
            stmt.setString(7,idDette);
            stmt.execute();
            ResultSet echeanceCree = stmt.getResultSet();
            echeanceCree.next();
            String echeanceCreeId = echeanceCree.getString(1);

            stmt.close() ;
            conn.close() ; 
            return echeanceCreeId;
        }catch (SQLException ex) {
             ex.printStackTrace();
        }catch (java.lang.ClassNotFoundException ex) {
            ex.printStackTrace();
    } 
        return null;
    }
    
     public void updateEcheance(EcheanceDetaillee e){
        try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        String requete1;
        PreparedStatement  stmt = null;
        requete1 = "UPDATE echeance SET date_deadline=?, montant_echeance=?, statut_paiement=? , statut_annulation=?, "
                + "date_paiement=?, raison_annulation=? WHERE id_echeance =?";
        stmt=conn.prepareStatement(requete1);
        stmt.setDate(1,Date.valueOf(e.getDateDeadLine()));
        stmt.setDouble(2,e.getMontant());
        stmt.setBoolean(3,e.getStatutPaiement());
        stmt.setBoolean(4,e.getStatutAnnulation());
        if(e.getDatePaiement() == null){
            stmt.setTimestamp(5,null);
        }else{
            stmt.setDate(5,Date.valueOf(e.getDatePaiement()));
        }
        stmt.setString(6,e.getRaisonAnnulation());
        stmt.setString(7,e.getIdEcheance());
        stmt.executeUpdate();
        
        stmt.close() ;
        conn.close() ; 
         
        }
    catch (SQLException ex) {
             ex.printStackTrace();
    }
    catch (java.lang.ClassNotFoundException ex) {
        ex.printStackTrace();
    } 
    }
     
    public void deleteEcheance(String idEcheance) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DaoHistorique.url, "postgres", DaoHistorique.motDePass);
            String requete1;
            PreparedStatement stmt = null;
            requete1 = "DELETE FROM echeance WHERE id_echeance = ?";
            stmt = conn.prepareStatement(requete1);
            stmt.setString(1, idEcheance);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public ArrayList<String> obtenirEcheancesPrecedentes(String idDette){
        ArrayList<String> idsEcheances = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
            String requete1, requete0;
            PreparedStatement  stmt = null;


            requete1 = "SELECT echeance.id_echeance FROM echeance WHERE id_dette=?";
            stmt=conn.prepareStatement(requete1);
            stmt.setString(1,idDette);
            ResultSet res = stmt.executeQuery();

            while (res.next()){  
                idsEcheances.add(res.getString("id_echeance"));
            }
            stmt.close();
            conn.close(); 
         
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (java.lang.ClassNotFoundException e) {
        e.printStackTrace();
        } 
        return idsEcheances;
    }
    
    public void effacerEcheance(String idDette){
         try {
        Class.forName("org.postgresql.Driver");
        
         Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
       
         String requete1 = "DELETE FROM echeance "
                      +"where echeance.id_dette=? " ;

        PreparedStatement  stmt=conn.prepareStatement(requete1);
        stmt.setString(1,idDette);
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
      public ArrayList<AgentComptable> obtenirAgents(){
          ArrayList<AgentComptable> agents = new ArrayList<>();
          try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);

            String requete1 = "SELECT nom_agent FROM agent_comptable ";
                          
            
            PreparedStatement  stmt=conn.prepareStatement(requete1);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                 AgentComptable agent = new AgentComptable();
                 agent.setNom(res.getString("nom_agent"));
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
      
   public String obtenirIdAgent(String nom){
       
        String idAgent="";
        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);

            String requete1 = "SELECT id_agent FROM agent_comptable WHERE nom_agent=? ";
                          
            
            PreparedStatement  stmt=conn.prepareStatement(requete1);
            stmt.setString(1,nom);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                 idAgent = res.getString("id_agent");
            }
            stmt.close() ;
            conn.close() ; 
            
        }catch (SQLException e) {
             e.printStackTrace();
        }
        catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
     return idAgent;
   }
}