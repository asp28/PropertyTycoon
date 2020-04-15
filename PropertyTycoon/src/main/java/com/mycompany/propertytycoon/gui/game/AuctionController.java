/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class AuctionController implements Initializable {

    @FXML
    private Text property_name, property_colour, property_original_price, p1_name, p2_name, p3_name, p4_name, p5_name, p6_name;

    @FXML
    private TextField p1_bid, p2_bid, p3_bid, p4_bid, p5_bid, p6_bid;

    @FXML
    private CheckBox p1_check, p2_check, p3_check, p4_check, p5_check, p6_check;

    @FXML
    private Button confirm;

    private StageManager SM = StageManager.getInstance();
    private GameVariableStorage GVS = GameVariableStorage.getInstance();

    private ArrayList<TextField> bids = new ArrayList<>();
    private ArrayList<CheckBox> checks = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        property_name.setText("Name: " + GVS.getAuctionProperty().getTitle());
        property_colour.setText("Group: " + GVS.getAuctionProperty().getGroup());
        property_original_price.setText("Original Cost: £" + GVS.getAuctionProperty().getCost());
        ArrayList<Player> playersWhoCanBid = new ArrayList<>();
        for (Player p : SM.getGame().getAmountOfPlayers()) {
            if (p.getGameloops() > 0) {
                playersWhoCanBid.add(p);
            }
        }
        switch (playersWhoCanBid.size()) {
            case 0:
                p1_name.setText("");
                p2_name.setText("");
                p3_name.setText("");
                p4_name.setText("");
                p5_name.setText("");
                p6_name.setText("");
                p1_bid.setDisable(true);
                p1_check.setDisable(true);
                p2_bid.setDisable(true);
                p2_check.setDisable(true);
                p3_bid.setDisable(true);
                p3_check.setDisable(true);
                p4_bid.setDisable(true);
                p4_check.setDisable(true);
                p5_bid.setDisable(true);
                p5_check.setDisable(true);
                p6_bid.setDisable(true);
                p6_check.setDisable(true);
                break;
            case 1:
                p2_name.setText("");
                p3_name.setText("");
                p4_name.setText("");
                p5_name.setText("");
                p6_name.setText("");
                p2_bid.setDisable(true);
                p2_check.setDisable(true);
                p3_bid.setDisable(true);
                p3_check.setDisable(true);
                p4_bid.setDisable(true);
                p4_check.setDisable(true);
                p5_bid.setDisable(true);
                p5_check.setDisable(true);
                p6_bid.setDisable(true);
                p6_check.setDisable(true);
                bids.add(p1_bid);
                checks.add(p1_check);
                p1_name.setText(playersWhoCanBid.get(0).getName());
                break;
            case 2:
                //disabled 3-6
                p3_name.setText("");
                p4_name.setText("");
                p5_name.setText("");
                p6_name.setText("");
                p3_bid.setDisable(true);
                p3_check.setDisable(true);
                p4_bid.setDisable(true);
                p4_check.setDisable(true);
                p5_bid.setDisable(true);
                p5_check.setDisable(true);
                p6_bid.setDisable(true);
                p6_check.setDisable(true);
                bids.add(p1_bid);
                bids.add(p2_bid);
                checks.add(p1_check);
                checks.add(p2_check);
                p1_name.setText(playersWhoCanBid.get(0).getName());
                p2_name.setText(playersWhoCanBid.get(1).getName());
                break;
            case 3:
                //disable 4-6
                p4_name.setText("");
                p5_name.setText("");
                p6_name.setText("");
                p4_bid.setDisable(true);
                p4_check.setDisable(true);
                p5_bid.setDisable(true);
                p5_check.setDisable(true);
                p6_bid.setDisable(true);
                p6_check.setDisable(true);
                bids.add(p1_bid);
                bids.add(p2_bid);
                bids.add(p3_bid);
                checks.add(p1_check);
                checks.add(p2_check);
                checks.add(p3_check);
                p1_name.setText(playersWhoCanBid.get(0).getName());
                p2_name.setText(playersWhoCanBid.get(1).getName());
                p3_name.setText(playersWhoCanBid.get(2).getName());
                break;
            case 4:
                //disable 5-6
                p5_name.setText("");
                p6_name.setText("");
                p5_bid.setDisable(true);
                p5_check.setDisable(true);
                p6_bid.setDisable(true);
                p6_check.setDisable(true);
                bids.add(p1_bid);
                bids.add(p2_bid);
                bids.add(p3_bid);
                bids.add(p4_bid);
                checks.add(p1_check);
                checks.add(p2_check);
                checks.add(p3_check);
                checks.add(p4_check);
                p1_name.setText(playersWhoCanBid.get(0).getName());
                p2_name.setText(playersWhoCanBid.get(1).getName());
                p3_name.setText(playersWhoCanBid.get(2).getName());
                p4_name.setText(playersWhoCanBid.get(3).getName());
                break;
            case 5:
                //disable 6
                p6_name.setText("");
                p6_bid.setDisable(true);
                p6_check.setDisable(true);
                bids.add(p1_bid);
                bids.add(p2_bid);
                bids.add(p3_bid);
                bids.add(p4_bid);
                bids.add(p5_bid);
                checks.add(p1_check);
                checks.add(p2_check);
                checks.add(p3_check);
                checks.add(p4_check);
                checks.add(p5_check);
                p1_name.setText(playersWhoCanBid.get(0).getName());
                p2_name.setText(playersWhoCanBid.get(1).getName());
                p3_name.setText(playersWhoCanBid.get(2).getName());
                p4_name.setText(playersWhoCanBid.get(3).getName());
                p5_name.setText(playersWhoCanBid.get(4).getName());
                break;
            case 6:
                bids.add(p1_bid);
                bids.add(p2_bid);
                bids.add(p3_bid);
                bids.add(p4_bid);
                bids.add(p5_bid);
                bids.add(p6_bid);
                checks.add(p1_check);
                checks.add(p2_check);
                checks.add(p2_check);
                checks.add(p3_check);
                checks.add(p4_check);
                checks.add(p5_check);
                p1_name.setText(playersWhoCanBid.get(0).getName());
                p2_name.setText(playersWhoCanBid.get(1).getName());
                p3_name.setText(playersWhoCanBid.get(2).getName());
                p4_name.setText(playersWhoCanBid.get(3).getName());
                p5_name.setText(playersWhoCanBid.get(4).getName());
                p6_name.setText(playersWhoCanBid.get(5).getName());
                break;
        }
        confirm.setOnAction(e -> {
            if (numbersOnly() && checkedBoxes()) {
                HashMap<Player, Integer> hm = new HashMap<>();
                /*
                if (!p1_bid.getText().isEmpty()) {
                    hm.put(SM.getGame().getAmountOfPlayers().get(0), Integer.parseInt(p1_bid.getText()));
                }
                
                if (!p2_bid.getText().isEmpty()) {
                    hm.put(SM.getGame().getAmountOfPlayers().get(1), Integer.parseInt(p2_bid.getText()));
                }
                
                if (!p3_bid.getText().isEmpty()) {
                    hm.put(SM.getGame().getAmountOfPlayers().get(2), Integer.parseInt(p3_bid.getText()));
                }
                if (!p4_bid.getText().isEmpty()) {
                    hm.put(SM.getGame().getAmountOfPlayers().get(3), Integer.parseInt(p4_bid.getText()));
                }
                if (!p5_bid.getText().isEmpty()) {
                    hm.put(SM.getGame().getAmountOfPlayers().get(4), Integer.parseInt(p5_bid.getText()));
                }
                if (!p6_bid.getText().isEmpty()) {
                    hm.put(SM.getGame().getAmountOfPlayers().get(5), Integer.parseInt(p6_bid.getText()));
                }
                */
                for (int i = 0; i < bids.size(); i++) {
                    if(!bids.get(i).getText().isEmpty()) {
                        hm.put(SM.getGame().getAmountOfPlayers().get(i), Integer.parseInt(bids.get(i).getText()));
                    }
                }
                if (SM.getGame().checkValidAuction(hm)) {
                    try {
                        SM.getGame().auction(hm);
                    } catch (NotAProperty exp) {
                        System.err.print(exp);
                    }
                    SM.changeScene(View.GAME);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Player bids");
                    alert.setHeaderText("Two or more players have the same maximum bid.");
                    alert.setContentText("Please bid again.");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Player bids");
                alert.setHeaderText("Bids must be valid numbers and confirm must be checked.");
                alert.showAndWait();
            }
        });
    }

    public boolean numbersOnly() {
        boolean num = true;
        for (TextField tf : bids) {
            if (!tf.getText().matches("[0-9]*")) {
                num = false;
                break;
            }
        }

        return num;
    }

    public boolean checkedBoxes() {
        boolean checked = true;
        for (CheckBox cb : checks) {
            if (!cb.isSelected()) {
                checked = false;
                break;
            }
        }
        return checked;
    }

}
