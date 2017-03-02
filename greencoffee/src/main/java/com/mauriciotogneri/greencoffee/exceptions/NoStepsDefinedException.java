package com.mauriciotogneri.greencoffee.exceptions;

public class NoStepsDefinedException extends RuntimeException
{
    public NoStepsDefinedException()
    {
        super("No steps defined for the feature");
    }
}