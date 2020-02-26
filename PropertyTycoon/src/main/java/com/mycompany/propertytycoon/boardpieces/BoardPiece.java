package com.mycompany.propertytycoon.boardpieces;

/**
 *  BoardPiece represents any tile on the board
 *  @author Big Nerd Notation
 */
public class BoardPiece {
    
    private final String title;
    
    /**
     * BoardPiece constructor
     * @param title 
     */
    public BoardPiece(String title) {
        this.title = title;
    }

    /**
     * Gets the title/name of the BoardPiece
     * @return 
     */
    public String getTitle() {
        return title;
    }
    
}
