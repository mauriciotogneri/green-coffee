package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.interactions.DataAdapterMatcher;
import com.mauriciotogneri.greencoffee.interactions.DataAdapterMatcher.DataMatcher;
import com.mauriciotogneri.greencoffee.testapp.Contact;
import com.mauriciotogneri.greencoffee.testapp.R;

@SuppressWarnings("unused")
public class ContactListSteps extends GreenCoffeeSteps
{
    private final DataAdapterMatcher<Contact, String> dataAdapterMatcher;

    public ContactListSteps()
    {
        this.dataAdapterMatcher = new DataAdapterMatcher<>(R.id.contacts_list, contactMatcher);
    }

    @When("^I select a contact from the list$")
    public void iSelectAContactFromTheList()
    {
        dataAdapterMatcher.withContent("Maddison Wallace").click();
    }

    @Then("^I see the detail screen for that contact$")
    public void iSeeTheDetailScreenForThatContact()
    {
        onViewWithText(R.string.details_title).isDisplayed();
    }

    private static final DataMatcher<Contact, String> contactMatcher = new DataMatcher<Contact, String>()
    {
        @Override
        public Class<Contact> dataClass()
        {
            return Contact.class;
        }

        @Override
        public boolean matches(Contact contact, String content)
        {
            return contact.name().equals(content);
        }
    };
}