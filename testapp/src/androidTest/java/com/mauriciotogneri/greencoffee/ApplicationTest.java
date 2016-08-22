package com.mauriciotogneri.greencoffee;

import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.util.Arrays;

// https://github.com/googlesamples/android-testing/blob/master/runner/AndroidJunitRunnerSample/app/src/androidTest/java/com/example/android/testing/androidjunitrunnersample/CalculatorAddParameterizedTest.java
public class ApplicationTest extends GreenCoffeeTest
{
    @Rule
    public ControlledActivityTestRule<LoginActivity> activityTestRule = new ControlledActivityTestRule<>(LoginActivity.class);

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";
    private static final String INVALID_USERNAME = "guest";
    private static final String INVALID_PASSWORD = "5678";

    private final String param;

    @Parameters
    public static Iterable<Object> data()
    {
        return Arrays.asList(new Object[] {"aaa", "bbb", "ccc"});
    }

    public ApplicationTest(String param)
    {
        this.param = param;
    }

    @Test
    public void init() throws IOException
    {
        System.out.println(param);
        // start(fromAssets("assets/login.feature"), this, activityTestRule);
    }

    @Given("^an empty login form$")
    public void anEmptyLoginForm()
    {
        typeTextWithId(R.id.login_input_username, "");
        typeTextWithId(R.id.login_input_password, "");
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        clickWithId(R.id.login_button_doLogin);
    }

    @When("^I introduce a valid username$")
    public void iIntroduceAValidUsername()
    {
        typeTextWithId(R.id.login_input_username, VALID_USERNAME);
    }

    @When("^I introduce a valid password$")
    public void iIntroduceAValidPassword()
    {
        typeTextWithId(R.id.login_input_password, VALID_PASSWORD);
    }

    @When("^I introduce an invalid username$")
    public void iIntroduceAnInvalidUsername()
    {
        typeTextWithId(R.id.login_input_username, INVALID_USERNAME);
    }

    @When("^I introduce an invalid password$")
    public void iIntroduceAInvalidPassword()
    {
        typeTextWithId(R.id.login_input_password, INVALID_PASSWORD);
    }

    @Then("^I see an error message with 'Invalid username'$")
    public void iSeeAnErrorMessageWithInvalidUsername()
    {
        isVisibleWithText(R.string.login_username_error);
    }

    @Then("^I see an error message with 'Invalid password'$")
    public void iSeeAnErrorMessageWithInvalidPassword()
    {
        isVisibleWithText(R.string.login_password_error);
    }

    @Then("^I see an error message with 'Invalid credentials'$")
    public void iSeeAnErrorMessageWithInvalidCredentials()
    {
        isVisibleWithText(R.string.login_credentials_error);
    }

    @Then("^I see the main screen$")
    public void iSeeTheMainScreen()
    {
        isVisibleWithText(R.string.main_title);
    }
}