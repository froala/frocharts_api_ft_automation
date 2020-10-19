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

public class GetSetChartAttribute extends APITestBase {
	//The api name according to the data sheet
	private final static String chart = "getSetChartAttribute()"; 
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
	public void getSetChartAttribute() throws IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		Assert.assertTrue(pom.buttonExists(),"button exists");
		
		
		long exportenabled = (long) js.executeScript("return tsChart.getChartAttribute(\"exportenabled\");");
		long showprintmenuitem = (long) js.executeScript("return tsChart.getChartAttribute(\"showprintmenuitem\");");
		
		if(exportenabled==1 && showprintmenuitem==1)
		{
			js.executeScript("tsChart.setChartAttribute(\"exportenabled\",0);");
			js.executeScript("tsChart.setChartAttribute(\"showprintmenuitem\",0);");
			
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
			exportenabled = (long) js.executeScript("return tsChart.getChartAttribute(\"exportenabled\");");
			showprintmenuitem = (long) js.executeScript("return tsChart.getChartAttribute(\"showprintmenuitem\");");
			
			Assert.assertTrue(exportenabled==0,"export disabled");
			Assert.assertTrue(showprintmenuitem==0,"showprintmenuitem disabled");
			
			Assert.assertTrue(!pom.buttonExists(),"button does not exist");
		}
	}
	
	@AfterTest
	public void shutDown()
	{
		try
		{
			System.out.println("getSetChartAttribute() executed");
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
