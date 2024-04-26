package com.venu.Hybrid.UtilsCommon;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class CommonUitilities {
	
	public final static  int Implicit_Wait_time=20;
	public final static int Page_load_time=15;

	public static String GenerateTimeStampWithEmail() {

		Date currentTimestamp = new Date();

		String newTimeStamp = currentTimestamp.toString().replace(" ", "_").replace(":", "_");
		 
		 return "test" +newTimeStamp+"@yopmail.com";
	}
	
	public static Object [][] getTestDataFromExcel(String SheetName) {
		
		File myExcelData=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\config\\HybridFrameWork_TestData.xlsx");
		
		Workbook wb=null;
		Sheet sheet=null;
		Row row=null;
		Cell cell=null;
		
		try {
			
			FileInputStream ReadDataSheet = new FileInputStream(myExcelData);
			wb= new XSSFWorkbook(ReadDataSheet);
			
		}catch(Exception E) {
			E.printStackTrace();
		}
		sheet=wb.getSheet(SheetName);
		
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			row=sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
			cell=row.getCell(j);
				
			CellType cellType = cell.getCellType();

			switch (cellType) {
			    case STRING:
			        data[i][j] = cell.getStringCellValue();
			        break;

			    case NUMERIC:
			        data[i][j] = String.valueOf((int) cell.getNumericCellValue());
			        break;

			    case BOOLEAN:
			        data[i][j] = cell.getBooleanCellValue();
			        break;

			    default:
			        break;
			}

			}
		}
		return data;
	}
	
	public static String CaptureScreenshot(WebDriver driver, String TestName) {
		String DestScreeshotpath = null;
		try {
				
			File SrcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DestScreeshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+TestName+".png";
			
			FileHandler.copy(SrcScreenshot, new File(DestScreeshotpath));
		} catch (Exception e) {
				e.printStackTrace();
		}
		return DestScreeshotpath;
	}
	

}
