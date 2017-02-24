package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;

@SuppressWarnings("unused")
public class ScreenshotSteps extends GreenCoffeeSteps
{
    @Then("^I take a screenshot named '(\\w+)'$")
    public void iSeeAnEmptyLoginForm(String name)
    {
        takeScreenshot(String.format("testapp/%s/%s", locale(), name));
    }
}