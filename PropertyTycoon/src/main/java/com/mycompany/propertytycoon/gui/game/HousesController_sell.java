/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
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
    private Button confirm, house_neg, house_pos, hotel_neg, hotel_pos;

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
        
        ObservableList<String> props = FXCollections.<String>observableArrayList();
        SM.getGame().getActivePlayer().getOwnedProperties().stream().filter((p) -> (p instanceof ColouredProperty)).forEachOrdered((p) -> {
            ColouredProperty prop = (ColouredProperty) p;
            if (prop.getHouseCount() != 0) {
                props.add(p.getTitle());
            }
        });

        canAddHouses.getItems().addAll(props);
        canAddHouses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        house_pos.setOnAction(e -> {
            if (houses_count == 5) {
                //do nothing
            } else {
                houses_count++;
                houses_val.setText(String.valueOf(houses_count));
            }
        });
        house_neg.setOnAction(e -> {
            if (houses_count == 0) {
                //do nothing
            } else {
                houses_count--;
                houses_val.setText(String.valueOf(houses_count));
            }
        });
        hotel_pos.setOnAction(e -> {
            if (hotel_count == 1) {
                //do nothing
            } else {
                hotel_count++;
                hotel_val.setText(String.valueOf(hotel_count));
            }
        });
        hotel_neg.setOnAction(e -> {
            if (hotel_count == 0) {
                //do nothing
            } else {
                hotel_count--;
                hotel_val.setText(String.valueOf(hotel_count));
            }
        });
        confirm.setOnAction(e -> {
            ObservableList<String> selected = canAddHouses.getSelectionModel().getSelectedItems();
            ArrayList<ColouredProperty> selectedProperty = new ArrayList<>();
            for (String s : selected) {
                for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
                    if (p.getTitle().equals(s)) {
                        selectedProperty.add((ColouredProperty) p);
                    }
                }
            }
            if (SM.getGame().canAddHouses(selectedProperty, (houses_count + (5 * hotel_count))) && SM.getGame().canAffordHouses(selectedProperty, (houses_count + (5 * hotel_count)))) {
                for (ColouredProperty p : selectedProperty) {
                    for (int i = 0; i < (houses_count + (5 * hotel_count)); i++) {
                        SM.getGame().buyHouse(p);
                    }
                }
            } else {
                if (!SM.getGame().canAddHouses(selectedProperty, (houses_count + (5 * hotel_count)))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("The amount of houses/hotel cannot be added to the property");
                    alert.setContentText("Please choose a lower amount.");
                    alert.showAndWait();
                }
                else if (!SM.getGame().canAffordHouses(selectedProperty, (houses_count + (5 * hotel_count)))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("Cannot Afford the number of houses/hotel.");
                    alert.setContentText("Please choose a lower amount.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("Cannot Afford the number of houses/hotel and number of houses/hotel is more than the allowed amount.");
                    alert.setContentText("Please choose a lower amount.");
                    alert.showAndWait();
                }
                
            }
        });

    }

}
