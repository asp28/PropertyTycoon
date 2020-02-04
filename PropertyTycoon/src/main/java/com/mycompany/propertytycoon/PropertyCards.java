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
public class PropertyCards {

    private String name;
    private String group;
    private String action;
    private boolean canBeBought;
    private int cost;
    private ArrayList<Integer> houses;
    private int rent;

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getAction() {
        return action;
    }

    public boolean isCanBeBought() {
        return canBeBought;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Integer> getHouses() {
        return houses;
    }

    public int getRent() {
        return rent;
    }

    public PropertyCards(String name, String group, String action, boolean canBeBought, int cost, int rent, ArrayList<Integer> houses) {
        this.rent = rent;
        this.name = name;
        this.action = action;
        this.group = group;
        this.canBeBought = canBeBought;
        this.cost = cost;
        this.houses = houses;
    }
}