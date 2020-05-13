/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class HousesController_sell implements Initializable {

    @FXML
    private Button confirm, cancel;

    @FXML
    private ListView<String> canAddHouses;

    private StageManager SM = StageManager.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> props = FXCollections.<String>observableArrayList();
        SM.getGame().getActivePlayer().getOwnedProperties().stream().filter((p) -> (p instanceof ColouredProperty)).forEachOrdered((p) -> {
            ColouredProperty prop = (ColouredProperty) p;
            if (prop.getHouseCount() != 0) {
                props.add(p.getTitle());
            }
        });

        canAddHouses.getItems().addAll(props);

        confirm.setOnAction(e -> {
            String selected = canAddHouses.getSelectionModel().getSelectedItem();
            ColouredProperty selectedProperty = null;
            for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
                if (p.getTitle().equals(selected)) {
                    selectedProperty = (ColouredProperty) p;
                }
            }
            SM.getGame().sellHouse(selectedProperty);
            SM.changeScene(View.HOUSES_CHOICE);
        });
        cancel.setOnAction(e -> SM.changeScene(View.HOUSES_CHOICE));
    }

}
