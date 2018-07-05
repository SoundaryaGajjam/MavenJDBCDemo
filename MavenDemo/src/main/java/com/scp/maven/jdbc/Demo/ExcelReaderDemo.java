package com.scp.maven.jdbc.Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Iterator;

import javax.swing.text.DateFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderDemo {
	public static void main(String[] args) throws IOException {
		File file=new File("C:\\Users\\Shree\\Desktop\\EmployeeInfo.xlsx");
		FileInputStream fis=new FileInputStream(file);
		Workbook wb=new XSSFWorkbook(fis);
		Sheet firstSheet=wb.getSheetAt(0);
		DateFormatter dtFrmt=new DateFormatter();
		
		Iterator<Row>itr=firstSheet.iterator();
		while(itr.hasNext()){
			Row nxtRow=itr.next();
			Iterator<Cell> citr=nxtRow.cellIterator();
			while(citr.hasNext()){
				Cell cell=citr.next();
				switch(cell.getCellType()){
				case Cell.CELL_TYPE_STRING:
											System.out.print(cell.getStringCellValue());
											break;
				case Cell.CELL_TYPE_NUMERIC:
											if(DateUtil.isCellDateFormatted(cell))
											System.out.println(cell.getDateCellValue());
											else
											System.out.print(cell.getNumericCellValue());
											break;
				
				
								
				}
				System.out.print("\t");
			}
			System.out.println();
		}
		
	}
}
