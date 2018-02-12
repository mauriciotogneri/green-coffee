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

    public Scenario scenario()
    {
        return scenario;
    }

    public Locale locale()
    {
        return locale;
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
        return String.format("%s - %s", scenario.name(), locale.toString());
    }
}