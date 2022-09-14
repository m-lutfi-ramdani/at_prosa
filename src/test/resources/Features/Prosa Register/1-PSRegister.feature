@register
Feature: Register
	
	Scenario Outline: User should be able to register new account
		Given User launch My Account page of PSegameshop web apps
		Then User is on My Account page
		When User fills Registration Account form "<rowCount>"
		And User clicks Register button
		Then User is successfully register an account
		And User open opens Registration verification link "<rowCount>"
		Then User is successfully verify an account
		
	Examples:
	|rowCount|
	|1		 |