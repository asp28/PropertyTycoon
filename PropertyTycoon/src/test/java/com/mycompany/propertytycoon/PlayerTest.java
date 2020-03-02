package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {
    
    public Player player;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player = new Player();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void nameTest(){
        String name = "Jekyll";
        player.setName(name);
        assertEquals(player.getName(), name);
    }
    
    @Test
    public void locationTest(){
        assertEquals(player.getLocation(), 0);
        player.setLocation(10);
        assertEquals(player.getLocation(), 10);
    }
    
    @Test
    public void ownedPropertiesTest(){
        ArrayList<Property> props = new ArrayList<>();
        Property prop = new Property("London Road", "Blue", 20, "5");
        props.add(prop);
        player.setOwnedProperties(props);
        assertEquals(player.getOwnedProperties(), props);
    }
    
    @Test
    public void balanceTest(){
        assertEquals(player.getBalance(), 1500);
        player.setBalance(2500);
        assertEquals(player.getBalance(), 2500);
    }
    
    @Test
    public void inJailTest(){
        assertEquals(player.isInJail(), false);
        player.setInJail(true);
        assertEquals(player.isInJail(), true);
    }
    
    @Test
    public void tokenTest(){
        String token = "cat";
        player.setToken(token);
        assertEquals(player.getToken(), token);
        
    }
}
