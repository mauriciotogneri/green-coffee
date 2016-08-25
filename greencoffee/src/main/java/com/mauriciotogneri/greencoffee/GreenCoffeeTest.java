package com.mauriciotogneri.greencoffee;

import android.text.TextUtils;

import com.mauriciotogneri.greencoffee.exceptions.DuplicatedStepDefinitionException;
import com.mauriciotogneri.greencoffee.exceptions.StepDefinitionNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gherkin.ast.Step;

// TODO: generate reports
public class GreenCoffeeTest
{
    private final Scenario scenario;

    public GreenCoffeeTest(Scenario scenario)
    {
        this.scenario = scenario;
    }

    protected void start(GreenCoffeeSteps firstTarget, GreenCoffeeSteps... restTargets)
    {
        log(String.format("Scenario: %s", scenario.name()));

        if (!TextUtils.isEmpty(scenario.description()))
        {
            logDescription("\t", scenario.description());
        }

        List<StepDefinition> stepDefinitions = firstTarget.stepDefinitions();

        for (GreenCoffeeSteps greenCoffeeSteps : restTargets)
        {
            stepDefinitions.addAll(greenCoffeeSteps.stepDefinitions());
        }

        validateStepDefinitions(stepDefinitions);

        for (Step step : scenario.steps())
        {
            processStep(step, stepDefinitions);
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

        log(String.format("\t%s %s", keyword, text));

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