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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class TimerController implements Initializable {

    @FXML
    private Button confirm, back;

    @FXML
    private TextField value;

    private StageManager SM = StageManager.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setOnAction(e -> SM.changeScene(View.MAIN_MENU));
        confirm.setOnAction(e -> {
            String num = value.getText();
            if (num.matches("[0-9]+")) {
                SM.setTimer(Integer.parseInt(num));
                SM.changeScene(View.PLAYER_BOT_COUNT);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("invalid value");
                alert.showAndWait();
            }

        });
    }

}
