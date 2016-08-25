Feature: Contact detail screen
	In order to see the contact detail screen
	As a user of the app
	I want to access the contact detail screen

	Scenario: Selecting a contact from the list
		Given I login as USER_1
		When  I select the contact called "James Houghton"
		Then  I see the detail screen for "James Houghton"
		And   I see his name is "James Houghton"
		And   I see his age is 31
		And   I see his weight is 76.9 kg.
		And   I see he is married