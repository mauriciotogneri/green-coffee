Feature: List of contacts screen
	In order to see the list of contacts
	As a user of the app
	I want to access the contact list screen

	Scenario: Selecting a contact from the list
		Given I login as "USER_1"
		When  I select the contact called "James Houghton"
		Then  I see the detail screen for "James Houghton"

	Scenario: Selecting a contact from the list
		Given I login as "USER_2"
		When  I select the contact called "Maddison Wallace"
		Then  I see the detail screen for "Maddison Wallace"

	Scenario: User has no contacts
		Given I login as "USER_3"
		Then  I see an empty contact list