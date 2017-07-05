package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;

public class ActionableData extends ActionableObject
{
    private final DataInteraction dataInteraction;

    public ActionableData(DataInteraction dataInteraction)
    {
        this.dataInteraction = dataInteraction;
    }

    @Override
    public ActionableObject check(ViewAssertion viewAssertion)
    {
        return new ActionableView(dataInteraction.check(viewAssertion));
    }

    @Override
    public ActionableObject perform(ViewAction viewAction)
    {
        return new ActionableView(dataInteraction.perform(viewAction));
    }
}