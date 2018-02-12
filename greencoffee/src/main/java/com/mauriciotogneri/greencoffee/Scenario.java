package com.mauriciotogneri.greencoffee;

import java.util.Collections;
import java.util.List;

import gherkin.ast.Step;

public class Scenario
{
    private final String name;
    private final String description;
    private final List<String> tags;
    private final List<Step> steps;

    public Scenario(String name, String description, List<Step> steps, List<String> tags)
    {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.steps = Collections.unmodifiableList(steps);
    }

    public String name()
    {
        return name;
    }

    public String description()
    {
        return description;
    }

    public boolean hasTags(List<String> tagList)
    {
        for (String tag : tagList)
        {
            if (tags.contains(tag))
            {
                return true;
            }
        }

        return false;
    }

    public boolean hasTags(String... tagList)
    {
        for (String tag : tagList)
        {
            if (tags.contains(tag))
            {
                return true;
            }
        }

        return false;
    }

    public List<Step> steps()
    {
        return steps;
    }
}