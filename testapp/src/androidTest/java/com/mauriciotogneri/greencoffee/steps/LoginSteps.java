package com.mauriciotogneri.greencoffee.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.R;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

@SuppressWarnings("unused")
public class LoginSteps extends GreenCoffeeSteps
{
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";
    private static final String INVALID_USERNAME = "guest";
    private static final String INVALID_PASSWORD = "5678";

    @Given("^I see an empty login form$")
    public void iSeeAnEmptyLoginForm()
    {
        containsTextWithId(R.id.login_input_username, "");
        containsTextWithId(R.id.login_input_password, "");
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

    @When("^I introduce as username (.+)$")
    public void iIntroduceAsUsername(String username)
    {
        typeTextWithId(R.id.login_input_username, username);
    }

    @When("^I introduce as password (.+)$")
    public void iIntroduceAsPassword(String password)
    {
        typeTextWithId(R.id.login_input_password, password);
    }

    @Then("^I see an error message with 'Invalid username'$")
    public void iSeeAnErrorMessageWithInvalidUsername()
    {
        isDisplayedWithText(R.string.login_username_error);
    }

    @Then("^I see an error message with 'Invalid password'$")
    public void iSeeAnErrorMessageWithInvalidPassword()
    {
        isDisplayedWithText(R.string.login_password_error);
    }

    @Then("^I see an error message with 'Invalid credentials'$")
    public void iSeeAnErrorMessageWithInvalidCredentials()
    {
        isDisplayedWithText(R.string.login_credentials_error);
    }

    @Then("^I see the contacts screen$")
    public void iSeeTheContactsScreen()
    {
        isDisplayedWithText(R.string.contacts_title);
    }
}