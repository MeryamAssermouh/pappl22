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
public class Personne {
    
    private String nom;
    private String adresseMail;

    /**
     * Constructerur de la classe Personne
     * @param nom nom et prénom de la personne 
     * @param adresseMail adresse mail de la personne
     */
    public Personne(){
        
    }
    
    public Personne(String nom, String adresseMail) {
        this.nom = nom;
        this.adresseMail = adresseMail;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }
    
    public void envoyerMail(){
        
    }
    
}
