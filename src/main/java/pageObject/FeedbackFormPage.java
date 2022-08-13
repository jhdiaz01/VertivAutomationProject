package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeedbackFormPage {
	WebDriver driver;
	
	public FeedbackFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#SendEmailModel_FirstName")
	WebElement firstName;
	
	@FindBy(css="#SendEmailModel_LastName")
	WebElement lastName;
	
	@FindBy(css="#SendEmailModel_CountryCode")
	WebElement country;
	
	@FindBy(css="#SendEmailModel_EmailAddress")
	WebElement email;
	
	@FindBy(css="#SendEmailModel_ConfirmEmailAddress")
	WebElement confirmEmail;
	
	@FindBy(css="#SendEmailModel_SubjectLine")
	WebElement subjectLine;
	
	@FindBy(css="#SendEmailModel_Message")
	WebElement message;
	
	@FindBy(css="#feedbackFormSubmit")
	WebElement submit;
	
	public void click_submitFeedback() {	
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("#feedbackForm"))).click().perform();
	}
	
	public void select_salutation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Select sel = new Select(wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.cssSelector("#ddlSendEmailModel\\.Title"))))));
		sel.selectByValue("Engr.");
	}
	
	public WebElement enter_firstName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(firstName));
		return firstName;
	}
	
	public WebElement enter_lastName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#SendEmailModel_LastName")));
		return lastName;
	}
	
	public WebElement enter_email() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#SendEmailModel_EmailAddress")));
		return email;
	}
	
	public WebElement enter_confirmEmail() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(confirmEmail));
		return confirmEmail;
	}
	
	public void select_country() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Select sel = new Select(wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.cssSelector("#SendEmailModel_CountryCode"))))));
		sel.selectByValue("USA");
	}
	
	public WebElement enter_subject() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(subjectLine));
		return subjectLine;
	}
	
	public WebElement enter_message() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(message));
		return message;
	}
	
	public void tick_gdrpConsent() {	
		Actions act =  new Actions(driver);
		try {
		act.moveToElement(driver.findElement(By.cssSelector("#SendEmailModel_GdprConsentMarketingBool"))).click().perform();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public WebElement click_submitBtn() {
		return submit;
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	   	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
}
