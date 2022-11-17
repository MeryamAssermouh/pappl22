/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Exceptions.MontantException;
import Exceptions.VideException;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import daos.DaoCreation;
import daos.DaoEdition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.AgentComptable;
import models.DetteSimplifiee;
import models.EcheanceSimplifiee;
import models.Redevable;

/**
 *
 * @author 96441
 */
public class ConCreation {
    DaoEdition daoEdit;
    DaoCreation daocreation;
    
    public ConCreation(){
        this.daoEdit=new DaoEdition();
        this.daocreation=new DaoCreation();
    }
    
    public DetteSimplifiee enregistrerRedevable(JTable table,JTextField mailRedevable, JTextField nomRedevable,JTextField libelle, JTextField montant, JTextField infoComplementaire, JTextField actionentreprendre,JTextField actioneffectuee, JComboBox nomAgent) throws  NumberFormatException, MontantException, VideException{
        
        ArrayList<EcheanceSimplifiee> echeances=new ArrayList<EcheanceSimplifiee>();
        
        for (int i=0;i<table.getRowCount();i++){
            EcheanceSimplifiee e=new EcheanceSimplifiee();
            if(!(((String)table.getValueAt(i, 2)).equals("") || (table.getValueAt(i,1)).equals(" "))){
                e.setMontant(Double.parseDouble((String)table.getValueAt(i, 2)));
                try{
                    e.setDateDeadLine((LocalDate)table.getValueAt(i,1));
                }catch(java.lang.ClassCastException exe){
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                     formatter = formatter.withLocale(Locale.getDefault()); 
                     LocalDate date = LocalDate.parse((String)table.getValueAt(i,1), formatter);
                     e.setDateDeadLine(date);
                }
                echeances.add(e);
            }else{
               throw new VideException("Les champs ne peuvent pas être vides"); 
            }
        }
        DetteSimplifiee detSimp = new DetteSimplifiee();
        detSimp.setLibelle(libelle.getText());
        Redevable red = new Redevable();  
        if(!(mailRedevable.getText().equals("") || nomRedevable.getText().equals("")) ){
            red.setAdresseMail(mailRedevable.getText());
            red.setNom(nomRedevable.getText());
        }else{
            throw new VideException("Les champs ne peuvent pas être vides");
        }
            
        detSimp.setRedev(red);
        detSimp.setEs(echeances);
        detSimp.setMontant(Double.parseDouble(montant.getText()));
        
        
        double sum=0;
        for(EcheanceSimplifiee echeance: echeances){
            sum = sum + echeance.getMontant();
        }
        
        if(sum == detSimp.getMontant()){
            daocreation.CreationRedevable(mailRedevable.getText(), nomRedevable.getText(), echeances, libelle.getText(), montant.getText(), infoComplementaire.getText(), actionentreprendre.getText(), actioneffectuee.getText(),  nomAgent.getSelectedItem().toString());
        }else{
            throw new MontantException("La somme total des échéances n'est pas égale au montant");
        }
        return detSimp;
    }
    
    
    public JComboBox afficherAgent(JComboBox agentComptable){
        ArrayList<AgentComptable> agents = daoEdit.obtenirAgents();
        agentComptable.removeAllItems();
        for(AgentComptable agent: agents){
            agentComptable.addItem(agent.getNom());
        }
        return agentComptable;
    }
    
    public void nombreDesEcheances(JTextField montant, JComboBox nbEcheances, JTable listeEcheances){
        
        if(!nbEcheances.getSelectedItem().equals("")){
            double  nb = Double.parseDouble((String)nbEcheances.getSelectedItem());
            String montan = montant.getText();
            double resultat = 0;
            String resultatS = "";


            if (montan != null && montan.length() > 0) {
                double mont= Double.parseDouble(montan);
                 if (mont != 0){
                    resultat=mont/nb;
                    resultatS =String.valueOf(resultat);
                }
             }
            DefaultTableModel model = (DefaultTableModel)listeEcheances.getModel();
            TableColumn dateColonne = listeEcheances.getColumnModel().getColumn(1);
            DateTableEditor a = new DateTableEditor();
            a.getDatePickerSettings().setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            listeEcheances.setDefaultEditor(LocalDate.class, a);
            listeEcheances.setDefaultRenderer(LocalDate.class, a);
            dateColonne.setCellEditor(listeEcheances.getDefaultEditor(LocalDate.class));
            dateColonne.setCellRenderer(listeEcheances.getDefaultRenderer(LocalDate.class));

            model.setRowCount(0);
            for(int i= 1; i<=nb; i++){ 
                model.addRow(new Object[]{
                    "Deadline"+i,

                    " ", 
                    resultatS

                });
            }
        }
    }
    
    public JComboBox afficherEcheances(JComboBox nbEcheances){
        
        nbEcheances.removeAllItems();
        nbEcheances.addItem("");
        for (int i=1; i<21; i++){
            nbEcheances.addItem(String.valueOf(i));
        }
        
        return nbEcheances;
    }
    
}
