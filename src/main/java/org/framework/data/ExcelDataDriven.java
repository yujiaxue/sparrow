package org.framework.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.base.Converter;

public class ExcelDataDriven implements Iterator<Object[]> {
	Logger logger = LogManager.getLogger(ExcelDataDriven.class.getName());
	private String excel;
	private String sheet;

	private Sheet mysheet;

	private int rowIndex = 0;
	private int columnIndex = 0;
	private int datasize;
	String[] rowname ;
	InputStream in;

	public ExcelDataDriven(Object classes, String method) throws Exception {
		Method m = AnnotationMethod(classes, method);
		if (m == null) {
			throw new Exception();
		}
		setFile(classes, m.getName());
		openSheet();
		datasize = mysheet.getLastRowNum();
		getRowname();
	}

	public void getRowname() {
		Row row = mysheet.getRow(rowIndex);
		rowIndex++;
		while (row.getCell(this.columnIndex) != null && row.getCell(this.columnIndex).getCellType() != Cell.CELL_TYPE_BLANK) {
			this.columnIndex++;
		}
		rowname = new String[this.columnIndex];
		for (int i = 0; i < this.columnIndex; i++) {
			//if (row.getCell(i).toString().contains("\n")) {
				rowname[i] = StringUtils.remove(row.getCell(i).toString(), "\n");
			//} else {
			//	rowname[i] = row.getCell(i).toString();
			//}
		}
	}

	private void openSheet() throws FileNotFoundException {
		try {
			in = new FileInputStream(excel);
			try {
				Workbook workbook = WorkbookFactory.create(in);
				mysheet = workbook.getSheet(sheet);
			} catch (EncryptedDocumentException | InvalidFormatException | NullPointerException | IOException e) {
				try {
					if (null != in) {
						in.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			logger.info("file is not exists " + excel);
			throw new FileNotFoundException();
		}
	}

	public void setFile(Object classes, String method) {
		String root = System.getProperty("selenium.dataroot");
		if (!root.endsWith("/")) {
			root = root.concat("/");
		}
		excel = new StringBuilder(root).append(classes.getClass().getName().replaceAll("\\.", File.separator))
				.append(".xlsx").toString();
		sheet = method;
	}

	public Method AnnotationMethod(Object classes, String method) {
		Method[] methods = classes.getClass().getDeclaredMethods();
		for (Method m : methods) {
			if (m.getName().equals(method)) {
				return m;
			}
		}
		return null;
	}

	public boolean hasNext() {
		if (rowIndex <= datasize) {
			return true;
		} else {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public Object[] next() {
		HashMap<String, String> data = new HashMap<String, String>();
		Row row = mysheet.getRow(rowIndex);
		for (int i = 0; i < columnIndex; i++) {
			if(row.getCell(i) == null){
				data.put(rowname[i], null);
			}else{
				data.put(rowname[i], row.getCell(i).toString());
			}
//			if (row.getCell(i).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			
//			}
//			if (row.getCell(i).getCellType() == Cell.CELL_TYPE_STRING) {
//				data.put(rowname[i], row.getCell(i).getStringCellValue());
//			}
		}

		Object[] o = new Object[1];
		o[0] = data;
		rowIndex++;
		return o;
	}

}
