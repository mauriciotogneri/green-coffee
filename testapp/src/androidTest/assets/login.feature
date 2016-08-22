Feature: Login screen to authenticate users
  In order to login into the app
  As a user of the app
  I want to authenticate using my credentials

  Background:
    Given an empty login form

  Scenario: Username not introduced
    When I press the login button
    Then I see an error message with 'Invalid username'

  Scenario: Password not introduced
    When I introduce a valid username
    And  I press the login button
    Then I see an error message with 'Invalid password'