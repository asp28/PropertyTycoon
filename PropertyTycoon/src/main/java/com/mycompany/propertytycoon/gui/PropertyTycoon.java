/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui;

import com.mycompany.propertytycoon.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author BigNerdNotation
 */
public class PropertyTycoon extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, InvalidFormatException {
        GameLoop gameLoop = new GameLoop(2);

        BorderPane bPane = new BorderPane();
        VBox hBox = new VBox(8);
        bPane.setRight(hBox);

        Label playerStatus = new Label("Player");
        Label playerCards = new Label("Player Cards");       
        hBox.getChildren().addAll(playerStatus, playerCards);

        Scene scene = new Scene(bPane, 300, 250);

        primaryStage.setTitle("Property Tycoon");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
