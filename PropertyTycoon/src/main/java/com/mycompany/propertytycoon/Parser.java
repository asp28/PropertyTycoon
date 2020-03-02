package com.mycompany.propertytycoon;

import com.mycompany.propertytycoon.cards.PotLuck;
import com.mycompany.propertytycoon.cards.OpportunityKnocks;
import com.mycompany.propertytycoon.boardpieces.PotLuckPiece;
import com.mycompany.propertytycoon.boardpieces.JailPiece;
import com.mycompany.propertytycoon.boardpieces.BoardPiece;
import com.mycompany.propertytycoon.boardpieces.FreeParkingPiece;
import com.mycompany.propertytycoon.boardpieces.GoPiece;
import com.mycompany.propertytycoon.boardpieces.StationProperty;
import com.mycompany.propertytycoon.boardpieces.GoToJailPiece;
import com.mycompany.propertytycoon.boardpieces.UtilityProperty;
import com.mycompany.propertytycoon.boardpieces.ColouredProperty;
import com.mycompany.propertytycoon.boardpieces.OpportunityKnocksPiece;
import com.mycompany.propertytycoon.boardpieces.TaxPiece;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author BigNerdNotation
 *
 * Parser object will turn the excel documents into java objects that can be
 * used.
 *
 */
public class Parser {

    /**
     * String location of where the board is located String location of where
     * the opportunityKnocks cards and PotLuck cards are located.
     */
    private final String board = "./src/main/java/resources/PropertyTycoonBoardData.xlsx";
    private final String OppoPotLuck = "./src/main/java/resources/PropertyTycoonCardData.xlsx";

    /**
     *
     * @return ArrayList of propertyCard objects.
     * @throws IOException
     * @throws InvalidFormatException
     */
    public ArrayList<BoardPiece> boardMaker() throws IOException, InvalidFormatException {
        ArrayList<BoardPiece> b = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File(board));
        Sheet s = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = s.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            if (r.getRowNum() < 4) {
                continue;
            }
            if (r.getRowNum() > 43) {
                break;
            }
            if (r.getCell(3) == null) {
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("go")) {
                    b.add(new GoPiece(r.getCell(1).getStringCellValue(), r.getCell(4).getStringCellValue()));
                    continue;
                }
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("free parking")) {
                    b.add(new FreeParkingPiece(r.getCell(1).getStringCellValue(), r.getCell(4).getStringCellValue()));
                    continue;
                }
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("income tax")) {
                    b.add(new TaxPiece(r.getCell(1).getStringCellValue(), 200));
                    continue;
                }
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("super tax")) {
                    b.add(new TaxPiece(r.getCell(1).getStringCellValue(), 100));
                    continue;
                }
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("opportunity knocks")) {
                    b.add(new OpportunityKnocksPiece(r.getCell(1).getStringCellValue(), r.getCell(4).getStringCellValue()));
                    continue;
                }
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("pot luck")) {
                    b.add(new PotLuckPiece(r.getCell(1).getStringCellValue(), r.getCell(4).getStringCellValue()));
                    continue;
                }
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("jail/just visiting")) {
                    b.add(new JailPiece(r.getCell(1).getStringCellValue()));
                    continue;
                }

            } else {
                if (r.getCell(1).getStringCellValue().equalsIgnoreCase("go to jail")) {
                    b.add(new GoToJailPiece(r.getCell(1).getStringCellValue()));
                    continue;
                } else {
                    String name = r.getCell(1).getStringCellValue();
                    String group = r.getCell(3).getStringCellValue();
                    int cost = (int) r.getCell(7).getNumericCellValue();
                    if (r.getCell(3).getStringCellValue().equalsIgnoreCase("station")) {
                        String rent = r.getCell(8).getStringCellValue();
                        b.add(new StationProperty(name, group, cost, rent));
                    } else if (r.getCell(3).getStringCellValue().equalsIgnoreCase("utilities")) {
                        String rent = r.getCell(8).getStringCellValue();
                        b.add(new UtilityProperty(name, group, cost, rent));
                    } else {
                        
                        int rentInt = (int) r.getCell(8).getNumericCellValue();
                        String rent = Integer.toString(rentInt);
                        ArrayList<Integer> houses = new ArrayList<>();
                        houses.add(rentInt);
                        for (int i = 0; i < 5; i++) {
                            double houseDoub = r.getCell(10 + i).getNumericCellValue();
                            int houseInt = (int) houseDoub;
                            houses.add(houseInt);
                        }
                        int houseCost = 0;
                        if (workbook.getSheetAt(0).getRow(47).getCell(7).getStringCellValue().contains("Blue") && r.getCell(3).getStringCellValue().equalsIgnoreCase("blue") || workbook.getSheetAt(0).getRow(47).getCell(7).getStringCellValue().contains("Brown") && r.getCell(3).getStringCellValue().equalsIgnoreCase("brown")) {
                            houseCost = 50;
                        }
                        if (workbook.getSheetAt(0).getRow(48).getCell(7).getStringCellValue().contains("Purple") && r.getCell(3).getStringCellValue().equalsIgnoreCase("purple") || workbook.getSheetAt(0).getRow(48).getCell(7).getStringCellValue().contains("Orange") && r.getCell(3).getStringCellValue().equalsIgnoreCase("orange")) {
                            houseCost = 100;
                        }
                        if (workbook.getSheetAt(0).getRow(49).getCell(7).getStringCellValue().contains("Red") && r.getCell(3).getStringCellValue().equalsIgnoreCase("red") || workbook.getSheetAt(0).getRow(49).getCell(7).getStringCellValue().contains("Yellow") && r.getCell(3).getStringCellValue().equalsIgnoreCase("yellow")) {
                            houseCost = 150;
                        }
                        if (workbook.getSheetAt(0).getRow(50).getCell(7).getStringCellValue().contains("Green") && r.getCell(3).getStringCellValue().equalsIgnoreCase("green") || workbook.getSheetAt(0).getRow(50).getCell(7).getStringCellValue().contains("Deep blue") && r.getCell(3).getStringCellValue().equalsIgnoreCase("deep blue")) {
                            houseCost = 200;
                        }
                        b.add(new ColouredProperty(name, group, cost, rent, houses, houseCost));
                    }
                }
            }
        }
        return b;
    }

    /**
     * Creates the Opportunity Knocks cards
     *
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public ArrayList<OpportunityKnocks> createOppoCards() throws IOException, InvalidFormatException {
        ArrayList<OpportunityKnocks> oppo = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File(OppoPotLuck));
        Sheet s = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = s.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            if (r.getRowNum() < 25) {
                continue;
            } else {
                OpportunityKnocks ok = new OpportunityKnocks(r.getCell(0).getStringCellValue(), r.getCell(3).getStringCellValue());
                oppo.add(ok);
            }
        }
        Collections.shuffle(oppo);

        return oppo;
    }

    /**
     * Creates the PotLuck cards
     *
     * @return
     * @throws IOException
     * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
     */
    public ArrayList<PotLuck> createPotLuckCards() throws IOException, InvalidFormatException {
        ArrayList<PotLuck> potluck = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File(OppoPotLuck));
        Sheet s = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = s.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            if (r.getRowNum() < 5) {
                continue;
            } else if (r.getRowNum() > 21) {
                break;
            } else {
                PotLuck pl = new PotLuck(r.getCell(0).getStringCellValue(), r.getCell(3).getStringCellValue());
                potluck.add(pl);
            }

        }
        Collections.shuffle(potluck);
        return potluck;
    }
    
}
