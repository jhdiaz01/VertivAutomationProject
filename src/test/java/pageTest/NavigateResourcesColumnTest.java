package pageTest;

import java.io.IOException;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class NavigateResourcesColumnTest extends Base {
	public WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(NavigateResourcesColumnTest.class.getName());

	@BeforeTest
	private void initializeBrowser() throws IOException {
		driver = initializeDriver();
		LOGGER.info("Browser is initialized.");
		

	}

	@Test
	private void ResourcesColumnFooter() throws InterruptedException {
		scrollDownToFooter();
		resourcesColumn();
		visitEachLink();
	}
	
	@AfterTest
	private void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
