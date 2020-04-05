/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private ImageView profileToken;
    @FXML
    private ScrollPane log;
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
        try {
            updateControls();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roll.setOnAction(e -> {

        });
        buy.setOnAction(e -> {

        });
        sell.setOnAction(e -> {

        });
        houses.setOnAction(e -> {

        });
        trade.setOnAction(e -> {

        });
        mortgage.setOnAction(e -> {

        });
        endTurn.setOnAction(e -> {
            SM.getGame().endTurn();
            try {
                updateControls();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void playerProfile() throws FileNotFoundException {
        String pic = SM.getGame().getActivePlayer().getToken() + ".png";
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/" + pic);
        Image image = new Image(inputstream) {
        };
        playerName.setText("Player: " + SM.getGame().getActivePlayer().getName());
        playerMoney.setText("£" + SM.getGame().getActivePlayer().getBalance());
        profileToken.setImage(image);
    }

    public void playerCards() {
    }

    public void PlayerPosition() {

    }

    public void buttons() {
    }

    public void log() {
       
    }

    public void updateControls() throws FileNotFoundException {
        playerProfile();
        log();
    }

}
