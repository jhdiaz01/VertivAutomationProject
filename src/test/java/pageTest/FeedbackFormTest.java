package pageTest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.FeedbackFormPage;
import resources.Base;

public class FeedbackFormTest extends Base {
	
	
	@BeforeTest
	private void initializeBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(VertivURL);

	}
	
	@Test(dataProvider="getData")
	private void submitFeedback(String firstname, String lastname, String email, String subject, String message) throws InterruptedException {
		FeedbackFormPage ffp = new FeedbackFormPage(driver);
		
		ffp.scrollDown();

		Thread.sleep(3000L);
		
		ffp.click_submitFeedback();
		
		ffp.select_salutation();
		ffp.enter_firstName().sendKeys(firstname);
		ffp.enter_lastName().sendKeys(lastname);
		ffp.enter_email().sendKeys(email);
		ffp.enter_confirmEmail().sendKeys(email);
		ffp.select_country();
		ffp.enter_subject().sendKeys(subject);
		ffp.enter_message().sendKeys(message);
		ffp.tick_gdrpConsent();
		ffp.click_submitBtn().click();
	}
	
	@DataProvider
	private Object[][] getData() {
		Object data[][] = new Object[1][5];
		data[0][0] = "Sherlock";
		data[0][1] = "Holmes";
		data[0][2] = "sherlock.holmes@yopmail.com";
		data[0][3] = "This is a subject line test";
		data[0][4] = "This is an automation test message";
		
		return data;
	}
	
	@AfterTest
	private void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
