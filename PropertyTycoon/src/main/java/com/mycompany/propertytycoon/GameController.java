package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.*;
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.cards.PotLuck;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import com.mycompany.propertytycoon.gui.game.GameVariableStorage;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import com.mycompany.propertytycoon.log.Log;
import javafx.util.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController {

    private static Log log = Log.getInstance();
    private static StageManager SM = StageManager.getInstance();
    private static GameVariableStorage GVS = GameVariableStorage.getInstance();

    private ArrayList<Player> amountOfPlayers = new ArrayList<>();
    private ArrayList<Integer> playerLocations = new ArrayList<>();
    private ArrayList<OpportunityKnocks> oppocards = new ArrayList<>();
    private ArrayList<PotLuck> potluckcards = new ArrayList<>();
    private Board board;
    private Bank bank;

    private Player activePlayer;
    private Pair<Integer, Integer> rolls;
    private ArrayList<String> actions;
    private int moveTotal;
    private int doublesRolled;
    private ArrayList<String> tokens = new ArrayList<String>() {
        {
            add("boot");
            add("smartphone");
            add("goblet");
            add("hatstand");
            add("cat");
            add("spoon");

        }
    };

    /**
     * Constructor to create players, board, bank, etc.
     *
     * @param amountOfPlayer
     * @throws IOException - Cant load
     * @throws InvalidFormatException - Excel parser error
     */
    public GameController(int amountOfPlayer, int amountOfBots) throws IOException, InvalidFormatException {
        for (int i = 0; i < amountOfPlayer; i++) {
            Player player = new Player();
            player.setName("Player" + i);
            amountOfPlayers.add(player);
            playerLocations.add(0);
        }
        for (int x = 0; x < amountOfBots; x++) {
            AiPlayer player = new AiPlayer();
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
     * @return a pair that holds the 2 dice values
     */
    private void roll() {
        Random rn = new Random();
        int roll1 = rn.nextInt(6) + 1;
        int roll2 = rn.nextInt(6) + 1;
        rolls = new Pair<>(roll1, roll2);
    }

    /**
     * Move is the method called by the button roll on the GUI it causes the
     * dice to be rolled and the player to be moved to the location landed on
     * the dice. Move also contains the logic if the dice rolls a double to roll
     * again and to move to jail if the dice has rolled 3 doubles
     */
    public void move() {
        actions = new ArrayList<>();
        roll();

        if (activePlayer.isInJail()) {
            if (rolls.getKey() == rolls.getValue()) {
                activePlayer.setInJail(false);
            }
        } else {
            if (rolls.getKey() == rolls.getValue() && doublesRolled < 3) {
                actions.add("ROLL");
                log.addToLog(activePlayer.getName() + " has rolled a double " + rolls.getKey() + ".");
                int currentLocation = activePlayer.getLocation();
                int newLocation = currentLocation + (rolls.getKey() + rolls.getValue());
                activePlayer.setLocation(newLocation);
                doublesRolled++;
            } else if (doublesRolled > 2) {
                log.addToLog(activePlayer.getName() + " was sent to jail for rolling 3 doubles in a row.");
                goToJail();
                doublesRolled = 0;
            } else {
                log.addToLog(activePlayer.getName() + " has rolled " + rolls.getKey() + " and " + rolls.getValue() + ".");
                int currentLocation = activePlayer.getLocation();
                int newLocation = currentLocation + (rolls.getKey() + rolls.getValue());
                activePlayer.setLocation(newLocation);
                doublesRolled = 0;
            }

            if (activePlayer.getLocation() >= 40) {
                passingGo();
                activePlayer.setGameloops(activePlayer.getGameloops() + 1);
                int newLocation = activePlayer.getLocation() - 40;
                activePlayer.setLocation(newLocation);

            }
            playerLocations.set(amountOfPlayers.indexOf(activePlayer), activePlayer.getLocation());
            actions.addAll(getPlayerActions());
            actions = performActions(actions);
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
                if (activePlayer.getGameloops() > 0) {
                    playerActions.add("BUY");
                }
            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof StationProperty) {
            StationProperty buyable = (StationProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (activePlayer.getGameloops() > 0) {
                    playerActions.add("BUY");
                }
            } else {
                playerActions.add("RENT");
            }
        } else if (boardPiece instanceof UtilityProperty) {
            UtilityProperty buyable = (UtilityProperty) boardPiece;
            if (buyable.getOwnedBuy().equals("The Bank")) {
                if (activePlayer.getGameloops() > 0) {
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
     * @param playerActions list of actions the player can take at the start of the turn
     * @return Arraylist of player required commands
     */
    public ArrayList<String> performActions(ArrayList<String> playerActions) { //local variable as output of getPlayerActions
        ArrayList<String> remaining = new ArrayList<>();
        for (String s : playerActions) {
            switch (s) {
                case "RENT":

                    if (activePlayer.isIsAI()) {
                        payRent();
                    } else {
                        remaining.add("RENT");
                    }
                    break;
                case "BUY":
                    if (activePlayer.isIsAI()) {
                        AiPlayer aiPlayer = (AiPlayer) activePlayer;
                        if (aiPlayer.DoesAiBuy()) {
                            try {
                                buyProperty(board.getBoardPiece(activePlayer.getLocation()));
                            } catch (NotAProperty ex) {
                                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            SM.setAuctionProperty(board.getBoardPiece(activePlayer.getLocation()));
                            SM.changeScene(View.AUCTION);
                        }
                    } else {
                        remaining.add("BUY");
                    }
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
                    if (activePlayer.isIsAI()) {
                        GVS.endTurnForBot();
                    } else {
                        remaining.add("END");
                    }
                    break;
                case "TAX":
                    payTax((TaxPiece) getBoard().getBoardPiece(activePlayer.getLocation()));
                    break;
                case "INJAIL":
                    break;
                case "ROLL":
                    remaining.add("ROLL");
                    break;
            }
        }
        actions.clear();
        return remaining;
    }

    /**
     * Buy Property (Non auction)
     *
     * @param bp BoardPiece a player buys
     * @throws NotAProperty property invalid
     */
    public void buyProperty(BoardPiece bp) throws NotAProperty {
        if (bp instanceof Property) {
            Property prop = (Property) bp;

            if (prop.getOwnedBuy().equalsIgnoreCase("The Bank")) {
                activePlayer.decreaseBalance(prop.getCost());
                bank.deposit(prop.getCost());
                bank.removeProperties(prop.getTitle());
                activePlayer.addProperty(prop);
                prop.setOwnedBuy(activePlayer.getName());
                log.addToLog(activePlayer.getName() + " has bought " + bp.getTitle() + ".");

            }

        } else {
            throw new NotAProperty("");
        }

    }

    /**
     *
     * Buy property for auctioning
     *
     * @param bp BoardPiece to be bought
     * @param bidder Player and their bid to buy property
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
                log.addToLog(bidder.getKey().getName() + " has bought" + bp.getTitle() + " for £" + bidder.getValue() + ".");

            }

        }

    }

    /**
     * Sell property back to bank for full amount/half amount if mortgaged
     *
     * @param prop Property to be sold
     */
    public void sellProperty(Property prop) {
        if (prop.getOwnedBuy().equals(activePlayer.getName()) && prop.isMortgaged() == false) {
            prop.setOwnedBuy("The Bank");
            activePlayer.increaseBalance(prop.getCost());
            bank.withdraw(prop.getCost());
            activePlayer.removeProperty(prop);
            bank.addProperties(prop);
            log.addToLog(activePlayer.getName() + " has sold " + prop.getTitle() + ".");
        } else {
            prop.setOwnedBuy("The Bank");
            activePlayer.increaseBalance(prop.getCost() / 2);
            bank.withdraw(prop.getCost() / 2);
            activePlayer.removeProperty(prop);
            bank.addProperties(prop);
            log.addToLog(activePlayer.getName() + " has sold " + prop.getTitle() + ". Only recieved half due to prop being on mortage");
        }

    }

    /**
     * Trade method between player A and player B
     *
     * @param tradingP activePlayer's proposed properties to give
     * @param cash activePlayer's proposed cash to give
     * @param desiredCash exchangee's proposed cash to give
     * @param desiredP exchangee's proposed properties to give
     * @param choosenPlayer exchangee of trade
     */
    public void trade(ArrayList<Property> tradingP, int cash, int desiredCash, ArrayList<Property> desiredP, Player choosenPlayer) {

        if (tradingP.size() > 0) {
            for (Property t : tradingP) {
                activePlayer.removeProperty(t);
                choosenPlayer.addProperty(t);
            }
        }

        if (cash > 0) {
            activePlayer.decreaseBalance(cash);
            choosenPlayer.increaseBalance(cash);
        }

        if (desiredCash > 0) {
            choosenPlayer.decreaseBalance(desiredCash);
            activePlayer.increaseBalance(desiredCash);
        }

        if (desiredP.size() > 0) {
            for (Property d : desiredP) {
                choosenPlayer.removeProperty(d);
                activePlayer.addProperty(d);
            }
        }
        log.addToLog(activePlayer.getName() + " has traded with " + choosenPlayer.getName() + ".");

    }

    /**
     * Ends the turn of the active player
     */
    public void endTurn() {
        doublesRolled = 0;
        moveTotal = 0;
        activePlayer.incrementPlayerTurns();
        log.addToLog(activePlayer.getName() + " has ended their turn.");
        if (amountOfPlayers.indexOf(activePlayer) == amountOfPlayers.size() - 1) {
            activePlayer = amountOfPlayers.get(0);
        } else {
            int player = amountOfPlayers.indexOf(activePlayer) + 1;
            activePlayer = amountOfPlayers.get(player);
        }
        if (activePlayer.isIsAI()) {
            move();
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                @Override
                public void run() {
                    endTurn();
                }
            },
                    2000
            );

        }
    }

    /**
     * Pay rent
     */
    public void payRent() {
        Property p = (Property) board.getBoardPiece(activePlayer.getLocation());
        Player owner = null;
        for (Player player : amountOfPlayers) {
            if (player.getName().equalsIgnoreCase(p.getOwnedBuy())) { //make lower case equals
                owner = player;
                break;
            }
        }

        if (owner.isInJail()) {
            log.addToLog("No rent is paid as owner of the property is in jail");
            return;
        }

        if (p.isMortgaged()) {
            log.addToLog("No rent is paid as property is mortaged");
            return;
        }

        if (doubleRent(p, owner)) {

            activePlayer.increaseBalance(-(2 * Integer.parseInt(p.getRent())));
            owner.increaseBalance(2 * Integer.parseInt(p.getRent()));
            log.addToLog(activePlayer.getName() + " has paid rent to " + owner.getName() + " of £" + (2 * Integer.parseInt(p.getRent())));
            checkIfBankrupt();
        } else {
            activePlayer.increaseBalance(-Integer.parseInt(p.getRent()));
            owner.increaseBalance(Integer.parseInt(p.getRent()));
            log.addToLog(activePlayer.getName() + " has paid rent to " + owner.getName() + " of £" + p.getRent());
            checkIfBankrupt();
        }
    }
    
    /**
     * A check to see if the rent must be doubled if the owner of the property owns
     * all of the colour group
     * @param property Property in question
     * @param owner Owner of property
     * @return true if rent should be doubled
     */
    private boolean doubleRent(Property property, Player owner) {
        if (property instanceof ColouredProperty) {
            String colourGroup = property.getGroup();
            int countOfColours = 0;
            for (Property ownedProperties : owner.getOwnedProperties()) {
                if (ownedProperties instanceof ColouredProperty) {
                    if (ownedProperties.getGroup().equals(colourGroup) && ((ColouredProperty) ownedProperties).getHouseCount() < 1) {
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
        }
        return false;
    }

    /**
     * Player picks up a card if landed on an Opportunity Knocks BoardPiece 
     * or a PotLuck BoardPiece
     */
    private void pickUpCard() {
        if (board.getBoardPiece(activePlayer.getLocation()) instanceof OpportunityKnocksPiece) {
            log.addToLog(activePlayer.getName() + " has picked up an opportunity knocks card.");
            OpportunityKnocks card = oppocards.get(0);
            log.addToLog(card.getDescription());
            doCardAction(card);
            if (card.getAction().equals("Retained by the player until needed. No resale or trade value")) {
                oppocards.remove(card);
            } else {
                Collections.rotate(oppocards, -1);
            }

        } else if (board.getBoardPiece(activePlayer.getLocation()) instanceof PotLuckPiece) {
            log.addToLog(activePlayer.getName() + " has picked up a potluck card.");
            PotLuck card = potluckcards.get(0);
            log.addToLog(card.getDescription());
            doCardAction(card);
            if (card.getAction().equals("Retained by the player until needed. No resale or trade value")) {
                potluckcards.remove(card);
            } else {
                Collections.rotate(potluckcards, -1);
            }
        } else {
            //throw exception
        }
    }

    /**
     * Does the action of the OpportunityKnocks card.
     *
     * @param opportunityKnocks the card picked up
     */
    private void doCardAction(OpportunityKnocks opportunityKnocks) {
        String action = opportunityKnocks.getAction();
        FreeParkingPiece fpp = (FreeParkingPiece) board.getBoardPiece(20);
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
                playerLocations.set(amountOfPlayers.indexOf(activePlayer), 39);
                SM.changeScene(View.GAME);
                break;
            case "Player moves token":
                if (opportunityKnocks.getDescription().contains("han xin")) {
                    if (activePlayer.getLocation() > 24) {
                        activePlayer.setLocation(24);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 24);
                        passingGo();
                    } else {
                        activePlayer.setLocation(24);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 24);
                    }
                    SM.changeScene(View.GAME);
                    break;
                }
                if (opportunityKnocks.getDescription().contains("hove station")) {
                    if (activePlayer.getLocation() > 15) {
                        activePlayer.setLocation(15);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 15);
                        passingGo();
                    } else {
                        activePlayer.setLocation(15);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 15);
                    }
                    SM.changeScene(View.GAME);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("advance to go")) {
                    activePlayer.setLocation(0);
                    playerLocations.set(amountOfPlayers.indexOf(activePlayer), 0);
                    passingGo();
                    SM.changeScene(View.GAME);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("go back 3 spaces")) {
                    if (activePlayer.getLocation() - 3 < 0) {
                        int left = 3 - activePlayer.getLocation();
                        activePlayer.setLocation(40 - left);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 40 - left);
                    } else {
                        activePlayer.setLocation(activePlayer.getLocation() - 3);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), activePlayer.getLocation());
                    }
                    SM.changeScene(View.GAME);
                    break;
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("Advance to Skywalker Drive. If you pass GO collect £200")) {
                    if (activePlayer.getLocation() > 11) {
                        activePlayer.setLocation(11);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 11);
                        passingGo();
                    } else {
                        activePlayer.setLocation(11);
                        playerLocations.set(amountOfPlayers.indexOf(activePlayer), 11);
                    }
                    SM.changeScene(View.GAME);
                    break;
                }
            case "Player puts £15 on free parking":
                activePlayer.decreaseBalance(15);
                fpp.setBalance(fpp.getBalance() + 15);
                activePlayer.increaseBalance(-15);
                checkIfBankrupt();
                break;
            case "Player pays £150 to the bank":
                activePlayer.increaseBalance(-150);
                bank.deposit(150);
                checkIfBankrupt();
                break;
            case "Bank pays £150 to the player":
                bank.withdraw(150);
                activePlayer.increaseBalance(150);
                SM.changeScene(View.GAME);
                break;
            case "Player pays money to the bank":
                if (opportunityKnocks.getDescription().equalsIgnoreCase("You are assessed for repairs, £40/house, £115/hotel")) {
                    int house = 0;
                    int hotel = 0;
                    for (Property p : activePlayer.getOwnedProperties()) {
                        if (p instanceof ColouredProperty) {
                            if (((ColouredProperty) p).getHouseCost() == 5) {
                                hotel++;
                            } else {
                                house += ((ColouredProperty) p).getHouseCount();
                            }
                        }
                    }
                    activePlayer.decreaseBalance((house * 40) + (hotel * 115));
                    fpp.setBalance(fpp.getBalance() + ((house * 40) + (hotel * 115)));
                    SM.changeScene(View.GAME);
                }
                if (opportunityKnocks.getDescription().equalsIgnoreCase("You are assessed for repairs, £25/house, £100/hotel")) {
                    int house = 0;
                    int hotel = 0;
                    for (Property p : activePlayer.getOwnedProperties()) {
                        if (p instanceof ColouredProperty) {
                            if (((ColouredProperty) p).getHouseCost() == 5) {
                                hotel++;
                            } else {
                                house += ((ColouredProperty) p).getHouseCount();
                            }
                        }
                    }
                    activePlayer.decreaseBalance((house * 25) + (hotel * 100));
                    fpp.setBalance(fpp.getBalance() + ((house * 25) + (hotel * 100)));
                    SM.changeScene(View.GAME);
                }
                checkIfBankrupt();
                break;
            case "As the card says":
                goToJail();
                SM.changeScene(View.GAME);
                break;
            case "Player puts £20 on free parking":
                FreeParkingPiece fpp2 = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp2.setBalance(fpp2.getBalance() + 20);
                activePlayer.increaseBalance(-20);
                checkIfBankrupt();
                break;
            case "Retained by the player until needed. No resale or trade value":
                activePlayer.setGOJF(opportunityKnocks);
                break;
        }
    }

    /**
     * Does action for the potluck card chosen.
     *
     * @param potluck the card picked up
     */
    private void doCardAction(PotLuck potluck) {
        String action = potluck.getAction();
        switch (action) {
            case "Bank pays player £20":
                bank.withdraw(20);
                activePlayer.increaseBalance(20);
                SM.changeScene(View.GAME);
                break;
            case "Bank pays player £50":
                bank.withdraw(50);
                activePlayer.increaseBalance(50);
                SM.changeScene(View.GAME);
                break;
            case "Bank pays player £100":
                bank.withdraw(100);
                activePlayer.increaseBalance(100);
                SM.changeScene(View.GAME);
                break;
            case "Bank pays player £200":
                bank.withdraw(200);
                activePlayer.increaseBalance(200);
                SM.changeScene(View.GAME);
                break;
            case "Player token moves backwards to Crapper Street":
                activePlayer.setLocation(1);
                playerLocations.set(amountOfPlayers.indexOf(activePlayer), 1);
                SM.changeScene(View.GAME);
                break;
            case "If fine paid, player puts £10 on free parking":
                //FIX THIS CODE NEED TO ASK PLAYER
                FreeParkingPiece fpp = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp.setBalance(fpp.getBalance() + 10);
                activePlayer.increaseBalance(-10);
                checkIfBankrupt();
                break;
            case "Player puts £50 on free parking":
                FreeParkingPiece fpp2 = (FreeParkingPiece) board.getBoardLocations().get(20);
                fpp2.setBalance(fpp2.getBalance() + 50);
                activePlayer.increaseBalance(-50);
                checkIfBankrupt();
                break;
            case "Bank pays £100 to the player":
                bank.withdraw(100);
                activePlayer.increaseBalance(100);
                SM.changeScene(View.GAME);
                break;
            case "Bank pays player £25":
                bank.withdraw(25);
                activePlayer.increaseBalance(25);
                SM.changeScene(View.GAME);
                break;
            case "Player receives £10 from each player":
                amountOfPlayers.stream().map((p) -> {
                    p.increaseBalance(-10);
                    return p;
                }).forEachOrdered((_item) -> {
                    activePlayer.increaseBalance(10);
                });
                SM.changeScene(View.GAME);
                break;
            case "As the card says":
                goToJail();
                SM.changeScene(View.GAME);
                break;
            case "Player moves forwards to GO":
                activePlayer.setLocation(0);
                passingGo();
                SM.changeScene(View.GAME);
                break;
            case "Retained by the player until needed. No resale or trade value":
                activePlayer.setGOJF(potluck);
                SM.changeScene(View.GAME);
                break;
            case "Player pays £50 to the bank":
                activePlayer.increaseBalance(-50);
                bank.deposit(50);
                SM.changeScene(View.GAME);
                break;
            case "Player pays £100 to the bank":
                activePlayer.increaseBalance(-100);
                checkIfBankrupt();
                bank.deposit(100);
                SM.changeScene(View.GAME);
                break;
        }
    }

    /**
     * Sends the active player to jail
     */
    private void goToJail() {
        activePlayer.setLocation(10);
        activePlayer.setInJail(true);
        log.addToLog(activePlayer.getName() + " was sent to jail.");
    }

    /**
     * When landed on FreeParking, active player received FreeParking's balance
     */
    private void acquireFreeParkingMoney() {
        FreeParkingPiece fp = (FreeParkingPiece) board.getBoardPiece(activePlayer.getLocation());
        activePlayer.increaseBalance(fp.getBalance());
        fp.setBalance(0);
        log.addToLog(activePlayer.getName() + " has acquired the free parking balance.");
    }

    /**
     * When the player lands on Go or passes Go
     */
    private void passingGo() {
        bank.withdraw(200);
        activePlayer.increaseBalance(200);
        log.addToLog(activePlayer.getName() + " has passed Go and collected £200.");
    }

    /**
     * A check to determine if the player owns all properties
     * of a given group
     * @param prop Property of a group
     * @return true of player owns all properties of group
     */
    public boolean checkAllColoursOwned(ColouredProperty prop) {
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

    /**
     * A check to ensure all properties have no difference of
     * 1 house between them
     * @param prop Property that wants an additional house
     * @return true if property can have an additional house
     */
    public boolean checkHouseCount(Property prop) {
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
            if ((property.getHouseCount() - check.getHouseCount()) >= 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a list of properties a player can buy a house for
     * @return a list of ColouredProperty
     */
    public ArrayList<ColouredProperty> listOfHousableProps() {
        ArrayList<ColouredProperty> housableProps = new ArrayList<>();
        for (Property prop : activePlayer.getOwnedProperties()) {
            if (prop instanceof ColouredProperty) {
                ColouredProperty propColour = (ColouredProperty) prop;
                boolean checkColour = checkAllColoursOwned(propColour);
                boolean checkHouse = checkHouseCount(propColour);
                if (checkColour && checkHouse) {
                    housableProps.add(propColour);
                }
            }
        }
        return housableProps;
    }

    /**
     * Check to see if a person can add a house to a property
     * @param prop ColouredProperty to add proposed house
     * @return true if property can be improved with house
     */
    public boolean canAddHouse(ColouredProperty prop) {
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
            if (((property.getHouseCount() + 1) - check.getHouseCount()) >= 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check to see if the player can afford to add another house on a property
     * @param prop ColouredProperty in question
     * @return true if player can afford another house on property
     */
    public boolean canAffordHouse(ColouredProperty prop) {
        if (prop.getHouseCost() > activePlayer.getBalance()) {
            return false;
        }
        return true;
    }

    /**
     * Buys a house on property
     * @param prop Property to have an additional house
     */
    public void buyHouse(Property prop) {
        ColouredProperty propColour = (ColouredProperty) prop;
        if (propColour.getHouseCost() <= activePlayer.getBalance() && propColour.getHouseCount() <= 5 && checkHouseCount(prop)) {
            activePlayer.decreaseBalance(propColour.getHouseCost());
            checkIfBankrupt();
            propColour.setHouseCount(propColour.getHouseCount() + 1);
            int rentValue = propColour.getHouses().get(propColour.getHouseCount());
            propColour.setRent(Integer.toString(rentValue));
            log.addToLog(activePlayer.getName() + "has bought a house on" + prop.getTitle());
            bank.deposit(propColour.getHouseCost());
        } else {
            log.addToLog(activePlayer.getName() + "has insuffient funds or has the maximum amount of houses");
        }
    }

    /**
     * Buys a house on the player's location if owned
     */
    public void buyHouse() {
        ColouredProperty prop = (ColouredProperty) board.getBoardPiece(activePlayer.getLocation());
        if (prop.getHouseCost() < activePlayer.getBalance() && prop.getHouseCount() <= 5) {
            activePlayer.decreaseBalance(prop.getHouseCost());
            checkIfBankrupt();
            prop.setHouseCount(prop.getHouseCount() + 1);
            int rentValue = prop.getHouses().get(prop.getHouseCount());
            prop.setRent(Integer.toString(rentValue));
            log.addToLog(activePlayer.getName() + "has bought a house.");
            bank.deposit(prop.getHouseCost());
        }
    }

    /**
     * Sells house on a property
     * @param property Property to sell a house
     */
    public void sellHouse(ColouredProperty property) {
        if (property.getHouseCount() > 0) {
            activePlayer.increaseBalance(property.getHouseCost());
            property.setHouseCount(property.getHouseCount() - 1);
            bank.withdraw(property.getHouseCost());
            int rentValue = property.getHouses().get(property.getHouseCount());
            property.setRent(Integer.toString(rentValue));
            log.addToLog(activePlayer.getName() + " has sold a house.");
        }
    }

    /**
     * Check to see if the bids in an auction are valid such that the maximum bid does not
     * occur more than once
     * @param bids HashMap of Players and their bids
     * @return true if valid auction
     */
    public boolean checkValidAuction(HashMap<Player, Integer> bids) {
        int maxBid = 0;
        for (Entry<Player, Integer> playerBid : bids.entrySet()) {
            if (playerBid.getValue() > maxBid) { //find max bid
                maxBid = playerBid.getValue();
            }
        }

        int maxBidOccurances = 0;
        for (Entry<Player, Integer> playerBid : bids.entrySet()) {
            if (playerBid.getValue() == maxBid) {
                maxBidOccurances++;
            }
            if (maxBidOccurances > 1) {
                //If more than 1 player inputs the same highest bid
                return false;
            }
            if ((playerBid.getKey().getBalance() < playerBid.getValue()) || (playerBid.getValue() < 0)) {
                //If player's balance is less than bid OR bid is less than 0
                return false;
            }
        }
        return true;
    }

    /**
     * Performs an auction
     * @param bids HashMap of Players and their bids
     * @throws NotAProperty Not a property piece
     */
    public void auction(HashMap<Player, Integer> bids) throws NotAProperty {
        if (!(board.getBoardPiece(activePlayer.getLocation()) instanceof Property)) {
            throw new NotAProperty("Player's current location is not a Property");
        } else {
            Property prop = (Property) board.getBoardPiece(activePlayer.getLocation());
            Entry<Player, Integer> winner = null;
            for (Entry<Player, Integer> playerBid : bids.entrySet()) {
                if ((winner == null) || (playerBid.getValue() > winner.getValue())) {
                    winner = playerBid;
                }
            }
            buyProperty(prop, new Pair<>(winner.getKey(), winner.getValue()));
        }
    }

    /**
     * Mortgage a property
     * @param prop Property to be mortgaged
     */
    public void mortgageProperty(Property prop) {
        prop.setMortgaged(true);
        bank.withdraw(prop.getCost() / 2);
        activePlayer.increaseBalance(prop.getCost() / 2);
        log.addToLog(activePlayer.getName() + " has mortgaged " + prop.getTitle() + ".");
    }

    /**
     * Unmortgage a property
     * @param prop Property to be unmortgaged
     */
    public void unmortgageProperty(Property prop) {
        prop.setMortgaged(false);
        bank.deposit(prop.getCost() / 2);
        activePlayer.decreaseBalance(prop.getCost() / 2);
        checkIfBankrupt();
        log.addToLog(activePlayer.getName() + "has paid his debt to the bank for " + prop.getTitle() + ".");
    }

    /**
     * Player pays specified tax to FreeParking
     * @param tp TaxPiece player has landed upon
     */
    public void payTax(TaxPiece tp) {
        FreeParkingPiece fpp = (FreeParkingPiece) getBoard().getBoardPiece(20);
        getActivePlayer().decreaseBalance(tp.getTaxAmount());
        checkIfBankrupt();
        fpp.setBalance(fpp.getBalance() + tp.getTaxAmount());

    }

    /**
     * Removes player if bankrupt
     */
    public void checkIfBankrupt() {
        if (checkBankrupt()) {
            log.addToLog(activePlayer.getName() + "has gone bankrupt and is out the game");
            amountOfPlayers.remove(activePlayer);
            if (winningConditions() != null) {
                log.addToLog(amountOfPlayers.get(0).getName() + " has won the game");
            }

        }
    }

    /**
     * Checks if player is bankrupt
     * @return true if player has no money and properties
     */
    public boolean checkBankrupt() {
        return activePlayer.getBalance() < 0 && activePlayer.getOwnedProperties().isEmpty();
    }

    /**
     * The winning conditions of the game
     * @return the winning player
     */
    public Player winningConditions() {
        if (amountOfPlayers.size() == 1) {
            return amountOfPlayers.get(0);
        }
        return null;
    }
    
    /*
    Getters and setters
     */
    
    /**
     * Gets board object
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets list of players
     * @return list of players
     */
    public ArrayList<Player> getAmountOfPlayers() {
        return amountOfPlayers;
    }

    /**
     * Gets current player
     * @return current player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Gets roll of both dice
     * @return dice values
     */
    public Pair<Integer, Integer> getRolls() {
        return rolls;
    }

    /**
     * Gets the player's actions
     * @return list of player's actions
     */
    public ArrayList<String> getActions() {
        return actions;
    }

    /**
     * Gets the list of playable tokens
     * @return list of playable tokens
     */
    public ArrayList<String> getTokens() {
        return tokens;
    }

    /**
     * Add an OpportunityKnocks card to card stack
     * @param ok OpportunityKnocks card
     */
    public void addToOppo(OpportunityKnocks ok) {
        oppocards.add(ok);
    }

    /**
     * Add a PotLuck card to card stack
     * @param pl PotLuck card
     */
    public void addToPotLuck(PotLuck pl) {
        potluckcards.add(pl);
    }

}
