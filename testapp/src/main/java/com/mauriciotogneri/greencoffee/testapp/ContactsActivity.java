package com.mauriciotogneri.greencoffee.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView listView = (ListView) findViewById(R.id.contacts_list);
        listView.setAdapter(new ContactAdapter(this, contacts()));
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Contact contact = (Contact) parent.getItemAtPosition(position);
                onContactSelected(contact);
            }
        });
    }

    private void onContactSelected(Contact contact)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    private List<Contact> contacts()
    {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John", "Smith", 42, 81.2, true));
        contacts.add(new Contact("Martha", "Phillips", 30, 65.7, false));
        contacts.add(new Contact("Freddie", "James", 55, 78.8, true));
        contacts.add(new Contact("Megan", "Woodard", 49, 73.0, true));
        contacts.add(new Contact("Jack", "Baxter", 28, 71.4, false));
        contacts.add(new Contact("Amelia", "Harrison", 22, 62.7, false));
        contacts.add(new Contact("Thomas", "Wolfe", 40, 77.2, true));
        contacts.add(new Contact("Rebecca", "Rees", 60, 74.5, true));
        contacts.add(new Contact("James", "Houghton", 31, 76.9, true));
        contacts.add(new Contact("Maddison", "Wallace", 25, 71.6, false));

        return contacts;
    }
}