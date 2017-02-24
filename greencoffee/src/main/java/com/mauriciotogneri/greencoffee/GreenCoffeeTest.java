package com.mauriciotogneri.greencoffee;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.support.test.rule.ActivityTestRule;
import android.util.DisplayMetrics;

import com.mauriciotogneri.greencoffee.exceptions.DuplicatedStepDefinitionException;
import com.mauriciotogneri.greencoffee.exceptions.StepDefinitionNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import gherkin.ast.Node;
import gherkin.ast.Step;

public class GreenCoffeeTest
{
    private final ScenarioConfig scenarioConfig;
    private final TestLog testLog;

    public GreenCoffeeTest(ScenarioConfig scenario)
    {
        this.scenarioConfig = scenario;
        this.testLog = new TestLog();
    }

    protected void start(ActivityTestRule<? extends Activity> activity, GreenCoffeeSteps firstTarget, GreenCoffeeSteps... restTargets)
    {
        locale(activity);

        Scenario scenario = scenarioConfig.scenario();

        testLog.logScenario(scenario);

        List<StepDefinition> stepDefinitions = firstTarget.stepDefinitions();

        for (GreenCoffeeSteps greenCoffeeSteps : restTargets)
        {
            stepDefinitions.addAll(greenCoffeeSteps.stepDefinitions());
        }

        validateStepDefinitions(stepDefinitions);

        try
        {
            for (Step step : scenario.steps())
            {
                processStep(step, stepDefinitions);
            }
        }
        catch (Exception e)
        {
            if (scenarioConfig.hasScreenshotPath())
            {
                String date = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());
                String path = String.format("%s/%s/%s_%s.jpg", Environment.getExternalStorageDirectory().toString(), scenarioConfig.screenshotPath(), date, scenario.name().replace(" ", "_"));

                ScreenCapture screenCapture = new ScreenCapture();
                screenCapture.takeScreenshot(path);
            }

            throw e;
        }
    }

    private void locale(ActivityTestRule<? extends Activity> activity)
    {
        Locale locale = scenarioConfig.locale();

        Resources resources = activity.getActivity().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        if (!config.locale.equals(locale))
        {
            config.locale = locale;
            resources.updateConfiguration(config, displayMetrics);
        }
    }

    private void validateStepDefinitions(List<StepDefinition> stepDefinitions)
    {
        Set<String> patterns = new HashSet<>();

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            String pattern = stepDefinition.pattern();

            if (!patterns.contains(pattern))
            {
                patterns.add(pattern);
            }
            else
            {
                throw new DuplicatedStepDefinitionException(stepDefinition.method(), pattern);
            }
        }
    }

    private void processStep(Step step, List<StepDefinition> stepDefinitions)
    {
        String keyword = step.getKeyword().trim();
        String text = step.getText().trim();
        Node argument = step.getArgument();

        testLog.logStep(keyword, text);

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            if (stepDefinition.matches(text))
            {
                stepDefinition.invoke(text, argument);
                return;
            }
        }

        throw new StepDefinitionNotFoundException(keyword, text);
    }
}