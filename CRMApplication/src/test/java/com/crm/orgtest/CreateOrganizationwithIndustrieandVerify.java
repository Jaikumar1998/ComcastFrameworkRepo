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


public class CreateOrganizationwithIndustrieandVerify {

	public static void main(String[] args) throws IOException {
		
		//Object Creation
		PropertyFileUtility pfu = new PropertyFileUtility();
		ExcelUtility eu = new ExcelUtility();
		WebDriverUtility wdu = new WebDriverUtility();
		JavaUtility ju = new JavaUtility();
		
		//Read the data from Property file
		String Browser = pfu.getDataFromPropertyFile("browser");
		String Url = pfu.getDataFromPropertyFile("url");
		String Username = pfu.getDataFromPropertyFile("username");
		String Password = pfu.getDataFromPropertyFile("password");
		
		//Read the data from Excel file
		String orgName = eu.getDataFromExcel("Org", 4, 2)+ju.randomNumber();
		String industry =eu.getDataFromExcel("Org", 4, 3);
		String type = eu.getDataFromExcel("Org", 4, 4);
		
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
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Create the Organization with Industry and Type
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Select the industry and type
		WebElement industries =driver.findElement(By.name("industry"));
		wdu.selectByText(industries, industry);
		WebElement types = driver.findElement(By.name("accounttype"));
		wdu.selectByText(types, type);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//Verification the Result
	    String headerMsg = driver.findElement(By.className("dvHeaderText")).getText();
		
		if(headerMsg.contains(orgName)) {
			System.out.println("The Organization is sucessfuly created = Pass");
		}
		else {
			System.out.println("The Organization is not created = Fail");
		}
		
		String industryVerify = driver.findElement(By.id("mouseArea_Industry")).getText();;
		
		if(industryVerify.equals(industry)) {
			System.out.println("Created orgainzation with industry is correct = Pass");
		}
		else {
			System.out.println("Created orgainzation with industry is wrong = Fail");
		}
		
		String typeVerify = driver.findElement(By.id("mouseArea_Type")).getText();
		
		if(typeVerify.equals(type)) {
			System.out.println("Created Organization with type is correct = Pass");
		}
		else {
			System.out.println("Created Organization with type is wrong = Fail");
		}
		
		//Logout
		driver.close();
		
	}

}
