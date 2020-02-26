package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;

/**
 * PropertyTycoon Bank
 *
 * @author Big Nerd Notation
 */
public class Bank {

    private int totalMoney;
    private ArrayList<Property> unownedProperties = new ArrayList<>();

    /**
     * Bank constructor
     *
     * @param properties property cards
     */
    public Bank(ArrayList<Property> properties) {
        totalMoney = 50000;
        unownedProperties = properties;
    }

    /**
     * getter for bank money
     *
     * @return total money
     */
    public int getTotalMoney() {
        return totalMoney;
    }

    /**
     * setter for bank money
     *
     * @param totalMoney
     */
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * getter for bank properties
     *
     * @return unowned properties
     */
    public ArrayList<Property> getUnownedProperties() {
        return unownedProperties;
    }

    /**
     * setter for bank properties
     *
     * @param unownedProperties
     */
    public void setUnownedProperties(ArrayList<Property> unownedProperties) {
        this.unownedProperties = unownedProperties;
    }

}
