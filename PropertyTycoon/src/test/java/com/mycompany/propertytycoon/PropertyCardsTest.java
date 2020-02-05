/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ankeet
 */
public class PropertyCardsTest {

    PropertyCards pc;

    public PropertyCardsTest() {
        ArrayList<Integer> houses = new ArrayList<>();
        houses.add(50);
        houses.add(60);
        houses.add(90);
        houses.add(150);
        houses.add(200);
        pc = new PropertyCards("Sainsburys", "Indigo", "Pay 500", true, 500, "50", houses, 50);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHouseCost method, of class PropertyCards.
     */
    @Test
    public void testGetHouseCost() {
        System.out.println("getHouseCost");
        int expResult = 50;
        int result = pc.getHouseCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class PropertyCards.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Sainsburys";
        String result = pc.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGroup method, of class PropertyCards.
     */
    @Test
    public void testGetGroup() {
        System.out.println("getGroup");
        String expResult = "Indigo";
        String result = pc.getGroup();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAction method, of class PropertyCards.
     */
    @Test
    public void testGetAction() {
        System.out.println("getAction");
        String expResult = "Pay 500";
        String result = pc.getAction();
        assertEquals(expResult, result);

    }

    /**
     * Test of isCanBeBought method, of class PropertyCards.
     */
    @Test
    public void testIsCanBeBought() {
        System.out.println("isCanBeBought");
        boolean expResult = true;
        boolean result = pc.isCanBeBought();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCost method, of class PropertyCards.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        int expResult = 500;
        int result = pc.getCost();
        assertEquals(expResult, result);

    }

    /**
     * Test of getHouses method, of class PropertyCards.
     */
    @Test
    public void testGetHouses() {
        System.out.println("getHouses");
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(50);
        expResult.add(60);
        expResult.add(90);
        expResult.add(150);
        expResult.add(200);
        ArrayList<Integer> result = pc.getHouses();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRent method, of class PropertyCards.
     */
    @Test
    public void testGetRent() {
        System.out.println("getRent");
        String expResult = "50";
        String result = pc.getRent();
        assertEquals(expResult, result);

    }

}
