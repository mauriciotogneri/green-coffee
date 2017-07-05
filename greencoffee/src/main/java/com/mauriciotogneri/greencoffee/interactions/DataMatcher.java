package com.mauriciotogneri.greencoffee.interactions;

import android.support.annotation.IdRes;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class DataMatcher<T, C>
{
    private final int resourceId;
    private final Class<T> clazz;

    public DataMatcher(@IdRes int resourceId, Class<T> clazz)
    {
        this.resourceId = resourceId;
        this.clazz = clazz;
    }

    public DataMatcher(Class<T> clazz)
    {
        this(0, clazz);
    }

    public ActionableData with(C content)
    {
        DataInteraction dataInteraction = onData(dataMatcher(content));

        if (resourceId != 0)
        {
            return new ActionableData(dataInteraction.inAdapterView(withId(resourceId)));
        }
        else
        {
            return new ActionableData(dataInteraction);
        }
    }

    private Matcher<Object> dataMatcher(final C content)
    {
        final DataMatcher<T, C> dataMatcher = this;

        return new BoundedMatcher<Object, T>(clazz)
        {
            @Override
            public boolean matchesSafely(T data)
            {
                return dataMatcher.matches(data, content);
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText(String.format("with content: '%s'", content));
            }
        };
    }

    public boolean matches(T element, C content)
    {
        return element.equals(content);
    }
}