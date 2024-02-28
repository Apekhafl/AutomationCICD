package selenium.SeleniumTesting;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.TestComponents.BaseTest;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.CheckoutPage;
import selenium.pageobjects.ConfirmationPage;
import selenium.pageobjects.LogInPage;
import selenium.pageobjects.OrderPage;
import selenium.pageobjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	String productname = "ZARA COAT 3";
	String countryName = "India";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitorder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue pc = lp.setLogin(input.get("email"), input.get("password"));
		pc.addToCart(input.get("product"));
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyItemsInCart(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage cpage = cp.goToCheckout();
		cpage.selectCountry(countryName);
		ConfirmationPage confirmpage = cpage.placeOrder();
		String message = confirmpage.verifyConfirmationMessage();
		Assert.assertTrue(message.trim().equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitorder" })
	public void orderHistory() throws InterruptedException {
		ProductCatalogue pc = lp.setLogin("apex@gmail.com", "Apex@1995");
		OrderPage orderpage = pc.goToOrderPage();
		Assert.assertTrue(orderpage.verifyItemInOrderPage(productname));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "apex@gmail.com"); map.put("password", "Apex@1995"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "apex@gmail.com"); map1.put("password", "Apex@1995");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 */
		// return new Object[][] {{"apex@gmail.com","Apex@1995","ZARA COAT
		// 3"},{"apex@gmail.com","Apex@1995","ADIDAS ORIGINAL"}};
		
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "//src//test//java//selenium//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
