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
    
    private String log = "";
    
    /**
     * 
     * @return instance of log
     */
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
    
    /**
     * 
     * @return log
     */
    public String getLog() {
        return log;
    }
    
    /**
     * Reset the log
     */
    public void resetLog() {
        log = "";
    }
    
    /**
     * Add text to the log.
     * @param textToAdd - Text to add to log (new line added at the end)
     */
    public void addToLog(String textToAdd) {
        log += textToAdd + "\n";
    }
    
}
