package pageTest;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class NavigateCorporateColumnTest extends Base {
	public WebDriver driver;
	
	@BeforeTest
	private void initializeBrowser() throws IOException {
		driver = initializeDriver();

	}
	
	@Test
	private void CorporateColumnFooter() throws InterruptedException {
		scrollDownToFooter();
		corporateColumn();
		visitEachLink();
	}
	
	@AfterTest
	private void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
