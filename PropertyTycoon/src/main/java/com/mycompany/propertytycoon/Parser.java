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

    public final String board = "./src/main/resources/PropertyTycoonBoardData.xlsx";

    public ArrayList<PropertyCards> boardMaker() throws IOException, InvalidFormatException {
        ArrayList<PropertyCards> b = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new File(board));
        Sheet s = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = s.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            if (r.getRowNum() == 0 || r.getRowNum() == 1 || r.getRowNum() == 2 || r.getRowNum() == 3) {
                continue;
            }
            if (r.getRowNum() > 43) {
                break;
            }
            double positionDoub = r.getCell(0).getNumericCellValue();
            String name = r.getCell(1).getStringCellValue();
            String group = "";
            if (r.getCell(3) == null) {
            } else {
                group = r.getCell(3).getStringCellValue();
            }
            String action = "";
            if (r.getCell(4) == null) {
            } else {
                action = r.getCell(4).getStringCellValue();
            }
            String bought = r.getCell(5).getStringCellValue();
            double costDoub = 0;
            if (r.getCell(7) == null) {
            } else {
                costDoub = r.getCell(7).getNumericCellValue();
            }
            String rent = "0";
            if (r.getCell(8).getCellType() == CellType.STRING && r.getCell(8).getStringCellValue().equalsIgnoreCase("see notes")) {
                if (r.getCell(3).getStringCellValue().equalsIgnoreCase("utilities")) {
                    rent = workbook.getSheetAt(0).getRow(46).getCell(0).getStringCellValue() + "\n" + workbook.getSheetAt(0).getRow(47).getCell(0).getStringCellValue();
                } else {
                    rent = workbook.getSheetAt(0).getRow(48).getCell(0).getStringCellValue() + "\n" + workbook.getSheetAt(0).getRow(49).getCell(0).getStringCellValue() + "\n" + workbook.getSheetAt(0).getRow(50).getCell(0).getStringCellValue() + "\n" + workbook.getSheetAt(0).getRow(51).getCell(0).getStringCellValue();
                }
            } else {
                double rentDoub = r.getCell(8).getNumericCellValue();
                int rentInt = (int) rentDoub;
                rent = Integer.toString(rentInt);
            }
            ArrayList<Integer> houses = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                if (r.getCell(8) == null) {
                    houses.add(0);
                } else {
                    double houseDoub = r.getCell(10 + i).getNumericCellValue();
                    int houseInt = (int) houseDoub;
                    houses.add(houseInt);
                }
            }
            int position = (int) positionDoub;
            int cost = (int) costDoub;
            boolean canBeBought = true;
            if (bought.equalsIgnoreCase("No")) {
                canBeBought = false;
            }
            int houseCost = 0;
            if (r.getCell(3) == null) {

            } else {
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
            }
            //System.out.println(position + " " + name + " " + group + " " + action + " " + bought + " " + cost + " " + rent + " " + houses);
            PropertyCards pc = new PropertyCards(name, group, action, canBeBought, cost, rent, houses, houseCost);
            b.add(pc);
        }

        return b;
    }
}
