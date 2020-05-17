package Sendbox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Products {
	WebDriver driver;
	Map<String, String> locators;

	public String name, id, short_description, long_description, price, tangible, recurring, approved_url;
	private static final String PRODUCTS = "PRODUCTS";
	private static final String ADD_NEW_PRODUCT = "ADD_NEW_PRODUCT";
	private static final String PRODUCT_NAME = "PRODUCT_NAME";
	private static final String PRODUCT_ID = "PRODUCT_ID";
	private static final String PRODUCT_SHORTDESCR = "PRODUCT_SHORTDESCR";
	private static final String PRODUCT_LONGDESCR= "PRODUCT_LONGDESCR";
	private static final String PRODUCT_PRICE = "PRODUCT_PRICE";
	private static final String PRODUCT_TANGIBLE = "PRODUCT_TANGIBLE";
	private static final String PRODUCT_RECURRING = "PRODUCT_RECURRING";
	private static final String PRODUCT_SAVE = "PRODUCT_SAVE";
	private static final String PRODUCT_VIEW= "PRODUCT_VIEW";
	private static final String PRODUCT_EDIT = "PRODUCT_EDIT";
	private static final String MULES_PRICE= "MULES_PRICE";
	private static final String BLOUSE_PRICE= "BLOUSE_PRICE";
	private static final String JACKET_PRICE = "JACKET_PRICE";
	private static final String DRESS_PRICE = "DRESS_PRICE";
	private static final String SHIRT_PRICE = "SHIRT_PRICE";
	private static final String SAVE_CHANGES = "SAVE_CHANGES";

	public Products(String name, String id, String short_description, String long_description, String price,
			String tangible, String recurring, String approved_url) {

		this.name = name;
		this.id = id;
		this.short_description = short_description;
		this.long_description = long_description;
		this.price = price;
		this.tangible = tangible;
		this.recurring = recurring;
		this.approved_url = approved_url;
	}

	public Products(WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public void addNewProductPage() {
		driver.findElement(By.xpath(locators.get(PRODUCTS))).click();
		driver.findElement(By.xpath(locators.get(ADD_NEW_PRODUCT))).click();
	}

	public static String getDataProduct(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("C:\\Users\\Natasa\\Desktop\\Products.xlsx");
			wb = new XSSFWorkbook(fis);
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
	}

	public void createProduct(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(locators.get(PRODUCT_NAME))).sendKeys(getDataProduct(i, 0));
		driver.findElement(By.xpath(locators.get(PRODUCT_ID))).sendKeys(getDataProduct(i, 1));
		driver.findElement(By.xpath(locators.get(PRODUCT_SHORTDESCR))).sendKeys(getDataProduct(i, 2));
		driver.findElement(By.xpath(locators.get(PRODUCT_LONGDESCR))).sendKeys(getDataProduct(i, 3));
		driver.findElement(By.xpath(locators.get(PRODUCT_PRICE))).sendKeys(getDataProduct(i, 4));
		driver.findElement(By.xpath(locators.get(PRODUCT_TANGIBLE))).click();
		driver.findElement(By.xpath(locators.get(PRODUCT_RECURRING))).click();
		driver.findElement(By.xpath(locators.get(PRODUCT_SAVE))).click();

		Thread.sleep(2000);

	}

	public void addNewProduct() {
		driver.findElement(By.xpath(locators.get(PRODUCT_VIEW))).click();
		driver.findElement(By.xpath(locators.get(ADD_NEW_PRODUCT))).click();
	}

	public void updateMulesPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String mules = driver.findElement(By.xpath(locators.get(MULES_PRICE))).getAttribute("value");
		double mules_p = Double.parseDouble(mules);
		double mules_newp = mules_p + 100;
		String mulesnewp = String.valueOf(mules_newp);

		WebElement mules_newprice = driver.findElement(By.xpath(locators.get(MULES_PRICE)));
		mules_newprice.clear();
		mules_newprice.sendKeys(mulesnewp);

		driver.findElement(By.xpath(locators.get(SAVE_CHANGES))).click();
	}

	public void updateBlousePrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String blouse = driver.findElement(By.xpath(locators.get(BLOUSE_PRICE))).getAttribute("value");
		double blouse_p = Double.parseDouble(blouse);
		double blouse_newp = blouse_p + 100;
		String blousenewp = String.valueOf(blouse_newp);

		WebElement blousenewprice = driver.findElement(By.xpath(locators.get(BLOUSE_PRICE)));
		blousenewprice.clear();
		blousenewprice.sendKeys(blousenewp);
		
		driver.findElement(By.xpath(locators.get(SAVE_CHANGES))).click();
	}

	public void updateJacketPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String jacket = driver.findElement(By.xpath(locators.get(JACKET_PRICE))).getAttribute("value");
		double jacket_p = Double.parseDouble(jacket);
		double jacket_newp = jacket_p + 100;
		String jacketnewp = String.valueOf(jacket_newp);

		WebElement jacket_newprice = driver.findElement(By.xpath(locators.get(JACKET_PRICE)));
		jacket_newprice.clear();
		jacket_newprice.sendKeys(jacketnewp);
		
		driver.findElement(By.xpath(locators.get(SAVE_CHANGES))).click();
	}

	public void updateDressPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String dress = driver.findElement(By.xpath(locators.get(DRESS_PRICE))).getAttribute("value");
		double dress_p = Double.parseDouble(dress);
		double dress_newp = dress_p + 100;
		String dressnewp = String.valueOf(dress_newp);

		WebElement dress_newprice = driver.findElement(By.xpath(locators.get(DRESS_PRICE)));
		dress_newprice.clear();
		dress_newprice.sendKeys(dressnewp);
		
		driver.findElement(By.xpath(locators.get(SAVE_CHANGES))).click();
	}

	public void updateShirtPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String shirt = driver.findElement(By.xpath(locators.get(SHIRT_PRICE))).getAttribute("value"); 
		double shirt_p = Double.parseDouble(shirt);
		double shirt_newp = shirt_p + 100;
		String shirtnewp = String.valueOf(shirt_newp);

		WebElement shirt_newprice = driver.findElement(By.xpath(locators.get(SHIRT_PRICE)));
		shirt_newprice.clear();
		shirt_newprice.sendKeys(shirtnewp);
		
		driver.findElement(By.xpath(locators.get(SAVE_CHANGES))).click();
	}

}



