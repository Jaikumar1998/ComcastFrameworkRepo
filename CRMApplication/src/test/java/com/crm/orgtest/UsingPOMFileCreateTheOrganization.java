package com.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.PropertyFileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.objectrepositoryutility.CreateOrganizationPage;
import com.crm.objectrepositoryutility.Homepage;
import com.crm.objectrepositoryutility.Organizationpage;
import com.crm.objectrepositoryutility.login;


public class UsingPOMFileCreateTheOrganization {
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
	//Object Creation
			PropertyFileUtility pfu = new PropertyFileUtility();
			ExcelUtility exu = new ExcelUtility();
			JavaUtility ju = new JavaUtility();
			WebDriverUtility wdu = new WebDriverUtility();
			
			//Read the data from Property file
			String Browser = pfu.getDataFromPropertyFile("browser");
			String Url = pfu.getDataFromPropertyFile("url");
			String Username = pfu.getDataFromPropertyFile("username");
			String Password = pfu.getDataFromPropertyFile("password");
			
			//Read the data from Excel file
			String orgName = exu.getDataFromExcel("Org", 1, 2)+ju.randomNumber();
			
			WebDriver driver = null;
			
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
			
			driver.manage().window().maximize();
			wdu.waitForPageToLoad(driver);
			
			//Navigate to Application Page
			driver.get(Url);
			
			//login l =PageFactory.initElements(driver, login.class);
			login l = new login(driver);
			
			
			//one of the way to access the method for single element
			//l.getUsernameEdit().sendKeys(Username);
			//l.getPasswordEdit().sendKeys(Password);
			//l.getLoginBtn().click(); 
			
			l.loginToApp (Username, Password);
			
			
			
			//Navigate to organization module
			
			Homepage hm = new Homepage(driver);
			hm.getOrgLink().click();
			
			
			//Create New Organization
			Organizationpage op = new Organizationpage(driver);
			op.getCreateNewOrgLink().click();
			
			//Create Organization
			CreateOrganizationPage co = new CreateOrganizationPage(driver);
			co.createOrg(orgName);
			
			
			driver.close();
		}


}
