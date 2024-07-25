package com.crm.listenertuility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpClass implements ITestListener, ISuiteListener {
	public static ExtentReports report;
	public static ExtentTest test;
	

	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		//Spark Report Config
	        	String time = new Date().toString().replace(" ", "_").replace(":"," ");
				ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReporting/Report_"+time+".html");
				spark.config().setDocumentTitle("CRM Test Results");
				spark.config().setReportName("CRM Result");
				spark.config().setTheme(Theme.DARK);
				
				//Add The Env Report
			    report =  new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows 10");
				report.setSystemInfo("Browser", "Chrome");
	}
	
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+"===Start===");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===Started===");
	}
	
	public void onTestSuucess(ITestResult result) {
		System.out.println("====="+result.getMethod().getMethodName()+"==End========");
		test.log(Status.PASS,result.getMethod().getMethodName()+"====completed===");
	}
	 
	  
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		//Step 1: Create the Object of the   WebDriver
				TakesScreenshot ts = (TakesScreenshot)com.crm.basetest.BaseClass.sdriver;
				String scrfile = ts.getScreenshotAs(OutputType.BASE64);
				String time = new Date().toString().replace(" ", "_").replace(":"," ");
				test.addScreenCaptureFromBase64String(scrfile, testName+"-"+time );
				test.log(Status.FAIL,result.getMethod().getMethodName()+"====Failure====");

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
}
