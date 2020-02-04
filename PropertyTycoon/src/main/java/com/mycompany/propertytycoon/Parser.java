/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.propertytycoon;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ankeet
 */
public class Parser {

    public static final String board = "C:/Users/ankee/Documents/UNIVERSITY/YEAR 2/SWE/PropertyTycoon/PropertyTycoon/src/main/java/com/mycompany/propertytycoon/PropertyTycoonBoardData.xlsx";

    public ArrayList<PropertyCards> boardMaker() throws IOException, InvalidFormatException {
        ArrayList<PropertyCards> b = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File(board));
        Sheet s = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = s.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            if (r.getCell(0) == null) {
                break;
            }
            double positionDoub = r.getCell(0).getNumericCellValue();
            String name = r.getCell(1).getStringCellValue();
            String group = r.getCell(2).getStringCellValue();
            String action = r.getCell(3).getStringCellValue();
            String bought = r.getCell(4).getStringCellValue();
            double costDoub = r.getCell(5).getNumericCellValue();
            double rentDoub = r.getCell(6).getNumericCellValue();
            ArrayList<Integer> houses = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                double houseDoub = r.getCell(7 + i).getNumericCellValue();
                int houseInt = (int) houseDoub;
                houses.add(houseInt);
            }
            int position = (int) positionDoub;
            int cost = (int) costDoub;
            int rent = (int) rentDoub;
            boolean canBeBought = true;
            if (bought.equalsIgnoreCase("No")) {
                canBeBought = false;
            }
            //System.out.println(position + " " + name + " " + group + " " + action + " " + bought + " " + cost + " " + rent + " " + houses);
            PropertyCards pc = new PropertyCards(name, group, action, canBeBought, cost, rent, houses);
            b.add(pc);
        }

        return b;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Parser p = new Parser();
        System.out.print(p.boardMaker());
    }

}
