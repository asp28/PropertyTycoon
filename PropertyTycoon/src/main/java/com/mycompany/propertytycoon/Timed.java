/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author ankeet
 */
public class Timed extends GameController {
    
    private static StageManager SM = StageManager.getInstance();
    
    /**
     * TIMER IS IN MINUTES
     * @param amountOfPlayer
     * @param amountOfBots
     * @param timerLength
     * @throws IOException
     * @throws InvalidFormatException 
     */
    public Timed(int amountOfPlayer, int amountOfBots, int timerLength) throws IOException, InvalidFormatException {
        super(amountOfPlayer, amountOfBots);
        
    }
    
    private boolean fullTurn() {
        return getAmountOfPlayers().get(0) == getAmountOfPlayers().get(getAmountOfPlayers().size()-1);
    }
    
    @Override
    public Player winningConditions() {
        if(getAmountOfPlayers().size() == 1) {
            return getAmountOfPlayers().get(0);
        } else {
            ArrayList<Pair<Player, Integer>> totals = new ArrayList<>();
            for (Player p : getAmountOfPlayers()) {
                int total = 0;
                for (Property prop : p.getOwnedProperties()) {
                    if (prop.isMortgaged()) {
                        total += prop.getCost()/2;
                    } else {
                        total += prop.getCost();
                    }
                    if (prop instanceof ColouredProperty) {
                        ColouredProperty cp = (ColouredProperty) prop;
                        total += (cp.getHouseCount() * cp.getHouseCost());
                    }
                }
                total += p.getBalance();
                totals.add(new Pair(p, total));
            }
            Pair<Player, Integer> p = totals.get(0);
            for (Pair<Player, Integer> pair : totals) {
                if(pair.getValue() > p.getValue()) {
                    p = pair;
                }
            }
            return p.getKey();
        }
    }
}
