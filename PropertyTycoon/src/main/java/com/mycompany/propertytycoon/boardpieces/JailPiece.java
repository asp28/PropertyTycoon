package com.mycompany.propertytycoon.boardpieces;

import java.util.ArrayList;

/**
 *  JailPiece represents the Jail tile on the board
 *  @author Big Nerd Notation
 */
public class JailPiece extends BoardPiece{
    private ArrayList<String> heldPlayers;
    
    /**
     * JailPiece constructor
     * @param title 
     */
    public JailPiece(String title) {
        super(title);
        heldPlayers = new ArrayList<>();
    }
    
    /**
     * Gets the players that are currently in jail
     * @return heldPlayers
     */
    public ArrayList<String> getHeldPlayers() {
        return heldPlayers;
    }

    /**
     * Adds a player to jail
     * @param name 
     */
    public void addPlayer(String name) {
        heldPlayers.add(name);
    }
    
    /**
     * Removes a player from jail
     * @param name 
     */
    public void removePlayer(String name){
        heldPlayers.remove(name);
    }
    
}
