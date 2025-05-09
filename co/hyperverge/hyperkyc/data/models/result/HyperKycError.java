package co.hyperverge.hyperkyc.data.models.result;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HyperKycError.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b0\n\u0002\u0010$\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u00106\u001a\u00020\u00042\b\u00107\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u00108R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000405X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycError;", "", "()V", "ACTIVITY_DESTROYED_ERROR", "", "BROWSER_NOT_SUPPORTED", "CAPTURE_TIME_OUT", "DEVICE_ROOTED_ERROR", "FACE_NOT_DETECTED_ERROR", "FORM_V2_ERROR", "GPS_ACCESS_DENIED", "HARDWARE_ERROR", "HS_ACTIVE_SESSION_ERROR", "HS_BLURRY_FACE_DETECTION_ERROR", "HS_CAPTURE_TIMEOUT_ERROR", "HS_DOC_DETECT_MODULE_NOT_FOUND_ERROR", "HS_FACE_DETECTION_ERROR", "HS_GPS_ACCESS_DENIED", "HS_HARDWARE_ERROR", "HS_INITIALIZATION_ERROR", "HS_INPUT_ERROR", "HS_INSTRUCTION_ERROR", "HS_INTERNAL_SDK_ERROR", "HS_INTERNAL_SERVER_ERROR", "HS_INVALID_FILE_ERROR", "HS_LOCATION_PERMISSION_NOT_AVAILABLE_ERROR", "HS_LOW_STORAGE_ERROR", "HS_NETWORK_ERROR", "HS_OPERATION_CANCELLED_BY_USER_ERROR", "HS_PERMISSIONS_NOT_GRANTED_ERROR", "HS_QR_PARSER_ERROR", "HS_QR_SCANNER_ERROR", "HS_SIGNATURE_FAILED_ERROR", "HS_SSL_CONNECT_ERROR", "HS_TRANSACTION_ID_EMPTY", "INVALID_FILE_ERROR", "LOW_STORAGE_ERROR", "NETWORK_ERROR", "NFC_AUTHENTICATION_ERROR", "NFC_CONNECTION_ERROR", "NFC_INCOMPLETE_SCAN_ERROR", "NFC_INVALID_ERROR", "NFC_UNAVAILABLE_ERROR", "PERMISSIONS_ERROR", "QR_SCANNER_ERROR", "SDK_CONFIG_ERROR", "SDK_INPUT_ERROR", "SDK_VERSION_ERROR", "SIGNATURE_FAILED_ERROR", "SSL_CONNECT_ERROR", "USER_CANCELLED_ERROR", "WORKFLOW_ERROR", "hsHkErrorCodeMap", "", "mapIfRequired", "errorCode", "(Ljava/lang/Integer;)I", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperKycError {
    public static final int ACTIVITY_DESTROYED_ERROR = 117;
    public static final int BROWSER_NOT_SUPPORTED = 124;
    public static final int CAPTURE_TIME_OUT = 115;
    public static final int DEVICE_ROOTED_ERROR = 114;
    private static final int FACE_NOT_DETECTED_ERROR = 113;
    public static final int FORM_V2_ERROR = 123;
    private static final int GPS_ACCESS_DENIED = 108;
    private static final int HARDWARE_ERROR = 107;
    private static final int HS_ACTIVE_SESSION_ERROR = 16;
    private static final int HS_BLURRY_FACE_DETECTION_ERROR = 23;
    private static final int HS_CAPTURE_TIMEOUT_ERROR = 35;
    private static final int HS_DOC_DETECT_MODULE_NOT_FOUND_ERROR = 34;
    private static final int HS_FACE_DETECTION_ERROR = 22;
    private static final int HS_GPS_ACCESS_DENIED = 33;
    private static final int HS_HARDWARE_ERROR = 5;
    private static final int HS_INITIALIZATION_ERROR = 11;
    private static final int HS_INPUT_ERROR = 6;
    private static final int HS_INSTRUCTION_ERROR = 31;
    private static final int HS_INTERNAL_SDK_ERROR = 2;
    private static final int HS_INTERNAL_SERVER_ERROR = 14;
    private static final int HS_INVALID_FILE_ERROR = 37;
    private static final int HS_LOCATION_PERMISSION_NOT_AVAILABLE_ERROR = 8;
    private static final int HS_LOW_STORAGE_ERROR = 25;
    private static final int HS_NETWORK_ERROR = 12;
    private static final int HS_OPERATION_CANCELLED_BY_USER_ERROR = 3;
    private static final int HS_PERMISSIONS_NOT_GRANTED_ERROR = 4;
    private static final int HS_QR_PARSER_ERROR = 7;
    private static final int HS_QR_SCANNER_ERROR = 32;
    private static final int HS_SIGNATURE_FAILED_ERROR = 18;
    private static final int HS_SSL_CONNECT_ERROR = 15;
    private static final int HS_TRANSACTION_ID_EMPTY = 17;
    private static final int INVALID_FILE_ERROR = 116;
    public static final int LOW_STORAGE_ERROR = 118;
    public static final int NETWORK_ERROR = 111;
    public static final int NFC_AUTHENTICATION_ERROR = 121;
    public static final int NFC_CONNECTION_ERROR = 122;
    public static final int NFC_INCOMPLETE_SCAN_ERROR = 125;
    public static final int NFC_INVALID_ERROR = 119;
    public static final int NFC_UNAVAILABLE_ERROR = 120;
    public static final int PERMISSIONS_ERROR = 106;
    private static final int QR_SCANNER_ERROR = 109;
    public static final int SDK_CONFIG_ERROR = 101;
    public static final int SDK_INPUT_ERROR = 102;
    public static final int SDK_VERSION_ERROR = 105;
    public static final int SIGNATURE_FAILED_ERROR = 112;
    private static final int SSL_CONNECT_ERROR = 110;
    public static final int USER_CANCELLED_ERROR = 103;
    public static final int WORKFLOW_ERROR = 104;
    public static final HyperKycError INSTANCE = new HyperKycError();
    private static final Map<Integer, Integer> hsHkErrorCodeMap = MapsKt.mapOf(TuplesKt.to(11, 104), TuplesKt.to(2, 104), TuplesKt.to(6, 104), TuplesKt.to(17, 104), TuplesKt.to(16, 104), TuplesKt.to(31, 104), TuplesKt.to(34, 104), TuplesKt.to(3, 103), TuplesKt.to(4, 106), TuplesKt.to(8, 106), TuplesKt.to(32, 109), TuplesKt.to(7, 109), TuplesKt.to(12, 111), TuplesKt.to(14, 111), TuplesKt.to(15, 110), TuplesKt.to(18, 112), TuplesKt.to(22, 113), TuplesKt.to(23, 113), TuplesKt.to(33, 108), TuplesKt.to(5, 107), TuplesKt.to(35, 115), TuplesKt.to(37, 116), TuplesKt.to(25, 118));

    private HyperKycError() {
    }

    public final int mapIfRequired(Integer errorCode) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
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
        String str2 = "mapIfRequired() called with: errorCode = " + errorCode;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "mapIfRequired() called with: errorCode = " + errorCode;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        if (errorCode != null && errorCode.intValue() == 117) {
            return 117;
        }
        if (errorCode == null) {
            return 104;
        }
        if (new IntRange(400, 600).contains(errorCode.intValue())) {
            return errorCode.intValue();
        }
        Integer num = hsHkErrorCodeMap.get(errorCode);
        if (num != null) {
            return num.intValue();
        }
        return 104;
    }
}
