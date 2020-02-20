package com.mycompany.propertytycoon;

import java.util.ArrayList;

/**
 * PropertyTycoon Bank
 *
 * @author Duarte
 * @version 1.0
 */
public class Bank {

    private int totalMoney;
    private ArrayList<BoardPiece> unownedProperties = new ArrayList<>();

    /**
     * Bank constructor
     *
     * @param properties property cards
     */
    public Bank(ArrayList<BoardPiece> properties) {
        totalMoney = 50000;
        for (BoardPiece pc : properties) {
            if (pc instanceof Property) {
                unownedProperties.add(pc);
            }
        }
    }

    /**
     * Returns banks total money
     *
     * @return current cash
     */
    public int getMoney() {
        return totalMoney;
    }

    /**
     * Deposes money from players to bank
     *
     * @param cash money to bank
     */
    public void deposit(int cash) {
        this.totalMoney += cash;
    }

    /**
     * Returns requested amount
     *
     * @param cash money to withdraw
     * @return cash asked or 0 if bank can't pay
     */
    public int withdraw(int cash) {
        if (totalMoney > 0 && totalMoney - cash >= 0) {
            totalMoney -= cash;
            return cash;
        } else {
            totalMoney += 50000;
            System.out.print("Bank funds have been replenished");
            return withdraw(cash);
        }
    }

    /**
     * Gets properties
     *
     * @param name property name
     * @return property object or null if it doesn't exist
     */
    public BoardPiece getProperties(String name) {
        for (BoardPiece p : unownedProperties) {
            if (name.equals(p.getTitle())) {
                return p;
            }
        }
        return null; // Watch out for later
    }

    /**
     * Adds property to list of properties
     *
     * @param property property object
     */
    public void addProperties(BoardPiece property) {
        unownedProperties.add(property);
    }

    /**
     * Removes property from list of properties
     *
     * @param name property name
     */
    public void removeProperties(String name) {
        unownedProperties.remove(getProperties(name));
    }
}