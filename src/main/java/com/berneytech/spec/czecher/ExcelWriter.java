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
    public List<List<String>> SensorInfo;
    private String[] Headers;
    public ExcelWriter(List<List<String>> HardwareInfo){
        this.HardwareInfo=HardwareInfo;
        Headers= new String[HardwareInfo.size()];
    }

    public void createSheet() throws IOException, InvalidFormatException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Spec-Czecher");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create Other rows and cells
        int rowNum = 0;
        for(List<String> s: HardwareInfo) {
            Row row = sheet.createRow(rowNum++);
                for (int x=0; x<s.size(); x++)
                    row.createCell(x).setCellValue(s.get(x));
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < Headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
}