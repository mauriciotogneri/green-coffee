package com.mauriciotogneri.greencoffee;

import java.util.Locale;

public class ScenarioConfig
{
    private final Scenario scenario;
    private final Locale locale;
    private final String screenshotPath;

    ScenarioConfig(Scenario scenario, Locale locale, String screenshotPath)
    {
        this.scenario = scenario;
        this.locale = locale;
        this.screenshotPath = screenshotPath;
    }

    Scenario scenario()
    {
        return scenario;
    }

    Locale locale()
    {
        return locale;
    }

    boolean hasScreenshotPath()
    {
        return (screenshotPath != null);
    }

    String screenshotPath()
    {
        return screenshotPath;
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", scenario.name(), locale.toString());
    }
}