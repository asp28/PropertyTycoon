/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.log;

/**
 *
 * @author ankeet
 */
public class Log {
    
    private static Log instance;
    
    private String log;
    
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
    
    public String getLog() {
        return log;
    }
    
    public void resetLog() {
        log = "";
    }
    
    public void addToLog(String textToAdd) {
        log += textToAdd + "\n";
    }
    
}
