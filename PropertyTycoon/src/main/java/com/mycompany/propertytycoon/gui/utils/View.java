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
    
    MAIN_MENU ("./src/main/java/com/mycompany/propertytycoon/gui/mainmenu/Homepage.fxml"),
    INIT_2 ("./src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_2.fxml"),
    INIT_3 ("./src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_3.fxml"),
    INIT_4 ("./src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_4.fxml"),
    INIT_5 ("./src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_5.fxml"),
    INIT_6 ("./src/main/java/com/mycompany/propertytycoon/gui/initialisegame/init_6.fxml"),
    GAME ("./src/main/java/com/mycompany/propertytycoon/gui/game/Game.fxml");
    
    String getFXMLPath() { return fxmlPath; }

    View(String fxmlPath) { this.fxmlPath = fxmlPath; }

    private String fxmlPath;
}
