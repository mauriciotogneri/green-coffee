package com.mauriciotogneri.greencoffee.exceptions;

import java.lang.reflect.Method;

public class InvalidMethodSignatureException extends RuntimeException
{
    public InvalidMethodSignatureException(Method method, String pattern, String text)
    {
        super(String.format("Invalid number of type of parameters%n%nClass: %s%nMethod: %s%nPattern: \"%s\"%nText matched: \"%s\"%n", method.getDeclaringClass().getSimpleName(), method.getName(), pattern, text));
    }
}