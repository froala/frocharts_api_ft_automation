package com.froalacharts.apitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

public class GetChartData extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "getChartData()"; 
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
	public void getChartData() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		String caption = (String) js.executeScript("return tsChart.getChartData().caption.text");
		long chart = (long) js.executeScript("return tsChart.getChartData().chart.exportenabled");
		String data = (String) js.executeScript("return tsChart.getChartData().data._data[0][0]");
		String subcaption = (String) js.executeScript("return tsChart.getChartData().subcaption.text");
		String yaxis = (String) js.executeScript("return tsChart.getChartData().yaxis[0].series");
		
		
		Assert.assertTrue(caption.equals("I am a caption"),"getChartData caption");
		Assert.assertTrue(chart==1,"getChartData chart exportenabled");
		Assert.assertTrue(data.equals("US"),"getChartData data is US");
		Assert.assertTrue(subcaption.equals("I am a subcaption"),"getChartData subcaption");
		Assert.assertTrue(yaxis.equals("Country"),"getChartData yaxis series");
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("getChartData() executed");
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
