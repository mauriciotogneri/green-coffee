package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.interactions.DataMatcher;
import com.mauriciotogneri.greencoffee.testapp.R;
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

    @When("^I select the contact called \"([\\w| ]+)\"$")
    public void iSelectTheContactCalled$(String username)
    {
        dataMatcher.withContent(username).click();
    }

    @Then("^I see an empty contact list$")
    public void iSeeAnEmptyContactList()
    {
        onViewWithText(R.string.contacts_emptyList).isDisplayed();
    }

    @Then("^I see the contacts screen$")
    public void iSeeTheContactsScreen()
    {
        onViewWithText(R.string.contacts_title).isDisplayed();
    }
}