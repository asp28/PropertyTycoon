/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.initialisegame;

import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.Tokens;
import com.mycompany.propertytycoon.gui.utils.View;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class InitController implements Initializable {

    @FXML
    private Button confirm, back;

    @FXML
    private ListView<String> p1_list, p2_list, p3_list, p4_list, p5_list, p6_list;

    @FXML
    private TextField p1_name, p2_name, p3_name, p4_name, p5_name, p6_name;

    private StageManager SM = StageManager.getInstance();
    private ObservableList<String> tokens = FXCollections.<String>observableArrayList("cat", "boot", "cup", "hat_hanger", "phone", "spoon");
    private ArrayList<TextField> nameCheck = new ArrayList<>();
    private ArrayList<ListView<String>> tokenCheck = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1_list.setCellFactory(listview -> new ListCell<String>() {
            private ImageView image = new ImageView();

            @Override
            public void updateItem(String a, boolean b) {
                super.updateItem(a, b);
                if (b) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FileInputStream ab = null;
                    try {
                        switch (a) {
                            case "cat":
                                ab = new FileInputStream(Tokens.CAT.getPath());
                                break;
                            case "boot":
                                ab = new FileInputStream(Tokens.BOOT.getPath());
                                break;
                            case "cup":
                                ab = new FileInputStream(Tokens.CUP.getPath());
                                break;
                            case "hat_hanger":
                                ab = new FileInputStream(Tokens.HAT_HANGER.getPath());
                                break;
                            case "phone":
                                ab = new FileInputStream(Tokens.PHONE.getPath());
                                break;
                            case "spoon":
                                ab = new FileInputStream(Tokens.SPOON.getPath());
                                break;
                        }
                    } catch (FileNotFoundException e) {

                    }
                    Image imag = new Image(ab);
                    image.setImage(imag);
                    image.setFitHeight(50);
                    image.setFitWidth(50);
                    setText(null);
                    setGraphic(image);

                }
            }

        });
        p2_list.setCellFactory(listview -> new ListCell<String>() {
            private ImageView image = new ImageView();

            @Override
            public void updateItem(String a, boolean b) {
                super.updateItem(a, b);
                if (b) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FileInputStream ab = null;
                    try {
                        switch (a) {
                            case "cat":
                                ab = new FileInputStream(Tokens.CAT.getPath());
                                break;
                            case "boot":
                                ab = new FileInputStream(Tokens.BOOT.getPath());
                                break;
                            case "cup":
                                ab = new FileInputStream(Tokens.CUP.getPath());
                                break;
                            case "hat_hanger":
                                ab = new FileInputStream(Tokens.HAT_HANGER.getPath());
                                break;
                            case "phone":
                                ab = new FileInputStream(Tokens.PHONE.getPath());
                                break;
                            case "spoon":
                                ab = new FileInputStream(Tokens.SPOON.getPath());
                                break;
                        }
                    } catch (FileNotFoundException e) {

                    }
                    Image imag = new Image(ab);
                    image.setImage(imag);
                    image.setFitHeight(50);
                    image.setFitWidth(50);
                    setText(null);
                    setGraphic(image);

                }
            }

        });
        p3_list.setCellFactory(listview -> new ListCell<String>() {
            private ImageView image = new ImageView();

            @Override
            public void updateItem(String a, boolean b) {
                super.updateItem(a, b);
                if (b) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FileInputStream ab = null;
                    try {
                        switch (a) {
                            case "cat":
                                ab = new FileInputStream(Tokens.CAT.getPath());
                                break;
                            case "boot":
                                ab = new FileInputStream(Tokens.BOOT.getPath());
                                break;
                            case "cup":
                                ab = new FileInputStream(Tokens.CUP.getPath());
                                break;
                            case "hat_hanger":
                                ab = new FileInputStream(Tokens.HAT_HANGER.getPath());
                                break;
                            case "phone":
                                ab = new FileInputStream(Tokens.PHONE.getPath());
                                break;
                            case "spoon":
                                ab = new FileInputStream(Tokens.SPOON.getPath());
                                break;
                        }
                    } catch (FileNotFoundException e) {

                    }
                    Image imag = new Image(ab);
                    image.setImage(imag);
                    image.setFitHeight(50);
                    image.setFitWidth(50);
                    setText(null);
                    setGraphic(image);

                }
            }

        });
        p4_list.setCellFactory(listview -> new ListCell<String>() {
            private ImageView image = new ImageView();

            @Override
            public void updateItem(String a, boolean b) {
                super.updateItem(a, b);
                if (b) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FileInputStream ab = null;
                    try {
                        switch (a) {
                            case "cat":
                                ab = new FileInputStream(Tokens.CAT.getPath());
                                break;
                            case "boot":
                                ab = new FileInputStream(Tokens.BOOT.getPath());
                                break;
                            case "cup":
                                ab = new FileInputStream(Tokens.CUP.getPath());
                                break;
                            case "hat_hanger":
                                ab = new FileInputStream(Tokens.HAT_HANGER.getPath());
                                break;
                            case "phone":
                                ab = new FileInputStream(Tokens.PHONE.getPath());
                                break;
                            case "spoon":
                                ab = new FileInputStream(Tokens.SPOON.getPath());
                                break;
                        }
                    } catch (FileNotFoundException e) {

                    }
                    Image imag = new Image(ab);
                    image.setImage(imag);
                    image.setFitHeight(50);
                    image.setFitWidth(50);
                    setText(null);
                    setGraphic(image);

                }
            }

        });
        p5_list.setCellFactory(listview -> new ListCell<String>() {
            private ImageView image = new ImageView();

            @Override
            public void updateItem(String a, boolean b) {
                super.updateItem(a, b);
                if (b) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FileInputStream ab = null;
                    try {
                        switch (a) {
                            case "cat":
                                ab = new FileInputStream(Tokens.CAT.getPath());
                                break;
                            case "boot":
                                ab = new FileInputStream(Tokens.BOOT.getPath());
                                break;
                            case "cup":
                                ab = new FileInputStream(Tokens.CUP.getPath());
                                break;
                            case "hat_hanger":
                                ab = new FileInputStream(Tokens.HAT_HANGER.getPath());
                                break;
                            case "phone":
                                ab = new FileInputStream(Tokens.PHONE.getPath());
                                break;
                            case "spoon":
                                ab = new FileInputStream(Tokens.SPOON.getPath());
                                break;
                        }
                    } catch (FileNotFoundException e) {

                    }
                    Image imag = new Image(ab);
                    image.setImage(imag);
                    image.setFitHeight(50);
                    image.setFitWidth(50);
                    setText(null);
                    setGraphic(image);

                }
            }

        });
        p6_list.setCellFactory(listview -> new ListCell<String>() {
            private ImageView image = new ImageView();

            @Override
            public void updateItem(String a, boolean b) {
                super.updateItem(a, b);
                if (b) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FileInputStream ab = null;
                    try {
                        switch (a) {
                            case "cat":
                                ab = new FileInputStream(Tokens.CAT.getPath());
                                break;
                            case "boot":
                                ab = new FileInputStream(Tokens.BOOT.getPath());
                                break;
                            case "cup":
                                ab = new FileInputStream(Tokens.CUP.getPath());
                                break;
                            case "hat_hanger":
                                ab = new FileInputStream(Tokens.HAT_HANGER.getPath());
                                break;
                            case "phone":
                                ab = new FileInputStream(Tokens.PHONE.getPath());
                                break;
                            case "spoon":
                                ab = new FileInputStream(Tokens.SPOON.getPath());
                                break;
                        }
                    } catch (FileNotFoundException e) {

                    }
                    Image imag = new Image(ab);
                    image.setImage(imag);
                    image.setFitHeight(50);
                    image.setFitWidth(50);
                    setText(null);
                    setGraphic(image);

                }
            }

        });

        switch (SM.getPlayerNum()) {
            case 2:
                p1_list.getItems().addAll(tokens);
                p2_list.getItems().addAll(tokens);
                p3_list.setDisable(true);
                p3_name.setDisable(true);
                p4_list.setDisable(true);
                p4_name.setDisable(true);
                p5_list.setDisable(true);
                p5_name.setDisable(true);
                p6_list.setDisable(true);
                p6_name.setDisable(true);
                nameCheck.add(p1_name);
                nameCheck.add(p2_name);
                tokenCheck.add(p1_list);
                tokenCheck.add(p2_list);
                break;
            case 3:
                p1_list.getItems().addAll(tokens);
                p2_list.getItems().addAll(tokens);
                p3_list.getItems().addAll(tokens);
                p4_list.setDisable(true);
                p4_name.setDisable(true);
                p5_list.setDisable(true);
                p5_name.setDisable(true);
                p6_list.setDisable(true);
                p6_name.setDisable(true);
                nameCheck.add(p1_name);
                nameCheck.add(p2_name);
                nameCheck.add(p3_name);
                tokenCheck.add(p1_list);
                tokenCheck.add(p2_list);
                tokenCheck.add(p3_list);
                break;
            case 4:
                p1_list.getItems().addAll(tokens);
                p2_list.getItems().addAll(tokens);
                p3_list.getItems().addAll(tokens);
                p4_list.getItems().addAll(tokens);
                p5_list.setDisable(true);
                p5_name.setDisable(true);
                p6_list.setDisable(true);
                p6_name.setDisable(true);
                nameCheck.add(p1_name);
                nameCheck.add(p2_name);
                nameCheck.add(p3_name);
                nameCheck.add(p4_name);
                tokenCheck.add(p1_list);
                tokenCheck.add(p2_list);
                tokenCheck.add(p3_list);
                tokenCheck.add(p4_list);
                break;
            case 5:
                p1_list.getItems().addAll(tokens);
                p2_list.getItems().addAll(tokens);
                p3_list.getItems().addAll(tokens);
                p4_list.getItems().addAll(tokens);
                p5_list.getItems().addAll(tokens);
                p6_list.setDisable(true);
                p6_name.setDisable(true);
                nameCheck.add(p1_name);
                nameCheck.add(p2_name);
                nameCheck.add(p3_name);
                nameCheck.add(p5_name);
                tokenCheck.add(p1_list);
                tokenCheck.add(p2_list);
                tokenCheck.add(p3_list);
                tokenCheck.add(p4_list);
                tokenCheck.add(p5_list);
                break;
            case 6:
                p1_list.getItems().addAll(tokens);
                p2_list.getItems().addAll(tokens);
                p3_list.getItems().addAll(tokens);
                p4_list.getItems().addAll(tokens);
                p5_list.getItems().addAll(tokens);
                p6_list.getItems().addAll(tokens);
                nameCheck.add(p1_name);
                nameCheck.add(p2_name);
                nameCheck.add(p3_name);
                nameCheck.add(p5_name);
                nameCheck.add(p6_name);
                tokenCheck.add(p1_list);
                tokenCheck.add(p2_list);
                tokenCheck.add(p3_list);
                tokenCheck.add(p4_list);
                tokenCheck.add(p5_list);
                tokenCheck.add(p6_list);
                break;
        }

        confirm.setOnAction(e -> {
            if (checkNames() && uniqueToken()) {
                SM.createGameController(SM.getPlayerNum());
                switch (SM.getPlayerNum()) {
                    case 2:
                        SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                        SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                        SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                        break;
                    case 3:
                        SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                        SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                        SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(2).setName(p3_name.getText());
                        SM.getGame().getAmountOfPlayers().get(2).setToken(p3_list.getSelectionModel().getSelectedItem());
                        break;
                    case 4:
                        SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                        SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                        SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(2).setName(p3_name.getText());
                        SM.getGame().getAmountOfPlayers().get(2).setToken(p3_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(3).setName(p4_name.getText());
                        SM.getGame().getAmountOfPlayers().get(3).setToken(p4_list.getSelectionModel().getSelectedItem());
                        break;
                    case 5:
                        SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                        SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                        SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(2).setName(p3_name.getText());
                        SM.getGame().getAmountOfPlayers().get(2).setToken(p3_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(3).setName(p4_name.getText());
                        SM.getGame().getAmountOfPlayers().get(3).setToken(p4_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(4).setName(p5_name.getText());
                        SM.getGame().getAmountOfPlayers().get(4).setToken(p5_list.getSelectionModel().getSelectedItem());
                        break;
                    case 6:
                        SM.getGame().getAmountOfPlayers().get(0).setName(p1_name.getText());
                        SM.getGame().getAmountOfPlayers().get(0).setToken(p1_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(1).setName(p2_name.getText());
                        SM.getGame().getAmountOfPlayers().get(1).setToken(p2_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(2).setName(p3_name.getText());
                        SM.getGame().getAmountOfPlayers().get(2).setToken(p3_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(3).setName(p4_name.getText());
                        SM.getGame().getAmountOfPlayers().get(3).setToken(p4_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(4).setName(p5_name.getText());
                        SM.getGame().getAmountOfPlayers().get(4).setToken(p5_list.getSelectionModel().getSelectedItem());
                        SM.getGame().getAmountOfPlayers().get(5).setName(p6_name.getText());
                        SM.getGame().getAmountOfPlayers().get(5).setToken(p6_list.getSelectionModel().getSelectedItem());
                        break;
                }
                SM.changeScene(View.GAME);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid inputs");
                alert.setHeaderText("Fields not entered correctly");
                alert.setContentText("Name fields must be filled in and each player must have a unique token");
                alert.showAndWait();
            }

        });
        back.setOnAction(e -> SM.changeScene(View.PLAYER_BOT_COUNT));

    }

    public boolean uniqueToken() {
        boolean unique = true;
        ArrayList<String> strings = new ArrayList<>();
        for (ListView<String> lv : tokenCheck) {
            strings.add(lv.getSelectionModel().getSelectedItem());
        }

        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size(); j++) {
                if (i != j && strings.get(i).equalsIgnoreCase(strings.get(j))) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    public boolean checkNames() {
        boolean notEmpty = true;

        for (TextField tf : nameCheck) {
            if (tf.getText().isEmpty()) {
                notEmpty = false;
            }
        }

        return notEmpty;
    }
}
