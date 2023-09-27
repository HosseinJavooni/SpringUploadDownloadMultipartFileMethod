package com.example.SpringUploadDownloadMultipartFileMethod.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;

public class ExcelFilesUtil {
    public static boolean checkTheExcelHeaders(List<String> headerTemplate, String fileCode) {
        try {
                FileInputStream fileInputStream = findAExcelFile(fileCode);
                XSSFSheet sheet = getSheetOfAnExcelFile(fileInputStream, 0);
                List<String> rowList = getARowOfASheet(sheet, 0);
                return new HashSet<>(rowList).containsAll(headerTemplate);
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static FileInputStream findAExcelFile(String fileCode) throws IOException {
        Path path = Paths.get("src/Files-Excel");
        Optional<Path> pathOfExcel = Files.list(path).filter(file ->
                file.getFileName().toString().startsWith(fileCode)
        ).findFirst();
        FileInputStream fileInputStream = null;
        if(pathOfExcel.isPresent()){
            File file = new File(pathOfExcel.get().toUri());
             fileInputStream= new FileInputStream(file);
        }
        return fileInputStream;
    }

    public static XSSFSheet getSheetOfAnExcelFile(FileInputStream fileInputStream, int sheetNumber) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        return workbook.getSheetAt(sheetNumber);
    }

    public static List<String> getARowOfASheet(XSSFSheet sheet, int rowNumber){
        Row row = sheet.getRow(rowNumber);
        Iterator<Cell> cellIterator = row.cellIterator();
        List<String> rowContentList = new Vector<>();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            //Check the cell type and format accordingly
            switch (cell.getCellType()) {
                case NUMERIC:{
                    rowContentList.add(String.valueOf(cell.getNumericCellValue()));
                    System.out.print(cell.getNumericCellValue() + "\n");
                    break;
                }
                case STRING:{
                    rowContentList.add(cell.getStringCellValue().trim());
                    System.out.print(cell.getStringCellValue().trim() + "\n");
                    break;
                }
                case BOOLEAN:{
                    rowContentList.add(String.valueOf(cell.getBooleanCellValue()));
                    break;
                }
                default:
                    //_NONE(-1)
                    //BLANK(3)
                    //ERROR(5)
                    //FORMULA(2)
                    rowContentList.add("");
            }
        }
        return rowContentList;
    }
}
