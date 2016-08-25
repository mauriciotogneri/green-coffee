package com.mauriciotogneri.greencoffee.testapp.test.matchers;

import com.mauriciotogneri.greencoffee.interactions.DataMatcher;
import com.mauriciotogneri.greencoffee.testapp.model.Contact;

public class ContactMatcher extends DataMatcher<Contact, String>
{
    public ContactMatcher(int resourceId)
    {
        super(resourceId);
    }

    @Override
    public Class<Contact> dataClass()
    {
        return Contact.class;
    }

    @Override
    public boolean matches(Contact contact, String content)
    {
        return contact.name().equals(content);
    }
}