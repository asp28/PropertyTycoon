package com.mycompany.propertytycoon.boardpieces;

/**
 *  FreeParkingPiece represents the FreeParking tile on the board
 *  @author Big Nerd Notation
 */
public class FreeParkingPiece extends BoardPiece {
    private int balance;
    private String action;
    
    /**
     * FreeParkingPiece constructor
     * @param title title of FreeParking
     * @param action action of FreeParking
     */
    public FreeParkingPiece(String title, String action) {
        super(title);
        balance = 0;
        this.action = action;
    }

    /**
     * Gets the action in relation to the FreeParkingPiece
     * @return action
     */
    public String getAction() {
        return action;
    }
    
    /**
     * Gets the current balance of the FreeParkingPiece
     * @return FreeParking balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * Sets the balance of the FreeParkingPiece
     * @param num proposed balance of FreeParking
     */
    public void setBalance(int num) {
        balance = num;
    }
}
