package com.venu.Hybrid.UtilsCommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReporterclass {
	
	public static ExtentReports ExtentReports_HybridFM() throws FileNotFoundException {
		Properties configProps=new Properties();
		ExtentReports extentReport = new ExtentReports();
		File myReportfile=new File(System.getProperty("user.dir")+"\\ExtentReport\\hybridFrameWorkReport.html");
		ExtentSparkReporter extentspark_Reporter = new ExtentSparkReporter(myReportfile);
		
		extentspark_Reporter.config().setTheme(Theme.DARK);
		extentspark_Reporter.config().setReportName("Tutorials Ninja Automation Report");
		extentspark_Reporter.config().setDocumentTitle("TutorialsNinja Test Result");
		extentspark_Reporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(extentspark_Reporter);
		
		FileInputStream propFile=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\config\\Config.properties"));
		try {
			configProps.load(propFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL : ",configProps.getProperty("url") );
		extentReport.setSystemInfo("Browser Name : ", configProps.getProperty("browserName"));
		extentReport.setSystemInfo("Email: ", configProps.getProperty("validEmail"));
		extentReport.setSystemInfo("PassWord :", configProps.getProperty("validPassword"));
		
		extentReport.setSystemInfo("Operating System : ", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName: ", System.getProperty("user.name"));
		extentReport.setSystemInfo("java Version: ", System.getProperty("java.version"));
		
		return extentReport;
	}
}