package com.mauriciotogneri.greencoffee;

import java.util.Locale;

public class ScenarioConfig
{
    private final Scenario scenario;
    private final Locale locale;
    private final String screenshotPath;

    public ScenarioConfig(Scenario scenario, Locale locale, String screenshotPath)
    {
        this.scenario = scenario;
        this.locale = locale;
        this.screenshotPath = screenshotPath;
    }

    public ScenarioConfig(Scenario scenario, Locale locale)
    {
        this(scenario, locale, null);
    }

    public ScenarioConfig(Scenario scenario, String screenshotPath)
    {
        this(scenario, null, screenshotPath);
    }

    public ScenarioConfig(Scenario scenario)
    {
        this(scenario, null, null);
    }

    public Scenario scenario()
    {
        return scenario;
    }

    public Locale locale()
    {
        return (locale != null) ? locale : Locale.getDefault();
    }

    public boolean hasScreenshotPath()
    {
        return (screenshotPath != null);
    }

    public String screenshotPath()
    {
        return screenshotPath;
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