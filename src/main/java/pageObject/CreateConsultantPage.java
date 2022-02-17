package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateConsultantPage {

	WebDriver driver;
	public CreateConsultantPage(WebDriver driver) {
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
	
	@FindBy(xpath="//input[@name='CreateAccountModel.ZipCode']")
	WebElement zipCode;
	
	@FindBy(xpath="//div[@id='requestUserAgreement']//div[2]//label[1]//span[1]")
	WebElement userAgreement;
	
	@FindBy(xpath="//div[@id='GDPRMarket']//span[@class='subscribe-checkbox']")
	WebElement gdprConsent;
	
	@FindBy(xpath="//article[@class='page']//div[@class='row']//div[1]//div[1]//div[1]//label[1]//span[1]")
	WebElement yesRadio;
	
	@FindBy(xpath="//button[@class='create-account button primary']")
	WebElement create;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_ChannelGrowth']")
	WebElement howDidyouKnow;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_CompanyName']")
	WebElement companyName;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_Address1']")
	WebElement address1;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_Address2']")
	WebElement address2;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_PhoneNumber']")
	WebElement phoneNumber;
	
	@FindBy(xpath="//input[@id='CreateAccountModel_City']")
	WebElement city;
	
	@FindBy(xpath="//label[@for='desktopNavDropdown416']")
	WebElement geoLocation;
	
	@FindBy(xpath="//select[@name='languages']")
	WebElement geoLanguage;
	
	@FindBy(xpath="//body[@class='ng-scope']/article[@class='page']/div[@class='page-content']/div[@class='user-register-component set-min-height']/div[@class='container layer']/div[@id='step']/div[@class='second-step']/div[@class='row']/div[@class='col-sm-8 col-sm-offset-2']/form/div[@id='requestUserAgreement']/div[1]/label[1]/span[1]")
	WebElement notRobot;
	
	@FindBy(xpath="//label[@class='desktop-nav-dropdown__btn']")
	WebElement myAcctLogin;
	
	@FindBy(css="body > article.page > div > div > div > div > div > div.col-sm-10.col-sm-offset-1.text-center > span > a")
	WebElement continue_browsing;
	
	
	public void clickMyAcctLogin() {
		myAcctLogin.click();
		}
	
	public void clickCreateAcctLink() {
		createAcct.click();
	}	
	
	public void selectPrefix() {
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='CreateAccountModel_Prefix']")));
		Select prefix = new Select(driver.findElement(By.xpath("//select[@id='CreateAccountModel_Prefix']")));
		prefix.selectByValue("Mr.");
	}
	
	public WebElement first_name() {
		return firstName;
	}
	
	public WebElement last_name() {
		return lastName;
	}
	
	public WebElement emailAdd() {
		return email;
	}
	
	public WebElement confirm_email() {
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
		state.selectByValue("California");
	}
		
	public WebElement enterZipCode() {
		return zipCode;
	}
	
	public void clickNextStep() {
		
		try {
			new WebDriverWait(driver, 5000).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='next-step button primary']")));
			driver.findElement(By.xpath("//button[@class='next-step button primary']")).click();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void notARobot() {
		try	{
			new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(notRobot));
			notRobot.click();
		}
		catch (org.openqa.selenium.ElementClickInterceptedException e) {
			System.out.println(e);
		}
	}
	
	public void requestUserAgreement() {
		userAgreement.click();
		System.out.println(userAgreement.isEnabled());
	}
	
	public void checkGDPRConsent() {
		gdprConsent.click();
		System.out.println(gdprConsent.isEnabled());
	}

	public void yes_radioBtn() {
		yesRadio.click();
		System.out.println("YES radio button is selected.");
		
	}
	
	public void createBtn() {
		create.click();
	}
	
	public void verificationEmailConfirmation() {
		
		String expectedtext = "You are one step away from using your account. A verification link was sent to the email address you used to create the account. Please click the link in the email to verify your email address. If you don't verify your address, you may not be able to access Vertiv features available for account owners only.";
		String actualtext = driver.findElement(By.cssSelector("body > article.page > div > div > div > div > div > div.col-sm-10.col-sm-offset-1.text-center > p")).getText();
		
		if (expectedtext.equalsIgnoreCase(actualtext)) {
		       System.out.println("Account did not proceed successfully.");
		 } 
		 else {
		       System.out.println("Account proceed successfully.");
		 }
	}
	
	public void selectConsultant() {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//input[@id='Consultant']"))).click().perform();
	}
	
	public WebElement vertivPartnerProgram() {
		return howDidyouKnow;
	}
			
	public void yes_reseller() {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//input[@id='Reseller_Yes']"))).click().perform();
	}
	
	public WebElement company() {
		return companyName;
	}
	
	public void selectOccupation() {
		Select occupation = new Select(driver.findElement(By.xpath("//select[@id='CreateAccountModel_OcupationTitle']")));
		occupation.selectByValue("Project Manager");
	}
	
	public WebElement enterAddress1() {
		return address1;
	}
	
	public WebElement enterAddress2() {
		return address2;
	}
	
	public WebElement enterPhoneNo() {
		return phoneNumber;
	}
	
	public WebElement enterCity() {
		return city;

	}
	
	public void clickGeoLoc() {
		geoLocation.click();
	}
	
	public void selectGeoLoc() {
		Select geo = new Select(driver.findElement(By.xpath("/html/body/article[1]/section[1]/header/div[2]/article/div[1]/div[1]/div/form[1]/div[1]/select")));
		geo.selectByValue("USA");
	}
	
	public void clickSave() {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("/html/body/article[1]/section[1]/header/div[2]/article/div[1]/div[1]/div/form[1]/button"))).click().perform();
	}
	
	public void selectGeoLanguage() {
		Select lang = new Select(geoLanguage);
		lang.getFirstSelectedOption().click();
	}
	
	public WebElement clickContinueBrowsingLink() {
		return continue_browsing;
	}
}