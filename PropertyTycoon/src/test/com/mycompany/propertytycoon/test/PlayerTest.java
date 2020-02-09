package com.mycompany.propertytycoon.test;

import com.mycompany.propertytycoon.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {


    @Test
    public void testRollDice()
    {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test","green","",true,1000,"10",a,100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board(properties);
        Player player = new Player(board,dice,bank);

        player.rollDice();
        System.out.println(player.getGetPlayerLocation());
    }


    @Test
    public void testRoundTheBoard()
    {ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test","green","",true,1000,"10",a,100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board(properties);
        Player player = new Player(board,dice,bank);
        player.setGetPlayerLocation(42);
        player.rollDice();
        System.out.println(player.getGetPlayerLocation());
    }

    @Test
    public void testBuy()
    {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        PropertyCards propertyCards = new PropertyCards("test","green","",true,1000,"10",a,100);
        ArrayList<PropertyCards> properties = new ArrayList<PropertyCards>();
        properties.add(propertyCards);
        Bank bank = new Bank(properties);
        Dice dice = new Dice();
        Board board = new Board(properties);
        Player player = new Player(board,dice,bank);

        player.setGetPlayerLocation(0);
        PropertyCards propertyLocation = board.getBoardLocations().get(player.getGetPlayerLocation());
        player.buyProperty(propertyLocation);

        Assertions.assertEquals(1,player.getOwnedProperties().size());

    }


}
