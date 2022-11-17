/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DaoMail;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 96441
 */
public class ConMail {
    private DaoMail daomail;

    public ConMail() {
        this.daomail = new DaoMail();
    }

    public void affichageInfo(Boolean estRedevable,JTextField jourAvant,JTextArea messageAvant,JTextField jourApres,JTextArea messageApres) {
         ArrayList<String> infos=new ArrayList<String>();
         infos=daomail.lireInformationMail(estRedevable);
         messageAvant.setText(infos.get(0));
         jourAvant.setText(infos.get(1));
         messageApres.setText(infos.get(2));
         jourApres.setText(infos.get(3));
         
         
    }
    
    public void enregistrerInfo(Boolean estRedevable, JTextField jourAvant,JTextArea messageAvant,JTextField jourApres,JTextArea messageApres){
        daomail.enregistrerMail(estRedevable,messageAvant.getText(), jourAvant.getText(), messageApres.getText(), jourApres.getText());
    }
}
