package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericUtilities.ExcelFileUtility;

public class ReadMultippleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		ExcelFileUtility eLib = new ExcelFileUtility();
		int rowCount = eLib.getRowCount("Practice");
		
		for (int row=1;row<=rowCount;row++) 
		{
			String Data = eLib.readDataFromExcelFile("Practice", row, 0);
			System.out.println(Data);
		}
			
	}

}
