Feature: Login screen to authenticate users
	In order to login into the app
	As a user of the app
	I want to authenticate using my credentials

	Background:
		Given I see an empty login form

	Scenario: Username not introduced
		When I introduce a valid password
		And  I press the login button
		Then I see an error message with 'Invalid username'

	Scenario: Password not introduced
		When I introduce a valid username
		And  I press the login button
		Then I see an error message with 'Invalid password'

	Scenario: Invalid password
		When I introduce a valid username
		And  I introduce an invalid password
		And  I press the login button
		Then I see an error message with 'Invalid credentials'

	Scenario: Invalid username
		When I introduce an invalid username
		And  I introduce a valid password
		And  I press the login button
		Then I see an error message with 'Invalid credentials'

	Scenario: Invalid username and password
		When I introduce an invalid username
		And  I introduce an invalid password
		And  I press the login button
		Then I see an error message with 'Invalid credentials'

	Scenario Outline: Set of wrong credentials
		When I introduce as username <user>
		When I introduce as password <pass>
		And  I press the login button
		Then I see an error message with 'Invalid credentials'

		Examples:
			| user  | pass  |
			| admin | 12345 |
			| admin | admin |
			| guest | 1234  |
			| guest | guest |

	Scenario: Valid username and password
		When I introduce a valid username
		And  I introduce a valid password
		And  I press the login button
		Then I see the main screen