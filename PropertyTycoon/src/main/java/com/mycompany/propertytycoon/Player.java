package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.cards.Card;
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.cards.PotLuck;
import java.util.ArrayList;

/**
 * Property Tycoon Player
 */
public class Player {
    private String name;
    private int location;
    private ArrayList<Property> ownedProperties;
    private int balance;
    private boolean inJail;
    private String token;
    private int gameloops;
    private int playerTurns;
    private boolean isAI;
    private OpportunityKnocks GOJFoppoknocks;
    private PotLuck GOJFpotluck;
    
    

    /**
     * Player constructor
     */
    public Player() {
        this.location = 0;
        this.gameloops = 0;
        this.playerTurns = 0;
        this.ownedProperties = new ArrayList<>();
        this.balance = 1500;
        this.inJail = false;
        this.isAI = false;
    }
    
    public Card getOutOfJailCard() {
        if (GOJFoppoknocks != null && GOJFpotluck == null) {
            return GOJFoppoknocks;
        } else if (GOJFpotluck != null && GOJFoppoknocks == null){
            return GOJFpotluck;
        } else if (GOJFoppoknocks == null && GOJFpotluck == null) {
            return null;
        }
        return GOJFoppoknocks;
    }

    //Getters and Setters

    /**
     * Returns player's name
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets player's name
     * @param name player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets player's location
     * @return player location
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets player's location
     * @param location player's location
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Gets player's owned properties
     * @return list of player's owned properties
     */
    public ArrayList<Property> getOwnedProperties() {
        return ownedProperties;
    }

    /**
     * Set player's properties
     * @param ownedProperties list of player's properties
     */
    public void setOwnedProperties(ArrayList<Property> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }

    /**
     * Gets player's balance
     * @return player's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets player's balance
     * @param balance player's balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Is player in jail
     * @return true if player in jail
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Puts player in or out of jail
     * @param inJail is player in jail?
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Gets player's token
     * @return player's token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set player's token
     * @param token one of six tokens
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets player's gameLoops
     * @return how many loops around the board player has traveled
     */
    public int getGameloops() {
        return gameloops;
    }

    /**
     * Set player's gameLoops
     * @param gameloops loops around the board
     */
    public void setGameloops(int gameloops) {
        this.gameloops = gameloops;
    }

    /**
     * Gets player's turn counter
     * @return player's turn counter
     */
    public int getPlayerTurns() {
        return playerTurns;
    }

    /**
     * Sets player's turn counter
     * @param playerTurns amount of turns
     */
    public void setPlayerTurns(int playerTurns) {
        this.playerTurns = playerTurns;
    }

    /**
     * Is the player an AI
     * @return true if player an AI
     */
    public boolean isIsAI() {
        return isAI;
    }

    /**
     * Sets whether player is AI
     * @param isAI is player AI?
     */
    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    /**
     * Gets GetOutOfJailFree Opportunity Knocks card
     * @return Opportunity Knocks card
     */
    public OpportunityKnocks getGOJFoppoknocks() {
        return GOJFoppoknocks;
    }

    /**
     * Sets the GetOutOfJailFree Opportunity Knocks card
     * @param GOJFoppoknocks OpportunityKnocks card
     */
    public void setGOJFoppoknocks(OpportunityKnocks GOJFoppoknocks) {
        this.GOJFoppoknocks = GOJFoppoknocks;
    }

    /**
     * Gets GetOutOfJailFree PotLuck card
     * @return PotLuck card
     */
    public PotLuck getGOJFpotluck() {
        return GOJFpotluck;
    }

    /**
     * Sets the GetOutOfJailFree PotLuck card
     * @param GOJFpotluck PotLuck card
     */
    public void setGOJFpotluck(PotLuck GOJFpotluck) {
        this.GOJFpotluck = GOJFpotluck;
    }
    
    
    //Methods
    

    /**
     * Add a property to player's owned properties
     * @param prop property to be added
     */
    public void addProperty(Property prop){
        ownedProperties.add(prop);
    }
    
    /**
     * Remove a property from player's owned properties
     * @param prop property to be removed
     */
    public void removeProperty(Property prop){
        ownedProperties.remove(prop);
    }

    /**
     * Increase player's balance by a certain amount
     * @param value amount to increase by
     */
    public void increaseBalance(int value) {
        balance += value;
    }

    /**
     * Decrease player's balance by a certain amount
     * @param value amount to decrease by
     */
    public void decreaseBalance(int value) {
        balance -= value;
    }

    /**
     * Increments player's gameLoop counter
     */
    public void incrementGameloops() {
        this.gameloops++;
    }

    /**
     * Increments player's turn counter
     */
    public void incrementPlayerTurns() {
        this.playerTurns++;
    }
    
    /**
     * Sets GetOutOfJailFree card of Opportunity Knocks
     * @param card OpportunityKnocks card
     */
    public void setGOJF(OpportunityKnocks card) {
        GOJFoppoknocks = card;
    }
    
    /**
     * Sets GetOutOfJailFree card of PotLuck
     * @param card PotLuck card
     */
    public void setGOJF(PotLuck card) {
        GOJFpotluck = card;
    }
    
    /**
     * Removes GetOutOfJailFree card of OpportunityKnocks
     */
    public void removeGOJFoppo() {
        GOJFoppoknocks = null;
    }
    
    /**
     * Removes GetOutOfJailFree card of PotLuck
     */
    public void removeGOJFpotluck() {
        GOJFpotluck = null;
    }


}
    