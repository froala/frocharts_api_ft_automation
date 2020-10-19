package com.froalacharts.apitest;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;

public class GetSetTimeSelection extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "getSetTimeSelection()"; 
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
	public static void getSetTimeSelection()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		long startTime = (long) js.executeScript("return tsChart.getTimeSelection().start");
		long endTime = (long) js.executeScript("return tsChart.getTimeSelection().end");
		
		Assert.assertTrue(startTime<endTime, "getTimeSelection is working");
		
		js.executeScript("return tsChart.getTimeSelection();");
		
		js.executeScript("tsChart.setTimeSelection({start:345730046344, end:346139622984})");
		
		long startTimeSet = (long) js.executeScript("return tsChart.getTimeSelection().start");
		long endTimeSet = (long) js.executeScript("return tsChart.getTimeSelection().end");
		
		
		Assert.assertTrue(startTimeSet==345730046344L, "setTimeSelection is working");
		Assert.assertTrue(endTimeSet==346139622984L, "setTimeSelection is working");
	}
	
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("getSetTimeSelection() executed");
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
