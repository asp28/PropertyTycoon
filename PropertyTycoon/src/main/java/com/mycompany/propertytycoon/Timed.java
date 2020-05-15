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
 * Timed Version of Property Tycoon
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
    
    /**
     * Determines if the active player is the last player in the list
     * @return true if player is the last player in list
     */
    public boolean fullTurn() {
        System.out.println("Player 1:" + getAmountOfPlayers().get(0).getPlayerTurns() + " Player 2:" + getAmountOfPlayers().get(getAmountOfPlayers().size() - 1).getPlayerTurns());
        return (getAmountOfPlayers().size() -1) == getAmountOfPlayers().indexOf(getActivePlayer());
    }
    
    /**
     * Gets the player who wins of the game
     * @return winner of game
     */
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

    /**
     * Gets timer
     * @return timer
     */
    public Timer getT() {
        return t;
    }

    /**
     * Sets timer
     * @param t timer
     */
    public void setT(Timer t) {
        this.t = t;
    }

    /**
     * Gets the current TimerTask
     * @return TimerTask
     */
    public TimerTask getTask() {
        return task;
    }

    /**
     * Sets the current TimerTask
     * @param task TimerTask
     */
    public void setTask(TimerTask task) {
        this.task = task;
    }
    
}
