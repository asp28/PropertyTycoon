/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class TradeChoicePageController implements Initializable {

    @FXML
    private ListView<String> player_list;

    @FXML
    private Button confirm, back;

    private StageManager SM = StageManager.getInstance();
    private GameVariableStorage GVS = GameVariableStorage.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> p1 = new ArrayList<>();
        for (Player p : SM.getGame().getAmountOfPlayers()) {
            if (!p.getName().equalsIgnoreCase(SM.getGame().getActivePlayer().getName())) {
                p1.add(p.getName());
            }

        }
        ObservableList<String> p1_names = FXCollections.<String>observableArrayList(p1);
        player_list.getItems().addAll(p1_names);

        confirm.setOnAction(e -> {
            //DO CHECK FOR 1 PERSON BEING SELECTED
            if (player_list.getSelectionModel().getSelectedItem() != null) {
                String player = player_list.getSelectionModel().getSelectedItem();
                Player other = null;
                for (Player p : SM.getGame().getAmountOfPlayers()) {
                    if (p.getName().equalsIgnoreCase(player)) {
                        other = p;
                        break;
                    }
                }
                GVS.tradePlayers(SM.getGame().getActivePlayer(), other);
                SM.changeScene(View.TRADE);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Choose a player");
                alert.setHeaderText("Choose a player to trade with");
                alert.setContentText("Only one player can be selected.");
                alert.showAndWait();
            }

        });
        back.setOnAction(e -> {
            SM.changeScene(View.GAME);
        });
    }

}
