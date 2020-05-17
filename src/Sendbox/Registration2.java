package Sendbox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration2 {
	WebDriver driver;
	Map<String, String> locators;
	
	private static final String ACCOUNT = "ACCOUNT";
	private static final String USER_MANAGMENT = "USER_MANAGMENT";
	private static final String CREATE_USERNAME = "CREATE_USERNAME";
	private static final String EMAIL_R = "EMAIL_R";
	private static final String USERNAME_R = "USERNAME_R";
	private static final String PASSWORD_R = "PASSWORD_R";
	private static final String CONFIRM_R = "CONFIRM_R";
	private static final String CREATE_BTN = "CREATE_BTN";
	//private static final String UPDATEUSER = "UPDATEUSER";
	private static final String ACCOUNT_AVATAR = "ACCOUNT_AVATAR";
	private static final String LOGOUT = "LOGOUT";
	//private static final String USERNAME_XPATH = "USERNAME_XPATH";
	//private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String LOGINBTN_XPATH = "LOGINBTN_XPATH";
	
	
	public Registration2 (WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}
		
	public void goToRegPage(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(ACCOUNT))).click();
		driver.findElement(By.xpath(locators.get(USER_MANAGMENT))).click();
		driver.findElement(By.xpath(locators.get(CREATE_USERNAME))).click();
		
	}
	
	public void inputEmail (String data) {
		driver.findElement(By.xpath(locators.get(EMAIL_R))).sendKeys(data);
	}
	
	public void inputUsername (String data) {
		driver.findElement(By.xpath(locators.get(USERNAME_R))).sendKeys(data);
	}
	
	public void inputPassword (String data) {
		driver.findElement(By.xpath(locators.get(PASSWORD_R))).sendKeys(data);
	}
	
	public void confirmPassword (String data) {
		driver.findElement(By.xpath(locators.get(CONFIRM_R))).sendKeys(data);
	}
	
	public void selectAccess () {
		driver.findElement(By.id("sa-access")).click();
		driver.findElement(By.id("va_user1")).click();
	}
	
	public void createUsernameBtn () {
		driver.findElement(By.xpath(locators.get(CREATE_BTN))).click();
	}
	
	public void registerNew () {
		
		driver.findElement(By.xpath(locators.get(ACCOUNT))).click();
		driver.findElement(By.xpath(locators.get(USER_MANAGMENT))).click();
		driver.findElement(By.xpath(locators.get(CREATE_USERNAME))).click();
	}
	
	public static String getUsersData(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("C:\\Users\\Natasa\\Desktop\\Users.xlsx");
			wb = new XSSFWorkbook(fis);
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed";
		}
	}
	
	public String Reg30Users(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(locators.get(ACCOUNT))).click();
		driver.findElement(By.xpath(locators.get(USER_MANAGMENT))).click();
		driver.findElement(By.xpath(locators.get(CREATE_USERNAME))).click();
		
		Thread.sleep(1000);

		driver.findElement(By.xpath(locators.get(USERNAME_R))).sendKeys(getUsersData(i, 1));
		driver.findElement(By.xpath(locators.get(EMAIL_R))).sendKeys(getUsersData(i, 2));
		driver.findElement(By.xpath(locators.get(PASSWORD_R))).sendKeys(getUsersData(i, 3));
		driver.findElement(By.xpath(locators.get(CONFIRM_R))).sendKeys(getUsersData(i, 4));
		driver.findElement(By.id("sa-access")).click();
		driver.findElement(By.id("va_user1")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(locators.get(CREATE_BTN))).click();
		
		return driver.getTitle();

	}
	
	public void logOut() {
		driver.findElement(By.xpath(locators.get(ACCOUNT_AVATAR))).click();
		driver.findElement(By.xpath(locators.get(LOGOUT))).click();
	}
}

