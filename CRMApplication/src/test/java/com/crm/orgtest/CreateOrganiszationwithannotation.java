package com.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.basetest.BaseClass;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.listenertuility.ListenerImpClass;
import com.crm.objectrepositoryutility.CreateOrganizationPage;
import com.crm.objectrepositoryutility.Homepage;
import com.crm.objectrepositoryutility.Organizationpage;

@Listeners(com.crm.listenertuility.ListenerImpClass.class)
public class CreateOrganiszationwithannotation extends BaseClass  {
	
	
	public ExtentTest test;
	@Test(groups ="SmokeTest")
	public void createOrganizationTest() throws EncryptedDocumentException, IOException, InterruptedException   { 
		
		//ListenerImpClass.test.log(Status.INFO,"Navigae to Org Page"); //After creating Utility class object in WebDriver
		//Instead of using listener imp class to Utility class
		
		UtilityClassObject.getTest().log(Status.INFO,"Navigae to Home Page");
	    Homepage hp = new Homepage(driver);
	    
		hp.getOrgLink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org page");
		Organizationpage cp = new Organizationpage(driver);
		cp.getCreateNewOrgLink().click();
		
		ExcelUtility eu = new ExcelUtility();
		
		JavaUtility ju= new JavaUtility();
		String orgName = eu.getDataFromExcel("Org", 1, 2)+ju.randomNumber();
		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Org page");
		CreateOrganizationPage cop =new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
		
		UtilityClassObject.getTest().log(Status.INFO,"====Organization Page is Created=====");
		
		}
		
	
	@Test
	public void createOrganizationwithIndustriesTest() throws EncryptedDocumentException, IOException, InterruptedException {
		
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to the Home Page");
		Homepage hp = new Homepage(driver);
		hp.getOrgLink().click();
		
		Organizationpage op = new Organizationpage(driver);
		op.getCreateNewOrgLink().click();
		
		JavaUtility ju = new JavaUtility();
		
		ExcelUtility eu = new ExcelUtility();
		String orgName =eu.getDataFromExcel("Org", 1, 2)+ju.randomNumber();
		
		
		UtilityClassObject.getTest().log(Status.INFO,"Create Org With Industries");
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithIndustries(orgName, "Energy");
		
		
		UtilityClassObject.getTest().log(Status.INFO,"====Organization Page with idnustry is Created====");
		
	}
	
	@Test
	public void createOrganizationWithPhnNo() throws EncryptedDocumentException, IOException, InterruptedException {
		UtilityClassObject.getTest().log(Status.INFO,"Nacigate to HomePage");
		Homepage hp = new Homepage(driver);
		hp.getOrgLink().click();;
		
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Org Page");
		Organizationpage op = new Organizationpage(driver);
		op.getCreateNewOrgLink().click();
		
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		String orgName =eu.getDataFromExcel("Org", 1, 2)+ju.randomNumber();
		
	   String phnno = eu.getDataFromExcel("org", 7, 4);
	   
	   UtilityClassObject.getTest().log(Status.INFO,"Create Org with Phno");
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrgWithPhnNo(orgName, phnno);
		

		
		
	}

}
