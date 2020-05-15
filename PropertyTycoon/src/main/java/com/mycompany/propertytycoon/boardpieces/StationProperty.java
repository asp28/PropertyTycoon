package com.mycompany.propertytycoon.boardpieces;

/**
 *  Station Property represents the 4 station properties
 *  @author Big Nerd Notation
 */
public class StationProperty extends Property {
    
    /**
     * StationProperty constructor
     * @param name name of station
     * @param group group of station
     * @param cost cost of station
     * @param rent rent of station
     */
    public StationProperty(String name, String group, int cost, String rent) {
        super(name, group, cost, rent);
    }
    
}
