package com.mauriciotogneri.greencoffee.testapp.test.features;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.ScenarioConfig;
import com.mauriciotogneri.greencoffee.testapp.activities.LoginActivity;
import com.mauriciotogneri.greencoffee.testapp.test.steps.ContactListSteps;
import com.mauriciotogneri.greencoffee.testapp.test.steps.LoginSteps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;

import static com.mauriciotogneri.greencoffee.testapp.test.TestSuite.ENGLISH;
import static com.mauriciotogneri.greencoffee.testapp.test.TestSuite.SPANISH;

@RunWith(Parameterized.class)
public class LoginFeatureTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> activity = new ActivityTestRule<>(LoginActivity.class);

    public LoginFeatureTest(ScenarioConfig scenarioConfig)
    {
        super(scenarioConfig);
    }

    @Parameters
    public static Iterable<ScenarioConfig> scenarios() throws IOException
    {
        return new GreenCoffeeConfig("testapp")
                .withFeatureFromAssets("assets/login.feature")
                .scenarios(ENGLISH, SPANISH);
    }

    @Test
    public void test()
    {
        start(activity, new LoginSteps(), new ContactListSteps());
    }
}