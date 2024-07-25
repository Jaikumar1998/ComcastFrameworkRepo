package com.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriverutility.WebDriverUtility;

public class Homepage {
	
	WebDriver driver;
	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contLink;

	@FindBy(linkText = "Campaigns")
	private WebElement CampLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContLink() {
		return contLink;
	}
	
	public WebElement getCampLink() {
		return CampLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	
	
	public void navigateToTheCampaingPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		CampLink.click();
	}
	
	public void logout() {
		WebDriverUtility wdu= new WebDriverUtility();
		WebElement element =driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]"));
		wdu.mouseMoveOnElement(driver,element);
		driver.findElement(By.partialLinkText("Sign Out")).click();
	}
	
	

}
