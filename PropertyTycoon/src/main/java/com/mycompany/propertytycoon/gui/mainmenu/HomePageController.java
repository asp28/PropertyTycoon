/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.mainmenu;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author ankeet
 */
public class HomePageController implements Initializable {
    
    private StageManager SM = StageManager.getInstance();
    
    @FXML
    private Button normal;
    @FXML
    private Button timed;
    @FXML
    private Button quit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        normal.setOnAction(e -> SM.changeScene(View.PLAYER_BOT_COUNT));
        timed.setOnAction(e -> SM.changeScene(View.PLAYER_BOT_COUNT));
        quit.setOnAction(e -> SM.quit());
    }
    
    
}
