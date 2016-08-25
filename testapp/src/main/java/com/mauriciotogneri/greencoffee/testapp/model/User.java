package com.mauriciotogneri.greencoffee.testapp.model;

public class User
{
    private final String username;
    private final String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String username()
    {
        return username;
    }

    public String password()
    {
        return password;
    }

    public boolean hasUsername(String username)
    {
        return this.username.equals(username);
    }

    public boolean matches(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }
}