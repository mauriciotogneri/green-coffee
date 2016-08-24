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

    public void click()
    {
        dataInteraction.perform(ViewActions.click());
    }
}