package com.mauriciotogneri.greencoffee;

import java.io.File;
import java.util.Locale;

public class ScenarioConfig
{
    private final Scenario scenario;
    private final Locale locale;
    private final File screenshotFolder;

    public ScenarioConfig(Scenario scenario, Locale locale, File screenshotFolder)
    {
        this.scenario = scenario;
        this.locale = locale;
        this.screenshotFolder = screenshotFolder;
    }

    public ScenarioConfig(Scenario scenario, Locale locale)
    {
        this(scenario, locale, null);
    }

    public ScenarioConfig(Scenario scenario, File screenshotFolder)
    {
        this(scenario, null, screenshotFolder);
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

    public boolean hasScreenshotFolder()
    {
        return (screenshotFolder != null);
    }

    public File screenshotFolder()
    {
        return screenshotFolder;
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