package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class DataAdapterMatcher<T, C>
{
    private final int resourceId;
    private final DataMatcher<T, C> dataMatcher;

    public DataAdapterMatcher(int resourceId, DataMatcher<T, C> dataMatcher)
    {
        this.resourceId = resourceId;
        this.dataMatcher = dataMatcher;
    }

    public DataAdapterMatcher(DataMatcher<T, C> dataMatcher)
    {
        this(0, dataMatcher);
    }

    public ActionableData withContent(C content)
    {
        DataInteraction dataInteraction = onData(dataMatcher(dataMatcher, content));

        if (resourceId != 0)
        {
            return new ActionableData(dataInteraction.inAdapterView(withId(resourceId)));
        }
        else
        {
            return new ActionableData(dataInteraction);
        }
    }

    private Matcher<Object> dataMatcher(final DataMatcher<T, C> dataMatcher, final C content)
    {
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
                description.appendText("with content '" + content + "'");
            }
        };
    }

    public interface DataMatcher<T, C>
    {
        Class<T> dataClass();

        boolean matches(T data, C content);
    }
}