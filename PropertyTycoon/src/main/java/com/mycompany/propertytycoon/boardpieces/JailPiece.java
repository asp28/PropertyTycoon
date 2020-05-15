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
     * @param title name of jail
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
     * @param name Name of player to be added to jail
     */
    public void addPlayer(String name) {
        heldPlayers.add(name);
    }
    
    /**
     * Removes a player from jail
     * @param name Name of player to be removed from jail
     */
    public void removePlayer(String name){
        heldPlayers.remove(name);
    }
    
}
