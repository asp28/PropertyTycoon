
import com.mycompany.propertytycoon.Bank;
import com.mycompany.propertytycoon.PropertyCards;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Duarte
 */
public class BankJUnitTest {

    Bank b;
    ArrayList<Integer> houses = new ArrayList<>();
    PropertyCards p2 = new PropertyCards("Asda", "Indigo", "Pay 1000", true, 500, "50", houses, 50);
    PropertyCards p = new PropertyCards("Sainsburys", "Indigo", "Pay 500", true, 500, "50", houses, 50);

    public BankJUnitTest() {
        houses.add(50);
        ArrayList<PropertyCards> property = new ArrayList<>();
        property.add(p2);
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
        int cash2 = b.withdraw(49500);
        assertEquals(b.getMoney(), 0);
        int cash3 = b.withdraw(500);
        assertEquals(b.getMoney(), 0);
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
        PropertyCards p1 = new PropertyCards("Aldi", "Indigo", "Pay 500", true, 500, "50", houses, 50);
        b.addProperties(p1);
        assertEquals(b.getProperties("Aldi"), p1);
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
