package com.mauriciotogneri.greencoffee;

import java.io.File;

public interface ScreenshotProvider {

    static ScreenshotProvider getDefault() {
        return new DefaultScreenshotProvider();
    }

    void takeScreenshot(File file);

    class DefaultScreenshotProvider implements ScreenshotProvider {

        private final ScreenCapture screenCapture;

        DefaultScreenshotProvider() {
            screenCapture = new ScreenCapture();
        }

        @Override
        public void takeScreenshot(File file) {
            screenCapture.takeScreenshot(file);
        }
    }
}