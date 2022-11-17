/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.ArrayList;

/**
 *
 * @author 96441
 */
public class DetteDetaillee extends Dette {
    /*
    *les actions que l'agence comptable a effectuee sur le redevable
    */
    private String actionEffectuee;
     /*
    *les actions que l'agence comptable va entreprendre sur le redevable(par exemple si le redevable ne paie toujours pas une echeance)
    */
    private String actionEntreprendre;
    /*
    *toutes les echenaces detaillee de cette dette
    */
    public ArrayList<EcheanceDetaillee> ed;
    /**
    *le constructeur pour creer une dette simplifiee avec les infos d'une dette et des echeances proposee par l'agence comptable
    *@param actionEffectuee les actions que l'agence comptable a effectuee sur le redevable
    *@param actionEntreprendre les actions que l'agence comptable va entreprendre sur le redevable(par exemple si le redevable ne paie toujours pas une echeance)
    *@param attribut une attribut pour juger si la dette est toute payee et si l'on classe le redevable dans la liste des actifs ou dans l'historique
    *@param ed toutes les echenaces detaillee de cette dette
    */

    public DetteDetaillee(String actionEffectuee, String actionEntreprendre,  ArrayList<EcheanceDetaillee> ed, String libelle, Double montant, String infoComplementaire, AgentComptable agent, Redevable redev, Double detteActuelle, String idDette) {
        super(libelle, montant, infoComplementaire, agent, redev, detteActuelle,idDette);
        this.actionEffectuee = actionEffectuee;
        this.actionEntreprendre = actionEntreprendre;
        this.ed = ed;
    }

    public DetteDetaillee() {
    }
    
    public String getActionEffectuee() {
        return actionEffectuee;
    }

    public String getActionEntreprendre() {
        return actionEntreprendre;
    }

    public ArrayList<EcheanceDetaillee> getEd() {
        return ed;
    }
    
    public void setActionEffectuee(String actionEffectuee) {
        this.actionEffectuee = actionEffectuee;
    }

    public void setActionEntreprendre(String actionEntreprendre) {
        this.actionEntreprendre = actionEntreprendre;
    }

    public void setEd(ArrayList<EcheanceDetaillee> ed) {
        this.ed = ed;
    }

    @Override
    public String toString() {
        return "DetteDetaillee{" + "actionEffectuee=" + actionEffectuee + ", actionEntreprendre=" + actionEntreprendre + ", ed=" + ed + '}';
    }
    
    
   
}
