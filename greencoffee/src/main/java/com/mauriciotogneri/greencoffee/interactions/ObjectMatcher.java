package com.mauriciotogneri.greencoffee.interactions;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ObjectMatcher<T> extends BaseMatcher<T>
{
    private final T element;

    public ObjectMatcher(T element)
    {
        this.element = element;
    }

    @Override
    public boolean matches(Object object)
    {
        return element.equals(object);
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText("Spinner matcher for: " + element);
    }
}