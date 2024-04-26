package com.testng.listeners.hybridframework;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.venu.Hybrid.UtilsCommon.CommonUitilities;
import com.venu.Hybrid.UtilsCommon.extentReporterclass;

public class Listenersclass implements ITestListener{

	ExtentReports extentreports;
	ExtentTest extenttest;
		
	@Override
	public void onStart(ITestContext context) {
		try {
			extentreports = extentReporterclass.ExtentReports_HybridFM();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
				
		extenttest = extentreports.createTest(result.getName());
		extenttest.log(Status.INFO, result.getName() + "Test Get Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extenttest.log(Status.PASS, result.getName()+ "Got successfully Executed" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		
		try {
			driver = (WebDriver)	result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String DestScreeshotpath = CommonUitilities.CaptureScreenshot(driver, result.getName());
		extenttest.addScreenCaptureFromPath(DestScreeshotpath);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, result.getName()+"Got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.SKIP, result.getName()+"Got Skipped");
		
	}

	
	@Override
	public void onFinish(ITestContext context) {
		extentreports.flush();
		
		String PathofExtentReport  = System.getProperty("user.dir")+"\\ExtentReport\\hybridFrameWorkReport.html";
		File extentReport = new File(PathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
