package training.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import training.abstractcomponents.AbstractComponents;

public class HomePage extends AbstractComponents {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// By locators
	// Displayed product name
	static By productBy = By.tagName("b");
	// Add to cart button
	static By addToCart = By.className("w-10");

	// Page Factory
	// Catalog elements
	@FindBy(className = "mb-3")
	List<WebElement> products;

	// waiting spinner
	@FindBy(className = "ng-animating")
	WebElement spinner;

	// added to cart toast
	@FindBy(className = "toast-success")
	WebElement toastAdded;

	// Action Methods
	List<WebElement> getProductCatalog() {
		waitForElementVisibility(products.get(1));
		return products;
	}

	public boolean addProductToCart(String productName) {
		WebElement prod = getProductCatalog().stream()
				.filter(product -> product.findElement(productBy).getText().contains(productName)).findFirst()
				.orElse(null);
		if (prod != null) {
			prod.findElement(addToCart).click();
			waitForElementVisibility(spinner);
			// waitForElementInvisibility(spinner);
			waitForElementVisibility(toastAdded);
			waitForElementInvisibility(toastAdded);
			return true;
		} else {
			return false;
		}
	}
}
