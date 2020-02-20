package com.mycompany.propertytycoon;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ankeet
 */
public class ColouredProperty extends Property {
    
    private final ArrayList<Integer> houses;
    private final int houseCost;
    
    public ColouredProperty(String name, String group, int cost, String rent, ArrayList<Integer> houses, int houseCost) {
        super(name, group, cost, rent);
        this.houseCost = houseCost;
        this.houses = houses;
    }

    public ArrayList<Integer> getHouses() {
        return houses;
    }

    public int getHouseCost() {
        return houseCost;
    }
    
    
}
