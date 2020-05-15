package com.mycompany.propertytycoon.boardpieces;

/**
 * Property represents ownable board pieces
 * @author Big Nerd Notation
 */
public class Property extends BoardPiece {

    private final String group;
    private final int cost;
    private String rent;
    private String ownedBuy;
    private boolean isMortgaged;

    /**
     * Each object is created in the Parser class
     * Each property object is initially owned by the bank
     * @param name name of property
     * @param group group of property
     * @param cost cost of property
     * @param rent rent of property
     */
    public Property(String name, String group, int cost, String rent) {
        super(name);
        this.rent = rent;
        this.group = group;
        this.cost = cost;
        this.isMortgaged = false;
        ownedBuy = "The Bank";
    }

    /**
     * Gets the property's group
     * @return property group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Gets the property's cost
     * @return property cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the property's rent
     * @return property rent
     */
    public String getRent() {
        return rent;
    }

    /**
     * Sets the property's rent
     * @param rent proposed rent
     */
    public void setRent(String rent) {
        this.rent = rent;
    }

    /**
     * Gets the owner of the property
     * @return owner of property
     */
    public String getOwnedBuy() {
        return ownedBuy;
    }

    /**
     * Sets the owner of the property as a player name or the bank
     * @param ownedBuy player's name
     */
    public void setOwnedBuy(String ownedBuy) {
        this.ownedBuy = ownedBuy;
    }

    /**
     * Check if property is isMortgaged
     * @return true if isMortgaged; false otherwise
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * Sets property to be mortgaged or not
     * @param isMortgaged is the property mortgaged?
     */
    public void setMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

}
