package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.utilities.AbstractComponents;

public class LogInPage extends AbstractComponents {
	WebDriver driver;

	public LogInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement useremail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginbtn;

	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;

	public void getTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogue setLogin(String email, String pwd) {
		useremail.sendKeys(email);
		password.sendKeys(pwd);
		loginbtn.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	public String getErrormsg() {
		waitForWeElementToAppear(errormsg);
		String error=errormsg.getText();
		return error;
		
	}
}
