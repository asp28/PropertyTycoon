package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.Property;
import com.mycompany.propertytycoon.exceptions.NotAProperty;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class GameControllerTestV2 {

    //Moving around the bored and ending turn
    @Test
    public void testMove() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.move();
        Assert.assertTrue(controller.getActivePlayer().getLocation() > 0);
    }

    @Test
    public void testIncreasingLoopAroundBoard() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(40);
        controller.move();
        if (controller.getActivePlayer().getLocation() == 10) {
            Assert.assertEquals(0, controller.getActivePlayer().getGameloops());
        } else {
            Assert.assertTrue(controller.getActivePlayer().getGameloops() > 0);
        }
    }

    @Test
    public void testEndTurn() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.getAmountOfPlayers().get(0).setName("Player 1");
        controller.getAmountOfPlayers().get(1).setName("Player 2");
        controller.endTurn();
        Assert.assertTrue(controller.getActivePlayer().getName().equals("Player 2"));

    }

    //Buying and selling properties buttons logic
    @Test
    public void testBuyProperty() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(6);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        Assert.assertTrue(controller.getActivePlayer().getName().equals(cp.getOwnedBuy()));
    }

    @Test
    public void testBuyHouse() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(3);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.buyHouse();

        Assert.assertTrue(cp.getHouseCount() > 0);
    }

    @Test
    public void testSellHouse() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(3);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.buyHouse();
        controller.sellHouse(cp);
        Assert.assertTrue(cp.getHouseCount() == 0);

    }

    @Test
    public void testSellProperty() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(6);
        ColouredProperty cp = (ColouredProperty) controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation());
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.sellProperty(cp);
        Assert.assertFalse(controller.getActivePlayer().getName().equals(cp.getOwnedBuy()));
    }

    //Paying rent
    @Test
    public void paySingleRent() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(3);
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.endTurn();
        controller.getActivePlayer().setLocation(3);
        controller.performActions(controller.getPlayerActions());
        Assert.assertTrue(1496 == controller.getActivePlayer().getBalance());
    }

    @Test
    public void payDoubleRent() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(1);
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.getActivePlayer().setLocation(3);
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));

        controller.endTurn();
        controller.getActivePlayer().setLocation(3);
        controller.performActions(controller.getPlayerActions());
        Assert.assertTrue(1492 == controller.getActivePlayer().getBalance());
    }

    @Test
    public void PayRentWithHouse() throws IOException, InvalidFormatException, NotAProperty {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(1);
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.getActivePlayer().setLocation(3);
        controller.buyProperty(controller.getBoard().getBoardLocations().get(controller.getActivePlayer().getLocation()));
        controller.buyHouse();
        controller.endTurn();
        controller.getActivePlayer().setLocation(3);
        controller.performActions(controller.getPlayerActions());
        Assert.assertTrue(1480 == controller.getActivePlayer().getBalance());
    }

    @Test
    public void testBuyPropertyFromAuction() {
    }

    @Test
    public void testMortageProperty() throws IOException, InvalidFormatException {
        GameController controller = new GameController(2);
        controller.getActivePlayer().setLocation(3);
        Property prop = (Property) controller.getBoard().getBoardPiece(controller.getActivePlayer().getLocation());
        int initialBalance = controller.getActivePlayer().getBalance();
        int propPrice = prop.getCost();
        Assert.assertEquals(prop.isMortgaged(), false);
        controller.mortgageProperty(prop);
        Assert.assertEquals(prop.isMortgaged(), true);
        Assert.assertEquals(controller.getActivePlayer().getBalance(), initialBalance + propPrice / 2);
    }

    @Test
    public void testTradeProperty() {
    }

    //Getting and performing actions
    @Test
    public void testGetActions() {
    }

    @Test
    public void testDoActions() {
    }

    @Test
    public void testValidAuction() throws IOException, InvalidFormatException{
        GameController gc = new GameController(3);
        HashMap<Player, Integer> bids = new HashMap<>();
        
        //Test for highest bid duplicates
        bids.put(gc.getAmountOfPlayers().get(0), 100);
        bids.put(gc.getAmountOfPlayers().get(1), 200);
        bids.put(gc.getAmountOfPlayers().get(2), 200);
        Assert.assertFalse(gc.checkValidAuction(bids));
        bids.clear();
        
        //Test for insuffienct funds
        bids.put(gc.getAmountOfPlayers().get(0), 100);
        gc.getAmountOfPlayers().get(1).setBalance(100);
        bids.put(gc.getAmountOfPlayers().get(1), 200);
        bids.put(gc.getAmountOfPlayers().get(2), 300);
        Assert.assertFalse(gc.checkValidAuction(bids));
        bids.clear();
        
        //Test for highest bid duplicates and insuffient funds
        bids.put(gc.getAmountOfPlayers().get(0), 100);
        gc.getAmountOfPlayers().get(1).setBalance(100);
        bids.put(gc.getAmountOfPlayers().get(1), 300);
        bids.put(gc.getAmountOfPlayers().get(2), 300);
        Assert.assertFalse(gc.checkValidAuction(bids));
        bids.clear();
        
        //Test for success
        bids.put(gc.getAmountOfPlayers().get(0), 100);
        bids.put(gc.getAmountOfPlayers().get(1), 100);
        bids.put(gc.getAmountOfPlayers().get(2), 200);
        Assert.assertTrue(gc.checkValidAuction(bids));
        
    }
    
    @Test
    public void testAuctionPass() throws IOException, InvalidFormatException, NotAProperty{
        GameController gc = new GameController(2);
        gc.getActivePlayer().setLocation(3);
        HashMap<Player, Integer> bids = new HashMap<>();
        bids.put(gc.getAmountOfPlayers().get(0), 100);
        bids.put(gc.getAmountOfPlayers().get(1), 200);
        gc.auction(bids);
        Assert.assertTrue(gc.getAmountOfPlayers().get(1).getOwnedProperties().size() > 0);
        Assert.assertEquals(1300, gc.getAmountOfPlayers().get(1).getBalance()); 
    }
    
    @Test
    public void testAuctionPropFail() throws IOException, InvalidFormatException, NotAProperty{
        GameController gc = new GameController(2);
        gc.getActivePlayer().setLocation(0);
        HashMap<Player, Integer> bids = new HashMap<>();
        bids.put(gc.getAmountOfPlayers().get(0), 100);
        bids.put(gc.getAmountOfPlayers().get(1), 200);
        int propCount = gc.getAmountOfPlayers().get(1).getOwnedProperties().size();
        try{
            gc.auction(bids);
        }
        catch (NotAProperty np){
            Assert.assertEquals(propCount, gc.getAmountOfPlayers().get(1).getOwnedProperties().size());
        }
    }
}
