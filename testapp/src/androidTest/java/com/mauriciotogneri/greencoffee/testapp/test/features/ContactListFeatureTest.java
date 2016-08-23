package com.mauriciotogneri.greencoffee.testapp.test.features;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.testapp.LoginActivity;
import com.mauriciotogneri.greencoffee.Scenario;
import com.mauriciotogneri.greencoffee.testapp.test.steps.ContactListSteps;
import com.mauriciotogneri.greencoffee.testapp.test.steps.LoginSteps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;

public class ContactListFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

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
        start(new LoginSteps(), new ContactListSteps());
    }
}