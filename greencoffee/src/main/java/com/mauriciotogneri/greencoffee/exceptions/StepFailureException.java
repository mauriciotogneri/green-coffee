package com.mauriciotogneri.greencoffee.exceptions;

import java.lang.reflect.Method;

public class StepFailureException extends RuntimeException
{
    public StepFailureException(Method method, String expression, String text, Throwable cause)
    {
        super(String.format("Error processing step%n%nClass: %s%nMethod: %s%nExpression: \"%s\"%nText matched: \"%s\"%n%n%s", method.getDeclaringClass().getSimpleName(), method.getName(), expression, text, cause.toString()));
    }
}