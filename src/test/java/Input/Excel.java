package Input;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Library.CommonLib;
 
//import gherkin.formatter.model.Row;

public class Excel {
	
    private static String worksheet = System.getProperty("user.dir")+"/test-data/" + (CommonLib.getValueFromProperty("worksheet"));
    static HashMap<String, String> excelinfo = new HashMap<String, String>();

     public static String get(String ColName, String row, String sheetName) throws IOException {
    	FileInputStream fis;
    	int k =0;
    	String ColValue=null;
    	fis = new FileInputStream(worksheet);
    	XSSFWorkbook wb = new XSSFWorkbook(fis);
    	XSSFSheet ws = wb.getSheet(sheetName);
//    	int rows = ws.getPhysicalNumberOfRows();
    	Integer rowCount = Integer.valueOf(row);
    	XSSFRow HeaderRow = ws.getRow(0);
    	for(Integer i=1;i<=rowCount;i++){
    		int cols = ws.getRow(0).getPhysicalNumberOfCells();
    		for (Integer j = 0; j < cols; j++) {
//    			 ColName=HeaderRow.getCell(j).getStringCellValue();
//    			ColValue=ws.getRow(i).getCell(j).getStringCellValue();
    			XSSFCell cell = ws.getRow(i).getCell(j);
    			switch(cell.getCellType())
				{
    			case STRING: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),cell.getStringCellValue()); break;
    			case NUMERIC: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),String.valueOf((int)cell.getNumericCellValue())); break;
    			case BOOLEAN: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),String.valueOf(cell.getBooleanCellValue())); break;
    			case FORMULA: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),cell.getCellFormula()); break;
    			case BLANK: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),cell.getStringCellValue()); break;
    			case ERROR: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),String.valueOf(cell.getErrorCellValue())); break;
    			case _NONE: excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),String.valueOf(cell.getStringCellValue())); break;
				}
//    			excelinfo.put(HeaderRow.getCell(j).getStringCellValue(),ws.getRow(i).getCell(j).getStringCellValue());
    			}
    		for(Map.Entry<String, String> m : excelinfo.entrySet()) {
    			if(m.getKey().equals(ColName)) {
    				ColValue= m.getValue();
    				}
    			}
    		}
    	return ColValue;
    	}
	
}
