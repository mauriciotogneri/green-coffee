package com.mauriciotogneri.greencoffee.testapp.test;

import com.mauriciotogneri.greencoffee.testapp.test.features.ContactListFeatureTest;
import com.mauriciotogneri.greencoffee.testapp.test.features.DetailsFeatureTest;
import com.mauriciotogneri.greencoffee.testapp.test.features.LoginFeatureTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginFeatureTest.class,
        ContactListFeatureTest.class,
        DetailsFeatureTest.class
})
public class TestSuite
{
}