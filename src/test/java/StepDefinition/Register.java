package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import Input.Excel;
import Library.CommonLib;
import UIConstant.ConstantMailinator;
import UIConstant.ConstantRegister;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Register extends CommonLib {
	
	private static String sheetRegistration = CommonLib.getValueFromProperty("sheetRegistration");
	private static String sheetLogin = CommonLib.getValueFromProperty("sheetLogin");
	
	@After
	public void closeApp() throws Throwable {
		CommonLib.closeApplication();
	}
	
	@Given("User launch My Account page of PSegameshop web apps")
	public void user_launch_my_account_page_of_p_segameshop_web_apps() throws Throwable {
	    CommonLib.AppLaunch();
	    CommonLib.shortSleep();
	}

	@Then("User is on My Account page")
	public void user_is_on_my_account_page() throws Throwable {
	    CommonLib.isElementVerification(ConstantRegister.ACCOUNT_PAGE);
	}

	@When("User fills Registration Account form {string}")
	public void user_fills_registration_account_form(String rowCount) throws Throwable {
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_INPUT);
	    CommonLib.enterValue(ConstantRegister.REGISTER_EMAIL_FIELD, Excel.get("regEmail", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_CONFIRM_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	}

	@When("User clicks Register button")
	public void user_clicks_register_button() throws Throwable {
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_BTN);
	    CommonLib.shortSleep();
	}

	@Then("User is successfully register an account")
	public void user_is_successfully_register_an_account() throws Throwable {
	    CommonLib.textValidation(ConstantRegister.REGISTER_ALERT, "Thank you for registering. Your account has to be activated before you can login. Please check your email.");
	}

	@Then("User open opens Registration verification link {string}")
	public void user_open_opens_registration_verification_link(String rowCount) throws Throwable {
	    CommonLib.navigateToUrl("https://www.mailinator.com/");
	    CommonLib.enterValue(ConstantMailinator.SEARCH_MAIL, Excel.get("regEmail", rowCount, sheetRegistration));
	    driver.findElement(By.xpath(ConstantMailinator.SEARCH_MAIL)).sendKeys(Keys.ENTER); Thread.sleep(2000);
	    CommonLib.isElementVerifyClick(ConstantMailinator.INBOX_ACTIVATE_ACCOUNT); Thread.sleep(1000);
	    driver.switchTo().frame("html_msg_body");
	    CommonLib.isElementVerifyClick(ConstantMailinator.LINK_ACTIVATE_ACCOUNT);
	    driver.switchTo().defaultContent();
	}

	@Then("User is successfully verify an account")
	public void user_is_successfully_verify_an_account() throws Throwable {
		CommonLib.switchTab();
	    Thread.sleep(3000);
	    CommonLib.textValidation(ConstantRegister.REGISTER_ALERT, "Success: Your account has been activated!");
	}
	
	//Errors
	@When("User enters invalid email address on Registration Account form {string}")
	public void user_enters_invalid_email_address_on_registration_account_form(String rowCount) throws Throwable {
		CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_INPUT);
	    CommonLib.enterValue(ConstantRegister.REGISTER_EMAIL_FIELD, Excel.get("regEmailInvalid", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_CONFIRM_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	}

	@Then("User should be able to see error message due to invalid email address on Registration Account form")
	public void user_should_be_able_to_see_error_message_due_to_invalid_email_address_on_registration_account_form() throws Throwable {
		Thread.sleep(1000);
		CommonLib.textValidation(ConstantRegister.REGISTER_ALERT, "Error: Please provide a valid email address.");
	}

	@When("User enters invalid password requirement on Registration Account form {string}")
	public void user_enters_invalid_password_requirement_on_registration_account_form(String rowCount) throws Throwable {
		CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_INPUT);
	    CommonLib.enterValue(ConstantRegister.REGISTER_EMAIL_FIELD, Excel.get("regEmail", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_PASSWORD_FIELD, Excel.get("regPasswordInvalid", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_CONFIRM_PASSWORD_FIELD, Excel.get("regPasswordInvalid", rowCount, sheetRegistration));
	}

	@Then("User should be able to see error message due to invalid password requirement on Registration Account form")
	public void user_should_be_able_to_see_error_message_due_to_invalid_password_requirement_on_registration_account_form() throws Throwable {
		Thread.sleep(1000);
		CommonLib.textValidation(ConstantRegister.REGISTER_ERR_PASS, "Weak - Please enter a stronger password.");
	    CommonLib.textValidation(ConstantRegister.REGISTER_ERR_PASS_HINT, "Hint: The password should be at least twelve characters long. To make it stronger, use upper and lower case letters, numbers, and symbols like ! \" ? $ % ^ & ).");
	    CommonLib.scrollToTopPage();
	}

	@When("User enters unmatched password on Registration Account form {string}")
	public void user_enters_unmatched_password_on_registration_account_form(String rowCount) throws Throwable {
		CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_INPUT);
	    CommonLib.enterValue(ConstantRegister.REGISTER_EMAIL_FIELD, Excel.get("regEmail2", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_CONFIRM_PASSWORD_FIELD, Excel.get("regPasswordInvalid", rowCount, sheetRegistration));
	}

	@Then("User should be able to see error message due to unmatched password on Registration Account form")
	public void user_should_be_able_to_see_error_message_due_to_unmatched_password_on_registration_account_form() throws Throwable {
		Thread.sleep(1000);
	    CommonLib.textValidation(ConstantRegister.REGISTER_ALERT, "Error: Passwords do not match");
	}

	@When("User mandatory fields as empty on Registration Account form {string}")
	public void user_mandatory_fields_as_empty_on_registration_account_form(String string) throws Throwable {
	    CommonLib.navigateToUrl("https://www.psegameshop.com/my-account/");
	}

	@Then("User should be able to see error message due to mandatory fields are empties on Registration Account form")
	public void user_should_be_able_to_see_error_message_due_to_mandatory_fields_are_empties_on_registration_account_form() throws Throwable {
		Thread.sleep(1000);	   
		CommonLib.textValidation(ConstantRegister.REGISTER_ALERT, "Error: Please provide a valid email address.");
	}
	
	@When("User enters the registered account on Registration Account form {string}")
	public void user_enters_the_registered_account_on_registration_account_form(String rowCount) throws Throwable {
		CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_GENRE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_CONSOLE_INPUT);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_FIELD);
	    CommonLib.isElementVerifyClick(ConstantRegister.REGISTER_HOBBY_INPUT);
	    CommonLib.enterValue(ConstantRegister.REGISTER_EMAIL_FIELD, Excel.get("logEmail", rowCount, sheetLogin));
	    CommonLib.enterValue(ConstantRegister.REGISTER_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	    CommonLib.enterValue(ConstantRegister.REGISTER_CONFIRM_PASSWORD_FIELD, Excel.get("regPassword", rowCount, sheetRegistration));
	}

	@Then("User should be able to see error message due to the registered account on Registration Account form")
	public void user_should_be_able_to_see_error_message_due_to_the_registered_account_on_registration_account_form() throws Throwable {
		Thread.sleep(1000);	   
		CommonLib.textValidation(ConstantRegister.REGISTER_ALERT, "Error: An account is already registered with your email address. Please log in.");
	}

}
