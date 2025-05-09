package co.hyperverge.hyperkyc.data.network;

import android.app.Application;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.service.HVSignatureService;
import co.hyperverge.hypersnapsdk.service.security.HVSecurity;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: SignatureUtils.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0002J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006J\u001c\u0010\u000f\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\u0010"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/SignatureUtils;", "", "()V", "getPublicKey", "Ljava/security/PublicKey;", "getSortedJSON", "", "nonSortedJson", "hash", WorkflowRequestType.PLAIN_TEXT, "key", "isSignatureVerified", "", "responseBody", "signature", "verify", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SignatureUtils {
    public static final SignatureUtils INSTANCE = new SignatureUtils();

    private SignatureUtils() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x015d, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isSignatureVerified(String key, String responseBody, String signature) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        Intrinsics.checkNotNullParameter(signature, "signature");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str5 = "isSignatureVerified() called with: key = [" + key + "], responseBody = [" + responseBody + "], signature = [" + signature + ']';
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        str = null;
                    } else {
                        str = null;
                        str2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls2 = getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (str2 == null) {
                        str3 = "N/A";
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                            str3 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        str4 = "isSignatureVerified() called with: key = [" + key + "], responseBody = [" + responseBody + "], signature = [" + signature + ']';
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str3, sb2.toString());
                    }
                    str3 = str2;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                    if (matcher.find()) {
                    }
                    if (str3.length() > 23) {
                        str3 = str3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb22 = new StringBuilder();
                    str4 = "isSignatureVerified() called with: key = [" + key + "], responseBody = [" + responseBody + "], signature = [" + signature + ']';
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        return verify(hash(getSortedJSON(responseBody), key), signature);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x01e7, code lost:
    
        if (r9 != null) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0267 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x033c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getSortedJSON(String nonSortedJson) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        StackTraceElement stackTraceElement;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        String str6;
        Matcher matcher2;
        String str7;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher3.find()) {
            canonicalName = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str8 = "isSignatureVerified() called with: nonSortedJson = [" + nonSortedJson + ']';
        if (str8 == null) {
            str8 = "null ";
        }
        sb.append(str8);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher4.find()) {
                        canonicalName2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str9 = "isSignatureVerified() called with: nonSortedJson = [" + nonSortedJson + ']';
                    if (str9 == null) {
                        str9 = "null ";
                    }
                    sb2.append(str9);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                String sortedJson = HVSignatureService.sortJSONKeysAlphabetically(HVSignatureService.convertJSONObjToMap(new JSONObject(nonSortedJson)));
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null) {
                    str2 = "Throwable().stackTrace";
                } else {
                    str2 = "Throwable().stackTrace";
                    str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                Class<?> cls3 = getClass();
                String canonicalName3 = cls3 == null ? cls3.getCanonicalName() : null;
                str3 = canonicalName3 != null ? str : canonicalName3;
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                if (matcher.find()) {
                    str3 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                }
                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str3 = str3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb3.append(str3);
                sb3.append(" - ");
                str4 = "Sorted JSON = [" + sortedJson + ']';
                if (str4 == null) {
                    str4 = "null ";
                }
                sb3.append(str4);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str2);
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str5 = null;
                            } else {
                                str5 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str6 = substringAfterLast$default;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                                    if (matcher2.find()) {
                                        str6 = matcher2.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                    }
                                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str6 = str6.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    str7 = "Sorted JSON = [" + sortedJson + ']';
                                    if (str7 == null) {
                                        str7 = "null ";
                                    }
                                    sb4.append(str7);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str6, sb4.toString());
                                }
                            }
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str5;
                            str6 = canonicalName4 == null ? str : canonicalName4;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                            if (matcher2.find()) {
                            }
                            if (str6.length() > 23) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb42 = new StringBuilder();
                            str7 = "Sorted JSON = [" + sortedJson + ']';
                            if (str7 == null) {
                            }
                            sb42.append(str7);
                            sb42.append(' ');
                            sb42.append("");
                            Log.println(3, str6, sb42.toString());
                        }
                    }
                }
                Intrinsics.checkNotNullExpressionValue(sortedJson, "sortedJson");
                return sortedJson;
            }
        }
        str = "N/A";
        String sortedJson2 = HVSignatureService.sortJSONKeysAlphabetically(HVSignatureService.convertJSONObjToMap(new JSONObject(nonSortedJson)));
        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb32 = new StringBuilder();
        StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace32, "Throwable().stackTrace");
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
        if (stackTraceElement != null) {
        }
        str2 = "Throwable().stackTrace";
        Class<?> cls32 = getClass();
        if (cls32 == null) {
        }
        if (canonicalName3 != null) {
        }
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
        if (matcher.find()) {
        }
        if (str3.length() > 23) {
            str3 = str3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb32.append(str3);
        sb32.append(" - ");
        str4 = "Sorted JSON = [" + sortedJson2 + ']';
        if (str4 == null) {
        }
        sb32.append(str4);
        sb32.append(' ');
        sb32.append("");
        companion42.log(level22, sb32.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        Intrinsics.checkNotNullExpressionValue(sortedJson2, "sortedJson");
        return sortedJson2;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x028b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x021d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean verify(String hash, String signature) {
        String canonicalName;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        String className;
        StackTraceElement stackTraceElement;
        String canonicalName3;
        Matcher matcher;
        String str2;
        Object m1202constructorimpl2;
        String str3;
        String str4;
        Matcher matcher2;
        String str5;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className4 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher3.find()) {
            canonicalName = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str6 = "verify() called with: hash = [" + hash + "], signature = [" + signature + ']';
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                charSequence = "co.hyperverge";
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher4.find()) {
                        canonicalName2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str7 = "verify() called with: hash = [" + hash + "], signature = [" + signature + ']';
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                boolean verifySignature = HVSecurity.builder().publicKey(getPublicKey()).signatureAlgorithm(HVSecurity.SignatureAlgorithm.SHA256withRSA).data(hash).build().verifySignature(signature);
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName3 = cls3 == null ? cls3.getCanonicalName() : null;
                    if (canonicalName3 == null) {
                        canonicalName3 = str;
                    }
                }
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                if (matcher.find()) {
                    canonicalName3 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                }
                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName3 = canonicalName3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb3.append(canonicalName3);
                sb3.append(" - ");
                str2 = "Is signature verified = [" + verifySignature + ']';
                if (str2 == null) {
                    str2 = "null ";
                }
                sb3.append(str2);
                sb3.append(' ');
                sb3.append("");
                companion4.log(level2, sb3.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                    } catch (Throwable th2) {
                        Result.Companion companion6 = Result.INSTANCE;
                        m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                        m1202constructorimpl2 = "";
                    }
                    String packageName2 = (String) m1202constructorimpl2;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                str3 = null;
                            } else {
                                str3 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str4 = substringAfterLast$default;
                                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                    if (matcher2.find()) {
                                        str4 = matcher2.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                    }
                                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str4 = str4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    str5 = "Is signature verified = [" + verifySignature + ']';
                                    if (str5 == null) {
                                        str5 = "null ";
                                    }
                                    sb4.append(str5);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str4, sb4.toString());
                                }
                            }
                            Class<?> cls4 = getClass();
                            String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str3;
                            str4 = canonicalName4 == null ? str : canonicalName4;
                            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher2.find()) {
                            }
                            if (str4.length() > 23) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb42 = new StringBuilder();
                            str5 = "Is signature verified = [" + verifySignature + ']';
                            if (str5 == null) {
                            }
                            sb42.append(str5);
                            sb42.append(' ');
                            sb42.append("");
                            Log.println(3, str4, sb42.toString());
                        }
                    }
                }
                return verifySignature;
            }
        }
        charSequence = "co.hyperverge";
        str = "N/A";
        boolean verifySignature2 = HVSecurity.builder().publicKey(getPublicKey()).signatureAlgorithm(HVSecurity.SignatureAlgorithm.SHA256withRSA).data(hash).build().verifySignature(signature);
        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb32 = new StringBuilder();
        StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace32, "Throwable().stackTrace");
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
        if (stackTraceElement != null) {
        }
        Class<?> cls32 = getClass();
        if (cls32 == null) {
        }
        if (canonicalName3 == null) {
        }
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
        if (matcher.find()) {
        }
        if (canonicalName3.length() > 23) {
            canonicalName3 = canonicalName3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb32.append(canonicalName3);
        sb32.append(" - ");
        str2 = "Is signature verified = [" + verifySignature2 + ']';
        if (str2 == null) {
        }
        sb32.append(str2);
        sb32.append(' ');
        sb32.append("");
        companion42.log(level22, sb32.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        return verifySignature2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x04be, code lost:
    
        if (r0 != null) goto L188;
     */
    /* JADX WARN: Removed duplicated region for block: B:132:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0253 A[Catch: Exception -> 0x0398, TryCatch #1 {Exception -> 0x0398, blocks: (B:22:0x01bf, B:24:0x0221, B:26:0x0227, B:29:0x0242, B:31:0x0253, B:32:0x025a, B:34:0x0264, B:37:0x026b, B:38:0x0273, B:41:0x0293, B:48:0x02da, B:51:0x02e1, B:53:0x02e9, B:55:0x02fb, B:57:0x0311, B:59:0x0317, B:62:0x0332, B:64:0x0343, B:65:0x034a, B:67:0x0354, B:70:0x035b, B:71:0x0363, B:74:0x0382, B:76:0x0322, B:78:0x0328, B:84:0x02d0, B:85:0x0232, B:87:0x0238, B:47:0x02ad), top: B:21:0x01bf, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0238 A[Catch: Exception -> 0x0398, TryCatch #1 {Exception -> 0x0398, blocks: (B:22:0x01bf, B:24:0x0221, B:26:0x0227, B:29:0x0242, B:31:0x0253, B:32:0x025a, B:34:0x0264, B:37:0x026b, B:38:0x0273, B:41:0x0293, B:48:0x02da, B:51:0x02e1, B:53:0x02e9, B:55:0x02fb, B:57:0x0311, B:59:0x0317, B:62:0x0332, B:64:0x0343, B:65:0x034a, B:67:0x0354, B:70:0x035b, B:71:0x0363, B:74:0x0382, B:76:0x0322, B:78:0x0328, B:84:0x02d0, B:85:0x0232, B:87:0x0238, B:47:0x02ad), top: B:21:0x01bf, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x023d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String hash(String plainText, String key) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        String str6;
        Matcher matcher;
        String localizedMessage;
        String className2;
        String className3;
        StackTraceElement stackTraceElement;
        String canonicalName3;
        Matcher matcher2;
        String str7;
        Object m1202constructorimpl3;
        String canonicalName4;
        String className4;
        String className5;
        String className6;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className6 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str8 = "";
        if (matcher3.find()) {
            canonicalName = matcher3.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str9 = "hash() called with: plainText = [" + plainText + "], key = [" + key + ']';
        if (str9 == null) {
            str9 = "null ";
        }
        sb.append(str9);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        try {
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher4.find()) {
                            canonicalName2 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str10 = "hash() called with: plainText = [" + plainText + "], key = [" + key + ']';
                        if (str10 == null) {
                            str10 = "null ";
                        }
                        sb2.append(str10);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    Mac mac = Mac.getInstance("HmacSHA256");
                    Charset UTF_8 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                    byte[] bytes = key.getBytes(UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    mac.init(new SecretKeySpec(bytes, "HmacSHA256"));
                    Charset UTF_82 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
                    byte[] bytes2 = plainText.getBytes(UTF_82);
                    Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                    byte[] hashBytes = mac.doFinal(bytes2);
                    Intrinsics.checkNotNullExpressionValue(hashBytes, "hashBytes");
                    String hexString = CoreExtsKt.toHexString(hashBytes);
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement != null || (className5 = stackTraceElement.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        canonicalName3 = cls3 == null ? cls3.getCanonicalName() : null;
                        if (canonicalName3 == null) {
                            canonicalName3 = str;
                        }
                    }
                    matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher2.find()) {
                        canonicalName3 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(canonicalName3);
                    sb3.append(" - ");
                    str7 = "created hash = [" + hexString + ']';
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb3.append(str7);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = getClass();
                                    canonicalName4 = cls4 != null ? cls4.getCanonicalName() : null;
                                    if (canonicalName4 == null) {
                                        canonicalName4 = str;
                                    }
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                                if (matcher5.find()) {
                                    canonicalName4 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName4 = canonicalName4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str11 = "created hash = [" + hexString + ']';
                                if (str11 == null) {
                                    str11 = "null ";
                                }
                                sb4.append(str11);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(3, canonicalName4, sb4.toString());
                            }
                        }
                    }
                    return hexString;
                }
            }
            Mac mac2 = Mac.getInstance("HmacSHA256");
            Charset UTF_83 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_83, "UTF_8");
            byte[] bytes3 = key.getBytes(UTF_83);
            Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
            mac2.init(new SecretKeySpec(bytes3, "HmacSHA256"));
            Charset UTF_822 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_822, "UTF_8");
            byte[] bytes22 = plainText.getBytes(UTF_822);
            Intrinsics.checkNotNullExpressionValue(bytes22, "this as java.lang.String).getBytes(charset)");
            byte[] hashBytes2 = mac2.doFinal(bytes22);
            Intrinsics.checkNotNullExpressionValue(hashBytes2, "hashBytes");
            String hexString2 = CoreExtsKt.toHexString(hashBytes2);
            HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
            HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb32 = new StringBuilder();
            StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace32, "Throwable().stackTrace");
            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
            if (stackTraceElement != null) {
            }
            Class<?> cls32 = getClass();
            if (cls32 == null) {
            }
            if (canonicalName3 == null) {
            }
            matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
            if (matcher2.find()) {
            }
            Unit unit32 = Unit.INSTANCE;
            if (canonicalName3.length() > 23) {
                canonicalName3 = canonicalName3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb32.append(canonicalName3);
            sb32.append(" - ");
            str7 = "created hash = [" + hexString2 + ']';
            if (str7 == null) {
            }
            sb32.append(str7);
            sb32.append(' ');
            sb32.append("");
            companion42.log(level22, sb32.toString());
            if (!CoreExtsKt.isRelease()) {
            }
            return hexString2;
        } catch (Exception e) {
            HyperLogger.Level level3 = HyperLogger.Level.ERROR;
            HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb5 = new StringBuilder();
            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
            if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls5 = getClass();
                String canonicalName5 = cls5 != null ? cls5.getCanonicalName() : null;
                str2 = canonicalName5 == null ? str : canonicalName5;
            }
            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
            if (matcher6.find()) {
                str2 = matcher6.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
            }
            Unit unit5 = Unit.INSTANCE;
            if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str2 = str2.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb5.append(str2);
            sb5.append(" - ");
            sb5.append("Failed to create a hash");
            sb5.append(' ');
            Exception exc = e;
            String localizedMessage2 = exc.getLocalizedMessage();
            if (localizedMessage2 != null) {
                str3 = '\n' + localizedMessage2;
            } else {
                str3 = "";
            }
            sb5.append(str3);
            companion7.log(level3, sb5.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion8 = Result.INSTANCE;
                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion9 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName3 = (String) m1202constructorimpl2;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                    if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                        str4 = null;
                    } else {
                        str4 = null;
                        str5 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls6 = getClass();
                    str5 = cls6 != null ? cls6.getCanonicalName() : str4;
                    if (str5 == null) {
                        str6 = str;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                        if (matcher.find()) {
                            str6 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                        }
                        Unit unit6 = Unit.INSTANCE;
                        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str6 = str6.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("Failed to create a hash");
                        sb6.append(' ');
                        localizedMessage = exc.getLocalizedMessage();
                        if (localizedMessage != null) {
                            str8 = '\n' + localizedMessage;
                        }
                        sb6.append(str8);
                        Log.println(6, str6, sb6.toString());
                        return str4;
                    }
                    str6 = str5;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                    if (matcher.find()) {
                    }
                    Unit unit62 = Unit.INSTANCE;
                    if (str6.length() > 23) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb62 = new StringBuilder();
                    sb62.append("Failed to create a hash");
                    sb62.append(' ');
                    localizedMessage = exc.getLocalizedMessage();
                    if (localizedMessage != null) {
                    }
                    sb62.append(str8);
                    Log.println(6, str6, sb62.toString());
                    return str4;
                }
            }
            str4 = null;
            return str4;
        }
        str = "N/A";
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x01db, code lost:
    
        if (r15 != null) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x02d5, code lost:
    
        if (r0 != null) goto L129;
     */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0330  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final PublicKey getPublicKey() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        Object m1202constructorimpl2;
        CharSequence charSequence;
        String str;
        String str2;
        String str3;
        String str4;
        Object m1202constructorimpl3;
        String str5;
        Object obj;
        String str6;
        String str7;
        Matcher matcher;
        String localizedMessage;
        Class<?> cls;
        String className2;
        Class<?> cls2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls3 = getClass();
            canonicalName = cls3 != null ? cls3.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str8 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        sb.append("getPublicKey() called with");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls4 = getClass();
                        canonicalName2 = cls4 != null ? cls4.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, canonicalName2, "getPublicKey() called with ");
                }
            }
        }
        byte[] decode = Base64.decode("MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA7hhIXqpCbIpfdilqLKVQR4wC/T7yUPDsn+/SgCcbv+ANi+zAHR4sQh/q4SvQhOdlPk81uk93Slu+2fr9jG4P3toVYygKM92mYxnXia/NpFLviJg+0iHoIywqGjpa5YGU/7x4IfklkO0/BdCerq3+PrzepD9FI0LnVxKaoki3QpgTb+HSg9IIgWd+alj8YFcRklvwzySZN0ACGrKfyOsb8cxJXn9+n5mR/EDOrG9ET0yjW4d9eonP7R+3yx0h0Ihb0EBoLAUI0u0C9oL07j23+ZArbLjQH4dKHiXwPlmTdjTyQk4UyXHiJgUPmeCQw6YjTOj+ZsgIyEQSSmiaETPG81voIAuGMaWvRF4gTmzCF9cpb1JOubk/2Kp/39ow9av8NxxeI4XmlUVV8ogaC6WnLTytTATRZSqoyHV36R391vO6tQ3KC7/EAin+0RmyFkKbjBWXMS5I+GhSRkvXz9gMgGsfCgAsId+aE85chReUK92TX+/Q22p7a7DOwS+4mQqndV45GjgSJiTeSkqEcMre6VtG+NdgDfV0WMvOAr+jI8WpKXpuOOBIhswiG0wcroT3Bya2FLvGzMfKWm5Q8IE4gfhEzZmJTLYg7deHGjMV+3o/nM+BMswNpVPgnmjGy00VfzH1O5pq2QiXTJrFAqAAY70Ri/xxTdmnXvh+W83NH8MCAwEAAQ==", 0);
        try {
            Result.Companion companion4 = Result.INSTANCE;
            SignatureUtils signatureUtils = this;
            m1202constructorimpl2 = Result.m1202constructorimpl(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode)));
        } catch (Throwable th2) {
            Result.Companion companion5 = Result.INSTANCE;
            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
        if (m1205exceptionOrNullimpl == null) {
            obj = m1202constructorimpl2;
        } else {
            SignatureUtils signatureUtils2 = INSTANCE;
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb2 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                charSequence = "co.hyperverge";
                str = "Throwable().stackTrace";
                str2 = "N/A";
            } else {
                charSequence = "co.hyperverge";
                str = "Throwable().stackTrace";
                str2 = "N/A";
                str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            String canonicalName3 = (signatureUtils2 == null || (cls2 = signatureUtils2.getClass()) == null) ? null : cls2.getCanonicalName();
            str3 = canonicalName3 == null ? str2 : canonicalName3;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher4.find()) {
                str3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb2.append(str3);
            sb2.append(" - ");
            sb2.append("failed creating public key");
            sb2.append(' ');
            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str4 = '\n' + localizedMessage2;
            } else {
                str4 = "";
            }
            sb2.append(str4);
            companion6.log(level2, sb2.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion7 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion8 = Result.INSTANCE;
                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                m1202constructorimpl3 = "";
            }
            String packageName2 = (String) m1202constructorimpl3;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, str);
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str5 = null;
                    } else {
                        str5 = null;
                        str6 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    str6 = (signatureUtils2 == null || (cls = signatureUtils2.getClass()) == null) ? str5 : cls.getCanonicalName();
                    if (str6 == null) {
                        str7 = str2;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                        if (matcher.find()) {
                            str7 = matcher.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                        }
                        if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str7 = str7.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("failed creating public key");
                        sb3.append(' ');
                        localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str5;
                        if (localizedMessage != null) {
                            str8 = '\n' + localizedMessage;
                        }
                        sb3.append(str8);
                        Log.println(6, str7, sb3.toString());
                        obj = str5;
                    }
                    str7 = str6;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                    if (matcher.find()) {
                    }
                    if (str7.length() > 23) {
                        str7 = str7.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb32 = new StringBuilder();
                    sb32.append("failed creating public key");
                    sb32.append(' ');
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    if (localizedMessage != null) {
                    }
                    sb32.append(str8);
                    Log.println(6, str7, sb32.toString());
                    obj = str5;
                }
            }
            str5 = null;
            obj = str5;
        }
        return (PublicKey) obj;
    }
}
