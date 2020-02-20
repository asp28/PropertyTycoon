/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import java.util.ArrayList;

/**
 *
 * @author ankeet
 */
public class JailPiece extends BoardPiece{
    private ArrayList<String> heldPlayers;
    
    public JailPiece(String title) {
        super(title);
        heldPlayers = new ArrayList<>();
    }
    
    public ArrayList<String> getHeldPlayers() {
        return heldPlayers;
    }

    public void addPlayer(String name) {
        heldPlayers.add(name);
    }
    
}
