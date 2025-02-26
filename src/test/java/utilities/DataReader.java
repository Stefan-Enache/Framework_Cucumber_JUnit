package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

    public static List<HashMap<String, String>> data(String filePath, String sheetName) {

        List<HashMap<String, String>> myData = new ArrayList<>();

        try {
            FileInputStream fileInput = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row currentRow = sheet.getRow(i);
                HashMap<String, String> currentHash = new HashMap<String, String>();
                for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                    Cell currentCell = currentRow.getCell(j);
                    DataFormatter formatter = new DataFormatter();
                    String data;
                    try {
                        data = formatter.formatCellValue(currentCell);
                    } catch (Exception e) {
                        data = "";
                    }
                    currentHash.put(headerRow.getCell(j).getStringCellValue(), data);
                }
                myData.add(currentHash);
            }
            fileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myData;
    }

}