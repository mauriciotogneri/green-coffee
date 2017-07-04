package com.mauriciotogneri.greencoffee.interactions;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class SpinnerMatcher<T> extends BaseMatcher<T>
{
    private final T element;

    public SpinnerMatcher(T element)
    {
        this.element = element;
    }

    @Override
    public boolean matches(Object object)
    {
        return element.getClass().equals(object.getClass()) && element.equals(object);
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText("Spinner matcher for: " + element);
    }
}