package com.froalacharts.apitest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.ScriptData;
import com.froalacharts.util.TestUtil;

public class GetSVGString extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "getSVGString()"; 
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
	public void getSVGString() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		String svgData = (String) js.executeScript("return tsChart.getSVGString()");
		
		Assert.assertTrue(svgData.startsWith("<svg"),"getSVGString format is correct");
		Assert.assertTrue(svgData.endsWith("</svg>"),"getSVGString format is correct");
		
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("getSVGString() executed");
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
