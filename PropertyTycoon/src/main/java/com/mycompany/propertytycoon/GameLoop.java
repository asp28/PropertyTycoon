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

    public Player getActivePlayer() {
        return activePlayer;
    }


    public GameLoop(int NoOfPlayer) throws IOException, InvalidFormatException {
        board = new Board();
        bank = new Bank(board.getBoardLocations());
        dice = new Dice();

        for (int i = 0; i < NoOfPlayer; i++) {
            System.out.println("Please enter a character name");
            String characterName = scanner.nextLine();
            Player player = new Player(board, dice, bank, characterName);
            amountOfPlayers.add(player);
        }
        activePlayer = amountOfPlayers.get(0);
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        GameLoop gameLoop = new GameLoop(2);
        gameLoop.gameLoop();
    }

    private void viewStats(Player player) {
        System.out.println(player.getCharacter() + " Current balance is: " + player.getPlayerBalance());
        System.out.println(player.getCharacter() + " Current owned Properties are: ");
        for (PropertyCards pc : player.getOwnedProperties()) {
            System.out.println(pc.getName());
        }
    }

    private void rollingDice(Player player) {
        int startLocation = player.getPlayerLocation();
        String rollDice = "n";
        while (rollDice.equals("n")) {
            System.out.println("Would you like to roll the dice type y");
            rollDice = scanner.nextLine();
        }
        player.rollDice();
        int endLocation = player.getPlayerLocation();

        System.out.println(player.getCharacter() + " has rolled a: " + (endLocation - startLocation));
    }

    private void performCommand(Player player) {
        String commandsAllowed = player.viewActionsOnBoardPosition();

        if (commandsAllowed.equals("BUY")) {
            String buyOrNotBuy = "x";

            System.out.println("Would you like to buy the property (y/n)");
            buyOrNotBuy = scanner.nextLine();

            if (buyOrNotBuy.equals("y")) {
                player.buyProperty(board.getBoardLocations().get(player.getPlayerLocation()), player.getPlayerLocation());
                System.out.println(player.getCharacter() + " has Brought: " + board.getBoardLocations().get(player.getPlayerLocation()).getName());
            }
        }
        if (commandsAllowed.equals("RENT")) {
            String ownedBy = board.getBoardLocations().get(player.getPlayerLocation()).getOwnedBuy();
            System.out.println("This Property is owned by " + ownedBy + "\nYou are required to pay: £" + board.getBoardLocations().get(player.getPlayerLocation()).getRent() + " in rent");
            Player ownerPlayer = null;
            for (Player p : amountOfPlayers) {
                if (p.getCharacter().equals(ownedBy)) {
                    ownerPlayer = p;
                }
            }
            String hasPaid = player.payRent(board.getBoardLocations().get(player.getPlayerLocation()), ownerPlayer);
            if (hasPaid.equals("UNABLETOPAY")) {
                System.out.println("It seems that " + player.getCharacter() + "Has been unable to pay rent they are out of the game");
                amountOfPlayers.remove(player);
            }
            System.out.println("Rent paid successfully \n");
        }
    }

    public void gameLoop() {
        while (amountOfPlayers.size() > 1) {
            for (Player player : amountOfPlayers) {
                activePlayer = player;
                //Starting the round
                System.out.println("It is " + player.getCharacter() + " go!");
                viewStats(player);

                //Rolling and movement
                System.out.println(player.getCharacter() + " is currently at: " + board.getBoardLocations().get(player.getPlayerLocation()).getName());
                rollingDice(player);
                System.out.println(player.getCharacter() + " has move to  " + board.getBoardLocations().get(player.getPlayerLocation()).getName());
                System.out.println(board.getBoardLocations().get(player.getPlayerLocation()).getName() + " is currently owned by " + board.getBoardLocations().get(player.getPlayerLocation()).getOwnedBuy());

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

}
