package SendboxTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ReadFiles.ReadFiles;
import Sendbox.LogIn;
import Sendbox.Registration2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterSuite;

public class RegistrationTest2 {
	WebDriver driver;
	WebDriverWait wait;
	SoftAssert sa = new SoftAssert();

	public String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
	public static final String USERNAME_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[2]/span";
	public static final String EMAIL_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[1]/span";
	public static final String PASSWORD_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[3]/span";
	public static final String PASSWORD_CONF_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[4]/span";
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users\\Natasa\\eclipse-workspace\\SellerArea\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*@BeforeMethod
	public void logIn() {
		driver.navigate().to(LOGIN_URL);
		//LogIn login = new LogIn(driver, ReadingFromFile.readXPaths());
		
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.typeUsername("jcogley14");
		registration.typePassword("y4FyhwdA5");
		registration.createUsernameBtn();
	}*/
	
	@BeforeMethod
	public void login() {
		driver.navigate().to(LOGIN_URL);
		LogIn login = new LogIn(driver, ReadFiles.readLocators());
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		
		login.inputUsername("ccastilloi");
		login.inputPassword("BruyEASQ5");
		login.clickSubmit();
		
		
	}
	@Test (priority = 1)
	public void openRegPage() {
		driver.navigate().to(LOGIN_URL);
		
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		
		registration.goToRegPage(driver);
		Assert.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/acct/create_username");
	}

	
	@Test(priority = 2)
	public void validUserRegistration() throws InterruptedException {
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		registration.goToRegPage(driver);
		registration.inputEmail("natasakon762@gmail.com");
		registration.inputUsername("Natasa123568876");
		registration.inputPassword("Itbootcamp123");
		registration.confirmPassword("Itbootcamp123");
		registration.selectAccess();
		registration.createUsernameBtn();
		
		Assert.assertEquals("Update User", driver.getTitle());
	}
	
	@Test(priority = 3)
	public void RegistrationWithoutUsername() throws InterruptedException {
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		registration.goToRegPage(driver);
		registration.inputEmail("natasako7n2@gmail.com");
		registration.inputPassword("Njfjeif");
		registration.confirmPassword("Njfjeif");
		registration.selectAccess();
		registration.createUsernameBtn();

		WebElement usernameErrorMessage = driver.findElement(By.xpath(USERNAME_MSG));
		Assert.assertTrue(usernameErrorMessage.isDisplayed());
	}
	
	@Test(priority = 4)
	public void InvalidRegistrationWithoutEmail() throws InterruptedException {
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		registration.goToRegPage(driver);
		registration.inputUsername("Natasa12135464");
		registration.inputPassword("94809385hH");
		registration.confirmPassword("94809385hH");
		registration.selectAccess();
		registration.createUsernameBtn();

		WebElement emailErrorMessage = driver.findElement(By.xpath(EMAIL_MSG));
		Assert.assertTrue(emailErrorMessage.isDisplayed());
	}

	@Test(priority = 5)
	public void InvalidRegistrationWithoutPassword() throws InterruptedException {
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		registration.goToRegPage(driver);
		registration.inputEmail("natasakon2@gmail.com");
		registration.inputUsername("Natasa12135464");
		registration.confirmPassword("94809385hH");
		registration.selectAccess();
		registration.createUsernameBtn();

		WebElement passwordErrorMessage = driver.findElement(By.xpath(PASSWORD_MSG));
		Assert.assertTrue(passwordErrorMessage.isDisplayed());
	}
	
	@Test(priority = 6)
	public void InvalidRegistrationWithoutPassConfirm() throws InterruptedException {
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		registration.goToRegPage(driver);
		registration.inputEmail("natasakon2@gmail.com");
		registration.inputUsername("Natasa12135464");
		registration.confirmPassword("94809385hH");
		registration.selectAccess();
		registration.createUsernameBtn();
		
		WebElement confirmPasswordErrorMessage = driver.findElement(By.xpath(PASSWORD_CONF_MSG));
		Assert.assertTrue(confirmPasswordErrorMessage.isDisplayed());
	}
	
	@Test(priority = 7)
	public void Register30Users() throws InterruptedException {

		for (int i = 1; i <= 30; i++) {

			Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
			registration.goToRegPage(driver);
			
			String actualTitle = registration.Reg30Users(driver, i);
			sa.assertEquals(actualTitle, "Update User");
			sa.assertAll();
			
			registration.logOut();
			
			LogIn login = new LogIn(driver, ReadFiles.readLocators());
			

			login.inputUsername("ccastilloi");
			login.inputPassword("BruyEASQ5");
			login.clickSubmit();
		}
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
	
	@AfterMethod
	public void LogOut () {
		Registration2 registration = new Registration2(driver, ReadFiles.readLocators());
		registration.logOut();

	}
}


  

  


