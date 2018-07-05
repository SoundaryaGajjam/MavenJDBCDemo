package com.scp.maven.jdbc.Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.prism.impl.shape.OpenPiscesRasterizer;

public class ExcelWriterDemo {
	static String []columns={"Id","Name","DOB"};
	static List<Employee>listOfEmp=new ArrayList<>();
	static Workbook wb=null;
	static Sheet sheet=null;
	static Row row=null;
	static{
		Calendar DOB=Calendar.getInstance();
		DOB.set(1989, 05, 12);
	
		listOfEmp.add(new Employee(101, "Smith", DOB.getTime()));
		
		DOB.set(1991,11,04);
		listOfEmp.add(new Employee(102, "John", DOB.getTime()));
		
		DOB.set(1983,9,30);
		listOfEmp.add(new Employee(103, "Ashu", DOB.getTime()));
		
		
		
	}
	public static void main(String[] args) throws IOException, InvalidFormatException {
		//creatingNewWorkbook();
		modifingExistingWorkbook();
		System.out.println("Successfully Emp info is written into Excel file");
		
	}
	private static void modifingExistingWorkbook() throws InvalidFormatException, IOException {
		//File file=new File("C:\\Users\\Shree\\Desktop\\EmployeeInfo.xlsx");
		//FileInputStream fis=new FileInputStream(file);
		
		//OPCPackage opc=OPCPackage.open(fis);
		 wb=WorkbookFactory.create(new FileInputStream(new File("C:\\Users\\Shree\\Desktop\\EmployeeInfo.xlsx")));
		sheet=wb.getSheetAt(0);
		row=sheet.getRow(2);
		Cell cell=row.getCell(1);
		
		
		//cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("Radha");
		
		FileOutputStream fos=new FileOutputStream(new File("C:\\Users\\Shree\\Desktop\\EmployeeInfo.xlsx"));
		wb.write(fos);
		fos.close();
		//opc.close();
		
	}
	private static void creatingNewWorkbook() throws IOException {
		wb=new XSSFWorkbook();
		CreationHelper hlper=wb.getCreationHelper();
		
		sheet=wb.createSheet();
		
		CellStyle dateCell=wb.createCellStyle();
		dateCell.setDataFormat(hlper.createDataFormat().getFormat("dd/mm/yyyy"));
		
		Row headerRow=sheet.createRow(0);
		for(int i=0;i<columns.length;i++){
			Cell cell=headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int rowNum=1;
		for (Employee item : listOfEmp) {
			row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(item.getEmpId());
			row.createCell(1).setCellValue(item.getEmpName());
			Cell dateOfBirth=row.createCell(2);
			dateOfBirth.setCellValue(item.getDOB());
			dateOfBirth.setCellStyle(dateCell);
			
		}
		FileOutputStream fos=new FileOutputStream(new File("C:\\Users\\Shree\\Desktop\\EmployeeInfo.xlsx"));
		wb.write(fos);
		fos.close();
		
		
	}
}
class Employee{
	int empId;
	String empName;
	Date DOB;
	public Employee(int empId, String empName, Date dOB) {
		super();
		this.empId = empId;
		this.empName = empName;
		DOB = dOB;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	
	
}