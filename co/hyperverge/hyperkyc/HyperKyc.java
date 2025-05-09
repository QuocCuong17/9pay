package co.hyperverge.hyperkyc;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfigKt;
import co.hyperverge.hyperkyc.data.models.result.HyperKycResult;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.webCore.ui.HKConfigLoadingActivity;
import co.hyperverge.hyperlogger.HyperLogger;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import io.sentry.protocol.SentryStackFrame;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HyperKyc.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0016H\u0007J\b\u0010\u0017\u001a\u00020\u0004H\u0007J \u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0007J4\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0003R\u0010\u0010\u0003\u001a\u00020\u00048@X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048@X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048@X\u0080T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u00020\b8@X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00048@X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048@X\u0080T¢\u0006\u0002\n\u0000R8\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000ej\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u000f8@X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0012¨\u0006!"}, d2 = {"Lco/hyperverge/hyperkyc/HyperKyc;", "", "()V", "CONFIGS_CACHE_DIR", "", "FORM_WEB_VIEW_URL", "INPUTS_CACHE_DIR", "INTENT_BUNDLE_SIZE_LIMIT", "", "getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release", "()I", "RESULTS_CACHE_DIR", "TEMP_CACHE_DIR", "metadataMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getMetadataMap$hyperkyc_release$annotations", "getMetadataMap$hyperkyc_release", "()Ljava/util/HashMap;", "addMetadata", "", TtmlNode.TAG_METADATA, "", "createUniqueId", "prefetch", "context", "Landroid/content/Context;", "appId", "workflowId", "languageCode", "shouldPreLoadWebSDK", "", "Contract", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperKyc {
    public static final String CONFIGS_CACHE_DIR = "hv/temp/configs/";
    public static final String FORM_WEB_VIEW_URL = "file:///android_asset/web_form/native.html";
    public static final String INPUTS_CACHE_DIR = "hv/temp/inputs/";
    public static final String RESULTS_CACHE_DIR = "hv/temp/results/";
    public static final String TEMP_CACHE_DIR = "hv/temp/";
    public static final HyperKyc INSTANCE = new HyperKyc();
    private static final HashMap<String, String> metadataMap = new HashMap<>();
    private static final int INTENT_BUNDLE_SIZE_LIMIT = AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND;

    @Deprecated(message = "Moved to HyperKycConfig")
    public static /* synthetic */ void getMetadataMap$hyperkyc_release$annotations() {
    }

    private HyperKyc() {
    }

    public final /* synthetic */ HashMap getMetadataMap$hyperkyc_release() {
        return metadataMap;
    }

    @Deprecated(message = "Moved to HyperKycConfig")
    @JvmStatic
    public static final void addMetadata(Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        HashMap<String, String> hashMap = metadataMap;
        if (!hashMap.containsKey(SentryStackFrame.JsonKeys.PACKAGE)) {
            hashMap.put(SentryStackFrame.JsonKeys.PACKAGE, "co.hyperverge");
        }
        hashMap.putAll(metadata);
    }

    @JvmStatic
    public static final String createUniqueId() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        return uuid;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x015a, code lost:
    
        if (r0 != null) goto L57;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01c3  */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void prefetch(Context context, String appId, String workflowId) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str;
        String str2;
        String str3;
        Matcher matcher;
        String str4;
        Class<?> cls2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        HyperKyc hyperKyc = INSTANCE;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (hyperKyc == null || (cls = hyperKyc.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str5 = "prefetch() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId;
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
                    str2 = (hyperKyc == null || (cls2 = hyperKyc.getClass()) == null) ? str : cls2.getCanonicalName();
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
                        str4 = "prefetch() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId;
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
                    str4 = "prefetch() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId;
                    if (str4 == null) {
                    }
                    sb22.append(str4);
                    sb22.append(' ');
                    sb22.append("");
                    Log.println(3, str3, sb22.toString());
                }
            }
        }
        NetworkRepo.prefetchInternal$hyperkyc_release$default(NetworkRepo.INSTANCE, context, appId, workflowId, null, false, false, 56, null);
    }

    static /* synthetic */ void prefetch$default(Context context, String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = "en";
        }
        if ((i & 16) != 0) {
            z = true;
        }
        prefetch(context, str, str2, str3, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0159, code lost:
    
        if (r0 != null) goto L57;
     */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void prefetch(Context context, String appId, String workflowId, String languageCode, boolean shouldPreLoadWebSDK) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str;
        String str2;
        Class<?> cls2;
        String className;
        String className2;
        HyperKyc hyperKyc = INSTANCE;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (hyperKyc == null || (cls = hyperKyc.getClass()) == null) ? null : cls.getCanonicalName();
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
        String str3 = "prefetch() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId + ", languageCode = " + languageCode;
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
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
                    str2 = (hyperKyc == null || (cls2 = hyperKyc.getClass()) == null) ? str : cls2.getCanonicalName();
                    if (str2 == null) {
                        str2 = "N/A";
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "prefetch() called with: context = " + context + ", appId = " + appId + ", workflowId = " + workflowId + ", languageCode = " + languageCode;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                    NetworkRepo.prefetchInternal$hyperkyc_release$default(NetworkRepo.INSTANCE, context, appId, workflowId, languageCode, shouldPreLoadWebSDK, false, 32, null);
                }
            }
        }
        NetworkRepo.prefetchInternal$hyperkyc_release$default(NetworkRepo.INSTANCE, context, appId, workflowId, languageCode, shouldPreLoadWebSDK, false, 32, null);
    }

    public final /* synthetic */ int getINTENT_BUNDLE_SIZE_LIMIT$hyperkyc_release() {
        return INTENT_BUNDLE_SIZE_LIMIT;
    }

    /* compiled from: HyperKyc.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u001a\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lco/hyperverge/hyperkyc/HyperKyc$Contract;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycResult;", "()V", "ctx", "Landroid/content/Context;", "transactionId", "", "createIntent", "Landroid/content/Intent;", "context", "input", "parseResult", "resultCode", "", SDKConstants.PARAM_INTENT, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Contract extends ActivityResultContract<HyperKycConfig, HyperKycResult> {
        private Context ctx;
        private String transactionId = "";

        /* JADX WARN: Code restructure failed: missing block: B:37:0x0148, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0158, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x015c, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x016b, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x016d, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x017a, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0180, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0183, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x018a, code lost:
        
            r0 = new java.lang.StringBuilder();
            r4 = "createIntent() called with: context = " + r17 + ", input = " + r18.getTransactionId$hyperkyc_release();
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x01a8, code lost:
        
            if (r4 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x01ab, code lost:
        
            r10 = r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x01ac, code lost:
        
            r0.append(r10);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x015b, code lost:
        
            r8 = r0;
         */
        @Override // androidx.activity.result.contract.ActivityResultContract
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Intent createIntent(Context context, HyperKycConfig input) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(input, "input");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            String str3 = "N/A";
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
            String str4 = "createIntent() called with: context = " + context + ", input = " + input.getTransactionId$hyperkyc_release();
            String str5 = "null ";
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
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
                    }
                }
            }
            this.ctx = context;
            this.transactionId = input.getTransactionId$hyperkyc_release();
            Intent intent = new Intent(context, (Class<?>) HKConfigLoadingActivity.class);
            intent.setFlags(131072);
            HyperKycConfigKt.replaceLargeValuesWithFiles(input, context);
            intent.putExtra(HyperKycConfig.ARG_KEY, input);
            return intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0a4f, code lost:
        
            if (r0 != null) goto L447;
         */
        /* JADX WARN: Code restructure failed: missing block: B:326:0x05d3, code lost:
        
            if (r12 != null) goto L243;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x095b, code lost:
        
            if (r9 != null) goto L405;
         */
        /* JADX WARN: Removed duplicated region for block: B:144:0x0ad3  */
        /* JADX WARN: Removed duplicated region for block: B:147:0x0af3  */
        /* JADX WARN: Removed duplicated region for block: B:155:0x0aca  */
        /* JADX WARN: Removed duplicated region for block: B:162:0x0803 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:201:0x0791  */
        /* JADX WARN: Removed duplicated region for block: B:203:0x0799  */
        /* JADX WARN: Removed duplicated region for block: B:204:0x079c  */
        /* JADX WARN: Removed duplicated region for block: B:205:0x0796  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x01cd  */
        /* JADX WARN: Removed duplicated region for block: B:321:0x05a0  */
        /* JADX WARN: Removed duplicated region for block: B:387:0x074b  */
        /* JADX WARN: Removed duplicated region for block: B:395:0x0743  */
        /* JADX WARN: Removed duplicated region for block: B:406:0x0acf  */
        /* JADX WARN: Removed duplicated region for block: B:414:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:417:0x011a  */
        /* JADX WARN: Removed duplicated region for block: B:443:0x01c9  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x07ae  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x07e7  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x08f2 A[Catch: all -> 0x0913, TryCatch #1 {all -> 0x0913, blocks: (B:69:0x08ee, B:71:0x08f2, B:72:0x08f8, B:74:0x0907, B:75:0x090a), top: B:68:0x08ee }] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0907 A[Catch: all -> 0x0913, TryCatch #1 {all -> 0x0913, blocks: (B:69:0x08ee, B:71:0x08f2, B:72:0x08f8, B:74:0x0907, B:75:0x090a), top: B:68:0x08ee }] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0928  */
        @Override // androidx.activity.result.contract.ActivityResultContract
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public HyperKycResult parseResult(int resultCode, Intent intent) {
            String canonicalName;
            String str;
            String str2;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String canonicalName2;
            String className;
            HyperKycResult hyperKycResult;
            Object m1202constructorimpl2;
            Throwable m1205exceptionOrNullimpl;
            Object obj;
            HyperKycResult hyperKycResult2;
            String str3;
            String str4;
            Object m1202constructorimpl3;
            String canonicalName3;
            String str5;
            String className2;
            String className3;
            HyperKycResult hyperKycResult3;
            StackTraceElement stackTraceElement;
            String str6;
            Matcher matcher;
            String str7;
            Object m1202constructorimpl4;
            String canonicalName4;
            String className4;
            Object m1202constructorimpl5;
            Throwable m1205exceptionOrNullimpl2;
            HyperKycResult hyperKycResult4;
            String str8;
            String str9;
            Object m1202constructorimpl6;
            String str10;
            String str11;
            String className5;
            String className6;
            File file;
            Context context;
            String className7;
            String str12;
            Object m1202constructorimpl7;
            String canonicalName5;
            String className8;
            String canonicalName6;
            Object m1202constructorimpl8;
            String canonicalName7;
            String className9;
            String className10;
            String className11;
            String className12;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement2 == null || (className12 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className12, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls = getClass();
                canonicalName = cls != null ? cls.getCanonicalName() : null;
                if (canonicalName == null) {
                    canonicalName = "N/A";
                }
            }
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
            String str13 = "";
            if (matcher2.find()) {
                canonicalName = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
            }
            Unit unit = Unit.INSTANCE;
            if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName = canonicalName.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(canonicalName);
            sb.append(" - ");
            String str14 = "parseResult() called with: resultCode = " + resultCode + ", intent = " + intent;
            if (str14 == null) {
                str14 = "null ";
            }
            sb.append(str14);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str = "N/A";
                str2 = "null ";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = "N/A";
                    str2 = "null ";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                        }
                        if (intent == null) {
                        }
                        if (hyperKycResult != null) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = "N/A";
                    str2 = "null ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    charSequence = "co.hyperverge";
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
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
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str15 = "parseResult() called with: resultCode = " + resultCode + ", intent = " + intent;
                        if (str15 == null) {
                            str15 = str2;
                        }
                        sb2.append(str15);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                }
            }
            if (intent == null) {
                if (intent.hasExtra(HyperKycResult.ARG_CACHE_FILE_PATH_KEY)) {
                    String stringExtra = intent.getStringExtra(HyperKycResult.ARG_CACHE_FILE_PATH_KEY);
                    Intrinsics.checkNotNull(stringExtra);
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement4 == null || (className11 = stackTraceElement4.getClassName()) == null || (str12 = StringsKt.substringAfterLast$default(className11, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = intent.getClass();
                        String canonicalName8 = cls3 != null ? cls3.getCanonicalName() : null;
                        str12 = canonicalName8 == null ? str : canonicalName8;
                    }
                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str12);
                    if (matcher4.find()) {
                        str12 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str12, "replaceAll(\"\")");
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (str12.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str12 = str12.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str12, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str12);
                    sb3.append(" - ");
                    String str16 = "parseResult: resultFilePath: " + stringExtra;
                    if (str16 == null) {
                        str16 = str2;
                    }
                    sb3.append(str16);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl7 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl7 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl7)) {
                            m1202constructorimpl7 = "";
                        }
                        String packageName3 = (String) m1202constructorimpl7;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement5 == null || (className8 = stackTraceElement5.getClassName()) == null || (canonicalName5 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls4 = intent.getClass();
                                    canonicalName5 = cls4 != null ? cls4.getCanonicalName() : null;
                                    if (canonicalName5 == null) {
                                        canonicalName5 = str;
                                    }
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName5);
                                if (matcher5.find()) {
                                    canonicalName5 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName5, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName5 = canonicalName5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str17 = "parseResult: resultFilePath: " + stringExtra;
                                if (str17 == null) {
                                    str17 = str2;
                                }
                                sb4.append(str17);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(4, canonicalName5, sb4.toString());
                            }
                        }
                    }
                    File file2 = new File(stringExtra);
                    if (file2.exists()) {
                        hyperKycResult2 = (HyperKycResult) new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create().fromJson(FilesKt.readText$default(file2, null, 1, null), HyperKycResult.class);
                    } else {
                        HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                        HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb5 = new StringBuilder();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement6 == null || (className10 = stackTraceElement6.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls5 = intent.getClass();
                            canonicalName6 = cls5 != null ? cls5.getCanonicalName() : null;
                            if (canonicalName6 == null) {
                                canonicalName6 = str;
                            }
                        }
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName6);
                        if (matcher6.find()) {
                            canonicalName6 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName6, "replaceAll(\"\")");
                        }
                        Unit unit5 = Unit.INSTANCE;
                        if (canonicalName6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName6 = canonicalName6.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName6, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb5.append(canonicalName6);
                        sb5.append(" - ");
                        String str18 = "parseResult: File not found at path: " + stringExtra;
                        if (str18 == null) {
                            str18 = str2;
                        }
                        sb5.append(str18);
                        sb5.append(' ');
                        sb5.append("");
                        companion7.log(level3, sb5.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl8 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        } catch (Throwable th4) {
                            Result.Companion companion9 = Result.INSTANCE;
                            m1202constructorimpl8 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl8)) {
                            m1202constructorimpl8 = "";
                        }
                        String packageName4 = (String) m1202constructorimpl8;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName4, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName4, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement7 == null || (className9 = stackTraceElement7.getClassName()) == null || (canonicalName7 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls6 = intent.getClass();
                                    canonicalName7 = cls6 != null ? cls6.getCanonicalName() : null;
                                    if (canonicalName7 == null) {
                                        canonicalName7 = str;
                                    }
                                }
                                Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName7);
                                if (matcher7.find()) {
                                    canonicalName7 = matcher7.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName7, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (canonicalName7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName7 = canonicalName7.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb6 = new StringBuilder();
                                String str19 = "parseResult: File not found at path: " + stringExtra;
                                if (str19 == null) {
                                    str19 = str2;
                                }
                                sb6.append(str19);
                                sb6.append(' ');
                                sb6.append("");
                                Log.println(6, canonicalName7, sb6.toString());
                            }
                        }
                        hyperKycResult2 = new HyperKycResult("error", this.transactionId, null, null, "File not found at path: " + stringExtra + ", HyperKycResult cannot be extracted", null, null, null, null, null, 1004, null);
                    }
                } else {
                    try {
                        Result.Companion companion10 = Result.INSTANCE;
                        try {
                            if (Build.VERSION.SDK_INT >= 33) {
                                hyperKycResult3 = (Parcelable) intent.getParcelableExtra(HyperKycResult.ARG_KEY, HyperKycResult.class);
                            } else {
                                Parcelable parcelableExtra = intent.getParcelableExtra(HyperKycResult.ARG_KEY);
                                if (parcelableExtra == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type co.hyperverge.hyperkyc.data.models.result.HyperKycResult");
                                }
                                hyperKycResult3 = (HyperKycResult) parcelableExtra;
                            }
                            m1202constructorimpl2 = Result.m1202constructorimpl(hyperKycResult3);
                        } catch (Throwable th5) {
                            th = th5;
                            Result.Companion companion11 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                            Object obj2 = m1202constructorimpl2;
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj2);
                            if (m1205exceptionOrNullimpl == null) {
                            }
                            if (Result.m1208isFailureimpl(obj)) {
                            }
                            Parcelable parcelable = (Parcelable) obj;
                            Intrinsics.checkNotNull(parcelable);
                            hyperKycResult2 = (HyperKycResult) parcelable;
                            HyperKycResult hyperKycResult5 = hyperKycResult2;
                            HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
                            HyperLogger companion12 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb7 = new StringBuilder();
                            StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace7, "Throwable().stackTrace");
                            stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                            if (stackTraceElement != null) {
                            }
                            Class<?> cls7 = intent.getClass();
                            if (cls7 == null) {
                            }
                            if (r2 != null) {
                            }
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                            if (matcher.find()) {
                            }
                            Unit unit7 = Unit.INSTANCE;
                            if (str6.length() > 23) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb7.append(str6);
                            sb7.append(" - ");
                            str7 = "parseResult: result: " + hyperKycResult5;
                            if (str7 == null) {
                            }
                            sb7.append(str7);
                            sb7.append(' ');
                            sb7.append("");
                            companion12.log(level4, sb7.toString());
                            if (!CoreExtsKt.isRelease()) {
                            }
                            Result.Companion companion13 = Result.INSTANCE;
                            try {
                                context = this.ctx;
                                if (context == null) {
                                }
                                file = new File(context.getFilesDir(), HyperKyc.TEMP_CACHE_DIR);
                                if (file.exists()) {
                                }
                                Unit unit8 = Unit.INSTANCE;
                                m1202constructorimpl5 = Result.m1202constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th6) {
                                th = th6;
                                Result.Companion companion14 = Result.INSTANCE;
                                m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                                if (m1205exceptionOrNullimpl2 != null) {
                                }
                                hyperKycResult = hyperKycResult4;
                                if (hyperKycResult != null) {
                                }
                            }
                            m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                            if (m1205exceptionOrNullimpl2 != null) {
                            }
                            hyperKycResult = hyperKycResult4;
                            if (hyperKycResult != null) {
                            }
                        }
                    } catch (Throwable th7) {
                        th = th7;
                    }
                    Object obj22 = m1202constructorimpl2;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj22);
                    if (m1205exceptionOrNullimpl == null) {
                        HyperLogger.Level level5 = HyperLogger.Level.ERROR;
                        HyperLogger companion15 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb8 = new StringBuilder();
                        StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace8, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                        if (stackTraceElement8 == null || (className3 = stackTraceElement8.getClassName()) == null) {
                            obj = obj22;
                        } else {
                            obj = obj22;
                            str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls8 = intent.getClass();
                        String canonicalName9 = cls8 != null ? cls8.getCanonicalName() : null;
                        str3 = canonicalName9 == null ? str : canonicalName9;
                        Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher8.find()) {
                            str3 = matcher8.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                        }
                        Unit unit9 = Unit.INSTANCE;
                        if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb8.append(str3);
                        sb8.append(" - ");
                        sb8.append("getParcelable: failed for key hyperKycResult");
                        sb8.append(' ');
                        String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                        if (localizedMessage != null) {
                            str4 = '\n' + localizedMessage;
                        } else {
                            str4 = "";
                        }
                        sb8.append(str4);
                        companion15.log(level5, sb8.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion16 = Result.INSTANCE;
                            Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke4, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                        } catch (Throwable th8) {
                            Result.Companion companion17 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName5 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName5, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName5, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace9, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                                if (stackTraceElement9 == null || (className2 = stackTraceElement9.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    Class<?> cls9 = intent.getClass();
                                    canonicalName3 = cls9 != null ? cls9.getCanonicalName() : null;
                                    if (canonicalName3 == null) {
                                        canonicalName3 = str;
                                    }
                                }
                                Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                                if (matcher9.find()) {
                                    canonicalName3 = matcher9.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                                }
                                Unit unit10 = Unit.INSTANCE;
                                if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName3 = canonicalName3.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb9 = new StringBuilder();
                                sb9.append("getParcelable: failed for key hyperKycResult");
                                sb9.append(' ');
                                String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                if (localizedMessage2 != null) {
                                    str5 = '\n' + localizedMessage2;
                                } else {
                                    str5 = "";
                                }
                                sb9.append(str5);
                                Log.println(6, canonicalName3, sb9.toString());
                            }
                        }
                    } else {
                        obj = obj22;
                    }
                    if (Result.m1208isFailureimpl(obj)) {
                        obj = null;
                    }
                    Parcelable parcelable2 = (Parcelable) obj;
                    Intrinsics.checkNotNull(parcelable2);
                    hyperKycResult2 = (HyperKycResult) parcelable2;
                }
                HyperKycResult hyperKycResult52 = hyperKycResult2;
                HyperLogger.Level level42 = HyperLogger.Level.DEBUG;
                HyperLogger companion122 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb72 = new StringBuilder();
                StackTraceElement[] stackTrace72 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace72, "Throwable().stackTrace");
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace72);
                if (stackTraceElement != null || (className7 = stackTraceElement.getClassName()) == null || (str6 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls72 = intent.getClass();
                    String canonicalName10 = cls72 == null ? cls72.getCanonicalName() : null;
                    str6 = canonicalName10 != null ? str : canonicalName10;
                }
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                if (matcher.find()) {
                    str6 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                }
                Unit unit72 = Unit.INSTANCE;
                if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str6 = str6.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb72.append(str6);
                sb72.append(" - ");
                str7 = "parseResult: result: " + hyperKycResult52;
                if (str7 == null) {
                    str7 = str2;
                }
                sb72.append(str7);
                sb72.append(' ');
                sb72.append("");
                companion122.log(level42, sb72.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion18 = Result.INSTANCE;
                        Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke5, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                    } catch (Throwable th9) {
                        Result.Companion companion19 = Result.INSTANCE;
                        m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th9));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                        m1202constructorimpl4 = "";
                    }
                    String packageName6 = (String) m1202constructorimpl4;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName6, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName6, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace10, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                            if (stackTraceElement10 == null || (className4 = stackTraceElement10.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls10 = intent.getClass();
                                canonicalName4 = cls10 != null ? cls10.getCanonicalName() : null;
                                if (canonicalName4 == null) {
                                    canonicalName4 = str;
                                }
                            }
                            Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                            if (matcher10.find()) {
                                canonicalName4 = matcher10.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                            }
                            Unit unit11 = Unit.INSTANCE;
                            if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName4 = canonicalName4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb10 = new StringBuilder();
                            String str20 = "parseResult: result: " + hyperKycResult52;
                            sb10.append(str20 == null ? str2 : str20);
                            sb10.append(' ');
                            sb10.append("");
                            Log.println(4, canonicalName4, sb10.toString());
                        }
                    }
                }
                try {
                    Result.Companion companion132 = Result.INSTANCE;
                    context = this.ctx;
                    if (context == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("ctx");
                        context = null;
                    }
                    file = new File(context.getFilesDir(), HyperKyc.TEMP_CACHE_DIR);
                    if (file.exists()) {
                        FilesKt.deleteRecursively(file);
                    }
                    Unit unit82 = Unit.INSTANCE;
                    m1202constructorimpl5 = Result.m1202constructorimpl(Unit.INSTANCE);
                } catch (Throwable th10) {
                    th = th10;
                }
                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                if (m1205exceptionOrNullimpl2 != null) {
                    HyperLogger.Level level6 = HyperLogger.Level.ERROR;
                    HyperLogger companion20 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb11 = new StringBuilder();
                    StackTraceElement[] stackTrace11 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace11, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement11 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace11);
                    if (stackTraceElement11 == null || (className6 = stackTraceElement11.getClassName()) == null) {
                        hyperKycResult4 = hyperKycResult52;
                    } else {
                        hyperKycResult4 = hyperKycResult52;
                        str8 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    Class<?> cls11 = intent.getClass();
                    String canonicalName11 = cls11 != null ? cls11.getCanonicalName() : null;
                    str8 = canonicalName11 == null ? str : canonicalName11;
                    Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str8);
                    if (matcher11.find()) {
                        str8 = matcher11.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str8, "replaceAll(\"\")");
                    }
                    Unit unit12 = Unit.INSTANCE;
                    if (str8.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str8 = str8.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str8, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb11.append(str8);
                    sb11.append(" - ");
                    sb11.append("finish() error deleting TEMP_CACHE_DIR: hv/temp/");
                    sb11.append(' ');
                    String localizedMessage3 = m1205exceptionOrNullimpl2 != null ? m1205exceptionOrNullimpl2.getLocalizedMessage() : null;
                    if (localizedMessage3 != null) {
                        str9 = '\n' + localizedMessage3;
                    } else {
                        str9 = "";
                    }
                    sb11.append(str9);
                    companion20.log(level6, sb11.toString());
                    CoreExtsKt.isRelease();
                    try {
                        Result.Companion companion21 = Result.INSTANCE;
                        Object invoke6 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke6, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke6).getPackageName());
                    } catch (Throwable th11) {
                        Result.Companion companion22 = Result.INSTANCE;
                        m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th11));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                        m1202constructorimpl6 = "";
                    }
                    String packageName7 = (String) m1202constructorimpl6;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName7, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName7, charSequence, false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace12 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace12, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement12 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace12);
                            if (stackTraceElement12 == null || (className5 = stackTraceElement12.getClassName()) == null) {
                                str10 = null;
                            } else {
                                str10 = null;
                                str11 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls12 = intent.getClass();
                            str11 = cls12 != null ? cls12.getCanonicalName() : str10;
                            if (str11 == null) {
                                str11 = str;
                            }
                            Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str11);
                            if (matcher12.find()) {
                                str11 = matcher12.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str11, "replaceAll(\"\")");
                            }
                            Unit unit13 = Unit.INSTANCE;
                            if (str11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str11 = str11.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb12 = new StringBuilder();
                            sb12.append("finish() error deleting TEMP_CACHE_DIR: hv/temp/");
                            sb12.append(' ');
                            String localizedMessage4 = m1205exceptionOrNullimpl2 != null ? m1205exceptionOrNullimpl2.getLocalizedMessage() : str10;
                            if (localizedMessage4 != null) {
                                str13 = '\n' + localizedMessage4;
                            }
                            sb12.append(str13);
                            Log.println(6, str11, sb12.toString());
                        }
                    }
                } else {
                    hyperKycResult4 = hyperKycResult52;
                }
                hyperKycResult = hyperKycResult4;
            } else {
                hyperKycResult = null;
            }
            return hyperKycResult != null ? new HyperKycResult("error", this.transactionId, null, null, null, null, null, null, null, null, 1020, null) : hyperKycResult;
        }
    }
}
