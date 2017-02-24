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
    public void takeScreenshot(final String path)
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                Iterator<Activity> iterator = resumedActivities.iterator();

                if (iterator.hasNext())
                {
                    Activity activity = iterator.next();

                    try
                    {
                        takeScreenshot(activity, path);
                    }
                    catch (Exception e)
                    {
                        // ignore
                        System.out.println();
                    }
                }
            }
        });
    }

    private void takeScreenshot(Activity activity, String path) throws Exception
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        File imageFile = new File(path);
        File parentFolder = imageFile.getParentFile();

        if (parentFolder.exists() || parentFolder.mkdirs())
        {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        }
    }
}