package com.mauriciotogneri.greencoffee;

import android.support.test.rule.ActivityTestRule;

import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ApplicationTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void init()
    {
        start("test.feature", activityTestRule.getActivity(), this);
    }

    @Given("^some precondition$")
    public void testScenarioGiven()
    {
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    @When("^some action by the actor$")
    public void testScenarioWhen()
    {
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    @Then("^some testable outcome is achieved$")
    public void testScenarioThen()
    {
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    @Given("^a global administrator named \"(.+)\"$")
    public void testBackgroundGiven(String name)
    {
        onView(withText(name)).check(matches(isDisplayed()));
    }
}