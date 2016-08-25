package com.mauriciotogneri.greencoffee.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mauriciotogneri.greencoffee.testapp.R;
import com.mauriciotogneri.greencoffee.testapp.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact>
{
    private final LayoutInflater layoutInflater;

    public ContactAdapter(Context context, List<Contact> contacts)
    {
        super(context, R.layout.row_contact, contacts);

        this.layoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            view = layoutInflater.inflate(R.layout.row_contact, null, false);
        }

        Contact contact = getItem(position);

        if (contact != null)
        {
            TextView firstName = (TextView) view.findViewById(R.id.contact_name);
            firstName.setText(contact.name());

            TextView age = (TextView) view.findViewById(R.id.contact_age);
            age.setText(contact.age());

            TextView weight = (TextView) view.findViewById(R.id.contact_weight);
            weight.setText(contact.weight());

            TextView married = (TextView) view.findViewById(R.id.contact_married);
            married.setText(contact.married(getContext()));
        }

        return view;
    }
}