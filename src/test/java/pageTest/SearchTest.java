package pageTest;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import resources.Base;

public class SearchTest extends Base {
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		
		driver = initializeDriver();
		driver.get(VertivURL);
	}
	
	@Test(dataProvider = "getData")
	public void search(String keyword) {
		HomePage home = new HomePage(driver);
		
		home.search_icon().click();
		home.search_field().sendKeys(keyword);
		home.search_icon().click();
	}
	
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][1];
		
			data[0][0] = "UPS";
		
		return data;
		
		
	}
	
	
	/*	@AfterTest
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.close();
	}  */
	

}
