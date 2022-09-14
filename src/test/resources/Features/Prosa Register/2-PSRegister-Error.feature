@register-error
Feature: Register - Error Scenario
	
	Scenario Outline: User should be not able to register new account due to errors
		Given User launch My Account page of PSegameshop web apps
		Then User is on My Account page
		When User enters invalid email address on Registration Account form "<rowCount>"
		And User clicks Register button
		Then User should be able to see error message due to invalid email address on Registration Account form
		When User enters invalid password requirement on Registration Account form "<rowCount>"
		And User clicks Register button
		Then User should be able to see error message due to invalid password requirement on Registration Account form
		When User enters unmatched password on Registration Account form "<rowCount>"
		And User clicks Register button
		Then User should be able to see error message due to unmatched password on Registration Account form
		When User mandatory fields as empty on Registration Account form "<rowCount>"
		And User clicks Register button
		Then User should be able to see error message due to mandatory fields are empties on Registration Account form
		When User enters the registered account on Registration Account form "<rowCount>"
		And User clicks Register button
		Then User should be able to see error message due to the registered account on Registration Account form
		
	Examples:
	|rowCount|
	|1		 |