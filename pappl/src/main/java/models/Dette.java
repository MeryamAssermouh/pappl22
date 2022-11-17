/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *la classe qui represente la dette d'un redevable
 * @author luz et chenkai
 */
public class Dette {
    private String libelle;
    private Double montant;
    private String infoComplementaire;
    private AgentComptable agent;
    private Redevable redev;
    private Double detteActuelle;
    private String idDette;
    /**
    *le constructeur pour creer un objet Dette quan on sait les infos necessaires
    *@param libelle 
    *@param montant
    *@param infoComplemantaire
    *@param agent (l'agent qui est responsable de traiter cette dette)
    *@param redev 
    */
    
    public Dette(){
        
    }
    public Dette(String libelle, Double montant, String infoComplementaire, AgentComptable agent, Redevable redev, Double detteActuelle, String idDette) {
        this.libelle = libelle;
        this.montant = montant;
        this.infoComplementaire = infoComplementaire;
        this.agent = agent;
        this.redev=redev;
        this.detteActuelle=detteActuelle;
        this.idDette=idDette;
    }   

    public String getLibelle() {
        return libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public String getInfoComplementaire() {
        return infoComplementaire;
    }

    public AgentComptable getAgent() {
        return agent;
    }

    public Redevable getRedev() {
        return redev;
    }

    public Double getDetteActuelle() {
        return detteActuelle;
    }

    public String getIdDette() {
        return idDette;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public void setInfoComplementaire(String infoComplementaire) {
        this.infoComplementaire = infoComplementaire;
    }

    public void setAgent(AgentComptable agent) {
        this.agent = agent;
    }

    public void setRedev(Redevable redev) {
        this.redev = redev;
    }

    public void setDetteActuelle(Double detteActuelle) {
        this.detteActuelle = detteActuelle;
    }

    public void setIdDette(String idDette) {
        this.idDette = idDette;
    }

    @Override
    public String toString() {
        return  "libelle=" + libelle + ", montant=" + montant + ", infoComplementaire=" + infoComplementaire + ", agent=" + agent + ", redev=" + redev + ", detteActuelle=" + detteActuelle + ", idDette=" + idDette + '}';
    }
    
    
}
