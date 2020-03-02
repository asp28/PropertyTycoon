package com.mycompany.propertytycoon.boardpieces;

/**
 *  TaxPiece represents the tax/fee tiles on the board
 *  @author Big Nerd Notation
 */
public class TaxPiece extends BoardPiece {
    
    private final int taxAmount;
    
    /**
     * TaxPiece Constructor
     * @param title
     * @param taxAmount 
     */
    public TaxPiece(String title, int taxAmount) {
        super(title);
        this.taxAmount = taxAmount;
    }

    /**
     * Gets the tax amount for a player to pay to the bank
     * @return taxAmount
     */
    public int getTaxAmount() {
        return taxAmount;
    }
    
    
}
