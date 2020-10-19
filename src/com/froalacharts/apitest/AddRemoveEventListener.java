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
import com.relevantcodes.extentreports.LogStatus;

public class AddRemoveEventListener extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "addRemoveEventListener()"; 
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
	public void addRemoveEventListener() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		Actions action = new Actions(driver);
		String bgColor = "";
		String bgColorPrev = "";
		int ctr = 0;
		
		js.executeScript(ScriptData.addEventListener);
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		WebElement columnPlotParent = driver.findElement(By.xpath("//*[contains(@class,'column-plot-meso')]"));
		List<WebElement> columnPlots = columnPlotParent.findElements(By.tagName("rect"));
		
		for(WebElement plot:columnPlots)
		{
			action.moveToElement(plot).click().build().perform();
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
			bgColor = (String) js.executeScript("return document.body.style.backgroundColor");
			
			Assert.assertTrue(bgColor.equals("rgb(255, 0, 0)")||bgColor.equals("rgb(0, 0, 255)"),"addEventListener working");
			Assert.assertTrue(!bgColor.equals(bgColorPrev),"addEventListener working");
			
			bgColorPrev=bgColor;
		}
		
		
		
		js.executeScript(ScriptData.removeEventListener);
		
		for(WebElement plot:columnPlots)
		{
			action.moveToElement(plot).click().build().perform();
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
			bgColor = (String) js.executeScript("return document.body.style.backgroundColor");
			
			Assert.assertTrue(bgColor.equals("rgb(255, 0, 0)")||bgColor.equals("rgb(0, 0, 255)"),"removeEventListener working");
			
			if(ctr>=1)
			{
				Assert.assertTrue(bgColor.equals(bgColorPrev),"removeEventListener working");
			}
			
			bgColorPrev=bgColor;
			
			ctr++;
		}
		
		
		
		Assert.assertTrue(true,"column absent after setChartData");
		
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("addRemoveEventListener() executed");
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
