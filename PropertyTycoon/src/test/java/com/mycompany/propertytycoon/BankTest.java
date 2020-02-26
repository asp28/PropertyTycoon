/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Duarte
 */
public class BankTest {

    private ArrayList<Property> properties1 = new ArrayList<>();
    private ArrayList<Property> properties2 = new ArrayList<>();
    private Bank b = new Bank(properties1);

    public BankTest() {
        Property p1 = new Property("Asda", "5", 5, "someone");
        Property p2 = new Property("Sainsburrys", "5", 5, "someone");
        Property p3 = new Property("Tesco", "5", 5, "someone");
        properties1.add(p1);
        properties1.add(p2);
        properties2.add(p3);

    }

    @Test
    public void getTotalMoneyTest() {
        assertEquals(b.getTotalMoney(), 50000);
    }

    @Test
    public void setTotalMoneyTest() {
        b.setTotalMoney(500);
        assertEquals(b.getTotalMoney(), 500);
    }

    @Test
    public void getUnownedPropertiesTest() {

        assertEquals(b.getUnownedProperties(), properties1);
    }

    @Test
    public void boardLocationsTest() {
        b.setUnownedProperties(properties2);
        assertEquals(b.getUnownedProperties(), properties2);
    }
}
