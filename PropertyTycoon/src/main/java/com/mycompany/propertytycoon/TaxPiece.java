/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

/**
 *
 * @author ankeet
 */
public class TaxPiece extends BoardPiece {
    
    private final int tax;
    
    public TaxPiece(String title, int taxAmount) {
        super(title);
        tax = taxAmount;
    }

    public int getTax() {
        return tax;
    }
    
    
}
