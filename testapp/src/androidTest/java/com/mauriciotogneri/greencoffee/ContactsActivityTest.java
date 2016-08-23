package com.mauriciotogneri.greencoffee;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;

public class ContactsActivityTest extends GreenCoffee
{
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    public ContactsActivityTest(Scenario scenario)
    {
        super(scenario);
    }

    @Parameters
    public static Iterable<Scenario> data() throws IOException
    {
        return new GreenCoffeeConfig().fromAssets("assets/contacts.feature");
    }

    @Test
    public void test()
    {
        start(this);
    }

    @Given("^I see an empty login form$")
    public void iSeeAnEmptyLoginForm()
    {
        containsTextWithId(R.id.login_input_username, "");
        containsTextWithId(R.id.login_input_password, "");
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

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        clickWithId(R.id.login_button_doLogin);
    }

    @When("^I select a contact from the list$")
    public void iSelectAContactFromTheList()
    {
    }

    @Then("^I see the contacts screen$")
    public void iSeeTheContactsScreen()
    {
        isDisplayedWithText(R.string.contacts_title);
    }

    @Then("^I see the detail screen for that contact$")
    public void iSeeTheDetailScreenForThatContact()
    {
    }
}