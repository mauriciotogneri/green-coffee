package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.testapp.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

@SuppressWarnings("unused")
public class ContactListSteps extends GreenCoffeeSteps
{
    @When("^I select a contact from the list$")
    public void iSelectAContactFromTheList()
    {
        //onData(withText("Item Text")).inAdapterView(withId(R.id.cardsGridView)).atPosition(0).perform(click());
        //onData(withText("Freddie James")).perform(click());

        /*onData(anything())
                .inAdapterView(withId(R.id.contacts_list))
                .atPosition(1)
                .perform(click());*/

        //onData(allOf(is(instanceOf(Contact.class)), withText("Freddie James"))).perform(click());

        /*onData(withText("Freddie James"))
                .inAdapterView(withId(R.id.contacts_list))
                //.atPosition(1)
                .perform(click());*/

        onData(anything())
                .inAdapterView(withId(R.id.contacts_list))
                .atPosition(2)
                .perform(click());

        //onData(allOf(is(Contact.class), hasSibling(withText("Sample Text Here 1"))))

        //onData(withText("Freddie James")).perform(click());
    }

    @Then("^I see the detail screen for that contact$")
    public void iSeeTheDetailScreenForThatContact()
    {
    }
}