package com.mauriciotogneri.greencoffee;

import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class ApplicationTest extends GreenCoffeeTest
{
    @Rule
    public ControlledActivityTestRule<LoginActivity> activityTestRule = new ControlledActivityTestRule<>(LoginActivity.class);

    private static final String VALID_USERNAME = "admin";

    @Test
    public void init() throws IOException
    {
        start(fromAssets("assets/login.feature"), this, activityTestRule);
    }

    @Given("^an empty login form$")
    public void anEmptyLoginForm()
    {
        typeTextWithId(R.id.login_username, "");
        typeTextWithId(R.id.login_password, "");
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        clickWithId(R.id.login_doLogin);
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

    @When("^I introduce a valid username$")
    public void iIntroduceAUsername()
    {
        typeTextWithId(R.id.login_username, VALID_USERNAME);
    }
}