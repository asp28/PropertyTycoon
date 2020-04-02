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
    private ListView p1_list, p2_list;
    
    @FXML
    private TextField p1_name, p2_name;
    
    private StageManager SM = StageManager.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        confirm.setOnAction(e -> {
            //todo
            
        });
        back.setOnAction(e -> SM.changeScene(View.PLAYER_BOT_COUNT));
        
        
    }    
    
}
