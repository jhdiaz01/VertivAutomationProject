package pageTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.HomePage;
import resources.Base;

public class NavigateBrandLandingPagesTest extends Base {
	public WebDriver driver;
		
		@BeforeTest
		private void initializeBrowser() throws IOException {
			driver = initializeDriver();
			driver.get(VertivURL);

		}
		
		@Test
		private void BrandLandingPages() throws InterruptedException {
			
			HomePage home = new HomePage(driver);
			
			scrollDownToFooter();	
			home.navigateOnEachLink();
			home.clickOnEachLink();
		}
		
		@AfterTest
		private void tearDown() {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
}
