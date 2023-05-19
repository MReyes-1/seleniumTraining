package training.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class StandAloneTest {

	public static void main(String[] args) { // variables
		String itemName = "ZARA";

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1920, 1080));		
		

		// login
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("randomemail@random.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password123");
		driver.findElement(By.id("login")).click();

		WebElement loadingAnimation = driver.findElement(By.className("ng-animating"));
		WebElement addedToCart = driver.findElement(By.className("toast-success"));
		WebElement cartButton = driver.findElement(By.xpath("//button[contains(@routerlink,\"cart\")]"));
		

		List<WebElement> products = driver.findElements(By.className("mb-3"));
		getProduct(products, itemName);
		waitForElement(loadingAnimation, wait);
		waitForElement(addedToCart, wait);
		waitForElementToDisappear(addedToCart, wait);
		cartButton.click();
		List<WebElement> cart = driver.findElements(By.xpath("//ul[contains(@class,\"cartWrap\")]"));
		productAdded(cart, itemName);
		WebElement checkOutButton = driver.findElement(By.xpath("//*[contains(@class, 'totalRow')]/button"));		
		checkOutButton.click();
		
		WebElement countryInput = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		WebElement placeOrder = driver.findElement(By.xpath("//a[contains(@class, 'submit')]"));		
		countryInput.sendKeys("IND");
		WebElement countryOptions = driver.findElement(By.xpath("//section/button"));
		waitForElement(countryOptions, wait);
		List<WebElement> countries = driver.findElements(By.xpath("//section/button"));
		countries.stream().filter(country->country.getText().equalsIgnoreCase("INDIA")).collect(Collectors.toList()).get(0).click();
		placeOrder.click();
		WebElement confirmation = driver.findElement(By.tagName("h1"));
		Boolean orderConfirmed = confirmation.getText().equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(orderConfirmed);
		
	}

	static void getProduct(List<WebElement> products, String itemName) {
		products.stream().filter(s -> s.findElement(By.tagName("b")).getText().contains(itemName)).findFirst()
				.orElse(null).findElement(By.className("w-10")).click();
	}

	static void productAdded(List<WebElement> products, String itemName) {
		boolean match = products.stream().anyMatch(s -> s.findElement(By.xpath("//ul[contains(@class,'cartWrap')]")).getText().contains(itemName));
		Assert.assertTrue(match);

	}

	static void waitForElement(WebElement element, WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	static void waitForElementToDisappear(WebElement element, WebDriverWait wait) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

}