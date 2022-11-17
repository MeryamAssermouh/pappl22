/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.DateFormatSymbols;

/**
 *
 * @author Luz
 */
public class AuxMois {
    
    private int month;

    public AuxMois(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return new DateFormatSymbols().getMonths()[month-1];
    }
    
    
    
}
