package com.venu.Test.Testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.HybridFramework.QA.base.BaseClass;
import com.venu.Hybrid.UtilsCommon.CommonUitilities;

public class RegistrationTest extends BaseClass{

	public WebDriver driver;
	
	public RegistrationTest() {
		super();
	}
	@BeforeMethod
	public void BrowserOpen() {
		/*
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo");
		 */
	
		driver = LaunchingBrowserAndOpeningApplicationUrl(properties.getProperty("browserName"));
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	@Test(priority = 1)
	public void RegisterWithMandatoryFields() {
		/*
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * 
		 * driver.findElement(By.linkText("Register")).click();
		 */				
				driver.findElement(By.id("input-firstname")).sendKeys("Test1");
				driver.findElement(By.id("input-lastname")).sendKeys("test");
				driver.findElement(By.id("input-email")).sendKeys(CommonUitilities.GenerateTimeStampWithEmail());
				driver.findElement(By.id("input-telephone")).sendKeys("45789268263");
				driver.findElement(By.id("input-password")).sendKeys("12341234@");
				driver.findElement(By.id("input-confirm")).sendKeys("12341234@");
				driver.findElement(By.name("agree")).click();
				driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
				
				String RegiSuccess = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
				assertEquals(RegiSuccess, "Your Account Has Been Created!", "Registration was not successful and Registration page not visible");
				
				driver.quit();
	}
	
	@Test(priority = 2)
	public void EnterAlltheFields() {
		
		/*
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * 
		 * driver.findElement(By.linkText("Register")).click();
		 */		
		driver.findElement(By.id("input-firstname")).sendKeys("Test1");
		driver.findElement(By.id("input-lastname")).sendKeys("test");
		driver.findElement(By.id("input-email")).sendKeys(CommonUitilities.GenerateTimeStampWithEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("45789268263");
		driver.findElement(By.id("input-password")).sendKeys("12341234@");
		driver.findElement(By.id("input-confirm")).sendKeys("12341234@");
		
		driver.findElement(By.xpath("//label[@class='radio-inline']/input[@value='1']")).click();
		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String RegiSuccess = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		assertEquals(RegiSuccess, "Your Account Has Been Created!", "Registration was not successful and Registration page not visible");
		
		driver.quit();

		
	}
	
	@Test(priority = 3)
	public void verifyWithRegisteredMailId() {
		/*
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * 
		 * driver.findElement(By.linkText("Register")).click();
		 */		
		driver.findElement(By.id("input-firstname")).sendKeys("Test1");
		driver.findElement(By.id("input-lastname")).sendKeys("test");
		driver.findElement(By.id("input-email")).sendKeys("abcvenu@yopmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("45789268263");
		driver.findElement(By.id("input-password")).sendKeys("12341234@");
		driver.findElement(By.id("input-confirm")).sendKeys("12341234@");
		
		driver.findElement(By.xpath("//label[@class='radio-inline']/input[@value='1']")).click();
		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String ActualWarning=driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		assertTrue(ActualWarning.contains("Warning: E-Mail Address is already registered!"),"Email id is New hence, user is going forward");
		
		driver.quit();
		
	}
	
	@Test(priority = 4)
	public void verifyRegisterPageWithoutAnyDetails() {
		
		/*
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * 
		 * driver.findElement(By.linkText("Register")).click();
		 */		
		
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String ActualPrivacyPolicy = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		assertTrue(ActualPrivacyPolicy.contains("Warning: You must agree to the Privacy Policy!"),"Privacy Warning message not displayed");
		
		String ActualFirstNameWarning = driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText();
		assertTrue(ActualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"),"First Name Edit field not showing proper warning");
		
		String ActualLastNameWarning = driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).getText();
		assertTrue(ActualLastNameWarning.contains("Last Name must be between 1 and 32 characters!"),"Last Name Error warning not coming");
		
		String ActualEmailIdWarning = driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).getText();
		assertTrue(ActualEmailIdWarning.contains("E-Mail Address does not appear to be valid!"),"Email ID warning is not coming up");
		
		String ActualTelephoneWarning = driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).getText();
		assertTrue(ActualTelephoneWarning.contains("Telephone must be between 3 and 32 characters!"),"Telephone warning is not coming up");
		
		String ActualPasswordWarning = driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).getText();
		assertTrue(ActualPasswordWarning.contains("Password must be between 4 and 20 characters!"),"Password warning is not coming up");
			
	//	driver.quit();
	}
	
	@AfterMethod
	public void BrowserClose() {
		driver.quit();
	}
	/*
	 * public String GenerateTimeStampWithEmail() {
	 * 
	 * Date currentTimestamp = new Date();
	 * 
	 * String newTimeStamp = currentTimestamp.toString().replace(" ", "_").replace(":", "_");
	 * 
	 * return "test" +newTimeStamp+"yopmail.com";
	 * 
	 * }
	 */
}
