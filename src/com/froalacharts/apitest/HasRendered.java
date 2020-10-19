package com.froalacharts.apitest;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.froalacharts.main.APITestBase;
import com.froalacharts.pom.APIPageObjectModel;
import com.froalacharts.util.TestUtil;

public class HasRendered extends APITestBase{
	//The api name according to the data sheet
		private final static String chart = "hasRendered()"; 
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
		public static void hasRendered()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			boolean chartHasRendered = (boolean) js.executeScript("return tsChart.hasRendered();");
			Assert.assertTrue(!chartHasRendered, "Chart has not rendered");
			js.executeScript("tsChart.render();");
			chartHasRendered = (boolean) js.executeScript("return tsChart.hasRendered();");
			Assert.assertTrue(chartHasRendered, "Chart has rendered");
		}
		
		@AfterTest
		public void shutDown()
		{
			try
			{
				System.out.println("hasRendered() executed");
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
