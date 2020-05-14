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
public enum Tokens {
    
    CAT("src/main/java/resources/img/CAT.png"),
    BOOT("src/main/java/resources/img/BOOT.png"),
    HAT_HANGER("src/main/java/resources/img/hat_hanger.png"),
    PHONE("src/main/java/resources/img/PHONE.png"),
    CUP("src/main/java/resources/img/cup.png"),
    SPOON("src/main/java/resources/img/SPOON.png");
    
    private final String path;

    /**
     * 
     * @return String path of the enum
     */
    public String getPath() {
        return path;
    }

    /**
     * Token constructor using the path
     * @param path 
     */
    Tokens(String path) {
        this.path = path;
    }
}
