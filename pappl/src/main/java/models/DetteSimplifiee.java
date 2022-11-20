/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *une sous-classe de Dette qui contient les infos simples d'un redevable qui ensuite permet de voir des details
 * @author luz et chenkai
 */
public class DetteSimplifiee extends Dette{
    public LocalDate dateCreation;
    public ArrayList<EcheanceSimplifiee> es;
    
    
    public DetteSimplifiee() {
        
    }
    
    /**
     * le constructeur pour créer une dette simplifiée avec les infos d'une dette et des echeances proposées par l'agence comptable
     * @param dateCreation la date de creation de l'echeancier du redevable
     * @param es
     * @param libelle
     * @param montant
     * @param infoComplementaire
     * @param agent l'agent qui est responsable de traiter cette dette
     * @param redev le redevable concerné 
     * @param detteActuelle
     * @param idDette 
     */
    public DetteSimplifiee(LocalDate dateCreation, ArrayList<EcheanceSimplifiee> es, String libelle, Double montant, String infoComplementaire, AgentComptable agent, Redevable redev, Double detteActuelle, String idDette) {
        super(libelle, montant, infoComplementaire, agent, redev, detteActuelle, idDette);
        this.dateCreation = dateCreation;
        this.es=es;
    }


    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public ArrayList<EcheanceSimplifiee> getEs() {
        return es;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setEs(ArrayList<EcheanceSimplifiee> es) {
        this.es = es;
    }

    @Override
    public String toString() {
        return "DetteSimplifiee{" + "dateCreation=" + dateCreation + ", es=" + es + '}'+ super.toString();
        
    }
    
    
    
    
    
}
