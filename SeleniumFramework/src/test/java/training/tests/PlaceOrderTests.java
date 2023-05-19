package training.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import training.testComponents.BaseTest;

public class PlaceOrderTests extends BaseTest {

	String pass = "Password123";
	String user = "randomemail@random.com";
	String itemName = "ZARA";

	@Test
	public void submitOrder() throws IOException {		
		String inputText = "IND";
		String wantedCountry = "india";

		// Landing page login
		Assert.assertTrue(landing.logInSecuence(user, pass).equalsIgnoreCase(loginSuccessText()));
		// Product Catalog Page
		Assert.assertTrue(home.addProductToCart(itemName));
		home.goToCart();
		// my cart page
		Assert.assertTrue(cart.getPageHeadther().equalsIgnoreCase(cartPageHeader()));
		Assert.assertTrue(cart.verifyItemInCart(itemName));
		cart.goToCheckout();
		// Checkout page
		Assert.assertTrue(checkOut.getPageHeadther().equalsIgnoreCase(checkOutHeather()));
		Assert.assertTrue(checkOut.selectCountry(inputText, wantedCountry));
		checkOut.placeOrder();
		// Order Confirmation
		Assert.assertTrue(orderConfirmation.verification().equalsIgnoreCase(orederConfirmationText()));
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void wrongProductName() throws IOException {
		// Landing page login
		Assert.assertTrue(landing.logInSecuence(user, pass).equalsIgnoreCase(loginSuccessText()));
		// Product Catalog Page
		Assert.assertTrue(home.addProductToCart(itemName));
		home.goToCart();
		// my cart page
		Assert.assertTrue(cart.getPageHeadther().equalsIgnoreCase(cartPageHeader()));
		Assert.assertFalse(cart.verifyItemInCart(itemName+"AAA"));		
	}
	@Test
	public void verifyOrder() throws IOException {
		// Landing page login
		Assert.assertTrue(landing.logInSecuence(user, pass).equalsIgnoreCase(loginSuccessText()));
		// Product Catalog Page
		Assert.assertTrue(home.addProductToCart(itemName));
		home.goToOrders();
		// my cart page
		Assert.assertTrue(orders.orderIsDisplayed(itemName));			
	}

}