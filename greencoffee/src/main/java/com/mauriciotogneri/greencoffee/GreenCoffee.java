package com.mauriciotogneri.greencoffee;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import gherkin.ast.Step;

@RunWith(Parameterized.class)
public class GreenCoffee
{
    private final Scenario scenario;

    public GreenCoffee(Scenario scenario)
    {
        this.scenario = scenario;
    }

    protected void start(Object target)
    {
        log(String.format("\tScenario: %s", scenario.name()));

        if (!TextUtils.isEmpty(scenario.description()))
        {
            logDescription("\t\t", scenario.description());
        }

        List<StepDefinition> stepDefinitions = stepDefinitions(target);

        for (Step step : scenario.steps())
        {
            processStep(step, stepDefinitions);
        }
    }

    private List<StepDefinition> stepDefinitions(Object target)
    {
        List<StepDefinition> stepDefinitions = new ArrayList<>();

        for (Method method : target.getClass().getDeclaredMethods())
        {
            String expression = expression(method);

            if (expression != null)
            {
                StepDefinition stepDefinition = new StepDefinition(expression, method, target);
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

    private void processStep(Step step, List<StepDefinition> stepDefinitions)
    {
        String keyword = step.getKeyword().trim();
        String text = step.getText().trim();

        log(String.format("\t\t%s %s", keyword, text));

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            if (stepDefinition.matches(text))
            {
                stepDefinition.invoke(text);
                return;
            }
        }

        throw new RuntimeException(String.format("Step definition not found for: '%s: %s'", keyword, text));
    }

    private void logDescription(String tab, String description)
    {
        for (String line : description.split("\n"))
        {
            log(String.format("%s%s", tab, line.trim()));
        }
    }

    private void log(String message)
    {
        System.out.println(message);
        System.out.flush();
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

    protected void isDisplayedWithId(@IdRes int resourceId)
    {
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    protected void isDisplayedWithText(@StringRes int resourceId)
    {
        onView(withText(resourceId)).check(matches(isDisplayed()));
    }
}