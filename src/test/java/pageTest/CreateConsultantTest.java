package pageTest;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.CreateConsultantPage;
import resources.Base;

public class CreateConsultantTest extends Base {
	
	Base d = new Base();
	
	@BeforeTest
	public void initializeBrowser() throws IOException {

		driver = initializeDriver();	
		driver.get(VertivPrep);
	}
	
	@Test
	public void createConsultant() throws InterruptedException, IOException {
		ArrayList<String> data = d.getDataConsultant("testDataConsultant");
		
		CreateConsultantPage cc = new CreateConsultantPage(driver);
	
		cc.clickMyAcctLogin();
		cc.clickCreateAcctLink();
		cc.selectConsultant();
		
		//NAME AND EMAIL
		cc.selectPrefix();
		cc.first_name().sendKeys(data.get(1));
		cc.last_name().sendKeys(data.get(2));
		cc.emailAdd().sendKeys(data.get(3));
		cc.confirm_email().sendKeys(data.get(3));
		
		Thread.sleep(2000L);
		//COMPANY INFORMATION
		cc.company().sendKeys(data.get(4));;
		cc.selectOccupation();
		cc.enterCity().sendKeys(data.get(5));
		
		Thread.sleep(2000L);		
		//CHOOSE YOUR COUNTRY AND LANGUAGE
		cc.selectCountry();
		cc.selectLanguage();
		cc.selectState();
		cc.enterZipCode().sendKeys(data.get(9));
		
		cc.clickNextStep();
		
		Thread.sleep(3000L);	
		//LANDED TO CREATE YOUR VERTIV ACCOUNT
		cc.notARobot();
		cc.requestUserAgreement();
		cc.checkGDPRConsent();
		cc.createBtn();

		//Landed on: https://prep.vertiv.com/en-us/create-account/CreateAccountSuccess/	
		cc.verificationEmailConfirmation();
		cc.clickContinueBrowsingLink();
	}
	
	@AfterTest
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
