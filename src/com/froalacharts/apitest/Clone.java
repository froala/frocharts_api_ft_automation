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
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class Clone extends APITestBase{
	//The api name according to the data sheet
	private final static String chart = "clone()"; 
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
	public void cloneAPI() throws IOException
	{
		BufferedImage mainCanvasRef = null;
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
		
		Assert.assertTrue(pom.svgCount()==1,"chart not cloned - 1 chart");
		
		driver.findElement(By.id("exact_copy")).click();
		
		Assert.assertTrue(pom.svgCount()==2,"chart cloned - 2 charts");
		
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("two similar charts - 2nd one cloned")));
		
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("clone() executed");
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
