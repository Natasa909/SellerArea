package SendboxTest;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ReadFiles.ReadFiles;
import Sendbox.Registration;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegistrationTest {
	
	WebDriver driver;
	public String LOG_IN_URL="https://sandbox.2checkout.com/sandbox";
	public String HOME_PAGE_URL = "https://sandbox.2checkout.com/sandbox/home/dashboard";
	public String SIGN_UP_URL = "https://sandbox.2checkout.com/sandbox/signup";
	
	By USERNAME_ERROR_MSG = By.xpath("//div[@id='username_message']");
	By EMAIL_ERROR_MSG=By.xpath("//div[@id='email_error']");
	By PASSWORD_ERROR_MSG = By.xpath("//div[@id='password_error']");
	By CONFIRM_PASSWORD_MSG = By.xpath("//div[@id='confirm_error']");
	By ABOUTYOU_ERROR_MSG = By.xpath("//div[@id='aboutyou_error']");
	@BeforeClass
		public void createDriver() {
			System.setProperty("webdriver.chrome.driver", "/Users\\Natasa\\eclipse-workspace\\SellerArea\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	  

  @Test(priority = 1)
  public void openRegPage() {
	  driver.navigate().to(LOG_IN_URL);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.openRegPage(driver);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = SIGN_UP_URL;

		Assert.assertEquals(actualUrl, expectedUrl);

  }
  @Test(priority = 2)
	public void testValidUserReg() throws InterruptedException {
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("Natasak");
		registration.inputEmail("natasak@gmail.com");
		registration.inputPassword("natasaK1995");
		registration.inputConfirmPassword("natasaK1995");
		registration.clickAboutYouMenu();
		registration.clickAboutYouOption();
		registration.clickSubmitBtn();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = HOME_PAGE_URL;

		Assert.assertEquals(actualUrl, expectedUrl);
	}

  @Test(priority = 3)
	public void RegWithoutUsernameData() throws InterruptedException {
		driver.navigate().to(SIGN_UP_URL);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputEmail("natasak1@gmail.com");
		registration.inputPassword("natasaK1995");
		registration.inputConfirmPassword("natasaK1995");
		registration.clickAboutYouMenu();
		registration.clickAboutYouOption();
		registration.clickSubmitBtn();

		WebElement username_error_msg = driver.findElement(USERNAME_ERROR_MSG);
		Assert.assertTrue(username_error_msg.isDisplayed());
	}

	@Test(priority = 4)
	public void RegWithoutEmailData() throws InterruptedException {
		driver.navigate().to(SIGN_UP_URL);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("natasaK19955");
		registration.inputPassword("natasaK1995");
		registration.inputConfirmPassword("natasaK1995");
		registration.clickAboutYouMenu();
		registration.clickAboutYouOption();
		registration.clickSubmitBtn();

		WebElement email_error_msg = driver.findElement(EMAIL_ERROR_MSG);
		Assert.assertTrue(email_error_msg.isDisplayed());
	}

	@Test(priority = 5)
	public void RegWithoutPasswordData() throws InterruptedException {
		driver.navigate().to(SIGN_UP_URL);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("natasaK19955");
		registration.inputEmail("natasak21@gmail.com");
		registration.inputConfirmPassword("natasaK1995");
		registration.clickAboutYouMenu();
		registration.clickAboutYouOption();
		registration.clickSubmitBtn();

		WebElement password_error_msg = driver.findElement(PASSWORD_ERROR_MSG);
		Assert.assertTrue(password_error_msg.isDisplayed());
	}

	@Test(priority = 6)
	public void RegWithoutConfirmPasswordData() throws InterruptedException {
		driver.navigate().to(SIGN_UP_URL);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("natasaK199555");
		registration.inputEmail("natasak21@gmail.com");
		registration.inputPassword("natasaK1995");
		registration.clickAboutYouMenu();
		registration.clickAboutYouOption();
		registration.clickSubmitBtn();

		WebElement confirmPasswordErrorMessage = driver.findElement(CONFIRM_PASSWORD_MSG);
		Assert.assertTrue(confirmPasswordErrorMessage.isDisplayed());
	}

	@Test(priority = 7)
	public void RegWithoutChoosingAboutYouOpt() throws InterruptedException {
		driver.navigate().to(SIGN_UP_URL);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("NatasaK199555");
		registration.inputEmail("natasak213@gmail.com");
		registration.inputPassword("natasaK1995");
		registration.inputConfirmPassword("natasaK1995");
		registration.clickSubmitBtn();

		WebElement aboutYouErrorMessage = driver.findElement(ABOUTYOU_ERROR_MSG);
		Assert.assertTrue(aboutYouErrorMessage.isDisplayed());
	}
	@Test(priority = 8)
	public void Registrate30Users() throws InterruptedException {

		for (int i = 1; i <= 30; i++) {
			SoftAssert sa = new SoftAssert();
			driver.navigate().to(SIGN_UP_URL);
			Registration registration = new Registration(driver, ReadFiles.readLocators());
			
			String actualTitle = registration.reg30Users(driver, i);
			sa.assertEquals(actualTitle, "Seller Area / Home");
			sa.assertAll();
			
			registration.logout();
		}
	}


  
  @AfterClass
  public void closeDriver() {
		driver.quit();
	}
}
