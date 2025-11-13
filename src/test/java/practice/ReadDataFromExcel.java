package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
//Create java representation
		FileInputStream fis = new FileInputStream("C:\\Users\\savisaro\\Documents\\NINZA_CRM.xlsx");
		//Open the excel in read mode
		Workbook wb = WorkbookFactory.create(fis);
		//get the control of the sheet
		Sheet sh = wb.getSheet("Campaigns");
		//get the control of the row
		Row r = sh.getRow(1);
		//get the control of the cell
		Cell c = r.getCell(2);
		//read the data
		String campaignName = c.getStringCellValue();
		System.out.println(campaignName);
		String targetSize = wb.getSheet("Campaigns").getRow(1).getCell(3).getStringCellValue();
		System.out.println(targetSize);
		//close the workbook
		wb.close();			
	}

}
