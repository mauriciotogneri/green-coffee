package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.testapp.R;

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
        onViewWithId(R.id.login_input_username).contains("");
        onViewWithId(R.id.login_input_password).contains("");
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        onViewWithId(R.id.login_button_doLogin).click();
    }

    @When("^I introduce a valid username$")
    public void iIntroduceAValidUsername()
    {
        onViewWithId(R.id.login_input_username).type(VALID_USERNAME);
    }

    @When("^I introduce a valid password$")
    public void iIntroduceAValidPassword()
    {
        onViewWithId(R.id.login_input_password).type(VALID_PASSWORD);
    }

    @When("^I introduce an invalid username$")
    public void iIntroduceAnInvalidUsername()
    {
        onViewWithId(R.id.login_input_username).type(INVALID_USERNAME);
    }

    @When("^I introduce an invalid password$")
    public void iIntroduceAInvalidPassword()
    {
        onViewWithId(R.id.login_input_password).type(INVALID_PASSWORD);
    }

    @When("^I introduce as username (.+)$")
    public void iIntroduceAsUsername(String username)
    {
        onViewWithId(R.id.login_input_username).type(username);
    }

    @When("^I introduce as password (.+)$")
    public void iIntroduceAsPassword(String password)
    {
        onViewWithId(R.id.login_input_password).type(password);
    }

    @Then("^I see an error message saying 'Invalid username'$")
    public void iSeeAnErrorMessageSayingInvalidUsername()
    {
        onViewWithText(R.string.login_username_error).isDisplayed();
    }

    @Then("^I see an error message saying 'Invalid password'$")
    public void iSeeAnErrorMessageSayingInvalidPassword()
    {
        onViewWithText(R.string.login_password_error).isDisplayed();
    }

    @Then("^I see an error message saying 'Invalid credentials'$")
    public void iSeeAnErrorMessageSayingInvalidCredentials()
    {
        onViewWithText(R.string.login_credentials_error).isDisplayed();
    }

    @Then("^I see the contacts screen$")
    public void iSeeTheContactsScreen()
    {
        onViewWithText(R.string.contacts_title).isDisplayed();
    }
}