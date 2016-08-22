package com.mauriciotogneri.greencoffee;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StepDefinition
{
    private final Pattern pattern;
    private final Method method;
    private final Object target;

    public StepDefinition(String expression, Method method, Object target)
    {
        this.pattern = Pattern.compile(expression);
        this.method = method;
        this.target = target;
    }

    public boolean matches(String text)
    {
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }

    public void invoke(String text) throws IOException
    {
        Matcher matcher = pattern.matcher(text);

        if (matcher.find())
        {
            Object[] parameters = new Object[matcher.groupCount()];

            for (int i = 0; i < parameters.length; i++)
            {
                parameters[i] = matcher.group(i + 1);
            }

            try
            {
                method.invoke(target, parameters);
            }
            catch (Exception e)
            {
                throw new IOException(e);
            }
        }
        else
        {
            throw new RuntimeException();
        }
    }
}