package com.mauriciotogneri.greencoffee.interactions;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class ActionableData extends ActionableObject
{
    private final DataInteraction dataInteraction;

    public ActionableData(DataInteraction dataInteraction)
    {
        this.dataInteraction = dataInteraction;
    }

    @Override
    public ActionableObject check(ViewAssertion viewAssertion)
    {
        return new ActionableView(dataInteraction.check(viewAssertion));
    }

    @Override
    public ActionableObject perform(ViewAction viewAction)
    {
        return new ActionableView(dataInteraction.perform(viewAction));
    }

    @Override
    public String text()
    {
        String[] stringHolder = {null};

        dataInteraction.perform(new ViewAction()
        {
            @Override
            public Matcher<View> getConstraints()
            {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription()
            {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view)
            {
                TextView textView = (TextView) view;
                stringHolder[0] = textView.getText().toString();
            }
        });

        return stringHolder[0];
    }
}