package com.berneytech.spec.czecher;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;

/**
 *
 * @author liamrberney
 */
public class ExcelWriter {
    
    public List<List<String>> HardwareInfo;
    private final String[] Headers;
    public ExcelWriter(List<List<String>> HardwareInfo){
        this.HardwareInfo=HardwareInfo;
        Headers= new String[HardwareInfo.size()];
    }

    public void createSheet() throws IOException, InvalidFormatException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Spec-Czecher");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        CellStyle style = workbook.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        // Create Other rows and cells 
        int rowNum = 0;
        for(List<String> s: HardwareInfo) {
            if(s.get(0)!=null||!s.get(0).equals("")){
                Row row = sheet.createRow(rowNum++);
                int offset = 0;
                for (int x=0; x<s.size(); x++){
                    row.createCell(x-offset).setCellValue(s.get(x));
                    if (x%4==0 && x != 0){
                        row = sheet.createRow(rowNum++);
                        offset = x;
                    }
                    if (x==0&&row.getCell(x)!=null)
                        row.getCell(x).setCellStyle(headerCellStyle);
                } 
            }
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < Headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        for(int i = 0; i < sheet.getLastRowNum(); i++){
            if(isEmpty(sheet.getRow(i))){
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                i--;
            }
        }
        for(int i=0 ;i<sheet.getLastRowNum();i++){
            if (i%2==0)
                sheet.getRow(i).setRowStyle(style);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
    
    public static boolean isEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }
}