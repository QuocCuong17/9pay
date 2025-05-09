package co.hyperverge.hypersnapsdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import co.hyperverge.hypersnapsdk.R;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public class UIUtils {
    private static final String TAG = "co.hyperverge.hypersnapsdk.utils.UIUtils";
    public static final int TOP_MARGIN = 90;

    public static boolean hasAlpha(int i) {
        return (i >>> 24) != 255;
    }

    public static boolean isSmallScreenDevice(DisplayMetrics displayMetrics) {
        return ((float) displayMetrics.heightPixels) / displayMetrics.density < 640.0f;
    }

    public static int dpToPx(Context context, float f) {
        return (int) (f * (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    public static float pxToEm(DisplayMetrics displayMetrics, float f) {
        return f > 0.0f ? f / displayMetrics.densityDpi : f;
    }

    public static int getTopMargin(Context context) {
        return (int) context.getResources().getDimension(R.dimen.margin_face_circle_top);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getPercentageMargin(Context context) {
        return (int) (Math.min(getScreenWidth(), getScreenHeight()) * 0.2f);
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static Point getAppUsableScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static Point getRealScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                point.x = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                point.y = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
            }
        }
        return point;
    }

    public static boolean hasNavBar(Context context) {
        return getAppUsableScreenSize(context).y < getRealScreenSize(context).y;
    }

    public static Bitmap getRoundedCornerBitmap(Context context, Bitmap bitmap, double d, float f, int i, boolean z) {
        if (f >= 1.0f || !z) {
            d = 0.0d;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            int color = context.getResources().getColor(R.color.hv_white);
            Paint paint = new Paint();
            int i2 = (int) d;
            Rect rect = new Rect(0, i2, bitmap.getWidth(), bitmap.getHeight() - i2);
            RectF rectF = new RectF(rect);
            float f2 = i;
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, f2, f2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        } catch (Exception | OutOfMemoryError e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
            return null;
        }
    }

    public static Bitmap getCircularCroppedBitmap(Bitmap bitmap, Integer num) {
        Bitmap createBitmap;
        int width;
        try {
            if (bitmap.getWidth() > bitmap.getHeight()) {
                createBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            } else {
                createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            if (bitmap.getWidth() > bitmap.getHeight()) {
                width = bitmap.getHeight() / 2;
            } else {
                width = bitmap.getWidth() / 2;
            }
            float f = width;
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawCircle(f, f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            return null;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        switch (i) {
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public static int getScreenBrightness(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "screen_brightness", 0);
    }

    public static void setScreenBrightness(Activity activity, int i) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = 0.8f;
        activity.getWindow().setAttributes(attributes);
    }

    public static String toARGB(String str) {
        if (StringUtils.isEmptyOrNull(str)) {
            return null;
        }
        if (str.length() != 9) {
            return str;
        }
        int length = str.length();
        for (int i = 0; i < 2; i++) {
            int i2 = length - 1;
            str = str.charAt(i2) + str.substring(0, i2);
        }
        return "#" + str.replace("#", "");
    }

    public static RotateAnimation getInfiniteRotationAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        return rotateAnimation;
    }

    public static int alphaOf(int i) {
        return (int) ((hasAlpha(i) ? ((i >>> 24) & 255) / 255.0f : 1.0f) * 100.0f);
    }
}
