package com.HybridFramework.QA.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.venu.Hybrid.UtilsCommon.CommonUitilities;

public class BaseClass {
	WebDriver driver;
	public Properties properties;
	public Properties dataprop;
	
	public WebDriver LaunchingBrowserAndOpeningApplicationUrl(String browserName) {
			
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUitilities.Implicit_Wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUitilities.Page_load_time));
		driver.get(properties.getProperty("url"));	
		return driver;

	}
	public BaseClass() {
		
		properties = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\config\\Config.properties");
		try {
			FileInputStream fis = new FileInputStream(propfile);
			properties.load(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		dataprop = new Properties();
		File testdataprop=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\testdata\\TestData.properties");
		try {
			FileInputStream Fis_DataProp=new FileInputStream(testdataprop);
			dataprop.load(Fis_DataProp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
