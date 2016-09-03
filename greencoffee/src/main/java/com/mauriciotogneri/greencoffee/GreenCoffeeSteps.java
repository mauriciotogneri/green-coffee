package com.mauriciotogneri.greencoffee;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.matcher.ViewMatchers;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.exceptions.InvalidStepDefinitionException;
import com.mauriciotogneri.greencoffee.interactions.ActionableView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;

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
        String result = null;

        Given given = method.getAnnotation(Given.class);

        if (given != null)
        {
            result = given.value();
        }

        When when = method.getAnnotation(When.class);

        if (when != null)
        {
            checkInvalidStepDefinition(result, method);
            result = when.value();
        }

        Then then = method.getAnnotation(Then.class);

        if (then != null)
        {
            checkInvalidStepDefinition(result, method);
            result = then.value();
        }

        And and = method.getAnnotation(And.class);

        if (and != null)
        {
            checkInvalidStepDefinition(result, method);
            result = and.value();
        }

        But but = method.getAnnotation(But.class);

        if (but != null)
        {
            checkInvalidStepDefinition(result, method);
            result = but.value();
        }

        return result;
    }

    private void checkInvalidStepDefinition(String pattern, Method method)
    {
        if (pattern != null)
        {
            throw new InvalidStepDefinitionException(method);
        }
    }

    protected ActionableView onViewWithId(@IdRes int resourceId)
    {
        return new ActionableView(onView(ViewMatchers.withId(resourceId)));
    }

    protected ActionableView onViewWithText(@StringRes int resourceId)
    {
        return new ActionableView(onView(ViewMatchers.withText(resourceId)));
    }

    protected ActionableView onViewWithText(String text)
    {
        return new ActionableView(onView(ViewMatchers.withText(text)));
    }
}