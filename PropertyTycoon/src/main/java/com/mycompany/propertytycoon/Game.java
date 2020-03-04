/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.gui.PropertyTycoon;

/**
 *
 * @author Big Nerd Notation
 */
public class Game {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(PropertyTycoon.class);
            }
        }.start();
    }
}
