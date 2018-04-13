package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matchers;

public abstract class ActionableObject
{
    public ActionableObject click()
    {
        return perform(ViewActions.click());
    }

    public ActionableObject doubleClick()
    {
        return perform(ViewActions.doubleClick());
    }

    public ActionableObject longClick()
    {
        return perform(ViewActions.longClick());
    }

    public ActionableObject type(String text)
    {
        return perform(ViewActions.typeText(text));
    }

    public ActionableObject clearText()
    {
        return perform(ViewActions.clearText());
    }

    public ActionableObject scrollTo()
    {
        return perform(ViewActions.scrollTo());
    }

    public ActionableObject swipeUp()
    {
        return perform(ViewActions.swipeUp());
    }

    public ActionableObject swipeDown()
    {
        return perform(ViewActions.swipeDown());
    }

    public ActionableObject swipeLeft()
    {
        return perform(ViewActions.swipeLeft());
    }

    public ActionableObject swipeRight()
    {
        return perform(ViewActions.swipeRight());
    }

    public ActionableObject doesNotExist()
    {
        return check(ViewAssertions.doesNotExist());
    }

    public boolean checkIfDoesNotExist()
    {
        return checkIf(this::doesNotExist);
    }

    public ActionableObject contains(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString(text.toString()))));
    }

    public boolean checkIfContains(Object text)
    {
        return checkIf(() -> contains(text));
    }

    public ActionableObject doesNotContain(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.not(Matchers.containsString(text.toString())))));
    }

    public boolean checkIfDoesNotContain(Object text)
    {
        return checkIf(() -> doesNotContain(text));
    }

    public ActionableObject isEmpty()
    {
        return check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

    public boolean checkIfIsEmpty()
    {
        return checkIf(this::isEmpty);
    }

    public ActionableObject isNotEmpty()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))));
    }

    public boolean checkIfIsNotEmpty()
    {
        return checkIf(this::isNotEmpty);
    }

    public ActionableObject hasFocus()
    {
        return check(ViewAssertions.matches(ViewMatchers.hasFocus()));
    }

    public boolean checkIfHasFocus()
    {
        return checkIf(this::hasFocus);
    }

    public ActionableObject doesNotHaveFocus()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.hasFocus())));
    }

    public boolean checkIfDoesNotHaveFocus()
    {
        return checkIf(this::doesNotHaveFocus);
    }

    public ActionableObject hasErrorText(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.hasErrorText(text.toString())));
    }

    public boolean checkIfHasErrorText(Object text)
    {
        return checkIf(() -> hasErrorText(text));
    }

    public ActionableObject isChecked()
    {
        return check(ViewAssertions.matches(ViewMatchers.isChecked()));
    }

    public boolean checkIfIsChecked()
    {
        return checkIf(this::isChecked);
    }

    public ActionableObject isNotChecked()
    {
        return check(ViewAssertions.matches(ViewMatchers.isNotChecked()));
    }

    public boolean checkIfIsNotChecked()
    {
        return checkIf(this::isNotChecked);
    }

    public ActionableObject isClickable()
    {
        return check(ViewAssertions.matches(ViewMatchers.isClickable()));
    }

    public boolean checkIfIsClickable()
    {
        return checkIf(this::isClickable);
    }

    public ActionableObject isFocusable()
    {
        return check(ViewAssertions.matches(ViewMatchers.isFocusable()));
    }

    public boolean checkIfIsFocusable()
    {
        return checkIf(this::isFocusable);
    }

    public ActionableObject isNotFocusable()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isFocusable())));
    }

    public boolean checkIfIsNotFocusable()
    {
        return checkIf(this::isNotFocusable);
    }

    public ActionableObject isEnabled()
    {
        return check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    public boolean checkIfIsEnabled()
    {
        return checkIf(this::isEnabled);
    }

    public ActionableObject isDisabled()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));
    }

    public boolean checkIfIsDisabled()
    {
        return checkIf(this::isDisabled);
    }

    public ActionableObject isSelected()
    {
        return check(ViewAssertions.matches(ViewMatchers.isSelected()));
    }

    public boolean checkIfIsSelected()
    {
        return checkIf(this::isSelected);
    }

    public ActionableObject isNotSelected()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isSelected())));
    }

    public boolean checkIfIsNotSelected()
    {
        return checkIf(this::isNotSelected);
    }

    public ActionableObject isDisplayed()
    {
        return check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public boolean checkIfIsDisplayed()
    {
        return checkIf(this::isDisplayed);
    }

    public ActionableObject isCompletelyDisplayed()
    {
        return check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }

    public boolean checkIfIsCompletelyDisplayed()
    {
        return checkIf(this::isCompletelyDisplayed);
    }

    public ActionableObject isNotDisplayed()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));
    }

    public boolean checkIfIsNotDisplayed()
    {
        return checkIf(this::isNotDisplayed);
    }

    public ActionableObject hasDrawable()
    {
        return check(ViewAssertions.matches(hasDrawableImageView()));
    }

    public boolean checkIfHasDrawable()
    {
        return checkIf(this::hasDrawable);
    }

    public ActionableObject doesNotHaveDrawable()
    {
        return check(ViewAssertions.matches(Matchers.not(hasDrawableImageView())));
    }

    public boolean checkIfDoesNotHaveDrawable()
    {
        return checkIf(this::doesNotHaveDrawable);
    }

    public abstract ActionableObject check(ViewAssertion viewAssertion);

    public abstract ActionableObject perform(ViewAction viewAction);

    public abstract String text();

    private boolean checkIf(Runnable runnable)
    {
        try
        {
            runnable.run();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public BoundedMatcher<View, ImageView> hasDrawableImageView()
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