package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.gui.LoadNewGameGUI;
/**
 * Main class to start the game
 */
public class Game {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(LoadNewGameGUI.class);
            }
        }.start();
    }
}
