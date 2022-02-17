package pageTest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import resources.Base;

public class ConsultantLoginTest extends Base {

	@BeforeTest
	public void initializeBrowser() throws IOException {

		driver = initializeDriver();
		driver.get(VertivPrep);
	}
	
	@Test(dataProvider = "consultantData")
	public void consultantLogin(String username, 
								String password
								) throws InterruptedException {
		
		LoginPage clp = new LoginPage(driver);

		//Login from Home page
		clp.MyAcctLog().click();
		clp.email().sendKeys(username);
		clp.pw().sendKeys(password);	
		clp.submitBtn().click();

		Thread.sleep(3000L);
		
		clp.MyAcctLog().click();
		clp.consultantDashboard().click();
		clp.check_loginStatus_consultant();
		
		//Logout
		clp.MyAcctLog().click();
		clp.logout().click();
	}
	

	@DataProvider
	//Data for Consultant user.
	public Object[][] consultantData() {
		Object[][] data = new Object[1][2];
				data[0][0]="qa.con.prep@yopmail.com";
				data[0][1]="Vertiv2021";
				
				return data;
	}
	
	@AfterTest
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
