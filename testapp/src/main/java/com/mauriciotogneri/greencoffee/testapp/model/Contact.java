package com.mauriciotogneri.greencoffee.testapp.model;

import android.content.Context;

import com.mauriciotogneri.greencoffee.testapp.R;

public class Contact
{
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final Double weight;
    private final Boolean married;

    public Contact(String firstName, String lastName, Integer age, Double weight, Boolean married)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.married = married;
    }

    public String name()
    {
        return String.format("%s %s", firstName, lastName);
    }

    public String age()
    {
        return age.toString();
    }

    public String weight()
    {
        return String.format("%s kg.", weight);
    }

    public String married(Context context)
    {
        return married ? context.getString(R.string.contacts_married_yes) : context.getString(R.string.contacts_married_no);
    }
}