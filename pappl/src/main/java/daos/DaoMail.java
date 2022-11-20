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

/**
 *
 * @author 96441
 */
public class DaoMail {

    public DaoMail() {
    }
    
    /**
     * Cette méthode permet d'enregistrer les mails selon le statut de la personne (redevable ou pas)
     * @param estRedevable
     * @param messageAvant
     * @param jourAvant
     * @param messageApres
     * @param jourApres 
     */
    public void enregistrerMail (Boolean estRedevable,String messageAvant, String jourAvant,String messageApres, String jourApres)  {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
            String requete1 = "BEGIN; "
                     +"UPDATE mail set corps_message=?, jour=? where mail.id_mail=?; "
                     +"UPDATE mail set corps_message=?, jour=? where mail.id_mail=?; "
                     +"End;";

            PreparedStatement  stmt=conn.prepareStatement(requete1);

            stmt.setString(1,messageAvant);
            stmt.setInt(2,Integer.parseInt(jourAvant));
            if(estRedevable){
               stmt.setString(3,"redevableAvant"); 
               stmt.setString(6,"redevableApres"); 
            }else{
              stmt.setString(3,"agentAvant");  
              stmt.setString(6,"agentApres"); 
            }       
            stmt.setString(4,messageApres);
            stmt.setInt(5,Integer.parseInt(jourApres));
          

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
    
    
 
    /**
     * Cette méthode permet d'afficher le corps du message contenu dans le mail ainsi que 
       le jour selon le statut de la personne (redevable ou pas)
     * @param estRedevable
     * @return 
     */
    public ArrayList<String> lireInformationMail(Boolean estRedevable) {
           
        try {
            ArrayList<String>lignes=new ArrayList<>();
            Class.forName("org.postgresql.Driver");
            
            Connection conn = DriverManager.getConnection(DaoHistorique.url,"postgres", DaoHistorique.motDePass);
            String requete1 =  "SELECT corps_message,jour FROM mail WHERE mail.id_mail=? OR mail.id_mail=?";
            PreparedStatement  stmt = conn.prepareStatement(requete1) ;
            if(estRedevable){
               stmt.setString(1,"redevableAvant"); 
               stmt.setString(2,"redevableApres"); 
            }else{
              stmt.setString(1,"agentAvant");  
              stmt.setString(2,"agentApres"); 
            }
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {

                do {
                    lignes.add(res.getString("corps_message"));
                    lignes.add(res.getString("jour"));
                } while (res.next());
            }
            return lignes;
    }catch (SQLException | java.lang.ClassNotFoundException e) {
             e.printStackTrace();
    }
        return null;
}
    
}
