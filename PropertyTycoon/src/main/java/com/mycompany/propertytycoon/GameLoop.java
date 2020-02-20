package com.mycompany.propertytycoon;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {

    private ArrayList<Player> amountOfPlayers = new ArrayList<>();
    private Board board;
    private Bank bank;
    private Dice dice;
    private Scanner scanner = new Scanner(System.in);

    private Player activePlayer;

    /**
     * Gets the player who is currently taking their round
     *
     * @return Player object
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Get main board object
     *
     * @return Board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get bank from game
     *
     * @return Bank object
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Initial method to initialize Player objects
     *
     * @param NoOfPlayer
     * @throws IOException
     * @throws InvalidFormatException
     */
    public GameLoop(int NoOfPlayer) throws IOException, InvalidFormatException {
        board = new Board();
        bank = new Bank(board.getBoardLocations());
        dice = new Dice();
        Token token = Token.CAT;

        for (int i = 0; i < NoOfPlayer; i++) {
            System.out.println("Please enter a character name");
            String characterName = scanner.nextLine();
            Player player = new Player(board, dice, bank, characterName, token);
            amountOfPlayers.add(player);
        }
        activePlayer = amountOfPlayers.get(0);
    }

    /**
     * main method to run program
     *
     * @param args
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        GameLoop gameLoop = new GameLoop(2);
        gameLoop.gameLoop();
    }

    /**
     * View player properties such as balance and owned properties
     *
     * @param player
     */
    private void viewStats(Player player) {
        System.out.println(player.getCharacter() + " Current balance is: " + player.getPlayerBalance());
        System.out.println(player.getCharacter() + " Current owned Properties are: ");
        for (BoardPiece pc : player.getOwnedProperties()) {
            System.out.println(pc.getTitle());
        }
    }

    /**
     *
     * @param player
     */
    private void rollingDice(Player player) {
        int startLocation = player.getPlayerLocation();
        String rollDice = "n";
        while (rollDice.equals("n")) {
            System.out.println("Would you like to roll the dice? Type 'y'");
            rollDice = scanner.nextLine();
        }
        player.rollDice();
        int endLocation = player.getPlayerLocation();

        System.out.println(player.getCharacter() + " has rolled a: " + (endLocation - startLocation));
    }

    /**
     * Player to perform an operation such as buying a property or paying rent
     * to another player
     *
     * @param player
     */
    private void performCommand(Player player) {
        String commandsAllowed = player.viewActionsOnBoardPosition();

        if (commandsAllowed.equals("BUY")) {
            String buyOrNotBuy = "x";

            System.out.println("Would you like to buy the property (y/n)");
            buyOrNotBuy = scanner.nextLine();

            if (buyOrNotBuy.equals("y")) {
                player.buyProperty(board.getBoardLocations().get(player.getPlayerLocation()), player.getPlayerLocation());
                System.out.println(player.getCharacter() + " has Brought: " + board.getBoardLocations().get(player.getPlayerLocation()).getTitle());
            }
        }
        if (commandsAllowed.equals("RENT")) {
            Property prop = (Property) board.getBoardLocations().get(player.getPlayerBalance());
            String ownedBy = prop.getOwnedBuy();
            System.out.println("This Property is owned by " + ownedBy + "\nYou are required to pay: £" + board.getBoardLocations().get(player.getPlayerLocation()).getTitle()+ " in rent");
            Player ownerPlayer = null;
            for (Player p : amountOfPlayers) {
                if (p.getCharacter().equals(ownedBy)) {
                    ownerPlayer = p;
                }
            }
            String hasPaid = player.payRent(board.getBoardLocations().get(player.getPlayerLocation()), ownerPlayer);
            if (hasPaid.equals("UNABLETOPAY")) {
                System.out.println("It seems that " + player.getCharacter() + "Has been unable to pay rent. They are out of the game");
                amountOfPlayers.remove(player);
            }
            System.out.println("Rent paid successfully \n");
        }
    }

    /**
     * Loop of player turns until there is only 1 player left
     */
    public void gameLoop() {
        while (amountOfPlayers.size() > 1) {
            for (Player player : amountOfPlayers) {
                activePlayer = player;
                //Starting the round
                System.out.println("It is " + player.getCharacter() + " go!");
                viewStats(player);

                //Rolling and movement
                System.out.println(player.getCharacter() + " is currently at: " + board.getBoardLocations().get(player.getPlayerLocation()).getTitle());
                rollingDice(player);
                System.out.println(player.getCharacter() + " has move to  " + board.getBoardLocations().get(player.getPlayerLocation()).getTitle());
                Property prop = (Property) board.getBoardLocations().get(player.getPlayerLocation());
                System.out.println(prop.getTitle()+ " is currently owned by " + prop.getOwnedBuy());

                //Player commands
                performCommand(player);

                //Final view of Player Stats
                System.out.println("Viewing your player Stats");
                viewStats(player);
                System.out.println();
                //Ending the players go
                String endGo = "n";
                while (endGo.equals("n")) {
                    System.out.println("Would you like to end your go, type y");
                    endGo = scanner.nextLine();
                }

            }
        }
    }

    /**
     * Does the action of the OpportunityKnocks card.
     *
     * @param opportunityKnocks
     */
    public void doAction(OpportunityKnocks opportunityKnocks) {
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
 * @param potluck 
 */
    public void doAction(PotLuck potluck) {
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

}
