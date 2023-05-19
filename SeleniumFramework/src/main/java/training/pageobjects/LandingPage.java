package training.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import training.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	// local variables
	WebDriver driver;

	// constructor
	public LandingPage(WebDriver driver) {
		// driver initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory

	// UserID box
	@FindBy(id = "userEmail")
	WebElement userID;

	// Password box
	@FindBy(id = "userPassword")
	WebElement password;

	// Login button
	@FindBy(id = "login")
	WebElement loginButton;

	// Login confirmation
	@FindBy(xpath = "//*[@aria-label='Login Successfully']")
	WebElement loginToast;

	// Login failed
	@FindBy(className = "toast-error")
	WebElement loginToastFail;

	// Action Methods

	public void goToLandingPage(String landingURL) {
		driver.get(landingURL);
	}

	public String getLandingPageTitle() {
		return driver.getTitle();
	}

	public String logInSecuence(String id, String pass) {
		String text = null;
		userID.sendKeys(id);
		password.sendKeys(pass);
		loginButton.click();

		try {
			if (loginToast.isDisplayed()) {
				text = loginToast.getText();
				waitForElementInvisibility(loginToast);
			}
		} catch (NoSuchElementException e) {
			if (loginToastFail.isDisplayed() == true) {
				text = loginToastFail.getText();
				waitForElementInvisibility(loginToastFail);
			}
		}
		return text;
	}
}
