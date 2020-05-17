package SendboxTest;



import ReadFiles.ReadFiles;
import Sendbox.LogIn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

public class LogInTest {
   WebDriver driver;
	
	public String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
	By ERROR_MSG = By.xpath("//div[@id='login-error']");
	
	@BeforeTest
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users\\Natasa\\eclipse-workspace\\SellerArea\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(LOGIN_URL);
	}

  
  @Test(priority=1)
	public void loginValidUser() {
		LogIn login = new LogIn(driver, ReadFiles.readLocators());
		login.inputUsername("ccastilloi");
		login.inputPassword("BruyEASQ5");
		login.clickSubmit();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://sandbox.2checkout.com/sandbox/home/dashboard";
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}
  @Test(priority=2)
  public void loginWithoutRegistration() {
	  LogIn login = new LogIn(driver, ReadFiles.readLocators());
		login.logout();
		login.inputUsername("Natasa123");
		login.inputPassword("12345");
		login.clickSubmit();
		
		WebElement error = driver.findElement(ERROR_MSG);
	    Assert.assertTrue(error.isDisplayed());
  }
  
  @AfterTest
	public void closeDriver() {
		driver.quit(); 
	}
}
 
  

   
