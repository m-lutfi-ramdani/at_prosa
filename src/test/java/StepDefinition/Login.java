package StepDefinition;


import Input.Excel;
import Library.CommonLib;
import UIConstant.ConstantLogin;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login extends CommonLib {
	
	private static String sheetLogin = CommonLib.getValueFromProperty("sheetLogin");
	
	@When("User enters valid email and password on Login form {string}")
	public void user_enters_valid_email_and_password_on_login_form(String rowCount) throws Throwable {
	    CommonLib.enterValue(ConstantLogin.LOGIN_EMAIL_FIELD, Excel.get("logEmail", rowCount, sheetLogin));
	    CommonLib.enterValue(ConstantLogin.LOGIN_PASSWORD_FIELD, Excel.get("logPassword", rowCount, sheetLogin));

	}

	@When("User clicks Login button")
	public void user_clicks_login_button() throws Throwable {
	    CommonLib.isElementVerifyClick(ConstantLogin.LOGIN_BTN);
	    CommonLib.mediumSleep();
	}

	@Then("User is successfully logged in")
	public void user_is_successfully_logged_in() throws Throwable {
	    CommonLib.isElementVerification(ConstantLogin.DASHBOARD);
	}
	
	//Errors
	@When("User enters invalid email address on Login form {string}")
	public void user_enters_invalid_email_address_on_login_form(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantLogin.LOGIN_EMAIL_FIELD, Excel.get("logEmailInvalid", rowCount, sheetLogin));
	    CommonLib.enterValue(ConstantLogin.LOGIN_PASSWORD_FIELD, Excel.get("logPassword", rowCount, sheetLogin));
	}

	@Then("User should be able to see error message due to invalid email address on Login form {string}")
	public void user_should_be_able_to_see_error_message_due_to_invalid_email_address_on_login_form(String rowCount) throws Throwable {
	    Thread.sleep(1000);
	    String username = Excel.get("logEmailInvalid", rowCount, sheetLogin);
	    CommonLib.textValidation(ConstantLogin.LOGIN_ALERT, "Error: The username " + username + " is not registered on this site. If you are unsure of your username, try your email address instead.");
	}

	@When("User enters invalid password requirement on Login form {string}")
	public void user_enters_invalid_password_requirement_on_login_form(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantLogin.LOGIN_EMAIL_FIELD, Excel.get("logEmail", rowCount, sheetLogin));
	    CommonLib.enterValue(ConstantLogin.LOGIN_PASSWORD_FIELD, Excel.get("logPasswordInvalid", rowCount, sheetLogin));
	}

	@Then("User should be able to see error message due to invalid password requirement on Login form {string}")
	public void user_should_be_able_to_see_error_message_due_to_invalid_password_requirement_on_login_form(String rowCount) throws Throwable {
		Thread.sleep(1000);
	    String email = Excel.get("logEmail", rowCount, sheetLogin);
	    CommonLib.textValidation(ConstantLogin.LOGIN_ALERT, "Error: The password you entered for the email address " +email+ " is incorrect. Lost your password?");
	}

	@When("User leaves email and password field as empty on Login form {string}")
	public void user_leaves_email_and_password_field_as_empty_on_login_form(String string) throws Throwable {
	    CommonLib.navigateToUrl("https://www.psegameshop.com/my-account/");
	}

	@Then("User should be able to see error message due to email and password field are empties on Login form")
	public void user_should_be_able_to_see_error_message_due_to_email_and_password_field_are_empties_on_login_form() throws Throwable {
	    Thread.sleep(1000);
		CommonLib.textValidation(ConstantLogin.LOGIN_ALERT, "Error: Username is required.");
	}

	@When("User enters unverified account on Login form on Login form {string}")
	public void user_enters_unverified_account_on_login_form_on_login_form(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantLogin.LOGIN_EMAIL_FIELD, Excel.get("logEmailUnverified", rowCount, sheetLogin));
	    CommonLib.enterValue(ConstantLogin.LOGIN_PASSWORD_FIELD, Excel.get("logPassword", rowCount, sheetLogin));
	}

	@Then("User should be able to see error message due to unverified account on Login form")
	public void user_should_be_able_to_see_error_message_due_to_unverified_account_on_login_form() throws Throwable {
	    Thread.sleep(1000);
		CommonLib.textValidation(ConstantLogin.LOGIN_ALERT, "Your account has to be activated before you can login. You can resend the email with verification link by clicking here.");
	}
	
	@When("User user leaves password field as empty on Login form on Login form {string}")
	public void user_user_leaves_password_field_as_empty_on_login_form_on_login_form(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantLogin.LOGIN_EMAIL_FIELD, Excel.get("logEmail", rowCount, sheetLogin));
	}

	@Then("User should be able to see error message due to password field as empty")
	public void user_should_be_able_to_see_error_message_due_to_password_field_as_empty() throws Throwable {
		Thread.sleep(1000);
		CommonLib.textValidation(ConstantLogin.LOGIN_ALERT, "Error: The password field is empty.");
	}

	@When("User enters unregistered email on Login form on Login form {string}")
	public void user_enters_unregistered_email_on_login_form_on_login_form(String rowCount) throws Throwable {
		CommonLib.enterValue(ConstantLogin.LOGIN_EMAIL_FIELD, Excel.get("logEmailUnregistered", rowCount, sheetLogin));
	    CommonLib.enterValue(ConstantLogin.LOGIN_PASSWORD_FIELD, Excel.get("logPassword", rowCount, sheetLogin));
	}

	@Then("User should be able to see error message due to unregistered email")
	public void user_should_be_able_to_see_error_message_due_to_unregistered_email() throws Throwable {
		Thread.sleep(1000);
		CommonLib.textValidation(ConstantLogin.LOGIN_ALERT, "Unknown email address. Check again or try your username.");
	}

}
