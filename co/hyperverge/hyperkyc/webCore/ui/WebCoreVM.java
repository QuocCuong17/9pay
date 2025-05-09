package co.hyperverge.hyperkyc.webCore.ui;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowAPIHeaders;
import co.hyperverge.hyperkyc.utils.HSStateHandler;
import co.hyperverge.hyperkyc.utils.extensions.ContextExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.utils.InternalToolUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.gson.Gson;
import io.sentry.protocol.App;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: WebCoreVM.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 H2\u00020\u0001:\u0001HB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u00106\u001a\u00020(H\u0000¢\u0006\u0002\b7J\u0014\u00108\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020(09H\u0002J\u0013\u0010:\u001a\u00020(H\u0080@ø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001d\u0010=\u001a\u00020(2\u0006\u0010>\u001a\u00020?2\u0006\u0010-\u001a\u00020.H\u0000¢\u0006\u0002\b@J\u0015\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020CH\u0000¢\u0006\u0002\bDJ\r\u0010E\u001a\u00020FH\u0000¢\u0006\u0002\bGR\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0011\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u001dX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u0010\u001a\u0004\b$\u0010%R\u001a\u0010'\u001a\u00020(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006I"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/ui/WebCoreVM;", "Landroidx/lifecycle/AndroidViewModel;", App.TYPE, "Landroid/app/Application;", "(Landroid/app/Application;)V", "allowNativeBackPress", "", "getAllowNativeBackPress$hyperkyc_release", "()Z", "setAllowNativeBackPress$hyperkyc_release", "(Z)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "gson", "Lcom/google/gson/Gson;", "getGson$hyperkyc_release", "()Lcom/google/gson/Gson;", "setGson$hyperkyc_release", "(Lcom/google/gson/Gson;)V", "hsStateHandler", "Lco/hyperverge/hyperkyc/utils/HSStateHandler;", "getHsStateHandler$hyperkyc_release", "()Lco/hyperverge/hyperkyc/utils/HSStateHandler;", "hsStateHandler$delegate", HyperKycConfig.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "getHyperKycConfig$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "setHyperKycConfig$hyperkyc_release", "(Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;)V", "hyperSnapResultStateDir", "Ljava/io/File;", "getHyperSnapResultStateDir", "()Ljava/io/File;", "hyperSnapResultStateDir$delegate", "journeyId", "", "getJourneyId$hyperkyc_release", "()Ljava/lang/String;", "setJourneyId$hyperkyc_release", "(Ljava/lang/String;)V", HyperKycConfig.ARG_REMOTE_CONFIG, "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "getRemoteConfig$hyperkyc_release", "()Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "setRemoteConfig$hyperkyc_release", "(Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;)V", "secure", "getSecure$hyperkyc_release", "setSecure$hyperkyc_release", "backPressedJS", "backPressedJS$hyperkyc_release", "getDefaultHeaders", "", "initWebSDKJS", "initWebSDKJS$hyperkyc_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadHtmlFromAssets", "assets", "Landroid/content/res/AssetManager;", "loadHtmlFromAssets$hyperkyc_release", "sendNativeModuleDataJS", "webCoreNativeResponse", "Lco/hyperverge/hyperkyc/webCore/models/WebCoreNativeResponse;", "sendNativeModuleDataJS$hyperkyc_release", "setAnalyticsCommonProperties", "", "setAnalyticsCommonProperties$hyperkyc_release", "Companion", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WebCoreVM extends AndroidViewModel {
    public static final String ASSET_URL = "https://android_asset/web_core";
    public static final String CUSTOM_SCHEME = "https://hv-web-core/files/";
    public static final String WEB_CORE_VERSION = "8.10.1";
    private boolean allowNativeBackPress;

    /* renamed from: context$delegate, reason: from kotlin metadata */
    private final Lazy context;
    private Gson gson;

    /* renamed from: hsStateHandler$delegate, reason: from kotlin metadata */
    private final Lazy hsStateHandler;
    public HyperKycConfig hyperKycConfig;

    /* renamed from: hyperSnapResultStateDir$delegate, reason: from kotlin metadata */
    private final Lazy hyperSnapResultStateDir;
    private String journeyId;
    private HSRemoteConfig remoteConfig;
    private boolean secure;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebCoreVM(Application app) {
        super(app);
        Intrinsics.checkNotNullParameter(app, "app");
        this.context = LazyKt.lazy(new Function0<Context>() { // from class: co.hyperverge.hyperkyc.webCore.ui.WebCoreVM$context$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Context invoke() {
                Application application = WebCoreVM.this.getApplication();
                Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
                return application;
            }
        });
        this.gson = new Gson();
        this.hyperSnapResultStateDir = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.webCore.ui.WebCoreVM$hyperSnapResultStateDir$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                Context context;
                StringBuilder sb = new StringBuilder();
                context = WebCoreVM.this.getContext();
                sb.append(context.getFilesDir().getAbsolutePath());
                sb.append("/hv/hyperSnapResultState/");
                File file = new File(sb.toString());
                if (!file.exists()) {
                    file.mkdirs();
                }
                return file;
            }
        });
        this.hsStateHandler = LazyKt.lazy(new Function0<HSStateHandler>() { // from class: co.hyperverge.hyperkyc.webCore.ui.WebCoreVM$hsStateHandler$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HSStateHandler invoke() {
                File hyperSnapResultStateDir;
                hyperSnapResultStateDir = WebCoreVM.this.getHyperSnapResultStateDir();
                return new HSStateHandler(hyperSnapResultStateDir);
            }
        });
        this.remoteConfig = new HSRemoteConfig(false, false, null, 7, null);
        this.journeyId = "";
        this.allowNativeBackPress = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        return (Context) this.context.getValue();
    }

    public final HyperKycConfig getHyperKycConfig$hyperkyc_release() {
        HyperKycConfig hyperKycConfig = this.hyperKycConfig;
        if (hyperKycConfig != null) {
            return hyperKycConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException(HyperKycConfig.ARG_KEY);
        return null;
    }

    public final void setHyperKycConfig$hyperkyc_release(HyperKycConfig hyperKycConfig) {
        Intrinsics.checkNotNullParameter(hyperKycConfig, "<set-?>");
        this.hyperKycConfig = hyperKycConfig;
    }

    /* renamed from: getGson$hyperkyc_release, reason: from getter */
    public final Gson getGson() {
        return this.gson;
    }

    public final void setGson$hyperkyc_release(Gson gson) {
        Intrinsics.checkNotNullParameter(gson, "<set-?>");
        this.gson = gson;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getHyperSnapResultStateDir() {
        return (File) this.hyperSnapResultStateDir.getValue();
    }

    public final HSStateHandler getHsStateHandler$hyperkyc_release() {
        return (HSStateHandler) this.hsStateHandler.getValue();
    }

    /* renamed from: getRemoteConfig$hyperkyc_release, reason: from getter */
    public final HSRemoteConfig getRemoteConfig() {
        return this.remoteConfig;
    }

    public final void setRemoteConfig$hyperkyc_release(HSRemoteConfig hSRemoteConfig) {
        Intrinsics.checkNotNullParameter(hSRemoteConfig, "<set-?>");
        this.remoteConfig = hSRemoteConfig;
    }

    /* renamed from: getJourneyId$hyperkyc_release, reason: from getter */
    public final String getJourneyId() {
        return this.journeyId;
    }

    public final void setJourneyId$hyperkyc_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.journeyId = str;
    }

    /* renamed from: getSecure$hyperkyc_release, reason: from getter */
    public final boolean getSecure() {
        return this.secure;
    }

    public final void setSecure$hyperkyc_release(boolean z) {
        this.secure = z;
    }

    /* renamed from: getAllowNativeBackPress$hyperkyc_release, reason: from getter */
    public final boolean getAllowNativeBackPress() {
        return this.allowNativeBackPress;
    }

    public final void setAllowNativeBackPress$hyperkyc_release(boolean z) {
        this.allowNativeBackPress = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01cc, code lost:
    
        if (((java.lang.String) r0.element).length() <= 23) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01d0, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01d3, code lost:
    
        r0 = ((java.lang.String) r0.element).substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01e3, code lost:
    
        android.util.Log.println(3, r0, "initWebSDKJS() called ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01df, code lost:
    
        r0 = (java.lang.String) r0.element;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01a6, code lost:
    
        r13 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0193, code lost:
    
        if (r8 != null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01a3, code lost:
    
        if (r8 == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01a7, code lost:
    
        r0.element = r13;
        r8 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher((java.lang.CharSequence) r0.element);
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01b9, code lost:
    
        if (r8.find() == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01bb, code lost:
    
        r8 = r8.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
        r0.element = r8;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Type inference failed for: r11v10, types: [T] */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2, types: [T] */
    /* JADX WARN: Type inference failed for: r5v38, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v16, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x02d7 -> B:10:0x02dd). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x02e3 -> B:13:0x02e5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initWebSDKJS$hyperkyc_release(Continuation<? super String> continuation) {
        WebCoreVM$initWebSDKJS$1 webCoreVM$initWebSDKJS$1;
        WebCoreVM webCoreVM;
        int i;
        ?? canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        String str3;
        String className;
        String str4;
        String str5;
        WebCoreVM webCoreVM2;
        String str6;
        String str7;
        Iterator it;
        Map map;
        String className2;
        String uniqueId;
        String str8;
        String defaultLangCode;
        String str9;
        if (continuation instanceof WebCoreVM$initWebSDKJS$1) {
            webCoreVM$initWebSDKJS$1 = (WebCoreVM$initWebSDKJS$1) continuation;
            if ((webCoreVM$initWebSDKJS$1.label & Integer.MIN_VALUE) != 0) {
                webCoreVM$initWebSDKJS$1.label -= Integer.MIN_VALUE;
                webCoreVM = this;
                Object obj = webCoreVM$initWebSDKJS$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = webCoreVM$initWebSDKJS$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    ?? r13 = "N/A";
                    if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        Class<?> cls = getClass();
                        canonicalName = cls != null ? cls.getCanonicalName() : 0;
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str = (String) objectRef.element;
                    } else {
                        str = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str);
                    sb.append(" - ");
                    sb.append("initWebSDKJS() called");
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
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                    str2 = null;
                                } else {
                                    str2 = null;
                                    str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                }
                                Class<?> cls2 = getClass();
                                str3 = cls2 != null ? cls2.getCanonicalName() : str2;
                            }
                        }
                    }
                    str2 = null;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\n            const hyperKycConfig = new HyperKycConfig(\n                '");
                    String accessToken = getHyperKycConfig$hyperkyc_release().getAccessToken();
                    if (accessToken == null) {
                        accessToken = "";
                    }
                    sb2.append(accessToken);
                    sb2.append("',\n                '");
                    sb2.append(getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release());
                    sb2.append("',\n                '");
                    sb2.append(getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release());
                    sb2.append("',\n                false,\n                null,\n                true,\n                false,\n                true,\n                '");
                    String appId = getHyperKycConfig$hyperkyc_release().getAppId();
                    if (appId == null) {
                        appId = "";
                    }
                    sb2.append(appId);
                    sb2.append("',\n                '");
                    String appKey = getHyperKycConfig$hyperkyc_release().getAppKey();
                    if (appKey == null) {
                        appKey = "";
                    }
                    sb2.append(appKey);
                    sb2.append("'\n            );\n        ");
                    String trimIndent = StringsKt.trimIndent(sb2.toString());
                    str4 = "const callback = (response) => {\n    let obj = response.details;\n    for (let key in obj) {\n        if (obj.hasOwnProperty(key)) {\n            obj[key] = (typeof obj[key] === 'object' && obj[key] !== null) \n                ? JSON.stringify(obj[key]) \n                : String(obj[key]);\n        }\n    }\n    response.details = obj;\n\n    const CHUNK_SIZE = 50000;\n    const stringResponse = JSON.stringify(response);\n    const totalChunks = Math.ceil(stringResponse.length / CHUNK_SIZE);\n    for (let i = 0; i < totalChunks; i++) {\n        let chunk = stringResponse.slice(i * CHUNK_SIZE, (i + 1) * CHUNK_SIZE);\n        window.JSInterface.receiveChunk(chunk, i, totalChunks);\n    }\n};";
                    if (!(!getHyperKycConfig$hyperkyc_release().getInputs().isEmpty())) {
                        str5 = trimIndent;
                        webCoreVM2 = webCoreVM;
                        str6 = "";
                        uniqueId = webCoreVM2.getHyperKycConfig$hyperkyc_release().getUniqueId();
                        if (uniqueId == null) {
                        }
                        if (str8 == null) {
                        }
                        if (!webCoreVM2.getHyperKycConfig$hyperkyc_release().getUseLocation()) {
                        }
                        defaultLangCode = webCoreVM2.getHyperKycConfig$hyperkyc_release().getDefaultLangCode();
                        if (defaultLangCode == null) {
                        }
                        if (str9 != null) {
                        }
                        return StringsKt.trimIndent("\n            (function() {\n                " + str5 + "\n                " + str4 + "\n                " + str6 + "\n                " + str8 + "\n                " + r5 + "\n                " + (str9 != null ? str9 : "") + "\n                HVWebSDKWebCoreMode({\n                    hyperKYCConfig: hyperKycConfig,\n                    hyperKYCCallback: callback,\n                    webCoreAnalyticsCallback: (eventKey, eventProps) => {\n                        window.JSInterface.logAnalytics(eventKey, eventProps);\n                    },\n                    setJourneyIdCallback: (journeyId) => {\n                        window.JSInterface.setJourneyId(journeyId);\n                    },\n                    defaultHeaders: " + webCoreVM2.gson.toJson(webCoreVM2.getDefaultHeaders()) + ",\n                    executeNativeModuleCallback: (moduleType, moduleConfig) => {\n                        window.JSInterface.executeNativeModule(moduleType, moduleConfig);\n                    },\n                    setUiConfigCallback: (uiConfigJSON) => {\n                        window.JSInterface.setNativeUIConfig(uiConfigJSON);\n                    },\n                    setSecureFlag: (secure) => {\n                        window.JSInterface.setSecureFlag(secure);\n                    },\n                    isRooted: " + ContextExtsKt.isDeviceRooted(webCoreVM2.getContext()) + "\n                });\n            })();\n        ");
                    }
                    HashMap inputs = getHyperKycConfig$hyperkyc_release().getInputs();
                    LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(inputs.size()));
                    str5 = trimIndent;
                    webCoreVM2 = webCoreVM;
                    str7 = "const callback = (response) => {\n    let obj = response.details;\n    for (let key in obj) {\n        if (obj.hasOwnProperty(key)) {\n            obj[key] = (typeof obj[key] === 'object' && obj[key] !== null) \n                ? JSON.stringify(obj[key]) \n                : String(obj[key]);\n        }\n    }\n    response.details = obj;\n\n    const CHUNK_SIZE = 50000;\n    const stringResponse = JSON.stringify(response);\n    const totalChunks = Math.ceil(stringResponse.length / CHUNK_SIZE);\n    for (let i = 0; i < totalChunks; i++) {\n        let chunk = stringResponse.slice(i * CHUNK_SIZE, (i + 1) * CHUNK_SIZE);\n        window.JSInterface.receiveChunk(chunk, i, totalChunks);\n    }\n};";
                    it = inputs.entrySet().iterator();
                    map = linkedHashMap;
                    if (!it.hasNext()) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object key = webCoreVM$initWebSDKJS$1.L$7;
                    map = (Map) webCoreVM$initWebSDKJS$1.L$6;
                    Object obj2 = webCoreVM$initWebSDKJS$1.L$5;
                    it = (Iterator) webCoreVM$initWebSDKJS$1.L$4;
                    Map map2 = (Map) webCoreVM$initWebSDKJS$1.L$3;
                    str7 = (String) webCoreVM$initWebSDKJS$1.L$2;
                    str5 = (String) webCoreVM$initWebSDKJS$1.L$1;
                    webCoreVM2 = (WebCoreVM) webCoreVM$initWebSDKJS$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    Object obj3 = obj2;
                    str2 = null;
                    Object value = (String) obj;
                    if (value == null) {
                        value = obj3;
                    }
                    map.put(key, value);
                    map = map2;
                    if (!it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        key = entry.getKey();
                        value = entry.getValue();
                        String str10 = value instanceof String ? (String) value : str2;
                        if (str10 != null) {
                            webCoreVM$initWebSDKJS$1.L$0 = webCoreVM2;
                            webCoreVM$initWebSDKJS$1.L$1 = str5;
                            webCoreVM$initWebSDKJS$1.L$2 = str7;
                            webCoreVM$initWebSDKJS$1.L$3 = map;
                            webCoreVM$initWebSDKJS$1.L$4 = it;
                            webCoreVM$initWebSDKJS$1.L$5 = value;
                            webCoreVM$initWebSDKJS$1.L$6 = map;
                            webCoreVM$initWebSDKJS$1.L$7 = key;
                            webCoreVM$initWebSDKJS$1.label = 1;
                            Object encodeFileToBase64 = FileExtsKt.encodeFileToBase64(str10, webCoreVM$initWebSDKJS$1);
                            if (encodeFileToBase64 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            map2 = map;
                            obj3 = value;
                            obj = encodeFileToBase64;
                            Object value2 = (String) obj;
                            if (value2 == null) {
                            }
                            map.put(key, value2);
                            map = map2;
                            if (!it.hasNext()) {
                                str6 = "hyperKycConfig.setInputs(" + JSONExtsKt.toJSONString(map) + ");";
                                str4 = str7;
                                uniqueId = webCoreVM2.getHyperKycConfig$hyperkyc_release().getUniqueId();
                                if (uniqueId == null) {
                                    str8 = "hyperKycConfig.setUniqueId('" + uniqueId + "');";
                                } else {
                                    str8 = str2;
                                }
                                if (str8 == null) {
                                    str8 = "";
                                }
                                String str11 = !webCoreVM2.getHyperKycConfig$hyperkyc_release().getUseLocation() ? "hyperKycConfig.setUseLocation(true);" : "";
                                defaultLangCode = webCoreVM2.getHyperKycConfig$hyperkyc_release().getDefaultLangCode();
                                if (defaultLangCode == null) {
                                    str9 = "hyperKycConfig.setDefaultLangCode('" + defaultLangCode + "');";
                                } else {
                                    str9 = str2;
                                }
                                return StringsKt.trimIndent("\n            (function() {\n                " + str5 + "\n                " + str4 + "\n                " + str6 + "\n                " + str8 + "\n                " + str11 + "\n                " + (str9 != null ? str9 : "") + "\n                HVWebSDKWebCoreMode({\n                    hyperKYCConfig: hyperKycConfig,\n                    hyperKYCCallback: callback,\n                    webCoreAnalyticsCallback: (eventKey, eventProps) => {\n                        window.JSInterface.logAnalytics(eventKey, eventProps);\n                    },\n                    setJourneyIdCallback: (journeyId) => {\n                        window.JSInterface.setJourneyId(journeyId);\n                    },\n                    defaultHeaders: " + webCoreVM2.gson.toJson(webCoreVM2.getDefaultHeaders()) + ",\n                    executeNativeModuleCallback: (moduleType, moduleConfig) => {\n                        window.JSInterface.executeNativeModule(moduleType, moduleConfig);\n                    },\n                    setUiConfigCallback: (uiConfigJSON) => {\n                        window.JSInterface.setNativeUIConfig(uiConfigJSON);\n                    },\n                    setSecureFlag: (secure) => {\n                        window.JSInterface.setSecureFlag(secure);\n                    },\n                    isRooted: " + ContextExtsKt.isDeviceRooted(webCoreVM2.getContext()) + "\n                });\n            })();\n        ");
                            }
                        } else {
                            map2 = map;
                            map.put(key, value2);
                            map = map2;
                            if (!it.hasNext()) {
                            }
                        }
                    }
                }
            }
        }
        webCoreVM = this;
        webCoreVM$initWebSDKJS$1 = new WebCoreVM$initWebSDKJS$1(webCoreVM, continuation);
        Object obj4 = webCoreVM$initWebSDKJS$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = webCoreVM$initWebSDKJS$1.label;
        if (i != 0) {
        }
    }

    public final void setAnalyticsCommonProperties$hyperkyc_release() {
        AnalyticsLogger.INSTANCE.setCommonProperties$hyperkyc_release(MapsKt.mutableMapOf(TuplesKt.to("appId", getHyperKycConfig$hyperkyc_release().getAppId()), TuplesKt.to("transactionId", getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release()), TuplesKt.to("workflowId", getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release()), TuplesKt.to("journeyId", this.journeyId), TuplesKt.to("uniqueId", getHyperKycConfig$hyperkyc_release().getUniqueId())));
    }

    public final String backPressedJS$hyperkyc_release() {
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
        sb.append("backPressedJS() called");
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            return "window.nativeGoBack()";
        }
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
        if (!CoreExtsKt.isDebug()) {
            return "window.nativeGoBack()";
        }
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
            return "window.nativeGoBack()";
        }
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
        Log.println(3, str, "backPressedJS() called ");
        return "window.nativeGoBack()";
    }

    public final String sendNativeModuleDataJS$hyperkyc_release(WebCoreNativeResponse webCoreNativeResponse) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String json;
        String className2;
        Intrinsics.checkNotNullParameter(webCoreNativeResponse, "webCoreNativeResponse");
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
        sb.append("sendHyperSnapDataJS() called");
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
                    Log.println(3, str, "sendHyperSnapDataJS() called ");
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n            window.nativeModuleResultCallback(\n                ");
        json = new Gson().toJson(webCoreNativeResponse);
        sb2.append(json);
        sb2.append("\n            )\n        ");
        return StringsKt.trimIndent(sb2.toString());
    }

    public final String loadHtmlFromAssets$hyperkyc_release(AssetManager assets, HSRemoteConfig remoteConfig) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(assets, "assets");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
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
        sb.append("loadHtmlFromAssets() called");
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
                    Log.println(3, str, "loadHtmlFromAssets() called ");
                }
            }
        }
        this.remoteConfig = remoteConfig;
        InputStream open = assets.open("web_core/webCore.html");
        Intrinsics.checkNotNullExpressionValue(open, "assets.open(\"web_core/webCore.html\")");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
        StringBuilder sb2 = new StringBuilder();
        BufferedReader bufferedReader2 = bufferedReader;
        try {
            Iterator<String> it = TextStreamsKt.lineSequence(bufferedReader2).iterator();
            while (it.hasNext()) {
                sb2.append(it.next());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader2, null);
            String sb3 = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(sb3, "stringBuilder.toString()");
            return StringsKt.replace$default(StringsKt.replace$default(sb3, "hyperverge-web-sdk@8.10.1", "hyperverge-web-sdk@" + this.remoteConfig.getWebCoreVersion$hyperkyc_release(), false, 4, (Object) null), "transactionIdToBeReplacedInCode_f48ae56d-60e9-4b5f-a9a9-373e1a65bdc8", getHyperKycConfig$hyperkyc_release().getTransactionId$hyperkyc_release(), false, 4, (Object) null);
        } finally {
        }
    }

    private final Map<String, String> getDefaultHeaders() {
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
        sb.append("getDefaultHeaders() called");
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
                    Log.println(3, str, "getDefaultHeaders() called ");
                }
            }
        }
        Application application = getApplication();
        String str2 = application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName;
        Object obj = getHyperKycConfig$hyperkyc_release().getMetadataMap().get(WorkflowAPIHeaders.SDK_TYPE);
        if (obj == null) {
            obj = "android";
        }
        String str3 = (String) obj;
        Object obj2 = getHyperKycConfig$hyperkyc_release().getMetadataMap().get(WorkflowAPIHeaders.SDK_VERSION);
        if (obj2 == null) {
            obj2 = BuildConfig.HYPERKYC_VERSION_NAME;
        }
        String sdkMode = InternalToolUtils.getSdkMode(getContext());
        Pair[] pairArr = new Pair[15];
        pairArr[0] = TuplesKt.to("workflowId", getHyperKycConfig$hyperkyc_release().getWorkflowId$hyperkyc_release());
        pairArr[1] = TuplesKt.to("device", Build.MODEL);
        pairArr[2] = TuplesKt.to(WorkflowAPIHeaders.DEVICE_MAKE, Build.BRAND);
        pairArr[3] = TuplesKt.to("platform", "android");
        pairArr[4] = TuplesKt.to("os", "android");
        pairArr[5] = TuplesKt.to(WorkflowAPIHeaders.SDK_VERSION, (String) obj2);
        pairArr[6] = TuplesKt.to(WorkflowAPIHeaders.SDK_TYPE, str3);
        pairArr[7] = TuplesKt.to("sdk-mode", sdkMode);
        pairArr[8] = TuplesKt.to(WorkflowAPIHeaders.APP_VERSION, str2);
        pairArr[9] = TuplesKt.to(WorkflowAPIHeaders.PLATFORM_VERSION, Build.VERSION.RELEASE);
        pairArr[10] = TuplesKt.to(WorkflowAPIHeaders.WEB_CORE_MODE, "yes");
        String webViewServicesVersion = ContextExtsKt.getWebViewServicesVersion(getContext());
        String str4 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        if (webViewServicesVersion == null) {
            webViewServicesVersion = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        pairArr[11] = TuplesKt.to(WorkflowAPIHeaders.WEBVIEW_VERSION, webViewServicesVersion);
        pairArr[12] = TuplesKt.to(WorkflowAPIHeaders.WEBVIEW_ENABLED, String.valueOf(ContextExtsKt.isWebViewEnabled(getContext())));
        pairArr[13] = TuplesKt.to(WorkflowAPIHeaders.PLAY_SERVICES_VERSION, String.valueOf(ContextExtsKt.getGooglePlayServicesVersion(getContext())));
        String playStoreVersion = ContextExtsKt.getPlayStoreVersion(getContext());
        if (playStoreVersion != null) {
            str4 = playStoreVersion;
        }
        pairArr[14] = TuplesKt.to(WorkflowAPIHeaders.PLAY_STORE_VERSION, str4);
        Map mapOf = MapsKt.mapOf(pairArr);
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(mapOf.size()));
        for (Map.Entry entry : mapOf.entrySet()) {
            Object key = entry.getKey();
            String str5 = (String) entry.getValue();
            if (str5 == null) {
                str5 = "";
            } else {
                Intrinsics.checkNotNullExpressionValue(str5, "it.value ?: \"\"");
            }
            linkedHashMap.put(key, str5);
        }
        return linkedHashMap;
    }
}
