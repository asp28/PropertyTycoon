/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.utils;

/**
 *
 * @author ankeet
 */
public enum View {

    MAIN_MENU("src/main/java/com/mycompany/propertytycoon/gui/mainmenu/homepage.fxml"),
    PLAYER_BOT_COUNT("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/player_an_ai_count.fxml"),
    INIT_2("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_2.fxml"),
    INIT_3("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_3.fxml"),
    INIT_4("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_4.fxml"),
    INIT_5("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_5.fxml"),
    INIT_6("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_6.fxml"),
    GAME("src/main/java/com/mycompany/propertytycoon/gui/game/Game.fxml"),
    TRADE("src/main/java/com/mycompany/propertytycoon/gui/game/Trade.fxml"),
    TRADERCHOICE("src/main/java/com/mycompany/propertytycoon/gui/game/TradeChoicePage.fxml"),
    MORTGAGE("src/main/java/com/mycompany/propertytycoon/gui/game/Mortgage.fxml"),
    SELL("src/main/java/com/mycompany/propertytycoon/gui/game/sell.fxml"),
    AUCTION("src/main/java/com/mycompany/propertytycoon/gui/game/auctions.fxml");
    

    private String fxmlPath;

    public String getFXMLPath() {
        return fxmlPath;
    }

    View(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

}
