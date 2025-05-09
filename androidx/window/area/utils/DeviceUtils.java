package androidx.window.area.utils;

import android.util.DisplayMetrics;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.sentry.protocol.Device;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceUtils.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/area/utils/DeviceUtils;", "", "()V", "deviceList", "", "Landroidx/window/area/utils/DeviceMetrics;", "getRearDisplayMetrics", "Landroid/util/DisplayMetrics;", Device.JsonKeys.MANUFACTURER, "", "model", "getRearDisplayMetrics$window_release", "hasDeviceMetrics", "", "hasDeviceMetrics$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceUtils {
    public static final DeviceUtils INSTANCE = new DeviceUtils();
    private static final List<DeviceMetrics> deviceList;

    private DeviceUtils() {
    }

    static {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics.widthPixels = 1080;
        displayMetrics.heightPixels = 2092;
        displayMetrics.density = 2.625f;
        displayMetrics.densityDpi = TypedValues.CycleType.TYPE_EASING;
        Unit unit = Unit.INSTANCE;
        deviceList = CollectionsKt.listOf(new DeviceMetrics("google", "pixel fold", displayMetrics));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0064 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[LOOP:0: B:9:0x0023->B:20:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean hasDeviceMetrics$window_release(String manufacturer, String model) {
        boolean z;
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(model, "model");
        List<DeviceMetrics> list = deviceList;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (DeviceMetrics deviceMetrics : list) {
                String manufacturer2 = deviceMetrics.getManufacturer();
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase = manufacturer.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (Intrinsics.areEqual(manufacturer2, lowerCase)) {
                    String model2 = deviceMetrics.getModel();
                    Locale US2 = Locale.US;
                    Intrinsics.checkNotNullExpressionValue(US2, "US");
                    String lowerCase2 = model.toLowerCase(US2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    if (Intrinsics.areEqual(model2, lowerCase2)) {
                        z = true;
                        if (!z) {
                            return true;
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0057 A[EDGE_INSN: B:11:0x0057->B:12:0x0057 BREAK  A[LOOP:0: B:2:0x0012->B:18:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[LOOP:0: B:2:0x0012->B:18:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final DisplayMetrics getRearDisplayMetrics$window_release(String manufacturer, String model) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(model, "model");
        Iterator<T> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            DeviceMetrics deviceMetrics = (DeviceMetrics) obj;
            String manufacturer2 = deviceMetrics.getManufacturer();
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = manufacturer.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (Intrinsics.areEqual(manufacturer2, lowerCase)) {
                String model2 = deviceMetrics.getModel();
                Locale US2 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US2, "US");
                String lowerCase2 = model.toLowerCase(US2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                if (Intrinsics.areEqual(model2, lowerCase2)) {
                    z = true;
                    if (!z) {
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
            }
        }
        DeviceMetrics deviceMetrics2 = (DeviceMetrics) obj;
        if (deviceMetrics2 != null) {
            return deviceMetrics2.getRearDisplayMetrics();
        }
        return null;
    }
}
