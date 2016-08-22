package com.mauriciotogneri.greencoffee;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.Feature;
import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.Step;

public class GreenCoffeeConfig
{
    public List<Scenario> fromAssets(String featurePath) throws IOException
    {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(featurePath);

        return fromInputStream(stream);
    }

    public List<Scenario> fromUrl(String featureUrl) throws IOException
    {
        URL url = new URL(featureUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        return fromInputStream(urlConnection.getInputStream());
    }

    public List<Scenario> fromInputStream(InputStream featureInput) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(featureInput));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
        {
            builder.append(line).append("\n");
        }

        reader.close();

        return scenarios(builder.toString());
    }

    private List<Scenario> scenarios(String featureSource)
    {
        List<Scenario> result = new ArrayList<>();

        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        GherkinDocument gherkinDocument = parser.parse(featureSource);
        Feature feature = gherkinDocument.getFeature();

        List<ScenarioDefinition> backgrounds = filterScenariosBy(feature, "Background");
        List<ScenarioDefinition> scenarios = filterScenariosBy(feature, "Scenario");

        for (ScenarioDefinition scenarioDefinition : scenarios)
        {
            String name = scenarioDefinition.getName();
            String description = scenarioDefinition.getDescription();
            List<Step> steps = new ArrayList<>();

            for (ScenarioDefinition background : backgrounds)
            {
                steps.addAll(background.getSteps());
            }

            steps.addAll(scenarioDefinition.getSteps());

            result.add(new Scenario(name, description, steps));
        }

        return result;
    }

    private List<ScenarioDefinition> filterScenariosBy(Feature feature, String keyword)
    {
        List<ScenarioDefinition> backgrounds = new ArrayList<>();

        for (ScenarioDefinition scenario : feature.getChildren())
        {
            if (TextUtils.equals(scenario.getKeyword(), keyword))
            {
                backgrounds.add(scenario);
            }
        }

        return backgrounds;
    }
}