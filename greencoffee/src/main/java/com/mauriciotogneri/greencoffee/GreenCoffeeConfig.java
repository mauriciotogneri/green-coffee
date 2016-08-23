package com.mauriciotogneri.greencoffee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.Examples;
import gherkin.ast.Feature;
import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.ScenarioOutline;
import gherkin.ast.Step;
import gherkin.ast.TableCell;
import gherkin.ast.TableRow;

public class GreenCoffeeConfig
{
    public List<Scenario> fromString(String featureSource)
    {
        return scenarios(featureSource);
    }

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
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        GherkinDocument gherkinDocument = parser.parse(featureSource);
        Feature feature = gherkinDocument.getFeature();

        List<ScenarioDefinition> backgrounds = filterScenariosBy(feature, gherkin.ast.Background.class);
        List<ScenarioDefinition> scenarios = filterScenariosBy(feature, gherkin.ast.Scenario.class);

        List<ScenarioOutline> scenarioOutlines = filterScenariosBy(feature, gherkin.ast.ScenarioOutline.class);
        scenarios.addAll(scenarioOutlines(scenarioOutlines));

        List<Scenario> result = new ArrayList<>();

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

    @SuppressWarnings("unchecked")
    private <T extends ScenarioDefinition> List<T> filterScenariosBy(Feature feature, Class<?> clazz)
    {
        List<T> backgrounds = new ArrayList<>();

        for (ScenarioDefinition scenario : feature.getChildren())
        {
            if (clazz.isInstance(scenario))
            {
                backgrounds.add((T) scenario);
            }
        }

        return backgrounds;
    }

    private List<ScenarioDefinition> scenarioOutlines(List<ScenarioOutline> scenarioOutlines)
    {
        List<ScenarioDefinition> result = new ArrayList<>();

        for (ScenarioOutline scenarioOutline : scenarioOutlines)
        {
            for (Examples examples : scenarioOutline.getExamples())
            {
                for (TableRow row : examples.getTableBody())
                {
                    result.add(concreteScenario(scenarioOutline, parametersMap(examples.getTableHeader(), row)));
                }
            }
        }

        return result;
    }

    private Map<String, String> parametersMap(TableRow header, TableRow row)
    {
        List<TableCell> headerCells = header.getCells();
        List<TableCell> rowCells = row.getCells();

        if (headerCells.size() == rowCells.size())
        {
            Map<String, String> parameters = new LinkedHashMap<>();

            for (int i = 0; i < headerCells.size(); i++)
            {
                TableCell headerCell = headerCells.get(i);
                TableCell rowCell = rowCells.get(i);

                parameters.put(headerCell.getValue(), rowCell.getValue());
            }

            return parameters;
        }
        else
        {
            throw new RuntimeException(String.format("Invalid example format at: [%s, %s]", header.getLocation().getLine(), header.getLocation().getColumn()));
        }
    }

    private ScenarioDefinition concreteScenario(ScenarioOutline abstractScenario, Map<String, String> parameters)
    {
        List<Step> steps = new ArrayList<>();

        for (Step step : abstractScenario.getSteps())
        {
            steps.add(concreteStep(step, parameters));
        }

        return new gherkin.ast.Scenario(
                abstractScenario.getTags(),
                abstractScenario.getLocation(),
                abstractScenario.getKeyword(),
                abstractScenario.getName(),
                abstractScenario.getDescription(),
                steps);
    }

    private Step concreteStep(Step abstractStep, Map<String, String> parameters)
    {
        String text = abstractStep.getText();

        for (Entry<String, String> entry : parameters.entrySet())
        {
            String target = String.format("<%s>", entry.getKey());
            String replacement = entry.getValue();

            text = text.replace(target, replacement);
        }

        return new Step(
                abstractStep.getLocation(),
                abstractStep.getKeyword(),
                text,
                abstractStep.getArgument());
    }
}