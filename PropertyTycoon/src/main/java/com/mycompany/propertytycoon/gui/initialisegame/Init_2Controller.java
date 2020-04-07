/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.initialisegame;

import com.mycompany.propertytycoon.GameController;
import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class Init_2Controller implements Initializable {

    @FXML
    private Button confirm, back;

    @FXML
    private ListView<String> p1_list, p2_list;

    @FXML
    private TextField p1_name, p2_name;

    private StageManager SM = StageManager.getInstance();
    private ObservableList<String> tokens = FXCollections.<String>observableArrayList("cat", "boot", "cup", "hat_hanger", "phone", "spoon");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1_list.getItems().addAll(tokens);
        p2_list.getItems().addAll(tokens);

        confirm.setOnAction(e -> {
            if (!p1_name.getText().isEmpty() && !p2_name.getText().isEmpty() && uniqueToken()) {
                SM.setPlayerNumber(2);
                SM.createGameController(SM.getPlayerNum());
                SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                SM.changeScene(View.GAME);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid inputs");
                alert.setHeaderText("Fields not entered correctly");
                alert.setContentText("Name fields must be filled in and each player must have a unique token");
                alert.showAndWait();
            }

        });
        back.setOnAction(e -> SM.changeScene(View.PLAYER_BOT_COUNT));

    }

    public boolean uniqueToken() {
        boolean unique = false;

        String p1 = p1_list.getSelectionModel().getSelectedItem();
        String p2 = p2_list.getSelectionModel().getSelectedItem();

        if (!p1.equalsIgnoreCase(p2)) {
            unique = true;
        }
        return unique;
    }
}
