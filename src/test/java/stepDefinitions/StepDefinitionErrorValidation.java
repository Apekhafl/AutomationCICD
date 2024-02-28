package stepDefinitions;

import org.testng.AssertJUnit;

import io.cucumber.java.en.Then;
import selenium.TestComponents.BaseTest;
import selenium.pageobjects.LogInPage;

public class StepDefinitionErrorValidation extends BaseTest{
	LogInPage loginpage;
    @Then("{string} message is displayed as credentials are invalid")
    public void error_message_is_displayed(String incorrectMsg) {
    	loginpage.setLogin("apex@gmail.com", "Apex1995");
		AssertJUnit.assertEquals("Incorrect email or password.", loginpage.getErrormsg());
		System.out.println(loginpage.getErrormsg());
    }
}
