package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import co.hyperverge.hyperlogger.HyperLogger;
import java.io.Serializable;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: IntentExts.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a(\u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\b¢\u0006\u0002\u0010\b\u001a(\u0010\t\u001a\u0004\u0018\u0001H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\n*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\b¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"extrasSize", "", "Landroid/content/Intent;", "getParcelable", "T", "Landroid/os/Parcelable;", "key", "", "(Landroid/content/Intent;Ljava/lang/String;)Landroid/os/Parcelable;", "getSerializable", "Ljava/io/Serializable;", "(Landroid/content/Intent;Ljava/lang/String;)Ljava/io/Serializable;", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class IntentExtsKt {
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01ab, code lost:
    
        if (r0 != null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bb, code lost:
    
        if (r0 == null) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01bf, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d1, code lost:
    
        if (r0.find() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01d3, code lost:
    
        r11 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01e3, code lost:
    
        if (r11.length() <= 23) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01e9, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01ec, code lost:
    
        r11 = r11.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01f4, code lost:
    
        r0 = new java.lang.StringBuilder();
        r1 = "getSerializable: failed for key " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x020b, code lost:
    
        if (r1 != null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x020e, code lost:
    
        r14 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x020f, code lost:
    
        r0.append(r14);
        r0.append(' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0217, code lost:
    
        if (r4 == null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0219, code lost:
    
        r1 = r4.getLocalizedMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0222, code lost:
    
        if (r1 == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0224, code lost:
    
        r12 = '\n' + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0233, code lost:
    
        r0.append(r12);
        android.util.Log.println(6, r11, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x021e, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01be, code lost:
    
        r11 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ <T extends Serializable> T getSerializable(Intent intent, String key) {
        Object m1202constructorimpl;
        String canonicalName;
        String str;
        Object m1202constructorimpl2;
        String str2;
        String className;
        String className2;
        Serializable serializableExtra;
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intent intent2 = intent;
            if (Build.VERSION.SDK_INT >= 33) {
                Intrinsics.reifiedOperationMarker(4, "T");
                serializableExtra = intent.getSerializableExtra(key, Serializable.class);
            } else {
                serializableExtra = intent.getSerializableExtra(key);
                Intrinsics.reifiedOperationMarker(1, "T");
                Serializable serializable = serializableExtra;
                Serializable serializable2 = serializableExtra;
            }
            m1202constructorimpl = Result.m1202constructorimpl(serializableExtra);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
        String str3 = null;
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str4 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = intent.getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            String str5 = "";
            if (matcher.find()) {
                canonicalName = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str6 = "getSerializable: failed for key " + key;
            String str7 = "null ";
            if (str6 == null) {
                str6 = "null ";
            }
            sb.append(str6);
            sb.append(' ');
            String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage != null) {
                str = '\n' + localizedMessage;
            } else {
                str = "";
            }
            sb.append(str);
            companion3.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = intent.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str3;
                }
            }
            str3 = null;
        }
        if (Result.m1208isFailureimpl(obj)) {
            obj = str3;
        }
        return (T) obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x01ad, code lost:
    
        if (r0 != null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01bd, code lost:
    
        if (r0 == null) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c1, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01d3, code lost:
    
        if (r0.find() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01d5, code lost:
    
        r11 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01e5, code lost:
    
        if (r11.length() <= 23) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01eb, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01ee, code lost:
    
        r11 = r11.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01f6, code lost:
    
        r0 = new java.lang.StringBuilder();
        r1 = "getParcelable: failed for key " + r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x020d, code lost:
    
        if (r1 != null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0210, code lost:
    
        r14 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0211, code lost:
    
        r0.append(r14);
        r0.append(' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0219, code lost:
    
        if (r4 == null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x021b, code lost:
    
        r1 = r4.getLocalizedMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0224, code lost:
    
        if (r1 == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0226, code lost:
    
        r12 = '\n' + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0235, code lost:
    
        r0.append(r12);
        android.util.Log.println(6, r11, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0220, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01c0, code lost:
    
        r11 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ <T extends Parcelable> T getParcelable(Intent intent, String key) {
        Object m1202constructorimpl;
        String canonicalName;
        String str;
        Object m1202constructorimpl2;
        String str2;
        String className;
        String className2;
        Parcelable parcelableExtra;
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Result.Companion companion = Result.INSTANCE;
            Intent intent2 = intent;
            if (Build.VERSION.SDK_INT >= 33) {
                Intrinsics.reifiedOperationMarker(4, "T");
                parcelableExtra = (Parcelable) intent.getParcelableExtra(key, Parcelable.class);
            } else {
                parcelableExtra = intent.getParcelableExtra(key);
                Intrinsics.reifiedOperationMarker(1, "T");
                Parcelable parcelable = parcelableExtra;
                Parcelable parcelable2 = parcelableExtra;
            }
            m1202constructorimpl = Result.m1202constructorimpl(parcelableExtra);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = m1202constructorimpl;
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
        String str3 = null;
        if (m1205exceptionOrNullimpl != null) {
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str4 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = intent.getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            String str5 = "";
            if (matcher.find()) {
                canonicalName = matcher.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str6 = "getParcelable: failed for key " + key;
            String str7 = "null ";
            if (str6 == null) {
                str6 = "null ";
            }
            sb.append(str6);
            sb.append(' ');
            String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage != null) {
                str = '\n' + localizedMessage;
            } else {
                str = "";
            }
            sb.append(str);
            companion3.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = intent.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str3;
                }
            }
            str3 = null;
        }
        if (Result.m1208isFailureimpl(obj)) {
            obj = str3;
        }
        return (T) obj;
    }

    public static final /* synthetic */ long extrasSize(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "<this>");
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return 0L;
        }
        Parcel obtain = Parcel.obtain();
        Intrinsics.checkNotNullExpressionValue(obtain, "obtain()");
        try {
            obtain.writeBundle(extras);
            return obtain.dataSize();
        } finally {
            obtain.recycle();
        }
    }
}
