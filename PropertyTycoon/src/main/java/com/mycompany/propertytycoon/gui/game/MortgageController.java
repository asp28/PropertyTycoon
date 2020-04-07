/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.boardpieces.Property;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class MortgageController implements Initializable {

    @FXML
    private Text player_name, property_value;
    
    @FXML
    private ListView<String> player_properties, player_mortgaged;
    
    @FXML
    private Button confirm;
    
    private StageManager SM = StageManager.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player_name.setText("Player: " + SM.getGame().getActivePlayer().getName());
        List<String> p1 = new ArrayList<>();
        SM.getGame().getActivePlayer().getOwnedProperties().forEach((p) -> {
            p1.add(p.getTitle());
        });
        ObservableList<String> p1_names = FXCollections.<String>observableArrayList(p1);
        player_properties = new ListView(p1_names);
       
        List<String> p2 = new ArrayList<>();
        SM.getGame().getActivePlayer().getOwnedProperties().forEach((p) -> {
            if (p.isMortgaged()) {
                p2.add(p.getTitle());
            }
        });
        ObservableList<String> p2_names = FXCollections.<String>observableArrayList(p2);
        player_mortgaged = new ListView(p2_names);
        confirm.setOnAction(e -> {
            ObservableList<String> propToSell = player_properties.getSelectionModel().getSelectedItems();
            ObservableList<String> propToUnMortgage = player_mortgaged.getSelectionModel().getSelectedItems();
            for (String s : propToSell) {
                for (Property p: SM.getGame().getActivePlayer().getOwnedProperties()) {
                    if (p.getTitle().equalsIgnoreCase(s)) {
                        SM.getGame().mortgageProperty(p);
                        break;
                    }
                }
            }
            for (String s : propToUnMortgage) {
                for (Property p: SM.getGame().getActivePlayer().getOwnedProperties()) {
                    if (p.getTitle().equalsIgnoreCase(s)) {
                        SM.getGame().unmortgageProperty(p);
                        break;
                    }
                }
            }
            SM.changeScene(View.GAME);
        
        });
    }    
    
}
