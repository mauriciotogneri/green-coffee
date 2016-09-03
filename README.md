[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/mauriciotogneri/green-coffee/blob/master/LICENSE.txt)
[![Download](https://api.bintray.com/packages/mauriciotogneri/maven/greencoffee/images/download.svg)](https://bintray.com/mauriciotogneri/maven/greencoffee/_latestVersion)

# Green Coffee
**Green Coffee** is an Android library that allows you to run Cucumber scenarios in your instrumentation tests.

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

First, create a test that extends from `GreenCoffeeTest` and declare the Activity and the feature that will be used:

```java
@RunWith(Parameterized.class)
public class LoginFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activity = new ActivityTestRule<>(LoginActivity.class);

    public LoginFeatureTest(Scenario scenario)
    {
        super(scenario);
    }

    @Parameters
    public static Iterable<Scenario> data() throws IOException
    {
        return new GreenCoffeeConfig()
                        .withFeatureFromAssets("assets/login.feature")
                        .scenarios();
    }

    @Test
    public void test()
    {
        start(new LoginSteps());
    }
}
```

This configuration makes the test work in the following way:
* The feature will be parsed and all its scenarios will be loaded
* For each scenario, an instance of the declared activity will be launched
* The steps definitions passed as parameters in the method `start` will be used to match the steps in the scenarios

The second step is to create a class containing the steps definitions:

```java
public class LoginSteps extends GreenCoffeeSteps
{
    @Given("^I see an empty login form$")
    public void iSeeAnEmptyLoginForm()
    {
        onViewWithId(R.id.login_input_username).isEmpty();
        onViewWithId(R.id.login_input_password).isEmpty();
    }

    @When("^I introduce an invalid username$")
    public void iIntroduceAnInvalidUsername()
    {
        onViewWithId(R.id.login_input_username).type("guest");
    }

    @When("^I introduce an invalid password$")
    public void iIntroduceAnInvalidPassword()
    {
        onViewWithId(R.id.login_input_password).type("1234");
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        onViewWithId(R.id.login_button_doLogin).click();
    }

    @Then("^I see an error message saying 'Invalid credentials'$")
    public void iSeeAnErrorMessageSayingInvalidCredentials()
    {
        onViewWithText(R.string.login_credentials_error).isDisplayed();
    }
}
```

This is how it looks when you run your instrumentation tests:
![Example](http://i.imgur.com/4rMK1KK.gif)

## Step definitions
Methods used as step definitions can be declared using the following annotations:
* `Given`
* `When`
* `Then`
* `And`
* `But`

## Espresso helper methods
Green Coffee includes a set of methods that makes more readable the interaction with the UI components. More information in the [wiki](https://github.com/mauriciotogneri/green-coffee/wiki).

## Installation
Add the following dependency to your `build.gradle` file:

```groovy
compile 'com.mauriciotogneri:greencoffee:1.0.0'
```