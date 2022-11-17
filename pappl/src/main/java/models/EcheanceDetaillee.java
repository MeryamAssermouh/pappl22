/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author Luz et Chenkai
 */
public class EcheanceDetaillee extends EcheanceSimplifiee{
    private boolean statutPaiement;
    private LocalDate datePaiement;
    private boolean statutAnnulation;
    private String raisonAnnulation;
    private String idEcheance;

    /**
     * COnstructeur de la classe EcheanceDetaillee
     * @param statutPaiement c'est une booléen qui est vrai lorsque le paiement a été effectué et false dans le cas contraire
     * @param datePaiement  c'est la date à laquelle l'échéance a été payé
     * @param statutAnnulation c'est une booléen qui est vrai lorsque le paiement a été annulé et false dans le cas contraire
     * @param raisonAnnulation c'est la raison pour laquelle le paiement est annulée
     * @param dateDeadLine c'est la date à laquelle l'échéance doit être payé
     * @param montant  c'est le montant à payer dans l'échéance 
     */
    public EcheanceDetaillee(boolean statutPaiement, LocalDate datePaiement, boolean statutAnnulation, String raisonAnnulation, LocalDate dateDeadLine, double montant) {
        super(dateDeadLine, montant);
        this.statutPaiement = statutPaiement;
        this.datePaiement = datePaiement;
        this.statutAnnulation = statutAnnulation;
        this.raisonAnnulation = raisonAnnulation;
    }

    public EcheanceDetaillee() {
    }

    public boolean getStatutPaiement() {
        return statutPaiement;
    }

    public void setIdEcheance(String idEcheance) {
        this.idEcheance = idEcheance;
    }

    public String getIdEcheance() {
        return idEcheance;
    }

    public boolean getStatutAnnulation() {
        return statutAnnulation;
    }

   
    
    public LocalDate getDatePaiement() {
        return datePaiement;
    }


    public String getRaisonAnnulation() {
        return raisonAnnulation;
    }

    public void setStatutPaiement(boolean statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public void setStatutAnnulation(boolean statutAnnulation) {
        this.statutAnnulation = statutAnnulation;
    }

    public void setRaisonAnnulation(String raisonAnnulation) {
        this.raisonAnnulation = raisonAnnulation;
    }
    
    
            
}
