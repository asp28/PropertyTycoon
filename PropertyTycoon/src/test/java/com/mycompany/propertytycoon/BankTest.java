package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class BankTest {

    Bank b;

    Property p = new Property("Sainsburys", "1", 1, "someone");

    public BankTest() {
        ArrayList<BoardPiece> property = new ArrayList<>();
        property.add(p);

        b = new Bank(property);
    }

    /**
     * Get money test
     */
    @Test
    public void testGetMoney() {
        assertEquals(b.getMoney(), 50000);
    }

    /**
     * Deposit test
     */
    @Test
    public void testDeposit() {
        b.deposit(500);
        assertEquals(b.getMoney(), 50500);
    }

    /**
     * Withdraw test
     */
    @Test
    public void testWithdraw() {
        int cash = b.withdraw(500);
        System.out.print(b.getMoney());
        assertEquals(cash, 500);
        assertEquals(b.getMoney(), 49500);
        b.withdraw(49500);
        assertEquals(b.getMoney(), 0);
        b.withdraw(500);
        assertEquals(b.getMoney(), 49500);
    }

    /**
     * Get properties test
     */
    @Test
    public void testGetProperties() {
        assertEquals(b.getProperties("Sainsburys"), p);
        assertNull(b.getProperties("Tesco"));
    }

    /**
     * Add properties test
     */
    @Test
    public void testAddProperties() {
        Property p2 = new Property("Aldi", "1", 1, "someone");
        b.addProperties(p2);
        assertEquals(b.getProperties("Aldi"), p2);
    }

    /**
     * Remove properties test
     */
    @Test
    public void testRemoveProperties() {
        b.removeProperties("Sainsburys");
        assertNull(b.getProperties("Sainsburys"));
    }

}
