package com.mauriciotogneri.greencoffee.interactions;

import androidx.annotation.IdRes;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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

    private Matcher<Object> dataMatcher(C content)
    {
        DataMatcher<T, C> dataMatcher = this;

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