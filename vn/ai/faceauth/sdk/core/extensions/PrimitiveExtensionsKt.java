package vn.ai.faceauth.sdk.core.extensions;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.text.StringsKt;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\n\u0010\t\u001a\u00020\u0004*\u00020\u0004\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u000b\u001a\n\u0010\f\u001a\u00020\u000b*\u00020\u000b\u001a\n\u0010\r\u001a\u00020\u0004*\u00020\u0004\u001a\n\u0010\u000e\u001a\u00020\u000f*\u00020\u0010\u001a\u0011\u0010\u0011\u001a\u00020\u0012*\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a\n\u0010\u0014\u001a\u00020\u0004*\u00020\u000b\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u000b*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"ANIMATION_FAST_MILLIS", "", "ANIMATION_SLOW_MILLIS", "DELIMITER_FILE", "", "DELIMITER_PATH", "FILE_NAME_INDEX", "", "FLAGS_FULLSCREEN", "encoderFilePath", "flipHorizontally", "Landroid/graphics/Bitmap;", "flipVertically", "getFileNameWithoutExtension", "makeStatusBarTransparent", "", "Landroid/app/Activity;", "orFalse", "", "(Ljava/lang/Boolean;)Z", "toBase64", "toBitmap", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PrimitiveExtensionsKt {
    public static long ANIMATION_FAST_MILLIS;
    public static long ANIMATION_SLOW_MILLIS;
    private static String DELIMITER_FILE;
    private static String DELIMITER_PATH;
    private static int FILE_NAME_INDEX;
    public static int FLAGS_FULLSCREEN;

    static {
        tfwhgw.rnl(PrimitiveExtensionsKt.class, 489, 494);
    }

    public static final String encoderFilePath(String str) {
        return !new File(str).exists() ? "" : Base64.encodeToString(FilesKt.readBytes(new File(str)), 2);
    }

    public static final Bitmap flipHorizontally(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static final Bitmap flipVertically(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static final String getFileNameWithoutExtension(String str) {
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{tfwhgw.rnigpa(4), tfwhgw.rnigpa(5)}, false, 0, 6, (Object) null);
        return (String) split$default.get(split$default.size() - 2);
    }

    public static final void makeStatusBarTransparent(Activity activity) {
        View decorView;
        int i;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            window.addFlags(Integer.MIN_VALUE);
            if (i2 >= 23) {
                decorView = window.getDecorView();
                i = 4866;
            } else {
                decorView = window.getDecorView();
                i = 1024;
            }
            decorView.setSystemUiVisibility(i);
            window.setStatusBarColor(0);
        }
    }

    public static final boolean orFalse(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final String toBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static final Bitmap toBitmap(String str) {
        if (new File(str).exists()) {
            return BitmapFactory.decodeFile(str);
        }
        return null;
    }
}
