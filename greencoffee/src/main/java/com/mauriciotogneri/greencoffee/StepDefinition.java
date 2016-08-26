package com.mauriciotogneri.greencoffee;

import com.mauriciotogneri.greencoffee.exceptions.InvalidMethodSignatureException;
import com.mauriciotogneri.greencoffee.exceptions.StepFailureException;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gherkin.ast.DataTable;
import gherkin.ast.DocString;
import gherkin.ast.Node;

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

    public String pattern()
    {
        return pattern.pattern();
    }

    public Method method()
    {
        return method;
    }

    public boolean matches(String text)
    {
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }

    public void invoke(String text, Node argument)
    {
        Matcher matcher = pattern.matcher(text);

        if (matcher.find())
        {
            Object[] parameters;

            try
            {
                parameters = parameters(matcher, argument);
            }
            catch (Exception e)
            {
                throw new InvalidMethodSignatureException(method, pattern.pattern(), text);
            }

            try
            {
                method.invoke(target, parameters);
            }
            catch (Exception e)
            {
                Throwable cause = e.getCause();

                throw new StepFailureException(method, pattern.pattern(), text, (cause != null) ? cause.toString() : e.toString());
            }
        }
        else
        {
            throw new RuntimeException();
        }
    }

    private Object[] parameters(Matcher matcher, Node argument)
    {
        Object[] parameters = new Object[matcher.groupCount() + ((argument != null) ? 1 : 0)];
        Class<?>[] types = method.getParameterTypes();

        int limit = (argument != null) ? (parameters.length - 1) : parameters.length;

        for (int i = 0; i < limit; i++)
        {
            parameters[i] = castParameter(matcher.group(i + 1), types[i]);
        }

        if (argument != null)
        {
            if (argument.getClass().equals(DocString.class))
            {
                DocString docString = (DocString) argument;

                parameters[parameters.length - 1] = docString.getContent();
            }
            else if (argument.getClass().equals(DataTable.class))
            {
                DataTable dataTable = (DataTable) argument;

                parameters[parameters.length - 1] = dataTable.getRows();
            }
        }

        return parameters;
    }

    private Object castParameter(String value, Class<?> clazz)
    {
        if (clazz.equals(int.class) || clazz.equals(Integer.class))
        {
            return Integer.parseInt(value);
        }
        else if (clazz.equals(long.class) || clazz.equals(Long.class))
        {
            return Long.parseLong(value);
        }
        else if (clazz.equals(float.class) || clazz.equals(Float.class))
        {
            return Float.parseFloat(value);
        }
        else if (clazz.equals(double.class) || clazz.equals(Double.class))
        {
            return Double.parseDouble(value);
        }
        else if (clazz.equals(byte.class) || clazz.equals(Byte.class))
        {
            return Byte.parseByte(value);
        }
        else if (clazz.equals(short.class) || clazz.equals(Short.class))
        {
            return Short.parseShort(value);
        }
        else if (clazz.equals(boolean.class) || clazz.equals(Boolean.class))
        {
            return Boolean.parseBoolean(value);
        }
        else if (clazz.equals(char.class) || clazz.equals(Character.class))
        {
            return value.charAt(0);
        }
        else
        {
            return value;
        }
    }
}