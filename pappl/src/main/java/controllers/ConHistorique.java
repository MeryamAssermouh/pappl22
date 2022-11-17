/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import daos.DaoHistorique;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.*;

/**
 *
 * @author Luz
 */

public class ConHistorique {
    
    private DaoHistorique daohisto;

    public ConHistorique() {
        this.daohisto = new DaoHistorique();
    }
    
    
    public void rechercheHistorique(JComboBox annee, JComboBox mois1, JComboBox mois2){
        
        mois1.removeAllItems();
        mois2.removeAllItems();
        annee.removeAllItems();
        mois1.addItem("");
        mois2.addItem("");
        for(int i=1;i<=12;i++){
            
            mois1.addItem(new AuxMois(i));
            mois2.addItem(new AuxMois(i));
        }
        //Il faut ajouter la condition que mois1> mois2
        Calendar cal= Calendar.getInstance();
        annee.addItem("");
        int ann = cal.get(Calendar.YEAR);
        for(int i=2018;i<=ann;i++){
            annee.addItem(i);
        }      
    }
    
    
    public boolean showListeHistorique(JTable table,JComboBox annee, JComboBox mois1, JComboBox mois2, JTextField nom){
        
        int ann;
        int mo1;
        int mo2;
        boolean recherche;
        
        try{
          ann = (Integer)annee.getSelectedItem();  
        }catch(Exception e){
          ann = 0;
        }
        
        try{
          mo1 = ((AuxMois)mois1.getSelectedItem()).getMonth();  
        }catch(Exception e){
          mo1 = 0;
        }
        try{
          mo2 = ((AuxMois)mois2.getSelectedItem()).getMonth();
        }catch(Exception e){
          mo2 = 0;
        }

        ArrayList<DetteSimplifiee> dettes = daohisto.demandeHistorique(nom.getText(), ann, mo1, mo2);
        
        TableColumn dateColonne = table.getColumnModel().getColumn(2);
        DateTableEditor a = new DateTableEditor();
        a.getDatePickerSettings().setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        table.setDefaultEditor(LocalDate.class, a);
        table.setDefaultRenderer(LocalDate.class, a);
        dateColonne.setCellEditor(table.getDefaultEditor(LocalDate.class));
        dateColonne.setCellRenderer(table.getDefaultRenderer(LocalDate.class));
        
        if (dettes.size()==0){
            recherche = false;
        }else{
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setRowCount(0);
            
            for(DetteSimplifiee dette: dettes){
                Object[] ligne = new Object[6]; 
                ligne[0]= dette.getRedev().getNom();
                if (dette.getLibelle()!=null){ligne[1] = dette.getLibelle();}
                ligne[2] = dette.getDateCreation();
                ligne[3] = dette.getMontant();
                ligne[4] = dette.getAgent().getNom();
                ligne[5] = dette.getIdDette();
                model.addRow(ligne);
            }
            recherche = true;
        }
        
        table.getColumnModel().getColumn(5).setMinWidth(0);
        table.getColumnModel().getColumn(5).setMaxWidth(0);
        table.getColumnModel().getColumn(5).setWidth(0);
        
        
        return recherche;
    }
    
}
