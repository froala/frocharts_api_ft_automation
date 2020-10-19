package com.froalacharts.apitest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ShowHideChartMessage extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "showHideChartMessage()"; 
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
	public void showHideChartMessage() throws IOException
	{
		String msg = "Test Message";
		WebElement btnShowMessage = driver.findElement(By.id("show_message"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		driver.findElement(By.id("chart_message")).sendKeys(msg);
		Select mode =new Select(driver.findElement(By.id("mode")));
		
		mode.selectByValue("overlaycan");
		btnShowMessage.click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		mode.selectByValue("overlay");
		btnShowMessage.click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(!pom.msgGroupRect().getAttribute("style").contains("display: none;"),"showChartMessage() rect is displayed");
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("overlay message on chart with less opacity")));
		
		
		mode.selectByValue("onchart");
		btnShowMessage.click();
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		Assert.assertTrue(!pom.msgGroupText().getAttribute("style").contains("display: none;"),"showChartMessage() text is displayed");
		test.log(LogStatus.PASS, test.addScreenCapture(APITestBase.capture("onchart message with chart fully not visible")));
		
		
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("showHideChartMessage() executed");
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
