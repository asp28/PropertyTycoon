/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon.gui.utils;

import com.mycompany.propertytycoon.gui.utils.View;
import java.util.Date;

/**
 *
 * @author ankeet
 */
public class Timer implements Runnable {

    private long timer, start = System.currentTimeMillis(), elapsed;

    public Timer(int length) {
        timer = length * 60 * 1000;
    }

    public void startTimer() {
        while (elapsed < timer) {
            elapsed = (new Date()).getTime() - start;
        }
    }

    @Override
    public void run() {
        startTimer();
    }
    
    public boolean timerComplete() {
        return elapsed > timer;
    }

}
