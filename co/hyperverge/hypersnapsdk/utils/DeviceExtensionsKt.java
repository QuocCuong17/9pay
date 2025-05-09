package co.hyperverge.hypersnapsdk.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.LocaleList;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import com.google.firebase.firestore.BuildConfig;
import io.sentry.protocol.DebugImage;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* compiled from: DeviceExtensions.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0016\u0010\t\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00060\u00060\nH\u0000\u001a\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0000\u001a\u001e\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0000\u001a\n\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0000\u001a\u001e\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0000\u001a\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\b\u0010\u0015\u001a\u00020\u0016H\u0001\u001a\b\u0010\u0017\u001a\u00020\u0016H\u0001\u001a\u000f\u0010\u0018\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0002\u0010\u0019\u001a\u000e\u0010\u001a\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0000\u001a\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u0002H\u0000¢\u0006\u0002\u0010\u001d\u001a\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001c*\u00020\u0002H\u0000¢\u0006\u0002\u0010\u001d\u001a\u000e\u0010\u001f\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0000\u001a\u0013\u0010 \u001a\u0004\u0018\u00010!*\u00020\u0002H\u0000¢\u0006\u0002\u0010\"\u001a\u000e\u0010#\u001a\u0004\u0018\u00010$*\u00020\u0002H\u0000\u001a\u000e\u0010%\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0000\u001a\u000e\u0010&\u001a\u0004\u0018\u00010\u0006*\u00020\u0002H\u0000\u001a\u000e\u0010'\u001a\u0004\u0018\u00010(*\u00020\u0002H\u0000\u001a\u0013\u0010)\u001a\u0004\u0018\u00010!*\u00020\u0002H\u0000¢\u0006\u0002\u0010\"\u001a\u0013\u0010*\u001a\u0004\u0018\u00010\u0016*\u00020\u0002H\u0000¢\u0006\u0002\u0010+\u001a\f\u0010,\u001a\u00020\u0016*\u00020\u0002H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006-"}, d2 = {"packageInfo", "Landroid/content/pm/PackageInfo;", "Landroid/content/Context;", "getPackageInfo", "(Landroid/content/Context;)Landroid/content/pm/PackageInfo;", "versionCodeString", "", "getVersionCodeString", "(Landroid/content/pm/PackageInfo;)Ljava/lang/String;", "getArchitectures", "", "kotlin.jvm.PlatformType", "getBootTime", "Ljava/util/Date;", "getDeviceProperties", "", "", "context", "getKernelVersion", "getOsProperties", "getVersionCodeDep", "isAPI21OrAbove", "", "isAPI23OrAbove", "isEmulator", "()Ljava/lang/Boolean;", "getAppName", "getBatteryPercentage", "", "(Landroid/content/Context;)Ljava/lang/Float;", "getBatteryTemperature", "getDeviceId", "getFreeStorage", "", "(Landroid/content/Context;)Ljava/lang/Long;", "getMemoryInfo", "Landroid/app/ActivityManager$MemoryInfo;", "getNetworkType", "getOrientation", "getTimeZone", "Ljava/util/TimeZone;", "getTotalStorage", "isCharging", "(Landroid/content/Context;)Ljava/lang/Boolean;", "isNetworkAvailable", "hypersnapsdk_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceExtensionsKt {
    public static final boolean isAPI21OrAbove() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static final boolean isAPI23OrAbove() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Map<String, Object> getDeviceProperties(Context context) {
        List split$default;
        List emptyList;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        HashMap hashMap = new HashMap();
        hashMap.put("name", Settings.Global.getString(context.getContentResolver(), "device_name"));
        String str2 = Build.MODEL;
        hashMap.put("model", str2);
        if (str2 != null && (split$default = StringsKt.split$default((CharSequence) str2, new String[]{" "}, false, 0, 6, (Object) null)) != null) {
            if (!split$default.isEmpty()) {
                ListIterator listIterator = split$default.listIterator(split$default.size());
                while (listIterator.hasPrevious()) {
                    if (!(((String) listIterator.previous()).length() == 0)) {
                        emptyList = CollectionsKt.take(split$default, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            if (emptyList != null) {
                Object[] array = emptyList.toArray(new String[0]);
                Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
                String[] strArr = (String[]) array;
                if (strArr != null) {
                    str = strArr[0];
                    hashMap.put(Device.JsonKeys.FAMILY, str);
                    List<String> architectures = getArchitectures();
                    hashMap.put(Device.JsonKeys.ARCHS, architectures);
                    hashMap.put(DebugImage.JsonKeys.ARCH, CollectionsKt.getOrNull(architectures, 0));
                    hashMap.put(Device.JsonKeys.MANUFACTURER, Build.MANUFACTURER);
                    hashMap.put(Device.JsonKeys.BRAND, Build.BRAND);
                    hashMap.put("modelId", Build.ID);
                    hashMap.put(Device.JsonKeys.SIMULATOR, isEmulator());
                    HashMap hashMap2 = new HashMap();
                    TimeZone timeZone = getTimeZone(context);
                    hashMap2.put("timezone", timeZone != null ? null : timeZone.getID());
                    hashMap2.put(Device.JsonKeys.LANGUAGE, Locale.getDefault().getDisplayName());
                    hashMap2.put(Device.JsonKeys.ORIENTATION, getOrientation(context));
                    hashMap2.put("bootTime", getBootTime());
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("level", getBatteryPercentage(context));
                    hashMap3.put(Device.JsonKeys.CHARGING, isCharging(context));
                    hashMap3.put("temperature", getBatteryTemperature(context));
                    HashMap hashMap4 = new HashMap();
                    ActivityManager.MemoryInfo memoryInfo = getMemoryInfo(context);
                    hashMap4.put("lowMemory", Boolean.valueOf(memoryInfo == null && memoryInfo.lowMemory));
                    hashMap4.put("memorySizeBytes", memoryInfo != null ? null : Long.valueOf(memoryInfo.totalMem));
                    hashMap4.put("freeMemoryBytes", memoryInfo != null ? Long.valueOf(memoryInfo.availMem) : null);
                    hashMap4.put("storageSizeBytes", getTotalStorage(context));
                    hashMap4.put("freeStorageBytes", getFreeStorage(context));
                    HashMap hashMap5 = new HashMap();
                    hashMap5.put("connectionType", getNetworkType(context));
                    hashMap5.put("online", Boolean.valueOf(isNetworkAvailable(context)));
                    HashMap hashMap6 = new HashMap();
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
                    hashMap6.put("screenDensity", Float.valueOf(displayMetrics.density));
                    hashMap6.put("screenDpi", Integer.valueOf(displayMetrics.densityDpi));
                    hashMap6.put("heightPixels", Integer.valueOf(displayMetrics.heightPixels));
                    hashMap6.put("widthPixels", Integer.valueOf(displayMetrics.widthPixels));
                    StringBuilder sb = new StringBuilder();
                    sb.append(displayMetrics.heightPixels);
                    sb.append('x');
                    sb.append(displayMetrics.widthPixels);
                    hashMap6.put("screenResolution", sb.toString());
                    hashMap.put("additionalInfo", hashMap2);
                    hashMap.put("batteryInfo", hashMap3);
                    hashMap.put("memoryInfo", hashMap4);
                    hashMap.put("networkInfo", hashMap5);
                    hashMap.put("displayInfo", hashMap6);
                    return hashMap;
                }
            }
        }
        str = null;
        hashMap.put(Device.JsonKeys.FAMILY, str);
        List<String> architectures2 = getArchitectures();
        hashMap.put(Device.JsonKeys.ARCHS, architectures2);
        hashMap.put(DebugImage.JsonKeys.ARCH, CollectionsKt.getOrNull(architectures2, 0));
        hashMap.put(Device.JsonKeys.MANUFACTURER, Build.MANUFACTURER);
        hashMap.put(Device.JsonKeys.BRAND, Build.BRAND);
        hashMap.put("modelId", Build.ID);
        hashMap.put(Device.JsonKeys.SIMULATOR, isEmulator());
        HashMap hashMap22 = new HashMap();
        TimeZone timeZone2 = getTimeZone(context);
        hashMap22.put("timezone", timeZone2 != null ? null : timeZone2.getID());
        hashMap22.put(Device.JsonKeys.LANGUAGE, Locale.getDefault().getDisplayName());
        hashMap22.put(Device.JsonKeys.ORIENTATION, getOrientation(context));
        hashMap22.put("bootTime", getBootTime());
        HashMap hashMap32 = new HashMap();
        hashMap32.put("level", getBatteryPercentage(context));
        hashMap32.put(Device.JsonKeys.CHARGING, isCharging(context));
        hashMap32.put("temperature", getBatteryTemperature(context));
        HashMap hashMap42 = new HashMap();
        ActivityManager.MemoryInfo memoryInfo2 = getMemoryInfo(context);
        hashMap42.put("lowMemory", Boolean.valueOf(memoryInfo2 == null && memoryInfo2.lowMemory));
        hashMap42.put("memorySizeBytes", memoryInfo2 != null ? null : Long.valueOf(memoryInfo2.totalMem));
        hashMap42.put("freeMemoryBytes", memoryInfo2 != null ? Long.valueOf(memoryInfo2.availMem) : null);
        hashMap42.put("storageSizeBytes", getTotalStorage(context));
        hashMap42.put("freeStorageBytes", getFreeStorage(context));
        HashMap hashMap52 = new HashMap();
        hashMap52.put("connectionType", getNetworkType(context));
        hashMap52.put("online", Boolean.valueOf(isNetworkAvailable(context)));
        HashMap hashMap62 = new HashMap();
        DisplayMetrics displayMetrics2 = context.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics2, "context.resources.displayMetrics");
        hashMap62.put("screenDensity", Float.valueOf(displayMetrics2.density));
        hashMap62.put("screenDpi", Integer.valueOf(displayMetrics2.densityDpi));
        hashMap62.put("heightPixels", Integer.valueOf(displayMetrics2.heightPixels));
        hashMap62.put("widthPixels", Integer.valueOf(displayMetrics2.widthPixels));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(displayMetrics2.heightPixels);
        sb2.append('x');
        sb2.append(displayMetrics2.widthPixels);
        hashMap62.put("screenResolution", sb2.toString());
        hashMap.put("additionalInfo", hashMap22);
        hashMap.put("batteryInfo", hashMap32);
        hashMap.put("memoryInfo", hashMap42);
        hashMap.put("networkInfo", hashMap52);
        hashMap.put("displayInfo", hashMap62);
        return hashMap;
    }

    public static final Map<String, Object> getOsProperties(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        HashMap hashMap = new HashMap();
        hashMap.put("name", "Android");
        hashMap.put("version", Build.VERSION.RELEASE);
        hashMap.put("build", Build.DISPLAY);
        hashMap.put("kernelVersion", getKernelVersion());
        hashMap.put(OperatingSystem.JsonKeys.ROOTED, Boolean.valueOf(new RootChecker(context).isDeviceRooted$hypersnapsdk_release()));
        return hashMap;
    }

    public static final boolean isNetworkAvailable(Context context) {
        NetworkCapabilities networkCapabilities;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("connectivity");
        ConnectivityManager connectivityManager = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
        if (connectivityManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
                return false;
            }
            return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(2);
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x006a, code lost:
    
        if (r6.intValue() != 9) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x005d, code lost:
    
        if (r6.intValue() != 1) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String getNetworkType(Context context) {
        Object m1202constructorimpl;
        Object systemService;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            systemService = context.getSystemService("connectivity");
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        String str = "cellular";
        if (isAPI23OrAbove()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            Intrinsics.checkNotNull(networkCapabilities);
            Intrinsics.checkNotNullExpressionValue(networkCapabilities, "connectivityManager.getNetworkCapabilities(nw)!!");
            if (networkCapabilities.hasTransport(1)) {
                str = "wifi";
            } else if (!networkCapabilities.hasTransport(0)) {
                if (networkCapabilities.hasTransport(3)) {
                    str = "ethernet";
                }
                str = null;
            }
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            Integer valueOf = activeNetworkInfo == null ? null : Integer.valueOf(activeNetworkInfo.getType());
            if (valueOf != null) {
            }
            if (valueOf != null) {
            }
            if (valueOf != null && valueOf.intValue() == 0) {
            }
            str = null;
        }
        m1202constructorimpl = Result.m1202constructorimpl(str);
        return (String) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    public static final PackageInfo getPackageInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        Intrinsics.checkNotNullExpressionValue(packageInfo, "packageManager.getPackageInfo(packageName, 0)");
        return packageInfo;
    }

    public static final String getVersionCodeString(PackageInfo packageInfo) {
        Intrinsics.checkNotNullParameter(packageInfo, "<this>");
        if (Build.VERSION.SDK_INT >= 28) {
            return String.valueOf(packageInfo.getLongVersionCode());
        }
        return getVersionCodeDep(packageInfo);
    }

    private static final String getVersionCodeDep(PackageInfo packageInfo) {
        Number valueOf;
        if (Build.VERSION.SDK_INT >= 28) {
            valueOf = Long.valueOf(packageInfo.getLongVersionCode());
        } else {
            valueOf = Integer.valueOf(packageInfo.versionCode);
        }
        return valueOf.toString();
    }

    public static final String getAppName(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "applicationInfo");
            int i = applicationInfo.labelRes;
            if (i == 0) {
                if (applicationInfo.nonLocalizedLabel != null) {
                    return applicationInfo.nonLocalizedLabel.toString();
                }
                return context.getPackageManager().getApplicationLabel(applicationInfo).toString();
            }
            return context.getString(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final List<String> getArchitectures() {
        if (!isAPI21OrAbove()) {
            return CollectionsKt.listOf((Object[]) new String[]{Build.CPU_ABI, Build.CPU_ABI2});
        }
        String[] SUPPORTED_ABIS = Build.SUPPORTED_ABIS;
        Intrinsics.checkNotNullExpressionValue(SUPPORTED_ABIS, "SUPPORTED_ABIS");
        return ArraysKt.toMutableList(SUPPORTED_ABIS);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String getOrientation(Context context) {
        Object m1202constructorimpl;
        int i;
        String str;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            i = context.getResources().getConfiguration().orientation;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (i != 0) {
            if (i == 1) {
                str = "portrait";
            } else if (i == 2) {
                str = "landscape";
            }
            m1202constructorimpl = Result.m1202constructorimpl(str);
            return (String) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        }
        str = null;
        m1202constructorimpl = Result.m1202constructorimpl(str);
        return (String) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    public static final Float getBatteryPercentage(Context context) {
        Object m1202constructorimpl;
        int intExtra;
        int intExtra2;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            intExtra = registerReceiver == null ? -1 : registerReceiver.getIntExtra("level", -1);
            intExtra2 = registerReceiver == null ? -1 : registerReceiver.getIntExtra("scale", -1);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (intExtra != -1 && intExtra2 != -1) {
            m1202constructorimpl = Result.m1202constructorimpl(Float.valueOf((intExtra / intExtra2) * 100.0f));
            return (Float) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x002a, code lost:
    
        if (r3.intValue() != 1) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Boolean isCharging(Context context) {
        Object m1202constructorimpl;
        Integer valueOf;
        boolean z;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            valueOf = registerReceiver == null ? null : Integer.valueOf(registerReceiver.getIntExtra("plugged", -1));
            z = true;
            if (valueOf != null) {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (valueOf != null && valueOf.intValue() == 2) {
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
            return (Boolean) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        }
        z = false;
        m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
        return (Boolean) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    public static final Float getBatteryTemperature(Context context) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver == null ? -1 : registerReceiver.getIntExtra("temperature", -1);
            m1202constructorimpl = Result.m1202constructorimpl(intExtra != -1 ? Float.valueOf(intExtra / 10) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        return (Float) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x011d, code lost:
    
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) io.sentry.protocol.Device.JsonKeys.SIMULATOR, false, 2, (java.lang.Object) null) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0029, code lost:
    
        if (kotlin.text.StringsKt.startsWith$default(r7, "generic", false, 2, (java.lang.Object) null) == false) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Boolean isEmulator() {
        Object m1202constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            String BRAND = Build.BRAND;
            Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
            boolean z = false;
            if (StringsKt.startsWith$default(BRAND, "generic", false, 2, (Object) null)) {
                String DEVICE = Build.DEVICE;
                Intrinsics.checkNotNullExpressionValue(DEVICE, "DEVICE");
            }
            String FINGERPRINT = Build.FINGERPRINT;
            Intrinsics.checkNotNullExpressionValue(FINGERPRINT, "FINGERPRINT");
            if (!StringsKt.startsWith$default(FINGERPRINT, "generic", false, 2, (Object) null)) {
                String FINGERPRINT2 = Build.FINGERPRINT;
                Intrinsics.checkNotNullExpressionValue(FINGERPRINT2, "FINGERPRINT");
                if (!StringsKt.startsWith$default(FINGERPRINT2, "unknown", false, 2, (Object) null)) {
                    String HARDWARE = Build.HARDWARE;
                    Intrinsics.checkNotNullExpressionValue(HARDWARE, "HARDWARE");
                    if (!StringsKt.contains$default((CharSequence) HARDWARE, (CharSequence) "goldfish", false, 2, (Object) null)) {
                        String HARDWARE2 = Build.HARDWARE;
                        Intrinsics.checkNotNullExpressionValue(HARDWARE2, "HARDWARE");
                        if (!StringsKt.contains$default((CharSequence) HARDWARE2, (CharSequence) "ranchu", false, 2, (Object) null)) {
                            String MODEL = Build.MODEL;
                            Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
                            if (!StringsKt.contains$default((CharSequence) MODEL, (CharSequence) "google_sdk", false, 2, (Object) null)) {
                                String MODEL2 = Build.MODEL;
                                Intrinsics.checkNotNullExpressionValue(MODEL2, "MODEL");
                                if (!StringsKt.contains$default((CharSequence) MODEL2, (CharSequence) "Emulator", false, 2, (Object) null)) {
                                    String MODEL3 = Build.MODEL;
                                    Intrinsics.checkNotNullExpressionValue(MODEL3, "MODEL");
                                    if (!StringsKt.contains$default((CharSequence) MODEL3, (CharSequence) "Android SDK built for x86", false, 2, (Object) null)) {
                                        String MANUFACTURER = Build.MANUFACTURER;
                                        Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
                                        if (!StringsKt.contains$default((CharSequence) MANUFACTURER, (CharSequence) "Genymotion", false, 2, (Object) null)) {
                                            String PRODUCT = Build.PRODUCT;
                                            Intrinsics.checkNotNullExpressionValue(PRODUCT, "PRODUCT");
                                            if (!StringsKt.contains$default((CharSequence) PRODUCT, (CharSequence) "sdk_google", false, 2, (Object) null)) {
                                                String PRODUCT2 = Build.PRODUCT;
                                                Intrinsics.checkNotNullExpressionValue(PRODUCT2, "PRODUCT");
                                                if (!StringsKt.contains$default((CharSequence) PRODUCT2, (CharSequence) "google_sdk", false, 2, (Object) null)) {
                                                    String PRODUCT3 = Build.PRODUCT;
                                                    Intrinsics.checkNotNullExpressionValue(PRODUCT3, "PRODUCT");
                                                    if (!StringsKt.contains$default((CharSequence) PRODUCT3, (CharSequence) "sdk", false, 2, (Object) null)) {
                                                        String PRODUCT4 = Build.PRODUCT;
                                                        Intrinsics.checkNotNullExpressionValue(PRODUCT4, "PRODUCT");
                                                        if (!StringsKt.contains$default((CharSequence) PRODUCT4, (CharSequence) "sdk_x86", false, 2, (Object) null)) {
                                                            String PRODUCT5 = Build.PRODUCT;
                                                            Intrinsics.checkNotNullExpressionValue(PRODUCT5, "PRODUCT");
                                                            if (!StringsKt.contains$default((CharSequence) PRODUCT5, (CharSequence) "vbox86p", false, 2, (Object) null)) {
                                                                String PRODUCT6 = Build.PRODUCT;
                                                                Intrinsics.checkNotNullExpressionValue(PRODUCT6, "PRODUCT");
                                                                if (!StringsKt.contains$default((CharSequence) PRODUCT6, (CharSequence) BuildConfig.TARGET_BACKEND, false, 2, (Object) null)) {
                                                                    String PRODUCT7 = Build.PRODUCT;
                                                                    Intrinsics.checkNotNullExpressionValue(PRODUCT7, "PRODUCT");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            z = true;
            m1202constructorimpl = Result.m1202constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        return (Boolean) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    public static final ActivityManager.MemoryInfo getMemoryInfo(Context context) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            Object systemService = context.getSystemService("activity");
            ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
            }
            m1202constructorimpl = Result.m1202constructorimpl(memoryInfo);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        return (ActivityManager.MemoryInfo) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    public static final Date getBootTime() {
        Object m1202constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(System.currentTimeMillis() - SystemClock.elapsedRealtime());
            m1202constructorimpl = Result.m1202constructorimpl(calendar.getTime());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            m1202constructorimpl = null;
        }
        return (Date) m1202constructorimpl;
    }

    public static final TimeZone getTimeZone(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            Intrinsics.checkNotNullExpressionValue(locales, "resources.configuration.locales");
            if (!locales.isEmpty()) {
                return Calendar.getInstance(locales.get(0)).getTimeZone();
            }
        }
        return Calendar.getInstance().getTimeZone();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String getDeviceId(Context context) {
        RandomAccessFile randomAccessFile;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            Charset charset = Charset.forName("UTF-8");
            File file = new File(context.getFilesDir(), AppConstants.DEVICE_ID);
            if (!file.exists()) {
                randomAccessFile = new FileOutputStream(file);
                try {
                    FileOutputStream fileOutputStream = randomAccessFile;
                    String uuid = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                    Intrinsics.checkNotNullExpressionValue(charset, "charset");
                    byte[] bytes = uuid.getBytes(charset);
                    Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    CloseableKt.closeFinally(randomAccessFile, null);
                    return uuid;
                } finally {
                }
            } else {
                randomAccessFile = new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
                try {
                    RandomAccessFile randomAccessFile2 = randomAccessFile;
                    byte[] bArr = new byte[(int) randomAccessFile2.length()];
                    randomAccessFile2.readFully(bArr);
                    Intrinsics.checkNotNullExpressionValue(charset, "charset");
                    String str = new String(bArr, charset);
                    CloseableKt.closeFinally(randomAccessFile, null);
                    return str;
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Object m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            return (String) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        }
        Result.Companion companion22 = Result.INSTANCE;
        Object m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
        return (String) (Result.m1208isFailureimpl(m1202constructorimpl2) ? null : m1202constructorimpl2);
    }

    public static final Long getTotalStorage(Context context) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            File externalFilesDir = context.getExternalFilesDir(null);
            StatFs statFs = new StatFs(externalFilesDir == null ? null : externalFilesDir.getPath());
            m1202constructorimpl = Result.m1202constructorimpl(Long.valueOf(statFs.getBlockCountLong() * statFs.getBlockSizeLong()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        return (Long) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
    }

    public static final Long getFreeStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            File externalFilesDir = context.getExternalFilesDir(null);
            StatFs statFs = new StatFs(externalFilesDir == null ? null : externalFilesDir.getPath());
            return Long.valueOf(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Object m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            return (Long) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        }
    }

    public static final String getKernelVersion() {
        String property = System.getProperty("os.version");
        File file = new File("/proc/version");
        if (!file.canRead()) {
            return property;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String readLine = bufferedReader.readLine();
                CloseableKt.closeFinally(bufferedReader, null);
                return readLine;
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m1202constructorimpl(ResultKt.createFailure(th));
            return property;
        }
    }
}
