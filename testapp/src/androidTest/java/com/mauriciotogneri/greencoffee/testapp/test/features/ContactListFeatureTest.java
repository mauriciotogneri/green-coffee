package com.mauriciotogneri.greencoffee.testapp.test.features;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.Scenario;
import com.mauriciotogneri.greencoffee.testapp.ContactListActivity;
import com.mauriciotogneri.greencoffee.testapp.test.steps.ContactListSteps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;

@RunWith(Parameterized.class)
public class ContactListFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<ContactListActivity> activityTestRule = new ActivityTestRule<>(ContactListActivity.class);

    public ContactListFeatureTest(Scenario scenario)
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
        start(new ContactListSteps());
    }
}