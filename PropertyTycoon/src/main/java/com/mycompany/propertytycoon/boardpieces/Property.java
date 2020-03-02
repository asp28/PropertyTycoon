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

    /**
     * Each object is created in the Parser class
     * Each property object is initially owned by the bank
     * @param name
     * @param group
     * @param cost
     * @param rent 
     */
    public Property(String name, String group, int cost, String rent) {
        super(name);
        this.rent = rent;
        this.group = group;
        this.cost = cost;
        ownedBuy = "The Bank";
    }

    /**
     * Gets the property's group
     * @return group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Gets the property's cost
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the property's rent
     * @return rent
     */
    public String getRent() {
        return rent;
    }

    /**
     * Sets the property's rent
     * @param rent 
     */
    public void setRent(String rent) {
        this.rent = rent;
    }

    /**
     * Gets the owner of the property
     * @return 
     */
    public String getOwnedBuy() {
        return ownedBuy;
    }

    /**
     * Sets the owner of the property as a player name or the bank
     * @param ownedBuy 
     */
    public void setOwnedBuy(String ownedBuy) {
        this.ownedBuy = ownedBuy;
    }

}
