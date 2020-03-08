/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui;

import com.mycompany.propertytycoon.*;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.TextBox;

/**
 *
 * @author BigNerdNotation
 */
public class PropertyTycoon extends Application {

    private GameController gl;
    private ScrollPane sp;
    private Text logTextBox;
    private Label activePlayer, activePlayerMoney;
    private GridPane gPane, editor, gp;
    private ImageView profileToken, catToken, bootToken, spoonToken, gobletToken, hatstandToken, phoneToken;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private HBox controls;
    private VBox log;
    private BorderPane bPane;
    private Stage primaryStage;
    private Button roll, buy, sell, mortgage, endTurn, house;

    @Override
    public void start(Stage primaryStage) throws IOException, InvalidFormatException, NotAProperty {
        this.primaryStage = primaryStage;
        StackPane pane = new StackPane();
        editor = new GridPane();
        gp = new GridPane();
        try {
            newGameMenu();

        } catch (IOException e) {

        } catch (InvalidFormatException e) {

        } catch (NotAProperty e) {

        }
        editor.setPickOnBounds(false);
        gp.setPickOnBounds(false);
        Button start = new Button("New Game");
        start.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        start.setPrefSize(80, 60);
        Button quit = new Button("Quit");
        quit.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        quit.setPrefSize(80, 60);
        start.setOnAction(event -> {
            editor.setVisible(true);
        });
        quit.setOnAction(event -> {
            this.primaryStage.close();

        });
        HBox startScreen = new HBox();
        startScreen.getChildren().addAll(start, quit);
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setSpacing(20);
        pane.getChildren().addAll(startScreen, editor, gp);
        Scene scene = new Scene(pane, 1500, 1000);
        this.primaryStage.setTitle("Property Tycoon");
        this.primaryStage.setScene(scene);
        this.primaryStage.maximizedProperty();
        this.primaryStage.show();
    }

    public void newGameMenu() throws IOException, InvalidFormatException, NotAProperty {
        Label amountOfPlayers = new Label("Enter number of players");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        two.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        three.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        four.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        five.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        six.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        HBox but = new HBox();
        but.getChildren().addAll(two, three, four, five, six);
        but.setSpacing(10);
        Button cancel = new Button("cancel");
        cancel.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black");
        cancel.setOnAction(event -> {
            editor.setVisible(false);
        });
        two.setOnAction(event -> {
            try {
                gl = createGame(2);
            } catch (IOException e) {

            } catch (InvalidFormatException e) {

            }
            Label player1 = new Label("Player 1");
            TextField player1Name = new TextField();
            Label player2 = new Label("Player 2");
            TextField player2Name = new TextField();
            Button confirm = new Button("Confirm");
            confirm.setOnAction(event2 -> {
                if (!player1Name.getText().isEmpty() && !player2Name.getText().isEmpty()) {
                    gl.getAmountOfPlayers().get(0).setName(player1Name.getText());
                    gl.getAmountOfPlayers().get(1).setName(player2Name.getText());
                    try {
                        makeGame();
                    } catch (FileNotFoundException e) {

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing name");
                    alert.setHeaderText("Enter player names");
                    alert.setContentText("");
                    alert.showAndWait();
                }

            });
            gp.setAlignment(Pos.CENTER);
            gp.setMaxSize(300, 250);
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
            gp.add(player1, 0, 0);
            gp.add(player1Name, 0, 1);
            gp.add(player2, 0, 2);
            gp.add(player2Name, 0, 3);
            gp.add(confirm, 0, 4);
            gp.setVisible(true);
        });
        three.setOnAction(event -> {
            try {
                gl = createGame(3);
            } catch (IOException e) {

            } catch (InvalidFormatException e) {

            }
            Label player1 = new Label("Player 1");
            TextField player1Name = new TextField();
            Label player2 = new Label("Player 2");
            TextField player2Name = new TextField();
            Label player3 = new Label("Player 3");
            TextField player3Name = new TextField();
            Button confirm = new Button("Confirm");
            confirm.setOnAction(event2 -> {
                if (!player1Name.getText().isEmpty() && !player2Name.getText().isEmpty() && !player3Name.getText().isEmpty()) {
                    gl.getAmountOfPlayers().get(0).setName(player1Name.getText());
                    gl.getAmountOfPlayers().get(1).setName(player2Name.getText());
                    gl.getAmountOfPlayers().get(2).setName(player3Name.getText());
                    try {
                        makeGame();
                    } catch (FileNotFoundException e) {

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing name");
                    alert.setHeaderText("Enter player names");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            });
            VBox names = new VBox();
            names.getChildren().addAll(player1, player1Name, player2, player2Name, player3, player3Name);
            gp.setAlignment(Pos.CENTER);
            gp.setMaxSize(300, 250);
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
            gp.add(names, 0, 0);
            gp.add(confirm, 0, 4);
            gp.setVisible(true);
        });
        four.setOnAction(event -> {
            try {
                gl = createGame(4);
            } catch (IOException e) {

            } catch (InvalidFormatException e) {

            }
            Label player1 = new Label("Player 1");
            TextField player1Name = new TextField();
            Label player2 = new Label("Player 2");
            TextField player2Name = new TextField();
            Label player3 = new Label("Player 3");
            TextField player3Name = new TextField();
            Label player4 = new Label("Player 4");
            TextField player4Name = new TextField();
            Button confirm = new Button("Confirm");
            confirm.setOnAction(event2 -> {
                if (!player1Name.getText().isEmpty() && !player2Name.getText().isEmpty() && !player3Name.getText().isEmpty() && !player4Name.getText().isEmpty()) {
                    gl.getAmountOfPlayers().get(0).setName(player1Name.getText());
                    gl.getAmountOfPlayers().get(1).setName(player2Name.getText());
                    gl.getAmountOfPlayers().get(2).setName(player3Name.getText());
                    gl.getAmountOfPlayers().get(3).setName(player4Name.getText());
                    try {
                        makeGame();
                    } catch (FileNotFoundException e) {

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing name");
                    alert.setHeaderText("Enter player names");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            });
            VBox names = new VBox();
            names.getChildren().addAll(player1, player1Name, player2, player2Name, player3, player3Name, player4, player4Name);
            gp.setAlignment(Pos.CENTER);
            gp.setMaxSize(300, 250);
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
            gp.add(names, 0, 0);
            gp.add(confirm, 0, 4);
            gp.setVisible(true);
        });
        five.setOnAction(event -> {
            try {
                gl = createGame(5);
            } catch (IOException e) {

            } catch (InvalidFormatException e) {

            }
            Label player1 = new Label("Player 1");
            TextField player1Name = new TextField();
            Label player2 = new Label("Player 2");
            TextField player2Name = new TextField();
            Label player3 = new Label("Player 3");
            TextField player3Name = new TextField();
            Label player4 = new Label("Player 4");
            TextField player4Name = new TextField();
            Label player5 = new Label("Player 5");
            TextField player5Name = new TextField();
            Button confirm = new Button("Confirm");
            confirm.setOnAction(event2 -> {
                if (!player1Name.getText().isEmpty() && !player2Name.getText().isEmpty() && !player3Name.getText().isEmpty() && !player4Name.getText().isEmpty() && !player5Name.getText().isEmpty()) {
                    gl.getAmountOfPlayers().get(0).setName(player1Name.getText());
                    gl.getAmountOfPlayers().get(1).setName(player2Name.getText());
                    gl.getAmountOfPlayers().get(2).setName(player3Name.getText());
                    gl.getAmountOfPlayers().get(3).setName(player4Name.getText());
                    gl.getAmountOfPlayers().get(4).setName(player5Name.getText());
                    try {
                        makeGame();
                    } catch (FileNotFoundException e) {

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing name");
                    alert.setHeaderText("Enter player names");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            });
            VBox names = new VBox();
            names.getChildren().addAll(player1, player1Name, player2, player2Name, player3, player3Name, player4, player4Name, player5, player5Name);
            gp.setAlignment(Pos.CENTER);
            gp.setMaxSize(300, 250);
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
            gp.add(names, 0, 0);
            gp.add(confirm, 0, 4);
            gp.setVisible(true);
        });
        six.setOnAction(event -> {
            try {
                gl = createGame(6);
            } catch (IOException e) {

            } catch (InvalidFormatException e) {

            }
            Label player1 = new Label("Player 1");
            TextField player1Name = new TextField();
            Label player2 = new Label("Player 2");
            TextField player2Name = new TextField();
            Label player3 = new Label("Player 3");
            TextField player3Name = new TextField();
            Label player4 = new Label("Player 4");
            TextField player4Name = new TextField();
            Label player5 = new Label("Player 5");
            TextField player5Name = new TextField();
            Label player6 = new Label("Player 6");
            TextField player6Name = new TextField();
            Button confirm = new Button("Confirm");
            confirm.setOnAction(event2 -> {
                if (!player1Name.getText().isEmpty() && !player2Name.getText().isEmpty() && !player3Name.getText().isEmpty() && !player4Name.getText().isEmpty() && !player5Name.getText().isEmpty() && !player6Name.getText().isEmpty()) {
                    gl.getAmountOfPlayers().get(0).setName(player1Name.getText());
                    gl.getAmountOfPlayers().get(1).setName(player2Name.getText());
                    gl.getAmountOfPlayers().get(2).setName(player3Name.getText());
                    gl.getAmountOfPlayers().get(3).setName(player4Name.getText());
                    gl.getAmountOfPlayers().get(4).setName(player5Name.getText());
                    gl.getAmountOfPlayers().get(5).setName(player6Name.getText());
                    try {
                        makeGame();
                    } catch (FileNotFoundException e) {

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing name");
                    alert.setHeaderText("Enter player names");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            });
            VBox names = new VBox();
            names.getChildren().addAll(player1, player1Name, player2, player2Name, player3, player3Name, player4, player4Name, player5, player5Name, player6, player6Name);
            gp.setAlignment(Pos.CENTER);
            gp.setMaxSize(300, 250);
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
            gp.add(names, 0, 0);
            gp.add(confirm, 0, 4);
            gp.setVisible(true);
        });
        editor.setAlignment(Pos.CENTER);
        editor.setMaxSize(300, 250);
        editor.setHgap(10);
        editor.setVgap(10);
        editor.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
        editor.add(amountOfPlayers, 0, 0);
        editor.add(but, 0, 1);
        editor.add(cancel, 0, 4);
        editor.setVisible(false);
    }

    public void setGamePlayers(int players) throws IOException, InvalidFormatException {
        gl = createGame(players);
        gp = new GridPane();
        ListView<String> tokens1 = new ListView<>();
        tokens1.setOrientation(Orientation.VERTICAL);
        tokens1.getItems().addAll(gl.getTokens());
        /**
         * tokens.setCellFactory(param -> new ListCell<String>() {
         *
         * @Override public void updateItem(String name, boolean empty) {
         * super.updateItem(name, empty); if (empty) { setText(null);
         * setGraphic(null); } else { if (name.equals("cat")) {
         * setGraphic(catToken); } else if (name.equals("boot")) {
         * setGraphic(bootToken); } else if (name.equals("smartphone")) {
         * setGraphic(phoneToken); } else if (name.equals("goblet")) {
         * setGraphic(gobletToken); } else if (name.equals("hatstand")) {
         * setGraphic(hatstandToken); } else if (name.equals("spoon")) {
         * setGraphic(spoonToken); } setText(name); } } });
         */
        for (int i = 0; i < gl.getAmountOfPlayers().size(); i++) {
            gp = new GridPane();
            Label name = new Label("Enter name");
            TextField nameVal = new TextField("Player " + (i + 1));
            Label token = new Label("Choose a token");
            Button confirm = new Button("Confirm");
            confirm.setOnAction(event -> {
                if (!nameVal.getText().isEmpty()) {
                    //ERROR HERE//gl.getAmountOfPlayers().get(i).setName(nameVal.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Choose a name");
                    alert.setHeaderText("Choose a name");
                    alert.setContentText("Enter value");
                    alert.showAndWait();
                }
                if (!tokens1.getSelectionModel().getSelectedItem().isEmpty()) {
                    //ERROR HERE//gl.getAmountOfPlayers().get(i).setToken(tokens1.getSelectionModel().getSelectedItem());
                    tokens1.getItems().remove(tokens1.getSelectionModel().getSelectedItem());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Choose token");
                    alert.setHeaderText("Choose token");
                    alert.setContentText("Select a token from the list");
                    alert.showAndWait();
                }
            });
            gp.setAlignment(Pos.CENTER);
            gp.setMaxSize(300, 250);
            gp.setHgap(10);
            gp.setVgap(10);
            gp.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10");
            gp.add(name, 0, 0);
            gp.add(nameVal, 0, 1);
            gp.add(token, 0, 2);
            gp.add(tokens1, 0, 3);
            gp.add(confirm, 0, 4);
        }
        gp.setVisible(false);
    }

    public void makeGame() throws FileNotFoundException {
        ArrayList<BoardPiece> boardLocations;
        bPane = new BorderPane();
        gPane = new GridPane();
        StackPane sPane = new StackPane();

        log = log();
        try {
            updateControls();
        } catch (IOException e) {

        } catch (NotAProperty e) {

        }

        gPane.setGridLinesVisible(true);
        gPane.getChildren().addAll(playersTokenOnBoard());
        ColumnConstraints column;
        for (int i = 0; i < 11; i++) {
            if (i == 0 || i == 10) {
                column = new ColumnConstraints(135);

            } else if (i == 5) {
                column = new ColumnConstraints(100);
            } else {
                column = new ColumnConstraints(70);
            }
            gPane.getColumnConstraints().add(column);

        }

        RowConstraints row;
        for (int i = 0; i < 11; i++) {

            if (i == 0 || i == 10) {
                row = new RowConstraints(130);

            } else if (i == 7) {
                row = new RowConstraints(67);
            } else {
                row = new RowConstraints(80);
            }

            gPane.getRowConstraints().add(row);
        }

        boardLocations = gl.getBoard().getBoardLocations();

        for (int i = 0; i < 40; i++) {
            LocationNames(i, boardLocations.get(i).getTitle());
        }

        gPane.setAlignment(Pos.CENTER);
        sPane.getChildren().addAll(board(), gPane);
        bPane.setCenter(sPane);
        updateButtons();
        Scene scene = new Scene(bPane, 1500, 1000);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param num
     * @return new game
     * @throws IOException
     * @throws InvalidFormatException
     */
    public GameController createGame(int num) throws IOException, InvalidFormatException {
        gl = new GameController(num);
        for (Player p : gl.getAmountOfPlayers()) {
            p.setToken("spoon");
        }
        return gl;
    }

    /**
     * create players menu (name and balance)
     *
     */
    public VBox Players() {
        VBox hb = new VBox();
        activePlayer = new Label("Player : " + gl.getActivePlayer().getName());
        activePlayerMoney = new Label("£" + gl.getActivePlayer().getBalance());
        hb.getChildren().addAll(activePlayer, activePlayerMoney);
        hb.setStyle("-fx-border-color: black;");
        return hb;
    }

    /**
     *
     * create a menu showing all cards active player owns
     *
     */
    public VBox PlayerCards() {
        VBox playerCards = new VBox();
        playerCards.getChildren().add(new Label("Cards Owned by Player"));
        for (Property pc : gl.getActivePlayer().getOwnedProperties()) {
            playerCards.getChildren().add(new Label(pc.getTitle()));
        }
        playerCards.setStyle("-fx-border-color: black;");
        playerCards.setPrefSize(250, 250);
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
        imageView.setFitHeight(screen.getHeight() - 100);
        imageView.setFitWidth(screen.getHeight() - 100);
        return imageView;
    }

    /**
     * create all the buttons the player needs
     */
    public VBox buttons() throws NotAProperty {
        VBox buttons = new VBox();
        HBox first = new HBox();
        roll = new Button("Roll");
        //roll.setPadding(new Insets(10, 10, 10, 10));
        roll.setPrefSize(80, 60);
        roll.setStyle("-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-color: grey; -fx-text-size: 50;");
        buy = new Button("Buy");
        //buy.setPadding(new Insets(10, 10, 10, 10));
        buy.setPrefSize(80, 60);
        buy.setStyle("-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-color: grey; -fx-text-size: 50;");
        roll.setOnAction((event) -> {
            for (int i = 0; i < gl.getActions().size(); i++) {
                if (gl.getActions().get(i).equalsIgnoreCase("ROLL")) {
                    gl.getActions().remove(i);
                    break;
                }
            }
            gl.move();
            addLogTextBox(gl.getActivePlayer().getName() + " has rolled " + gl.getRolls().getKey() + " and " + gl.getRolls().getValue() + "\n");
            updateButtons();
        });
        buy.setOnAction((event) -> {
            try {
                gl.buyProperty(gl.getBoard().getBoardPiece(gl.getActivePlayer().getLocation()));
            } catch (NotAProperty e) {

            }

            addLogTextBox(gl.getActivePlayer().getName() + " has bought " + gl.getBoard().getBoardPiece(gl.getActivePlayer().getLocation()).getTitle() + "\n");
        });
        first.getChildren().addAll(roll, buy);
        first.setSpacing(20);
        HBox second = new HBox();
        sell = new Button("Sell");
        sell.setPrefSize(80, 60);
        sell.setStyle("-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-color: grey; -fx-text-size: 50;");
        //sell.setPadding(new Insets(10, 10, 10, 10));
        house = new Button("Houses");
        //house.setPadding(new Insets(10, 10, 10, 10));
        house.setPrefSize(80, 60);
        house.setStyle("-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-color: grey; -fx-text-size: 50;");
        sell.setOnAction((event) -> {
            //open sell menu for player
        });
        house.setOnAction((event) -> {
            //open houses menu for player
        });
        second.getChildren().addAll(sell, house);
        second.setSpacing(20);
        HBox third = new HBox();
        mortgage = new Button("Mortgage");
        //mortgage.setPadding(new Insets(10, 10, 10, 10));
        mortgage.setPrefSize(80, 60);
        mortgage.setStyle("-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-color: grey; -fx-text-size: 50;");
        mortgage.setOnAction((event) -> {
            //open mortgage menu
        });
        endTurn = new Button("End Turn");
        //endTurn.setPadding(new Insets(10, 10, 10, 10));
        endTurn.setPrefSize(80, 60);
        endTurn.setStyle("-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-color: grey; -fx-text-size: 50;");
        endTurn.setOnAction((event) -> {
            addLogTextBox(gl.getActivePlayer().getName() + " has ended their turn.\n");
            gl.endTurn();
            try {
                updateControls();
            } catch (NotAProperty e) {

            } catch (FileNotFoundException e) {

            }

        });
        third.getChildren().addAll(mortgage, endTurn);
        third.setSpacing(20);
        buttons.getChildren().addAll(first, second, third);
        buttons.setSpacing(10);
        //buttons.setStyle("-fx-border-color: black;");
        buttons.setPadding(new Insets(10, 10, 10, 45));
        buy.setDisable(true);
        sell.setDisable(true);
        mortgage.setDisable(true);
        house.setDisable(true);
        endTurn.setDisable(true);
        return buttons;
    }

    /**
     * creates a log of all the moves made.
     */
    public VBox log() {
        VBox log = new VBox();
        Text t = new Text("Log");
        sp = new ScrollPane();
        sp.setPrefSize(250, 250);
        logTextBox = new Text("");
        sp.setContent(logTextBox);
        log.getChildren().addAll(t, sp);
        log.setStyle("-fx-border-color: black;");
        log.setPadding(new Insets(10, 10, 10, 10));
        log.setSpacing(10);
        return log;
    }

    public Text getLogTextBox() {
        return logTextBox;
    }

    public void setLogTextBox(Text logTextBox) {
        this.logTextBox = logTextBox;
        sp.setContent(logTextBox);
    }

    public void addLogTextBox(String text) {
        Text log = new Text(logTextBox.getText() + text);
        this.logTextBox = log;
        sp.setContent(logTextBox);
    }

    public ScrollPane getLog() {
        return sp;
    }

    /**
     * Updates current player name, balance and picture
     */
    public void updatePlayer() throws FileNotFoundException {
        String pic = gl.getActivePlayer().getToken() + ".png";
        FileInputStream inputstream = new FileInputStream("./src/main/java/resources/img/" + pic);
        Image image = new Image(inputstream) {
        };
        profileToken.setImage(image);
        activePlayer.textProperty().set("Player : " + gl.getActivePlayer().getName());
        activePlayerMoney.textProperty().set("£" + gl.getActivePlayer().getBalance());
    }

    /**
     * Updates location
     *
     * @param ImageView Token to move
     */
    public void updatePlayerLocation(ImageView token) throws FileNotFoundException {

        switch (gl.getActivePlayer().getLocation()) {

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
     * Location names
     *
     * @param num
     * @param name
     */
    public void LocationNames(int num, String name) {
        Label labelName = new Label(name);
        switch (num) {

            case 0:
                gPane.add(labelName, 0, 10);
                break;

            case 1:
                gPane.add(labelName, 0, 9);
                labelName.setRotate(90);
                break;

            case 2:
                gPane.add(labelName, 0, 8);
                labelName.setRotate(90);
                break;

            case 3:
                gPane.add(labelName, 0, 7);
                labelName.setRotate(90);
                break;

            case 4:
                gPane.add(labelName, 0, 6);
                labelName.setRotate(90);
                break;

            case 5:
                gPane.add(labelName, 0, 5);
                labelName.setRotate(90);
                break;

            case 6:
                gPane.add(labelName, 0, 4);
                labelName.setRotate(90);
                break;

            case 7:
                gPane.add(labelName, 0, 3);
                labelName.setRotate(90);
                break;

            case 8:
                gPane.add(labelName, 0, 2);
                labelName.setRotate(90);
                break;

            case 9:
                gPane.add(labelName, 0, 1);
                labelName.setRotate(90);
                break;

            case 10:
                gPane.add(labelName, 0, 0);
                break;
            case 11:
                gPane.add(labelName, 1, 0);
                labelName.setRotate(180);
                break;

            case 12:
                gPane.add(labelName, 2, 0);
                labelName.setRotate(180);
                break;

            case 13:
                gPane.add(labelName, 3, 0);
                labelName.setRotate(180);
                break;

            case 14:
                gPane.add(labelName, 4, 0);
                labelName.setRotate(180);
                break;

            case 15:
                gPane.add(labelName, 5, 0);
                labelName.setRotate(180);
                break;

            case 16:
                gPane.add(labelName, 6, 0);
                labelName.setRotate(180);
                break;

            case 17:
                gPane.add(labelName, 7, 0);
                labelName.setRotate(180);
                break;

            case 18:
                gPane.add(labelName, 8, 0);
                labelName.setRotate(180);
                break;

            case 19:
                gPane.add(labelName, 9, 0);
                labelName.setRotate(180);
                break;

            case 20:
                gPane.add(labelName, 10, 0);
                break;

            case 21:
                gPane.add(labelName, 10, 1);
                labelName.setRotate(-90);
                break;

            case 22:
                gPane.add(labelName, 10, 2);
                labelName.setRotate(-90);
                break;

            case 23:
                gPane.add(labelName, 10, 3);
                labelName.setRotate(-90);
                break;

            case 24:
                gPane.add(labelName, 10, 4);
                labelName.setRotate(-90);
                break;

            case 25:
                gPane.add(labelName, 10, 5);
                labelName.setRotate(-90);
                break;

            case 26:
                gPane.add(labelName, 10, 6);
                labelName.setRotate(-90);
                break;

            case 27:
                gPane.add(labelName, 10, 7);
                labelName.setRotate(-90);
                break;

            case 28:
                gPane.add(labelName, 10, 8);
                labelName.setRotate(-90);
                break;

            case 29:
                gPane.add(labelName, 10, 9);
                labelName.setRotate(-90);
                break;

            case 30:
                gPane.add(labelName, 10, 10);
                break;

            case 31:
                gPane.add(labelName, 9, 10);
                break;

            case 32:
                gPane.add(labelName, 8, 10);
                break;

            case 33:
                gPane.add(labelName, 7, 10);
                break;

            case 34:
                gPane.add(labelName, 6, 10);
                break;

            case 35:
                gPane.add(labelName, 5, 10);
                break;

            case 36:
                gPane.add(labelName, 4, 10);
                break;

            case 37:
                gPane.add(labelName, 3, 10);
                break;

            case 38:
                gPane.add(labelName, 2, 10);
                break;

            case 39:
                gPane.add(labelName, 1, 10);
                break;

        }
    }

    /**
     * Create a pop up that takes number input to make game
     *
     * Then a second page that then lets player choose name and token
     *
     * GAME CAN THEN BEGIN
     */
    public void playGame() throws IOException, InvalidFormatException {
        createGame(0);
        for (Player p : gl.getAmountOfPlayers()) {
            p.setName("GET NAME");
            p.setToken("GET TOKEN");
        }

        //generate board scene here. Player 1 starts
        //method to display allowed buttons here
        //while loop to carry on until quit button is pressed or only one player is left
        //player wins screen for 15 seconds then back to main menu.
    }

    public void disableButton(Button name, Boolean type) {
        name.setDisable(type);
    }

    public void getActions() {
        ArrayList<String> actionsBefore = gl.getPlayerActions();
        gl.performActions(actionsBefore);
    }

    public void updateButtons() {
        getActions();
        for (String s : gl.getActions()) {
            if (s.equalsIgnoreCase("BUY")) {
                disableButton(buy, false);
            } else {
                disableButton(buy, true);
            }
            if (s.equalsIgnoreCase("SELL")) {
                disableButton(sell, false);
            } else {
                disableButton(sell, true);
            }
            if (s.equalsIgnoreCase("BUYHOUSE") || s.equalsIgnoreCase("SELLHOUSE")) {
                disableButton(house, false);
            } else {
                disableButton(house, true);
            }
            if (s.equalsIgnoreCase("END")) {
                disableButton(endTurn, false);
            }
            if (s.equalsIgnoreCase("ROLL")) {
                disableButton(roll, false);
            }
        }
    }

    public void updateControls() throws NotAProperty, FileNotFoundException {
        VBox all = new VBox();
        controls = new HBox();
        all.getChildren().add(Players());
        all.getChildren().addAll(PlayerCards(), buttons(), log);
        all.setSpacing(10);
        controls.getChildren().addAll(playerProfilePic(), all);
        all.setPadding(new Insets(10, 50, 10, 10));
        controls.setPadding(new Insets(50, 0, 0, 0));
        bPane.setRight(controls);
    }
}
