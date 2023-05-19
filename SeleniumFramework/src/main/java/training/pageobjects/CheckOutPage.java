package training.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import training.abstractcomponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// country text box
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryInput;

	// county options
	@FindBy(xpath = "//section/button")
	List<WebElement> countryOptions;

	// place order button
	@FindBy(xpath = "//a[contains(@class, 'submit')]")
	WebElement placeOrder;

	@FindBy(xpath = "//*[@class='payment__title']")
	static WebElement paymentTitle;

	List<WebElement> getCountryOptions(String input) {
		countryInput.sendKeys(input);
		waitForElementVisibility(countryOptions.get(0));
		return countryOptions;
	}

	public boolean selectCountry(String input, String option) {
		List<WebElement> countries = getCountryOptions(input);
		suggestiveTextBox(countries, option).get(0).click();
		return (getInputText(countryInput).equalsIgnoreCase(option));
	}

	public void placeOrder() {
		placeOrder.click();
	}

	public String getPageHeadther() {
		return paymentTitle.getText();
	}
}
