package com.mauriciotogneri.greencoffee;

import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ApplicationTest extends GreenCoffeeTest
{
    @Rule
    public ControlledActivityTestRule<LoginActivity> activityTestRule = new ControlledActivityTestRule<>(LoginActivity.class);

    private static final String VALID_USERNAME = "admin";

    @Test
    public void init() throws IOException
    {
        start(fromAssets("assets/login.feature"), this, activityTestRule);
    }

    @Given("^an empty login form$")
    public void anEmptyLoginForm()
    {
        onView(withId(R.id.login_username)).perform(typeText(""));
        onView(withId(R.id.login_password)).perform(typeText(""));
    }

    @When("^I press the login button$")
    public void iPressTheLoginButton()
    {
        onView(withId(R.id.login_doLogin)).perform(click());
    }

    @Then("^I see an error message with 'Invalid username'$")
    public void iSeeAnErrorMessageWithInvalidUsername()
    {
        onView(withText(R.string.login_username_error)).check(matches(isDisplayed()));
    }

    @Then("^I see an error message with 'Invalid password'$")
    public void iSeeAnErrorMessageWithInvalidPassword()
    {
        onView(withText(R.string.login_password_error)).check(matches(isDisplayed()));
    }

    @When("^I introduce a valid username$")
    public void iIntroduceAUsername()
    {
        onView(withId(R.id.login_username)).perform(typeText(VALID_USERNAME));
    }
}