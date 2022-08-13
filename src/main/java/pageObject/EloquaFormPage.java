package pageObject;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import resources.Base;

public class EloquaFormPage extends Base {
	
	private static Logger LOGGER = LogManager.getLogger(EloquaFormPage.class.getName());
	WebDriver driver;

	public EloquaFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="#FirstName")
	WebElement firstName;
	
	@FindBy(css="#LastName")
	WebElement lastName;
	
	@FindBy(css="#Email")
	WebElement email;
	
	@FindBy(css="#PhoneNumber")
	WebElement phoneNumber;
	
	@FindBy(css="#Company")
	WebElement company;
	
	@FindBy(css="#zipcode")
	WebElement zip;
	
	@FindBy(css="#Location")
	WebElement location;
	
	@FindBy(css="#Message")
	WebElement message;
	
	@FindBy(css="#ProjectName")
	WebElement projectName;
	
	@FindBy(css="#Submit")
	WebElement submit;
	
	@FindBy(xpath="//input[@id='FileUrl']")
	WebElement upload;
	
	@FindBy (xpath="/html/body/article[1]/section[1]/header/div[2]/article/div[1]/div[2]")
	WebElement desktopnandropdown;
	
	@FindBy (css="#cookiesAcceptButton")
	WebElement acceptBtn;
	

	public WebElement desktop_nav_dropdown() throws InterruptedException {
		Thread.sleep(3000L);
		return desktopnandropdown;
	}
	
	public WebElement enter_firstName() throws InterruptedException {
		return firstName;
	}
	
	public WebElement enter_lastName() {
		return lastName;
	}
	
	public WebElement enter_email() {
		return email;
	}
	
	public WebElement enter_phoneNumber() {
		return phoneNumber;
	}
	
	public WebElement enter_company() {
		return company;
	}
	
	public WebElement enter_zipcode() {
		return zip;
	}
	
	public WebElement enter_location() {
		return location;
	}
	
	public WebElement enter_project() {
		return projectName;
	}
	
	public WebElement enter_message() {
		return message;
	}
	
	public void select_month() {
		Select select = new Select(driver.findElement(By.cssSelector("#MM1")));
		select.selectByValue("11");  //Select 'November as month.
	}
	
	public void select_day() {
		Select select = new Select(driver.findElement(By.cssSelector("#DD1")));
		select.selectByValue("006");  //Select 'Friday' as day.
	}
	
	public void tick_gdrpConsent() throws InterruptedException {	
		Actions act =  new Actions(driver);
		try {
		act.moveToElement(driver.findElement(By.cssSelector("#gDPRMarketingCompliant1"))).click().perform();
		}
		catch (Exception e) {
			LOGGER.debug(e);
		}
		Thread.sleep(3L);
	}
	
	public void click_submit() {
		WebElement element = driver.findElement(By.cssSelector("#Submit"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		
	//	return submit;
	}
	
	public WebElement uploadFile() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('FileUrl').removeAttribute('readonly')");
		return upload;
	}
	
	public void open_eloquaForm() throws InterruptedException {
		try {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(eloqua_form_url);
		
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}
	
	public void open_eloquaFormWithAttachment() throws InterruptedException {
		try {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(eloqua_form_url2);
		Thread.sleep(3000L);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
	}
	
	public void scrollDown() throws InterruptedException {
		WebElement subHeading = driver.findElement(By.xpath("//p[contains(text(),'This is the Form Sub-heading')]"));
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", subHeading );
					Thread.sleep(5000L);
	}
	
	public void check_submission_status() {
	
		String msg = driver.findElement(By.xpath("//div[@id='eloquaForm-message_UntitledForm-1505831635058']//*[@class='text-center']/p[1]")).getAttribute("class");
		String successMsg = "eloquaForm-popup-title-thank-you hidden";
		
		if(msg.contains(successMsg)) {
		    System.out.println("Success: Form has been submitted.");
			}
		else {
		    System.out.println("Error: Form submission did not proceed.");
			};
		
	}
	
	public void clickAcceptBtn() {
		acceptBtn.click();
	}
}