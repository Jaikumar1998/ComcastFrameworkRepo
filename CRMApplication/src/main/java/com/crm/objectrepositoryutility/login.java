package com.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriverutility.WebDriverUtility;

public class login  extends WebDriverUtility {    //Rule-1 = Create the Seperate java class
	
	
	WebDriver driver;
	public login(WebDriver driver) {
		this.driver = driver;  
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(name = "user_name")
	private WebElement usernameEdit;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdit;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	
	

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	} 
	
	
	public void loginToApp( String username , String password) {
		driver.manage().window().maximize();
		waitForPageToLoad(driver);
		
		
		
		
		
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}
	
	
	
	
	
	
	

}
