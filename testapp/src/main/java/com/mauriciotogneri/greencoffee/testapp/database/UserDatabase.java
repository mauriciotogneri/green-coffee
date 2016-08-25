package com.mauriciotogneri.greencoffee.testapp.database;

import com.mauriciotogneri.greencoffee.testapp.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDatabase
{
    public static final User USER_1 = new User("admin", "1234");
    public static final User USER_2 = new User("guest", "qwerty");
    public static final User USER_3 = new User("root", "11111");

    private static final List<User> USERS = new ArrayList<>(Arrays.asList(
            USER_1,
            USER_2,
            USER_3
    ));

    public boolean isValid(String username, String password)
    {
        for (User user : USERS)
        {
            if (user.matches(username, password))
            {
                return true;
            }
        }

        return false;
    }
}