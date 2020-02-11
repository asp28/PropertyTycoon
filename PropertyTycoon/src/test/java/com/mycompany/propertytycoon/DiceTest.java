package java.com.mycompany.propertytycoon;


import com.mycompany.propertytycoon.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
