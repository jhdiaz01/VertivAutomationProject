package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObject.HomePage;

public class Base {
	private static Logger LOGGER = LogManager.getLogger(HomePage.class.getName());
	
	public final static String VertivURL = "https://www.vertiv.com/en-us/";
	public final static String VertivPrep = "https://prep.vertiv.com/";
	public final static String eloqua_form_url = "https://www.vertiv.com/en-asia/testing-area/qa-validation/eloqua-forms-for-testing/automation-eloqua-form-test-page/";
	public final static String eloqua_form_url2 = "https://www.vertiv.com/en-asia/testing-area/qa-validation/eloqua-forms-for-testing/automation-eloqua-form-test-page2/";

	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\git\\VertivAutomationProject\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
				
		System.out.println("Initialize " + browserName);

		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\git\\VertivAutomationProject\\webdriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\git\\VertivAutomationProject\\webdriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		} else if (browserName.equals("chromeBeta")) {
			ChromeOptions optionsBeta = new ChromeOptions();
			optionsBeta.setBinary("C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.driver", "C:\\git\\VertivAutomationProject\\webdriver\\chromedriver.exe");
			driver = new ChromeDriver(optionsBeta);
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	
//	public void VertivURL() throws IOException {
//
//		Properties prop2 = new Properties();
//		FileInputStream fis = new FileInputStream(
//				"C:\\workspace\\VertivAutomationProject\\src\\main\\java\\resources\\data.properties");
//
//		prop2.load(fis);		
//		String url = prop2.getProperty("VertivURL");
//		driver.get(url);
//	}
	
	// DATA DRIVEN FOR GENERAL USER
	public ArrayList<String> getData(String getData) throws IOException {

		ArrayList<String> a = new ArrayList<String>();

		Properties prop = new Properties(); // This code is used to establish a connection from data.properties to Base
											// class (for test data setup)
		FileInputStream fis2 = new FileInputStream(
				"C:\\git\\VertivAutomationProject\\src\\main\\java\\resources\\data.properties");
		prop.load(fis2);

		FileInputStream fis = new FileInputStream(
				"C:\\git\\VertivAutomationProject\\src\\main\\java\\resources\\testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase("GeneralData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// identify test cases column by scanning the entire row
				Iterator<Row> rows = sheet.iterator(); // sheet is a collection of rows
				Row firstRow = rows.next();

				Iterator<Cell> ce = firstRow.cellIterator(); // row is a collection of cells
				int k = 0;
				int column = 0;

				while (ce.hasNext()) {

					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("testCase")) {

						column = k;
					}

					k++;
				}
				System.out.println(column);

				String testData = prop.getProperty("general"); // This code used is to fetch the data of "test" from
																// data.properties.

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().contains(testData)) {

						Iterator<Cell> cv = r.cellIterator();

						while (cv.hasNext()) {

							Cell c = cv.next();

							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}
				}
				workbook.close();
			}
		}
		return a;
	}

	// DATA DRIVEN FOR CONSULTANT
	public ArrayList<String> getDataConsultant(String getDataConsultant) throws IOException {

		ArrayList<String> a = new ArrayList<String>();
		
	
		Properties prop = new Properties(); // This code is used to establish a connection from data.properties to Base
											// class (for test data setup)
		FileInputStream fis2 = new FileInputStream(
				"C:\\git\\VertivAutomationProject\\src\\main\\java\\resources\\data.properties");
		prop.load(fis2);

		FileInputStream fis = new FileInputStream(
				"C:\\git\\VertivAutomationProject\\src\\main\\java\\resources\\testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase("ConsultantData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// identify test cases column by scanning the entire row
				Iterator<Row> rows = sheet.iterator(); // sheet is a collection of rows
				Row firstRow = rows.next();

				Iterator<Cell> ce = firstRow.cellIterator(); // row is a collection of cells
				int k = 0;
				int column = 0;

				while (ce.hasNext()) {

					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("testCase")) {

						column = k;
					}

					k++;
				}
				System.out.println(column);

				String testData = prop.getProperty("testDataConsultant"); // This code used is to fetch the data of
																			// "test" from data.properties.

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().contains(testData)) {

						Iterator<Cell> cv = r.cellIterator();

						while (cv.hasNext()) {

							Cell c = cv.next();

							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}
				}
				workbook.close();
			}
		}
		return a;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String DestinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(DestinationFile));
		return DestinationFile;
	}
	
	public void scrollDownToFooter() {
	   	JavascriptExecutor js = (JavascriptExecutor) driver;
	   	js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void resourcesColumn() throws InterruptedException {
	    	
	   	 Thread.sleep(3000L);
	   	 
	   	 WebElement resourcesDriver = driver.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__bottom > div > div > div.col-xs-12.col-sm-9.col-sm-push-3 > div > div:nth-child(1) > div.p3-page-footer__submenu"));
	   	 
	   	 //click on each link in the column and check if the pages are opening
	   	 for (int i=0; i<resourcesDriver.findElements(By.tagName("a")).size(); i++) {
	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
	   		 
	   		resourcesDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
	   	 }
	}
	
	public void supportColumn() throws InterruptedException {
	    	
	   	 Thread.sleep(3000L);
	   	 
	   	 WebElement supportDriver = driver.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__bottom > div > div > div.col-xs-12.col-sm-9.col-sm-push-3 > div > div:nth-child(2) > div.p3-page-footer__submenu"));
	   	 
	   	 //click on each link in the column and check if the pages are opening
	   	 for (int i=0; i<supportDriver.findElements(By.tagName("a")).size(); i++) {
	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
	   		 
	   		supportDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
	   		
	  		 if(i == 2)
	   			 
	   			 break;
	   	 }
	   	 for (int i=4; i<supportDriver.findElements(By.tagName("a")).size(); i++) {
	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
	   		 
	   		supportDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
	   		
	  		 if(i == 2)
	   			 
	   			 break; 
	   	 }
	}
	
	public void corporateColumn() throws InterruptedException {
		
		Thread.sleep(3000L);
		
		WebElement corporateDriver = driver.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__bottom > div > div > div.col-xs-12.col-sm-9.col-sm-push-3 > div > div:nth-child(3) > div.p3-page-footer__submenu"));
	   	 
	   	 //click on each link in the column and check if the pages are opening
	   	 for (int i=0; i<corporateDriver.findElements(By.tagName("a")).size(); i++) {
	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
	   		 
	   		corporateDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
	   	 }
	}
	
	public void clickSocialMediaLinks() throws InterruptedException {
		
		Thread.sleep(3000L);
		
		WebElement socialMediaDriver = driver.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__bottom > div > div > div.col-xs-12.col-sm-3.col-sm-pull-9 > div.socials"));
	   	 
	   	 //click on each link in the column and check if the pages are opening
	   	 for (int i=0; i<socialMediaDriver.findElements(By.tagName("a")).size(); i++) {
	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
	   		 
	   		socialMediaDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
	   	 }
	}
	
	public void visitEachLink() {
	   	 Set <String> abc = driver.getWindowHandles();
	   	 Iterator <String> it = abc.iterator();
	   	 
	   	 while (it.hasNext()){
	   		 try {
	   			driver.switchTo().window(it.next());
	   			System.out.println(((driver.getTitle())+" | is loaded successfully."));
	   		 }
	   		 catch(Exception e) {
	   			System.out.println(((driver.getTitle())+" | is NOT loaded successfully."));
	   		 }
	   	 }
	}
	
//	public void clickOnEachLink() throws InterruptedException {
//		//all links have a tagName of "a"
//	   	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	   	 
//	   	//limiting web driver scope
//	   	 WebElement footerBrand = driver.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__brands"));    
//	   	    	
//	   	 Thread.sleep(3000L);
//	   	 
//	   	 WebElement rowDriver = footerBrand.findElement(By.cssSelector("#module2 > footer > div.p3-page-footer__brands > div > div > div.p3-page-footer__brands_links.col-xs-12.col-sm-9"));
//	   	 
//	   	 //click on each link in the column and check if the pages are opening
//	   	 for (int i=0; i<rowDriver.findElements(By.tagName("a")).size(); i++) {
//	   		 String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
//	   		 
//	   		rowDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLink);
//	   		 
//	   	 }
//	}
	
//	public void navigateOnEachLink() {
//	   	 //to visit and print each page title
//	   	 Set <String> abc = driver.getWindowHandles();
//	   	 Iterator <String> it = abc.iterator();
//	   	 
//	   	 while (it.hasNext()){
//	   		 
//	   		 driver.switchTo().window(it.next());
//	   		 LOGGER.info(((driver.getTitle())+" | is loaded successfully."));
//	   		 
//	   	 }
//	}
	
	

}