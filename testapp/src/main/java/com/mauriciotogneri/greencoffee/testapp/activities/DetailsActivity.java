package com.mauriciotogneri.greencoffee.testapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mauriciotogneri.greencoffee.testapp.R;
import com.mauriciotogneri.greencoffee.testapp.database.ContactDatabase;
import com.mauriciotogneri.greencoffee.testapp.model.Contact;

public class DetailsActivity extends AppCompatActivity
{
    private static final String PARAMETER_CONTACT_ID = "parameter.contact.id";

    public static Intent create(Context context, String contactId)
    {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(PARAMETER_CONTACT_ID, contactId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        setTitle(R.string.details_title);

        String contactId = getIntent().getStringExtra(PARAMETER_CONTACT_ID);

        ContactDatabase contactDatabase = new ContactDatabase();
        Contact contact = contactDatabase.contact(contactId);

        TextView contactName = (TextView) findViewById(R.id.contact_detail_name);
        contactName.setText(contact.name());

        TextView contactAge = (TextView) findViewById(R.id.contact_detail_age);
        contactAge.setText(contact.age());

        TextView contactWeight = (TextView) findViewById(R.id.contact_detail_weight);
        contactWeight.setText(contact.weight());

        TextView contactMarried = (TextView) findViewById(R.id.contact_detail_married);
        contactMarried.setText(contact.married(this));
    }
}