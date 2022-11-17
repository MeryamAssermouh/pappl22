/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import daos.DaoExcel;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.DetteSimplifiee;
import models.EcheanceSimplifiee;

/**
 *
 * @author Luz
 */
public class ConExcel {
    private DaoExcel daoExcel;
    
    public ConExcel() {
        daoExcel = new DaoExcel();
    }
    
    
    public File chosirFichier(JPanel optionsCreation){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers xlsx" ,"xlsx");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(optionsCreation);
        return chooser.getSelectedFile();
 
    }
    
    
    public void ajouterDonnees(File fichier,JTextField nom, JTextField libelle, JTextField montant, JTable echeancesT){
        DetteSimplifiee detSimp = new DetteSimplifiee();
        detSimp = daoExcel.lireExcel(fichier);
        
        DefaultTableModel model = (DefaultTableModel)echeancesT.getModel();
        TableColumn dateColonne = echeancesT.getColumnModel().getColumn(1);
        DateTableEditor a = new DateTableEditor();
        a.getDatePickerSettings().setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        echeancesT.setDefaultEditor(LocalDate.class, a);
        echeancesT.setDefaultRenderer(LocalDate.class, a);
        dateColonne.setCellEditor(echeancesT.getDefaultEditor(LocalDate.class));
        dateColonne.setCellRenderer(echeancesT.getDefaultRenderer(LocalDate.class));
        
        nom.setText(detSimp.getRedev().getNom());
        libelle.setText(detSimp.getLibelle());
        montant.setText(String.valueOf(detSimp.getMontant()));
        ArrayList<EcheanceSimplifiee> echeances = detSimp.getEs();
        
        
        model.setRowCount(0);
        int i = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(EcheanceSimplifiee echeance: echeances){
            Object[] ligneS = new Object[3];
            ligneS[0]= "Deadline " + i;
            ligneS[1] = formatter.format(echeance.getDateDeadLine());
            ligneS[2] = String.valueOf(echeance.getMontant());
            model.addRow(ligneS);
            i++;
            }
    }
    
    
}
