package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBacktoExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\savisaro\\Documents\\NINZA_CRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Practice");
		Row r = sh.getRow(1);
		Cell c = r.createCell(1, CellType.STRING);
		c.setCellValue("IPhone15");
		FileOutputStream fos = new FileOutputStream("C:\\\\Users\\\\savisaro\\\\Documents\\\\NINZA_CRM.xlsx");
		wb.write(fos);
		wb.close();
	}

}
