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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class TradeController implements Initializable {

    @FXML
    private Text p1_name, p2_name;

    @FXML
    private ListView p1_list, p2_list;

    @FXML
    private TextField p1_money, p2_money;

    @FXML
    private Button confirm, cancel;

    @FXML
    private CheckBox p1_check, p2_check;

    private StageManager SM = StageManager.getInstance();
    
    private GameVariableStorage GVS = GameVariableStorage.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Player> players = GVS.getTraders();

        p1_name.setText(players.get(0).getName());
        p2_name.setText(players.get(1).getName());

        List<String> p1 = new ArrayList<>();
        for (Property p : players.get(0).getOwnedProperties()) {
            p1.add(p.getTitle());
        }
        ObservableList<String> p1_properties = FXCollections.<String>observableArrayList(p1);
        p1_list.getItems().addAll(p1_properties);
        p1_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<String> p2 = new ArrayList<>();
        for (Property p :players.get(1).getOwnedProperties()) {
            p2.add(p.getTitle());
        }
        ObservableList<String> p2_properties = FXCollections.<String>observableArrayList(p2);
        p2_list.getItems().addAll(p2_properties);
        p2_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cancel.setOnAction(e -> {
            SM.getLog().addToLog("Trade between " + players.get(0).getName() + " and " + players.get(1).getName() + " was cancelled.");
            SM.changeScene(View.GAME);
        });

        confirm.setOnAction(e -> {
            if (numbersOnly() && checkedBoxes()) {
                ArrayList<Property> p1_temp = new ArrayList<>();
                ArrayList<Property> p2_temp = new ArrayList<>();
                
                ObservableList<String> p1_tempObserv = p1_list.getSelectionModel().getSelectedItems();
                ObservableList<String> p2_tempObserv = p2_list.getSelectionModel().getSelectedItems();
                p1_tempObserv.forEach((prop) -> {
                    for (Property p : players.get(0).getOwnedProperties()) {
                        if (p.getTitle().equalsIgnoreCase(prop)) {
                            p1_temp.add(p);
                            break;
                        }
                    }
                    
                });
                p2_tempObserv.forEach((prop) -> {
                    for (Property p : players.get(1).getOwnedProperties()) {
                        if (p.getTitle().equalsIgnoreCase(prop)) {
                            p2_temp.add(p);
                            break;
                        }
                    }
                    
                });
                SM.getGame().trade(p1_temp, Integer.parseInt(p1_money.getText()), Integer.parseInt(p2_money.getText()), p2_temp, players.get(1));
                SM.changeScene(View.GAME);
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Trade not valid");
                alert.showAndWait();
            }
        });

    }

    /**
     * 
     * @return if the money field is numbers only
     */
    public boolean numbersOnly() {
        boolean num = false;
        if (p1_money.getText().matches("[0-9]+") && p2_money.getText().matches("[0-9]+")) {
            num = true;
        }
        return num;
    }
    
    /**
     * 
     * @return if both confirm the trade
     */
    public boolean checkedBoxes() {
        boolean checked = false;
        if (p1_check.isSelected() && p2_check.isSelected()) {
            checked = true;
        }
        return checked;
    }
}
