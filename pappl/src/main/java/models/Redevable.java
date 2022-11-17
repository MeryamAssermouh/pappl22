/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 *
 * @author Luz et Chenkai
 */
public class Redevable extends Personne {

    /**
     * Constructeur de la classe Redevable
     * @param nom nom et prénom du redevable
     * @param adresseMail adresse mail du redevable
     */
    public Redevable(String nom, String adresseMail) {
        super(nom, adresseMail);
    }
    
     public Redevable() {
    }
    
}
