package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import Input.Excel;
import Library.CommonLib;
import UIConstant.ConstantForgotPassword;
import UIConstant.ConstantMailinator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ForgotPassword extends CommonLib {
	
	private static String sheetForgotPass = CommonLib.getValueFromProperty("sheetForgotPass");
	
	@When("User clicks Lost your password link")
	public void user_clicks_lost_your_password_link() throws Throwable {
	    CommonLib.isElementVerifyClick(ConstantForgotPassword.LOST_YOUR_PWD_LINK);
	    CommonLib.shortSleep();
	}

	@Then("User is navigated to Forgot Password page")
	public void user_is_navigated_to_forgot_password_page() throws Throwable {
		CommonLib.isElementVerification(ConstantForgotPassword.FORGOT_PWD_PAGE);
	}

	@When("User enters valid email address on Forgot Password page {string}")
	public void user_enters_valid_email_address_on_forgot_password_page(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantForgotPassword.EMAIL_FIELD, Excel.get("rstEmail", rowCount, sheetForgotPass));
	}

	@When("User clicks Reset Password button")
	public void user_clicks_reset_password_button() throws Throwable {
	    CommonLib.isElementVerifyClick(ConstantForgotPassword.RESET_PWD_BTN);
	    Thread.sleep(1000);
	}

	@Then("User is successfully send Reset Password link to email")
	public void user_is_successfully_send_reset_password_link_to_email() throws Throwable {
	    CommonLib.textValidation(ConstantForgotPassword.FORGOT_PWD_ALERT, "Password reset email has been sent.");;
	}
	
	@Given("User access Reset Password Link from email {string}")
	public void user_access_reset_password_link_from_email(String rowCount) throws Throwable {
		CommonLib.AppLaunch();
		CommonLib.navigateToUrl("https://www.mailinator.com/");
	    CommonLib.enterValue(ConstantMailinator.SEARCH_MAIL, Excel.get("rstEmail", rowCount, sheetForgotPass));
	    driver.findElement(By.xpath(ConstantMailinator.SEARCH_MAIL)).sendKeys(Keys.ENTER); Thread.sleep(2000);
	    CommonLib.isElementVerifyClick(ConstantMailinator.INBOX_RESET_PWD); Thread.sleep(1000);
	    driver.switchTo().frame("html_msg_body");
	    CommonLib.isElementVerifyClick(ConstantMailinator.LINK_RESET_PWD);
	    driver.switchTo().defaultContent();
	}

	@Then("User is successfully navigated to Reset Password page")
	public void user_is_successfully_navigated_to_reset_password_page() throws Throwable {
	    CommonLib.switchTab();
	    CommonLib.mediumSleep();
	    CommonLib.isElementVerification(ConstantForgotPassword.RESET_PWD_PAGE);
	}
	
	//Errors
	@When("User enters invalid email address on Forgot Password page {string}")
	public void user_enters_invalid_email_address_on_forgot_password_page(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantForgotPassword.EMAIL_FIELD, Excel.get("rstEmailInvalid", rowCount, sheetForgotPass));
	}

	@Then("User should be able to see error message due to invalid email address on Forgot Password page")
	public void user_should_be_able_to_see_error_message_due_to_invalid_email_address_on_forgot_password_page() throws Throwable {
	    CommonLib.textValidation(ConstantForgotPassword.FORGOT_PWD_ALERT, "Invalid username or email.");
	}

	@When("User leaves email address as empty on Forgot Password page {string}")
	public void user_leaves_email_address_as_empty_on_forgot_password_page(String string) throws Throwable {
	    driver.findElement(By.xpath(ConstantForgotPassword.EMAIL_FIELD)).clear();
	}

	@Then("User should be able to see error message due to email address is empty on Forgot Password page")
	public void user_should_be_able_to_see_error_message_due_to_email_address_is_empty_on_forgot_password_page() throws Throwable {
	    CommonLib.textValidation(ConstantForgotPassword.FORGOT_PWD_ALERT, "Enter a username or email address.");
	}

	@When("User access expired Reset Password link {string}")
	public void user_access_expired_reset_password_link(String rowCount) throws Throwable {
		CommonLib.navigateToUrl("https://www.mailinator.com/");
	    CommonLib.enterValue(ConstantMailinator.SEARCH_MAIL, Excel.get("rstEmailExpired", rowCount, sheetForgotPass));
	    driver.findElement(By.xpath(ConstantMailinator.SEARCH_MAIL)).sendKeys(Keys.ENTER); Thread.sleep(1000);
	    CommonLib.isElementVerifyClick(ConstantMailinator.INBOX_RESET_PWD); Thread.sleep(1000);
	    driver.switchTo().frame("html_msg_body");
	    CommonLib.isElementVerifyClick(ConstantMailinator.LINK_RESET_PWD);
	    driver.switchTo().defaultContent();
	}

	@Then("User should be navigated to Expired Reset Password page")
	public void user_should_be_navigated_to_expired_reset_password_page() throws Throwable {
		CommonLib.switchTab();
		Thread.sleep(2000);
	    CommonLib.textValidation(ConstantForgotPassword.FORGOT_PWD_ALERT, "This key is invalid or has already been used. Please reset your password again if needed.");
	}

}
