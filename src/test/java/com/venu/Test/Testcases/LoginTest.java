package com.venu.Test.Testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.HybridFramework.QA.base.BaseClass;
import com.tutorialsNinja.hybrid.pages.AccountPage;
import com.tutorialsNinja.hybrid.pages.Homepage;
import com.tutorialsNinja.hybrid.pages.LoginPage;
import com.venu.Hybrid.UtilsCommon.CommonUitilities;

public class LoginTest extends BaseClass {
	
	 LoginPage loginpage;
	public LoginTest() {
		super();
	}
	 public WebDriver driver;
		/*
		 * @BeforeMethod public void Starting() { String BrowserName="chrome";
		 * if(BrowserName.equalsIgnoreCase("Chrome")) { driver = new ChromeDriver();
		 * }else if(BrowserName.equalsIgnoreCase("FireFox")) { driver = new
		 * FirefoxDriver(); }else if(BrowserName.equalsIgnoreCase("Edge")) { driver=new
		 * EdgeDriver(); } //driver = new ChromeDriver();
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo/index.php?route=account");
		 */
	 
	 @BeforeMethod 
	 public void StartingTheTest() { 
		 
		driver = LaunchingBrowserAndOpeningApplicationUrl(properties.getProperty("browserName"));
		Homepage homepageObj=new Homepage(driver);
		homepageObj.ClickOnMyAccountDropMenu();
	  loginpage =	homepageObj.ClickonUserLoginLink();
		
	// driver.findElement(By.xpath("//span[text()='My Account']")).click();
	//	driver.findElement(By.linkText("Login")).click();
	}
	@Test(priority = 1, dataProvider = "ReadingDataFromExcel")
	public void VerifylogintoApplicationvalidcredentials(String Emailtext, String PasswordText) {

		/*
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo/index.php?route=account");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Login")).click();
		 */
		
	//	LoginPage loginpage=new LoginPage(driver);
		loginpage.EnterTheUserEmailid(Emailtext);
		loginpage.EnterThePassWord(PasswordText);
		loginpage.ClickingOnLoginButton();
		
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(properties.getProperty(
		 * "validEmail"));
		 * driver.findElement(By.id("input-password")).sendKeys(properties.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 */
		loginpage.AssertingtheSuccessfullLogin();

		
		 AccountPage accountpage=new AccountPage(driver);
		 assertTrue(accountpage.GetdisplaystatusLandingonAccountPage());
		 	
		/*
		 * assertTrue(driver.findElement(By.linkText("Edit your account information")).
		 * isDisplayed(), "Edit your account information is not displayed");
		 */

	//	driver.quit();
	}
	
	
	@DataProvider(name="ReadingDataFromExcel") 
	public static Object[][] Supply_TestData() {
		
		Object[][] data=CommonUitilities.getTestDataFromExcel("LoginData");
		return data;
	}

	@Test(priority = 2)
	public void VerifyWithInvalidCredentials() {
		/*
		 * driver = new ChromeDriver();
		 * 
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo/index.php?route=account");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Login")).click();
		 */
		
	//	LoginPage loginpage=new LoginPage(driver);
		loginpage.EnterTheUserEmailid(CommonUitilities.GenerateTimeStampWithEmail());
		loginpage.EnterThePassWord(dataprop.getProperty("InvalidPassword"));
		loginpage.ClickingOnLoginButton();
		
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(CommonUitilities.
		 * GenerateTimeStampWithEmail());
		 * driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty(
		 * "InvalidPassword"));
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 * 
		 */		
		
		String ActualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarning =dataprop.getProperty("EMailPassNoMatchWarning");
		assertTrue(ActualWarning.contains(ExpectedWarning), "Expected Warning is not matched with Actual Warning");

	//	driver.quit();
	}

	@Test(priority = 3)
	public void VerifyLoginWithInvalidEmailAndValidPass() {

		/*
		 * driver = new ChromeDriver();
		 * 
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo/index.php?route=account");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Login")).click();
		 */
		
	//	LoginPage loginpage=new LoginPage(driver);
		loginpage.EnterTheUserEmailid(CommonUitilities.GenerateTimeStampWithEmail());
		loginpage.EnterThePassWord(properties.getProperty("validPassword"));
	    AccountPage accountpage =	loginpage.ClickingOnLoginButton();
	    System.out.println(accountpage);
		
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(CommonUitilities.
		 * GenerateTimeStampWithEmail());
		 * driver.findElement(By.id("input-password")).sendKeys(properties.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 */
		
		String ActualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarning = dataprop.getProperty("EMailPassNoMatchWarning");
		assertTrue(ActualWarning.contains(ExpectedWarning), "Expected Warning is not matched with Actual Warning");

	//	driver.quit();
	}

	@Test(priority = 4)
	public void VerifyLoginWithValidEmailandInvalidPassword() {
		/*
		 * driver = new ChromeDriver();
		 * 
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo/index.php?route=account");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Login")).click();
		 */
		loginpage.EnterTheUserEmailid(properties.getProperty("validEmail"));
		loginpage.EnterThePassWord(dataprop.getProperty("InvalidPassword"));
		loginpage.ClickingOnLoginButton();
		
		/*
		 * driver.findElement(By.id("input-email")).sendKeys(properties.getProperty(
		 * "validEmail"));
		 * driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty(
		 * "InvalidPassword"));
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 */

		String ActualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarning = dataprop.getProperty("EMailPassNoMatchWarning");
		assertTrue(ActualWarning.contains(ExpectedWarning), "Expected Warning is not matched with Actual Warning");

	//	driver.quit();
	}

	@Test(priority = 5)
	public void VerifyLoginWithoutEmailandwithoutPassword() {
		/*
		 * driver = new ChromeDriver();
		 * 
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		 * 
		 * driver.get("https://tutorialsninja.com/demo/index.php?route=account");
		 * 
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Login")).click();
		 */
//		driver.findElement(By.id("input-email")).sendKeys("abcs@yopmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("Venu4@1234");
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String ActualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String ExpectedWarning = dataprop.getProperty("EMailPassNoMatchWarning");
		assertTrue(ActualWarning.contains(ExpectedWarning), "Expected Warning is not matched with Actual Warning");

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
	 * String newTimeStamp = currentTimestamp.toString().replace(" ",
	 * "_").replace(":", "_");
	 * 
	 * return "test" + newTimeStamp +"@yopmail.com"; }
	 */
}
