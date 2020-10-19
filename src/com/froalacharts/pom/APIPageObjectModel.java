package com.froalacharts.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.froalacharts.main.APITestBase;

public class APIPageObjectModel extends APITestBase{
	
	private static APIPageObjectModel pomObj = new APIPageObjectModel();
	@FindBy(tagName="svg")
	WebElement svgElement;
	
	@FindBy(tagName="svg")
	List<WebElement> svgElements;
	
	

	public APIPageObjectModel()
	{
		PageFactory.initElements(driver, this);
	}

	
	public WebElement canvasParent()
	{
		return driver.findElement(By.xpath("//*[contains(@class,'-chart-meso')]"));
	}
	
	public WebElement mainCanvas()
	{
		return pomObj.canvasParent().findElement(By.xpath("//*[contains(@class,'-chart-tropo')]"));
		
	}
	
	public List<WebElement> mainCanvases()
	{
		return pomObj.canvasParent().findElements(By.xpath("//*[contains(@class,'-chart-tropo')]"));
		
	}
	
	public WebElement canvas1()
	{
		int canvasNumber = 1;
		int elemCnt=0;
		List<WebElement> allCanvas = pomObj.canvasParent().findElements(By.xpath("//*[contains(@class,'-canvas-meso')]"));
		
		for(WebElement canvas : allCanvas)
		{
			elemCnt++;
			if(elemCnt==canvasNumber)
				return canvas;
		}
		return null;
	}
	
	public WebElement canvas5()
	{
		int canvasNumber = 5;
		int elemCnt=0;
		List<WebElement> allCanvas = pomObj.canvasParent().findElements(By.xpath("//*[contains(@class,'-canvas-meso')]"));
		
		for(WebElement canvas : allCanvas)
		{
			elemCnt++;
			if(elemCnt==canvasNumber)
				return canvas;
		}
		return null;
	}
	
	public boolean isColumnPlotPresentIn(WebElement canvas)
	{
		WebElement columnplot=null;
		try
		{
			columnplot = canvas.findElement(By.xpath("//*[contains(@class,'-column-plot-meso')]"));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean svgExists()
	{
		try
		{
			driver.findElement(By.tagName("svg"));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean svgDisplayed()
	{
		try
		{
			driver.findElement(By.tagName("svg")).isDisplayed();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public int svgCount()
	{
		List<WebElement> svg = driver.findElements(By.tagName("svg"));
		return svg.size();
	}
	
	public boolean buttonExists()
	{
		try
		{
			driver.findElement(By.xpath("//*[contains(@class,'-button')]"));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public WebElement msgGroup()
	{
		return driver.findElement(By.xpath("//*[contains(@class,'-messageGroup')]"));
	}
	public WebElement msgGroupRect()
	{
		return pomObj.msgGroup().findElement(By.tagName("rect"));
	}
	
	public WebElement msgGroupText()
	{
		return pomObj.msgGroup().findElement(By.tagName("text"));
	}

	public WebElement getSvg()
	{
		return svgElement;
	}

}
