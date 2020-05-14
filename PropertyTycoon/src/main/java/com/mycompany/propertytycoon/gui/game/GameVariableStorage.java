/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;

/**
 *
 * @author ankeet
 */
public class GameVariableStorage {
    
    private static GameVariableStorage GVS;
    
    private ArrayList<Player> players;
    private Property auctionProperty;
    private ArrayList<String> actions;
    private boolean rent = true;
    
    private boolean rolled = false;
    
    public static GameVariableStorage getInstance() {
        if (GVS == null) {
            GVS = new GameVariableStorage();
        }
        return GVS;
    }
        public void tradePlayers(Player trader, Player other) {
        players = new ArrayList<Player>() {
            {
                add(trader);
                add(other);
            }
        };
    }
    
    public ArrayList<Player> getTraders() {
        return players;
    }
    
    public void setAuctionProperty(BoardPiece prop) {
        auctionProperty = (Property) prop;
    }
    
    public Property getAuctionProperty() {
        return auctionProperty;
    }
    
    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }
    
    public ArrayList<String> getActions() {
        return actions;
    }
    
    public boolean getRoll() {
        return rolled;
    }
    
    public void setRolled(boolean roll) {
        rolled = roll;
    }
    
    public void setRent(boolean rentPaid) {
        rent = rentPaid;
    }
    
    public boolean getRentPaid() {
        return rent;
    }

}
