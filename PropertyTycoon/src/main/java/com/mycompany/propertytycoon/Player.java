/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;


import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;

/**
 * @author christian Saraty
 * @author Big Nerd Notation
 * @version 1.0
 * <p>
 * This is the player class that the user will have actions to control
 */
public class Player {
    private int playerLocation = 0;
    private ArrayList<BoardPiece> ownedProperties = new ArrayList<>();
    private int playerBalance;
    private boolean inJail = false;
    private int doublesRolled = 0;

    private Board board;
    private Bank bank;
    private String character;
    private Token token;

    public Bank getBank() {
        return bank;
    }

    /**
     * Player Constructor method
     *
     * @param character
     * @param token
     */
    public Player(String character, Token token) {
        playerBalance = 1500;
        this.character = character;
        this.token = token;
    }

    /**
     * Getter of character name
     * @return String of variable 'character'
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Gets the location of the player around the board by returning an integer which represents the players location in the board location array
     * @return current player Location
     */
    public int getPlayerLocation() {
        return playerLocation;
    }

    /**
     * Used for testing and for card actions later on
     * @param playerLocation
     */
    public void setPlayerLocation(int playerLocation) {
        this.playerLocation = playerLocation;
    }

    /**
     * Gets the owned Properties of the player
     * @return the players owned PropertyCards
     */
    public ArrayList<BoardPiece> getOwnedProperties() {
        return ownedProperties;
    }

    /**
     * Gets the players balance
     * @return an integer that represents the players balance
     */
    public int getPlayerBalance() {
        return playerBalance;
    }

    /**
     * Gets player token
     * @return String of token
     */
    public String getToken() {
        return token.name();
    }

    /**
     * Gets a boolean value of if the player is in jail
     * @return boolean value of true if player is in jail
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Gets the amount of doubles rolled
     * @return integer value of the amount of doubles rolled this term
     */
    public int getDoublesRolled() {
        return doublesRolled;
    }

    /**
     * Updates the variable that represents the location by adding the parameter to the current value
     * @param moveAmountOfPos
     */
    private void move(int moveAmountOfPos) {

        playerLocation += moveAmountOfPos;
    }

    /**
     * Used to roll both dices, check for doubles and roll again and if the amount of doubles is 3 times or more will move the player to jail
     */
    public void rollDice() {
        int diceOne = 0;
        int diceTwo = 0;
        int totalMovement = 0;
        while (doublesRolled < 3 && diceOne == diceTwo) {
            /**
             * SEE HERE, DICE NO LONGER EXISTS
             */
            //diceOne = dice.roll();
            //diceTwo = dice.roll();
            totalMovement = diceOne + diceTwo;
            if (diceOne == diceTwo) {
                doublesRolled++;
            }
        }
        if (doublesRolled >= 3) {
            //Some sort of calculation to move the player to jail for next dev cycle
            //Test Jail value pos 50
            int moveToJail = 50 - playerLocation;
            doublesRolled = 0;
            move(moveToJail);
        } else {
            //Test Size of board = 42
            if (playerLocation + totalMovement > board.getBoardLocations().size()) {
                int moveValue = playerLocation + totalMovement - board.getBoardLocations().size();
                playerLocation = 1;
                doublesRolled = 0;
                move(moveValue);
            }
            doublesRolled = 0;
            move(totalMovement);
        }
    }

    /**
     * Gets all the actions that can be performed on the location for that user
     * Currently can check if the player location is owned by the bank
     */
    public String viewActionsOnBoardPosition() {
        String commandsOnThatProperty = "";
        BoardPiece propertyCards = board.getBoardLocations().get(playerLocation);
        //Check if the property can be brought
        String nameOfProperty = propertyCards.getTitle();
        if (propertyCards instanceof Property) {

            if (bank.getProperties(nameOfProperty) != null) {
                commandsOnThatProperty = "BUY";
            } else {
                commandsOnThatProperty = "RENT";
            }
        } else {

        }
        return commandsOnThatProperty;
    }

    /**
     * Used to buy the property that the player is currently located at
     * Checks if the player has the correct balance to be able to afford the property and that the bank still owns it
     * Adds the property to the players ownedProperties array
     * Removes the property from the bank's owned properties
     *
     * @param property
     * @param location
     */
    public void buyProperty(BoardPiece property, int location) {
        String nameOfProperty = property.getTitle();
        //Check the balance
        if (property instanceof Property) {
            Property prop = (Property) property;
            if (playerBalance >= prop.getCost()) {
                playerBalance = playerBalance - prop.getCost();
                ownedProperties.add(property);
                bank.removeProperties(nameOfProperty);
                prop.setOwnedBuy(getCharacter());
            } else {
                System.out.println("Don't currently have the funds to be able to afford this property");
            }
        }

    }

    /**
     * Used when a player lands on another player's property and must pay rent. Returns in "paid".
     * If the player cannot pay rent, returns "unableToPay"
     * @param property
     * @param ownerOfProperty
     * @return 
     */
    public String payRent(BoardPiece property, Player ownerOfProperty) {
        Property prop = (Property) property;
        int rentOwed = Integer.parseInt(prop.getRent());
        if ((playerBalance - rentOwed) >= 0) {
            playerBalance = playerBalance - rentOwed;
            ownerOfProperty.increaseBalance(rentOwed);
            return "PAID";
        } else {
            return "UNABLETOPAY";
        }

    }

    /**
     * Increases the player balance by a certain value when receiving rent
     *
     * @param value
     */
    public void increaseBalance(int value) {
        playerBalance += value;
    }

    public void setOwnedProperties(ArrayList<BoardPiece> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }
}
