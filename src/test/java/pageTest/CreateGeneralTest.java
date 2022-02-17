package pageTest;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.CreateGeneralPage;
import resources.Base;

public class CreateGeneralTest extends Base {
	
	Base d = new Base();

	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(VertivPrep);
	}

	//create each @test for different locales (emea, latam, US)
	
	@Test
	public void createGeneral() throws IOException, InterruptedException {
		
		CreateGeneralPage gu_asia = new CreateGeneralPage(driver);
		
		ArrayList<String> data = d.getData("general");
		
		gu_asia.LogBtn().click();
		gu_asia.clickCreateAcctLink().click();
		gu_asia.selectPrefix();
		gu_asia.enterFirstName().sendKeys(data.get(1));
		gu_asia.enterLastName().sendKeys(data.get(2));
		gu_asia.enterEmailAdd().sendKeys(data.get(3));
		gu_asia.enterConfirmEmail().sendKeys(data.get(3));
		
		Thread.sleep(2000L);
		
		gu_asia.selectCountry();
		gu_asia.selectLanguage();
		gu_asia.selectState();
		gu_asia.enterZip().sendKeys(data.get(7));
		gu_asia.enterPassword().sendKeys(data.get(8));
		gu_asia.enterConfirmPassword().sendKeys(data.get(8));
		gu_asia.clickSubmitBtn();
		
		//Landed on https://vertiv.com/en-asia/create-account/CreateAccountSuccess/
		Thread.sleep(2000L);
		
		gu_asia.requestUserAgreement();
		gu_asia.requestUserAgreement2();
		gu_asia.checkGdpr().click();
		gu_asia.clickCreateBtn();
		gu_asia.verificationEmailConfirmation();
		gu_asia.clickContinueBrowsingLink().click();

	}
	
	@AfterTest
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
