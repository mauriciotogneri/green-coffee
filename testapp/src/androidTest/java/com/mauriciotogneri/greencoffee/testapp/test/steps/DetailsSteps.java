package com.mauriciotogneri.greencoffee.testapp.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.testapp.R;

@SuppressWarnings("unused")
public class DetailsSteps extends GreenCoffeeSteps
{
    @Then("^I see the detail screen for '([\\w| ]+)'$")
    public void iSeeTheDetailScreenFor$(String username)
    {
        onViewWithText(username).isDisplayed();
    }

    @Then("^I see his name is '([\\w| ]+)'$")
    public void iSeeHisHerNameIs(String name)
    {
        onViewWithId(R.id.contact_detail_name).contains(name);
    }

    @Then("^I see his age is (\\d+)$")
    public void iSeeHisHerAgeIs(int age)
    {
        onViewWithId(R.id.contact_detail_age).contains(age);
    }

    @Then("^I see his weight is (\\d+\\.?\\d+ kg.)$")
    public void iSeeHisHerWeightIs(String weight)
    {
        onViewWithId(R.id.contact_detail_weight).contains(weight);
    }

    @Then("^I see he is (single|married)$")
    public void iSeeHeIsSingleMarried(String status)
    {
        onViewWithId(R.id.contact_detail_married).contains(status);
    }
}