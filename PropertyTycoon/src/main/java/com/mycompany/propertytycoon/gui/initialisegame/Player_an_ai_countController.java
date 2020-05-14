/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.initialisegame;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class Player_an_ai_countController implements Initializable {

    @FXML
    private Button PlayerPlus, playerMinus, BotPlus, BotMinus, confirm, Back;

    @FXML
    private Text PlayerCount, BotCount;

    private int player_count, bot_count;

    private StageManager SM = StageManager.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player_count = 2;
        bot_count = 0;
        PlayerCount.setText(String.valueOf(player_count));
        BotCount.setText(String.valueOf(bot_count));
        PlayerPlus.setOnAction(e -> {
            if (player_count < 6 && player_count >= 1) {
                player_count++;
                PlayerCount.setText((String.valueOf(player_count)));
            }
        });
        playerMinus.setOnAction(e -> {
            if (player_count <= 6 && player_count > 1) {
                player_count--;
                PlayerCount.setText((String.valueOf(player_count)));
            }
        });
        BotPlus.setOnAction(e -> {
            if (bot_count < 6 && bot_count >= 0) {
                bot_count++;
                BotCount.setText((String.valueOf(bot_count)));
            }
        });
        BotMinus.setOnAction(e -> {
            if (bot_count <= 6 && bot_count > 0) {
                bot_count--;
                BotCount.setText((String.valueOf(bot_count)));
            }
        });
        confirm.setOnAction(e -> {
            if (player_count + bot_count <= 6 && player_count + bot_count >= 2) {
                SM.setPlayerNumber(player_count);
                SM.setBotNumber(bot_count);
                SM.changeScene(View.INIT);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Player Count");
                alert.setHeaderText("Total player count is " + (player_count + bot_count));
                alert.setContentText("Total must be between 2 and 6");
                alert.showAndWait();
            }

        });
        Back.setOnAction(e -> SM.changeScene(View.MAIN_MENU));
    }

}
