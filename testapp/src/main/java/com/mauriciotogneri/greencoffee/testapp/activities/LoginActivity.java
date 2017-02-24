package com.mauriciotogneri.greencoffee.testapp.activities;

import android.Manifest;
import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.mauriciotogneri.greencoffee.testapp.R;
import com.mauriciotogneri.greencoffee.testapp.database.UserDatabase;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setTitle(R.string.login_title);

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    private void login(String username, String password)
    {
        if (validForm(username, password))
        {
            if (validCredentials(username, password))
            {
                startActivity(ContactListActivity.create(this, username));
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
        UserDatabase userDatabase = new UserDatabase();

        return userDatabase.isValid(username, password);
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