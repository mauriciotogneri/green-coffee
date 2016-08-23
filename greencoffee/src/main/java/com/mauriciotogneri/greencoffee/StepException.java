package com.mauriciotogneri.greencoffee;

import java.lang.reflect.Method;

public class StepException extends RuntimeException
{
    public StepException(Method method, String expression, String text, Throwable cause)
    {
        super(String.format("Error processing step%n%nClass: %s%nMethod: %s%nExpression: \"%s\"%nText matched: \"%s\"%n%n%s", method.getDeclaringClass().getSimpleName(), method.getName(), expression, text, cause.toString()));
    }
}