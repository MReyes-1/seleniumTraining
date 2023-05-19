package training.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import training.abstractcomponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> productNames;

	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean orderIsDisplayed(String productName) {
		boolean match;		
		match = productNames.stream().anyMatch(product->product.getText().contains(productName.toLowerCase()));
		return match;
	}		

}
