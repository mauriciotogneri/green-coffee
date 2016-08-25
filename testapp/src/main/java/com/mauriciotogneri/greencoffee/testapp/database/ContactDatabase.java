package com.mauriciotogneri.greencoffee.testapp.database;

import com.mauriciotogneri.greencoffee.testapp.model.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactDatabase
{
    private static final Contact CONTACT_1 = new Contact("1", "John", "Smith", 42, 81.2, true);
    private static final Contact CONTACT_2 = new Contact("2", "Martha", "Phillips", 30, 65.7, false);
    private static final Contact CONTACT_3 = new Contact("3", "Freddie", "James", 55, 78.8, true);
    private static final Contact CONTACT_4 = new Contact("4", "Megan", "Woodard", 49, 73.0, true);
    private static final Contact CONTACT_5 = new Contact("5", "Jack", "Baxter", 28, 71.4, false);
    private static final Contact CONTACT_6 = new Contact("6", "Amelia", "Harrison", 22, 62.7, false);
    private static final Contact CONTACT_7 = new Contact("7", "Thomas", "Wolfe", 40, 77.2, true);
    private static final Contact CONTACT_8 = new Contact("8", "Rebecca", "Rees", 60, 74.5, true);
    private static final Contact CONTACT_9 = new Contact("9", "James", "Houghton", 31, 76.9, true);
    private static final Contact CONTACT_10 = new Contact("10", "Maddison", "Wallace", 25, 71.6, false);

    private static final List<Contact> CONTACTS = new ArrayList<>(Arrays.asList(
            CONTACT_1,
            CONTACT_2,
            CONTACT_3,
            CONTACT_4,
            CONTACT_5,
            CONTACT_6,
            CONTACT_7,
            CONTACT_8,
            CONTACT_9,
            CONTACT_10
    ));

    public List<Contact> contacts(String username)
    {
        if (UserDatabase.USER_1.hasUsername(username))
        {
            return new ArrayList<>(Arrays.asList(CONTACT_1, CONTACT_3, CONTACT_5, CONTACT_7, CONTACT_9));
        }
        else if (UserDatabase.USER_2.hasUsername(username))
        {
            return new ArrayList<>(Arrays.asList(CONTACT_2, CONTACT_4, CONTACT_6, CONTACT_8, CONTACT_10));
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public Contact contact(String contactId)
    {
        for (Contact contact : CONTACTS)
        {
            if (contact.hasId(contactId))
            {
                return contact;
            }
        }

        throw new RuntimeException();
    }
}