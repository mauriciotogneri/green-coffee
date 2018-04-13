[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/mauriciotogneri/green-coffee/blob/master/LICENSE.md)
[![Download](https://api.bintray.com/packages/mauriciotogneri/maven/greencoffee/images/download.svg)](https://bintray.com/mauriciotogneri/maven/greencoffee/_latestVersion)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-green--coffee-green.svg?style=true)](https://android-arsenal.com/details/1/4313)

# Green Coffee
**Green Coffee** is a library that allows you to run your acceptance tests written in Gherkin in your Android instrumentation tests using the step definitions that you declare. Visit the [wiki](https://github.com/mauriciotogneri/green-coffee/wiki) for more detailed information.

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

First, create a class that extends from `GreenCoffeeTest` and declare the Activity, the feature and the step definitions that will be used:

```java
@RunWith(Parameterized.class)
public class LoginFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activity = new ActivityTestRule<>(LoginActivity.class);

    public LoginFeatureTest(ScenarioConfig scenarioConfig)
    {
        super(scenarioConfig);
    }

    @Parameters(name = "{0}")
    public static Iterable<ScenarioConfig> scenarios() throws IOException
    {
        return new GreenCoffeeConfig("testapp") // folder to place the screenshot if a test fails
                        .withFeatureFromAssets("assets/login.feature")
                        .scenarios(
                            new Locale("en", "GB"),
                            new Locale("es", "ES")
                        ); // the locales used to run the scenarios (optional)
    }

    @Test
    public void test()
    {
        start(new LoginSteps());
    }
}
```

Next, create a class containing the steps definitions:

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

And that's it, now you can create your own tests using Green Coffee. This is how it looks when you run a more complex test:

![Example](http://i.imgur.com/4rMK1KK.gif)

You can see an example applied to a full app [here](https://github.com/vndly/green-coffee-example).

## Installation
In order to use Green Coffee, add the following dependency to your `build.gradle` file:

```groovy
dependencies
{
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.mauriciotogneri:greencoffee:3.1.0'
}
```

And the following test instrumentation runner:
```groovy
defaultConfig
{
    testInstrumentationRunner 'android.support.test.runner.AndroidJUnitRunner'
}
```

## License

    MIT License

    Copyright (c) 2017 Mauricio Togneri

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.