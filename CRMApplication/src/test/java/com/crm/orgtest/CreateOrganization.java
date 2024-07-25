package com.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.PropertyFileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganization {

	public static void main(String[] args) throws IOException {
		
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
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Create the Orgainzation 
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//Verification the Result
		String headerMsg = driver.findElement(By.className("dvHeaderText")).getText();
		
		if(headerMsg.contains(orgName)) {
			System.out.println("The Organization is sucessfuly created = Pass");
		}
		else {
			System.out.println("The Organization is not created = Fail");
		}
		
		//Logout
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wdu.mouseMoveOnElement(driver,element);
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.close();
	}

}
