package com.mauriciotogneri.greencoffee;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;

import com.mauriciotogneri.greencoffee.exceptions.DuplicatedStepDefinitionException;
import com.mauriciotogneri.greencoffee.exceptions.StepDefinitionNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gherkin.ast.Step;

// TODO: generate reports
public class GreenCoffeeTest
{
    private final Scenario scenario;

    public GreenCoffeeTest(Scenario scenario)
    {
        this.scenario = scenario;
    }

    private void getActivity()
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
            }
        });
    }

    private void takeScreenshot(Activity activity) throws Exception
    {
        View view = activity.getWindow().getDecorView().getRootView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        Date now = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
        File imageFile = new File(mPath);

        FileOutputStream outputStream = new FileOutputStream(imageFile);
        int quality = 100;
        bitmap.compress(CompressFormat.PNG, quality, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    protected void start(GreenCoffeeSteps firstTarget, GreenCoffeeSteps... restTargets)
    {
        log(String.format("Scenario: %s", scenario.name()));

        if (!TextUtils.isEmpty(scenario.description()))
        {
            logDescription("\t", scenario.description());
        }

        List<StepDefinition> stepDefinitions = firstTarget.stepDefinitions();

        for (GreenCoffeeSteps greenCoffeeSteps : restTargets)
        {
            stepDefinitions.addAll(greenCoffeeSteps.stepDefinitions());
        }

        validateStepDefinitions(stepDefinitions);

        for (Step step : scenario.steps())
        {
            processStep(step, stepDefinitions);
        }
    }

    private void validateStepDefinitions(List<StepDefinition> stepDefinitions)
    {
        Set<String> patterns = new HashSet<>();

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            String pattern = stepDefinition.pattern();

            if (!patterns.contains(pattern))
            {
                patterns.add(pattern);
            }
            else
            {
                throw new DuplicatedStepDefinitionException(stepDefinition.method(), pattern);
            }
        }
    }

    private void processStep(Step step, List<StepDefinition> stepDefinitions)
    {
        String keyword = step.getKeyword().trim();
        String text = step.getText().trim();

        log(String.format("\t%s %s", keyword, text));

        for (StepDefinition stepDefinition : stepDefinitions)
        {
            if (stepDefinition.matches(text))
            {
                stepDefinition.invoke(text);
                return;
            }
        }

        throw new StepDefinitionNotFoundException(keyword, text);
    }

    private void logDescription(String tab, String description)
    {
        for (String line : description.split("\n"))
        {
            log(String.format("%s%s", tab, line.trim()));
        }
    }

    private void log(String message)
    {
        System.out.println(message);
        System.out.flush();
    }
}