/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
public class HousesController implements Initializable {

    @FXML
    private Button confirm, houses_neg, houses_pos, hotel_neg, hotel_pos;
    
    @FXML
    private ListView<String> canAddHouses;
    
    @FXML
    private Text houses_val, hotel_val;
    
    private StageManager SM = StageManager.getInstance();
    private int hotel_count, houses_count;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hotel_count = 0;
        houses_count = 0;
        
        List<Property> properties = SM.getGame().getActivePlayer().getOwnedProperties();
        
    }    
    
}
