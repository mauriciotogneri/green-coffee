Feature: List of contacts screen
	In order to see the list of contacts
	As a user of the app
	I want to access the contact list screen

	Background:
		Given I see an empty login form
		When  I introduce a valid username
		And   I introduce a valid password
		And   I press the login button
		Then  I see the contacts screen

	Scenario: Selecting a contact from the list
		When I select a contact from the list
		Then I see the detail screen for that contact