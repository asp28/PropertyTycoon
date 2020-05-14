/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    public void setStage(Stage s) {
        currStage = s;
    }

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

    public void quit() {
        currStage.close();
    }

    public void setPlayerNumber(int num) {
        playerNum = num;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setBotNumber(int num) {
        botNum = num;
    }

    public int getBotNum() {
        return botNum;
    }

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

    public GameController getGame() {
        return game;
    }

    public Log getLog() {
        return log;
    }

    public void tradePlayers(Player trader, Player other) {
        players = new ArrayList<Player>() {
            {
                add(trader);
                add(other);
            }
        };
    }

    public ArrayList<Player> getTraders() {
        return players;
    }

    public void setAuctionProperty(BoardPiece prop) {
        auctionProperty = (Property) prop;
    }

    public Property getAuctionProperty() {
        return auctionProperty;
    }

    public boolean getTimed() {
        return timed;
    }

    public void isTimed(boolean yn) {
        timed = yn;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean timerEnded() {
        return timerEnded;  
    }

    public void setTimerEnded(boolean hasEnded) {
        timerEnded = hasEnded;
    }

}
