package com.venu.Test.Testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.HybridFramework.QA.base.BaseClass;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	
	public SearchTest() {
		super();
	} 
	
	@BeforeMethod
	public void Setup() {
		driver = LaunchingBrowserAndOpeningApplicationUrl(properties.getProperty("browserName"));
		driver.get(properties.getProperty("url"));
	}
	@Test(priority = 1)
	public void VerifyWithValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys("hp");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		
	}
	
	@Test(priority = 2)
	public void VerifywithNonExistingProduuctName() {
		driver.findElement(By.name("search")).sendKeys("honda");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		assertTrue(driver.findElement(By.cssSelector("#content > p:nth-child(7)")).isDisplayed());
	}
	
	@Test(priority = 3 )
	public void VerifywithoutProduuctName() {
		
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		assertTrue(driver.findElement(By.cssSelector("#content > p:nth-child(7)")).isDisplayed());
	}
	
	@AfterMethod
	public void Teardown() {
		driver.quit();
	}
}
