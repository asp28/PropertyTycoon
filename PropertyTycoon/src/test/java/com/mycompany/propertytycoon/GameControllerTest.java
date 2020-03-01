package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.Property;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class GameControllerTest {

    @Test
    public void testGetAction() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);

        // Test what actions are in the arrayList if the player lands at Go and owns 0 properties
        ArrayList<String> expected = new ArrayList<>();
        expected.add("ONGO");
        expected.add("END");
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on a property owned by the bank and owns 0 properties
        expected.removeAll(expected);
        expected.add("BUY");
        expected.add("END");
        controller.getActivePlayer().setLocation(6);
        Assert.assertEquals(expected, controller.getPlayerActions());

        //test what actions are in the arrayList if another player owns a card and the player owns more than 1 property
        expected.removeAll(expected);
        expected.add("RENT");
        expected.add("SELL");
        expected.add("END");
        BoardPiece bp = controller.getBoard().getBoardLocations().get(6);
        Property p = (Property) bp;
        ColouredProperty cp = (ColouredProperty) p;
        cp.setOwnedBuy("John");
        ArrayList<Property> owned = new ArrayList<>();
        owned.add(new Property("London Road", "Blue", 20, "5"));
        controller.getActivePlayer().setOwnedProperties(owned);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on a potluck card and owns 0 properties
        expected.removeAll(expected);
        expected.add("PICKCARD");
        expected.add("END");
        owned.remove(0);
        controller.getActivePlayer().setOwnedProperties(owned);
        controller.getActivePlayer().setLocation(2);
        Assert.assertEquals(expected, controller.getPlayerActions());

        //Test what actions are in the arrayList if the player lands on jail and is not in jail
        expected.removeAll(expected);
        expected.add("END");
        controller.getActivePlayer().setLocation(10);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on a opp card and owns 0 properties
        expected.removeAll(expected);
        expected.add("PICKCARD");
        expected.add("END");
        controller.getActivePlayer().setLocation(7);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on Go to jail owns 0 properties
        expected.removeAll(expected);
        expected.add("GOTOJAIL");
        expected.add("END");
        controller.getActivePlayer().setLocation(30);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test wha actions are in the arraylist if the player lands on a tax card owns 0 properties
        expected.removeAll(expected);
        expected.add("TAX");
        expected.add("END");
        controller.getActivePlayer().setLocation(4);
        Assert.assertEquals(expected, controller.getPlayerActions());

    }

    @Test
    public void testDoActions() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        ArrayList<String> actions = new ArrayList<>();
        actions.add("ONGO");
        actions.add("SELL");
        actions.add("END");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("SELL");
        expected.add("END");

        Assert.assertEquals(expected, controller.performActions(actions));

    }

    @Test
    public void testMove() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.move();
        Assert.assertTrue(controller.getActivePlayer().getLocation() > 0);

    }

}
