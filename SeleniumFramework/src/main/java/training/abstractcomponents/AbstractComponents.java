package training.abstractcomponents;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import training.pageobjects.CartPage;
import training.pageobjects.HomePage;
import training.pageobjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;
	int maxDuration = 5;
	
	HomePage home;
	OrdersPage orders;
	CartPage cart;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Banner Elements	
	@FindBy(xpath = "//button[@routerlink = '/dashboard/']")
	WebElement homeButton;
	
	@FindBy(xpath = "//button[contains(@routerlink,'myorders')]")
	WebElement ordersButton;

	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement cartButton;

	@FindBy(className = "fa-sign-out")
	WebElement signOutButton;

	public HomePage goToHome() {
		homeButton.click();
		home = new HomePage(driver);
		return home;
	}
	
	public OrdersPage goToOrders() {
		ordersButton.click();
		orders = new OrdersPage(driver);
		return orders;
	}
	
	public CartPage goToCart() {
		cartButton.click();
		cart = new CartPage(driver);
		return cart;
	}
	
	public void signOut() {
		signOutButton.click();
	}

	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxDuration));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public List<WebElement> suggestiveTextBox(List<WebElement> elements, String option) {
		return elements.stream().filter(country -> country.getText().equalsIgnoreCase(option))
				.collect(Collectors.toList());

	}

	public String getInputText(WebElement countryInput) {
		return countryInput.getAttribute("value");
	}

}
