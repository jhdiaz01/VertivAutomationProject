package pageTest;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class NavigateSocialMediaTest extends Base {
	
	public WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(NavigateResourcesColumnTest.class.getName());

	@BeforeTest
	private void initializeBrowser() throws IOException {
		driver = initializeDriver();
		LOGGER.info("Browser is initialized.");
		

	}
	
	@Test
	private void socialMediaLinks() throws InterruptedException {
		scrollDownToFooter();
		clickSocialMediaLinks();
		visitEachLink();
	}
	
	@AfterTest
	private void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
