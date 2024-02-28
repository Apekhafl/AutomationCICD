package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.utilities.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
 
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationmsg;
	
	By msgBy=By.cssSelector(".hero-primary");
	
	public String verifyConfirmationMessage() {
		waitForElementToAppear(msgBy);
		System.out.println(confirmationmsg.getText());
		return confirmationmsg.getText();
	}
}
