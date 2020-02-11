package com.mycompany.propertytycoon;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerTest {


    /**
     * Test that rolling the dice will cause the player to move to that location that it is not on;
     */
    @Test
    public void testRollDice() throws IOException, InvalidFormatException {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test", "green", "", true, 1000, "10", a, 100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board();
        Player player = new Player(board, dice, bank);

        player.rollDice();
        Assertions.assertTrue(player.getPlayerLocation() != 0);
    }


    /**
     * Test that the player will move around the board resetting the location back to 0 when it reaches the last known location
     */
    @Test
    public void testRoundTheBoard() throws IOException, InvalidFormatException {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test", "green", "", true, 1000, "10", a, 100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Dice dice = new Dice();
        Board board = new Board();
        Bank bank = new Bank(board.getBoardLocations());
        Player player = new Player(board, dice, bank);
        player.setPlayerLocation(42);
        player.rollDice();
        System.out.println(player.getPlayerLocation());
    }

    /**
     * Testing that the player will be able to successfully buy a property and add it to the players owned property list
     */
    @Test
    public void testBuy() throws IOException, InvalidFormatException {
        Dice dice = new Dice();
        Board board = new Board();
        Bank bank = new Bank(board.getBoardLocations());
        Player player = new Player(board, dice, bank);

        player.setPlayerLocation(1);
        PropertyCards propertyLocation = board.getBoardLocations().get(player.getPlayerLocation());
        player.buyProperty(propertyLocation);

        Assertions.assertEquals(1, player.getOwnedProperties().size());

    }


}
