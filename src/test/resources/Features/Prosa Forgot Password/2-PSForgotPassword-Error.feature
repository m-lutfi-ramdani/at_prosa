@forgot-password-error
Feature: Forgot Password
	
	Scenario Outline: User should not able to send Reset Password link to forgot the password due to errors
		Given User launch My Account page of PSegameshop web apps
		Then User is on My Account page
		When User clicks Lost your password link
		Then User is navigated to Forgot Password page
		When User enters invalid email address on Forgot Password page "<rowCount>"
		And User clicks Reset Password button
		Then User should be able to see error message due to invalid email address on Forgot Password page
		When User leaves email address as empty on Forgot Password page "<rowCount>"
		And User clicks Reset Password button
		Then User should be able to see error message due to email address is empty on Forgot Password page
		#Need to reset password manually due to using mailinator mail (temporary Inbox)
		When User access expired Reset Password link "<rowCount>"
		Then User should be navigated to Expired Reset Password page
		
	Examples:
	|rowCount|
	|1		 |