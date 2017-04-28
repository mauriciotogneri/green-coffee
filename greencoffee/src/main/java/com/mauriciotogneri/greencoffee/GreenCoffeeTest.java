package com.mauriciotogneri.greencoffee;

import android.os.Build;
import android.os.Environment;

import com.mauriciotogneri.greencoffee.exceptions.DuplicatedStepDefinitionException;
import com.mauriciotogneri.greencoffee.exceptions.NoStepsDefinedException;
import com.mauriciotogneri.greencoffee.exceptions.StepDefinitionNotFoundException;
import com.mauriciotogneri.ogma.Ogma;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
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

        beforeScenarioStarts(scenario.scenario(), scenario.locale());

        Ogma ogma = new Ogma(getTargetContext());
        ogma.locale(scenarioConfig.locale());
    }

    protected void beforeScenarioStarts(Scenario scenario, Locale locale)
    {
    }

    protected void afterScenarioEnds(Scenario scenario, Locale locale)
    {
    }

    protected void grantPermission(String permission) throws IOException
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            getInstrumentation()
                    .getUiAutomation()
                    .executeShellCommand(String.format("pm grant %s %s", getTargetContext().getPackageName(), permission))
                    .close();
        }
    }

    protected void start(GreenCoffeeSteps... targets)
    {
        if (targets.length == 0)
        {
            throw new NoStepsDefinedException();
        }

        Scenario scenario = scenarioConfig.scenario();

        testLog.logScenario(scenario);

        List<StepDefinition> stepDefinitions = new ArrayList<>();

        for (GreenCoffeeSteps greenCoffeeSteps : targets)
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

        afterScenarioEnds(scenarioConfig.scenario(), scenarioConfig.locale());
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