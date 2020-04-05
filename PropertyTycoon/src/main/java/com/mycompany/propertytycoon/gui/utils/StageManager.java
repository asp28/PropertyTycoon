/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.utils;

import com.mycompany.propertytycoon.GameController;
import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.log.Log;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author ankeet
 */
public class StageManager {

    private static StageManager instance;

    private Stage currStage;
    
    private int playerNum;
    
    private GameController game;
    
    private Log log;
    
    private ArrayList<Player> players;

    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }
    
    public void setStage(Stage s) {
        currStage = s;
    }

    public void init() {
        try {
            String canon = new File(".").getCanonicalPath();
            System.out.print("\n\n\n" + canon + "\n\n\n");
        } catch (IOException e) {
            
        }
        Parent root = loadHierarchy("src/main/java/com/mycompany/propertytycoon/gui/mainmenu/homepage.fxml");
        Scene scene = new Scene(root);

        currStage.setTitle("Property Tycoon");
        currStage.setScene(scene);
        currStage.show();
    }

    private Parent loadHierarchy(String fxmlFilePath) {
        Parent p = null;
        try {
            URL url = new File(fxmlFilePath).toURI().toURL();
            FXMLLoader loader = new FXMLLoader();
            p = FXMLLoader.load(url);
        } catch (IOException e) {
            System.err.print("Caught IO exception");
        }
        return p;
    }
    
    public void changeScene(final View view) {
        try {
            Parent viewHierarchy = loadHierarchy(view.getFXMLPath());
            Scene scene = new Scene(viewHierarchy);
            currStage.setScene(scene);
            currStage.show();
            
        }
        catch (Exception e) {
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
    
    public void createGameController(int players) {
        try {
            game = new GameController(players);
        } catch (IOException | InvalidFormatException e) {

        }
    }
    
    public GameController getGame() {
        return game;
    }
    
    public void createLog() {
        log = new Log();
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
    
}
