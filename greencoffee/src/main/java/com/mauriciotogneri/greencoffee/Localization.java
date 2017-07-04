package com.mauriciotogneri.greencoffee;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class Localization
{
    private final Context context;

    public Localization(Context context)
    {
        this.context = context;
    }

    public Locale locale()
    {
        return getSystemLocale(configuration(resources()));
    }

    public boolean locale(Locale newLocale)
    {
        Resources resources = resources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration config = configuration(resources);

        Locale systemLocale = getSystemLocale(config);

        if (!systemLocale.equals(newLocale))
        {
            updateSystemLocale(config, newLocale);
            updateSystemConfiguration(resources, config, displayMetrics);

            return true;
        }
        else
        {
            return false;
        }
    }

    public void reset()
    {
        locale(Locale.getDefault());
    }

    // =============================================================================================

    private Resources resources()
    {
        return context.getResources();
    }

    private Configuration configuration(Resources resources)
    {
        return resources.getConfiguration();
    }

    // =============================================================================================

    private Locale getSystemLocale(Configuration config)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            return systemLocale(config);
        }
        else
        {
            return systemLocaleLegacy(config);
        }
    }

    @SuppressWarnings("deprecation")
    private Locale systemLocaleLegacy(Configuration config)
    {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Locale systemLocale(Configuration config)
    {
        return config.getLocales().get(0);
    }

    // =============================================================================================

    private void updateSystemLocale(Configuration config, Locale locale)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            systemLocale(config, locale);
        }
        else
        {
            systemLocaleLegacy(config, locale);
        }
    }

    @SuppressWarnings("deprecation")
    private void systemLocaleLegacy(Configuration config, Locale locale)
    {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void systemLocale(Configuration config, Locale locale)
    {
        config.setLocale(locale);
    }

    // =============================================================================================

    @SuppressWarnings("deprecation")
    private void updateSystemConfiguration(Resources resources, Configuration config, DisplayMetrics displayMetrics)
    {
        resources.updateConfiguration(config, displayMetrics);
    }
}