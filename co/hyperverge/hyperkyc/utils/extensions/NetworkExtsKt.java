package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import co.hyperverge.hyperkyc.data.network.SignatureUtils;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.service.security.HVSecurityException;
import io.flutter.plugins.firebase.database.Constants;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NetworkExts.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a'\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0000¢\u0006\u0002\u0010\u0005\u001a\f\u0010\u0006\u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\u0016\u0010\t\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0000\u001a\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0002*\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0000¨\u0006\u000e"}, d2 = {"extractFromAccessToken", "T", "", "accessTokenPart", "Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/utils/extensions/AccessTokenPart;)Ljava/lang/Object;", "getAWSSignature", "Lokhttp3/Response;", "getAWSSignatureVerificationKey", "getJsonValue", Constants.PATH, "verifySignature", "key", "signature", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkExtsKt {
    public static final /* synthetic */ Object extractFromAccessToken(String str, AccessTokenPart accessTokenPart) {
        Object m1202constructorimpl;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(accessTokenPart, "accessTokenPart");
        try {
            Result.Companion companion = Result.INSTANCE;
            byte[] decode = Base64.decode((String) StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null).get(1), 0);
            Intrinsics.checkNotNullExpressionValue(decode, "decode(\n                …DEFAULT\n                )");
            Object obj = new JSONObject(new String(decode, Charsets.UTF_8)).get(accessTokenPart.getKey());
            if (obj == null) {
                obj = null;
            }
            m1202constructorimpl = Result.m1202constructorimpl(obj);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
            return null;
        }
        return m1202constructorimpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x016a A[Catch: all -> 0x008f, TryCatch #5 {all -> 0x008f, blocks: (B:114:0x0082, B:117:0x0087, B:18:0x0093, B:22:0x00af, B:64:0x00fb, B:67:0x0102, B:69:0x010a, B:74:0x011d, B:76:0x0133, B:83:0x0159, B:85:0x016a, B:86:0x0171, B:88:0x0177, B:91:0x017c, B:92:0x0183, B:95:0x019b, B:100:0x0149, B:102:0x014f, B:112:0x00f1, B:24:0x01b3, B:25:0x01d1, B:27:0x01d7, B:29:0x01df, B:30:0x01e2, B:32:0x01ec, B:63:0x00c9), top: B:113:0x0082, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x019a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ String getJsonValue(String str, String path) {
        Object obj;
        Object obj2;
        String canonicalName;
        Object m1202constructorimpl;
        Matcher matcher;
        String str2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            Result.Companion companion = Result.INSTANCE;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = str.getClass();
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
            int i = 0;
            if (canonicalName.length() > 23) {
                try {
                    if (Build.VERSION.SDK_INT < 26) {
                        canonicalName = canonicalName.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                } catch (Throwable th) {
                    th = th;
                    obj = null;
                    Result.Companion companion3 = Result.INSTANCE;
                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    return (String) (!Result.m1208isFailureimpl(obj2) ? obj : obj2);
                }
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str4 = str + ".getJsonValue() called";
            String str5 = "null ";
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
            sb.append(' ');
            sb.append("");
            companion2.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    Result.Companion companion5 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    try {
                        if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                                try {
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        str3 = substringAfterLast$default;
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
                                        str2 = str + ".getJsonValue() called";
                                        if (str2 == null) {
                                            str5 = str2;
                                        }
                                        sb2.append(str5);
                                        sb2.append(' ');
                                        sb2.append("");
                                        Log.println(3, str3, sb2.toString());
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    obj = null;
                                    Result.Companion companion32 = Result.INSTANCE;
                                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                    return (String) (!Result.m1208isFailureimpl(obj2) ? obj : obj2);
                                }
                            }
                            Class<?> cls2 = str.getClass();
                            String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 != null) {
                                str3 = canonicalName2;
                            }
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                            if (matcher.find()) {
                            }
                            if (str3.length() > 23) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb22 = new StringBuilder();
                            str2 = str + ".getJsonValue() called";
                            if (str2 == null) {
                            }
                            sb22.append(str5);
                            sb22.append(' ');
                            sb22.append("");
                            Log.println(3, str3, sb22.toString());
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        obj = null;
                    }
                }
            }
            JSONObject jSONObject = new JSONObject(str);
            String str6 = null;
            for (Object obj3 : StringsKt.split$default((CharSequence) path, new String[]{"."}, false, 0, 6, (Object) null)) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str7 = (String) obj3;
                if (i < r1.size() - 1) {
                    if (new Regex("[\\[\\]]").containsMatchIn(str7)) {
                        obj = null;
                        try {
                            int parseInt = Integer.parseInt(StringsKt.substringBefore$default(StringsKt.substringAfter$default(str7, "[", (String) null, 2, (Object) null), "]", (String) null, 2, (Object) null));
                            JSONArray optJSONArray = jSONObject.optJSONArray(str7);
                            jSONObject = optJSONArray != null ? optJSONArray.optJSONObject(parseInt) : null;
                        } catch (Throwable th5) {
                            th = th5;
                            Result.Companion companion322 = Result.INSTANCE;
                            obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            return (String) (!Result.m1208isFailureimpl(obj2) ? obj : obj2);
                        }
                    } else {
                        jSONObject = jSONObject.optJSONObject(str7);
                    }
                    Intrinsics.checkNotNull(jSONObject);
                } else {
                    str6 = jSONObject.optString(str7);
                }
                i = i2;
            }
            obj = null;
            obj2 = Result.m1202constructorimpl(str6);
        } catch (Throwable th6) {
            th = th6;
            obj = null;
        }
        return (String) (!Result.m1208isFailureimpl(obj2) ? obj : obj2);
    }

    public static final /* synthetic */ String verifySignature(Response response, String key, String signature) throws HVSecurityException {
        Intrinsics.checkNotNullParameter(response, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(signature, "signature");
        ResponseBody body = response.body();
        String string = body != null ? body.string() : null;
        if (string != null ? SignatureUtils.INSTANCE.isSignatureVerified(key, string, signature) : false) {
            return string;
        }
        throw new HVSecurityException("112");
    }

    public static final /* synthetic */ String getAWSSignature(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        String str = response.headers().get("x-amz-meta-x-hv-workflow-id");
        return str == null ? "" : str;
    }

    public static final /* synthetic */ String getAWSSignatureVerificationKey(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        return URLExtsKt.urlDecode$default(URLExtsKt.removePathSlashPrefix(response.request().url().url()), null, 1, null);
    }
}
