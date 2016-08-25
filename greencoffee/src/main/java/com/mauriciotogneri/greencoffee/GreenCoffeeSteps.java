package com.mauriciotogneri.greencoffee;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.interactions.ActionableView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class GreenCoffeeSteps
{
    public List<StepDefinition> stepDefinitions()
    {
        List<StepDefinition> stepDefinitions = new ArrayList<>();

        for (Method method : getClass().getDeclaredMethods())
        {
            String pattern = pattern(method);

            if (pattern != null)
            {
                StepDefinition stepDefinition = new StepDefinition(pattern, method, this);
                stepDefinitions.add(stepDefinition);
            }
        }

        return stepDefinitions;
    }

    private String pattern(Method method)
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

    protected ActionableView onViewWithId(@IdRes int resourceId)
    {
        return new ActionableView(onView(withId(resourceId)));
    }

    protected ActionableView onViewWithText(@StringRes int resourceId)
    {
        return new ActionableView(onView(withText(resourceId)));
    }
}