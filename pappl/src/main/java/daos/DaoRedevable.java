/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author T480
 */
public class DaoRedevable {
    
    /**
     * Permet d'obtenir la liste des redevables 
     * @return listes de redevables 
     */
    public ArrayList<Redevable> obtenirRedevables(){
        ArrayList<Redevable> redevables = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(DaoHistorique.url, DaoHistorique.Login, DaoHistorique.motDePass);

            String requete1 = "SELECT nom_redevable, adresse_mail_redevable FROM redevable ";
                          
            
            PreparedStatement  stmt=conn.prepareStatement(requete1);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Redevable redevable = new Redevable();
                redevable.setNom(res.getString("nom_redevable"));
                redevable.setAdresseMail(res.getString("adresse_mail_redevable"));
                redevables.add(redevable);
            }
            stmt.close() ;
            conn.close() ; 
            return redevables;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        return redevables;
    }
    
    /** 
     * Permet de supprimer un redevable ainsi que sa dette et ses échéances
     * @param redevable
     * @throws SQLException 
     */
    public void effacerRedevable(Redevable redevable) throws SQLException{
        try {    
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DaoHistorique.url,DaoHistorique.Login, DaoHistorique.motDePass);
            String requete1 = "DELETE FROM redevable WHERE adresse_mail_redevable =?";
            
            PreparedStatement stmt1=conn.prepareStatement(requete1);
            stmt1.setString(1,redevable.getAdresseMail());
            stmt1.executeUpdate();
            stmt1.close() ; 
            
            String requete2 = "SELECT id_dette FROM dette WHERE dette.adresse_mail_redevable = ?";
            PreparedStatement stmt2 = conn.prepareStatement(requete2);
            stmt2.setString(1, redevable.getAdresseMail());
            ResultSet res = stmt2.executeQuery();
            res.next();
            Integer id_dette = res.getInt("id_dette");
            stmt2.close();
            
            String requete3 = "DELETE FROM dette WHERE dette.adresse_mail_redevable = ?";
            PreparedStatement stmt3 = conn.prepareStatement(requete3);
            stmt3.setString(1, redevable.getAdresseMail());
            stmt3.executeUpdate();
            stmt3.close();
            
            String requete4 = "DELETE FROM echeance WHERE echeance.id_dette = ?";
            PreparedStatement stmt4 = conn.prepareStatement(requete4);
            stmt4.setInt(0, id_dette);
            stmt4.executeUpdate();
            stmt4.close();
            
            conn.close() ;      
            
        }catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
