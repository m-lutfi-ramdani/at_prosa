@login
Feature: Login
	
	Scenario Outline: User should be able to Login successfully
		Given User launch My Account page of PSegameshop web apps
		Then User is on My Account page
		When User enters valid email and password on Login form "<rowCount>"
		And User clicks Login button
		Then User is successfully logged in
		
	Examples:
	|rowCount|
	|1		 |