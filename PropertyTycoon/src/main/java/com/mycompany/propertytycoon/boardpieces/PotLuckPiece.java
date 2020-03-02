package com.mycompany.propertytycoon.boardpieces;

/**
 *  Pot Luck Piece represents the PotLuck tile on the board
 *  that a player can land on.
 *  @author Big Nerd Notation
 */
public class PotLuckPiece extends BoardPiece {
    
    private String action;
    
    /**
     * PotLuckPiece constructor
     * @param title
     * @param action 
     */
    public PotLuckPiece(String title, String action) {
        super(title);
        this.action = action;
    }
    
    /**
     * Returns the action of the PotLuckPiece object
     * @return action
     */
    public String getAction() {
        return action;
    }
    
}
