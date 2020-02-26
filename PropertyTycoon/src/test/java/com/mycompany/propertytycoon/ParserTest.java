/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.cards.PotLuck;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author ankeet
 */
public class ParserTest {

    ArrayList<BoardPiece> board;
    ArrayList<OpportunityKnocks> oppo;
    ArrayList<PotLuck> potluck;

    public ParserTest() throws IOException, InvalidFormatException {
        Parser p = new Parser();
        //board = p.boardMaker();
        oppo = p.createOppoCards();
        potluck = p.createPotLuckCards();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of boardMaker method, of class Parser.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBoardMaker() throws Exception {
        System.out.println("boardMaker");
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Go");
        expResult.add("Crapper Street");
        expResult.add("Pot Luck");
        expResult.add("Gangsters Paradise");
        expResult.add("Income Tax");
        expResult.add("Brighton Station");
        expResult.add("Weeping Angel");
        expResult.add("Opportunity Knocks");
        expResult.add("Potts Avenue");
        expResult.add("Nardole Drive");
        expResult.add("Jail/Just visiting");
        expResult.add("Skywalker Drive");
        expResult.add("Tesla Power Co");
        expResult.add("Wookie Hole");
        expResult.add("Rey Lane");
        expResult.add("Hove Station");
        expResult.add("Cooper Drive");
        expResult.add("Pot Luck");
        expResult.add("Wolowitz Street");
        expResult.add("Penny Lane");
        expResult.add("Free Parking");
        expResult.add("Yue Fei Square");
        expResult.add("Opportunity Knocks");
        expResult.add("Mulan Rouge");
        expResult.add("Han Xin Gardens");
        expResult.add("Falmer Station");
        expResult.add("Kirk Close");
        expResult.add("Picard Avenue");
        expResult.add("Edison Water");
        expResult.add("Crusher Creek");
        expResult.add("Go to Jail");
        expResult.add("Sirat Mews");
        expResult.add("Ghengis Crescent");
        expResult.add("Pot Luck");
        expResult.add("Ibis Close");
        expResult.add("Lewes Station");
        expResult.add("Opportunity Knocks");
        expResult.add("Hawking Way");
        expResult.add("Super Tax");
        expResult.add("Turing Heights");
        ArrayList<String> result = new ArrayList<>();
        board.forEach((pc) -> {
            result.add(pc.getTitle());
        });
        assertEquals(expResult, result);
    }

    /**
     * Tests Opportunity Knocks cards being made.
     */
    @Test
    public void testCreateOppoCards() {
        System.out.println("Opportunity Knocks Card Maker");
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("\"Bank pays you divided of £50\"");
        expResult.add("\"You have won a lip sync battle. Collect £100\"");
        expResult.add("\"Advance to Turing Heights\"");
        expResult.add("\"Advance to Han Xin Gardens. If you pass GO, collect £200\"");
        expResult.add("\"Fined £15 for speeding\"");
        expResult.add("\"Pay university fees of £150\"");
        expResult.add("\"Take a trip to Hove station. If you pass GO collect £200\"");
        expResult.add("\"Loan matures, collect £150\"");
        expResult.add("\"You are assessed for repairs, £40/house, £115/hotel\"");
        expResult.add("\"Advance to GO\"");
        expResult.add("\"You are assessed for repairs, £25/house, £100/hotel\"");
        expResult.add("\"Go back 3 spaces\"");
        expResult.add("\"Advance to Skywalker Drive. If you pass GO collect £200\"");
        expResult.add("\"Go to jail. Do not pass GO, do not collect £200\"");
        expResult.add("\"Drunk in charge of a skateboard. Fine £20\"");
        expResult.add("\"Get out of jail free\"");
        ArrayList<String> result = new ArrayList<>();
        oppo.forEach((ok) -> {
            result.add(ok.getDescription());
        });
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < expResult.size(); j++) {
                if (result.get(i) == null ? expResult.get(j) == null : result.get(i).equals(expResult.get(j))) {
                    expResult.remove(j);
                }
            }
        }
        if (expResult.isEmpty()) {
            assertTrue(true);
        } else {
            fail();
        }

    }

    /**
     * Tests PotLuck cards being made.
     */
    @Test
    public void testCreatePotLuckCards() {
        System.out.println("PotLuck Card Maker");
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("\"You inherit £100\"");
        expResult.add("\"You have won 2nd prize in a beauty contest, collect £20\"");
        expResult.add("\"Go back to Crapper Street\"");
        expResult.add("\"Student loan refund. Collect £20\"");
        expResult.add("\"Bank error in your favour. Collect £200\"");
        expResult.add("\"Pay bill for text books of £100\"");
        expResult.add("\"Mega late night taxi bill pay £50\"");
        expResult.add("\"Advance to go\"");
        expResult.add("\"From sale of Bitcoin you get £50\"");
        expResult.add("\"Pay a £10 fine or take opportunity knocks\"");
        expResult.add("\"Pay insurance fee of £50\"");
        expResult.add("\"Savings bond matures, collect £100");
        expResult.add("\"Go to jail. Do not pass GO, do not collect £200\"");
        expResult.add("\"Received interest on shares of £25\"");
        expResult.add("\"It's your birthday. Collect £10 from each player\"");
        expResult.add("\"Get out of jail free\"");
        ArrayList<String> result = new ArrayList<>();
        potluck.forEach((ok) -> {
            result.add(ok.getDescription());
        });
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < expResult.size(); j++) {
                if (result.get(i) == null ? expResult.get(j) == null : result.get(i).equals(expResult.get(j))) {
                    expResult.remove(j);
                }
            }
        }
        if (expResult.isEmpty()) {
            assertTrue(true);
        } else {
            fail();
        }
    }

}
