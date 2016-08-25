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

	Scenario: Selecting a contact from the list
		Given I login as USER_1
		When  I select the contact called "Jack Baxter"
		Then  I see the detail screen for "Jack Baxter"
		And   I see his name is "Jack Baxter"
		And   I see his age is 28
		And   I see his weight is 71.4 kg.
		And   I see he is single