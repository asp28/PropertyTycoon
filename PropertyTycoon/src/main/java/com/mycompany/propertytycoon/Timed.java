/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.log.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.util.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author ankeet
 */
public class Timed extends GameController {
    
    private static StageManager SM = StageManager.getInstance();
    private static Log log = Log.getInstance();
    private Timer t;
    private TimerTask task;
    
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
        t = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                SM.setTimerEnded(true);
                log.addToLog("TIME HAS RUN OUT. THE GAME WILL END WHEN ALL REMAINING PLAYERS HAVE TAKEN THE SAME NUMBER OF TURNS.");
                t.purge();
            }
        };
        long time = 60*1000*timerLength;
        t.schedule(task, time);
    }
    
    public boolean fullTurn() {
        System.out.println("Player 1:" + getAmountOfPlayers().get(0).getPlayerTurns() + " Player 2:" + getAmountOfPlayers().get(getAmountOfPlayers().size() - 1).getPlayerTurns());
        return (getAmountOfPlayers().size() -1) == getAmountOfPlayers().indexOf(getActivePlayer());
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

    public Timer getT() {
        return t;
    }

    public void setT(Timer t) {
        this.t = t;
    }

    public TimerTask getTask() {
        return task;
    }

    public void setTask(TimerTask task) {
        this.task = task;
    }
    
}
