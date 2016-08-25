package com.mauriciotogneri.greencoffee;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

class ScreenCapture
{
    private final String basePath;

    public ScreenCapture(String basePath)
    {
        this.basePath = basePath;
    }

    public void takeScreenshot()
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
                        takeScreenshot(activity);
                    }
                    catch (Exception e)
                    {
                        // ignore
                    }
                }
            }
        });
    }

    private void takeScreenshot(Activity activity) throws Exception
    {
        View view = activity.getWindow().getDecorView().getRootView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        String fileName = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.getDefault()).format(new Date());
        String path = String.format("%s/%s/%s.jpg", Environment.getExternalStorageDirectory().toString(), basePath, fileName);

        File imageFile = new File(path);
        FileOutputStream outputStream = new FileOutputStream(imageFile);
        bitmap.compress(CompressFormat.PNG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
    }
}