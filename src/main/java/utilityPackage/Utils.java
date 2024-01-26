package utilityPackage;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utils {

	String path ;
	FileInputStream fis;
	Workbook workbook;
	Sheet sheet;
	Row row;
	Cell cell;
	
	
	public Utils(String path) {
		this.path = path;
	}
	
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
		
		fis =  new FileInputStream(path);
		workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum() ; // getLastRowNum ----> starting from zero
		workbook.close();
		fis.close();
		return rowcount;
		
	}
	
	public int getCellCount(String sheetname, int rownum) throws EncryptedDocumentException, IOException {
		
		fis =  new FileInputStream(path);
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount =	row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
	}
	
	public String getCellDataString (String sheetname, int rownum, int colnum) throws EncryptedDocumentException, IOException {
		
		fis =  new FileInputStream(path);
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell =	row.getCell(colnum) ;
		
		//String data = cell.getStringCellValue();
		
		DataFormatter formatter = new DataFormatter();
		
		String data;
		try {
		data = formatter.formatCellValue(cell); //Returns the Formatted value of cell as a String regardless of the CellType  i.e. it may be String CellType, Numeric CellType and Boolean CellType....
		}
		catch (Exception e){
			data = "";
		}
		
		
		workbook.close();
		fis.close();
		
		return data;
		
	}
 	
	
	
	
}
