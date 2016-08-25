Feature: Login screen to authenticate users
	In order to login into the app
	As a user of the app
	I want to authenticate using my credentials

	Background:
		Given I see an empty login form

	Scenario: Username not introduced
		When I introduce a valid password
		And  I press the login button
		Then I see an error message saying 'Invalid username'

	Scenario: Password not introduced
		When I introduce a valid username
		And  I press the login button
		Then I see an error message saying 'Invalid password'

	Scenario: Invalid password
		When I introduce a valid username
		And  I introduce an invalid password
		And  I press the login button
		Then I see an error message saying 'Invalid credentials'

	Scenario: Invalid username
		When I introduce an invalid username
		And  I introduce a valid password
		And  I press the login button
		Then I see an error message saying 'Invalid credentials'

	Scenario: Invalid username and password
		When I introduce an invalid username
		And  I introduce an invalid password
		And  I press the login button
		Then I see an error message saying 'Invalid credentials'

	Scenario Outline: Set of wrong credentials
		When I introduce as username <user>
		When I introduce as password <pass>
		And  I press the login button
		Then I see an error message saying 'Invalid credentials'

		Examples:
			| user  | pass  |
			| admin | admin |
			| admin | 12345 |
			| guest | guest |
			| guest | 12345 |
			| root  | root  |
			| root  | 12345 |

	Scenario: Valid username and password
		When I introduce a valid username
		And  I introduce a valid password
		And  I press the login button
		Then I see the contacts screen