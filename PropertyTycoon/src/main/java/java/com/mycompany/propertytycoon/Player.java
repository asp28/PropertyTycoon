/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.com.mycompany.propertytycoon;


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
    private ArrayList<PropertyCards> ownedProperties = new ArrayList<PropertyCards>();
    private int playerBalance;
    private boolean inJail = false;
    private int doublesRolled = 0;

    private Board board;
    private Dice dice;
    private Bank bank;

    /**
     * Bank Constructor method
     * @param board
     * @param dice
     * @param bank
     */
    public Player(Board board, Dice dice, Bank bank) {
        playerBalance = 1500;

        this.board = board;
        this.dice = dice;
        this.bank = bank;
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
    public ArrayList<PropertyCards> getOwnedProperties() {
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
            int moveToJail = 50 - playerLocation;
            doublesRolled = 0;
            move(moveToJail);
        } else {
            //Test Size of board = 42
            if (playerLocation + totalMovement > 42) {
                int moveValue = playerLocation + totalMovement - 42;
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
    public void viewActionsOnBoardPosition() {
        PropertyCards propertyCards = board.getBoardLocations().get(playerLocation);
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

    /**
     * Used to buy the property that the player is currently located at
     * Checks if the player has the correct balance to be able to afford the property and that the bank still owns it
     * Adds the property to the players owenedpropertyArray
     * Removes the property from the banks owned properties
     *
     * @param property
     */
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
