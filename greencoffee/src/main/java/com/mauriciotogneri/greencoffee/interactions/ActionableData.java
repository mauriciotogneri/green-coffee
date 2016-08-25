package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.DataInteraction;
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
        return new ActionableView(dataInteraction.perform(ViewActions.click()));
    }

    public ActionableView doubleClick()
    {
        return new ActionableView(dataInteraction.perform(ViewActions.doubleClick()));
    }

    public ActionableView longClick()
    {
        return new ActionableView(dataInteraction.perform(ViewActions.longClick()));
    }
}