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
public class GoPiece extends BoardPiece {
    
    private String action;
    
    public GoPiece(String title, String action) {
        super(title);
    }

    public String getAction() {
        return action;
    }
    
}