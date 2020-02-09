/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import java.util.ArrayList;

/**
 * @author christian Saraty
 */
public class Player {
    private int getPlayerLocation = 1;
    private ArrayList<PropertyCards> ownedProperties = new ArrayList<PropertyCards>();
    private int playerBalance;
    private boolean inJail;
    private int doublesRolled;

    private Board board;
    private Dice dice;
    private Bank bank;

    public Player(Board board, Dice dice, Bank bank) {
        playerBalance = 1500;
        this.board = board;
        this.dice = dice;
        this.bank = bank;
    }

    public int getGetPlayerLocation() {
        return getPlayerLocation;
    }

    public ArrayList<PropertyCards> getOwnedProperties() {
        return ownedProperties;
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getDoublesRolled() {
        return doublesRolled;
    }

    private void move(int moveAmountOfPos) {

        getPlayerLocation += moveAmountOfPos;
    }

    public void setGetPlayerLocation(int getPlayerLocation) {
        this.getPlayerLocation = getPlayerLocation;
    }

    /**
     * Used to roll both dices
     */
    public void rollDice() {
        int diceOne = 0;
        int diceTwo = 0;
        int totalMovement = 0;
        while (doublesRolled < 3 && diceOne == diceTwo) {
            diceOne = dice.roll();
            diceTwo = dice.roll();
            totalMovement = diceOne + diceTwo;
            if (diceOne == diceTwo) {
                doublesRolled++;
            }
        }
        if (doublesRolled >= 3) {
            //Some sort of calculation to move the player to jail for next dev cycle
            //Test Jail value pos 50
            int moveToJail = 50 - getPlayerLocation;
            move(moveToJail);
        } else {
            //Test Size of board = 42
            if (getPlayerLocation + totalMovement > 42) {
                int moveValue = getPlayerLocation + totalMovement - 42;
                getPlayerLocation = 1;
                move(moveValue);
            }

            move(totalMovement);
        }
    }

    public void viewActionsOnBoardPosition() {
        PropertyCards propertyCards = board.getBoardLocations().get(getPlayerLocation);
        //Check if the property can be brought
        String nameOfProperty = propertyCards.getName();
        if (propertyCards.isCanBeBought()) {

            if (bank.getProperties(nameOfProperty) != null) {
                //Do Something that will enable the property to be able to be brought as it is still owned by the bank
            }
        } else {
            //Will need to in the future check if the card is one of the chest or owned by another player and have the actions required!
        }
    }

    public void buyProperty(PropertyCards property) {
        String nameOfProperty = property.getName();
        //Check the balance
        if (property.isCanBeBought()) {
            if (playerBalance >= property.getCost()) {
                playerBalance = playerBalance - property.getCost();
                ownedProperties.add(property);
                bank.removeProperties(nameOfProperty);
            } else {
                System.out.println("Don't currently have the funds to be able to afford this property");
            }
        }

    }


}
