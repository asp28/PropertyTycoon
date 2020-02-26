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
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.cards.PotLuck;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {

    private ArrayList<Player> amountOfPlayers = new ArrayList<>();
    private ArrayList<Integer> playerLocations = new ArrayList<>();
    private ArrayList<OpportunityKnocks> oppocards = new ArrayList<>();
    private ArrayList<PotLuck> potluckcards = new ArrayList<>();
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
        potluckcards = new Parser().createPotLuckCards();
        oppocards = new Parser().createOppoCards();

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
        for (String s : playerActions) {
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
        if (bp instanceof Property) {
            Property prop = (Property) bp;
            if (prop.getOwnedBuy().equalsIgnoreCase("bank")) {
                activePlayer.increaseBalance(-prop.getCost());
                bank.deposit(prop.getCost());
                bank.removeProperties(prop.getTitle());
                activePlayer.addProperty(prop);
            }

        }

    }

    public void sellProperty(Property prop) {
        if (prop.getOwnedBuy().equals(activePlayer.getCharacter())) {
            activePlayer.increaseBalance(prop.getCost());
            bank.withdraw(prop.getCost());
            activePlayer.removeProperty(prop);
            bank.addProperties(prop);
        }

    }

    public void endTurn() {
        doublesRolled = 0;
        moveTotal = 0;
        if (amountOfPlayers.indexOf(activePlayer) == amountOfPlayers.size() - 1) {
            activePlayer = amountOfPlayers.get(0);
        } else {
            int player = amountOfPlayers.indexOf(activePlayer) + 1;
            activePlayer = amountOfPlayers.get(player);
        }
    }

    private void payRent() {
        Property p = (Property) board.getProperty(activePlayer.getPlayerLocation());
        Player owner = null;
        for (Player player : amountOfPlayers) {
            if (player.getCharacter().equalsIgnoreCase(p.getOwnedBuy())) { //make lower case equals
                owner = player;
                break;
            }
        }
        activePlayer.increaseBalance(-Integer.parseInt(p.getRent()));
        owner.increaseBalance(-Integer.parseInt(p.getRent()));
    }

    private void pickUpCard() {
        if (board.getProperty(activePlayer.getPlayerLocation()) instanceof OpportunityKnocksPiece) {
            OpportunityKnocks card = oppocards.get(0);
            doCardAction(card);
            Collections.rotate(oppocards, -1);
        } else if (board.getProperty(activePlayer.getPlayerLocation()) instanceof PotLuckPiece) {
            PotLuck card = potluckcards.get(0);
            doCardAction(card);
            Collections.rotate(potluckcards, -1);
        } else {
            //throw exception
        }
    }

    /**
     * Does the action of the OpportunityKnocks card.
     *
     * @param opportunityKnocks
     */
    public void doCardAction(OpportunityKnocks opportunityKnocks) {
        String action = opportunityKnocks.getAction();
        switch (action) {
            case "Bank pays player £50":
                getActivePlayer().getBank().withdraw(50);
                getActivePlayer().increaseBalance(50);
                break;
            case "Bank pays player £100":
                getActivePlayer().getBank().withdraw(100);
                getActivePlayer().increaseBalance(100);
                break;
            case "Player token moves forwards to Turing Heights":
                getActivePlayer().setPlayerLocation(39);
                break;
            case "Player moves token":
                if (opportunityKnocks.getDescription().contains("han xin")) {
                    if (getActivePlayer().getPlayerLocation() > 24) {
                        getActivePlayer().setPlayerLocation(24);
                        getActivePlayer().increaseBalance(200);
                        getActivePlayer().getBank().withdraw(200);
                    } else {
                        getActivePlayer().setPlayerLocation(24);
                    }
                    break;
                }
                if (opportunityKnocks.getDescription().contains("hove station")) {
                    if (getActivePlayer().getPlayerLocation() > 15) {
                        getActivePlayer().setPlayerLocation(15);
                        getActivePlayer().increaseBalance(200);
                        getActivePlayer().getBank().withdraw(200);
                    } else {
                        getActivePlayer().setPlayerLocation(15);
                    }
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("advance to go")) {
                    getActivePlayer().setPlayerLocation(0);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("go back 3 spaces")) {
                    //fix error of if location is 2 or less, it should minus the rest from 40
                    getActivePlayer().setPlayerLocation(getActivePlayer().getPlayerLocation() - 3);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("Advance to Skywalker Drive. If you pass GO collect £200")) {
                    if (getActivePlayer().getPlayerLocation() > 11) {
                        getActivePlayer().setPlayerLocation(11);
                        getActivePlayer().increaseBalance(200);
                        getActivePlayer().getBank().withdraw(200);
                    } else {
                        getActivePlayer().setPlayerLocation(11);
                    }
                    break;
                }
            case "Player puts £15 on free parking":
                FreeParkingPiece fpp = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp.setBalance(fpp.getBalance() + 15);
                break;
            case "Player pays £150 to the bank":
                getActivePlayer().increaseBalance(-150);
                getActivePlayer().getBank().deposit(150);
                break;
            case "Bank pays £150 to the player":
                getActivePlayer().getBank().withdraw(150);
                getActivePlayer().increaseBalance(150);
                break;
            case "Player pays money to the bank":
                //£40 per house and £115 per hotel
                break;
            //£25 per house and £100 per hotel
            case "As the card says":
                getActivePlayer().setPlayerLocation(10);
                //implement sending someone to jail
                break;
            case "Player puts £20 on free parking":
                FreeParkingPiece fpp2 = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp2.setBalance(fpp2.getBalance() + 20);
                break;
            case "Retained by the player until needed. No resale or trade value":
                //give card to player, move to bottom of pile once used.
                break;
        }
    }

    /**
     * Does action for the potluck card chosen.
     *
     * @param potluck
     */
    public void doCardAction(PotLuck potluck) {
        String action = potluck.getAction();
        switch (action) {
            case "Bank pays player £20":
                getActivePlayer().getBank().withdraw(20);
                getActivePlayer().increaseBalance(20);
                break;
            case "Bank pays player £50":
                getActivePlayer().getBank().withdraw(50);
                getActivePlayer().increaseBalance(50);
                break;
            case "Bank pays player £100":
                getActivePlayer().getBank().withdraw(100);
                getActivePlayer().increaseBalance(100);
                break;
            case "Bank pays player £200":
                getActivePlayer().getBank().withdraw(200);
                getActivePlayer().increaseBalance(200);
                break;
            case "Player token moves backwards to Crapper Street":
                getActivePlayer().setPlayerLocation(1);
                break;
            case "If fine paid, player puts £10 on free parking":
                FreeParkingPiece fpp = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp.setBalance(fpp.getBalance() + 10);
                break;
            case "Player puts £50 on free parking":
                FreeParkingPiece fpp2 = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp2.setBalance(fpp2.getBalance() + 50);
                break;
            case "Bank pays £100 to the player":
                getActivePlayer().getBank().withdraw(100);
                getActivePlayer().increaseBalance(100);
                break;
            case "Bank pays player £25":
                getActivePlayer().getBank().withdraw(25);
                getActivePlayer().increaseBalance(25);
                break;
            case "Player receives £10 from each player":
                for (Player p : amountOfPlayers) {
                    p.increaseBalance(-10);
                    getActivePlayer().increaseBalance(10);
                }
                break;
            case "As the card says":
                getActivePlayer().setPlayerLocation(10);
                //implement sending someone to jail
                break;
            case "Player moves forwards to GO":
                getActivePlayer().setPlayerLocation(0);
                break;
            case "Retained by the player until needed. No resale or trade value":
                //give card to player, move to bottom of pile once used.
                break;
            case "Player pays £50 to the bank":
                getActivePlayer().increaseBalance(-50);
                getActivePlayer().getBank().deposit(50);
                break;
            case "Player pays £100 to the bank":
                getActivePlayer().increaseBalance(-100);
                getActivePlayer().getBank().deposit(100);
                break;
        }
    }

    private void goToJail() {
        activePlayer.setPlayerLocation(10);
        activePlayer.setInJail(true);
    }

    private void acquireFreeParkingMoney() {
        FreeParkingPiece fp = (FreeParkingPiece) board.getProperty(activePlayer.getPlayerLocation());
        activePlayer.increaseBalance(fp.getBalance());
        fp.setBalance(0);
    }

    private void passingGo() {
        bank.withdraw(200);
        activePlayer.increaseBalance(200);
    }

}
