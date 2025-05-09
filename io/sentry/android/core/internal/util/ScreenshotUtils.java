package io.sentry.android.core.internal.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.util.thread.IMainThreadChecker;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ScreenshotUtils {
    private static final long CAPTURE_TIMEOUT_MS = 1000;

    public static byte[] takeScreenshot(Activity activity, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        return takeScreenshot(activity, AndroidMainThreadChecker.getInstance(), iLogger, buildInfoProvider);
    }

    public static byte[] takeScreenshot(Activity activity, IMainThreadChecker iMainThreadChecker, final ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        if (!isActivityValid(activity, buildInfoProvider) || activity.getWindow() == null || activity.getWindow().getDecorView() == null || activity.getWindow().getDecorView().getRootView() == null) {
            iLogger.log(SentryLevel.DEBUG, "Activity isn't valid, not taking screenshot.", new Object[0]);
            return null;
        }
        final View rootView = activity.getWindow().getDecorView().getRootView();
        if (rootView.getWidth() <= 0 || rootView.getHeight() <= 0) {
            iLogger.log(SentryLevel.DEBUG, "View's width and height is zeroed, not taking screenshot.", new Object[0]);
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                Bitmap createBitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.ARGB_8888);
                final Canvas canvas = new Canvas(createBitmap);
                if (iMainThreadChecker.isMainThread()) {
                    rootView.draw(canvas);
                } else {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    activity.runOnUiThread(new Runnable() { // from class: io.sentry.android.core.internal.util.ScreenshotUtils$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ScreenshotUtils.lambda$takeScreenshot$0(rootView, canvas, countDownLatch, iLogger);
                        }
                    });
                    if (!countDownLatch.await(1000L, TimeUnit.MILLISECONDS)) {
                        byteArrayOutputStream.close();
                        return null;
                    }
                }
                createBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                if (byteArrayOutputStream.size() <= 0) {
                    iLogger.log(SentryLevel.DEBUG, "Screenshot is 0 bytes, not attaching the image.", new Object[0]);
                    byteArrayOutputStream.close();
                    return null;
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Taking screenshot failed.", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$takeScreenshot$0(View view, Canvas canvas, CountDownLatch countDownLatch, ILogger iLogger) {
        try {
            view.draw(canvas);
            countDownLatch.countDown();
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Taking screenshot failed (view.draw).", th);
        }
    }

    private static boolean isActivityValid(Activity activity, BuildInfoProvider buildInfoProvider) {
        if (buildInfoProvider.getSdkInfoVersion() >= 17) {
            return (activity.isFinishing() || activity.isDestroyed()) ? false : true;
        }
        return !activity.isFinishing();
    }
}
