package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;

public class ActionableData
{
    private final DataInteraction dataInteraction;

    public ActionableData(DataInteraction dataInteraction)
    {
        this.dataInteraction = dataInteraction;
    }

    public ActionableView click()
    {
        return perform(ViewActions.click());
    }

    public ActionableView doubleClick()
    {
        return perform(ViewActions.doubleClick());
    }

    public ActionableView longClick()
    {
        return perform(ViewActions.longClick());
    }

    public ActionableView scrollTo()
    {
        return perform(ViewActions.scrollTo());
    }

    private ActionableView perform(ViewAction viewAction)
    {
        return new ActionableView(dataInteraction.perform(viewAction));
    }
}