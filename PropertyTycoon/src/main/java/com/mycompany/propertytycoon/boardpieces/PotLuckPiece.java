/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.boardpieces;

/**
 *
 * @author ankeet
 */
public class PotLuckPiece extends BoardPiece {
    
    private String action;
    
    public PotLuckPiece(String title, String action) {
        super(title);
        this.action = action;
    }
    
    public String getAction() {
        return action;
    }
    
}
