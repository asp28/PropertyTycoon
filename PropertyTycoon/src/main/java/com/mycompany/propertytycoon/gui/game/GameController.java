/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class GameController implements Initializable {

    @FXML
    private Button roll, buy, sell, houses, trade, mortgage, endTurn;
    @FXML
    private Label playerName, playerMoney;
    @FXML
    private Image profileToken;
    @FXML
    private BorderPane bPane;
    @FXML
    private VBox right;

    private StageManager SM = StageManager.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roll.setOnAction(e -> {
          updateControls();
        });
    }

    public void playerProfile() {

        playerName.setText("Player: " + SM.getGame().getActivePlayer().getName());
        playerMoney.setText("£" + SM.getGame().getActivePlayer().getBalance());
    }

    public void playerCards() {
    }

    public void buttons() {
    }

    public void log() {
    }

    public void updateControls() {
        bPane = new BorderPane();
        right = new VBox();
        right.setSpacing(10);
        right.getChildren().add(playerName);
        right.setPadding(new Insets(10, 10, 10, 0));
        bPane.setRight(right);
        System.out.print(roll);

    }

}
