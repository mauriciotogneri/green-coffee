package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;

public class ActionableView
{
    private final ViewInteraction viewInteraction;

    public ActionableView(ViewInteraction viewInteraction)
    {
        this.viewInteraction = viewInteraction;
    }

    public ActionableView click()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.click()));
    }

    public ActionableView doubleClick()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.doubleClick()));
    }

    public ActionableView longClick()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.longClick()));
    }

    public ActionableView type(String text)
    {
        return new ActionableView(viewInteraction.perform(ViewActions.typeText(text)));
    }

    public ActionableView clearText()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.clearText()));
    }

    public ActionableView scrollTo()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.scrollTo()));
    }

    public ActionableView swipeUp()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.swipeUp()));
    }

    public ActionableView swipeDown()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.swipeDown()));
    }

    public ActionableView swipeLeft()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.swipeLeft()));
    }

    public ActionableView swipeRight()
    {
        return new ActionableView(viewInteraction.perform(ViewActions.swipeRight()));
    }

    public ActionableView doesNotExist()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.doesNotExist()));
    }

    public ActionableView contains(Object text)
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.withText(text.toString()))));
    }

    public ActionableView isEmpty()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.withText(""))));
    }

    public ActionableView hasFocus()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.hasFocus())));
    }

    public ActionableView hasErrorText(String text)
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.hasErrorText(text))));
    }

    public ActionableView isChecked()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isChecked())));
    }

    public ActionableView isNotChecked()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isNotChecked())));
    }

    public ActionableView isClickable()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isClickable())));
    }

    public ActionableView isEnabled()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isEnabled())));
    }

    public ActionableView isSelected()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isSelected())));
    }

    public ActionableView isCompletelyDisplayed()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed())));
    }

    public ActionableView isDisplayed()
    {
        return new ActionableView(viewInteraction.check(ViewAssertions.matches(ViewMatchers.isDisplayed())));
    }
}