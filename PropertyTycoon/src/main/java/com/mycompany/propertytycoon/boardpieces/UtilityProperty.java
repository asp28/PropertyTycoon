package com.mycompany.propertytycoon.boardpieces;

/**
 *  Utility Properties represent the Electricity and Water properties
 *  @author Big Nerd Notation
 */
public class UtilityProperty extends Property {
    
    /**
     * UtilityProperty constructor
     * @param name name of utility
     * @param group group of utility
     * @param cost price of utility
     * @param rent rent of utility
     */
    public UtilityProperty(String name, String group, int cost, String rent) {
        super(name, group, cost, rent);
    }
    
}
