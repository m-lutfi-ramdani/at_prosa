@forgot-password
Feature: Forgot Password
	
	Scenario Outline: User should be able to access Reset Password link on email
		Given User access Reset Password Link from email "<rowCount>"
		Then User is successfully navigated to Reset Password page
		
	Examples:
	|rowCount|
	|1		 |