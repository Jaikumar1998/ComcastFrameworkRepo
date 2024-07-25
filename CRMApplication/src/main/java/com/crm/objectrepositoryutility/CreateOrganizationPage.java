package com.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationPage {
	
	WebDriver driver;
	public CreateOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdit;

	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name ="industry")
	private WebElement industrydropdown;
	
	@FindBy(id = "phone")
	private WebElement phnedit;
	
	


	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getIndustrydropdown() {
		return industrydropdown;
	}
	public WebElement getPhnedit() {
		return phnedit;
	}
	
	
	public void createOrg(String orgName) throws InterruptedException {
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
		Thread.sleep(5000);
		
	}
	
	public void createOrgWithIndustries(String orgName,String text) throws InterruptedException {
		orgNameEdit.sendKeys(orgName);
	   // WebElement element =driver.findElement(By.name("industry"));
		WebElement element = getIndustrydropdown();
		element.click();
		WebDriverUtility wdu = new WebDriverUtility();
		wdu.selectByText(element, text);
		
		saveBtn.click();
		Thread.sleep(5000);
	}
	
	public void createOrgWithPhnNo(String orgName,String phnno) throws InterruptedException {
		orgNameEdit.sendKeys(orgName);
		phnedit.sendKeys(phnno);
		saveBtn.click();
		Thread.sleep(5000);
		
		
	}
	
	

}
