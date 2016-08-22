package com.mauriciotogneri.greencoffee;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.Feature;
import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.Step;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GreenCoffeeTest
{
    protected void start(String featurePath, Context context, Object target)
    {
        try
        {
            List<StepDefinition> stepDefinitions = stepDefinitions(target);
            String featureSource = featureSource(featurePath, context);
            Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
            GherkinDocument gherkinDocument = parser.parse(featureSource);
            processFeature(gherkinDocument.getFeature(), stepDefinitions);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private List<StepDefinition> stepDefinitions(Object target)
    {
        List<StepDefinition> stepDefinitions = new ArrayList<>();

        for (Method method : target.getClass().getDeclaredMethods())
        {
            Pair<String, String> pair = keyAndText(method);

            if (pair != null)
            {
                StepDefinition stepDefinition = new StepDefinition(pair.first, pair.second, method, target);
                stepDefinitions.add(stepDefinition);
            }
        }

        return stepDefinitions;
    }

    private Pair<String, String> keyAndText(Method method)
    {
        Given given = method.getAnnotation(Given.class);

        if (given != null)
        {
            return new Pair<>("Given", given.value());
        }

        When when = method.getAnnotation(When.class);

        if (when != null)
        {
            return new Pair<>("When", when.value());
        }

        Then then = method.getAnnotation(Then.class);

        if (then != null)
        {
            return new Pair<>("Then", then.value());
        }

        return null;
    }

    private void processFeature(Feature feature, List<StepDefinition> stepDefinitions) throws Exception
    {
        for (ScenarioDefinition scenario : feature.getChildren())
        {
            processScenario(scenario, stepDefinitions);
        }
    }

    private void processScenario(ScenarioDefinition scenario, List<StepDefinition> stepDefinitions) throws Exception
    {
        for (Step step : scenario.getSteps())
        {
            processStep(step, stepDefinitions);
        }
    }

    private void processStep(Step step, List<StepDefinition> stepDefinitions) throws Exception
    {
        String keyword = step.getKeyword().trim();
        String text = step.getText().trim();

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            if (stepDefinition.matches(keyword, text))
            {
                stepDefinition.invoke(text);
                return;
            }
        }

        throw new RuntimeException(String.format("Step definition not found for: '%s: %s'", keyword, text));
    }

    private String featureSource(String path, Context context) throws Exception
    {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(path);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
        {
            builder.append(line).append("\n");
        }

        reader.close();

        return builder.toString();
    }
}