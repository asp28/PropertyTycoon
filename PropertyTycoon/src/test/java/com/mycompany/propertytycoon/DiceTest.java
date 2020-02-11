package com.mycompany.propertytycoon;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class DiceTest {
    /**
     * Test if the dice rolls between 1 and 6
     */
    @Test
    public void testDiceRoll() {
        Dice dice = new Dice();
        Assertions.assertTrue(dice.roll() <= 6 && dice.roll() >= 1);

    }
}
