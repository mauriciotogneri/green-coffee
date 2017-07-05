package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;

public class ActionableView extends ActionableObject
{
    private final ViewInteraction viewInteraction;

    public ActionableView(ViewInteraction viewInteraction)
    {
        this.viewInteraction = viewInteraction;
    }

    @Override
    public ActionableObject check(ViewAssertion viewAssertion)
    {
        return new ActionableView(viewInteraction.check(viewAssertion));
    }

    @Override
    public ActionableObject perform(ViewAction viewAction)
    {
        return new ActionableView(viewInteraction.perform(viewAction));
    }
}