package com.mauriciotogneri.greencoffee;

import android.text.TextUtils;

import com.mauriciotogneri.greencoffee.exceptions.StepDefinitionNotFoundException;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import gherkin.ast.Step;

@RunWith(Parameterized.class)
public class GreenCoffeeTest
{
    private final Scenario scenario;

    public GreenCoffeeTest(Scenario scenario)
    {
        this.scenario = scenario;
    }

    protected void start(GreenCoffeeSteps firstTarget, GreenCoffeeSteps... restTargets)
    {
        log(String.format("\tScenario: %s", scenario.name()));

        if (!TextUtils.isEmpty(scenario.description()))
        {
            logDescription("\t\t", scenario.description());
        }

        List<StepDefinition> stepDefinitions = firstTarget.stepDefinitions();

        for (GreenCoffeeSteps greenCoffeeSteps : restTargets)
        {
            stepDefinitions.addAll(greenCoffeeSteps.stepDefinitions());
        }

        for (Step step : scenario.steps())
        {
            processStep(step, stepDefinitions);
        }
    }

    private void processStep(Step step, List<StepDefinition> stepDefinitions)
    {
        String keyword = step.getKeyword().trim();
        String text = step.getText().trim();

        log(String.format("\t\t%s %s", keyword, text));

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            if (stepDefinition.matches(text))
            {
                stepDefinition.invoke(text);
                return;
            }
        }

        throw new StepDefinitionNotFoundException(keyword, text);
    }

    private void logDescription(String tab, String description)
    {
        for (String line : description.split("\n"))
        {
            log(String.format("%s%s", tab, line.trim()));
        }
    }

    private void log(String message)
    {
        System.out.println(message);
        System.out.flush();
    }
}