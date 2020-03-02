package com.mycompany.propertytycoon.boardpieces;

/**
 *  GoPiece represents the Go tile on the board
 *  @author Big Nerd Notation
 */
public class GoPiece extends BoardPiece {
    
    private String action;
    
    /**
     * GoPiece constructor
     * @param title
     * @param action 
     */
    public GoPiece(String title, String action) {
        super(title);
        this.action = action;
    }

    /**
     * Gets the action in relation to the GoPiece
     * @return action
     */
    public String getAction() {
        return action;
    }
    
}
