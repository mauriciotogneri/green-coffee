package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ActionableView
{
    private final ViewInteraction viewInteraction;

    public ActionableView(ViewInteraction viewInteraction)
    {
        this.viewInteraction = viewInteraction;
    }

    public void click()
    {
        viewInteraction.perform(ViewActions.click());
    }

    public void type(String text)
    {
        viewInteraction.perform(ViewActions.typeText(text));
    }

    public void contains(Object text)
    {
        viewInteraction.check(matches(withText(text.toString())));
    }

    public void isDisplayed()
    {
        viewInteraction.check(matches(ViewMatchers.isDisplayed()));
    }
}