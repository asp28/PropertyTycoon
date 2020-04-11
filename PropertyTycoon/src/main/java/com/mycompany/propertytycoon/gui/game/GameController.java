/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.game;

import com.mycompany.propertytycoon.Player;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import com.mycompany.propertytycoon.gui.utils.StageManager;
import com.mycompany.propertytycoon.gui.utils.View;
import com.mycompany.propertytycoon.log.Log;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private Button roll, buy, sell, houses, trade, mortgage, endTurn, buy_yes, buy_no;
    @FXML
    private Label playerName, playerMoney;
    @FXML
    private ImageView profileToken, catToken, bootToken, spoonToken, gobletToken, hatstandToken, phoneToken;

    @FXML
    private ScrollPane log, ownedCards;
    @FXML
    private GridPane gPane;
    @FXML
    private BorderPane bPane;
    @FXML
    private VBox right;
    @FXML
    private AnchorPane anchorpane;

    private Log logObject = Log.getInstance();

    private StageManager SM = StageManager.getInstance();
    private int i = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorpane.setVisible(false);
        try {
            updateControls();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roll.setOnAction(e -> {
            SM.getGame().move();
            log();
            if (SM.getGame().getBoard().getBoardPiece(SM.getGame().getActivePlayer().getLocation()) instanceof Property) {
                anchorpane.setVisible(true);
            }
            try {
                updateControls();
            } catch (FileNotFoundException ex) {

            }
        });
        buy.setOnAction(e -> {
            try {
                SM.getGame().buyProperty(SM.getGame().getBoard().getBoardPiece(SM.getGame().getActivePlayer().getLocation()));
            } catch (NotAProperty exp) {
                System.err.print(exp);
            }
            log();
        });
        sell.setOnAction(e -> {
            SM.changeScene(View.SELL);
        });
        houses.setOnAction(e -> {
            SM.changeScene(View.HOUSES);

        });
        trade.setOnAction(e -> {
            SM.changeScene(View.TRADERCHOICE);
        });
        mortgage.setOnAction(e -> {
            SM.changeScene(View.MORTGAGE);
        });
        endTurn.setOnAction(e -> {
            SM.getGame().endTurn();
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
            anchorpane.setVisible(false);
        });
        buy_no.setOnAction(e -> {
            SM.setAuctionProperty((Property) SM.getGame().getBoard().getBoardPiece(SM.getGame().getActivePlayer().getLocation()));
            SM.changeScene(View.AUCTION);
        });
        try {
            placePlayersOnBoard();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 1; i < 40; i++) {
            LocationNames(i, SM.getGame().getBoard().getBoardLocations().get(i).getTitle());
        }

    }

    public void playerProfile() throws FileNotFoundException {
        String pic = SM.getGame().getActivePlayer().getToken() + ".png";
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/" + pic);
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
    }

    public void log() {
        log.setContent(new Text(logObject.getLog()));
    }

    public void placePlayersOnBoard() throws FileNotFoundException {
        FileInputStream inputstream;
        Image image;
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
        System.out.print(SM.getGame().getActivePlayer().getLocation());
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

    public void LocationNames(int num, String name) {
        Label labelName = new Label(name);
        switch (num) {

            case 1:
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
                labelName.setRotate(90);
                labelName.setAlignment(Pos.TOP_CENTER);
                labelName.setWrapText(true);
                labelName.setPrefWidth(44);
                labelName.setMinHeight(60);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 0, 1);

                break;

            case 11:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 1, 0);

                break;

            case 12:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 2, 0);

                break;

            case 13:
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
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 4, 0);

                break;

            case 15:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(60, 80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 5, 0);

                break;

            case 16:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 6, 0);

                break;

            case 17:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(39, 43);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 7, 0);
                break;

            case 18:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 8, 0);

                break;

            case 19:
                labelName.setRotate(180);
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 9, 0);

                break;

            case 21:

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
                labelName.setAlignment(Pos.BOTTOM_RIGHT);
                labelName.setWrapText(true);
                labelName.setMinSize(88, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 10, 10);
                break;

            case 31:
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 9, 10);
                break;

            case 32:
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 8, 10);
                break;

            case 33:
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(39, 43);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 7, 10);
                break;

            case 34:
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 6, 10);
                break;

            case 35:
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(60, 80);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 5, 10);
                break;

            case 36:
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(40, 85);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 4, 10);
                break;

            case 37:
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 3, 10);
                break;

            case 38:
                labelName.setAlignment(Pos.BOTTOM_CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 44);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 2, 10);
                break;

            case 39:
                labelName.setAlignment(Pos.CENTER);
                labelName.setWrapText(true);
                labelName.setMinSize(44, 87);
                labelName.setTextAlignment(TextAlignment.CENTER);
                labelName.setFont(new Font(9.0));
                gPane.add(labelName, 1, 10);
                break;

        }
    }

    public void updateControls() throws FileNotFoundException {
        playerProfile();
        PlayerPosition(getPlayerTokenImage());
        playerCards();

    }

//   @FXML
//    private void mouseEntered(MouseEvent e) {
//        Node source = (Node)e.getSource() ;
//        Integer colIndex = gPane.getColumnIndex(source);
//        Integer rowIndex = gPane.getRowIndex(source);
//        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
//    }
}
