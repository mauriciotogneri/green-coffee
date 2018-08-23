package com.mauriciotogneri.greencoffee;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.support.test.InstrumentationRegistry.getTargetContext;

public interface ScreenshotProvider
{
    static ScreenshotProvider getDefault()
    {
        return new DefaultScreenshotProvider();
    }

    void takeScreenshot(Scenario scenario);

    class DefaultScreenshotProvider implements ScreenshotProvider
    {
        private final ScreenCapture screenCapture;

        DefaultScreenshotProvider()
        {
            screenCapture = new ScreenCapture();
        }

        @Override
        public void takeScreenshot(Scenario scenario)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String fileName = String.format("%s - %s.jpg", scenario.name(), dateFormat.format(new Date()));
            File file = new File(getTargetContext().getExternalFilesDir("screenshots"), fileName);

            screenCapture.takeScreenshot(file);
        }
    }
}