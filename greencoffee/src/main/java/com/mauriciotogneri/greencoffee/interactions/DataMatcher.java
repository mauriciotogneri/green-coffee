package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public abstract class DataMatcher<T, C>
{
    private final int resourceId;

    public DataMatcher(int resourceId)
    {
        this.resourceId = resourceId;
    }

    public DataMatcher()
    {
        this(0);
    }

    public ActionableData withContent(C content)
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

        return new BoundedMatcher<Object, T>(dataMatcher.dataClass())
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

    public abstract Class<T> dataClass();

    public abstract boolean matches(T data, C content);
}