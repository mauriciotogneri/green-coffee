package com.mauriciotogneri.greencoffee;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

public class ControlledActivityTestRule<T extends Activity> extends ActivityTestRule<T>
{
    public ControlledActivityTestRule(Class<T> activityClass)
    {
        super(activityClass, false);
    }

    public ControlledActivityTestRule(Class<T> activityClass, boolean initialTouchMode)
    {
        super(activityClass, initialTouchMode, true);
    }

    public ControlledActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity)
    {
        super(activityClass, initialTouchMode, launchActivity);
    }

    public void restartActivity()
    {
        getActivity().finish();
        launchActivity(getActivityIntent());
    }
}