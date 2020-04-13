/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.Property;

import java.util.Random;

/**
 * @author chris
 */
public class AiPlayer extends Player {

    Random rn = new Random();

    public AiPlayer() {
        super();
        setIsAI(true);
    }

    public boolean DoesAiBuy() {
        int answer = rn.nextInt(1);
        if (answer == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int AiAuctionValue(Property p) {
        int maxValue = p.getCost();
        int value = rn.nextInt(maxValue);
        return value;
    }

    public boolean tryBuyHouse() {
        if (getBalance() > 500) {
            int answer = rn.nextInt(1);
            if (answer == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean trySellProperty() {
        if (getBalance() < 300) {

            int answer = rn.nextInt(1);
            if (answer == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
