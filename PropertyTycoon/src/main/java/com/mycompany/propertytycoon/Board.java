
package com.mycompany.propertytycoon;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;


public class Board {

    private ArrayList<PropertyCards> boardLocations;

    /**
     * Constructor to initialize Board object
     *
     * @throws IOException
     * @throws InvalidFormatException
     */
    public Board() throws IOException, InvalidFormatException {
        boardLocations = new Parser().boardMaker();
    }

    /**
     * Getter for variable boardLocations
     * @return an ArrayList of PropertyCards
     */
    public ArrayList<PropertyCards> getBoardLocations() {
        return boardLocations;
    }

}
