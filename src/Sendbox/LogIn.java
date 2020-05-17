package Sendbox;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LogIn {
	WebDriver driver;
	Map<String, String> locators;

	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String SUBMIT = "SUBMIT";
	private static final String ACCOUNT_AVATAR = "ACCOUNT_AVATAR";
	private static final String LOGOUT = "LOGOUT";


	public LogIn(WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public void inputUsername(String data) {
		driver.findElement(By.xpath(locators.get(USERNAME_XPATH))).sendKeys(data);
	}

	public void inputPassword(String data) {
		driver.findElement(By.xpath(locators.get(PASSWORD_XPATH))).sendKeys(data);
	}

	public void clickSubmit() {
		driver.findElement(By.xpath(locators.get(SUBMIT))).click();
	}

	public void logout() {
		driver.findElement(By.xpath(locators.get(ACCOUNT_AVATAR))).click();
		driver.findElement(By.xpath(locators.get(LOGOUT))).click();
	}
}



