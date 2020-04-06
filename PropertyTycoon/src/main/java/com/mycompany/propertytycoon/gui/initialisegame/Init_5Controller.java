/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.initialisegame;

import com.mycompany.propertytycoon.boardpieces.Property;
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
public class Init_5Controller implements Initializable {


    @FXML
    private Button confirm, back;

    @FXML
    private ListView<String> p1_list, p2_list, p3_list, p4_list, p5_list;

    @FXML
    private TextField p1_name, p2_name, p3_name, p4_name, p5_name;

    private StageManager SM = StageManager.getInstance();
    private ObservableList<String> tokens = FXCollections.<String>observableArrayList("cat", "boot", "cup", "hat_hanger", "phone", "spoon");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1_list.getItems().addAll(tokens);
        p2_list.getItems().addAll(tokens);
        p3_list.getItems().addAll(tokens);
        p4_list.getItems().addAll(tokens);
        p5_list.getItems().addAll(tokens);

        confirm.setOnAction(e -> {
            if (checkNames() && uniqueToken()) {
                SM.setPlayerNumber(5);
                SM.createGameController(SM.getPlayerNum());
                SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                SM.getGame().getAmountOfPlayers().get(2).setName(p3_name.getText());
                SM.getGame().getAmountOfPlayers().get(2).setToken(p3_list.getSelectionModel().getSelectedItem());
                SM.getGame().getAmountOfPlayers().get(3).setName(p4_name.getText());
                SM.getGame().getAmountOfPlayers().get(3).setToken(p4_list.getSelectionModel().getSelectedItem());
                SM.getGame().getAmountOfPlayers().get(4).setName(p5_name.getText());
                SM.getGame().getAmountOfPlayers().get(4).setToken(p5_list.getSelectionModel().getSelectedItem());
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
        boolean unique = true;

        String p1 = p1_list.getSelectionModel().getSelectedItem();
        String p2 = p2_list.getSelectionModel().getSelectedItem();
        String p3 = p3_list.getSelectionModel().getSelectedItem();
        String p4 = p4_list.getSelectionModel().getSelectedItem();
        String p5 = p5_list.getSelectionModel().getSelectedItem();

        ArrayList<String> strings = new ArrayList<>();
        strings.add(p1);
        strings.add(p2);
        strings.add(p3);
        strings.add(p4);
        strings.add(p5);
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size(); j++) {
                if (i != j && strings.get(i).equalsIgnoreCase(strings.get(j))) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    public boolean checkNames() {
        boolean notEmpty = true;
        if (p1_name.getText().isEmpty()) {
            notEmpty = false;
        } else if (p2_name.getText().isEmpty()) {
            notEmpty = false;
        } else if (p3_name.getText().isEmpty()) {
            notEmpty = false;
        } else if (p4_name.getText().isEmpty()) {
            notEmpty = false;
        } else if (p5_name.getText().isEmpty()) {
            notEmpty = false;
        }

        return notEmpty;
    } 
}
