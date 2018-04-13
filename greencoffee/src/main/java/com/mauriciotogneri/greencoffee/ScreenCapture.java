package com.mauriciotogneri.greencoffee;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Iterator;

class ScreenCapture
{
    void takeScreenshot(File file)
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() ->
        {
            Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
            Iterator<Activity> iterator = resumedActivities.iterator();

            if (iterator.hasNext())
            {
                Activity activity = iterator.next();

                try
                {
                    takeScreenshot(activity, file);
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        });
    }

    private void takeScreenshot(Activity activity, File file) throws Exception
    {
        View view = activity.getWindow().getDecorView().getRootView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        File parentFolder = file.getParentFile();

        if (parentFolder.exists() || parentFolder.mkdirs())
        {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        }
    }
}