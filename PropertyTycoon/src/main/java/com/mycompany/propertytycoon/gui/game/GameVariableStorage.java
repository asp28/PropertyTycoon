package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.Timed;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import com.mycompany.propertytycoon.log.Log;
import java.util.ArrayList;

/**
 *
 * @author ankeet
 */
public class GameVariableStorage {

    private static GameVariableStorage GVS;
    private static StageManager SM = StageManager.getInstance();
    private static Log log = Log.getInstance();

    private ArrayList<Player> players;
    private Property auctionProperty;
    private ArrayList<String> actions;
    private boolean rent = true;

    private boolean rolled = false;

    /**
     * static so that a new one isnt made every time a different scene is
     * initialised
     *
     * @return instance of the GVS
     */
    public static GameVariableStorage getInstance() {
        if (GVS == null) {
            GVS = new GameVariableStorage();
        }
        return GVS;
    }

    /**
     * Players who are trading
     *
     * @param trader - active player
     * @param other - selected trader
     */
    public void tradePlayers(Player trader, Player other) {
        players = new ArrayList<Player>() {
            {
                add(trader);
                add(other);
            }
        };
    }

    /**
     * Gets the traders
     *
     * @return arraylist of traders
     */
    public ArrayList<Player> getTraders() {
        return players;
    }

    /**
     * set the property to be auctioned
     *
     * @param prop - board piece object
     */
    public void setAuctionProperty(BoardPiece prop) {
        auctionProperty = (Property) prop;
    }

    /**
     *
     * @return the property to be auctioned
     */
    public Property getAuctionProperty() {
        return auctionProperty;
    }

    /**
     * Set the actions a player can do
     *
     * @param actions - all the buttons a player can click
     */
    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    /**
     *
     * @return arraylist of all actions a player can do
     */
    public ArrayList<String> getActions() {
        return actions;
    }

    /**
     * has the player rolled
     *
     * @return true if yes, false if no or player initially rolled a double
     */
    public boolean getRoll() {
        return rolled;
    }

    /**
     * set the player has rolled
     *
     * @param roll - boolean true if has rolled
     */
    public void setRolled(boolean roll) {
        rolled = roll;
    }

    /**
     * set if person has paid the rent that they owe
     *
     * @param rentPaid - boolean true if have paid
     */
    public void setRent(boolean rentPaid) {
        rent = rentPaid;
    }

    /**
     * check if player has paid the rent
     *
     * @return true if paid
     */
    public boolean getRentPaid() {
        return rent;
    }

    public void endTurnForBot() {
        if (SM.getGame() instanceof Timed) {
            Timed game = (Timed) SM.getGame();
            if (SM.timerEnded() && game.fullTurn()) {
                SM.setWinner(game.winningConditions());
                SM.changeScene(View.WINNER);
                System.out.println("Winner found");
            } else if (SM.timerEnded() && !game.fullTurn()) {
                System.out.println("full turn not done");
                //carry on until full turns ended
                if (SM.getGame().getActivePlayer().getBalance() < 0 && !SM.getGame().getActivePlayer().getOwnedProperties().isEmpty()) {
                    log.addToLog(SM.getGame().getActivePlayer().getName() + " cannot end turn as balance is negative.");
                    for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
                        SM.getGame().sellProperty(p);
                        if (SM.getGame().getActivePlayer().getBalance() >= 0) {
                            break;
                        }
                    }
                    if (SM.getGame().getActivePlayer().getBalance() < 0) {
                        SM.getGame().checkIfBankrupt();
                    } else {
                        GVS.setRolled(false);
                        SM.getGame().endTurn();
                        SM.changeScene(View.GAME);
                    }
                } else if (SM.getGame().checkBankrupt()) {
                    SM.getGame().checkIfBankrupt();
                    if (SM.getGame().winningConditions() != null) {
                        SM.setWinner(SM.getGame().winningConditions());
                        SM.changeScene(View.WINNER);
                    }
                } else {
                    System.out.println("Timer not done yet");
                    SM.getGame().endTurn();
                    GVS.setRolled(false);
                    SM.changeScene(View.GAME);
                }
            }
        } else {
            //not timed stuff here
            if (SM.getGame().getActivePlayer().getBalance() < 0 && !SM.getGame().getActivePlayer().getOwnedProperties().isEmpty()) {
                log.addToLog(SM.getGame().getActivePlayer().getName() + " cannot end turn as balance is negative.");
                for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
                    SM.getGame().sellProperty(p);
                    if (SM.getGame().getActivePlayer().getBalance() >= 0) {
                        break;
                    }
                }
                if (SM.getGame().getActivePlayer().getBalance() < 0) {
                    SM.getGame().checkIfBankrupt();
                } else {
                    GVS.setRolled(false);
                    SM.getGame().endTurn();
                    SM.changeScene(View.GAME);
                }
            } else if (SM.getGame().checkBankrupt()) {
                SM.getGame().checkIfBankrupt();
                if (SM.getGame().winningConditions() != null) {
                    SM.setWinner(SM.getGame().winningConditions());
                    SM.changeScene(View.WINNER);
                }
            } else {
                SM.getGame().endTurn();
                GVS.setRolled(false);
                SM.changeScene(View.GAME);
            }
        }
    }

}
