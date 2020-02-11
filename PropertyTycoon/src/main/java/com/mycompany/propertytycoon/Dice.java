/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.com.mycompany.propertytycoon;

import java.util.Random;

/**
 * @author christian Saraty
 * @author Big Nerd Notation
 * @version 1.0
 * <p>
 * This is the dice class containing one method roll
 */
public class Dice {

    /**
     * Rolls the dice and generates a random integer between 1 and 6
     *
     * @return integer that is a random number between 1 and 6
     */
    public int roll() {
        Random rn = new Random();
        int answer = rn.nextInt(6) + 1;
        return answer;
    }
}

