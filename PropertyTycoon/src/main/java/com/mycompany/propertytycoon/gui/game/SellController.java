/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
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
public class SellController implements Initializable {

    
    @FXML
    private Button confirm;
    
    @FXML
    private ListView<String> owned_properties;
    
    @FXML
    private Text player_name;
    
    private StageManager SM = StageManager.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player_name.setText("Player: " + SM.getGame().getActivePlayer().getName());
        List<Property> owned = SM.getGame().getActivePlayer().getOwnedProperties();
        ObservableList<String> props = FXCollections.<String>observableArrayList();
        for (Property p : owned) {
            props.add(p.getTitle());
        }
        owned_properties = new ListView(props);
        
        confirm.setOnAction(e -> {
            ObservableList<String> chosen = owned_properties.getSelectionModel().getSelectedItems();
            for (String s : chosen) {
                for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
                    if (p.getTitle().equalsIgnoreCase(s)) {
                        SM.getGame().sellProperty(p);
                        break;
                    }
                }
            }
            SM.changeScene(View.GAME);
            
        });
        
    
    }    
    
}
