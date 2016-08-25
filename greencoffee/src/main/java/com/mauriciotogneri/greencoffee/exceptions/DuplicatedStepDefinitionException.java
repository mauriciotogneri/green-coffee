package com.mauriciotogneri.greencoffee.exceptions;

import java.lang.reflect.Method;

public class DuplicatedStepDefinitionException extends RuntimeException
{
    public DuplicatedStepDefinitionException(Method method, String pattern)
    {
        super(String.format("Duplicated step definition%n%nClass: %s%nMethod: %s%nPattern: \"%s\"%n", method.getDeclaringClass().getSimpleName(), method.getName(), pattern));
    }
}