package training.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import training.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	// By locators
	// Items in cart
	static By cartSection = By.xpath("//ul[contains(@class,'cartWrap')]");

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[contains(@class,'cartWrap')]")
	List<WebElement> cart;

	@FindBy(xpath = "//*[contains(@class, 'totalRow')]/button")
	WebElement checkOutButton;

	@FindBy(xpath = "//h1[contains(text(), 'My Cart')]")
	static WebElement myCartTitle;

	// Action Methods
	List<WebElement> getCartItems() {
		waitForElementVisibility(cart.get(0));
		return cart;
	}

	public boolean verifyItemInCart(String productName) {
		boolean match = getCartItems().stream()
				.anyMatch(items -> items.findElement(cartSection).getText().contains(productName));
		return match;
	}

	public void goToCheckout() {
		checkOutButton.click();
	}

	public String getPageHeadther() {
		return myCartTitle.getText();
	}

}
