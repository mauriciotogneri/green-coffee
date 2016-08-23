package com.mauriciotogneri.greencoffee;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.annotations.Given;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;

public class ContactsActivityTest extends GreenCoffee
{
    @Rule
    public ActivityTestRule<ContactsActivity> activityTestRule = new ActivityTestRule<>(ContactsActivity.class);

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
}