/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    private ArrayList<TextField> bids = new ArrayList<>();
    private ArrayList<CheckBox> checks = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Property p = SM.getAuctionProperty();
        property_name.setText("Name: " + p.getTitle());
        property_colour.setText("Group: " + p.getGroup());
        property_original_price.setText("Original Price: " + String.valueOf(p.getCost()));

        int players = SM.getGame().getAmountOfPlayers().size();
        switch (players) {
            case 2:
                //disabled 3-6
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
                break;
            case 3:
                //disable 4-6
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
                break;
            case 4:
                //disable 5-6
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
                break;
            case 5:
                //disable 6
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
                break;
        }

        p1_name.setText(SM.getGame().getAmountOfPlayers().get(0).getName());
        p2_name.setText(SM.getGame().getAmountOfPlayers().get(1).getName());
        p3_name.setText(SM.getGame().getAmountOfPlayers().get(2).getName());
        p4_name.setText(SM.getGame().getAmountOfPlayers().get(3).getName());
        p5_name.setText(SM.getGame().getAmountOfPlayers().get(4).getName());
        p6_name.setText(SM.getGame().getAmountOfPlayers().get(5).getName());
        confirm.setOnAction(e -> {
            if (numbersOnly() && checkedBoxes()) {
                //SM.getGame().
            }
        });

    }

    public boolean numbersOnly() {
        boolean num = true;
        for (TextField tf : bids) {
            if(!tf.getText().matches("[0-9]*")) {
                num = false;
                break;
            } 
        }
        
        return num;
    }

    public boolean checkedBoxes() {
        boolean checked = true;
        for (CheckBox cb : checks) {
            if(!cb.isSelected()) {
                checked = false;
                break;
            }
        }
        return checked;
    }

}
