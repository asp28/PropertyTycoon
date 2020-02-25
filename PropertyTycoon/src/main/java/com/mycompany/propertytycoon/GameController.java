package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.PotLuckPiece;
import com.mycompany.propertytycoon.boardpieces.JailPiece;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.FreeParkingPiece;
import com.mycompany.propertytycoon.boardpieces.GoPiece;
import com.mycompany.propertytycoon.boardpieces.GoToJailPiece;
import com.mycompany.propertytycoon.boardpieces.StationProperty;
import com.mycompany.propertytycoon.boardpieces.UtilityProperty;
import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.OpportunityKnocksPiece;
import com.mycompany.propertytycoon.boardpieces.TaxPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    private ArrayList<Player> amountOfPlayers = new ArrayList<>();
    private ArrayList<Integer> playerLocations = new ArrayList<>();
    private Board board;
    private Bank bank;
    //private PropertyTycoon GUI;

    private Player activePlayer;
    private int moveTotal;
    private int doublesRolled;

    /**
     * The constructor
     *
     * @param amountOfPlayer
     * @throws IOException
     * @throws InvalidFormatException
     */
    public GameController(int amountOfPlayer) throws IOException, InvalidFormatException {
        for (int i = 0; i < amountOfPlayer; i++) {
            amountOfPlayers.add(new Player("Player" + i, Token.CAT));
            playerLocations.add(0);
        }
        board = new Board();
        bank = new Bank(board.getBoardLocations());

        activePlayer = amountOfPlayers.get(0);
        moveTotal = 0;
        doublesRolled = 0;
        // GUI = new PropertyTycoon();

    }

    /**
     * Roll is used to replace the dice class that generates a random number
     * between 1 and 6 for both dice
     *
     * @return an array that holds the 2 dice values
     */
    private int[] roll() {
        Random rn = new Random();
        int[] roll = new int[2];
        roll[0] = rn.nextInt(6) + 1;
        roll[1] = rn.nextInt(6) + 1;

        return roll;
    }

    /**
     * Move is the method called by the button roll on the GUI it causes the
     * dice to be rolled and the player to be moved to the location landed on
     * the dice. Move also contains the logic if the dice rolls a double to roll
     * again and to move to jail if the dice has rolled 3 doubles
     */
    public void move() {
        int[] roll = roll();
        if (roll[0] == roll[1] && doublesRolled < 3) {
            //GUI.updateLog("The player has rolled a double")
            moveTotal += roll[0] + roll[1];
            doublesRolled++;
            ArrayList<String> actions = new ArrayList<>();
            actions.add("ROLL");
            //GUI.update(actions)
        } else if (doublesRolled > 2) {
            //Move player to jail
            activePlayer.setPlayerLocation(11);
        } else {
            moveTotal += roll[0] + roll[1];
            //Set location values
            activePlayer.setPlayerLocation(moveTotal);
            int i = amountOfPlayers.indexOf(activePlayer);
            playerLocations.set(i, moveTotal);

            //GUI.update(doActions())
        }

    }

    /**
     * Gets the actions that a player can do on a particular position on a board
     * and returns an arraylist of the commands
     *
     * @return Arraylist<String> containing the actions that can be done
     */
    public ArrayList<String> getPlayerActions() {
        ArrayList<String> playerActions = new ArrayList<>();
        BoardPiece boardPiece = board.getBoardLocations().get(activePlayer.getPlayerLocation());
        if (boardPiece instanceof ColouredProperty) {
            ColouredProperty buyable = (ColouredProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (buyable.getCost() <= activePlayer.getPlayerBalance()) {
                    playerActions.add("BUY");
                }
            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof StationProperty) {
            StationProperty buyable = (StationProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (buyable.getCost() <= activePlayer.getPlayerBalance()) {
                    playerActions.add("BUY");
                }
            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof UtilityProperty) {
            UtilityProperty buyable = (UtilityProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (buyable.getCost() <= activePlayer.getPlayerBalance()) {
                    playerActions.add("BUY");
                }
            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof GoToJailPiece) {
            playerActions.add("GOTOJAIL");
        } else if (boardPiece instanceof GoPiece) {
            playerActions.add("ONGO");
        } else if (boardPiece instanceof OpportunityKnocksPiece) {
            playerActions.add("PICKCARD");
        } else if (boardPiece instanceof PotLuckPiece) {
            playerActions.add("PICKCARD");
        } else if (boardPiece instanceof JailPiece) {
            if (activePlayer.isInJail()) {
                playerActions.add("INJAIL");
            }
        } else if (boardPiece instanceof FreeParkingPiece) {
            playerActions.add("FREEPARKING");
        } else if (boardPiece instanceof TaxPiece) {
            playerActions.add("TAX");
        }

        //Add other actions
        if (activePlayer.getOwnedProperties().size() > 0) {
            playerActions.add("SELL");
        }
        playerActions.add("END");

        return playerActions;
    }

    /**
     * Performs the actions avaliable that do not require user input such as
     * paying rent etc then returns the methods that require user interaction
     * This is then used by the GUI to update and display the correct buttons
     *
     * @param playerActions
     * @return Arraylist of player required commands
     */
    public ArrayList<String> performActions(ArrayList<String> playerActions) { //local variable as output of getPlayerActions
	ArrayList<String> remaining = new ArrayList<>();
        for (String s  : playerActions) {
            switch (s) {
                case "RENT":
                    payRent();
                    break;
                case "BUY":
                    remaining.add("BUY");
                    break;
                case "PICKCARD":
                    pickUpCard();
                    break;
                case "GOTOJAIL":
                    goToJail();
                    break;
                case "FREEPARKING":
                    acquireFreeParkingMoney();
                    break;
                case "PASSGO":
                    passingGo();
                    break;
                case "SELL":
                    remaining.add("SELL");
                    break;
                case "END":
                    remaining.add("END");
                    break;
                case "TAX":
                    remaining.add("TAX");
                    break;
                case "INJAIL":
                    break;
            }
        }
        return remaining;
    }

    /*
    Getters and setters
     */
    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }
    
    public void buyProperty(BoardPiece bp) {
        
    }
    
    public void sellProperty(Property prop) {
        
    }
    
    public void endTurn() {
        
    }
    
    private void payRent() {
        
    }
    
    private void pickUpCard() {
        
    }
    
    private void goToJail() {
        
    }
    
    private void acquireFreeParkingMoney() {
        
    }
    
    private void passingGo() {
        
    }

}
