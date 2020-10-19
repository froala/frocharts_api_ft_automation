package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;

public class Dispose extends APITestBase{
	//The api name according to the data sheet
	private final static String chart = "dispose()"; 
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
	public void dispose() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		js.executeScript("tsChart.dispose();");
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		Assert.assertTrue(!pom.svgExists(),"chart disposed");
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("dispose() executed");
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
