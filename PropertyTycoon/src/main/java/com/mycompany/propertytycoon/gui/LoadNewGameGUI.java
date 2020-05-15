package com.mycompany.propertytycoon.gui;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author ankeet
 */
public class LoadNewGameGUI extends Application {

    /**
     * Method to load the game
     * @param primaryStage - the stage used to show the game
     * @throws MalformedURLException - When URL is bad
     */
    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        StageManager SM = StageManager.getInstance();
        SM.setStage(primaryStage);
        SM.init();
    }

}
