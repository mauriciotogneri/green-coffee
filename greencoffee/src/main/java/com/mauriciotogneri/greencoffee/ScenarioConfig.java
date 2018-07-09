package com.mauriciotogneri.greencoffee;

import java.util.Locale;

public class ScenarioConfig
{
    private final Scenario scenario;
    private final Locale locale;
    private final Boolean screenshotOnFail;
    private final ScreenshotProvider screenshotProvider;

    public ScenarioConfig(Scenario scenario, Locale locale, Boolean screenshotOnFail,
                          ScreenshotProvider screenshotProvider)
    {
        this.scenario = scenario;
        this.locale = locale;
        this.screenshotOnFail = screenshotOnFail;
        this.screenshotProvider = screenshotProvider;
    }

    public ScenarioConfig(Scenario scenario, Locale locale)
    {
        this(scenario, locale, false, ScreenshotProvider.getDefault());
    }

    public ScenarioConfig(Scenario scenario, Boolean screenshotOnFail)
    {
        this(scenario, null, screenshotOnFail, ScreenshotProvider.getDefault());
    }

    public ScenarioConfig(Scenario scenario)
    {
        this(scenario, null, false, ScreenshotProvider.getDefault());
    }

    public Scenario scenario()
    {
        return scenario;
    }

    public Locale locale()
    {
        return (locale != null) ? locale : Locale.getDefault();
    }

    public Boolean screenshotOnFail()
    {
        return screenshotOnFail;
    }

    public ScreenshotProvider screenshotProvider()
    {
        return screenshotProvider;
    }

    @Override
    public String toString()
    {
        if (locale != null)
        {
            return String.format("%s - %s", scenario.name(), locale.toString());
        }
        else
        {
            return scenario.name();
        }
    }
}