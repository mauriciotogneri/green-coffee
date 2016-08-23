package com.mauriciotogneri.greencoffee.exceptions;

public class InvalidExampleException extends RuntimeException
{
    public InvalidExampleException(int line, int column)
    {
        super(String.format("Invalid example format at: [%s, %s]", line, column));
    }
}