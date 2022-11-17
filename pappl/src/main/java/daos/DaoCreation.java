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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import models.EcheanceSimplifiee;

/**
 *
 * @author 96441
 */
public class DaoCreation {
     public void CreationRedevable(String mailRedevable, String nomRedevable, ArrayList<EcheanceSimplifiee> listEcheance, String libelle, String montant, String infoComplementaire, String actionentre, String actEfect ,String nomAgent){
       try {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
        PreparedStatement  stmt = null;
        String requete1 = "SELECT redevable.adresse_mail_redevable FROM redevable ";
        stmt=conn.prepareStatement(requete1);
        ResultSet res = stmt.executeQuery();
        boolean existe=false;
        res.next();
        do{
            if (res.getString("adresse_mail_redevable").equals(mailRedevable)){existe=true;}
        }while(res.next());
        
        if (existe==false){
          String requete2= "INSERT INTO redevable(adresse_mail_redevable,nom_redevable) VALUES (?,?) ";
           stmt=conn.prepareStatement(requete2);
           stmt.setString(1,mailRedevable);
           stmt.setString(2,nomRedevable);
           stmt.executeUpdate();
        }
        
        requete1 = "SELECT agent_comptable.id_agent FROM agent_comptable WHERE agent_comptable.nom_agent=?";
        stmt=conn.prepareStatement(requete1);
        stmt.setString(1,nomAgent);
        res = stmt.executeQuery();
        res.next();
        String idAgent=res.getString("id_agent");

        
        String requete3="INSERT INTO dette(libelle,montant_dette,info_complementaire,adresse_mail_redevable,id_agent,date_creation,statut_dette,dette_actuelle, action_effectuee, action_entreprendre) "
                        +"VALUES (?,?,?,?,?,?,?,?,?,?)";
        stmt=conn.prepareStatement(requete3,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1,libelle);
        stmt.setDouble(2,Double.parseDouble(montant));
        stmt.setString(3,infoComplementaire);
        stmt.setString(4,mailRedevable);
        stmt.setString(5,idAgent);
        stmt.setDate(6,Date.valueOf(LocalDate.now()));
        stmt.setBoolean(7, false);
        stmt.setDouble(8, Double.parseDouble(montant));
        stmt.setString(9,actEfect);
        stmt.setString(10,actionentre);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
            
            int de_id = 0;
            if(rs.next()){
                de_id = rs.getInt(1);}
        //requete3="SELECT currval('dette_sequence')AS de_id FROM dette";
        //stmt=conn.prepareStatement(requete3);
        //res = stmt.executeQuery();
        //res.next();
        for (EcheanceSimplifiee e : listEcheance){
        String requete4 = "INSERT INTO echeance(date_deadline,montant_echeance,statut_paiement,statut_annulation,id_dette) "
                   +"VALUES (?,?,?,?,?)";
        stmt=conn.prepareStatement(requete4);
        stmt.setDate(1,Date.valueOf(e.getDateDeadLine()));
        stmt.setDouble(2,e.getMontant());
        stmt.setBoolean(3,false);
        stmt.setBoolean(4,false);
        stmt.setInt(5,de_id);
        stmt.executeUpdate();
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
    }
}