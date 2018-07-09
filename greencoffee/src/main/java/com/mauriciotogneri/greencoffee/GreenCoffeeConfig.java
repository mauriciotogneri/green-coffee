package com.mauriciotogneri.greencoffee;

import com.mauriciotogneri.greencoffee.exceptions.InvalidExampleException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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
import gherkin.ast.Tag;

public class GreenCoffeeConfig
{
    private final List<Scenario> scenarios;
    private final Boolean screenshotOnFail;
    private final ScreenshotProvider screenshotProvider;

    private GreenCoffeeConfig(List<Scenario> scenarios, Boolean screenshotOnFail, ScreenshotProvider screenshotProvider)
    {
        this.scenarios = scenarios;
        this.screenshotOnFail = screenshotOnFail;
        this.screenshotProvider = screenshotProvider;
    }

    public GreenCoffeeConfig(Boolean screenshotOnFail, ScreenshotProvider screenshotProvider)
    {
        this(new ArrayList<>(), screenshotOnFail, screenshotProvider);
    }

    public GreenCoffeeConfig(Boolean screenshotOnFail)
    {
        this(new ArrayList<>(), screenshotOnFail, ScreenshotProvider.getDefault());
    }

    public GreenCoffeeConfig()
    {
        this(false, ScreenshotProvider.getDefault());
    }

    public List<ScenarioConfig> scenarios(Locale... locales)
    {
        List<ScenarioConfig> scenarioConfigs = new ArrayList<>();

        Locale[] finalLocales = locales;

        if (finalLocales.length == 0)
        {
            finalLocales = new Locale[1];
            finalLocales[0] = Locale.getDefault();
        }

        for (Locale locale : finalLocales)
        {
            for (Scenario scenario : scenarios)
            {
                scenarioConfigs.add(new ScenarioConfig(scenario, locale, screenshotOnFail,
                        screenshotProvider));
            }
        }

        return scenarioConfigs;
    }

    public GreenCoffeeConfig withTags(String firstTag, String... restTags)
    {
        List<String> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.addAll(Arrays.asList(restTags));

        List<Scenario> filtered = new ArrayList<>();

        for (Scenario scenario : scenarios)
        {
            if (scenario.hasTags(tagList))
            {
                filtered.add(scenario);
            }
        }

        return new GreenCoffeeConfig(filtered, screenshotOnFail, screenshotProvider);
    }

    public GreenCoffeeConfig withFeatureFromString(String featureSource)
    {
        return new GreenCoffeeConfig(scenarios(featureSource), screenshotOnFail, screenshotProvider);
    }

    public GreenCoffeeConfig withFeatureFromAssets(String featurePath) throws IOException
    {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(featurePath);

        return withFeatureFromInputStream(stream);
    }

    public GreenCoffeeConfig withFeatureFromUrl(String featureUrl) throws IOException
    {
        URL url = new URL(featureUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        return withFeatureFromInputStream(urlConnection.getInputStream());
    }

    public GreenCoffeeConfig withFeatureFromInputStream(InputStream featureInput) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(featureInput));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
        {
            builder.append(String.format("%s%n", line));
        }

        reader.close();

        return new GreenCoffeeConfig(scenarios(builder.toString()), screenshotOnFail,
                screenshotProvider);
    }

    private List<Scenario> scenarios(String featureSource)
    {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        GherkinDocument gherkinDocument = parser.parse(featureSource);
        Feature feature = gherkinDocument.getFeature();

        List<ScenarioDefinition> backgrounds = backgrounds(feature);
        List<ScenarioDefinition> scenarios = scenarios(feature);

        List<Scenario> result = new ArrayList<>();

        for (ScenarioDefinition scenarioDefinition : scenarios)
        {
            String name = scenarioDefinition.getName();
            String description = scenarioDefinition.getDescription();
            List<Step> steps = new ArrayList<>();
            List<String> tags = new ArrayList<>();

            for (ScenarioDefinition background : backgrounds)
            {
                steps.addAll(background.getSteps());
            }

            if (isScenarioNormal(scenarioDefinition))
            {
                gherkin.ast.Scenario scenarioNormal = (gherkin.ast.Scenario) scenarioDefinition;
                tags.addAll(tags(scenarioNormal.getTags()));
            }
            else if (isScenarioOutline(scenarioDefinition))
            {
                ScenarioOutline scenarioOutline = (ScenarioOutline) scenarioDefinition;
                tags.addAll(tags(scenarioOutline.getTags()));
            }

            steps.addAll(scenarioDefinition.getSteps());

            result.add(new Scenario(name, description, steps, tags));
        }

        return result;
    }

    private List<String> tags(List<Tag> tags)
    {
        List<String> result = new ArrayList<>();

        for (Tag tag : tags)
        {
            result.add(tag.getName());
        }

        return result;
    }

    private List<ScenarioDefinition> backgrounds(Feature feature)
    {
        List<ScenarioDefinition> result = new ArrayList<>();

        for (ScenarioDefinition scenario : feature.getChildren())
        {
            if (gherkin.ast.Background.class.isInstance(scenario))
            {
                result.add(scenario);
            }
        }

        return result;
    }

    private List<ScenarioDefinition> scenarios(Feature feature)
    {
        List<ScenarioDefinition> result = new ArrayList<>();

        for (ScenarioDefinition scenario : feature.getChildren())
        {
            if (isScenarioNormal(scenario))
            {
                result.add(scenario);
            }
            else if (isScenarioOutline(scenario))
            {
                ScenarioOutline scenarioOutline = (ScenarioOutline) scenario;

                for (Examples examples : scenarioOutline.getExamples())
                {
                    for (TableRow row : examples.getTableBody())
                    {
                        result.add(concreteScenario(scenarioOutline, parametersMap(examples.getTableHeader(), row)));
                    }
                }
            }
        }

        return result;
    }

    private boolean isScenarioNormal(ScenarioDefinition scenario)
    {
        return gherkin.ast.Scenario.class.isInstance(scenario);
    }

    private boolean isScenarioOutline(ScenarioDefinition scenario)
    {
        return gherkin.ast.ScenarioOutline.class.isInstance(scenario);
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
            throw new InvalidExampleException(header.getLocation().getLine(), header.getLocation().getColumn());
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