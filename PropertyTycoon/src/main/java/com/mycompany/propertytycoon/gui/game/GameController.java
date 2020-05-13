/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.FreeParkingPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.boardpieces.StationProperty;
import com.mycompany.propertytycoon.boardpieces.TaxPiece;
import com.mycompany.propertytycoon.boardpieces.UtilityProperty;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import com.mycompany.propertytycoon.log.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author ankeet
 */
public class GameController implements Initializable {

    @FXML
    private Button roll, gg, sell, houses, trade, mortgage, endTurn, buy_yes, buy_no, jail;
    @FXML
        private Label playerName, playerMoney, leftTitle, leftOwner, leftHouses, leftRent;
    @FXML
    private ImageView profileToken, catToken, bootToken, spoonToken, gobletToken, hatstandToken, phoneToken, leftPic;

    @FXML
    private ScrollPane log, ownedCards;
    @FXML
    private GridPane gPane;
    @FXML
    private BorderPane bPane;
    @FXML
    private VBox right, middle_gray;
    @FXML
    private AnchorPane anchorpane_left, anchorpane_right;

    private final Log logObject = Log.getInstance();

    private final StageManager SM = StageManager.getInstance();

    private final GameVariableStorage GVS = GameVariableStorage.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        anchorpane_left.setVisible(false);
        anchorpane_right.setVisible(false);
        middle_gray.setVisible(false);
        bPane.toBack();

        log();

        if (GVS.getRoll()) {
            if (!Objects.equals(SM.getGame().getRolls().getKey(), SM.getGame().getRolls().getValue())) {
                roll.setDisable(true);
                endTurn.setDisable(false);
            } else {
                roll.setDisable(false);
                endTurn.setDisable(true);
            }
        } else {
            roll.setDisable(false);
            endTurn.setDisable(true);
        }

        try {
            updateControls();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roll.setOnAction(e -> {
            SM.getGame().move();
            if (!Objects.equals(SM.getGame().getRolls().getKey(), SM.getGame().getRolls().getValue())) {
                GVS.setRolled(true);
                endTurn.setDisable(false);
                roll.setDisable(true);
            } else {
                roll.setDisable(false);
                endTurn.setDisable(true);
            }

            ArrayList<String> remaining = SM.getGame().getPlayerActions();
            GVS.setActions(remaining);
            //System.out.print(GVS.getActions() + " " + remaining + "\n");

            if (GVS.getActions().contains("BUY")) {
                bPane.toFront();
                anchorpane_right.setVisible(true);
                anchorpane_left.setVisible(true);
                middle_gray.setVisible(true);
                sell.setDisable(true);
                houses.setDisable(true);
                trade.setDisable(true);
                mortgage.setDisable(true);
                endTurn.setDisable(true);
                remaining.remove("BUY");
            }

            log();
            try {
                updateControls();
            } catch (FileNotFoundException ex) {

            }
        });
        sell.setOnAction(e -> {
            SM.changeScene(View.SELL);
            log();
        });
        houses.setOnAction(e -> {
            SM.changeScene(View.HOUSES_CHOICE);
            log();

        });
        trade.setOnAction(e -> {
            SM.changeScene(View.TRADERCHOICE);
            log();
        });
        jail.setOnAction(e -> {
            SM.changeScene(View.JAIL);
            log();
        });
        mortgage.setOnAction(e -> {
            SM.changeScene(View.MORTGAGE);
            log();
        });
        endTurn.setOnAction(e -> {
            SM.getGame().endTurn();
            GVS.setRolled(false);
            roll.setDisable(false);
            endTurn.setDisable(true);
            try {
                updateControls();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            log();
        });
        buy_yes.setOnAction(e -> {
            try {
                SM.getGame().buyProperty(SM.getGame().getBoard().getBoardPiece(SM.getGame().getActivePlayer().getLocation()));
            } catch (NotAProperty exp) {
                System.err.print(exp);
            }
            bPane.toBack();
            anchorpane_right.setVisible(false);
            anchorpane_left.setVisible(false);
            middle_gray.setVisible(false);
            sell.setDisable(false);
            houses.setDisable(false);
            trade.setDisable(false);
            mortgage.setDisable(false);
            endTurn.setDisable(false);
            log();
        });
        buy_no.setOnAction(e -> {
            ArrayList<Player> players = new ArrayList<>();
            for (Player p : SM.getGame().getAmountOfPlayers()) {
                if (p.getGameloops() > 0) {
                    players.add(p);
                }
            }
            if (players.size() > 0) {
                GVS.setAuctionProperty(SM.getGame().getBoard().getBoardPiece(SM.getGame().getActivePlayer().getLocation()));
                SM.changeScene(View.AUCTION);
            } else {
                bPane.toBack();
                anchorpane_right.setVisible(false);
                anchorpane_left.setVisible(false);
                middle_gray.setVisible(false);
                sell.setDisable(false);
                houses.setDisable(false);
                trade.setDisable(false);
                mortgage.setDisable(false);
                endTurn.setDisable(false);
                logObject.addToLog("No players can bid on property.");
            }

            log();
        });
        try {
            placePlayersOnBoard();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 40; i++) {
            try {
                LocationNames(i, SM.getGame().getBoard().getBoardLocations().get(i).getTitle());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void playerProfile() throws FileNotFoundException {
        String pic = SM.getGame().getActivePlayer().getToken() + ".gif";
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/ProfileAnimations/" + pic);
        Image image = new Image(inputstream) {
        };
        playerName.setText("Player: " + SM.getGame().getActivePlayer().getName());
        playerMoney.setText("£" + SM.getGame().getActivePlayer().getBalance());
        profileToken.setImage(image);

    }

    public void playerCards() {
        String properties = new String();

        for (Property p : SM.getGame().getActivePlayer().getOwnedProperties()) {
            properties = properties + p.getTitle() + "\n";
        }
        ownedCards.setContent(new Text(properties));
    }

    public void buttons() {
        for (String s : SM.getGame().getPlayerActions()) {

        }
    }

    public void log() {
        log.setContent(new Text(logObject.getLog()));
    }

    public void placePlayersOnBoard() throws FileNotFoundException {
        FileInputStream inputstream;
        Image image = null;
        for (Player p : SM.getGame().getAmountOfPlayers()) {

            switch (p.getToken()) {

                case "cat":
                    inputstream = new FileInputStream("./src/main/java/resources/img/cat.png");
                    image = new Image(inputstream) {
                    };
                    catToken.setImage(image);

                    break;

                case "boot":
                    inputstream = new FileInputStream("./src/main/java/resources/img/boot.png");
                    image = new Image(inputstream) {
                    };
                    bootToken.setImage(image);
                    break;

                case "spoon":
                    inputstream = new FileInputStream("./src/main/java/resources/img/SPOON.png");
                    image = new Image(inputstream) {
                    };
                    spoonToken.setImage(image);
                    break;

                case "cup":
                    inputstream = new FileInputStream("./src/main/java/resources/img/cup.png");
                    image = new Image(inputstream) {
                    };

                    gobletToken.setImage(image);
                    break;

                case "hat_hanger":
                    inputstream = new FileInputStream("./src/main/java/resources/img/hat_hanger.png");
                    image = new Image(inputstream) {
                    };

                    hatstandToken.setImage(image);
                    break;

                case "phone":
                    inputstream = new FileInputStream("./src/main/java/resources/img/phone.png");
                    image = new Image(inputstream) {
                    };
                    phoneToken.setImage(image);
                    break;

            }
        }

    }

    public void PlayerPosition(ImageView token) {
        switch (SM.getGame().getActivePlayer().getLocation()) {

            case 0:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 10);
                break;

            case 1:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 9);
                break;

            case 2:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 8);
                break;

            case 3:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 7);
                break;

            case 4:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 6);
                break;

            case 5:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 5);
                break;

            case 6:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 4);
                break;

            case 7:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 3);
                break;

            case 8:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 2);
                break;

            case 9:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 1);
                break;

            case 10:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 0);
                break;

            case 11:
                gPane.setColumnIndex(token, 1);
                gPane.setRowIndex(token, 0);
                break;
            case 12:
                gPane.setColumnIndex(token, 2);
                gPane.setRowIndex(token, 0);
                break;

            case 13:
                gPane.setColumnIndex(token, 3);
                gPane.setRowIndex(token, 0);
                break;

            case 14:
                gPane.setColumnIndex(token, 4);
                gPane.setRowIndex(token, 0);
                break;

            case 15:
                gPane.setColumnIndex(token, 5);
                gPane.setRowIndex(token, 0);
                break;

            case 16:
                gPane.setColumnIndex(token, 6);
                gPane.setRowIndex(token, 0);
                break;

            case 17:
                gPane.setColumnIndex(token, 7);
                gPane.setRowIndex(token, 0);
                break;

            case 18:
                gPane.setColumnIndex(token, 8);
                gPane.setRowIndex(token, 0);
                break;

            case 19:
                gPane.setColumnIndex(token, 9);
                gPane.setRowIndex(token, 0);
                break;

            case 20:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 0);
                break;

            case 21:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 1);
                break;

            case 22:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 2);
                break;

            case 23:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 3);
                break;

            case 24:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 4);
                break;

            case 25:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 5);
                break;

            case 26:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 6);
                break;

            case 27:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 7);
                break;

            case 28:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 8);
                break;

            case 29:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 9);
                break;

            case 30:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 10);
                break;

            case 31:
                gPane.setColumnIndex(token, 9);
                gPane.setRowIndex(token, 10);
                break;

            case 32:
                gPane.setColumnIndex(token, 8);
                gPane.setRowIndex(token, 10);
                break;

            case 33:
                gPane.setColumnIndex(token, 7);
                gPane.setRowIndex(token, 10);
                break;

            case 34:
                gPane.setColumnIndex(token, 6);
                gPane.setRowIndex(token, 10);
                break;

            case 35:
                gPane.setColumnIndex(token, 5);
                gPane.setRowIndex(token, 10);
                break;

            case 36:
                gPane.setColumnIndex(token, 4);
                gPane.setRowIndex(token, 10);
                break;

            case 37:
                gPane.setColumnIndex(token, 3);
                gPane.setRowIndex(token, 10);
                break;

            case 38:
                gPane.setColumnIndex(token, 2);
                gPane.setRowIndex(token, 10);
                break;

            case 39:
                gPane.setColumnIndex(token, 1);
                gPane.setRowIndex(token, 10);
                break;

            case 40:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 10);
                break;

        }
    }

    public ImageView getPlayerTokenImage() {
        if (SM.getGame().getActivePlayer().getToken() == "cat") {
            return catToken;
        } else if (SM.getGame().getActivePlayer().getToken() == "boot") {
            return bootToken;
        } else if (SM.getGame().getActivePlayer().getToken() == "spoon") {
            return spoonToken;
        } else if (SM.getGame().getActivePlayer().getToken() == "cup") {
            return gobletToken;
        } else if (SM.getGame().getActivePlayer().getToken() == "phone") {
            return phoneToken;
        } else {
            return hatstandToken;
        }

    }

    public void LocationNames(int num, String name) throws FileNotFoundException {
        Label labelName = new Label(name);

        switch (num) {

            case 0:
                labelName.setId("go");
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(100);
                labelName.setText(name);
                labelName.setMinHeight(100);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(0));
                gPane.add(labelName, 0, 10);

                break;

            case 1:
                labelName.setId("brown");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(60);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 9);

                break;

            case 2:
                labelName.setId("potLuck");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(30);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 8);

                break;

            case 3:
                labelName.setId("brown");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(60);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 7);

                break;

            case 4:
                labelName.setId("income");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(30);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 6);

                break;

            case 5:
                labelName.setId("train");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(55);
                labelName.setMinHeight(90);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 5);

                break;

            case 6:
                labelName.setId("blue");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(60);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 4);

                break;

            case 7:
                labelName.setId("knock");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(50);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 3);

                break;

            case 8:
                labelName.setId("blue");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(60);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 2);

                break;

            case 9:
                labelName.setId("blue");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(60);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 1);

                break;

            case 10:
                labelName.setId("prison");
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(100);
                labelName.setMinHeight(100);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(0));
                gPane.add(labelName, 0, 0);

                break;

            case 11:
                labelName.setId("purple");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 1, 0);

                break;

            case 12:
                labelName.setId("purple");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 2, 0);

                break;

            case 13:
                labelName.setId("thunder");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 70);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 3, 0);
                labelName.setRotate(180);

                break;

            case 14:
                labelName.setId("purple");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 4, 0);

                break;

            case 15:
                labelName.setId("train");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(60, 80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 5, 0);

                break;

            case 16:
                labelName.setId("orange");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 6, 0);

                break;

            case 17:
                labelName.setId("potLuck");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(39, 43);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 7, 0);

                break;

            case 18:
                labelName.setId("orange");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 8, 0);

                break;

            case 19:
                labelName.setId("orange");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 9, 0);

                break;

            case 20:
                labelName.setId("free");
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(100, 100);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(0));
                gPane.add(labelName, 10, 0);

                break;

            case 21:
                labelName.setId("red");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 1);

                break;

            case 22:
                labelName.setId("knock");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(130);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 2);

                break;

            case 23:
                labelName.setId("red");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 3);

                break;

            case 24:
                labelName.setId("red");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 4);

                break;

            case 25:
                labelName.setId("train");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(55);
                labelName.setMinHeight(90);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 5);

                break;

            case 26:
                labelName.setId("yellow");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(50);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 6);

                break;

            case 27:
                labelName.setId("yellow");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 7);

                break;

            case 28:
                labelName.setId("cup");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(120);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 8);

                break;

            case 29:
                labelName.setId("yellow");
                labelName.setRotate(-90);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 9);

                break;

            case 30:
                labelName.setId("jail");
                labelName.setAlignment(Pos.BOTTOM_RIGHT);
                labelName.setWrapText(true);
                labelName.setMinSize(88, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 10);

                break;

            case 31:
                labelName.setId("green");
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 9, 10);

                break;

            case 32:
                labelName.setId("green");
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 8, 10);

                break;

            case 33:
                labelName.setId("potLuck");
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(39, 43);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 7, 10);

                break;

            case 34:
                labelName.setId("green");
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 6, 10);

                break;

            case 35:
                labelName.setId("train");
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(60, 80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 5, 10);

                break;

            case 36:
                labelName.setId("knock");
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(40, 85);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 4, 10);

                break;

            case 37:
                labelName.setId("darkblue");
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 3, 10);

                break;

            case 38:
                labelName.setId("tax");
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 44);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 2, 10);

                break;

            case 39:
                labelName.setId("darkblue");
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 1, 10);

                break;

        }

       labelName.addEventFilter(MouseEvent.MOUSE_PRESSED, event
                -> {
            try {
                leftSide(labelName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            leftOwner.setText("No Owner");
            for (Player p : SM.getGame().getAmountOfPlayers()) {
                if (!p.getOwnedProperties().isEmpty()) {
                    for (Property py : p.getOwnedProperties()) {
                        if (py.getTitle().equals(labelName.getText())) {
                            leftOwner.setText("Owner: " + p.getName());

                        }
                    }
                }
            }
            leftRent.setText("No Rent");
            leftHouses.setText("No Houses");
            for (BoardPiece bp : SM.getGame().getBoard().getBoardLocations()) {
                if (bp instanceof ColouredProperty) {
                    if (labelName.getText().equals(bp.getTitle())) {
                        leftRent.setText("Rent: £" + ((ColouredProperty) bp).getRent());
                        leftHouses.setText("Number of Houses: " +((ColouredProperty) bp).getHouseCount() + " Cost: £" + ((ColouredProperty) bp).getCost());
                    }
                } else if (bp instanceof FreeParkingPiece) {
                    if (labelName.getText().equals(bp.getTitle())) {
                        leftRent.setText("Balance: £" + ((FreeParkingPiece) bp).getBalance());
                    }
                } else if (bp instanceof StationProperty) {
                    if (labelName.getText().equals(bp.getTitle())) {
                        leftRent.setText("Rent: £" + ((StationProperty) bp).getRent());
                    }
                } else if (bp instanceof TaxPiece) {
                    if (labelName.getText().equals(bp.getTitle())) {
                        leftRent.setText("Tax: £" + ((TaxPiece) bp).getTaxAmount());
                    }
                } else if (bp instanceof UtilityProperty) {
                    if (labelName.getText().equals(bp.getTitle())) {
                        leftRent.setText("Rent: £" + ((UtilityProperty) bp).getRent());
                    }
                }
            }
            

        });
    }

    public void updateControls() throws FileNotFoundException {
        playerProfile();
        PlayerPosition(getPlayerTokenImage());
        playerCards();

    }

    public void leftSide(Label n) throws FileNotFoundException {
        leftTitle.setText(n.getText());
        FileInputStream inputstream = null;
        Image Image = null;
        switch (n.getId()) {
            case "red":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/red.png");
                Image = new Image(inputstream) {
                };
                break;
            case "blue":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/blue.png");
                Image = new Image(inputstream) {
                };
                break;
            case "brown":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/brown.png");
                Image = new Image(inputstream) {
                };
                break;

            case "darkblue":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/darkblue.png");
                Image = new Image(inputstream) {
                };
                break;

            case "purple":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/purple.png");
                Image = new Image(inputstream) {
                };
                break;

            case "yellow":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/yellow.png");
                Image = new Image(inputstream) {
                };
                break;

            case "orange":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/orange.png");
                Image = new Image(inputstream) {
                };
                break;

            case "jail":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/jail.png");
                Image = new Image(inputstream) {
                };
                break;

            case "cup":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/cup.png");
                Image = new Image(inputstream) {
                };
                break;

            case "free":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/free.png");
                Image = new Image(inputstream) {
                };
                break;

            case "green":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/green.png");
                Image = new Image(inputstream) {
                };
                break;

            case "go":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/go.png");
                Image = new Image(inputstream) {
                };
                break;
            case "income":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/income.png");
                Image = new Image(inputstream) {
                };
                break;

            case "train":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/train.png");
                Image = new Image(inputstream) {
                };
                break;

            case "knock":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/knock.png");
                Image = new Image(inputstream) {
                };
                break;

            case "prison":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/prison.png");
                Image = new Image(inputstream) {
                };
                break;

            case "thunder":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/thunder.png");
                Image = new Image(inputstream) {
                };
                break;

            case "tax":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/tax.png");
                Image = new Image(inputstream) {
                };
                break;

            case "potLuck":
                inputstream = new FileInputStream("./src/main/java/resources/img/boardPieces/potLuck.png");
                Image = new Image(inputstream) {
                };
                break;
        }
        leftPic.setImage(Image);
    }

}
