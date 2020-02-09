/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import java.util.Random;

/**
 * @author christian
 */
public class Dice {

    public int roll() {
        Random rn = new Random();
        int answer = rn.nextInt(5) + 1;
        return answer;
    }
}

