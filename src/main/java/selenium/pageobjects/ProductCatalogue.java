package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.utilities.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement loading;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".btn.w-10.rounded");
	By toastmsg = By.id("toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProduct(String productname) {
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
		}

	public void addToCart(String productname) {
		WebElement prod = getProduct(productname);
		prod.findElement(addToCartBy).click();
		waitForElementToAppear(toastmsg);
		waitElementToDisappear(loading);
	}
}
