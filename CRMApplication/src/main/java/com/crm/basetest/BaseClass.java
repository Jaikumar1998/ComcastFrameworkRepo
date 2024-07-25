package com.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.databaseutility.DataBaseUtility;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.PropertyFileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.objectrepositoryutility.Homepage;
import com.crm.objectrepositoryutility.login;

public class BaseClass {
	
	public WebDriver driver = null;
	public  static WebDriver sdriver = null;

	
	DataBaseUtility dbu = new DataBaseUtility();
	PropertyFileUtility pfu = new PropertyFileUtility();
	ExcelUtility eu = new ExcelUtility();
	JavaUtility ju = new JavaUtility();
	WebDriverUtility wdu = new WebDriverUtility();
	
	
	
	
	@BeforeSuite(groups = {"SmokeTest","RegressionTest"})
	public void confiBS() {
		System.out.println("===Open DataBase===");
		dbu.getDBConnection();
	}
		
	
	@BeforeClass(groups = {"SmokeTest","RegressionTest"})
	public void confiBC() throws IOException {
		System.out.println("===Launch Browser===");
		String Browser =pfu.getDataFromPropertyFile("browser");
		
	
		
		if(Browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(Browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups = {"SmokeTest","RegressionTest"})
	public void confiBM() throws IOException {
		System.out.println("===Login the App===");
		String Url =pfu.getDataFromPropertyFile("url");
		String Username =pfu.getDataFromPropertyFile("username");
		String Password =pfu.getDataFromPropertyFile("password");
		login l = new login(driver);
		driver.get(Url);
		l.loginToApp(Username, Password);
		
		
		
		
		
	}
	
	@AfterMethod(groups = {"SmokeTest","RegressionTest"})
	public void confiAM() {
		System.out.println("===Logout the App===");
		Homepage hp = new Homepage(driver);
		hp.logout();
	}
	
	@AfterClass(groups = {"SmokeTest","RegressionTest"})
	public void confiAf() {
		System.out.println("===Close Browser===");
		driver.quit();
	}
	
	@AfterSuite(groups = {"SmokeTest","RegressionTest"})
	public void congiAs() throws SQLException {
		System.out.println("===Close DataBase===");
		dbu.closeConnection();
		
		
	}

}
