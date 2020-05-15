package com.mycompany.propertytycoon.gui.utils;

import com.mycompany.propertytycoon.GameController;
import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.Timed;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.log.Log;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author ankeet
 */
public class StageManager {

    private static StageManager instance;

    private Stage currStage;

    private int playerNum, botNum, timer;

    private GameController game;

    private Log log = Log.getInstance();

    private ArrayList<Player> players;

    private Property auctionProperty;

    private boolean timed, timerEnded = false;

    private Player winner;

    /**
     * 
     * @return instance of the stagemanager
     */
    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    /**
     * set the stage
     * @param s - Stage to set
     */
    public void setStage(Stage s) {
        currStage = s;
    }

    /**
     * Initial loading of the main menu
     * @throws MalformedURLException - Malformed URL
     */
    public void init() throws MalformedURLException {
        Parent root = loadHierarchy("src/main/java/com/mycompany/propertytycoon/gui/mainmenu/homepage.fxml");
        Scene scene = new Scene(root);

        currStage.setTitle("Property Tycoon");
        currStage.setScene(scene);
        URL url = new File("src/main/java/resources/img/PTBarLogo.png").toURI().toURL();
        Image i = new Image(url.toString());
        currStage.getIcons().add(i);
        currStage.show();
    }

    /**
     * private method to load the FXML file
     * @param fxmlFilePath - String filepath of where fxml is located
     * @return Parent node
     */
    private Parent loadHierarchy(String fxmlFilePath) {
        Parent p = null;
        try {
            URL url = new File(fxmlFilePath).toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            p = FXMLLoader.load(url);
        } catch (IOException e) {
            System.err.print(e + " at " + fxmlFilePath + "\n\n");
            quit();
        }
        return p;
    }

    /**
     * Changes the scene shown on the stage
     * @param view - enum containing file location
     */
    public void changeScene(final View view) {
        try {
            Parent viewHierarchy = loadHierarchy(view.getFXMLPath());
            Scene scene = new Scene(viewHierarchy);
            currStage.setScene(scene);
            currStage.show();

        } catch (Exception e) {
            System.err.print("");
        }
    }

    /**
     * Closes the stage
     */
    public void quit() {
        currStage.close();
    }

    /**
     * Set number of actual players(non bot)
     * @param num - number of players
     */
    public void setPlayerNumber(int num) {
        playerNum = num;
    }

    /**
     * 
     * @return number of non bot players
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Set number of bots in the game
     * @param num - number of bots to play game
     */
    public void setBotNumber(int num) {
        botNum = num;
    }

    /**
     * 
     * @return number of bots playing
     */
    public int getBotNum() {
        return botNum;
    }

    /**
     * Create the game
     * @param players - number of players
     * @param bots - number of bots
     */
    public void createGameController(int players, int bots) {
        try {
            if (timed) {
                game = new Timed(players, bots, timer);

            } else {
                game = new GameController(players, bots);
            }
        } catch (IOException | InvalidFormatException e) {

        }
    }

    /**
     * 
     * @return the game that was made by the createGameController method
     */
    public GameController getGame() {
        return game;
    }

    /**
     * 
     * @return log
     */
    public Log getLog() {
        return log;
    }

    /**
     * 
     * @param trader - active player
     * @param other - person who the active player wishes to trade with
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
     * 
     * @return a list of the traders (active player and the other trader)
     */
    public ArrayList<Player> getTraders() {
        return players;
    }

    /**
     * Set the property that is being auctioned
     * @param prop - property that is being auctioned
     */
    public void setAuctionProperty(BoardPiece prop) {
        auctionProperty = (Property) prop;
    }

    /**
     * 
     * @return the property currently being auctioned
     */
    public Property getAuctionProperty() {
        return auctionProperty;
    }

    /**
     * 
     * @return if the game is timed or not
     */
    public boolean getTimed() {
        return timed;
    }

    /**
     * Set if the game is timed or not
     * @param yn - true or false, if the game is timed
     */
    public void isTimed(boolean yn) {
        timed = yn;
    }

    /**
     * Get the name of the winner
     * @return player object of the winner
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Set who the winner is
     * @param winner - set who won the game
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Get the length of the timer if the game is timed
     * @return 
     */
    public int getTimer() {
        return timer;
    }

    /**
     * Set the length of the game
     * @param timer - length of game in minutes
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * Has the game ended
     * @return boolean true if the time limit has passed. False otherwise
     */
    public boolean timerEnded() {
        return timerEnded;  
    }

    /**
     * Set if the timer has ended.
     * @param hasEnded - true if the time has passed, false otherwise.
     */
    public void setTimerEnded(boolean hasEnded) {
        timerEnded = hasEnded;
    }

}
