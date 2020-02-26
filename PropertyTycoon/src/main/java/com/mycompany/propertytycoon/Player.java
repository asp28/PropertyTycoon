package com.mycompany.propertytycoon;


import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;

/**
 * @author Big Nerd Notation
 * @version 1.0
 * Player class to hold player's location, their ownedProperties, etc.
 */
public class Player {
    private String name;
    private int location;
    private ArrayList<Property> ownedProperties;
    private int balance;
    private boolean inJail;
    private String token;

    /**
     * Player constructor
     */
    public Player() {
        this.location = 0;
        this.ownedProperties = new ArrayList<>();
        this.balance = 1500;
        this.inJail = false;
    }
    
    
    //Getters and Setters
    

    /**
     * Gets the player's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the player's location on the board
     * @return location
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the location of the player
     * @param location 
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Returns all the properties the player owns
     * @return ownedProperties
     */
    public ArrayList<Property> getOwnedProperties() {
        return ownedProperties;
    }

    /**
     * Sets all owned properties of player
     * @param ownedProperties 
     */
    public void setOwnedProperties(ArrayList<Property> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }

    /**
     * Returns current player balance
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets player balance
     * @param balance 
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    /**
     * Increases the player balance by a certain value when receiving rent
     *
     * @param value
     */
    public void increaseBalance(int value) {
        balance += value;
    }    

    /**
     * Returns true whether player is in jail
     * @return inJail
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Decides whether player is in jail
     * @param inJail 
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Returns the player's token
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the player's token
     * @param token 
     */
    public void setToken(String token) {
        this.token = token;
    }

    public Bank getBank() {
        return bank;
    }
    
    
    //Methods
    
    /**
     * Adds a property to player's owned properties
     * @param prop 
     */
    public void addProperty(Property prop){
        ownedProperties.add(prop);
    }
    
    /**
     * Removes a property from player's owned properties
     * @param prop 
     */
    public void removeProperty(Property prop){
        ownedProperties.remove(prop);
    }
}
    