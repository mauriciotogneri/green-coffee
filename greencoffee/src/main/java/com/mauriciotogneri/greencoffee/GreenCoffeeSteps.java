package com.mauriciotogneri.greencoffee;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class GreenCoffeeSteps
{
    public List<StepDefinition> stepDefinitions()
    {
        List<StepDefinition> stepDefinitions = new ArrayList<>();

        for (Method method : getClass().getDeclaredMethods())
        {
            String expression = expression(method);

            if (expression != null)
            {
                StepDefinition stepDefinition = new StepDefinition(expression, method, this);
                stepDefinitions.add(stepDefinition);
            }
        }

        return stepDefinitions;
    }

    private String expression(Method method)
    {
        Given given = method.getAnnotation(Given.class);

        if (given != null)
        {
            return given.value();
        }

        When when = method.getAnnotation(When.class);

        if (when != null)
        {
            return when.value();
        }

        Then then = method.getAnnotation(Then.class);

        if (then != null)
        {
            return then.value();
        }

        And and = method.getAnnotation(And.class);

        if (and != null)
        {
            return and.value();
        }

        But but = method.getAnnotation(But.class);

        if (but != null)
        {
            return but.value();
        }

        return null;
    }

    protected void clickWithId(@IdRes int resourceId)
    {
        onView(withId(resourceId)).perform(click());
    }

    protected void clickWithText(@StringRes int resourceId)
    {
        onView(withText(resourceId)).perform(click());
    }

    protected void typeTextWithId(@IdRes int resourceId, String text)
    {
        onView(withId(resourceId)).perform(typeText(text));
    }

    protected void typeTextWithText(@StringRes int resourceId, String text)
    {
        onView(withText(resourceId)).perform(typeText(text));
    }

    protected void closeKeyboardWithId(@IdRes int resourceId)
    {
        onView(withId(resourceId)).perform(closeSoftKeyboard());
    }

    protected void closeKeyboardWithText(@StringRes int resourceId)
    {
        onView(withText(resourceId)).perform(closeSoftKeyboard());
    }

    protected void containsTextWithId(@IdRes int resourceId, String text)
    {
        onView(withId(resourceId)).check(matches(withText(text)));
    }

    protected void containsTextWithTest(@StringRes int resourceId, String text)
    {
        onView(withText(resourceId)).check(matches(withText(text)));
    }

    protected void isDisplayedWithId(@IdRes int resourceId)
    {
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    protected void isDisplayedWithText(@StringRes int resourceId)
    {
        onView(withText(resourceId)).check(matches(isDisplayed()));
    }
}