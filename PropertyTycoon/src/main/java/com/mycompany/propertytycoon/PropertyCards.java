/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import java.util.ArrayList;

/**
 * @author BigNerdNotation
 * @version 1.0
 * <p>
 * A propertyCard is one piece of the board.
 */
public class PropertyCards {

    private String name;
    private String group;
    private String action;
    private boolean canBeBought;
    private int cost;
    private ArrayList<Integer> houses;
    private String rent;
    private int houseCost;
    private String ownedBuy;

    /**
     * @param name
     * @param group
     * @param action
     * @param canBeBought
     * @param cost
     * @param rent
     * @param houses
     * @param houseCost   Constructor of the propertyCard.
     */
    public PropertyCards(String name, String group, String action, boolean canBeBought, int cost, String rent, ArrayList<Integer> houses, int houseCost) {
        this.rent = rent;
        this.name = name;
        this.action = action;
        this.group = group;
        this.canBeBought = canBeBought;
        this.cost = cost;
        this.houses = houses;
        this.houseCost = houseCost;
        if (canBeBought) {
            ownedBuy = "The Board";
        }
        ownedBuy = "The Bank";
    }

    public String getOwnedBuy() {
        return ownedBuy;
    }

    public void setOwnedBuy(String ownedBuy) {
        this.ownedBuy = ownedBuy;
    }

    /**
     * @return cost of house
     */
    public int getHouseCost() {
        return houseCost;
    }

    /**
     * @return name of card
     */
    public String getName() {
        return name;
    }

    /**
     * @return group card belongs too
     */
    public String getGroup() {
        return group;
    }

    /**
     * @return returns any action the card does
     */
    public String getAction() {
        return action;
    }

    /**
     * @return whether or not the card can be bought (Cards such as Go cannot be
     * bought)
     */
    public boolean isCanBeBought() {
        return canBeBought;
    }

    /**
     * @return cost to buy card
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return a arraylist of all the houses(and hotel) and the rent it costs
     * with that many houses(or hotel)
     */
    public ArrayList<Integer> getHouses() {
        return houses;
    }

    /**
     * @return normal unaffected rent cost
     */
    public String getRent() {
        return rent;
    }
}
