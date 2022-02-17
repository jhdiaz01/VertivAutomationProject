package pageTest;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import resources.Base;

public class GeneralLoginTest extends Base {
	
	@BeforeTest
	public void initializeBrowser() throws IOException {

		driver = initializeDriver();
		driver.get(VertivPrep);
	}

	
	@Test(dataProvider = "generalData")
	public void generalLogin(String username, 
							 String password
							 ) throws InterruptedException {
		
		LoginPage glp = new LoginPage(driver);
		
		//Login from Home page
		glp.MyAcctLog().click();
		glp.email().sendKeys(username);
		glp.pw().sendKeys(password);	
		glp.submitBtn().click();

		Thread.sleep(3000L);
			
		glp.MyAcctLog().click();
		glp.generalDashboard().click();
		glp.check_loginStatus_general();
		
		//Logout
		glp.MyAcctLog().click();
		glp.logout().click();
	}
	
	
	@DataProvider
	//Data for General user.
	public Object[][] generalData() {
		Object[][] data = new Object[1][2];
				data[0][0]="qa.gen.prep@yopmail.com";
				data[0][1]="Vertiv2021";
				
				return data;
	}	
	
	@AfterTest
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
