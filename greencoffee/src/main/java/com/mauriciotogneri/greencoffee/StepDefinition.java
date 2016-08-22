package com.mauriciotogneri.greencoffee;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepDefinition
{
    private final String keyword;
    private final Pattern pattern;
    private final Method method;
    private final Object target;

    public StepDefinition(String keyword, String expression, Method method, Object target)
    {
        this.keyword = keyword;
        this.pattern = Pattern.compile(expression);
        this.method = method;
        this.target = target;
    }

    public boolean matches(String keyword, String text)
    {
        Matcher matcher = pattern.matcher(text);

        return this.keyword.equals(keyword) && matcher.find();
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
    }
}