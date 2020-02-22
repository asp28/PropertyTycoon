/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui;

import com.mycompany.propertytycoon.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author BigNerdNotation
 */
public class PropertyTycoon extends Application {

    private GameLoop gl;
    private ScrollPane sp;
    private Text logTextBox;
    private Label activePlayer, activePlayerMoney;
    private GridPane gPane;
    private ImageView profileToken, catToken, bootToken, spoonToken, gobletToken, hatstandToken, phoneToken;

    @Override
    public void start(Stage primaryStage) throws IOException, InvalidFormatException {
        gl = createGame(2);
        BorderPane bPane = new BorderPane();
        VBox all = new VBox();
        HBox hBox = new HBox();
        gPane = new GridPane();
        StackPane sPane = new StackPane();
        hBox.getChildren().addAll(playerProfilePic(), all);
        all.getChildren().add(Players());
        all.getChildren().addAll(PlayerCards(), buttons(), log());
        all.setSpacing(10);
        all.setPadding(new Insets(10, 10, 10, 10));
        bPane.setRight(hBox);
        //(amount and tokens of players)
        gPane.setGridLinesVisible(true);
        gPane.getChildren().addAll(playersTokenOnBoard());
        ColumnConstraints column;
        for (int i = 0; i < 11; i++) {
            if (i == 0 || i == 10) {
                column = new ColumnConstraints(130);

            } else if (i == 5) {
                column = new ColumnConstraints(110);
            } else {
                column = new ColumnConstraints(80);
            }
            gPane.getColumnConstraints().add(column);

        }

        RowConstraints row;
        for (int i = 0; i < 11; i++) {

            if (i == 0 || i == 10) {
                row = new RowConstraints(130);

            } else {
                row = new RowConstraints(80);
            }

            gPane.getRowConstraints().add(row);
        }

        gPane.setAlignment(Pos.CENTER);
        sPane.getChildren().addAll(board(), gPane);
        bPane.setCenter(sPane);
        Scene scene = new Scene(bPane, 1500, 1000);
        primaryStage.setTitle("Property Tycoon");
        primaryStage.setScene(scene);
        primaryStage.maximizedProperty();
        primaryStage.show();

    }

    /**
     *
     * @param num
     * @return new game
     * @throws IOException
     * @throws InvalidFormatException
     */
    public GameLoop createGame(int num) throws IOException, InvalidFormatException {
        gl = new GameLoop(num);
        return gl;
    }

    /**
     * create players menu (name and balance)
     *
     * @return
     */
    public VBox Players() {
        VBox hb = new VBox();
        activePlayer = new Label("Player : " + gl.getActivePlayer().getCharacter());
        activePlayerMoney = new Label("£" + gl.getActivePlayer().getPlayerBalance());
        hb.getChildren().addAll(activePlayer, activePlayerMoney);
        hb.setStyle("-fx-border-color: black;");
        return hb;
    }

    /**
     * create a menu showing all cards active player owns
     *
     * @return
     */
    public VBox PlayerCards() {
        VBox playerCards = new VBox();
        playerCards.getChildren().add(new Label("Cards Owned by Player"));
        for (BoardPiece pc : gl.getActivePlayer().getOwnedProperties()) {
            playerCards.getChildren().add(new Label(pc.getTitle()));
        }
        playerCards.setStyle("-fx-border-color: black;");
        return playerCards;

    }

    /**
     * player profile image
     *
     * @return Image for player profile
     */
    public ImageView playerProfilePic() throws FileNotFoundException {
        String pic = gl.getActivePlayer().getToken() + ".png";
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/" + pic);
        Image image = new Image(inputstream) {
        };
        profileToken = new ImageView(image);
        profileToken.setFitHeight(80);
        profileToken.setFitWidth(60);
        return profileToken;
    }

    /**
     * places all tokens on board
     *
     * @return board tokens
     */
    public ImageView playersTokenOnBoard() throws FileNotFoundException {
        // String pic = gl.getActivePlayer().getToken() + ".png";
        // needs a loop for all players to assign all tokens
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/CAT.png");
        Image image = new Image(inputstream) {
        };
        catToken = new ImageView(image);
        catToken.setFitHeight(80);
        catToken.setFitWidth(60);
        gPane.setColumnIndex(catToken, 0);
        gPane.setRowIndex(catToken, 10);
        return catToken;
    }

    /**
     * create a image of the board and display it
     *
     * @return @throws FileNotFoundException
     */
    public ImageView board() throws FileNotFoundException {
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/PropertyTycoon.png");
        Image image = new Image(inputstream) {
        };
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(1000);
        imageView.setFitWidth(1000);
        return imageView;
    }

    /**
     * create all the buttons the player needs
     *
     * @return
     */
    public VBox buttons() {
        VBox buttons = new VBox();
        HBox first = new HBox();
        Button roll = new Button("Roll");
        roll.setPadding(new Insets(10, 10, 10, 10));
        Button buy = new Button("Buy");
        buy.setPadding(new Insets(10, 10, 10, 10));
        roll.setOnAction((event) -> {
            gl.getActivePlayer().rollDice();
        });
        buy.setOnAction((event) -> {
            gl.getActivePlayer().buyProperty(gl.getBoard().getBoardLocations().get(gl.getActivePlayer().getPlayerLocation()), gl.getActivePlayer().getPlayerLocation());
        });
        first.getChildren().addAll(roll, buy);
        first.setSpacing(50);
        HBox second = new HBox();
        Button sell = new Button("Sell");
        sell.setPadding(new Insets(10, 10, 10, 10));
        Button house = new Button("Houses");
        house.setPadding(new Insets(10, 10, 10, 10));
        sell.setOnAction((event) -> {
            /**
             * Sell
             */
        });
        house.setOnAction((event) -> {
            /**
             * Buy House
             */
        });
        second.getChildren().addAll(sell, house);
        second.setSpacing(50);
        buttons.getChildren().addAll(first, second);
        buttons.setSpacing(50);
        buttons.setStyle("-fx-border-color: black;");
        return buttons;
    }

    /**
     * creates a log of all the moves made.
     *
     * @return
     */
    public ScrollPane log() {
        sp = new ScrollPane();
        logTextBox = new Text(" ");
        sp.setContent(logTextBox);
        return sp;
    }

    public Text getLogTextBox() {
        return logTextBox;
    }

    public void setLogTextBox(Text logTextBox) {
        this.logTextBox = logTextBox;
    }

    public void addLogTextBox(String text) {
        Text log = new Text(logTextBox.getText() + text);
        this.logTextBox = log;
    }

    public ScrollPane getLog() {
        return sp;
    }

    /**
     * Updates current player name, balance and picture
     *
     */
    public void updatePlayer() throws FileNotFoundException {
        String pic = gl.getActivePlayer().getToken() + ".png";
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/" + pic);
        Image image = new Image(inputstream) {
        };
        profileToken.setImage(image);
        activePlayer.textProperty().set("Player : " + gl.getActivePlayer().getCharacter());
        activePlayerMoney.textProperty().set("£" + gl.getActivePlayer().getPlayerBalance());
    }

    /**
     * Updates location
     *
     * @param ImageView Token to move
     */
    public void updatePlayerLocation(ImageView token) throws FileNotFoundException {

        switch (gl.getActivePlayer().getPlayerLocation()) {

            case 1:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 10);
                break;

            case 2:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 9);
                break;

            case 3:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 8);
                break;

            case 4:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 7);
                break;

            case 5:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 6);
                break;

            case 6:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 5);
                break;

            case 7:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 4);
                break;

            case 8:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 3);
                break;

            case 9:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 2);
                break;

            case 10:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 1);
                break;

            case 11:
                gPane.setColumnIndex(token, 0);
                gPane.setRowIndex(token, 0);
                break;
            case 12:
                gPane.setColumnIndex(token, 1);
                gPane.setRowIndex(token, 0);
                break;

            case 13:
                gPane.setColumnIndex(token, 2);
                gPane.setRowIndex(token, 0);
                break;

            case 14:
                gPane.setColumnIndex(token, 3);
                gPane.setRowIndex(token, 0);
                break;

            case 15:
                gPane.setColumnIndex(token, 4);
                gPane.setRowIndex(token, 0);
                break;

            case 16:
                gPane.setColumnIndex(token, 5);
                gPane.setRowIndex(token, 0);
                break;

            case 17:
                gPane.setColumnIndex(token, 6);
                gPane.setRowIndex(token, 0);
                break;

            case 18:
                gPane.setColumnIndex(token, 7);
                gPane.setRowIndex(token, 0);
                break;

            case 19:
                gPane.setColumnIndex(token, 8);
                gPane.setRowIndex(token, 0);
                break;

            case 20:
                gPane.setColumnIndex(token, 9);
                gPane.setRowIndex(token, 0);
                break;

            case 21:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 0);
                break;

            case 22:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 1);
                break;

            case 23:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 2);
                break;

            case 24:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 3);
                break;

            case 25:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 4);
                break;

            case 26:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 5);
                break;

            case 27:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 6);
                break;

            case 28:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 7);
                break;

            case 29:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 8);
                break;

            case 30:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 9);
                break;

            case 31:
                gPane.setColumnIndex(token, 10);
                gPane.setRowIndex(token, 10);
                break;

            case 32:
                gPane.setColumnIndex(token, 9);
                gPane.setRowIndex(token, 10);
                break;

            case 33:
                gPane.setColumnIndex(token, 8);
                gPane.setRowIndex(token, 10);
                break;

            case 34:
                gPane.setColumnIndex(token, 7);
                gPane.setRowIndex(token, 10);
                break;

            case 35:
                gPane.setColumnIndex(token, 6);
                gPane.setRowIndex(token, 10);
                break;

            case 36:
                gPane.setColumnIndex(token, 5);
                gPane.setRowIndex(token, 10);
                break;

            case 37:
                gPane.setColumnIndex(token, 4);
                gPane.setRowIndex(token, 10);
                break;

            case 38:
                gPane.setColumnIndex(token, 3);
                gPane.setRowIndex(token, 10);
                break;

            case 39:
                gPane.setColumnIndex(token, 2);
                gPane.setRowIndex(token, 10);
                break;

            case 40:
                gPane.setColumnIndex(token, 1);
                gPane.setRowIndex(token, 10);
                break;

        }
    }

    /**
     * launches the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
