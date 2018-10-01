package com.mauriciotogneri.greencoffee;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.TimeUnit;

public class TimedIdlingResource implements IdlingResource
{
    private final long timeout;
    private ResourceCallback resourceCallback;

    public TimedIdlingResource(long millis)
    {
        this.timeout = System.currentTimeMillis() + millis;
    }

    public TimedIdlingResource(long value, TimeUnit timeUnit)
    {
        this.timeout = timeUnit.toMillis(value);
    }

    @Override
    public String getName()
    {
        return getClass().getName() + ":" + (timeout - System.currentTimeMillis());
    }

    @Override
    public boolean isIdleNow()
    {
        boolean idle = System.currentTimeMillis() >= timeout;

        if (idle && (resourceCallback != null))
        {
            resourceCallback.onTransitionToIdle();
        }

        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback)
    {
        this.resourceCallback = resourceCallback;
    }

    public void start()
    {
        Espresso.registerIdlingResources(this);
    }

    public void stop()
    {
        Espresso.unregisterIdlingResources(this);
    }
}