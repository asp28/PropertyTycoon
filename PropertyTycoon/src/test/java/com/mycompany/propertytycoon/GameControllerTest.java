package com.mycompany.propertytycoon;

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
        controller.getActivePlayer().setPlayerLocation(6);
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
        ArrayList<BoardPiece> owned = new ArrayList<>();
        owned.add(new BoardPiece("sa"));
        controller.getActivePlayer().setOwnedProperties(owned);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on a potluck card and owns 0 properties
        expected.removeAll(expected);
        expected.add("PICKPOT");
        expected.add("END");
        owned.remove(0);
        controller.getActivePlayer().setOwnedProperties(owned);
        controller.getActivePlayer().setPlayerLocation(2);
        Assert.assertEquals(expected, controller.getPlayerActions());

        //Test what actions are in the arrayList if the player lands on jail and is not in jail
        expected.removeAll(expected);
        expected.add("END");
        controller.getActivePlayer().setPlayerLocation(11);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on a opp card and owns 0 properties
        expected.removeAll(expected);
        expected.add("PICKOPP");
        expected.add("END");
        controller.getActivePlayer().setPlayerLocation(8);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test what actions are in the arrayList if the player lands on Go to jail owns 0 properties
        expected.removeAll(expected);
        expected.add("GOTOJAIL");
        expected.add("END");
        controller.getActivePlayer().setPlayerLocation(33);
        Assert.assertEquals(expected, controller.getPlayerActions());

        // Test wha actions are in the arraylist if the player lands on a tax card owns 0 properties
        expected.removeAll(expected);
        expected.add("TAX");
        expected.add("END");
        controller.getActivePlayer().setPlayerLocation(4);
        Assert.assertEquals(expected, controller.getPlayerActions());

    }

    @Test
    public void testDoActions() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        ArrayList<String> actions = new ArrayList<>();
        actions.add("RENT");
        actions.add("SELL");
        actions.add("END");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("SELL");
        expected.add("END");

        Assert.assertEquals(expected, controller.doActions(actions));

    }

    @Test
    public void testMove() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.move();
        Assert.assertTrue(controller.getActivePlayer().getPlayerLocation() > 0);

    }

}
