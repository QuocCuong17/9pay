package co.hyperverge.hyperkyc.core.hv.models;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.model.UIConfig;
import com.google.gson.Gson;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: HSUIConfig.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "Lco/hyperverge/hypersnapsdk/model/UIConfig;", "()V", "uiConfigJSON", "Lorg/json/JSONObject;", "getBackgroundImageUrl", "", "getDynamicFormUIConfig", "Lco/hyperverge/hyperkyc/core/hv/models/DynamicFormUIConfig;", "moduleId", "componentId", "getModuleBackgroundImageUrl", "setUIConfigJSON", "", "jsonString", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HSUIConfig extends UIConfig {
    private JSONObject uiConfigJSON;

    public final String getBackgroundImageUrl() {
        JSONObject jSONObject = this.uiConfigJSON;
        if (jSONObject != null) {
            return jSONObject.optString("backgroundImage");
        }
        return null;
    }

    public final String getModuleBackgroundImageUrl(String moduleId) {
        Intrinsics.checkNotNullParameter(moduleId, "moduleId");
        JSONObject jSONObject = this.uiConfigJSON;
        JSONObject optJSONObject = jSONObject != null ? jSONObject.optJSONObject(moduleId) : null;
        String optString = optJSONObject != null ? optJSONObject.optString("backgroundImage") : null;
        String str = optString;
        return !(str == null || str.length() == 0) ? optString : getBackgroundImageUrl();
    }

    public final void setUIConfigJSON(String jsonString) {
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
        String str2 = "setUIConfigJSON() called with: jsonString = " + jsonString;
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
                    String str3 = "setUIConfigJSON() called with: jsonString = " + jsonString;
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
        if (jsonString != null) {
            this.uiConfigJSON = new JSONObject(jsonString);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0134, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final DynamicFormUIConfig getDynamicFormUIConfig(String moduleId, String componentId) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        DynamicFormUIConfig copy;
        String nullIfInvalidHexColor;
        String nullIfInvalidHexColor2;
        String nullIfInvalidHexColor3;
        String className2;
        Intrinsics.checkNotNullParameter(moduleId, "moduleId");
        Intrinsics.checkNotNullParameter(componentId, "componentId");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
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
        sb.append("getDynamicFormUIConfig() called");
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
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(3, str, "getDynamicFormUIConfig() called ");
                }
            }
        }
        JSONObject jSONObject = this.uiConfigJSON;
        JSONObject optJSONObject = jSONObject != null ? jSONObject.optJSONObject(moduleId) : null;
        JSONObject optJSONObject2 = optJSONObject != null ? optJSONObject.optJSONObject(componentId) : null;
        DynamicFormUIConfig dynamicFormUIConfig = optJSONObject2 != null ? (DynamicFormUIConfig) new Gson().fromJson(optJSONObject2.toString(), DynamicFormUIConfig.class) : null;
        if (dynamicFormUIConfig == null) {
            return null;
        }
        String color = dynamicFormUIConfig.getColor();
        String argb = (color == null || (nullIfInvalidHexColor3 = UIExtsKt.nullIfInvalidHexColor(color)) == null) ? null : UIExtsKt.toArgb(nullIfInvalidHexColor3);
        String borderColor = dynamicFormUIConfig.getBorderColor();
        String argb2 = (borderColor == null || (nullIfInvalidHexColor2 = UIExtsKt.nullIfInvalidHexColor(borderColor)) == null) ? null : UIExtsKt.toArgb(nullIfInvalidHexColor2);
        String backgroundColor = dynamicFormUIConfig.getBackgroundColor();
        if (backgroundColor != null && (nullIfInvalidHexColor = UIExtsKt.nullIfInvalidHexColor(backgroundColor)) != null) {
            str2 = UIExtsKt.toArgb(nullIfInvalidHexColor);
        }
        copy = dynamicFormUIConfig.copy((r42 & 1) != 0 ? dynamicFormUIConfig.font : null, (r42 & 2) != 0 ? dynamicFormUIConfig.fontSize : null, (r42 & 4) != 0 ? dynamicFormUIConfig.fontWeight : null, (r42 & 8) != 0 ? dynamicFormUIConfig.color : argb, (r42 & 16) != 0 ? dynamicFormUIConfig.selectedTextColor : null, (r42 & 32) != 0 ? dynamicFormUIConfig.backgroundColor : str2, (r42 & 64) != 0 ? dynamicFormUIConfig.borderRadius : null, (r42 & 128) != 0 ? dynamicFormUIConfig.borderColor : argb2, (r42 & 256) != 0 ? dynamicFormUIConfig.selectedBorderColor : null, (r42 & 512) != 0 ? dynamicFormUIConfig.disabledBorderColor : null, (r42 & 1024) != 0 ? dynamicFormUIConfig.selectedBackgroundColor : null, (r42 & 2048) != 0 ? dynamicFormUIConfig.circleBorderColor : null, (r42 & 4096) != 0 ? dynamicFormUIConfig.alignment : null, (r42 & 8192) != 0 ? dynamicFormUIConfig.icons : null, (r42 & 16384) != 0 ? dynamicFormUIConfig.marginTop : null, (r42 & 32768) != 0 ? dynamicFormUIConfig.marginBottom : null, (r42 & 65536) != 0 ? dynamicFormUIConfig.marginLeft : null, (r42 & 131072) != 0 ? dynamicFormUIConfig.marginRight : null, (r42 & 262144) != 0 ? dynamicFormUIConfig.paddingTop : null, (r42 & 524288) != 0 ? dynamicFormUIConfig.paddingBottom : null, (r42 & 1048576) != 0 ? dynamicFormUIConfig.paddingLeft : null, (r42 & 2097152) != 0 ? dynamicFormUIConfig.paddingRight : null, (r42 & 4194304) != 0 ? dynamicFormUIConfig.lineHeight : null, (r42 & 8388608) != 0 ? dynamicFormUIConfig.charSpacing : null);
        return copy;
    }
}
