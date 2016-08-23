package com.mauriciotogneri.greencoffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button_doLogin).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText username = (EditText) findViewById(R.id.login_input_username);
                EditText password = (EditText) findViewById(R.id.login_input_password);

                login(username.getText().toString(), password.getText().toString());
            }
        });

        EditText username = (EditText) findViewById(R.id.login_input_username);
        username.setText("admin");

        EditText password = (EditText) findViewById(R.id.login_input_password);
        password.setText("1234");
    }

    private void login(String username, String password)
    {
        if (validForm(username, password))
        {
            if (validCredentials(username, password))
            {
                Intent intent = new Intent(this, ContactsActivity.class);
                startActivity(intent);

                finish();
            }
            else
            {
                errorDialog(R.string.login_credentials_error);
            }
        }
    }

    private boolean validForm(String username, String password)
    {
        if (TextUtils.isEmpty(username))
        {
            errorDialog(R.string.login_username_error);

            return false;
        }
        else if (TextUtils.isEmpty(password))
        {
            errorDialog(R.string.login_password_error);

            return false;
        }

        return true;
    }

    private boolean validCredentials(String username, String password)
    {
        return TextUtils.equals(username, "admin") && TextUtils.equals(password, "1234");
    }

    private void errorDialog(int message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(message);
        builder.setPositiveButton(R.string.dialog_button_ok, null);
        builder.show();
    }
}