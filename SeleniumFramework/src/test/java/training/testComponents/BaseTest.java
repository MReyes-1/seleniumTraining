package training.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import training.pageobjects.CartPage;
import training.pageobjects.CheckOutPage;
import training.pageobjects.HomePage;
import training.pageobjects.LandingPage;
import training.pageobjects.OrderConfirmationPage;
import training.pageobjects.OrdersPage;

public class BaseTest {

	static WebDriver driver;
	FileInputStream file;	
	Properties properties;
	
	final String landingURL = "https://rahulshettyacademy.com/client";
	final String landingTitle = "Let's Shop";
	final String loginSuccessMessage ="Login Successfully";
	final String loginFailMessage ="Incorrect email or password.";
	final String cartPageTitle = "My Cart"; 
	final String paymentPageTitle = "Payment Method";
	final String successfulOrderText = "THANKYOU FOR THE ORDER.";
	
	public LandingPage landing;
	public HomePage home;
	public CartPage cart;
	public OrderConfirmationPage orderConfirmation;
	public CheckOutPage checkOut;
	public OrdersPage orders;
	

	void initializeDriver() throws IOException {

		properties = new Properties();
		file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\training\\resources\\GlobalData.properties");
		properties.load(file);
		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();			
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1920, 1080));
	}
	
	@BeforeMethod(alwaysRun=true)//to run in all groups
	public void lunchBrowser() throws IOException {
		initializeDriver();
		landing = new LandingPage(driver);
		home = new HomePage(driver);
		cart = new CartPage (driver);
		orderConfirmation = new OrderConfirmationPage(driver);
		checkOut = new CheckOutPage(driver);
		orders = new OrdersPage(driver);
		
		landing.goToLandingPage(landingURL);		
		Assert.assertTrue(landing.getLandingPageTitle().equalsIgnoreCase(landingTitle));
		
	}
	@AfterMethod(alwaysRun=true)//to run in all groups
	public void closeBrowser() {
		driver.close();
	}
	public String cartPageHeader() {
		return cartPageTitle;
	}
	
	public String checkOutHeather() {
		return paymentPageTitle;
	}
	
	public String orederConfirmationText() {
		return successfulOrderText;
	}
	
	public String loginSuccessText() {
		return loginSuccessMessage;
	}	
	public String loginFailText() {
		return loginFailMessage;
	}
	
}
