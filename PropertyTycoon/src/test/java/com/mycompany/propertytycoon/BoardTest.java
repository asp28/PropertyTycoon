package com.mycompany.propertytycoon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    Board board;

    public BoardTest() throws IOException, InvalidFormatException {
        board = new Board();
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

    @Test
    public void getBoardLocations() {
        assertEquals("Go", board.getBoardLocations().get(0).getName());
    }

}
