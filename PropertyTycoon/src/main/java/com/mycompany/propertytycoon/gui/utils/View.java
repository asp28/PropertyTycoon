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
    INIT("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init.fxml"),
    GAME("src/main/java/com/mycompany/propertytycoon/gui/game/Game.fxml"),
    TRADE("src/main/java/com/mycompany/propertytycoon/gui/game/Trade.fxml"),
    TRADERCHOICE("src/main/java/com/mycompany/propertytycoon/gui/game/TradeChoicePage.fxml"),
    MORTGAGE("src/main/java/com/mycompany/propertytycoon/gui/game/Mortgage.fxml"),
    SELL("src/main/java/com/mycompany/propertytycoon/gui/game/sell.fxml"),
    HOUSES("src/main/java/com/mycompany/propertytycoon/gui/game/houses.fxml"),
    HOUSES_CHOICE("src/main/java/com/mycompany/propertytycoon/gui/game/houses_buy_or_sell.fxml"),
    HOUSES_SELL("src/main/java/com/mycompany/propertytycoon/gui/game/houses_sell.fxml"),
    JAIL("src/main/java/com/mycompany/propertytycoon/gui/game/jail.fxml"),
    WINNER("src/main/java/com/mycompany/propertytycoon/gui/game/winning.fxml"),
    TIMER("src/main/java/com/mycompany/propertytycoon/gui/initialisegame/Timer.fxml"),
    AUCTION("src/main/java/com/mycompany/propertytycoon/gui/game/auction.fxml");
    

    private final String fxmlPath;

    /**
     * 
     * @return String path of the FXML document
     */
    public String getFXMLPath() {
        return fxmlPath;
    }

    /**
     * constructor
     * @param fxmlPath - String path
     */
    View(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

}
