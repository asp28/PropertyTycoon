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
public class FreeParkingPiece extends BoardPiece {
    private int balance;
    private String action;
    
    public FreeParkingPiece(String title, String action) {
        super(title);
        balance = 0;
        this.action = action;
    }

    public String getAction() {
        return action;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int num) {
        balance = num;
    }
}
