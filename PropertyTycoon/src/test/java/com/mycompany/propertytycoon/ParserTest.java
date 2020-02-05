/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
public class ParserTest {
    ArrayList<PropertyCards> board;
    
    public ParserTest() throws IOException, InvalidFormatException {
        Parser p = new Parser();
        board = p.boardMaker();
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
     * Test of boardMaker method, of class Parser.
     * @throws java.lang.Exception
     */
    @Test
    public void testBoardMaker() throws Exception {
        System.out.println("boardMaker");
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Go");
        expResult.add("Crapper Street");
        expResult.add("Pot Luck");
        expResult.add("Gangsters Paradise");
        expResult.add("Income Tax");
        expResult.add("Brighton Station");
        expResult.add("Weeping Angel");
        expResult.add("Opportunity Knocks");
        expResult.add("Potts Avenue");
        expResult.add("Nardole Drive");
        expResult.add("Jail/Just visiting");
        expResult.add("Skywalker Drive");
        expResult.add("Tesla Power Co");
        expResult.add("Wookie Hole");
        expResult.add("Rey Lane");
        expResult.add("Hove Station");
        expResult.add("Cooper Drive");
        expResult.add("Pot Luck");
        expResult.add("Wolowitz Street");
        expResult.add("Penny Lane");
        expResult.add("Free Parking");
        expResult.add("Yue Fei Square");
        expResult.add("Opportunity Knocks");
        expResult.add("Mulan Rouge");
        expResult.add("Han Xin Gardens");
        expResult.add("Falmer Station");
        expResult.add("Kirk Close");
        expResult.add("Picard Avenue");
        expResult.add("Edison Water");
        expResult.add("Crusher Creek");
        expResult.add("Go to Jail");
        expResult.add("Sirat Mews");
        expResult.add("Ghengis Crescent");
        expResult.add("Pot Luck");
        expResult.add("Ibis Close");
        expResult.add("Lewes Station");
        expResult.add("Opportunity Knocks");
        expResult.add("Hawking Way");
        expResult.add("Super Tax");
        expResult.add("Turing Heights");
        ArrayList<String> result = new ArrayList<>();
        for (PropertyCards pc : board) {
            result.add(pc.getName());
        }
        assertEquals(expResult, result);
    }
    
}
