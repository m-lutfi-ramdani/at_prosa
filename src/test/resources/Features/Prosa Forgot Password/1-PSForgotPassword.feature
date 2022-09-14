@forgot-password
Feature: Forgot Password
	
	Scenario Outline: User should be able to send Reset Password link to forgot the password
		Given User launch My Account page of PSegameshop web apps
		Then User is on My Account page
		When User clicks Lost your password link
		Then User is navigated to Forgot Password page
		When User enters valid email address on Forgot Password page "<rowCount>"
		And User clicks Reset Password button
		Then User is successfully send Reset Password link to email
		
	Examples:
	|rowCount|
	|1		 |