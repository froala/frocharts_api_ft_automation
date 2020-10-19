package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.ScriptData;
import com.froalacharts.util.TestUtil;

public class GetObjectReference extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "getObjectReference()"; 
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
	public void getObjectReference() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		Actions action = new Actions(driver);
		
		boolean svgExistsBefore = pom.svgDisplayed();
		action.moveToElement(driver.findElement(By.id("btn"))).click().build().perform();
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		boolean svgExistsAfter = pom.svgDisplayed();
		
		Assert.assertTrue(svgExistsBefore,"svg exists");
		Assert.assertTrue(!svgExistsAfter,"svg does not exist");
		
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("getObjectReference() executed");
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
