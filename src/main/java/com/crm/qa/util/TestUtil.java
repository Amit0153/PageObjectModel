package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;
import com.google.common.io.Files;

public class TestUtil extends TestBase
{
	public static long PAGE_LOAD_TIMEOUT =30;
	public static long IMPLICIT_WAIT =20;
	
	public static String TESTDATA_SHEET_PATH = "E:/Automation Start After Format/Workspace/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCRMTestData.xlsx";
	static Workbook book;
	static Sheet sheet;

	public void switchToFrame(){
		driver.switchTo().frame("mainpanel");
	}
	
	public static Object[][] getTestData(String sheetName){
		FileInputStream file=null;
		try
		{
			file=new FileInputStream(TESTDATA_SHEET_PATH);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			book = WorkbookFactory.create(file);
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		//System.out.println(sheet.getLastRowNum() + "----" + sheet.getRow(0).getLastCellNum());
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			for (int k=0; k<sheet.getRow(0).getLastCellNum(); k++)
			{
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
				//System.out.println(data[i][k]);
			}
		}
		
		return data;
	}
	
	
	
	public static void takeScreenshotAtEndOfTest() {
		/*File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyfile(srcFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));*/
		TakesScreenshot ts = (TakesScreenshot)driver ;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/" + System.currentTimeMillis() + ".png" );
		try {
			Files.copy(srcFile,destFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
	
		//My experiment with Excel Loop
		/*public String readDataLoop() 
		{
			String value;
			//File file=new File("C:/Users/Owner/Desktop/datatest.xlsx");
			FileInputStream fis=new FileInputStream(TESTDATA_SHEET_PATH);
			
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet("contacts");
			int rowCount = sh.getLastRowNum();  // getLastRowNum() gives one rowCount less--> becoz 1st row we always use as a header
			System.out.println("Total rows: "+rowCount);
			
			for (int i = 0; i <=rowCount; i++) 
			{
				Row rw = sh.getRow(i);
				int cellCount = rw.getLastCellNum();
				System.out.println("Row: "+i+" total cells: "+cellCount);
				
				for (int j = 0; j < cellCount; j++) 
				{
					Cell cl = rw.getCell(j);
					String value = cl.getStringCellValue();
					System.out.println(value);
				}
			}
		
			return value;
			}*/
	
	
		
		
		
		
		
	
	
	
	
	
	

