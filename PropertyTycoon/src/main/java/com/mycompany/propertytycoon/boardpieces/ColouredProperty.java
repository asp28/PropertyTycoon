package com.mycompany.propertytycoon.boardpieces;
import java.util.ArrayList;

/**
 * ColouredProperty represents a property that is part of a subgroup of Property
 *
 * @author Big Nerd Notation
 */
public class ColouredProperty extends Property {

    private final ArrayList<Integer> houses;
    private final int houseCost;
    private int houseCount;

    /**
     * ColouredProperty constructor
     * @param name name of ColouredProperty
     * @param group group of ColouredProperty
     * @param cost cost of ColouredProperty
     * @param rent rent of ColouredProperty without houses
     * @param houses list of rent prices with associated houses of ColouredProperty
     * @param houseCost cost of purchasing a house on ColouredProperty
     */
    public ColouredProperty(String name, String group, int cost, String rent, ArrayList<Integer> houses, int houseCost) {
        super(name, group, cost, rent);
        this.houseCost = houseCost;
        this.houses = houses;
        this.houseCount = 0;
    }

    /**
     * Gets the ArrayList holding specific rents for specific houses
     * @return houses
     */
    public ArrayList<Integer> getHouses() {
        return houses;
    }

    /**
     * Gets the cost of a single house
     *
     * @return houseCost
     */
    public int getHouseCost() {
        return houseCost;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }
}
