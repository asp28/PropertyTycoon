package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        actions.add("GOTOJAIL");
        actions.add("END");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("END");

        Assert.assertEquals(expected, controller.performActions(actions));

    }

    @Test
    public void testMove() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.move();
        Assert.assertTrue(controller.getActivePlayer().getLocation() > 0);

    }

    @Test
    public void gameloopsAroundBoard() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(40);
        controller.move();
        System.out.println(controller.getActivePlayer().getLocation());
        Assert.assertTrue(controller.getActivePlayer().getGameloops() > 0);


    }

    @Test
    public void testBuyButton() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(6);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        System.out.println(cp.getTitle());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        System.out.println(cp.getOwnedBuy());
        Assert.assertTrue(controller.getActivePlayer().getName().equals(cp.getOwnedBuy()));


    }

    @Test
    public void buyHouse() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(3);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        System.out.println(cp.getTitle());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.buyHouse();

        System.out.println(cp.getRent());

        Assert.assertTrue(cp.getHouseCount() > 0);
    }

    @Test
    public void testIfAllColoursOwned() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);

        controller.getActivePlayer().setLocation(1);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        System.out.println(cp.getTitle());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));

        controller.getActivePlayer().setLocation(3);
        cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        System.out.println(cp.getTitle());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));

        System.out.println(controller.getPlayerActions());
        ArrayList<String> actions = new ArrayList<>();
        actions.add("BUYHOUSE");
        actions.add("SELL");
        actions.add("END");
        Assert.assertEquals(actions, controller.getPlayerActions());
    }

    @Test
    public void testHouseInLineCountAndSellHouse() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);

        controller.getActivePlayer().setLocation(1);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        System.out.println(cp.getTitle());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.buyHouse();

        System.out.println(controller.getPlayerActions());
        controller.getActivePlayer().setLocation(3);
        cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        System.out.println(cp.getTitle());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.buyHouse();

        controller.getActivePlayer().setLocation(1);
        System.out.println(controller.getPlayerActions());
        //controller.buyHouse();

        ArrayList<String> actions = new ArrayList<>();
        actions.add("BUYHOUSE");
        actions.add("SELL");
        actions.add("SELLHOUSE");
        actions.add("END");
        Assert.assertEquals(actions, controller.getPlayerActions());

    }
    
    @Test
    public void getMaxBidTest() throws InvalidFormatException, IOException{
        GameController controller = new GameController(2);
        
        HashMap<Player,Integer> bids = new HashMap<>();
        controller.getActivePlayer().setName("Jekyll");
        bids.put(controller.getActivePlayer(), 100);
        controller.endTurn();
        controller.getActivePlayer().setName("Hyde");
        bids.put(controller.getActivePlayer(), 200);
        
        Assert.assertEquals(controller.getHighestBid(bids).getKey().getName(), "Hyde");
        Assert.assertEquals(controller.getHighestBid(bids).getValue().intValue(), 200);
        
    }
    
    @Test
    public void mortgagePropertyTest() throws IOException, InvalidFormatException{
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(3);
        Property prop = (Property) controller.getBoard().getBoardPiece(controller.getActivePlayer().getLocation());
        int initialBalance = controller.getActivePlayer().getBalance();
        int propPrice = prop.getCost();
        Assert.assertEquals(prop.isMortgaged(), false);
        controller.mortgageProperty(prop);
        Assert.assertEquals(prop.isMortgaged(), true);
        Assert.assertEquals(controller.getActivePlayer().getBalance(), initialBalance + propPrice/2);
    }


}
