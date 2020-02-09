package com.mycompany.propertytycoon.test;

import com.mycompany.propertytycoon.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class PlayerTest {


    /**
     * Test that rolling the dice will cause the player to move to that location that it is not on;
     */
    @Test
    public void testRollDice() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test", "green", "", true, 1000, "10", a, 100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board(properties);
        Player player = new Player(board, dice, bank);

        player.rollDice();
        Assertions.assertTrue(player.getPlayerLocation() != 0);
    }


    /**
     * Test that the player will move around the board resetting the location back to 0 when it reaches the last known location
     */
    @Test
    public void testRoundTheBoard() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test", "green", "", true, 1000, "10", a, 100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board(properties);
        Player player = new Player(board, dice, bank);
        player.setPlayerLocation(42);
        player.rollDice();
        System.out.println(player.getGetPlayerLocation());
    }

    /**
     * Testing that the player will be able to successfully buy a property and add it to the players owned property list
     */
    @Test
    public void testBuy() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test", "green", "", true, 1000, "10", a, 100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board(properties);
        Player player = new Player(board,dice,bank);

        player.setPlayerLocation(0);
        PropertyCards propertyLocation = board.getBoardLocations().get(player.getGetPlayerLocation());
        player.buyProperty(propertyLocation);

        Assertions.assertEquals(1,player.getOwnedProperties().size());

    }


}
