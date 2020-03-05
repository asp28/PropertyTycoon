package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.*;
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.cards.PotLuck;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import javafx.util.Pair;

public class GameController {

    private ArrayList<Player> amountOfPlayers = new ArrayList<>();
    private ArrayList<Integer> playerLocations = new ArrayList<>();
    private ArrayList<OpportunityKnocks> oppocards = new ArrayList<>();
    private ArrayList<PotLuck> potluckcards = new ArrayList<>();
    private Board board;
    private Bank bank;
    //private PropertyTycoon GUI;

    private Player activePlayer;
    private Pair<Integer, Integer> rolls;
    private ArrayList<String> actions;
    private int moveTotal;
    private int doublesRolled;
    private ArrayList<String> tokens = new ArrayList<String>(){{
        add("boot");
        add("smartphone");
        add("goblet");
        add("hatstand");
        add("cat");
        add("spoon");
        
    }};

    /**
     * The constructor
     *
     * @param amountOfPlayer
     * @throws IOException
     * @throws InvalidFormatException
     */
    public GameController(int amountOfPlayer) throws IOException, InvalidFormatException {
        for (int i = 0; i < amountOfPlayer; i++) {
            Player player = new Player();
            player.setName("Player" + i);
            amountOfPlayers.add(player);
            playerLocations.add(0);
        }
        board = new Board();
        bank = new Bank(board.getBoardLocations());
        potluckcards = new Parser().createPotLuckCards();
        oppocards = new Parser().createOppoCards();

        activePlayer = amountOfPlayers.get(0); //the person who rolls the highest goes first and etc.
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
    private Pair<Integer, Integer> roll() {
        Random rn = new Random();
        int roll1 = rn.nextInt(6) + 1;
        int roll2 = rn.nextInt(6) + 1;
        rolls = new Pair<>(roll1,roll2);
        return rolls;
    }

    /**
     * Move is the method called by the button roll on the GUI it causes the
     * dice to be rolled and the player to be moved to the location landed on
     * the dice. Move also contains the logic if the dice rolls a double to roll
     * again and to move to jail if the dice has rolled 3 doubles
     */
    public void move() {
        Pair<Integer, Integer> roll = roll();
        int newLocation = 0;
        if (rolls.getKey() == rolls.getValue() && doublesRolled < 3) {
            //GUI.updateLog("The player has rolled a double")
            moveTotal += rolls.getKey() + rolls.getValue();
            doublesRolled++;
            ArrayList<String> actions = new ArrayList<>();
            actions.add("ROLL");
            //GUI.update(actions)
        } else if (doublesRolled > 2) {
            //Move player to jail
            activePlayer.setLocation(11);
        } else {
            moveTotal += rolls.getKey() + rolls.getValue();
            //Set location values
            if (activePlayer.getLocation() + moveTotal > 40) {
                activePlayer.incrementGameloops();
                moveTotal = (activePlayer.getLocation() + moveTotal) - 40;
                newLocation = moveTotal;

            } else {
                newLocation = activePlayer.getLocation() + moveTotal;
            }
            activePlayer.setLocation(newLocation);
            int i = amountOfPlayers.indexOf(activePlayer);
            playerLocations.set(i, newLocation);

            //GUI.update(doActions())
        }

    }

    /**
     * Gets the actions that a player can do on a particular position on a board
     * and returns an ArrayList of the commands
     *
     * @return ArrayList<String> containing the actions that can be done
     */
    public ArrayList<String> getPlayerActions() {
        ArrayList<String> playerActions = new ArrayList<>();
        BoardPiece boardPiece = board.getBoardLocations().get(activePlayer.getLocation());
        if (boardPiece instanceof ColouredProperty) {
            ColouredProperty buyable = (ColouredProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (buyable.getCost() <= activePlayer.getBalance()) {
                    playerActions.add("BUY");
                }
            } else if (buyable.getOwnedBuy().equals(activePlayer.getName())) {
                if (checkAllColoursOwned(buyable) && checkHouseCount(buyable)) {
                    playerActions.add("BUYHOUSE");
                }

            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof StationProperty) {
            StationProperty buyable = (StationProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (buyable.getCost() <= activePlayer.getBalance()) {
                    playerActions.add("BUY");
                }
            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof UtilityProperty) {
            UtilityProperty buyable = (UtilityProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (buyable.getCost() <= activePlayer.getBalance()) {
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
        int amountOfHouses = 0;
        for (Property p : activePlayer.getOwnedProperties()) {
            if (p instanceof ColouredProperty) {
                ColouredProperty cp = (ColouredProperty) p;
                amountOfHouses += cp.getHouseCount();
            }
        }
        if (amountOfHouses > 0) {
            playerActions.add("SELLHOUSE");
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
        actions = remaining;
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

    public void buyProperty(BoardPiece bp) throws NotAProperty {
        if (bp instanceof Property) {
            Property prop = (Property) bp;

            if (prop.getOwnedBuy().equalsIgnoreCase("The Bank")) {
                activePlayer.decreaseBalance(prop.getCost());
                bank.deposit(prop.getCost());
                bank.removeProperties(prop.getTitle());
                activePlayer.addProperty(prop);
                prop.setOwnedBuy(activePlayer.getName());


            }

        } else {
            throw new NotAProperty("");
        }

    }
   
    /**
     * 
     * Buy property for auctioning
     * 
     * @param bp
     * @param bidder
     * 
     */
    public void buyProperty(BoardPiece bp, Pair<Player, Integer> bidder) throws NotAProperty {
        Player player = bidder.getKey();
        int bid = bidder.getValue();
        if (bp instanceof Property) {
            Property prop = (Property) bp;

            if (prop.getOwnedBuy().equalsIgnoreCase("The Bank")) {
                player.decreaseBalance(bid);
                bank.deposit(bid);
                bank.removeProperties(prop.getTitle());
                player.addProperty(prop);
                prop.setOwnedBuy(player.getName());


            }

        }

    }
    
    public void sellProperty(Property prop) {
        if (prop.getOwnedBuy().equals(activePlayer.getName())) {
            activePlayer.increaseBalance(prop.getCost());
            bank.withdraw(prop.getCost());
            activePlayer.removeProperty(prop);
            bank.addProperties(prop);
        }

    }

    public void endTurn() {
        doublesRolled = 0;
        moveTotal = 0;
        activePlayer.incrementPlayerTurns();
        if (amountOfPlayers.indexOf(activePlayer) == amountOfPlayers.size() - 1) {
            activePlayer = amountOfPlayers.get(0);
        } else {
            int player = amountOfPlayers.indexOf(activePlayer) + 1;
            activePlayer = amountOfPlayers.get(player);
        }
    }

    private void payRent() {
        Property p = (Property) board.getBoardPiece(activePlayer.getLocation());
        Player owner = null;
        for (Player player : amountOfPlayers) {
            if (player.getName().equalsIgnoreCase(p.getOwnedBuy())) { //make lower case equals
                owner = player;
                break;
            }
        }
        activePlayer.increaseBalance(-Integer.parseInt(p.getRent()));
        owner.increaseBalance(-Integer.parseInt(p.getRent()));
    }

    private void pickUpCard() {
        if (board.getBoardPiece(activePlayer.getLocation()) instanceof OpportunityKnocksPiece) {
            OpportunityKnocks card = oppocards.get(0);
            doCardAction(card);
            Collections.rotate(oppocards, -1);
        } else if (board.getBoardPiece(activePlayer.getLocation()) instanceof PotLuckPiece) {
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
    private void doCardAction(OpportunityKnocks opportunityKnocks) {
        String action = opportunityKnocks.getAction();
        switch (action) {
            case "Bank pays player £50":
                bank.withdraw(50);
                activePlayer.increaseBalance(50);
                break;
            case "Bank pays player £100":
                bank.withdraw(100);
                activePlayer.increaseBalance(100);
                break;
            case "Player token moves forwards to Turing Heights":
                activePlayer.setLocation(39);
                break;
            case "Player moves token":
                if (opportunityKnocks.getDescription().contains("han xin")) {
                    if (activePlayer.getLocation() > 24) {
                        activePlayer.setLocation(24);
                        activePlayer.increaseBalance(200);
                        bank.withdraw(200);
                    } else {
                        activePlayer.setLocation(24);
                    }
                    break;
                }
                if (opportunityKnocks.getDescription().contains("hove station")) {
                    if (activePlayer.getLocation() > 15) {
                        activePlayer.setLocation(15);
                        activePlayer.increaseBalance(200);
                        bank.withdraw(200);
                    } else {
                        activePlayer.setLocation(15);
                    }
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("advance to go")) {
                    activePlayer.setLocation(0);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("go back 3 spaces")) {
                    //fix error of if location is 2 or less, it should minus the rest from 40
                    activePlayer.setLocation(activePlayer.getLocation() - 3);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("Advance to Skywalker Drive. If you pass GO collect £200")) {
                    if (activePlayer.getLocation() > 11) {
                        activePlayer.setLocation(11);
                        activePlayer.increaseBalance(200);
                        bank.withdraw(200);
                    } else {
                        activePlayer.setLocation(11);
                    }
                    break;
                }
            case "Player puts £15 on free parking":
                FreeParkingPiece fpp = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp.setBalance(fpp.getBalance() + 15);
                break;
            case "Player pays £150 to the bank":
                activePlayer.increaseBalance(-150);
                bank.deposit(150);
                break;
            case "Bank pays £150 to the player":
                bank.withdraw(150);
                activePlayer.increaseBalance(150);
                break;
            case "Player pays money to the bank":
                //£40 per house and £115 per hotel
                break;
            //£25 per house and £100 per hotel
            case "As the card says":
                goToJail();
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
    private void doCardAction(PotLuck potluck) {
        String action = potluck.getAction();
        switch (action) {
            case "Bank pays player £20":
                bank.withdraw(20);
                activePlayer.increaseBalance(20);
                break;
            case "Bank pays player £50":
                bank.withdraw(50);
                activePlayer.increaseBalance(50);
                break;
            case "Bank pays player £100":
                bank.withdraw(100);
                activePlayer.increaseBalance(100);
                break;
            case "Bank pays player £200":
                bank.withdraw(200);
                activePlayer.increaseBalance(200);
                break;
            case "Player token moves backwards to Crapper Street":
                activePlayer.setLocation(1);
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
                bank.withdraw(100);
                activePlayer.increaseBalance(100);
                break;
            case "Bank pays player £25":
                bank.withdraw(25);
                activePlayer.increaseBalance(25);
                break;
            case "Player receives £10 from each player":
                for (Player p : amountOfPlayers) {
                    p.increaseBalance(-10);
                    activePlayer.increaseBalance(10);
                }
                break;
            case "As the card says":
                goToJail();
                break;
            case "Player moves forwards to GO":
                activePlayer.setLocation(0);
                break;
            case "Retained by the player until needed. No resale or trade value":
                //give card to player, move to bottom of pile once used.
                break;
            case "Player pays £50 to the bank":
                activePlayer.increaseBalance(-50);
                bank.deposit(50);
                break;
            case "Player pays £100 to the bank":
                activePlayer.increaseBalance(-100);
                bank.deposit(100);
                break;
        }
    }

    private void goToJail() {
        activePlayer.setLocation(10);
        activePlayer.setInJail(true);
    }

    private void acquireFreeParkingMoney() {
        FreeParkingPiece fp = (FreeParkingPiece) board.getBoardPiece(activePlayer.getLocation());
        activePlayer.increaseBalance(fp.getBalance());
        fp.setBalance(0);
    }

    private void passingGo() {
        bank.withdraw(200);
        activePlayer.increaseBalance(200);
    }

    private boolean checkAllColoursOwned(ColouredProperty prop) {
        String colourGroup = prop.getGroup();
        int countOfColours = 0;
        for (Property ownedProperties : activePlayer.getOwnedProperties()) {
            if (ownedProperties instanceof ColouredProperty) {
                if (ownedProperties.getGroup().equals(colourGroup)) {
                    countOfColours++;
                }

            }
        }
        if (colourGroup.equals("Brown") || colourGroup.equals("Deep blue")) {
            if (countOfColours == 2) {
                return true;
            }
        } else {
            if (countOfColours == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHouseCount(Property prop) {
        ColouredProperty property = (ColouredProperty) prop;
        if (property.getHouseCount() >= 5) {
            return false;
        }
        ArrayList<ColouredProperty> cp = new ArrayList<>();

        for (Property ownedProperties : activePlayer.getOwnedProperties()) {
            if (prop.getGroup().equals(ownedProperties.getGroup()) && !ownedProperties.getTitle().equals(property.getTitle())) {
                cp.add((ColouredProperty) ownedProperties);
            }
        }
        for (ColouredProperty check : cp) {
            if (Math.abs(property.getHouseCount() - check.getHouseCount()) >= 1) {
                return false;
            }
        }
        return true;
    }

    public void buyHouse() {
        ColouredProperty prop = (ColouredProperty) board.getBoardPiece(activePlayer.getLocation());
        if (prop.getHouseCount() < activePlayer.getBalance()) {
            activePlayer.decreaseBalance(prop.getHouseCost());
            prop.setHouseCount(prop.getHouseCount() + 1);
            int rentValue = prop.getHouses().get(prop.getHouseCount());
            prop.setRent(Integer.toString(rentValue));


        }
    }
    
    public Pair<Player,Integer> getHighestBid(HashMap<Player,Integer> bids){
        int maxBid = 0;
        Player maxBidPlayer = null;
        for (Entry<Player,Integer> pair : bids.entrySet()){
            if (pair.getValue() > maxBid){
                maxBid = pair.getValue();
                maxBidPlayer = pair.getKey();
            }
        }
        Pair<Player,Integer> max = new Pair<>(maxBidPlayer,maxBid);
        return max;
    }
    
    public void mortgageProperty(Property prop){
        prop.setMortgaged(true);
        bank.withdraw(prop.getCost()/2);
        activePlayer.increaseBalance(prop.getCost()/2);  
    }
    
    public void updateGUI() {
        
    }

    public Pair<Integer, Integer> getRolls() {
        return rolls;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

}
