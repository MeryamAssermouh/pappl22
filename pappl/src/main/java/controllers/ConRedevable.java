/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.DaoRedevable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Redevable;

/**
 *
 * @author T480
 */
public class ConRedevable {
    DaoRedevable daoRedevable;

    public ConRedevable() {
        this.daoRedevable = new DaoRedevable();
    }
    
    public void afficherRedevables(JTable tRedevables){ 
        DefaultTableModel model = (DefaultTableModel)tRedevables.getModel();
        model.setRowCount(0); 
        ArrayList<Redevable> redevables = daoRedevable.obtenirRedevables();
        for(Redevable redevable: redevables){
            Object[] ligne = new Object[2];
              ligne[0]= redevable.getNom();
              ligne[1] = redevable.getAdresseMail();
              model.addRow(ligne);
        } 
    }
    
    public boolean effacerRedevable(JTable tRedevables){
        boolean exeption = false;
        Redevable redevable = new Redevable();
        int ligneE = tRedevables.getSelectedRow();
        redevable.setNom((String)tRedevables.getValueAt(ligneE, 0));
        redevable.setAdresseMail((String)tRedevables.getValueAt(ligneE, 1));
        try {  
            daoRedevable.effacerRedevable(redevable);
        } catch (SQLException e) {
           if (e.getSQLState().equals("23503")){
            exeption = true;
           }
        }
        if (!exeption){
            ((DefaultTableModel)tRedevables.getModel()).removeRow(ligneE);
        }
        
        return exeption; 
    }
}
