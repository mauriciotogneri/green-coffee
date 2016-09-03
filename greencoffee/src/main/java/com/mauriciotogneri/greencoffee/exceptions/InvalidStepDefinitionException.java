package com.mauriciotogneri.greencoffee.exceptions;

import java.lang.reflect.Method;

public class InvalidStepDefinitionException extends RuntimeException
{
    public InvalidStepDefinitionException(Method method)
    {
        super(String.format("A step definition cannot have more than one annotation%n%nClass: %s%nMethod: %s%n", method.getDeclaringClass().getSimpleName(), method.getName()));
    }
}