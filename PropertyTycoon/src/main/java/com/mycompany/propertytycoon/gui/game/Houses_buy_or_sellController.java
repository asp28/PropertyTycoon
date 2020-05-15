package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class Houses_buy_or_sellController implements Initializable {
    
    @FXML
    private Button buy, sell, cancel;
    
    private StageManager SM = StageManager.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buy.setOnAction(e -> {SM.changeScene(View.HOUSES);});
        sell.setOnAction(e -> {SM.changeScene(View.HOUSES_SELL);});
        cancel.setOnAction(e -> {SM.changeScene(View.GAME);});
    }    
    
}
