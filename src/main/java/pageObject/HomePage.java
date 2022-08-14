package pageObject;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class HomePage {
	private static Logger LOGGER = LogManager.getLogger(HomePage.class.getName());
	
	WebDriver driver;
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			  .withTimeout(Duration.ofSeconds(30))
			  .pollingEvery(Duration.ofSeconds(5))
			  .ignoring(NoSuchElementException.class);
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//header/div[2]/article[1]/div[1]/div[1]/label[1]")
	WebElement country;
	
	public WebElement country_dropdown() {
		return country;
	}

	
//	@FindBy(xpath="//*[@id=\"languageSwitch\"]/form[1]/div[1]/select")
//	WebElement country_drop;
//		Select country_dropdown = new Select(country_drop);
	

	@FindBy(css="#languageSwitch > form.select-lang.geo-diff.ng-pristine.ng-valid > div.col-xs-12.center-content > button")
	WebElement proceed;
	
	public WebElement proceedBtn() {
		
		if(proceed.isDisplayed()) {
			proceed.click();
		}
		
		
		
		return proceed;
	}
	
		
		
	@FindBy(xpath="//*[@id=\"languageSwitch\"]/form[1]/button")
	WebElement saveBtn;
	
	
	@FindBy(css="#cookiesOptInBannerDiv")
	WebElement cookieBanner;
	
	public WebElement hideBanner() {
		JavascriptExecutor js = (JavascriptExecutor)(driver);
		js.executeScript("document.getElementById('cookiesOptInBannerDiv').removeAttribute('cookiesOptInBanner')");
		return cookieBanner;
	}
	
	public WebElement save_button() {
		return saveBtn;
	}
	
	@FindBy(xpath="//header/div[2]/article[1]/div[1]/form[1]/button[1]") 
	WebElement searchbtn;
	
	public WebElement search_icon() {
		return searchbtn;
	}
	
	@FindBy(xpath="/html/body/article[1]/section[1]/header/div[2]/article/div[1]/form/input")
	WebElement searchfld;
	
	public WebElement search_field() {
		return searchfld;
	}
	
	public void clickOnEachLink() throws InterruptedException {
		//all links have a tagName of "a"
	   	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   	 
	   	//limiting web driver scope
	   	 WebElement footerBrand = driver.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__brands"));    
	   	    	
	   	 Thread.sleep(3000L);
	   	 
	   	 WebElement rowDriver = footerBrand.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__brands > div > div > div.p3-page-footer__brands_links.col-xs-12.col-sm-9"));
	   	 
	   	 //click on each link in the column and check if the pages are opening
	   	 for (int i=0; i<rowDriver.findElements(By.tagName("a")).size(); i++) {
	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
	   		 
	   		rowDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
	   		 
	   	 }
	}
	
	public void navigateOnEachLink() {
	   	 //to visit and print each page title
	   	 Set <String> abc = driver.getWindowHandles();
	   	 Iterator <String> it = abc.iterator();
	   	 
	   	 while (it.hasNext()){
	   		 
	   		 driver.switchTo().window(it.next());
	   		 LOGGER.info(((driver.getTitle())+" | is loaded successfully."));
	   		 
	   	 }
	}
}
