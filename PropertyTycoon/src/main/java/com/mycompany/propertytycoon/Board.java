package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Property Tycoon Board
 */
public class Board {

    private ArrayList<BoardPiece> boardLocations;

    /**
     * Constructor to initialize Board object
     * Calls Parser object to scan through Excel document
     * @throws IOException - Not able to load
     * @throws InvalidFormatException - Excel parser error
     */
    public Board() throws IOException, InvalidFormatException {
        this.boardLocations = new Parser().boardMaker();
    }
    
    //Methods

    /**
     * Getter for variable boardLocations
     * @return an ArrayList of PropertyCards
     */
    public ArrayList<BoardPiece> getBoardLocations() {
        return boardLocations;
    }
    
    //Getter
    
    /**
     * Gets the BoardPiece at a specific location
     * @param num BoardPiece index in Board arrayList
     * @return BoardPiece at index
     */
    public BoardPiece getBoardPiece(int num) {
        return boardLocations.get(num);
    }


}
