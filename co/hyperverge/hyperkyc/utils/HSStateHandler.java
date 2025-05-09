package co.hyperverge.hyperkyc.utils;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.core.hv.HSBridgeException;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import com.google.gson.Gson;
import java.io.File;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: HSStateHandler.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J$\u0010(\u001a\u0002H)\"\n\b\u0000\u0010)\u0018\u0001*\u00020\u00012\u0006\u0010*\u001a\u00020+H\u0080\b¢\u0006\u0004\b,\u0010-J\u0013\u0010.\u001a\u00020/H\u0080@ø\u0001\u0000¢\u0006\u0004\b0\u00101J,\u00102\u001a\u00020&\"\n\b\u0000\u0010)\u0018\u0001*\u00020\u00012\u0006\u00103\u001a\u0002H)2\u0006\u00104\u001a\u00020\u0018H\u0080\b¢\u0006\u0004\b5\u00106J*\u00107\u001a\b\u0012\u0004\u0012\u00020&082\u0006\u00103\u001a\u00020/H\u0080@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b9\u0010:R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u000f\u0010\u000bR\u001b\u0010\u0011\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\r\u001a\u0004\b\u0012\u0010\u000bR\u001b\u0010\u0014\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0015\u0010\u000bR\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\r\u001a\u0004\b\u001d\u0010\u000bR\u001b\u0010\u001f\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\r\u001a\u0004\b \u0010\u000bR\u001b\u0010\"\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\r\u001a\u0004\b#\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006;"}, d2 = {"Lco/hyperverge/hyperkyc/utils/HSStateHandler;", "", "filesDir", "Ljava/io/File;", "(Ljava/io/File;)V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "hsBridgeExceptionFile", "getHsBridgeExceptionFile", "()Ljava/io/File;", "hsBridgeExceptionFile$delegate", "Lkotlin/Lazy;", "hsDataFile", "getHsDataFile", "hsDataFile$delegate", "hsExceptionFile", "getHsExceptionFile", "hsExceptionFile$delegate", "hsResponseFile", "getHsResponseFile", "hsResponseFile$delegate", "isActivityRecreated", "", "()Z", "setActivityRecreated", "(Z)V", "resultDataDir", "getResultDataDir", "resultDataDir$delegate", "resultStateDir", "getResultStateDir", "resultStateDir$delegate", "uiConfigFile", "getUiConfigFile", "uiConfigFile$delegate", "deleteDataDir", "", "deleteStateDir", "retrieveState", "T", "moduleId", "", "retrieveState$hyperkyc_release", "(Ljava/lang/String;)Ljava/lang/Object;", "retrieveUIConfig", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "retrieveUIConfig$hyperkyc_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "storeState", "data", "isActivityDestroyed", "storeState$hyperkyc_release", "(Ljava/lang/Object;Z)V", "storeUIConfig", "Lkotlin/Result;", "storeUIConfig-gIAlu-s$hyperkyc_release", "(Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HSStateHandler {
    private final File filesDir;
    private final Gson gson;

    /* renamed from: hsBridgeExceptionFile$delegate, reason: from kotlin metadata */
    private final Lazy hsBridgeExceptionFile;

    /* renamed from: hsDataFile$delegate, reason: from kotlin metadata */
    private final Lazy hsDataFile;

    /* renamed from: hsExceptionFile$delegate, reason: from kotlin metadata */
    private final Lazy hsExceptionFile;

    /* renamed from: hsResponseFile$delegate, reason: from kotlin metadata */
    private final Lazy hsResponseFile;
    private boolean isActivityRecreated;

    /* renamed from: resultDataDir$delegate, reason: from kotlin metadata */
    private final Lazy resultDataDir;

    /* renamed from: resultStateDir$delegate, reason: from kotlin metadata */
    private final Lazy resultStateDir;

    /* renamed from: uiConfigFile$delegate, reason: from kotlin metadata */
    private final Lazy uiConfigFile;

    public HSStateHandler(File filesDir) {
        Intrinsics.checkNotNullParameter(filesDir, "filesDir");
        this.filesDir = filesDir;
        this.gson = new Gson();
        this.resultStateDir = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$resultStateDir$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File file;
                file = HSStateHandler.this.filesDir;
                File file2 = new File(file, "/hv/hyperSnapResultState/");
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                return file2;
            }
        });
        this.resultDataDir = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$resultDataDir$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File file;
                file = HSStateHandler.this.filesDir;
                File file2 = new File(file, "hv/sdkResult");
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                return file2;
            }
        });
        this.uiConfigFile = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$uiConfigFile$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File resultStateDir;
                resultStateDir = HSStateHandler.this.getResultStateDir();
                return new File(resultStateDir, "hk_ui_config.json");
            }
        });
        this.hsBridgeExceptionFile = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$hsBridgeExceptionFile$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File resultStateDir;
                resultStateDir = HSStateHandler.this.getResultStateDir();
                return new File(resultStateDir, "hs_bridge_exception_state.json");
            }
        });
        this.hsExceptionFile = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$hsExceptionFile$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File resultStateDir;
                resultStateDir = HSStateHandler.this.getResultStateDir();
                return new File(resultStateDir, "hs_exception_state.json");
            }
        });
        this.hsResponseFile = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$hsResponseFile$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File resultStateDir;
                resultStateDir = HSStateHandler.this.getResultStateDir();
                return new File(resultStateDir, "hs_bridge_exception_state.json");
            }
        });
        this.hsDataFile = LazyKt.lazy(new Function0<File>() { // from class: co.hyperverge.hyperkyc.utils.HSStateHandler$hsDataFile$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(HSStateHandler.this.getResultDataDir(), "hv_data.json");
            }
        });
    }

    public final Gson getGson() {
        return this.gson;
    }

    /* renamed from: isActivityRecreated, reason: from getter */
    public final boolean getIsActivityRecreated() {
        return this.isActivityRecreated;
    }

    public final void setActivityRecreated(boolean z) {
        this.isActivityRecreated = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getResultStateDir() {
        return (File) this.resultStateDir.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getResultDataDir() {
        return (File) this.resultDataDir.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getUiConfigFile() {
        return (File) this.uiConfigFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getHsBridgeExceptionFile() {
        return (File) this.hsBridgeExceptionFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getHsExceptionFile() {
        return (File) this.hsExceptionFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getHsResponseFile() {
        return (File) this.hsResponseFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getHsDataFile() {
        return (File) this.hsDataFile.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: storeUIConfig-gIAlu-s$hyperkyc_release, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m432storeUIConfiggIAlus$hyperkyc_release(HSUIConfig hSUIConfig, Continuation<? super Result<Unit>> continuation) {
        HSStateHandler$storeUIConfig$1 hSStateHandler$storeUIConfig$1;
        int i;
        if (continuation instanceof HSStateHandler$storeUIConfig$1) {
            hSStateHandler$storeUIConfig$1 = (HSStateHandler$storeUIConfig$1) continuation;
            if ((hSStateHandler$storeUIConfig$1.label & Integer.MIN_VALUE) != 0) {
                hSStateHandler$storeUIConfig$1.label -= Integer.MIN_VALUE;
                Object obj = hSStateHandler$storeUIConfig$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hSStateHandler$storeUIConfig$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HSStateHandler$storeUIConfig$2 hSStateHandler$storeUIConfig$2 = new HSStateHandler$storeUIConfig$2(this, hSUIConfig, null);
                    hSStateHandler$storeUIConfig$1.label = 1;
                    obj = CoroutineExtsKt.onIO$default(null, hSStateHandler$storeUIConfig$2, hSStateHandler$storeUIConfig$1, 1, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((Result) obj).getValue();
            }
        }
        hSStateHandler$storeUIConfig$1 = new HSStateHandler$storeUIConfig$1(this, continuation);
        Object obj2 = hSStateHandler$storeUIConfig$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hSStateHandler$storeUIConfig$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object retrieveUIConfig$hyperkyc_release(Continuation<? super HSUIConfig> continuation) {
        HSStateHandler$retrieveUIConfig$1 hSStateHandler$retrieveUIConfig$1;
        int i;
        if (continuation instanceof HSStateHandler$retrieveUIConfig$1) {
            hSStateHandler$retrieveUIConfig$1 = (HSStateHandler$retrieveUIConfig$1) continuation;
            if ((hSStateHandler$retrieveUIConfig$1.label & Integer.MIN_VALUE) != 0) {
                hSStateHandler$retrieveUIConfig$1.label -= Integer.MIN_VALUE;
                Object obj = hSStateHandler$retrieveUIConfig$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hSStateHandler$retrieveUIConfig$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HSStateHandler$retrieveUIConfig$2 hSStateHandler$retrieveUIConfig$2 = new HSStateHandler$retrieveUIConfig$2(this, null);
                    hSStateHandler$retrieveUIConfig$1.label = 1;
                    obj = CoroutineExtsKt.onIO$default(null, hSStateHandler$retrieveUIConfig$2, hSStateHandler$retrieveUIConfig$1, 1, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Intrinsics.checkNotNullExpressionValue(obj, "internal suspend fun ret…t found\")\n        }\n    }");
                return obj;
            }
        }
        hSStateHandler$retrieveUIConfig$1 = new HSStateHandler$retrieveUIConfig$1(this, continuation);
        Object obj2 = hSStateHandler$retrieveUIConfig$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hSStateHandler$retrieveUIConfig$1.label;
        if (i != 0) {
        }
        Intrinsics.checkNotNullExpressionValue(obj2, "internal suspend fun ret…t found\")\n        }\n    }");
        return obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0436  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ <T> void storeState$hyperkyc_release(T data, boolean isActivityDestroyed) {
        Class cls;
        File hsExceptionFile;
        String canonicalName;
        String str;
        String str2;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str3;
        String canonicalName2;
        String className;
        Object m1202constructorimpl2;
        Throwable m1205exceptionOrNullimpl;
        String str4;
        String str5;
        Object m1202constructorimpl3;
        String str6;
        String str7;
        Matcher matcher;
        String localizedMessage;
        String className2;
        String className3;
        String className4;
        Intrinsics.checkNotNullParameter(data, "data");
        if (isActivityDestroyed) {
            Gson gson = getGson();
            boolean z = data instanceof HSBridgeException;
            if (z) {
                cls = HSBridgeException.class;
            } else {
                Intrinsics.reifiedOperationMarker(4, "T");
                cls = Object.class;
            }
            String jsonData = gson.toJson(data, cls);
            if (z) {
                hsExceptionFile = getHsBridgeExceptionFile();
            } else {
                hsExceptionFile = data instanceof Throwable ? getHsExceptionFile() : getHsResponseFile();
            }
            File file = hsExceptionFile;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls2 = getClass();
                canonicalName = cls2 != null ? cls2.getCanonicalName() : null;
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
            StringBuilder sb2 = new StringBuilder();
            sb2.append("storeState() called for ");
            Intrinsics.reifiedOperationMarker(4, "T");
            sb2.append(Object.class.getCanonicalName());
            String sb3 = sb2.toString();
            if (sb3 == null) {
                sb3 = "null ";
            }
            sb.append(sb3);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str = "N/A";
                str3 = "packageName";
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
                        Result.Companion companion4 = Result.INSTANCE;
                        HSStateHandler hSStateHandler = this;
                        Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                        byte[] bytes = jsonData.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                        FilesKt.writeBytes(file, bytes);
                        m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
                        if (m1205exceptionOrNullimpl == null) {
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
                    str3 = "packageName";
                } else {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
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
                        StringBuilder sb4 = new StringBuilder();
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append("storeState() called for ");
                        Intrinsics.reifiedOperationMarker(4, "T");
                        sb5.append(Object.class.getCanonicalName());
                        String sb6 = sb5.toString();
                        if (sb6 == null) {
                            sb6 = str2;
                        }
                        sb4.append(sb6);
                        sb4.append(' ');
                        sb4.append("");
                        Log.println(3, canonicalName2, sb4.toString());
                    }
                }
            }
            try {
                Result.Companion companion42 = Result.INSTANCE;
                HSStateHandler hSStateHandler2 = this;
                Intrinsics.checkNotNullExpressionValue(jsonData, "jsonData");
                byte[] bytes2 = jsonData.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                FilesKt.writeBytes(file, bytes2);
                m1202constructorimpl2 = Result.m1202constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion5 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl2);
            if (m1205exceptionOrNullimpl == null) {
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb7 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls4 = getClass();
                    String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                    str4 = canonicalName3 == null ? str : canonicalName3;
                }
                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                if (matcher4.find()) {
                    str4 = matcher4.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                }
                if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str4 = str4.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb7.append(str4);
                sb7.append(" - ");
                StringBuilder sb8 = new StringBuilder();
                sb8.append("failed saving ");
                Intrinsics.reifiedOperationMarker(4, "T");
                sb8.append(Object.class.getCanonicalName());
                sb8.append(" state json to file");
                String sb9 = sb8.toString();
                if (sb9 == null) {
                    sb9 = str2;
                }
                sb7.append(sb9);
                sb7.append(' ');
                String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage2 != null) {
                    str5 = '\n' + localizedMessage2;
                } else {
                    str5 = "";
                }
                sb7.append(str5);
                companion6.log(level2, sb7.toString());
                CoreExtsKt.isRelease();
                try {
                    Result.Companion companion7 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th4) {
                    Result.Companion companion8 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                    m1202constructorimpl3 = "";
                }
                String str9 = (String) m1202constructorimpl3;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str9, str3);
                    if (StringsKt.contains$default((CharSequence) str9, charSequence, false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                            str6 = null;
                        } else {
                            str6 = null;
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default != null) {
                                str7 = substringAfterLast$default;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                if (matcher.find()) {
                                    str7 = matcher.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                }
                                if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str7 = str7.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb10 = new StringBuilder();
                                StringBuilder sb11 = new StringBuilder();
                                sb11.append("failed saving ");
                                Intrinsics.reifiedOperationMarker(4, "T");
                                sb11.append(Object.class.getCanonicalName());
                                sb11.append(" state json to file");
                                String sb12 = sb11.toString();
                                sb10.append(sb12 != null ? str2 : sb12);
                                sb10.append(' ');
                                localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str6;
                                if (localizedMessage != null) {
                                    str8 = '\n' + localizedMessage;
                                }
                                sb10.append(str8);
                                Log.println(6, str7, sb10.toString());
                            }
                        }
                        Class<?> cls5 = getClass();
                        String canonicalName4 = cls5 != null ? cls5.getCanonicalName() : str6;
                        str7 = canonicalName4 == null ? str : canonicalName4;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                        if (matcher.find()) {
                        }
                        if (str7.length() > 23) {
                            str7 = str7.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb102 = new StringBuilder();
                        StringBuilder sb112 = new StringBuilder();
                        sb112.append("failed saving ");
                        Intrinsics.reifiedOperationMarker(4, "T");
                        sb112.append(Object.class.getCanonicalName());
                        sb112.append(" state json to file");
                        String sb122 = sb112.toString();
                        sb102.append(sb122 != null ? str2 : sb122);
                        sb102.append(' ');
                        if (m1205exceptionOrNullimpl == null) {
                        }
                        if (localizedMessage != null) {
                        }
                        sb102.append(str8);
                        Log.println(6, str7, sb102.toString());
                    }
                }
            }
        }
    }

    private final void deleteDataDir() {
        File[] listFiles;
        if (!getResultDataDir().exists() || (listFiles = getResultDataDir().listFiles()) == null) {
            return;
        }
        for (File file : listFiles) {
            file.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteStateDir() {
        File[] listFiles;
        if (!getResultStateDir().exists() || (listFiles = getResultStateDir().listFiles()) == null) {
            return;
        }
        for (File file : listFiles) {
            file.delete();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:149:0x05a1, code lost:
    
        if (r14 != null) goto L227;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x01ef, code lost:
    
        if (r7 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0816, code lost:
    
        if (r9 != null) goto L321;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x097e  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0981  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03eb A[Catch: all -> 0x0557, TryCatch #4 {all -> 0x0557, blocks: (B:120:0x038f, B:122:0x03b2, B:124:0x03b8, B:127:0x03d7, B:129:0x03eb, B:130:0x03f2, B:132:0x03ff, B:135:0x0406, B:136:0x0415, B:139:0x0433, B:238:0x0481, B:241:0x0488, B:243:0x0490, B:245:0x04a2, B:247:0x04b8, B:249:0x04be, B:252:0x04d9, B:254:0x04ed, B:255:0x04f4, B:257:0x0501, B:260:0x0508, B:261:0x0517, B:264:0x0536, B:266:0x0514, B:267:0x04c9, B:269:0x04cf, B:275:0x0477, B:141:0x0550, B:276:0x0412, B:277:0x03c7, B:279:0x03cd, B:237:0x0454), top: B:119:0x038f, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x056b  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x074e  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x07db  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x072f  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0454 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x093e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ <T> T retrieveState$hyperkyc_release(String moduleId) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        String str2;
        Object m1202constructorimpl2;
        String canonicalName3;
        String className2;
        Object obj;
        Object m1202constructorimpl3;
        Throwable m1205exceptionOrNullimpl;
        Object obj2;
        HVError hVError;
        String str3;
        String str4;
        Object m1202constructorimpl4;
        String canonicalName4;
        String str5;
        String className3;
        String className4;
        HyperLogger.Level level;
        HyperLogger companion;
        StringBuilder sb;
        StackTraceElement stackTraceElement;
        String str6;
        String canonicalName5;
        Matcher matcher;
        String str7;
        Object m1202constructorimpl5;
        String canonicalName6;
        String className5;
        String className6;
        String str8;
        String str9;
        Object m1202constructorimpl6;
        String str10;
        String str11;
        Matcher matcher2;
        String className7;
        String className8;
        String className9;
        String className10;
        Intrinsics.checkNotNullParameter(moduleId, "moduleId");
        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
        HyperLogger companion2 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2 = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className10 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb2.append(canonicalName);
        sb2.append(" - ");
        sb2.append("retrieveState() called");
        sb2.append(' ');
        sb2.append("");
        companion2.log(level2, sb2.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion4 = Result.INSTANCE;
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
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = getClass();
                        canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 == null) {
                            canonicalName2 = "N/A";
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
                    Log.println(3, canonicalName2, "retrieveState() called ");
                }
            }
        }
        File file = new File(getResultDataDir(), moduleId + ".json");
        if (!file.exists()) {
            file = getHsDataFile();
        }
        File file2 = file;
        HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
        HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb3 = new StringBuilder();
        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
        if (stackTraceElement4 == null || (className9 = stackTraceElement4.getClassName()) == null) {
            str = "N/A";
        } else {
            str = "N/A";
            str2 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        Class<?> cls3 = getClass();
        str2 = cls3 != null ? cls3.getCanonicalName() : null;
        if (str2 == null) {
            str2 = str;
        }
        Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
        if (matcher5.find()) {
            str2 = matcher5.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
        }
        Unit unit3 = Unit.INSTANCE;
        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str2 = str2.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb3.append(str2);
        sb3.append(" - ");
        String str12 = "retrieveState: state targetFile exists : " + file2.exists();
        if (str12 == null) {
            str12 = "null ";
        }
        sb3.append(str12);
        sb3.append(' ');
        sb3.append("");
        companion5.log(level3, sb3.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion6 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion7 = Result.INSTANCE;
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
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement5 == null || (className2 = stackTraceElement5.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls4 = getClass();
                        canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                        if (canonicalName3 == null) {
                            canonicalName3 = str;
                        }
                    }
                    Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                    if (matcher6.find()) {
                        canonicalName3 = matcher6.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    Unit unit4 = Unit.INSTANCE;
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    String str13 = "retrieveState: state targetFile exists : " + file2.exists();
                    if (str13 == null) {
                        str13 = "null ";
                    }
                    sb4.append(str13);
                    sb4.append(' ');
                    sb4.append("");
                    Log.println(3, canonicalName3, sb4.toString());
                }
            }
        }
        if (file2.exists()) {
            try {
                Result.Companion companion8 = Result.INSTANCE;
                HSStateHandler hSStateHandler = this;
                obj = getGson().fromJson(FilesKt.readText$default(file2, null, 1, null), new HSStateHandler$retrieveState$3$type$1().getType());
                try {
                    level = HyperLogger.Level.DEBUG;
                    companion = HyperLogger.INSTANCE.getInstance();
                    sb = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion9 = Result.INSTANCE;
                    m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    Object obj3 = obj2;
                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                    Triple triple = (Triple) obj3;
                    hVError = (HVError) triple.component1();
                    HVResponse hVResponse = (HVResponse) triple.component2();
                    T t = (T) ((JSONObject) triple.component3());
                    if (hVError != null) {
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                obj = null;
            }
            if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null) {
                str6 = "null ";
            } else {
                str6 = "null ";
                canonicalName5 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                if (canonicalName5 != null) {
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName5);
                    Matcher matcher7 = matcher;
                    if (matcher.find()) {
                        canonicalName5 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    String str14 = canonicalName5;
                    if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        String str15 = canonicalName5;
                        canonicalName5 = canonicalName5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(canonicalName5);
                        sb.append(" - ");
                        str7 = "retrieveState: hvData from file: " + obj;
                        String str16 = str7;
                        if (str7 == null) {
                            str7 = str6;
                        }
                        sb.append(str7);
                        sb.append(' ');
                        sb.append("");
                        companion.log(level, sb.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion10 = Result.INSTANCE;
                                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                            } catch (Throwable th5) {
                                Result.Companion companion11 = Result.INSTANCE;
                                m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                m1202constructorimpl5 = "";
                            }
                            String packageName3 = (String) m1202constructorimpl5;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                    if (stackTraceElement6 == null || (className5 = stackTraceElement6.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls5 = getClass();
                                        canonicalName6 = cls5 != null ? cls5.getCanonicalName() : null;
                                        if (canonicalName6 == null) {
                                            canonicalName6 = str;
                                        }
                                    }
                                    Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName6);
                                    Matcher matcher9 = matcher8;
                                    if (matcher8.find()) {
                                        canonicalName6 = matcher8.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName6, "replaceAll(\"\")");
                                    }
                                    Unit unit6 = Unit.INSTANCE;
                                    String str17 = canonicalName6;
                                    if (canonicalName6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        String str18 = canonicalName6;
                                        canonicalName6 = canonicalName6.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName6, "this as java.lang.String…ing(startIndex, endIndex)");
                                        StringBuilder sb5 = new StringBuilder();
                                        String str19 = "retrieveState: hvData from file: " + obj;
                                        String str20 = str19;
                                        sb5.append(str19 != null ? str6 : str19);
                                        sb5.append(' ');
                                        sb5.append("");
                                        Log.println(3, canonicalName6, sb5.toString());
                                    }
                                    String str21 = canonicalName6;
                                    StringBuilder sb52 = new StringBuilder();
                                    String str192 = "retrieveState: hvData from file: " + obj;
                                    String str202 = str192;
                                    sb52.append(str192 != null ? str6 : str192);
                                    sb52.append(' ');
                                    sb52.append("");
                                    Log.println(3, canonicalName6, sb52.toString());
                                }
                            }
                        }
                        m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                        if (m1205exceptionOrNullimpl != null) {
                            HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                            HyperLogger companion12 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb6 = new StringBuilder();
                            StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace7, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                            if (stackTraceElement7 == null || (className4 = stackTraceElement7.getClassName()) == null) {
                                obj2 = obj;
                            } else {
                                obj2 = obj;
                                str3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            Class<?> cls6 = getClass();
                            String canonicalName7 = cls6 != null ? cls6.getCanonicalName() : null;
                            str3 = canonicalName7 == null ? str : canonicalName7;
                            Matcher matcher10 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                            if (matcher10.find()) {
                                str3 = matcher10.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                            }
                            Unit unit7 = Unit.INSTANCE;
                            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb6.append(str3);
                            sb6.append(" - ");
                            sb6.append("unable to retrieve hv_data state json from file");
                            sb6.append(' ');
                            String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                            if (localizedMessage != null) {
                                str4 = '\n' + localizedMessage;
                            } else {
                                str4 = "";
                            }
                            sb6.append(str4);
                            companion12.log(level4, sb6.toString());
                            CoreExtsKt.isRelease();
                            try {
                                Result.Companion companion13 = Result.INSTANCE;
                                Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke4, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                            } catch (Throwable th6) {
                                Result.Companion companion14 = Result.INSTANCE;
                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th6));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                m1202constructorimpl4 = "";
                            }
                            String packageName4 = (String) m1202constructorimpl4;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName4, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName4, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace8, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                    if (stackTraceElement8 == null || (className3 = stackTraceElement8.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls7 = getClass();
                                        canonicalName4 = cls7 != null ? cls7.getCanonicalName() : null;
                                        if (canonicalName4 == null) {
                                            canonicalName4 = str;
                                        }
                                    }
                                    Matcher matcher11 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName4);
                                    if (matcher11.find()) {
                                        canonicalName4 = matcher11.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                    if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        canonicalName4 = canonicalName4.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb7 = new StringBuilder();
                                    sb7.append("unable to retrieve hv_data state json from file");
                                    sb7.append(' ');
                                    String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                    if (localizedMessage2 != null) {
                                        str5 = '\n' + localizedMessage2;
                                    } else {
                                        str5 = "";
                                    }
                                    sb7.append(str5);
                                    Log.println(6, canonicalName4, sb7.toString());
                                }
                            }
                        } else {
                            obj2 = obj;
                        }
                        Object obj32 = obj2;
                        Intrinsics.checkNotNull(obj32, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                        Triple triple2 = (Triple) obj32;
                        hVError = (HVError) triple2.component1();
                        HVResponse hVResponse2 = (HVResponse) triple2.component2();
                        T t2 = (T) ((JSONObject) triple2.component3());
                        if (hVError != null) {
                            throw new HSBridgeException(hVError, hVResponse2);
                        }
                        if (hVResponse2 != null || t2 != null) {
                            Intrinsics.reifiedOperationMarker(4, "T");
                            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
                            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(HyperKycData.FaceData.class))) {
                                HyperKycData.FaceData.Companion companion15 = HyperKycData.FaceData.INSTANCE;
                                Gson gson = getGson();
                                Intrinsics.checkNotNull(hVResponse2);
                                t2 = (T) companion15.from$hyperkyc_release(gson, hVResponse2);
                            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(HyperKycData.DocData.class))) {
                                HyperKycData.DocData.Companion companion16 = HyperKycData.DocData.INSTANCE;
                                Gson gson2 = getGson();
                                Intrinsics.checkNotNull(hVResponse2);
                                t2 = (T) companion16.from$hyperkyc_release(gson2, "", hVResponse2);
                            } else if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append("Not supported - ");
                                Intrinsics.reifiedOperationMarker(4, "T");
                                sb8.append(Object.class.getCanonicalName());
                                throw new NotImplementedError("An operation is not implemented: " + sb8.toString());
                            }
                            Intrinsics.reifiedOperationMarker(1, "T");
                            T t3 = t2;
                            return t2;
                        }
                    }
                    String str22 = canonicalName5;
                    sb.append(canonicalName5);
                    sb.append(" - ");
                    str7 = "retrieveState: hvData from file: " + obj;
                    String str162 = str7;
                    if (str7 == null) {
                    }
                    sb.append(str7);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    Object obj322 = obj2;
                    Intrinsics.checkNotNull(obj322, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                    Triple triple22 = (Triple) obj322;
                    hVError = (HVError) triple22.component1();
                    HVResponse hVResponse22 = (HVResponse) triple22.component2();
                    T t22 = (T) ((JSONObject) triple22.component3());
                    if (hVError != null) {
                    }
                }
            }
            Class<?> cls8 = getClass();
            canonicalName5 = cls8 != null ? cls8.getCanonicalName() : null;
            if (canonicalName5 == null) {
                canonicalName5 = str;
            }
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName5);
            Matcher matcher72 = matcher;
            if (matcher.find()) {
            }
            Unit unit52 = Unit.INSTANCE;
            String str142 = canonicalName5;
            if (canonicalName5.length() > 23) {
                String str152 = canonicalName5;
                canonicalName5 = canonicalName5.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(canonicalName5);
                sb.append(" - ");
                str7 = "retrieveState: hvData from file: " + obj;
                String str1622 = str7;
                if (str7 == null) {
                }
                sb.append(str7);
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (!CoreExtsKt.isRelease()) {
                }
                m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                if (m1205exceptionOrNullimpl != null) {
                }
                Object obj3222 = obj2;
                Intrinsics.checkNotNull(obj3222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
                Triple triple222 = (Triple) obj3222;
                hVError = (HVError) triple222.component1();
                HVResponse hVResponse222 = (HVResponse) triple222.component2();
                T t222 = (T) ((JSONObject) triple222.component3());
                if (hVError != null) {
                }
            }
            String str222 = canonicalName5;
            sb.append(canonicalName5);
            sb.append(" - ");
            str7 = "retrieveState: hvData from file: " + obj;
            String str16222 = str7;
            if (str7 == null) {
            }
            sb.append(str7);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (!CoreExtsKt.isRelease()) {
            }
            m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
            if (m1205exceptionOrNullimpl != null) {
            }
            Object obj32222 = obj2;
            Intrinsics.checkNotNull(obj32222, "null cannot be cast to non-null type kotlin.Triple<co.hyperverge.hypersnapsdk.objects.HVError?, co.hyperverge.hypersnapsdk.objects.HVResponse?, org.json.JSONObject?>");
            Triple triple2222 = (Triple) obj32222;
            hVError = (HVError) triple2222.component1();
            HVResponse hVResponse2222 = (HVResponse) triple2222.component2();
            T t2222 = (T) ((JSONObject) triple2222.component3());
            if (hVError != null) {
            }
        } else {
            HyperLogger.Level level5 = HyperLogger.Level.DEBUG;
            HyperLogger companion17 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb9 = new StringBuilder();
            StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace9, "Throwable().stackTrace");
            StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
            if (stackTraceElement9 == null || (className8 = stackTraceElement9.getClassName()) == null) {
                str8 = "Throwable().stackTrace";
            } else {
                str8 = "Throwable().stackTrace";
                str9 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            Class<?> cls9 = getClass();
            String canonicalName8 = cls9 != null ? cls9.getCanonicalName() : null;
            str9 = canonicalName8 == null ? str : canonicalName8;
            Matcher matcher12 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
            if (matcher12.find()) {
                str9 = matcher12.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
            }
            Unit unit9 = Unit.INSTANCE;
            if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str9 = str9.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb9.append(str9);
            sb9.append(" - ");
            String str23 = "retrieveState: state targetFile exists : " + file2.exists();
            if (str23 == null) {
                str23 = "null ";
            }
            sb9.append(str23);
            sb9.append(' ');
            sb9.append("");
            companion17.log(level5, sb9.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion18 = Result.INSTANCE;
                    Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke5, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                } catch (Throwable th7) {
                    Result.Companion companion19 = Result.INSTANCE;
                    m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                    m1202constructorimpl6 = "";
                }
                String packageName5 = (String) m1202constructorimpl6;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName5, "packageName");
                    if (StringsKt.contains$default((CharSequence) packageName5, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace10, str8);
                        StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                        if (stackTraceElement10 == null || (className7 = stackTraceElement10.getClassName()) == null) {
                            str10 = null;
                        } else {
                            str10 = null;
                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default != null) {
                                str11 = substringAfterLast$default;
                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str11);
                                if (matcher2.find()) {
                                    str11 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str11, "replaceAll(\"\")");
                                }
                                Unit unit10 = Unit.INSTANCE;
                                if (str11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str11 = str11.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb10 = new StringBuilder();
                                String str24 = "retrieveState: state targetFile exists : " + file2.exists();
                                sb10.append(str24 != null ? "null " : str24);
                                sb10.append(' ');
                                sb10.append("");
                                Log.println(3, str11, sb10.toString());
                            }
                        }
                        Class<?> cls10 = getClass();
                        String canonicalName9 = cls10 != null ? cls10.getCanonicalName() : str10;
                        str11 = canonicalName9 == null ? str : canonicalName9;
                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str11);
                        if (matcher2.find()) {
                        }
                        Unit unit102 = Unit.INSTANCE;
                        if (str11.length() > 23) {
                            str11 = str11.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb102 = new StringBuilder();
                        String str242 = "retrieveState: state targetFile exists : " + file2.exists();
                        sb102.append(str242 != null ? "null " : str242);
                        sb102.append(' ');
                        sb102.append("");
                        Log.println(3, str11, sb102.toString());
                    }
                }
            }
        }
        throw new HSBridgeException(new HVError(117, "Workflow ended because of low memory"), new HVResponse());
    }
}
