package selenium.SeleniumTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.TestComponents.Retry;
import selenium.TestComponents.BaseTest;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {
	String productname="ZARA COAT 3";
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void getLoginErrorValidation() {
		lp.setLogin("apex@gmail.com", "Apex1995");
		AssertJUnit.assertEquals("Incorrect email or password.", lp.getErrormsg());
		System.out.println(lp.getErrormsg());
	}

	@Test
	public void getProductErrorValidation() {
		ProductCatalogue pc = lp.setLogin("apex@gmail.com", "Apex@1995");
		pc.addToCart(productname);
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyItemsInCart(productname);
		Assert.assertTrue(match);
	}
}
