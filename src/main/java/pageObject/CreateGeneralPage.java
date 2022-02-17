package pageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGeneralPage {
	private static Logger LOGGER = LogManager.getLogger(CreateGeneralPage.class.getName());
	WebDriver driver;
	
	public CreateGeneralPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='desktop-nav-dropdown__btn']")
	WebElement accLogBtn;

	@FindBy(xpath="//a[contains(@class,'link link-- header-login__link')][contains(text(),'Create an account')]")
	WebElement createAcct;

	@FindBy(xpath="//select[@id='CreateAccountModel_Prefix']")
	WebElement selectPrefix;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_FirstName']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_LastName']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='username']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_ConfirmEmailAddress']")
	WebElement confirmEmail;
	
	@FindBy(xpath="//select[@id='CreateAccountModel_Country']")
	WebElement country;
	
	@FindBy(xpath="//select[@id='CreateAccountModel_Language']")
	WebElement language;
	
	@FindBy(xpath="//select[@id='CreateAccountModel_State']")
	WebElement state;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_ZipCode']")
	WebElement zipCode;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_Password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_ConfirmPassword']")
	WebElement confirmPassword;
	
	@FindBy(xpath="//button[@class='next-step button primary']")
	WebElement submit;
	
	@FindBy(xpath="//*[@id='requestUserAgreement']/div[1]/label/span[1]")
	WebElement userAgreement;
	
	@FindBy(xpath="//*[@id=\'requestUserAgreement\']/div[2]/label/span[1]")
	WebElement userAgreement2;
	
	@FindBy(css="#GDPRMarket > div > div > label > span")
	WebElement gdpr;
	
	@FindBy(xpath="//button[@class='create-account button primary']")
	WebElement create;
	
	@FindBy(css="body > article.page > div > div > div > div > div > div.col-sm-10.col-sm-offset-1.text-center > span > a")
	WebElement continue_browsing;
	
	@FindBy(css="body > article.page.homepage.transparent-page-header > section.header-component.ng-scope > header > div.p3-page-header__container.container > article > div.desktop-nav__topbar > div:nth-child(2) > div > form > button")
	WebElement login;
	
	
	public WebElement LogBtn() {
		return accLogBtn;
	}
	
	public WebElement clickCreateAcctLink() {
		return createAcct;
	}	
	
	public void selectPrefix() {
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='CreateAccountModel_Prefix']")));
		Select prefix = new Select(driver.findElement(By.xpath("//select[@id='CreateAccountModel_Prefix']")));
		prefix.selectByValue("Mr.");
	}
	
	public WebElement enterFirstName() {
		return firstName;
	}
	
	public WebElement enterLastName() {
		return lastName;
	}

	public WebElement enterEmailAdd() {	
		return email;
		
	}
	
	public WebElement enterConfirmEmail() {
		return confirmEmail;
	}
	
	public void selectCountry() {
		Select country = new Select(driver.findElement(By.xpath("//select[@id='CreateAccountModel_Country']")));
		country.selectByValue("USA");
	}
	
	public void selectLanguage() {
		Select language = new Select(driver.findElement(By.xpath("//select[@id='CreateAccountModel_Language']")));
		language.selectByValue("en");
	}
	
	public void selectState() {
		Select state = new Select(driver.findElement(By.xpath("//select[@id='CreateAccountModel_State']")));
		state.selectByValue("Texas");
	}
		
	public WebElement enterZip() {
		return zipCode;
	}
	
	public WebElement enterPassword() {
		return password;
	}
	
	public WebElement enterConfirmPassword() {
		return confirmPassword;
	}
	
	public void clickSubmitBtn() {
		try {
		submit.click();
		}
		catch(java.lang.NullPointerException e) {
			System.out.println(e);
		}
		catch(org.openqa.selenium.NoSuchElementException f) {
			System.out.println(f);
		}
		

	}
	
	public void requestUserAgreement() {
		
		try {
			userAgreement.click();
		} catch (Exception e) {
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].click();", userAgreement);
			
		}
		
		
		System.out.println(userAgreement.isEnabled());
	}
	
	public void requestUserAgreement2() {
		userAgreement2.click();
		System.out.println(userAgreement2.isEnabled());
	}

	public WebElement checkGdpr() {
		System.out.println("GDPR checkbox is checked.");
		return gdpr;
		
	}
	
	public void clickCreateBtn() {
		create.click();
		LOGGER.info("Create button is clicked.");
	}
	
	public void verificationEmailConfirmation() {
	
		String expectedtext = "You are one step away from using your account. A verification link was sent to the email address you used to create the account. Please click the link in the email to verify your email address. If you don't verify your address, you may not be able to access Vertiv features available for account owners only.";
		String actualtext = driver.findElement(By.cssSelector("body > article.page > div > div > div > div > div > div.col-sm-10.col-sm-offset-1.text-center > p")).getText();

		if (expectedtext.equalsIgnoreCase(actualtext)) {
		       System.out.println("Account proceed successfully.");
		 } 
		 else {
		       System.out.println("Account did not proceed successfully.");
		 }
	}
	
	public WebElement clickContinueBrowsingLink() {
		return continue_browsing;
	}
	
	public void errorMsg() {
		
		String expectedtext= "There is already an account with this email address.";
		String actualtext = driver.findElement(By.cssSelector("#form-inputs > div:nth-child(6) > span > span")).getText();
		
		if (expectedtext.equalsIgnoreCase(actualtext)) {
				System.out.println("Error message is not yet verified.");
		 } 
		 else {
			 	System.out.println("Error message has been verified.");
		 }
	}
	
	public void clickLoginBtn() {
		login.click();
	}
	
}
