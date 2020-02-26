
package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;


public class Board {

    private ArrayList<BoardPiece> boardLocations;

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
    public ArrayList<BoardPiece> getBoardLocations() {
        return boardLocations;
    }
    
    public BoardPiece getProperty(int num) {
        return boardLocations.get(num);
    }

}
