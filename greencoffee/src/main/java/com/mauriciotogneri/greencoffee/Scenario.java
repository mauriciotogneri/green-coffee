package com.mauriciotogneri.greencoffee;

import java.util.Collections;
import java.util.List;

import gherkin.ast.Step;

public class Scenario
{
    private final String name;
    private final String description;
    private final List<Step> steps;

    public Scenario(String name, String description, List<Step> steps)
    {
        this.name = name;
        this.description = description;
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

    public List<Step> steps()
    {
        return steps;
    }
}