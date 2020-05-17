package SendboxTest;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import ReadFiles.ReadFiles;
import Sendbox.LogIn;
import Sendbox.Products;

import org.testng.annotations.BeforeTest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ProductsTest {
	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	
	public String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
	By CREATED = By.xpath ("//span[@class='form_valid large']");
	By MULES_PRICE = By.xpath("//input[@value='10100.00']");
	By BLOUSE_PRICE = By.xpath("//input[@value='3100.00']");
	By JACKET_PRICE = By.xpath("//input[@value='7090.00']");
	By DRESS_PRICE = By.xpath("//input[@value='5100.00']");
	By SHIRT_PRICE = By.xpath("//input[@value='3100.00']");
	

	@Test(priority = 1)
	public void openNewProductPage() {
		driver.navigate().to(LOGIN_URL);

		LogIn login = new LogIn(driver, ReadFiles.readLocators());
		login.inputUsername("ccastilloi");
		login.inputPassword("BruyEASQ5");
		login.clickSubmit();

		Products products = new Products(driver, ReadFiles.readLocators());
		products.addNewProductPage();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://sandbox.2checkout.com/sandbox/products/create_product";

		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(priority = 2)
	public void createProduct() throws InterruptedException {

		for (int i = 1; i <= 5; i++) {
			Products products = new Products(driver, ReadFiles.readLocators());
			products.createProduct(driver, i);
			
			WebElement created = driver.findElement(CREATED);
			sa.assertTrue(created.isDisplayed());
			sa.assertAll();
			
			products.addNewProduct();
		}
	}
	
	@Test(priority = 3)
	public void testUpdatePaintingPrices() {
		Products products = new Products(driver, ReadFiles.readLocators());
		
		products.updateMulesPrice(driver);
		String mules_price = driver.findElement(MULES_PRICE).getAttribute("value");
		String expected_price = "10100.00";
		sa.assertEquals(mules_price, expected_price);
		
		products.updateBlousePrice(driver);
		String actualFranzMarcUpdatedPrice = driver.findElement(BLOUSE_PRICE).getAttribute("value");
		String expectedFranzMarcUpdatedPrice = "3100.00";
		sa.assertEquals(actualFranzMarcUpdatedPrice, expectedFranzMarcUpdatedPrice);
		
		products.updateJacketPrice(driver);
		String actualKandinskyUpdatedPrice = driver.findElement(JACKET_PRICE).getAttribute("value");
		String expectedKandinskyUpdatedPrice = "7090.00";
		sa.assertEquals(actualKandinskyUpdatedPrice, expectedKandinskyUpdatedPrice);
		
		products.updateDressPrice(driver);
		String actualMatisseUpdatedPrice = driver.findElement(DRESS_PRICE).getAttribute("value");
		String expectedMatisseUpdatedPrice = "5100.00";
		sa.assertEquals(actualMatisseUpdatedPrice, expectedMatisseUpdatedPrice);
		
		products.updateShirtPrice(driver);
		String actualGauguinUpdatedPrice = driver.findElement(SHIRT_PRICE).getAttribute("value");
		String expectedGauguinUpdatedPrice = "3100.00";
		sa.assertEquals(actualGauguinUpdatedPrice, expectedGauguinUpdatedPrice);
		
		sa.assertAll();
	}

	@BeforeTest
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users\\Natasa\\eclipse-workspace\\SellerArea\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeDriver() {
		driver.quit();
	}
}

