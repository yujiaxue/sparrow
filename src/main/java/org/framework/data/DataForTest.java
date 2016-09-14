package org.framework.data;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class DataForTest {

	public static void main(String[] args) {
		aa();
	}
	
	
	//@DataProvider(name = "data", parallel = false)
	public ExcelDataDriven data4normalProcess() throws Exception {
		return new ExcelDataDriven(this, "");
	}
	
	public static void aa() {
		Workbook w = null;
		try {
			w = WorkbookFactory.create(new File("/Users/zhangfujun/Documents/seleniumWorkspace/sparrowDemo/data/RentHouse.xlsx"));
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet s = w.getSheet("normalProcess");
		System.out.println(s.getLastRowNum());
		System.out.println(s.getPhysicalNumberOfRows());
		Row row = s.getRow(1);
		System.out.println(row.getPhysicalNumberOfCells());
		int aa =0;
		while(row.getCell(aa) != null){
			aa++;
		}
		
		for(int i=0;i<aa;i++){
			System.out.println(row.getCell(i).getStringCellValue());
			System.out.println("a");
		}
		System.out.println("ok");
		try {
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
