package com.mycompany.propertytycoon;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BoardTest {
    
    private Board board;
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, InvalidFormatException {
        board = new Board();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void boardLocationsTest(){
        assertEquals(board.getBoardLocations().get(0).getClass().getSimpleName(), "GoPiece");
    }
}
