package com.froalacharts.apitest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;

public class LockResize extends APITestBase{

	//The api name according to the data sheet
	private final static String chart = "lockResize()"; 
	Object[][] data;
	static APIPageObjectModel pom;
	
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
	public static void lockResize()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("tsChart.lockResize()");
		Actions action = new Actions(driver);
		
		
		long width = (long) js.executeScript("return tsChart.apiInstance.getFromEnv('chartWidth')");
		long height = (long) js.executeScript("return tsChart.apiInstance.getFromEnv('chartHeight')");
		
		int widthInt = (int) width;
		int heightInt = (int) height;
		
		action.moveToElement(pom.getSvg()).build().perform();
		
		action.moveByOffset(widthInt/2, 0).build().perform();
		
		action.clickAndHold().build().perform();
		action.moveByOffset(150,0).build().perform();
		action.release().build().perform();
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		long newWidth = (long) js.executeScript("return tsChart.apiInstance.getFromEnv('chartWidth')");
		int newWidthInt = (int) newWidth;
		Assert.assertTrue(newWidthInt!=widthInt, "Chart should get resized");
		
		jsExecuteWithBuffer("tsChart.lockResize(true)");
		
		action.clickAndHold().build().perform();
		action.moveByOffset(150,0).build().perform();
		action.release().build().perform();
		
		long afterLockWidth = (long) js.executeScript("return tsChart.apiInstance.getFromEnv('chartWidth')");
		int afterLockWidthInt = (int) afterLockWidth;
		
		Assert.assertTrue(newWidthInt==afterLockWidthInt, "Chart should not get resized");
		
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("lockResize() executed");
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
