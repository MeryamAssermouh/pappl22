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
public class EcheanceSimplifiee {
    
    private LocalDate dateDeadLine;
    private double montant;
/**
 * Constructeur de la clase EcheanceSimplifiee
 * @param dateDeadLine  c'est la date à laquelle l'échéance doit être payé
 * @param montant  c'est le montant à payer dans l'échéance 
 */
    public EcheanceSimplifiee(LocalDate dateDeadLine, double montant) {
        this.dateDeadLine = dateDeadLine;
        this.montant = montant;
    }

    public EcheanceSimplifiee() {
    }

    public LocalDate getDateDeadLine() {
        return dateDeadLine;
    }

    public double getMontant() {
        return montant;
    }

    public void setDateDeadLine(LocalDate dateDeadLine) {
        this.dateDeadLine = dateDeadLine;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
        
    
}
