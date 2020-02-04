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
import java.util.Iterator;
/**
 *
 * @author ankeet
 */
public class Parser {
    public static final String board = "./sample-xlsx-file.xlsx";
    
    public void boardMaker(String boardLoc) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(board));
        for(Sheet sheet: workbook) {
            for (Row row: sheet) {
                PropertyCards pc = new PropertyCards(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue(), boardLoc, true, 0, 0, houses)
            }
        }
    }
    
}
