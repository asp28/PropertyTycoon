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
public class Property extends BoardPiece {

    private final String group;
    private final int cost;
    private String rent;
    private String ownedBuy;

    public Property(String name, String group, int cost, String rent) {
        super(name);
        this.rent = rent;
        this.group = group;
        this.cost = cost;
        ownedBuy = "The Bank";
    }

    public String getGroup() {
        return group;
    }

    public int getCost() {
        return cost;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getOwnedBuy() {
        return ownedBuy;
    }

    public void setOwnedBuy(String ownedBuy) {
        this.ownedBuy = ownedBuy;
    }

}
