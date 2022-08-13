package pageTest;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.EloquaFormPage;
import resources.Base;

public class EloquaFormTest extends Base {
	
	@BeforeTest
	public void initializeBrowser() throws IOException, InterruptedException {
		driver = initializeDriver();
		driver.get(eloqua_form_url);
	}
	
	@Test(priority=0, dataProvider = "getLoginData")
	public void eloquaWithoutAttachment(String firstname,
										String lastname,
										String email,
										String phonenumber,
										String company,
										String zipcode,
										String location,
										String projectname,
										String message
										) throws InterruptedException {
		
		EloquaFormPage efp = new EloquaFormPage(driver);
		
		efp.scrollDown();
		
		efp.clickAcceptBtn();
		efp.enter_firstName().sendKeys(firstname);
		efp.enter_lastName().sendKeys(lastname);
		efp.enter_email().sendKeys(email);
		efp.enter_phoneNumber().sendKeys(phonenumber);
		efp.enter_company().sendKeys(company);
		efp.enter_zipcode().sendKeys(zipcode);
		efp.enter_location().sendKeys(location);
		
		efp.select_month();
		efp.select_day();
		
		efp.enter_project().sendKeys(projectname);
		efp.enter_message().sendKeys(message);

		efp.tick_gdrpConsent();
		
		efp.click_submit();
		efp.check_submission_status();
	}
	
	@DataProvider
	private Object[][] getLoginData() {
		Object data[][] = new Object[1][9];
		data[0][0] = "test first";
		data[0][1] = "test last";
		data[0][2] = "test.email@yopmail.com";
		data[0][3] = "9561983322";
		data[0][4] = "test company";
		data[0][5] = "4107";
		data[0][6] = "test location";
		data[0][7] = "Vertiv Test Automation Project";
		data[0][8] = "This is a TEST message only.";
		
		return data;
	}
	
	@Test(priority=1, dataProvider="getDataToAttach")
	public void eloquaWithAttachment(String firstname, String lastname, String email, String phonenumber, String company, String sampleFile) throws InterruptedException {
		EloquaFormPage eat = new EloquaFormPage(driver);
		
		eat.open_eloquaFormWithAttachment();
		
		eat.scrollDown();
				
		eat.enter_firstName().sendKeys(firstname);
		eat.enter_lastName().sendKeys(lastname);
		eat.enter_email().sendKeys(email);
		eat.enter_phoneNumber().sendKeys(phonenumber);
		eat.enter_company().sendKeys(company);	
		eat.uploadFile().sendKeys(sampleFile);
		eat.tick_gdrpConsent();
		eat.click_submit();
		
		eat.check_submission_status();
	}
	
	@DataProvider
	public Object[][] getDataToAttach() {
		Object data[][] = new Object[1][6];
		data[0][0] = "Lily";
		data[0][1] = "Mosby";
		data[0][2] = "marshall.mosby@yopmail.com";
		data[0][3] = "9827163";
		data[0][4] = "Warner Bro.";
		data[0][5] = "C:\\TestAsset.pdf";
		
		return data;
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
}
