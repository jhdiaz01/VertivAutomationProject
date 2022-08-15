package pageObject;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.Base;

public class LoginPage {
	
	private static Logger LOGGER = LogManager.getLogger(LoginPage.class.getName());
	
	Base d = new Base();

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(xpath="//label[@class='desktop-nav-dropdown__btn-clear desktop-nav__lang-btn language-switcher']")
	WebElement country_lang_picker;
	
	@FindBy(xpath="//select[@name='countries']")
	WebElement countries;
	
	@FindBy(xpath="//form[contains(@name,'selectLangForm53')]//button[contains(@class,'select-lang__btn')][contains(text(),'Save')]")
	WebElement save;
		
	@FindBy(css="[class='desktop-nav-dropdown__btn']")
	WebElement accLogBtn;
		
	@FindBy(xpath="//div[contains(@class,'desktop-nav-dropdown__content')]//input[contains(@name,'loginModel.EmailAddress')]")
	WebElement username;
	
	@FindBy(xpath="//div[contains(@class,'desktop-nav-dropdown__content')]//input[contains(@name,'loginModel.Password')]")
	WebElement password;
	
	@FindBy(xpath="//div[@class='desktop-nav-dropdown__content']//button[@class='v3-button v3-button--primary header-login__login-btn'][contains(text(),'Login')]")
	WebElement subBtn;
	
	@FindBy(xpath="//a[contains(@class,'link link-- header-login__link')][contains(text(),'Create an account')]")
	WebElement createAcct;
	
	@FindBy(xpath="//div[@class='desktop-nav__topbar']//div[@class='header-my-account__sign-out']")
	WebElement logout;
	
	@FindBy(xpath="//div[@class='desktop-nav__topbar']//span[@class='header-my-account__label_text'][contains(text(),'Consultant')]")
	WebElement consultantTab;
	
	@FindBy(xpath="//div[@class='desktop-nav__topbar']//div[@class='header-my-account__tabs']//div[2]//a[1]")
	WebElement consultantDash;
	
	@FindBy(xpath="//div[contains(text(),'Log in to your account')]")
	WebElement suggestedSearchLogin;
	

	public void countryLanguage_picker() {
		country_lang_picker.click();
	}
	
	public void selectPHcountry() {
		Select country = new Select(driver.findElement(By.xpath("//select[@name='countries']")));
		country.selectByValue("PHL");
	}
	
	public void saveBtn() {
		save.click();
	}
	
	public void openNewTab() {
		try {
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get(Base.VertivURL);
			} catch (Exception e) {
				LOGGER.debug(e);
			}
	}
	
	public WebElement MyAcctLog() {
		return accLogBtn;
	}
	
	public WebElement email() {
		return username;
	}
	
	public WebElement pw() {
		return password;
	}
	
	public WebElement submitBtn() {
		return subBtn;
	}
	
	public WebElement logout() {
		return logout;
	}
	
	public WebElement createAcct() {
		return createAcct;
	}
	
	public WebElement conTab() {
		return consultantTab;
	}
	
	public WebElement conDash() {
		return consultantDash;
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public void suggestSearchLogin() {
		js.executeScript("arguments[0].scrollIntoView();", suggestedSearchLogin);
		suggestedSearchLogin.click();
	}
	
	
	//*****Objects from Login page*****
	
	@FindBy(css="body.collapsed.ng-scope:nth-child(24) article.page.homepage.transparent-page-header:nth-child(10) section.header-component.ng-scope:nth-child(3) header.p3-page-header.ng-scope div.p3-page-header__container.container:nth-child(4) article.desktop-nav.ng-scope div.desktop-nav__topbar div.desktop-nav-dropdown.ng-scope:nth-child(2) div.desktop-nav-dropdown__content form.header-login.ng-valid.ng-dirty.ng-valid-parse div.field-text.ng-scope.has-content:nth-child(2) > input.field-text__inp.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")
	WebElement loginUser;
	
	@FindBy(css="body.collapsed.ng-scope:nth-child(24) article.page.homepage.transparent-page-header:nth-child(10) section.header-component.ng-scope:nth-child(3) header.p3-page-header.ng-scope div.p3-page-header__container.container:nth-child(4) article.desktop-nav.ng-scope div.desktop-nav__topbar div.desktop-nav-dropdown.ng-scope:nth-child(2) div.desktop-nav-dropdown__content form.header-login.ng-valid.ng-dirty.ng-valid-parse div.field-password.ng-scope:nth-child(4) div.field-text.ng-scope.has-content > input.field-text__inp.field-password__inp.ng-valid.ng-not-empty.ng-dirty.ng-valid-parse.ng-touched")
	WebElement loginPass;
	
	@FindBy(xpath="//button[@class='button primary log-in']")
	WebElement submitBtn;
	
	@FindBy(xpath="/html/body/article[1]/section[1]/header/div[2]/article/div[1]/div[3]/div/div/a[1]")
	WebElement dash;
		
	
	public WebElement loginUsername() {
		return loginUser;
	}
	
	public WebElement loginPassword() {
		return loginPass;
	}
	
	public WebElement submitBtn2() {
		return submitBtn;
	}
	
	public WebElement dashboard() {
		return dash;
	}
	
	
	/*
	public boolean clickDashboard() {
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
				driver.findElement(By.xpath("/html/body/article[1]/section[1]/header/div[2]/article/div[1]/div[3]/div/div/a[1]"));
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				attempts++;
			}
		}
		return result;
	} */
	
	
	public void check_loginStatus_general() {
		
			String msg = driver.findElement(By.xpath("/html/body/article[1]/div/div[2]/div/div[1]")).getAttribute("class");
			String successMsg = "container general-welcome-section";
			

			
			if(msg.contains(successMsg)) {
			    System.out.println("General user has been logged in successfully.");
				}

				else {
						System.out.println("Error: Login did not proceed.");
		}
	}
	
	public void check_loginStatus_consultant() {
		
		String msg2 = driver.findElement(By.xpath("/html/body/article[1]/div[2]/div/div[4]")).getAttribute("class");
		String successMsg2 = "welcome";
		
		
		if(msg2.contains(successMsg2)) {
			  System.out.println("Consultant user has been logged in successfully.");
	} else {
		System.out.println("Error: Login did not proceed.");
		}
		
	}
}


