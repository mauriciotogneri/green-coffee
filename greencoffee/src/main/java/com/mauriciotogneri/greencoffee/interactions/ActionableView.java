package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matchers;

public class ActionableView
{
    private final ViewInteraction viewInteraction;

    public ActionableView(ViewInteraction viewInteraction)
    {
        this.viewInteraction = viewInteraction;
    }

    public ActionableView click()
    {
        return perform(ViewActions.click());
    }

    public ActionableView doubleClick()
    {
        return perform(ViewActions.doubleClick());
    }

    public ActionableView longClick()
    {
        return perform(ViewActions.longClick());
    }

    public ActionableView type(String text)
    {
        return perform(ViewActions.typeText(text));
    }

    public ActionableView clearText()
    {
        return perform(ViewActions.clearText());
    }

    public ActionableView scrollTo()
    {
        return perform(ViewActions.scrollTo());
    }

    public ActionableView swipeUp()
    {
        return perform(ViewActions.swipeUp());
    }

    public ActionableView swipeDown()
    {
        return perform(ViewActions.swipeDown());
    }

    public ActionableView swipeLeft()
    {
        return perform(ViewActions.swipeLeft());
    }

    public ActionableView swipeRight()
    {
        return perform(ViewActions.swipeRight());
    }

    public ActionableView doesNotExist()
    {
        return check(ViewAssertions.doesNotExist());
    }

    public ActionableView contains(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString(text.toString()))));
    }

    public ActionableView notContains(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.not(Matchers.containsString(text.toString())))));
    }

    public ActionableView isEmpty()
    {
        return check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

    public ActionableView isNotEmpty()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))));
    }

    public ActionableView hasFocus()
    {
        return check(ViewAssertions.matches(ViewMatchers.hasFocus()));
    }

    public ActionableView doesNotHaveFocus()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.hasFocus())));
    }

    public ActionableView hasErrorText(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.hasErrorText(text.toString())));
    }

    public ActionableView isChecked()
    {
        return check(ViewAssertions.matches(ViewMatchers.isChecked()));
    }

    public ActionableView isNotChecked()
    {
        return check(ViewAssertions.matches(ViewMatchers.isNotChecked()));
    }

    public ActionableView isClickable()
    {
        return check(ViewAssertions.matches(ViewMatchers.isClickable()));
    }

    public ActionableView isFocusable()
    {
        return check(ViewAssertions.matches(ViewMatchers.isFocusable()));
    }

    public ActionableView isNotFocusable()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isFocusable())));
    }

    public ActionableView isEnabled()
    {
        return check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    public ActionableView isDisabled()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));
    }

    public ActionableView isSelected()
    {
        return check(ViewAssertions.matches(ViewMatchers.isSelected()));
    }

    public ActionableView isNotSelected()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isSelected())));
    }

    public ActionableView isDisplayed()
    {
        return check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public ActionableView isCompletelyDisplayed()
    {
        return check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }

    public ActionableView isNotDisplayed()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));
    }

    public ActionableView isHasDrawable()
    {
        return check(ViewAssertions.matches(hasDrawable()));
    }

    public ActionableView isHasNotDrawable()
    {
        return check(ViewAssertions.matches(Matchers.not(hasDrawable())));
    }

    public ActionableView check(ViewAssertion viewAssertion)
    {
        return new ActionableView(viewInteraction.check(viewAssertion));
    }

    public ActionableView perform(ViewAction viewAction)
    {
        return new ActionableView(viewInteraction.perform(viewAction));
    }

    public BoundedMatcher<View, ImageView> hasDrawable()
    {
        return new BoundedMatcher<View, ImageView>(ImageView.class)
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText("has drawable");
            }

            @Override
            public boolean matchesSafely(ImageView imageView)
            {
                return (imageView.getDrawable() != null);
            }
        };
    }
}