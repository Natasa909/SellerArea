package Sendbox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
	WebDriver driver;
	Map<String, String> locators;
	
	private static final String SIGNUP = "SIGNUP";
	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String EMAIL_XPATH = "EMAIL_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String CONFIRM_PASSWORD_XPATH = "CONFIRM_PASSWORD_XPATH";
	private static final String ABOUT_YOU_XPATH = "ABOUT_YOU_XPATH";
	private static final String ABOUT_YOU_OPTION_XPATH = "ABOUT_YOU_OPTION_XPATH";
	private static final String SUBMIT = "SUBMIT";
	private static final String ACCOUNT_AVATAR = "ACCOUNT_AVATAR";
	private static final String LOGOUT = "LOGOUT";

	public Registration(WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public void openRegPage(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(SIGNUP))).click();
	}

	public void inputUsername(String data) {
		driver.findElement(By.xpath(locators.get(USERNAME_XPATH))).sendKeys(data);
	}

	public void inputEmail(String data) {
		driver.findElement(By.xpath(locators.get(EMAIL_XPATH))).sendKeys(data);
	}

	public void inputPassword(String data) {
		driver.findElement(By.xpath(locators.get(PASSWORD_XPATH))).sendKeys(data);
	}

	public void inputConfirmPassword(String data) {
		driver.findElement(By.xpath(locators.get(CONFIRM_PASSWORD_XPATH))).sendKeys(data);
	}

	public void clickAboutYouMenu() {
		driver.findElement(By.xpath(locators.get(ABOUT_YOU_XPATH))).click();
	}

	public void clickAboutYouOption() {
		driver.findElement(By.xpath(locators.get(ABOUT_YOU_OPTION_XPATH))).click();
	}

	public void clickSubmitBtn() {
		driver.findElement(By.xpath(locators.get(SUBMIT))).click();
	}

	public static String getUsersData(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("C:\\Users\\Natasa\\Desktop\\Users.xlsx");
			wb = new XSSFWorkbook(fis);
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Invalid";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Invalid";
		}
	}

	public String reg30Users(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(locators.get(SIGNUP))).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath(locators.get(USERNAME_XPATH))).sendKeys(getUsersData(i, 1));
		driver.findElement(By.xpath(locators.get(EMAIL_XPATH))).sendKeys(getUsersData(i, 2));
		driver.findElement(By.xpath(locators.get(PASSWORD_XPATH))).sendKeys(getUsersData(i, 3));
		driver.findElement(By.xpath(locators.get(CONFIRM_PASSWORD_XPATH))).sendKeys(getUsersData(i, 4));
		driver.findElement(By.xpath(locators.get(ABOUT_YOU_XPATH))).click();
		driver.findElement(By.xpath(locators.get(ABOUT_YOU_OPTION_XPATH))).click();

		Thread.sleep(3000);
		
		driver.findElement(By.xpath(locators.get(SUBMIT))).click();

		return driver.getTitle();

	}
	
	public void logout() {
		driver.findElement(By.xpath(locators.get(ACCOUNT_AVATAR))).click();
		driver.findElement(By.xpath(locators.get(LOGOUT))).click();
	}
}

