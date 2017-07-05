package com.mauriciotogneri.greencoffee.interactions;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matchers;

public class ActionableView
{
    private final ViewInteraction viewInteraction;

    public ActionableView(ViewInteraction viewInteraction)
    {
        this.viewInteraction = viewInteraction;
    }

    public ActionableView click()
    {
        return perform(ViewActions.click());
    }

    public ActionableView doubleClick()
    {
        return perform(ViewActions.doubleClick());
    }

    public ActionableView longClick()
    {
        return perform(ViewActions.longClick());
    }

    public ActionableView type(String text)
    {
        return perform(ViewActions.typeText(text));
    }

    public ActionableView clearText()
    {
        return perform(ViewActions.clearText());
    }

    public ActionableView scrollTo()
    {
        return perform(ViewActions.scrollTo());
    }

    public ActionableView swipeUp()
    {
        return perform(ViewActions.swipeUp());
    }

    public ActionableView swipeDown()
    {
        return perform(ViewActions.swipeDown());
    }

    public ActionableView swipeLeft()
    {
        return perform(ViewActions.swipeLeft());
    }

    public ActionableView swipeRight()
    {
        return perform(ViewActions.swipeRight());
    }

    public ActionableView doesNotExist()
    {
        return check(ViewAssertions.doesNotExist());
    }

    public boolean checkIfDoesNotExist()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                doesNotExist();
            }
        });
    }

    public ActionableView contains(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString(text.toString()))));
    }

    public boolean checkIfContains(final Object text)
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                contains(text);
            }
        });
    }

    public ActionableView doesNotContain(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.withText(Matchers.not(Matchers.containsString(text.toString())))));
    }

    public boolean checkIfDoesNotContain(final Object text)
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                doesNotContain(text);
            }
        });
    }

    public ActionableView isEmpty()
    {
        return check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

    public boolean checkIfIsEmpty()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isEmpty();
            }
        });
    }

    public ActionableView isNotEmpty()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))));
    }

    public boolean checkIfIsNotEmpty()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isNotEmpty();
            }
        });
    }

    public ActionableView hasFocus()
    {
        return check(ViewAssertions.matches(ViewMatchers.hasFocus()));
    }

    public boolean checkIfHasFocus()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                hasFocus();
            }
        });
    }

    public ActionableView doesNotHaveFocus()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.hasFocus())));
    }

    public boolean checkIfDoesNotHaveFocus()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                doesNotHaveFocus();
            }
        });
    }

    public ActionableView hasErrorText(Object text)
    {
        return check(ViewAssertions.matches(ViewMatchers.hasErrorText(text.toString())));
    }

    public boolean checkIfHasErrorText(final Object text)
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                hasErrorText(text);
            }
        });
    }

    public ActionableView isChecked()
    {
        return check(ViewAssertions.matches(ViewMatchers.isChecked()));
    }

    public boolean checkIfIsChecked()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isChecked();
            }
        });
    }

    public ActionableView isNotChecked()
    {
        return check(ViewAssertions.matches(ViewMatchers.isNotChecked()));
    }

    public boolean checkIfIsNotChecked()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isNotChecked();
            }
        });
    }

    public ActionableView isClickable()
    {
        return check(ViewAssertions.matches(ViewMatchers.isClickable()));
    }

    public boolean checkIfIsClickable()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isClickable();
            }
        });
    }

    public ActionableView isFocusable()
    {
        return check(ViewAssertions.matches(ViewMatchers.isFocusable()));
    }

    public boolean checkIfIsFocusable()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isFocusable();
            }
        });
    }

    public ActionableView isNotFocusable()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isFocusable())));
    }

    public boolean checkIfIsNotFocusable()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isNotFocusable();
            }
        });
    }

    public ActionableView isEnabled()
    {
        return check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    public boolean checkIfIsEnabled()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isEnabled();
            }
        });
    }

    public ActionableView isDisabled()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));
    }

    public boolean checkIfIsDisabled()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isDisabled();
            }
        });
    }

    public ActionableView isSelected()
    {
        return check(ViewAssertions.matches(ViewMatchers.isSelected()));
    }

    public boolean checkIfIsSelected()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isSelected();
            }
        });
    }

    public ActionableView isNotSelected()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isSelected())));
    }

    public boolean checkIfIsNotSelected()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isNotSelected();
            }
        });
    }

    public ActionableView isDisplayed()
    {
        return check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public boolean checkIfIsDisplayed()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isDisplayed();
            }
        });
    }

    public ActionableView isCompletelyDisplayed()
    {
        return check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }

    public boolean checkIfIsCompletelyDisplayed()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isCompletelyDisplayed();
            }
        });
    }

    public ActionableView isNotDisplayed()
    {
        return check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));
    }

    public boolean checkIfIsNotDisplayed()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                isNotDisplayed();
            }
        });
    }

    public ActionableView hasDrawable()
    {
        return check(ViewAssertions.matches(hasDrawableImageView()));
    }

    public boolean checkIfHasDrawable()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                hasDrawable();
            }
        });
    }

    public ActionableView doesNotHaveDrawable()
    {
        return check(ViewAssertions.matches(Matchers.not(hasDrawableImageView())));
    }

    public boolean checkIfDoesNotHaveDrawable()
    {
        return checkIf(new Runnable()
        {
            @Override
            public void run()
            {
                doesNotHaveDrawable();
            }
        });
    }

    public ActionableView check(ViewAssertion viewAssertion)
    {
        return new ActionableView(viewInteraction.check(viewAssertion));
    }

    public ActionableView perform(ViewAction viewAction)
    {
        return new ActionableView(viewInteraction.perform(viewAction));
    }

    private boolean checkIf(Runnable runnable)
    {
        try
        {
            runnable.run();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public BoundedMatcher<View, ImageView> hasDrawableImageView()
    {
        return new BoundedMatcher<View, ImageView>(ImageView.class)
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText("has drawable");
            }

            @Override
            public boolean matchesSafely(ImageView imageView)
            {
                return (imageView.getDrawable() != null);
            }
        };
    }
}