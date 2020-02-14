/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui;

import com.mycompany.propertytycoon.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author BigNerdNotation
 */
public class PropertyTycoon extends Application {

    GameLoop gl;

    @Override
    public void start(Stage primaryStage) throws IOException, InvalidFormatException {
        gl = createGame(2);

        BorderPane bPane = new BorderPane();
        VBox all = new VBox();
        all.getChildren().addAll(Players(), PlayerCards(), buttons(), log());
        all.setSpacing(10);
        all.setPadding(new Insets(10, 10, 10, 10));
        bPane.setRight(all);
        bPane.setCenter(board());

        Scene scene = new Scene(bPane, 1500, 1000);
        primaryStage.setTitle("Property Tycoon");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public GameLoop createGame(int num) throws IOException, InvalidFormatException {
        return new GameLoop(num);
    }

    public VBox Players() {
        VBox hb = new VBox();
        hb.getChildren().addAll(new Label("Player :" + "name"), new Label("£" + "BALANCE OF PLAYER"));
        hb.setStyle("-fx-border-color: black;");
        return hb;
    }

    public VBox PlayerCards() {
        VBox playerCards = new VBox();
        playerCards.getChildren().addAll(new Label("Cards Owned by Player"), new Label("GET PLAYER OWNEDPROPERTIES ARRAYLIST"));
        playerCards.setStyle("-fx-border-color: black;");
        return playerCards;

    }

    public ImageView board() throws FileNotFoundException {
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/PropertyTycoon.png");
        Image image = new Image(inputstream) {
        };
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(1000);
        imageView.setFitWidth(1000);
        return imageView;
    }

    public VBox buttons() {
        VBox buttons = new VBox();
        HBox first = new HBox();
        Button roll = new Button("Roll");
        Button buy = new Button("Buy");
        roll.setOnAction((event) -> {
            /**
             * ROLL
             */
        });
        buy.setOnAction((event) -> {
            /**
             * Buy Property
             */
        });
        first.getChildren().addAll(roll, buy);
        first.setSpacing(50);
        HBox second = new HBox();
        Button sell = new Button("Sell");
        Button house = new Button("Houses");
        sell.setOnAction((event) -> {
            /**
             * Sell
             */
        });
        house.setOnAction((event) -> {
            /**
             * Buy House
             */
        });
        second.getChildren().addAll(sell, house);
        second.setSpacing(50);
        buttons.getChildren().addAll(first, second);
        buttons.setSpacing(50);
        buttons.setStyle("-fx-border-color: black;");
        return buttons;
    }
    
    public ScrollPane log() {
        ScrollPane sp = new ScrollPane();
        return sp;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
