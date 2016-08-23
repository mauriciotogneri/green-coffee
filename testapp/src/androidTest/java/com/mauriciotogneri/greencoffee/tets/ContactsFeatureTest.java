package com.mauriciotogneri.greencoffee.tets;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.LoginActivity;
import com.mauriciotogneri.greencoffee.Scenario;
import com.mauriciotogneri.greencoffee.steps.ContactSteps;
import com.mauriciotogneri.greencoffee.steps.LoginSteps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;

public class ContactsFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    public ContactsFeatureTest(Scenario scenario)
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
        start(new LoginSteps(), new ContactSteps());
    }
}