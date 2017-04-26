package com.mauriciotogneri.greencoffee;

import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.view.ViewGroup;

import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.But;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.exceptions.InvalidStepDefinitionException;
import com.mauriciotogneri.greencoffee.interactions.ActionableView;
import com.mauriciotogneri.ogma.Ogma;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.support.test.espresso.Espresso.onView;

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

    /**
     * Find view with id, default return first found view
     *
     * @param resourceId
     * @return first found view
     */
    protected ActionableView onViewWithId(@IdRes int resourceId)
    {
        return onViewWithId(resourceId, 0);
    }

    /**
     * Find view with id and index for matches multiple views
     *
     * @param resourceId
     * @param index
     * @return found view
     */
    protected ActionableView onViewWithId(@IdRes int resourceId, int index)
    {
        return new ActionableView(onView(withIndex(ViewMatchers.withId(resourceId), index)));
    }

    /**
     * Find view with text, default return first found view
     *
     * @param resourceId
     * @return first found view
     */
    protected ActionableView onViewWithText(@StringRes int resourceId)
    {
        return onViewWithText(resourceId, 0);
    }

    /**
     * Find view with text and index for matches multiple views
     *
     * @param resourceId
     * @param index
     * @return found view
     */
    protected ActionableView onViewWithText(@StringRes int resourceId, int index)
    {
        return new ActionableView(onView(withIndex(ViewMatchers.withText(resourceId), index)));
    }

    /**
     * Find view with text, default return first found view
     *
     * @param text
     * @return first found view
     */
    protected ActionableView onViewWithText(String text)
    {
        return onViewWithText(text, 0);
    }

    /**
     * Find view with text and index for matches multiple views
     *
     * @param text
     * @param index
     * @return
     */
    protected ActionableView onViewWithText(String text, int index)
    {
        return new ActionableView(onView(withIndex(ViewMatchers.withText(text), index)));
    }

    /**
     * Find child view on parent view with index
     *
     * @param parentViewId parent view id
     * @param index
     * @return found child view
     */
    public ActionableView onViewChildOf(@IdRes int parentViewId, int index)
    {
        return new ActionableView(onView(nthChildOf(ViewMatchers.withId(parentViewId), index)));
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
        return new Ogma(InstrumentationRegistry.getTargetContext()).locale();
    }

    protected void takeScreenshot(String fileName)
    {
        String path = String.format("%s/%s.jpg", Environment.getExternalStorageDirectory().toString(), fileName);

        ScreenCapture screenCapture = new ScreenCapture();
        screenCapture.takeScreenshot(path);
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index)
    {
        return new TypeSafeMatcher<View>()
        {
            int currentIndex;
            int viewObjHash;

            @Override
            public void describeTo(Description description)
            {
                description.appendText(String.format("with index: %d ", index));
            }

            @Override
            public boolean matchesSafely(View view)
            {
                if (matcher.matches(view) && currentIndex++ == index)
                {
                    viewObjHash = view.hashCode();
                }
                return view.hashCode() == viewObjHash;
            }
        };
    }

    private static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition)
    {
        return new TypeSafeMatcher<View>()
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText("with " + childPosition + " child view of type parentMatcher");
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
}