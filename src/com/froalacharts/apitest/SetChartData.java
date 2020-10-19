package com.froalacharts.apitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.ScriptData;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class SetChartData extends APITestBase{
	//The api name according to the data sheet
	private final static String chart = "setChartData()"; 
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
	public void setChartData() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		Assert.assertTrue(pom.isColumnPlotPresentIn(pom.canvas1()) == true,"column present before setChartData");
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		js.executeScript(ScriptData.chartData);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		Assert.assertTrue(pom.isColumnPlotPresentIn(pom.canvas1()) == false,"column absent after setChartData");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("chart with no column and first plot area")));
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("setChartData() executed");
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
