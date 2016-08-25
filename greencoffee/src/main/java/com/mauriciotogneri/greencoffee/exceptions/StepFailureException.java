package com.mauriciotogneri.greencoffee.exceptions;

import java.lang.reflect.Method;

public class StepFailureException extends RuntimeException
{
    public StepFailureException(Method method, String pattern, String text, String cause)
    {
        super(String.format("Error processing step%n%nClass: %s%nMethod: %s%nPattern: \"%s\"%nText matched: \"%s\"%n%n%s", method.getDeclaringClass().getSimpleName(), method.getName(), pattern, text, cause));
    }
}