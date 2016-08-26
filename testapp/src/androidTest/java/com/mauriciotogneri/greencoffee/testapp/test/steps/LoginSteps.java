package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.testapp.R;
import com.mauriciotogneri.greencoffee.testapp.database.UserDatabase;

@SuppressWarnings("unused")
public class LoginSteps extends GreenCoffeeSteps
{
    private static final String INVALID_USERNAME = "guest";
    private static final String INVALID_PASSWORD = "5678";

    @Given("^I see an empty login form$")
    public void iSeeAnEmptyLoginForm()
    {
        onViewWithId(R.id.login_input_username).isEmpty();
        onViewWithId(R.id.login_input_password).isEmpty();
    }

    @Given("^I login as (\\w+)$")
    public void iLoginAs$(String user)
    {
        switch (user)
        {
            case "USER_1":
                onViewWithId(R.id.login_input_username).type(UserDatabase.USER_1.username());
                onViewWithId(R.id.login_input_password).type(UserDatabase.USER_1.password());
                break;

            case "USER_2":
                onViewWithId(R.id.login_input_username).type(UserDatabase.USER_2.username());
                onViewWithId(R.id.login_input_password).type(UserDatabase.USER_2.password());
                break;

            case "USER_3":
                onViewWithId(R.id.login_input_username).type(UserDatabase.USER_3.username());
                onViewWithId(R.id.login_input_password).type(UserDatabase.USER_3.password());
                break;

            default:
                throw new RuntimeException();
        }

        onViewWithId(R.id.login_button_doLogin).click();
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        onViewWithId(R.id.login_button_doLogin).click();
    }

    @When("^I introduce a valid username$")
    public void iIntroduceAValidUsername()
    {
        onViewWithId(R.id.login_input_username).type(UserDatabase.USER_1.username());
    }

    @When("^I introduce a valid password$")
    public void iIntroduceAValidPassword()
    {
        onViewWithId(R.id.login_input_password).type(UserDatabase.USER_1.password());
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
}