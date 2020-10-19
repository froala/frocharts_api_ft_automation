package com.froalacharts.apitest;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;

public class ResizeTo extends APITestBase{
	//The api name according to the data sheet
	private final static String chart = "resizeTo()"; 
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
	public static void resizeTo()
	{
		long height = 600;
		long width = 400;
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("tsChart.resizeTo("+width+","+height+")");
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		
		long chartWidth = (long) js.executeScript("return tsChart.apiInstance.getFromEnv('chartWidth')");
		long chartHeight = (long) js.executeScript("return tsChart.apiInstance.getFromEnv('chartHeight')");
		Assert.assertTrue(chartWidth==width, "Width is "+width);
		Assert.assertTrue(chartHeight==height, "height is "+height);
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("resizeTo() executed");
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
