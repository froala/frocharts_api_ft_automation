package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class GetSetCurrentBin extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "getSetCurrentBin()"; 
	Object[][] data;
	APIPageObjectModel pom;
	
	@BeforeTest
	public void setUp() 
	{
		api = new APITestBase();
		api.initialize();
		pom = new APIPageObjectModel();
		data = TestUtil.getTestData();
		boolean chartExists = TestUtil.thisDataexists(data, chart);
		Assert.assertTrue(chartExists, "Chart html exists");
		String htmlData = TestUtil.chartHtml(data, chart);
		TestUtil.htmlWrite(htmlData);
		driver.navigate().refresh();
	}
	
	@Test()
	public void getSetCurrentBin() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		Actions action = new Actions (driver);
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		String unit = (String) js.executeScript("return tsChart.getCurrentBin().unit");
		long multiplier = (long) js.executeScript("return tsChart.getCurrentBin().multiplier");
		
		Assert.assertTrue(unit.equals("day"),"unit should be day");
		Assert.assertTrue(multiplier==2,"multiplier should be 2");
		
		action.moveToElement(pom.canvas1()).build().perform();
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("getSetCurrentBin date format tooltip should be 2 dates range")));
		action.moveByOffset(20, 0).build().perform();
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("getSetCurrentBin date format tooltip should be 2 dates range_2")));
		
		js.executeScript("tsChart.setCurrentBin({\n" + 
				"            \"unit\": \"day\",\n" + 
				"            \"multiplier\": \"1\"\n" + 
				"          });");
		
		unit = (String) js.executeScript("return tsChart.getCurrentBin().unit");
		multiplier = (long) js.executeScript("return tsChart.getCurrentBin().multiplier");
		
		Assert.assertTrue(unit.equals("day"),"unit should be day");
		Assert.assertTrue(multiplier==1,"multiplier should be 1");
		
		//js.executeScript("window.scrollBy(0,1000)");
		action.moveToElement(pom.getSvg()).build().perform();
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("getSetCurrentBin date format tooltip should be 1 date range")));
		action.moveByOffset(20, 0).build().perform();
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("getSetCurrentBin date format tooltip should be 1 date range_2")));
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("getSetCurrentBin() executed");
			Thread.sleep(3000);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
