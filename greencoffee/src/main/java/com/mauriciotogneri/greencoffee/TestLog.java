package com.mauriciotogneri.greencoffee;

import android.text.TextUtils;

class TestLog
{
    void logScenario(Scenario scenario)
    {
        log(String.format("Scenario: %s", scenario.name()));

        if (!TextUtils.isEmpty(scenario.description()))
        {
            for (String line : scenario.description().split("\n"))
            {
                log(String.format("\t%s", line.trim()));
            }
        }
    }

    void logStep(String keyword, String text)
    {
        log(String.format("\t%s %s", keyword, text));
    }

    private void log(String message)
    {
        System.out.println(message);
        System.out.flush();
    }
}