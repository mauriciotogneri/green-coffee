package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.interactions.DataMatcher;
import com.mauriciotogneri.greencoffee.testapp.R;
import com.mauriciotogneri.greencoffee.testapp.database.UserDatabase;
import com.mauriciotogneri.greencoffee.testapp.model.Contact;
import com.mauriciotogneri.greencoffee.testapp.test.matchers.ContactMatcher;

@SuppressWarnings("unused")
public class ContactListSteps extends GreenCoffeeSteps
{
    private final DataMatcher<Contact, String> dataMatcher;

    public ContactListSteps()
    {
        this.dataMatcher = new ContactMatcher(R.id.contacts_list);
    }

    @Given("^I login as \"(\\w+)\"$")
    public void iLoginAs$(String user)
    {
        if (user.equals("USER_1"))
        {
            onViewWithId(R.id.login_input_username).type(UserDatabase.USER_1.username());
            onViewWithId(R.id.login_input_password).type(UserDatabase.USER_1.password());
        }
        else if (user.equals("USER_2"))
        {
            onViewWithId(R.id.login_input_username).type(UserDatabase.USER_2.username());
            onViewWithId(R.id.login_input_password).type(UserDatabase.USER_2.password());
        }
        else if (user.equals("USER_3"))
        {
            onViewWithId(R.id.login_input_username).type(UserDatabase.USER_3.username());
            onViewWithId(R.id.login_input_password).type(UserDatabase.USER_3.password());
        }
        else
        {
            throw new RuntimeException();
        }

        onViewWithId(R.id.login_button_doLogin).click();
    }

    @When("^I select the contact called \"([\\w| ]+)\"$")
    public void iSelectTheContactCalled$(String username)
    {
        dataMatcher.withContent(username).click();
    }

    @Then("^I see the detail screen for \"([\\w| ]+)\"$")
    public void iSeeTheDetailScreenFor$(String username)
    {
        onViewWithText(username).isDisplayed();
    }

    @Then("^I see an empty contact list$")
    public void iSeeAnEmptyContactList()
    {
        onViewWithText(R.string.contacts_emptyList).isDisplayed();
    }
}