package pageTest;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class NavigateSupportColumnTest extends Base {

	@BeforeTest
	private void initializeBrowser() throws IOException {
		driver = initializeDriver();

	}
	
	@Test
	private void SupportColumnFooter() throws InterruptedException {
		scrollDownToFooter();
		supportColumn();
		visitEachLink();
	}
	
	@AfterTest
	private void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
