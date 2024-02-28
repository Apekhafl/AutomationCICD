package selenium.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.utilities.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;

	@FindBy(css = ".totalRow button")
	WebElement checkoutbtn;
	
	By producttitle = By.cssSelector(".cartSection h3");
	By checkout = By.cssSelector(".totalRow button");

	public Boolean verifyItemsInCart(String productname){
		waitForElementToAppear(producttitle);
		Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckoutPage goToCheckout() throws InterruptedException {
		scroll();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
	    wait.until(ExpectedConditions.elementToBeClickable(checkoutbtn)).click();
	    CheckoutPage cpage=new CheckoutPage(driver);
	    return cpage;
	}
}
