package ecomm.generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public static XSSFWorkbook wb;

    public static XSSFSheet sh;

    public static String getCellValue(String filepath, int sheetIndex, int row, int coloumn) {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(filepath);
            wb = new XSSFWorkbook(fis);
            DataFormatter formatter = new DataFormatter();
            str = formatter.formatCellValue(wb.getSheetAt(sheetIndex).getRow(row).getCell(coloumn));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    public static Object[][] getData(String filepat, int sheetindex) {

        Object rowcell[][] = null;

        try {
            FileInputStream fis = new FileInputStream(filepat);
            wb = new XSSFWorkbook(fis);
            int rowcount = wb.getSheetAt(sheetindex).getLastRowNum();
            int cellcount = wb.getSheetAt(sheetindex).getRow(0).getLastCellNum();
            rowcell = new Object[rowcount][cellcount];
            for (int i = 0; i < rowcount; i++) {
                for (int j = 0; j < cellcount; j++) {
                    rowcell[i][j] = getCellValue(filepat, sheetindex, i + 1, j);
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowcell;

    }
}