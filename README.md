[![Build Status](https://travis-ci.org/mauriciotogneri/green-coffee.svg?branch=master)](https://travis-ci.org/mauriciotogneri/green-coffee)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/mauriciotogneri/green-coffee/blob/master/LICENSE.txt)

# Green Coffee
**Green Coffee** is an Android library that allows you to declare Cucumber step definitions in your instrumentation tests.

## Example

Given the following feature:

```gherkin
Feature: Login screen to authenticate users

	Scenario: Invalid username and password
	    Given I see an empty login form
		 When I introduce an invalid username
		  And I introduce an invalid password
		  And I press the login button
		 Then I see an error message saying 'Invalid credentials'
```

First, create a test that extends from `GreenCoffeeTest` and declare the entry point Activity and the feature that will be used:

```java
public class LoginFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    public LoginFeatureTest(Scenario scenario)
    {
        super(scenario);
    }

    @Parameters
    public static Iterable<Scenario> data() throws IOException
    {
        return new GreenCoffeeConfig().fromAssets("assets/login.feature");
    }

    @Test
    public void test()
    {
        start(new LoginSteps());
    }
}
```

This configuration makes the test work in the following way:
* The feature will be parsed and its the scenarios will be loaded
* For each scenario, an instance of the declared activity will be launched
* The steps definitions passed as parameters in the method `start` will be used to match the steps in the scenarios

The second step is to create a class containing the steps definitions:

```java
public class LoginSteps extends GreenCoffeeSteps
{
    @Given("^I see an empty login form$")
    public void iSeeAnEmptyLoginForm()
    {
        containsTextWithId(R.id.login_input_username, "");
        containsTextWithId(R.id.login_input_password, "");
    }

    @When("^I introduce an invalid username$")
    public void iIntroduceAnInvalidUsername()
    {
        typeTextWithId(R.id.login_input_username, "guest");
    }

    @When("^I introduce an invalid password$")
    public void iIntroduceAInvalidPassword()
    {
        typeTextWithId(R.id.login_input_password, "1234");
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        clickWithId(R.id.login_button_doLogin);
    }

    @Then("^I see an error message saying 'Invalid credentials'$")
    public void iSeeAnErrorMessageSayingInvalidCredentials()
    {
        isDisplayedWithText(R.string.login_credentials_error);
    }
}
```

## Configure
Add the following dependency to your `build.gradle` file:

```groovy
compile 'com.mauriciotogneri:greencoffee:1.0.0'
```