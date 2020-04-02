/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ankeet
 */
public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        StageManager SM = StageManager.getInstance();
        SM.setStage(primaryStage);
        SM.init();
        /*
        Parent root = null;
        Scene scene = null;
        try {
            URL url = new File("src/main/java/com/mycompany/propertytycoon/gui/mainmenu/homepage.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            scene = new Scene(root);
        } catch (IOException e) {

        }
        
        primaryStage.setScene(scene);
        primaryStage.show();
         */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
