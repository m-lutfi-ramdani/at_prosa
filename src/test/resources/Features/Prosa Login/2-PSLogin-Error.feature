@login-error
Feature: Login - Error Scenario
	
	Scenario Outline: User should not able to Login due to errors
		Given User launch My Account page of PSegameshop web apps
		Then User is on My Account page
		When User enters invalid email address on Login form "<rowCount>"
		And User clicks Login button
		Then User should be able to see error message due to invalid email address on Login form "<rowCount>"
		When User enters invalid password requirement on Login form "<rowCount>"
		And User clicks Login button
		Then User should be able to see error message due to invalid password requirement on Login form "<rowCount>"
		When User leaves email and password field as empty on Login form "<rowCount>"
		And User clicks Login button
		Then User should be able to see error message due to email and password field are empties on Login form
		When User enters unverified account on Login form on Login form "<rowCount>"
		And User clicks Login button
		Then User should be able to see error message due to unverified account on Login form
		When User user leaves password field as empty on Login form on Login form "<rowCount>"
		And User clicks Login button
		Then User should be able to see error message due to password field as empty
		When User enters unregistered email on Login form on Login form "<rowCount>"
		And User clicks Login button
		Then User should be able to see error message due to unregistered email
		
	Examples:
	|rowCount|
	|1		 |