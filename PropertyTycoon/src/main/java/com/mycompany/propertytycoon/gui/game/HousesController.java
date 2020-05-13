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
public class HousesController implements Initializable {

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

        List<ColouredProperty> properties = SM.getGame().listOfHousableProps();
        ObservableList<String> props = FXCollections.<String>observableArrayList();
        for (ColouredProperty p : properties) {
            props.add(p.getTitle());
        }

        canAddHouses.getItems().addAll(props);

        confirm.setOnAction(e -> {
            String selected = canAddHouses.getSelectionModel().getSelectedItem();
            ColouredProperty selectedProperty = null;
            for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
                if (p.getTitle().equals(selected)) {
                    selectedProperty = (ColouredProperty) p;
                }
            }

            if (SM.getGame().canAddHouse(selectedProperty) && SM.getGame().canAffordHouse(selectedProperty)) {
                SM.getGame().buyHouse(selectedProperty);
                SM.changeScene(View.HOUSES_CHOICE);
            } else {
                if (!SM.getGame().canAddHouse(selectedProperty)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("Cannot increase houses/hotel.");
                    alert.setContentText("Choose another property.");
                    alert.showAndWait();
                } else if (!SM.getGame().canAffordHouse(selectedProperty)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("Cannot afford another house.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("Cannot afford another houses/hotel and cannot add another house/hotel");
                    alert.showAndWait();
                }
            }
        });
        cancel.setOnAction(e -> SM.changeScene(View.HOUSES_CHOICE));

    }

}
