package com.mauriciotogneri.greencoffee.interactions;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class ActionableView extends ActionableObject
{
    private final ViewInteraction viewInteraction;

    public ActionableView(ViewInteraction viewInteraction)
    {
        this.viewInteraction = viewInteraction;
    }

    @Override
    public ActionableObject check(ViewAssertion viewAssertion)
    {
        return new ActionableView(viewInteraction.check(viewAssertion));
    }

    @Override
    public ActionableObject perform(ViewAction viewAction)
    {
        return new ActionableView(viewInteraction.perform(viewAction));
    }

    @Override
    public String text()
    {
        String[] stringHolder = {null};

        viewInteraction.perform(new ViewAction()
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