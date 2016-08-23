package com.mauriciotogneri.greencoffee.exceptions;

public class StepDefinitionNotFoundException extends RuntimeException
{
    public StepDefinitionNotFoundException(String keyword, String text)
    {
        super(String.format("Step definition not found for: '%s: %s'", keyword, text));
    }
}