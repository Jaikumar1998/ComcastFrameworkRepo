package com.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName, int rowNum, int celNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./Testdata/Org.xlsx");
		Workbook  wb = WorkbookFactory.create(fis);
	   String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
	   wb.close();
	   return data;
	}
	
	public int getrowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./Testdata/Org.xlsx");
		Workbook  wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	public void setValueIntoExcel(String sheetName, int rowNum, int celNum, String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./Testdata/Org.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
		
		FileOutputStream fos = new FileOutputStream("./Testdata/Org.xlsx");
		wb.write(fos);
		wb.close();
		 
	}
	
}