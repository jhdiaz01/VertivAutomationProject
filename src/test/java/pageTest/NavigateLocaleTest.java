package pageTest;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.HomePage;
import resources.Base;

public class NavigateLocaleTest extends Base {
	
	
	@BeforeTest
	public void initializeBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(VertivURL);
	}
	
	@Test
	public void changeLocale() {

		HomePage hp = new HomePage(driver);
		
		hp.country_dropdown().click();
		
		
		
	}
}
