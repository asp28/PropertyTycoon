/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.cards;

/**
 *
 * @author ankeet
 */
public class Card {
    
    private String description;
    private String action;
    
    public Card(String desc, String action) {
        description = desc;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public String getAction() {
        return action;
    }
    
}
