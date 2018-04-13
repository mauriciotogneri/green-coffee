package com.mauriciotogneri.greencoffee;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.view.ViewGroup;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.exceptions.InvalidStepDefinitionException;
import com.mauriciotogneri.greencoffee.interactions.ActionableData;
import com.mauriciotogneri.greencoffee.interactions.ActionableObject;
import com.mauriciotogneri.greencoffee.interactions.ActionableView;
import com.mauriciotogneri.greencoffee.interactions.DataMatcher;
import com.mauriciotogneri.greencoffee.interactions.ObjectMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class GreenCoffeeSteps
{
    List<StepDefinition> stepDefinitions()
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

    protected ActionableObject onViewWithId(@IdRes int resourceId)
    {
        return new ActionableView(onView(withId(resourceId)));
    }

    protected ActionableObject onViewWithId(@IdRes int resourceId, int index)
    {
        return new ActionableView(onView(withIndex(withId(resourceId), index)));
    }

    protected ActionableObject onViewWithText(@StringRes int resourceId)
    {
        return new ActionableView(onView(ViewMatchers.withText(resourceId)));
    }

    protected ActionableObject onViewWithText(@StringRes int resourceId, int index)
    {
        return new ActionableView(onView(withIndex(ViewMatchers.withText(resourceId), index)));
    }

    protected ActionableObject onViewWithText(Object text)
    {
        return new ActionableView(onView(ViewMatchers.withText(text.toString())));
    }

    protected ActionableObject onViewWithText(Object text, int index)
    {
        return new ActionableView(onView(withIndex(ViewMatchers.withText(text.toString()), index)));
    }

    protected ActionableObject onViewWithAll(Matcher<? super View>... matchers)
    {
        return new ActionableView(onView(allOf(matchers)));
    }

    protected <T> ActionableObject onViewWithObject(T object)
    {
        return new ActionableData(onData(new ObjectMatcher<>(object)));
    }

    protected <T> ActionableObject onViewWithObject(@IdRes int resourceId, Class<T> clazz, T object)
    {
        return new DataMatcher<>(resourceId, clazz).with(object);
    }

    protected ActionableObject onViewChildOf(@IdRes int parentViewId, int index)
    {
        return new ActionableView(onView(nthChildOf(withId(parentViewId), index)));
    }

    protected void pressBack()
    {
        Espresso.pressBack();
    }

    protected void closeKeyboard()
    {
        Espresso.closeSoftKeyboard();
    }

    protected String string(@StringRes int stringId)
    {
        return InstrumentationRegistry.getTargetContext().getString(stringId);
    }

    protected Locale locale()
    {
        return new Localization(InstrumentationRegistry.getTargetContext()).locale();
    }

    protected void takeScreenshot(File file)
    {
        ScreenCapture screenCapture = new ScreenCapture();
        screenCapture.takeScreenshot(file);
    }

    protected Matcher<View> withIndex(Matcher<View> matcher, int index)
    {
        return new TypeSafeMatcher<View>()
        {
            private int currentIndex;
            private int viewObjHash;

            @Override
            public void describeTo(Description description)
            {
                description.appendText(String.format("with index: %d", index));
            }

            @Override
            public boolean matchesSafely(View view)
            {
                if (matcher.matches(view) && (currentIndex++ == index))
                {
                    viewObjHash = view.hashCode();
                }

                return (view.hashCode() == viewObjHash);
            }
        };
    }

    protected Matcher<View> nthChildOf(Matcher<View> parentMatcher, int childPosition)
    {
        return new TypeSafeMatcher<View>()
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText(String.format("with %d child view of type parentMatcher", childPosition));
            }

            @Override
            public boolean matchesSafely(View view)
            {
                if (!(view.getParent() instanceof ViewGroup))
                {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();

                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    protected void waitFor(long value, TimeUnit timeUnit)
    {
        onView(isRoot()).perform(actionWaitFor(value, timeUnit));
    }

    protected void waitFor(long millis)
    {
        waitFor(millis, TimeUnit.MILLISECONDS);
    }

    private ViewAction actionWaitFor(long value, TimeUnit timeUnit)
    {
        long millis = timeUnit.toMillis(value);

        return new ViewAction()
        {
            @Override
            public Matcher<View> getConstraints()
            {
                return isRoot();
            }

            @Override
            public String getDescription()
            {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, View view)
            {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}