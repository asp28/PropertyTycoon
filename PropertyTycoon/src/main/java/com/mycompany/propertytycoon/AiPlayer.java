package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.Random;

/**
 * Property Tycoon AI Player
 */
public class AiPlayer extends Player {

    Random rn = new Random();

    /**
     * AI constructor
     */
    public AiPlayer() {
        super();
        setIsAI(true);
    }
    
    //Methods

    /**
     * Does the AI want to buy a property or a house
     * @return true if AI wants to buy and vice versa
     */
    public boolean DoesAiBuy() {
        int answer = rn.nextInt(2);
        if (answer == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * AI puts forward a bid for a property
     * @param p Property AI will bid for
     * @return a bid value for the property
     */
    public int AiAuctionValue(Property p) {
        int maxValue = p.getCost();
        int value = rn.nextInt(maxValue);
        return value;
    }

    /**
     * If the AI has the funds, does it want to buy a house
     * @return true if AI has the funds and wants to buy a house
     */
    public boolean tryBuyHouse() {
        if (getBalance() > 500) {
            int answer = rn.nextInt(2);
            if (answer == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * If AI is short in funds, does AI sell a property
     * @return true if AI wants to sell a property
     */
    public boolean trySellProperty() {
        if (getBalance() < 300) {

            int answer = rn.nextInt(2);
            if (answer == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
