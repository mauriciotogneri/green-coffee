package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.interactions.DataMatcher;
import com.mauriciotogneri.greencoffee.testapp.model.Contact;
import com.mauriciotogneri.greencoffee.testapp.R;
import com.mauriciotogneri.greencoffee.testapp.test.matchers.ContactMatcher;

@SuppressWarnings("unused")
public class ContactListSteps extends GreenCoffeeSteps
{
    private final DataMatcher<Contact, String> dataMatcher;

    public ContactListSteps()
    {
        this.dataMatcher = new ContactMatcher(R.id.contacts_list);
    }

    @When("^I select a contact from the list$")
    public void iSelectAContactFromTheList()
    {
        dataMatcher.withContent("Maddison Wallace").click();
    }

    @Then("^I see the detail screen for that contact$")
    public void iSeeTheDetailScreenForThatContact()
    {
        onViewWithText(R.string.details_title).isDisplayed();
    }
}