package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.TestComponents.BaseTest;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.CheckoutPage;
import selenium.pageobjects.ConfirmationPage;
import selenium.pageobjects.LogInPage;
import selenium.pageobjects.ProductCatalogue;

public class StepDefinitionSubmitOrder extends BaseTest {
	LogInPage logingpage;
	ProductCatalogue productcatalogue;
	CartPage cartpage;
	CheckoutPage cpage;
	ConfirmationPage confirmpage;

	@Given("I laned on Ecommerce platform")
	public void landing_on_ecommerce_page() throws IOException {
		logingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_application(String username, String password) {
		productcatalogue = logingpage.setLogin(username, password);
	}

	@When("I add product (.+) to the cart$")
	public void i_add_product_to_cart(String productName) {
		productcatalogue.addToCart(productName);
		cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyItemsInCart(productName);
		Assert.assertTrue(match);
	}

	@And("^Enter (.+) Checkout and submit the order$")
	public void checkout_and_submit_order(String countryName) throws InterruptedException {
		cpage = cartpage.goToCheckout();
		cpage.selectCountry(countryName);
	}
    
	@Then("{string} message is displayed on Confirmation Page")
	public void confirmation_message_is_displayed(String string) throws InterruptedException {
		confirmpage = cpage.placeOrder();
		String message = confirmpage.verifyConfirmationMessage();
		Assert.assertTrue(message.trim().equalsIgnoreCase("Thankyou for the order."));
	    driver.close();
	}
}
