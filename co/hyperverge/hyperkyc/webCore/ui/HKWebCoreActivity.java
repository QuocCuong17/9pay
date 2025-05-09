package co.hyperverge.hyperkyc.webCore.ui;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.os.BundleKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.common.MimeTypes;
import androidx.webkit.ProxyConfig;
import co.hyperverge.hyperkyc.HyperKyc;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt;
import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycResult;
import co.hyperverge.hyperkyc.databinding.HkActivityWebCoreBinding;
import co.hyperverge.hyperkyc.ui.HKBrowserActivity;
import co.hyperverge.hyperkyc.ui.WebViewFragment;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.nfc.NFCReaderInstructionFragment;
import co.hyperverge.hyperkyc.ui.nfc.WebCoreNFCReaderFragment;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.PicassoExtsKt;
import co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import co.hyperverge.hypersnapsdk.objects.HVError;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tekartik.sqflite.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: HKWebCoreActivity.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0003^_`B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010#\u001a\u00020$H\u0002J\u001a\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u000bH\u0002J\u0011\u0010)\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010*J\b\u0010+\u001a\u00020$H\u0002J\r\u0010,\u001a\u00020$H\u0001¢\u0006\u0002\b-J\u0019\u0010.\u001a\u00020$2\n\b\u0002\u0010/\u001a\u0004\u0018\u000100H\u0000¢\u0006\u0002\b1J\b\u00102\u001a\u00020$H\u0002J\b\u00103\u001a\u00020$H\u0017J\u0012\u00104\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u00107\u001a\u00020$H\u0014J\u001b\u00108\u001a\u00020$2\f\u00109\u001a\b\u0012\u0004\u0012\u0002000\u000eH\u0002¢\u0006\u0002\u0010:J\u0010\u0010;\u001a\u0002002\u0006\u0010<\u001a\u000200H\u0002J\u0010\u0010=\u001a\u00020$2\u0006\u0010>\u001a\u000200H\u0002J\u0015\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020AH\u0000¢\u0006\u0002\bBJ\u0010\u0010C\u001a\u00020$2\u0006\u0010D\u001a\u00020EH\u0002J\u0015\u0010F\u001a\u00020$2\u0006\u0010F\u001a\u00020EH\u0000¢\u0006\u0002\bGJ*\u0010H\u001a\b\u0012\u0004\u0012\u00020$0I2\u0006\u0010J\u001a\u00020KH\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ*\u0010N\u001a\b\u0012\u0004\u0012\u00020$0I2\u0006\u0010O\u001a\u00020PH\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bQ\u0010RJ*\u0010S\u001a\b\u0012\u0004\u0012\u00020$0I2\u0006\u0010T\u001a\u00020UH\u0082@ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020$2\u0006\u0010Y\u001a\u00020ZH\u0002J\u0010\u0010[\u001a\u00020$2\u0006\u0010\\\u001a\u00020]H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\b\u001a\u0004\b \u0010!\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006a"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lco/hyperverge/hyperkyc/databinding/HkActivityWebCoreBinding;", "getBinding$hyperkyc_release", "()Lco/hyperverge/hyperkyc/databinding/HkActivityWebCoreBinding;", "binding$delegate", "Lkotlin/Lazy;", "browserLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "filePickerLauncher", "kotlin.jvm.PlatformType", HyperKycConfig.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "getHyperKycConfig$hyperkyc_release", "()Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "hyperKycConfig$delegate", HyperKycConfig.ARG_REMOTE_CONFIG, "Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "getRemoteConfig", "()Lco/hyperverge/hyperkyc/core/hv/models/HSRemoteConfig;", "remoteConfig$delegate", TypedValues.AttributesType.S_TARGET, "Lcom/squareup/picasso/Target;", "webCoreVM", "Lco/hyperverge/hyperkyc/webCore/ui/WebCoreVM;", "getWebCoreVM", "()Lco/hyperverge/hyperkyc/webCore/ui/WebCoreVM;", "webCoreVM$delegate", "destroyWebView", "", "handleFilePickerResult", "resultCode", "", "data", "initHyperSnap", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initWebSDK", "initWebView", "initWebView$hyperkyc_release", "loadBackground", "url", "", "loadBackground$hyperkyc_release", "nativeBackPress", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openFilePicker", "acceptTypes", "([Ljava/lang/String;)V", "processBase64Strings", "inputString", "processFullResponse", Constant.PARAM_RESULT, "sendNativeModuleData", "webCoreNativeResponse", "Lco/hyperverge/hyperkyc/webCore/models/WebCoreNativeResponse;", "sendNativeModuleData$hyperkyc_release", "setSecureFlagForActivities", "secure", "", "showWebCoreFragment", "showWebCoreFragment$hyperkyc_release", "startBarcodeFlow", "Lkotlin/Result;", "barcodeFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;", "startBarcodeFlow-gIAlu-s", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startDocFlow", "docFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;", "startDocFlow-gIAlu-s", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startFaceFlow", "faceFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;", "startFaceFlow-gIAlu-s", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startNFCFlow", "nfcFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$NFCReader;", "startWebViewModule", "webViewUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$WebView;", "BrowserWebChromeClient", "BrowserWebClient", "JSInterface", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HKWebCoreActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> browserLauncher;
    private ValueCallback<Uri[]> filePathCallback;
    private final ActivityResultLauncher<Intent> filePickerLauncher;
    private Target target;

    /* renamed from: webCoreVM$delegate, reason: from kotlin metadata */
    private final Lazy webCoreVM;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<HkActivityWebCoreBinding>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HkActivityWebCoreBinding invoke() {
            return HkActivityWebCoreBinding.inflate(HKWebCoreActivity.this.getLayoutInflater());
        }
    });

    /* renamed from: hyperKycConfig$delegate, reason: from kotlin metadata */
    private final Lazy hyperKycConfig = LazyKt.lazy(new Function0<HyperKycConfig>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$hyperKycConfig$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x01df, code lost:
        
            if (r0 != null) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x01ef, code lost:
        
            if (r0 == null) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01f3, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0202, code lost:
        
            if (r0.find() == false) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0204, code lost:
        
            r11 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0211, code lost:
        
            if (r11.length() <= 23) goto L89;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0217, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x021a, code lost:
        
            r11 = r11.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0222, code lost:
        
            r0 = new java.lang.StringBuilder();
            r0.append("getSerializable: failed for key hyperKycConfig");
            r0.append(' ');
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x022f, code lost:
        
            if (r5 == null) goto L92;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0231, code lost:
        
            r2 = r5.getLocalizedMessage();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0237, code lost:
        
            if (r2 == null) goto L95;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0239, code lost:
        
            r12 = '\n' + r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0248, code lost:
        
            r0.append(r12);
            android.util.Log.println(6, r11, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0236, code lost:
        
            r2 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x01f2, code lost:
        
            r11 = r0;
         */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final HyperKycConfig invoke() {
            Object m1202constructorimpl;
            String str;
            String canonicalName;
            String str2;
            Object m1202constructorimpl2;
            String str3;
            String className;
            String className2;
            HyperKycConfig hyperKycConfig;
            if (HKWebCoreActivity.this.getIntent().hasExtra(HyperKycConfig.ARG_CACHE_FILE_PATH_KEY)) {
                String stringExtra = HKWebCoreActivity.this.getIntent().getStringExtra(HyperKycConfig.ARG_CACHE_FILE_PATH_KEY);
                Intrinsics.checkNotNull(stringExtra);
                if (new File(stringExtra).exists()) {
                    return (HyperKycConfig) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(FilesKt.readText$default(new File(stringExtra), null, 1, null), HyperKycConfig.class);
                }
                throw new IllegalStateException(("File not found at path: " + stringExtra + ", HyperKycConfig cannot be extracted").toString());
            }
            Intent intent = HKWebCoreActivity.this.getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            try {
                Result.Companion companion = Result.INSTANCE;
                if (Build.VERSION.SDK_INT >= 33) {
                    hyperKycConfig = intent.getSerializableExtra(HyperKycConfig.ARG_KEY, HyperKycConfig.class);
                } else {
                    Serializable serializableExtra = intent.getSerializableExtra(HyperKycConfig.ARG_KEY);
                    if (serializableExtra == null) {
                        throw new NullPointerException("null cannot be cast to non-null type co.hyperverge.hyperkyc.data.models.HyperKycConfig");
                    }
                    hyperKycConfig = (HyperKycConfig) serializableExtra;
                }
                m1202constructorimpl = Result.m1202constructorimpl(hyperKycConfig);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = m1202constructorimpl;
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
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
                sb.append("getSerializable: failed for key hyperKycConfig");
                sb.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str2 = '\n' + localizedMessage;
                } else {
                    str2 = "";
                }
                sb.append(str2);
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
                            str = null;
                        } else {
                            str = null;
                            str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = intent.getClass();
                        str3 = cls2 != null ? cls2.getCanonicalName() : str;
                    }
                }
                str = null;
            } else {
                str = null;
            }
            HyperKycConfig hyperKycConfig2 = (HyperKycConfig) ((Serializable) (Result.m1208isFailureimpl(obj) ? str : obj));
            if (hyperKycConfig2 != null) {
                return hyperKycConfig2;
            }
            throw new IllegalStateException("HyperKycConfig cannot be null".toString());
        }
    });

    /* renamed from: remoteConfig$delegate, reason: from kotlin metadata */
    private final Lazy remoteConfig = LazyKt.lazy(new Function0<HSRemoteConfig>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$remoteConfig$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x017b, code lost:
        
            if (r0 != null) goto L73;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x018b, code lost:
        
            if (r0 == null) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x018f, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x019e, code lost:
        
            if (r0.find() == false) goto L77;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x01a0, code lost:
        
            r11 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x01ad, code lost:
        
            if (r11.length() <= 23) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x01b3, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L82;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x01b6, code lost:
        
            r11 = r11.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x01be, code lost:
        
            r0 = new java.lang.StringBuilder();
            r0.append("getSerializable: failed for key remoteConfig");
            r0.append(' ');
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x01cb, code lost:
        
            if (r4 == null) goto L86;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x01cd, code lost:
        
            r2 = r4.getLocalizedMessage();
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x01d3, code lost:
        
            if (r2 == null) goto L89;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x01d5, code lost:
        
            r12 = '\n' + r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x01e4, code lost:
        
            r0.append(r12);
            android.util.Log.println(6, r11, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x01d2, code lost:
        
            r2 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x018e, code lost:
        
            r11 = r0;
         */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final HSRemoteConfig invoke() {
            Object m1202constructorimpl;
            String str;
            String canonicalName;
            String str2;
            Object m1202constructorimpl2;
            String str3;
            String className;
            String className2;
            HSRemoteConfig hSRemoteConfig;
            Intent intent = HKWebCoreActivity.this.getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            try {
                Result.Companion companion = Result.INSTANCE;
                if (Build.VERSION.SDK_INT >= 33) {
                    hSRemoteConfig = intent.getSerializableExtra(HyperKycConfig.ARG_REMOTE_CONFIG, HSRemoteConfig.class);
                } else {
                    Serializable serializableExtra = intent.getSerializableExtra(HyperKycConfig.ARG_REMOTE_CONFIG);
                    if (serializableExtra == null) {
                        throw new NullPointerException("null cannot be cast to non-null type co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig");
                    }
                    hSRemoteConfig = (HSRemoteConfig) serializableExtra;
                }
                m1202constructorimpl = Result.m1202constructorimpl(hSRemoteConfig);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = m1202constructorimpl;
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj);
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
                sb.append("getSerializable: failed for key remoteConfig");
                sb.append(' ');
                String localizedMessage = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                if (localizedMessage != null) {
                    str2 = '\n' + localizedMessage;
                } else {
                    str2 = "";
                }
                sb.append(str2);
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
                            str = null;
                        } else {
                            str = null;
                            str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        Class<?> cls2 = intent.getClass();
                        str3 = cls2 != null ? cls2.getCanonicalName() : str;
                    }
                }
                str = null;
            } else {
                str = null;
            }
            if (Result.m1208isFailureimpl(obj)) {
                obj = str;
            }
            HSRemoteConfig hSRemoteConfig2 = (HSRemoteConfig) ((Serializable) obj);
            if (hSRemoteConfig2 != null) {
                return hSRemoteConfig2;
            }
            throw new IllegalStateException("RemoteConfig cannot be null".toString());
        }
    });

    public HKWebCoreActivity() {
        final HKWebCoreActivity hKWebCoreActivity = this;
        final Function0 function0 = null;
        this.webCoreVM = new ViewModelLazy(Reflection.getOrCreateKotlinClass(WebCoreVM.class), new Function0<ViewModelStore>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        }, new Function0<CreationExtras>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = hKWebCoreActivity.getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "this.defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        });
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                HKWebCoreActivity.filePickerLauncher$lambda$1(HKWebCoreActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…e, result.data)\n        }");
        this.filePickerLauncher = registerForActivityResult;
        ActivityResultLauncher<Intent> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                HKWebCoreActivity.browserLauncher$lambda$7(HKWebCoreActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…        }\n        }\n    }");
        this.browserLauncher = registerForActivityResult2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WebCoreVM getWebCoreVM() {
        return (WebCoreVM) this.webCoreVM.getValue();
    }

    public final HkActivityWebCoreBinding getBinding$hyperkyc_release() {
        return (HkActivityWebCoreBinding) this.binding.getValue();
    }

    public final HyperKycConfig getHyperKycConfig$hyperkyc_release() {
        Object value = this.hyperKycConfig.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-hyperKycConfig>(...)");
        return (HyperKycConfig) value;
    }

    private final HSRemoteConfig getRemoteConfig() {
        return (HSRemoteConfig) this.remoteConfig.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void browserLauncher$lambda$7(HKWebCoreActivity this$0, ActivityResult activityResult) {
        String stringExtra;
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int resultCode = activityResult.getResultCode();
        if (resultCode != -1) {
            if (resultCode == 0) {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKWebCoreActivity$browserLauncher$1$2(this$0, null), 3, null);
                return;
            } else if (resultCode != 2) {
                return;
            }
        }
        int resultCode2 = activityResult.getResultCode();
        if (resultCode2 != -1) {
            if (resultCode2 == 2 && (data = activityResult.getData()) != null) {
                stringExtra = data.getStringExtra("error");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKWebCoreActivity$browserLauncher$1$1(this$0, stringExtra, null), 3, null);
            }
            stringExtra = null;
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKWebCoreActivity$browserLauncher$1$1(this$0, stringExtra, null), 3, null);
        }
        Intent data2 = activityResult.getData();
        if (data2 != null) {
            stringExtra = data2.getStringExtra("response");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKWebCoreActivity$browserLauncher$1$1(this$0, stringExtra, null), 3, null);
        }
        stringExtra = null;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new HKWebCoreActivity$browserLauncher$1$1(this$0, stringExtra, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        destroyWebView();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HKWebCoreActivity.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity$BrowserWebClient;", "Landroid/webkit/WebViewClient;", "(Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity;)V", "onPageFinished", "", "webView", "Landroid/webkit/WebView;", "url", "", "onReceivedError", ViewHierarchyConstants.VIEW_KEY, "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "shouldInterceptRequest", "Landroid/webkit/WebResourceResponse;", "shouldOverrideUrlLoading", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class BrowserWebClient extends WebViewClient {
        public BrowserWebClient() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0146, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0156, code lost:
        
            if (r0 == null) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x015a, code lost:
        
            r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0169, code lost:
        
            if (r0.find() == false) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x016b, code lost:
        
            r8 = r0.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0178, code lost:
        
            if (r8.length() <= 23) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x017e, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0181, code lost:
        
            r8 = r8.substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0188, code lost:
        
            r0 = new java.lang.StringBuilder();
            r1 = "onReceivedError() called with: view = " + r18 + ", request = " + r19 + ", error = " + r20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x01a8, code lost:
        
            if (r1 != null) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x01aa, code lost:
        
            r1 = "null ";
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x01ac, code lost:
        
            r0.append(r1);
            r0.append(' ');
            r0.append("");
            android.util.Log.println(3, r8, r0.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x01bf, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0159, code lost:
        
            r8 = r0;
         */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String className;
            String className2;
            super.onReceivedError(view, request, error);
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
            String str4 = "onReceivedError() called with: view = " + view + ", request = " + request + ", error = " + error;
            if (str4 == null) {
                str4 = "null ";
            }
            sb.append(str4);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                return;
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
                return;
            }
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                return;
            }
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

        /* JADX WARN: Can't wrap try/catch for region: R(16:1|(3:141|(1:143)(1:146)|(1:145))|7|(1:9)|10|(1:14)|15|(1:17)|18|(1:20)(9:98|99|100|101|102|103|(1:105)|106|(7:108|(9:110|(3:128|(1:130)(1:133)|(1:132))|116|(1:118)|119|(1:123)|124|(1:126)|127)|22|23|24|25|(15:27|(3:88|(1:90)(1:93)|(1:92))|33|(1:35)|36|(1:40)|41|(1:43)|44|45|46|47|(1:49)|50|(2:52|(13:54|(1:82)(2:58|(9:60|61|(1:63)|64|(1:68)|69|(1:71)|72|73))|75|(1:77)(1:81)|(1:79)(1:80)|61|(0)|64|(2:66|68)|69|(0)|72|73)(1:83))(1:84))(1:94))(1:134))|21|22|23|24|25|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x01e0, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x01e1, code lost:
        
            r2 = kotlin.Result.INSTANCE;
            r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
         */
        /* JADX WARN: Removed duplicated region for block: B:105:0x0115  */
        /* JADX WARN: Removed duplicated region for block: B:108:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:134:0x01cb  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x01f1  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0333  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0367  */
        /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onPageFinished(WebView webView, String url) {
            String canonicalName;
            String str;
            String str2;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str3;
            String canonicalName2;
            String className;
            Throwable m1205exceptionOrNullimpl;
            String canonicalName3;
            Object m1202constructorimpl2;
            String str4;
            String str5;
            Matcher matcher;
            String str6;
            String className2;
            String className3;
            String className4;
            super.onPageFinished(webView, url);
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
            String str7 = "onPageFinished() called with: webView = " + webView + ", url = " + url;
            if (str7 == null) {
                str7 = "null ";
            }
            sb.append(str7);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str = " - ";
                str2 = "N/A";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = " - ";
                    str2 = "N/A";
                } catch (Throwable th) {
                    th = th;
                    str = " - ";
                    str2 = "N/A";
                }
                try {
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    }
                    String packageName = (String) m1202constructorimpl;
                    if (!CoreExtsKt.isDebug()) {
                    }
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = str2;
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
                        StringBuilder sb2 = new StringBuilder();
                        String str8 = "onPageFinished() called with: webView = " + webView + ", url = " + url;
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb2.append(str8);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    HKWebCoreActivity hKWebCoreActivity = HKWebCoreActivity.this;
                    Result.Companion companion4 = Result.INSTANCE;
                    BrowserWebClient browserWebClient = this;
                    hKWebCoreActivity.initWebSDK();
                    Object m1202constructorimpl3 = Result.m1202constructorimpl(Unit.INSTANCE);
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                    if (m1205exceptionOrNullimpl == null) {
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                            if (canonicalName3 == null) {
                                canonicalName3 = str2;
                            }
                        }
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName3);
                        if (matcher4.find()) {
                            canonicalName3 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                        }
                        if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName3 = canonicalName3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(canonicalName3);
                        sb3.append(str);
                        String str9 = "onPageFinished(): " + m1205exceptionOrNullimpl;
                        if (str9 == null) {
                            str9 = "null ";
                        }
                        sb3.append(str9);
                        sb3.append(' ');
                        sb3.append("");
                        companion5.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion6 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion7 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String str10 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(str10, str3);
                            if (StringsKt.contains$default((CharSequence) str10, charSequence, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                                    str4 = null;
                                } else {
                                    str4 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        str5 = substringAfterLast$default;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                        if (matcher.find()) {
                                            str5 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                        }
                                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str5 = str5.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        str6 = "onPageFinished(): " + m1205exceptionOrNullimpl;
                                        if (str6 == null) {
                                            str6 = "null ";
                                        }
                                        sb4.append(str6);
                                        sb4.append(' ');
                                        sb4.append("");
                                        Log.println(6, str5, sb4.toString());
                                        return;
                                    }
                                }
                                Class<?> cls4 = getClass();
                                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                                str5 = canonicalName4 == null ? str2 : canonicalName4;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher.find()) {
                                }
                                if (str5.length() > 23) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb42 = new StringBuilder();
                                str6 = "onPageFinished(): " + m1205exceptionOrNullimpl;
                                if (str6 == null) {
                                }
                                sb42.append(str6);
                                sb42.append(' ');
                                sb42.append("");
                                Log.println(6, str5, sb42.toString());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                charSequence = "co.hyperverge";
            }
            str3 = "packageName";
            HKWebCoreActivity hKWebCoreActivity2 = HKWebCoreActivity.this;
            Result.Companion companion42 = Result.INSTANCE;
            BrowserWebClient browserWebClient2 = this;
            hKWebCoreActivity2.initWebSDK();
            Object m1202constructorimpl32 = Result.m1202constructorimpl(Unit.INSTANCE);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl32);
            if (m1205exceptionOrNullimpl == null) {
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:109:0x0112  */
        /* JADX WARN: Removed duplicated region for block: B:112:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:138:0x01cc  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x01d6  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0334  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x036e  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0371  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String canonicalName;
            String str;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str2;
            String canonicalName2;
            String className;
            Uri url;
            String str3;
            Object m1202constructorimpl2;
            String canonicalName3;
            String str4;
            Matcher matcher;
            String className2;
            String className3;
            String className4;
            Intrinsics.checkNotNullParameter(request, "request");
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
            String str5 = "shouldInterceptRequest() called with: view = " + view + ", request = " + request.getUrl();
            if (str5 == null) {
                str5 = "null ";
            }
            sb.append(str5);
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                charSequence = "co.hyperverge";
                str = "N/A";
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = "N/A";
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
                        if (!CoreExtsKt.isDebug()) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = "N/A";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str2 = "null ";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str6 = "shouldInterceptRequest() called with: view = " + view + ", request = " + request.getUrl();
                        if (str6 == null) {
                            str6 = str2;
                        }
                        sb2.append(str6);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    url = request.getUrl();
                    if (url != null) {
                        String uri = url.toString();
                        Intrinsics.checkNotNullExpressionValue(uri, "uri.toString()");
                        if (StringsKt.contains$default((CharSequence) uri, (CharSequence) WebCoreVM.CUSTOM_SCHEME, false, 2, (Object) null)) {
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb3 = new StringBuilder();
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls3 = getClass();
                                String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                                str3 = canonicalName4 == null ? str : canonicalName4;
                            }
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                            if (matcher4.find()) {
                                str3 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                            }
                            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str3 = str3.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb3.append(str3);
                            sb3.append(" - ");
                            String str7 = "shouldInterceptRequest() called with: uri.path = " + url.getPath();
                            if (str7 == null) {
                                str7 = str2;
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
                                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th3) {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                    m1202constructorimpl2 = "";
                                }
                                String packageName3 = (String) m1202constructorimpl2;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                                    if (StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            Class<?> cls4 = getClass();
                                            canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                            if (canonicalName3 == null) {
                                                str4 = str;
                                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                                if (matcher.find()) {
                                                    str4 = matcher.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                                                }
                                                if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str4 = str4.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb4 = new StringBuilder();
                                                String str8 = "shouldInterceptRequest() called with: uri.path = " + url.getPath();
                                                sb4.append(str8 != null ? str2 : str8);
                                                sb4.append(' ');
                                                sb4.append("");
                                                Log.println(3, str4, sb4.toString());
                                            }
                                        }
                                        str4 = canonicalName3;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                                        if (matcher.find()) {
                                        }
                                        if (str4.length() > 23) {
                                            str4 = str4.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb42 = new StringBuilder();
                                        String str82 = "shouldInterceptRequest() called with: uri.path = " + url.getPath();
                                        sb42.append(str82 != null ? str2 : str82);
                                        sb42.append(' ');
                                        sb42.append("");
                                        Log.println(3, str4, sb42.toString());
                                    }
                                }
                            }
                            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url.toString()));
                            File file = new File(HKWebCoreActivity.this.getFilesDir(), "hv/" + url.getLastPathSegment());
                            if (file.exists()) {
                                try {
                                    FileInputStream fileInputStream = new FileInputStream(file);
                                    HashMap hashMap = new HashMap();
                                    hashMap.put(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ProxyConfig.MATCH_ALL_SCHEMES);
                                    return new WebResourceResponse(mimeTypeFromExtension, null, 200, "OK", hashMap, fileInputStream);
                                } catch (IOException unused) {
                                    return null;
                                }
                            }
                            return super.shouldInterceptRequest(view, request);
                        }
                    }
                    return super.shouldInterceptRequest(view, request);
                }
                charSequence = "co.hyperverge";
            }
            str2 = "null ";
            url = request.getUrl();
            if (url != null) {
            }
            return super.shouldInterceptRequest(view, request);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            Uri url;
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
            String str2 = "shouldOverrideUrlLoading() called with: view = " + view + ", request = " + request;
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
                        String str3 = "shouldOverrideUrlLoading() called with: view = " + view + ", request = " + request;
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
            if (view == null || request == null || (url = request.getUrl()) == null) {
                return false;
            }
            Intent intent = new Intent("android.intent.action.VIEW", url);
            Context context = view.getContext();
            if (context == null) {
                return true;
            }
            context.startActivity(intent);
            return true;
        }
    }

    /* compiled from: HKWebCoreActivity.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity$BrowserWebChromeClient;", "Landroid/webkit/WebChromeClient;", "(Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity;)V", "onPermissionRequest", "", "request", "Landroid/webkit/PermissionRequest;", "onShowFileChooser", "", "webView", "Landroid/webkit/WebView;", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "fileChooserParams", "Landroid/webkit/WebChromeClient$FileChooserParams;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class BrowserWebChromeClient extends WebChromeClient {
        public BrowserWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onPermissionRequest(PermissionRequest request) {
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
            String str2 = "onPermissionRequest() called with: request = [" + request + ']';
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
                        String str3 = "onPermissionRequest() called with: request = [" + request + ']';
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
            if (request != null) {
                request.grant(request.getResources());
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(webView, "webView");
            Intrinsics.checkNotNullParameter(filePathCallback, "filePathCallback");
            Intrinsics.checkNotNullParameter(fileChooserParams, "fileChooserParams");
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
            String str2 = "onShowFileChooser() called with: webView = [" + webView + "], filePathCallback = [" + filePathCallback + "], fileChooserParams = [" + fileChooserParams + ']';
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
                            str = canonicalName2 == null ? "N/A" : canonicalName2;
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
                        String str3 = "onShowFileChooser() called with: webView = [" + webView + "], filePathCallback = [" + filePathCallback + "], fileChooserParams = [" + fileChooserParams + ']';
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
            ValueCallback valueCallback = HKWebCoreActivity.this.filePathCallback;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
            }
            HKWebCoreActivity.this.filePathCallback = filePathCallback;
            HKWebCoreActivity hKWebCoreActivity = HKWebCoreActivity.this;
            String[] acceptTypes = fileChooserParams.getAcceptTypes();
            Intrinsics.checkNotNullExpressionValue(acceptTypes, "fileChooserParams.acceptTypes");
            hKWebCoreActivity.openFilePicker(acceptTypes);
            return true;
        }
    }

    /* compiled from: HKWebCoreActivity.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0007J \u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\tH\u0007J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\tH\u0007J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0007R\u0012\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity$JSInterface;", "", "(Lco/hyperverge/hyperkyc/webCore/ui/HKWebCoreActivity;)V", "responseBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "executeNativeModule", "", AnalyticsLogger.Keys.MODULE_TYPE, "", "moduleConfig", "logAnalytics", "eventKey", "eventProps", "receiveChunk", "chunk", FirebaseAnalytics.Param.INDEX, "", "totalChunks", "setJourneyId", "journeyId", "setNativeUIConfig", HVRetakeActivity.CONFIG_TAG, "setSecureFlag", "secure", "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public final class JSInterface {
        private final StringBuilder responseBuilder = new StringBuilder();

        public JSInterface() {
        }

        @JavascriptInterface
        public final void setNativeUIConfig(String config) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(config, "config");
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
            sb.append("setUIConfig() called");
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
                        Log.println(3, str, "setUIConfig() called ");
                    }
                }
            }
            HSUIConfig hSUIConfig = (HSUIConfig) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(config, HSUIConfig.class);
            hSUIConfig.setUIConfigJSON(config);
            HyperSnapBridgeKt.setUIConfig(HKWebCoreActivity.this, hSUIConfig);
        }

        /* JADX WARN: Code restructure failed: missing block: B:80:0x0159, code lost:
        
            if (r0 == null) goto L55;
         */
        @JavascriptInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void executeNativeModule(String moduleType, String moduleConfig) {
            String canonicalName;
            Object m1202constructorimpl;
            String canonicalName2;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(moduleType, "moduleType");
            Intrinsics.checkNotNullParameter(moduleConfig, "moduleConfig");
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
            String str2 = "executeNativeModule() called with: moduleType = [" + moduleType + "], moduleConfig = [" + moduleConfig + ']';
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
                        StringBuilder sb2 = new StringBuilder();
                        String str3 = "executeNativeModule() called with: moduleType = [" + moduleType + "], moduleConfig = [" + moduleConfig + ']';
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
            switch (moduleType.hashCode()) {
                case -333584256:
                    if (moduleType.equals("barcode")) {
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKWebCoreActivity.this), null, null, new HKWebCoreActivity$JSInterface$executeNativeModule$4(HKWebCoreActivity.this, (WorkflowUIState.BarcodeCapture) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(moduleConfig, WorkflowUIState.BarcodeCapture.class), null), 3, null);
                        return;
                    }
                    return;
                case 108971:
                    if (moduleType.equals(WorkflowModule.TYPE_NFC)) {
                        WorkflowUIState.NFCReader nFCReader = (WorkflowUIState.NFCReader) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(moduleConfig, WorkflowUIState.NFCReader.class);
                        HKWebCoreActivity.this.getWebCoreVM().setAllowNativeBackPress$hyperkyc_release(nFCReader.getShowBackButton());
                        HKWebCoreActivity.this.showWebCoreFragment$hyperkyc_release(false);
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKWebCoreActivity.this), null, null, new HKWebCoreActivity$JSInterface$executeNativeModule$6(HKWebCoreActivity.this, nFCReader, null), 3, null);
                        return;
                    }
                    return;
                case 3135069:
                    if (moduleType.equals("face")) {
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKWebCoreActivity.this), null, null, new HKWebCoreActivity$JSInterface$executeNativeModule$3(HKWebCoreActivity.this, (WorkflowUIState.FaceCapture) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(moduleConfig, WorkflowUIState.FaceCapture.class), null), 3, null);
                        return;
                    }
                    return;
                case 861720859:
                    if (moduleType.equals(WorkflowModule.TYPE_DOCUMENT)) {
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKWebCoreActivity.this), null, null, new HKWebCoreActivity$JSInterface$executeNativeModule$2(HKWebCoreActivity.this, (WorkflowUIState.DocCapture) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(moduleConfig, WorkflowUIState.DocCapture.class), null), 3, null);
                        return;
                    }
                    return;
                case 1224424441:
                    if (moduleType.equals(WorkflowModule.TYPE_WEBVIEW)) {
                        WorkflowUIState.WebView webView = (WorkflowUIState.WebView) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(moduleConfig, WorkflowUIState.WebView.class);
                        HKWebCoreActivity.this.getWebCoreVM().setAllowNativeBackPress$hyperkyc_release(webView.getShowBackButton());
                        HKWebCoreActivity.this.showWebCoreFragment$hyperkyc_release(false);
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(HKWebCoreActivity.this), null, null, new HKWebCoreActivity$JSInterface$executeNativeModule$5(HKWebCoreActivity.this, webView, null), 3, null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x0148, code lost:
        
            if (r0 != null) goto L55;
         */
        /* JADX WARN: Removed duplicated region for block: B:48:0x016f  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01a9  */
        @JavascriptInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void receiveChunk(String chunk, int index, int totalChunks) {
            String canonicalName;
            Object m1202constructorimpl;
            String str;
            String str2;
            String str3;
            Matcher matcher;
            String str4;
            String className;
            String className2;
            Intrinsics.checkNotNullParameter(chunk, "chunk");
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
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Received chunk ");
            int i = index + 1;
            sb2.append(i);
            sb2.append(" of ");
            sb2.append(totalChunks);
            String sb3 = sb2.toString();
            if (sb3 == null) {
                sb3 = "null ";
            }
            sb.append(sb3);
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
                            StringBuilder sb4 = new StringBuilder();
                            str4 = "Received chunk " + i + " of " + totalChunks;
                            if (str4 == null) {
                                str4 = "null ";
                            }
                            sb4.append(str4);
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str3, sb4.toString());
                        }
                        str3 = str2;
                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
                        if (matcher.find()) {
                        }
                        if (str3.length() > 23) {
                            str3 = str3.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb42 = new StringBuilder();
                        str4 = "Received chunk " + i + " of " + totalChunks;
                        if (str4 == null) {
                        }
                        sb42.append(str4);
                        sb42.append(' ');
                        sb42.append("");
                        Log.println(3, str3, sb42.toString());
                    }
                }
            }
            this.responseBuilder.append(chunk);
            if (index == totalChunks - 1) {
                String sb5 = this.responseBuilder.toString();
                Intrinsics.checkNotNullExpressionValue(sb5, "responseBuilder.toString()");
                HKWebCoreActivity.this.processFullResponse(sb5);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x023d  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0274  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0290 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x032c  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0362  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0220  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0228  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x022b  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x0225  */
        @JavascriptInterface
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void logAnalytics(String eventKey, String eventProps) {
            String canonicalName;
            Object m1202constructorimpl;
            CharSequence charSequence;
            String str;
            String canonicalName2;
            String className;
            StackTraceElement stackTraceElement;
            String str2;
            Matcher matcher;
            String str3;
            Object m1202constructorimpl2;
            String str4;
            String str5;
            Matcher matcher2;
            String str6;
            String className2;
            String className3;
            String className4;
            Intrinsics.checkNotNullParameter(eventKey, "eventKey");
            Intrinsics.checkNotNullParameter(eventProps, "eventProps");
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
            String str7 = "logAnalytics() called with: eventKey = [" + eventKey + "], eventProps = [" + eventProps + ']';
            if (str7 == null) {
                str7 = "null ";
            }
            sb.append(str7);
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
                        String str8 = "logAnalytics() called with: eventKey = [" + eventKey + "], eventProps = [" + eventProps + ']';
                        if (str8 == null) {
                            str8 = "null ";
                        }
                        sb2.append(str8);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    Map<String, Object> eventPropsMap = (Map) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(eventProps, new TypeToken<Map<String, Object>>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$JSInterface$logAnalytics$typeToken$1
                    }.getType());
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement != null || (className3 = stackTraceElement.getClassName()) == null || (str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls3 = getClass();
                        String canonicalName3 = cls3 == null ? cls3.getCanonicalName() : null;
                        str2 = canonicalName3 != null ? str : canonicalName3;
                    }
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
                    if (matcher.find()) {
                        str2 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str2);
                    sb3.append(" - ");
                    str3 = "logAnalytics() eventPropsMap = " + eventPropsMap;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb3.append(str3);
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
                                    str4 = null;
                                } else {
                                    str4 = null;
                                    String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default != null) {
                                        str5 = substringAfterLast$default;
                                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                        if (matcher2.find()) {
                                            str5 = matcher2.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                        }
                                        if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str5 = str5.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb4 = new StringBuilder();
                                        str6 = "logAnalytics() eventPropsMap = " + eventPropsMap;
                                        if (str6 == null) {
                                            str6 = "null ";
                                        }
                                        sb4.append(str6);
                                        sb4.append(' ');
                                        sb4.append("");
                                        Log.println(3, str5, sb4.toString());
                                    }
                                }
                                Class<?> cls4 = getClass();
                                String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                                str5 = canonicalName4 == null ? str : canonicalName4;
                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                if (matcher2.find()) {
                                }
                                if (str5.length() > 23) {
                                    str5 = str5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb42 = new StringBuilder();
                                str6 = "logAnalytics() eventPropsMap = " + eventPropsMap;
                                if (str6 == null) {
                                }
                                sb42.append(str6);
                                sb42.append(' ');
                                sb42.append("");
                                Log.println(3, str5, sb42.toString());
                            }
                        }
                    }
                    AnalyticsLogger analyticsLogger = AnalyticsLogger.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(eventPropsMap, "eventPropsMap");
                    analyticsLogger.logEvent$hyperkyc_release(eventKey, eventPropsMap);
                }
            }
            charSequence = "co.hyperverge";
            str = "N/A";
            Map<String, Object> eventPropsMap2 = (Map) HKWebCoreActivity.this.getWebCoreVM().getGson().fromJson(eventProps, new TypeToken<Map<String, Object>>() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$JSInterface$logAnalytics$typeToken$1
            }.getType());
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
            if (canonicalName3 != null) {
            }
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
            if (matcher.find()) {
            }
            if (str2.length() > 23) {
                str2 = str2.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb32.append(str2);
            sb32.append(" - ");
            str3 = "logAnalytics() eventPropsMap = " + eventPropsMap2;
            if (str3 == null) {
            }
            sb32.append(str3);
            sb32.append(' ');
            sb32.append("");
            companion42.log(level22, sb32.toString());
            if (!CoreExtsKt.isRelease()) {
            }
            AnalyticsLogger analyticsLogger2 = AnalyticsLogger.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(eventPropsMap2, "eventPropsMap");
            analyticsLogger2.logEvent$hyperkyc_release(eventKey, eventPropsMap2);
        }

        @JavascriptInterface
        public final void setJourneyId(String journeyId) {
            String canonicalName;
            Object m1202constructorimpl;
            String className;
            String substringAfterLast$default;
            String className2;
            Intrinsics.checkNotNullParameter(journeyId, "journeyId");
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
            String str2 = "setJourneyId() called with: journeyId = [" + journeyId + ']';
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
                        String str3 = "setJourneyId() called with: journeyId = [" + journeyId + ']';
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
            HKWebCoreActivity.this.getWebCoreVM().setJourneyId$hyperkyc_release(journeyId);
        }

        @JavascriptInterface
        public final void setSecureFlag(boolean secure) {
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
            String str2 = "setSecureFlag() called with: secure = [" + secure + ']';
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
                        String str3 = "setSecureFlag() called with: secure = [" + secure + ']';
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
            if (secure) {
                HKWebCoreActivity.this.getWebCoreVM().setSecure$hyperkyc_release(true);
                HKWebCoreActivity.this.setSecureFlagForActivities(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startDocFlow-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m442startDocFlowgIAlus(WorkflowUIState.DocCapture docCapture, Continuation<? super Result<Unit>> continuation) {
        HKWebCoreActivity$startDocFlow$1 hKWebCoreActivity$startDocFlow$1;
        int i;
        if (continuation instanceof HKWebCoreActivity$startDocFlow$1) {
            hKWebCoreActivity$startDocFlow$1 = (HKWebCoreActivity$startDocFlow$1) continuation;
            if ((hKWebCoreActivity$startDocFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKWebCoreActivity$startDocFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKWebCoreActivity$startDocFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKWebCoreActivity$startDocFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKWebCoreActivity$startDocFlow$2 hKWebCoreActivity$startDocFlow$2 = new HKWebCoreActivity$startDocFlow$2(this, docCapture, null);
                    hKWebCoreActivity$startDocFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKWebCoreActivity$startDocFlow$2, hKWebCoreActivity$startDocFlow$1);
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
        hKWebCoreActivity$startDocFlow$1 = new HKWebCoreActivity$startDocFlow$1(this, continuation);
        Object obj2 = hKWebCoreActivity$startDocFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKWebCoreActivity$startDocFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startBarcodeFlow-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m441startBarcodeFlowgIAlus(WorkflowUIState.BarcodeCapture barcodeCapture, Continuation<? super Result<Unit>> continuation) {
        HKWebCoreActivity$startBarcodeFlow$1 hKWebCoreActivity$startBarcodeFlow$1;
        int i;
        if (continuation instanceof HKWebCoreActivity$startBarcodeFlow$1) {
            hKWebCoreActivity$startBarcodeFlow$1 = (HKWebCoreActivity$startBarcodeFlow$1) continuation;
            if ((hKWebCoreActivity$startBarcodeFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKWebCoreActivity$startBarcodeFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKWebCoreActivity$startBarcodeFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKWebCoreActivity$startBarcodeFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKWebCoreActivity$startBarcodeFlow$2 hKWebCoreActivity$startBarcodeFlow$2 = new HKWebCoreActivity$startBarcodeFlow$2(this, barcodeCapture, null);
                    hKWebCoreActivity$startBarcodeFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKWebCoreActivity$startBarcodeFlow$2, hKWebCoreActivity$startBarcodeFlow$1);
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
        hKWebCoreActivity$startBarcodeFlow$1 = new HKWebCoreActivity$startBarcodeFlow$1(this, continuation);
        Object obj2 = hKWebCoreActivity$startBarcodeFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKWebCoreActivity$startBarcodeFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: startFaceFlow-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m443startFaceFlowgIAlus(WorkflowUIState.FaceCapture faceCapture, Continuation<? super Result<Unit>> continuation) {
        HKWebCoreActivity$startFaceFlow$1 hKWebCoreActivity$startFaceFlow$1;
        int i;
        if (continuation instanceof HKWebCoreActivity$startFaceFlow$1) {
            hKWebCoreActivity$startFaceFlow$1 = (HKWebCoreActivity$startFaceFlow$1) continuation;
            if ((hKWebCoreActivity$startFaceFlow$1.label & Integer.MIN_VALUE) != 0) {
                hKWebCoreActivity$startFaceFlow$1.label -= Integer.MIN_VALUE;
                Object obj = hKWebCoreActivity$startFaceFlow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKWebCoreActivity$startFaceFlow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    HKWebCoreActivity$startFaceFlow$2 hKWebCoreActivity$startFaceFlow$2 = new HKWebCoreActivity$startFaceFlow$2(this, faceCapture, null);
                    hKWebCoreActivity$startFaceFlow$1.label = 1;
                    obj = CoroutineScopeKt.coroutineScope(hKWebCoreActivity$startFaceFlow$2, hKWebCoreActivity$startFaceFlow$1);
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
        hKWebCoreActivity$startFaceFlow$1 = new HKWebCoreActivity$startFaceFlow$1(this, continuation);
        Object obj2 = hKWebCoreActivity$startFaceFlow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKWebCoreActivity$startFaceFlow$1.label;
        if (i != 0) {
        }
        return ((Result) obj2).getValue();
    }

    public static /* synthetic */ void loadBackground$hyperkyc_release$default(HKWebCoreActivity hKWebCoreActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        hKWebCoreActivity.loadBackground$hyperkyc_release(str);
    }

    public final void loadBackground$hyperkyc_release(String url) {
        this.target = new Target() { // from class: co.hyperverge.hyperkyc.webCore.ui.HKWebCoreActivity$loadBackground$1
            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String canonicalName;
                Object m1202constructorimpl;
                String className;
                String substringAfterLast$default;
                String className2;
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                Intrinsics.checkNotNullParameter(from, "from");
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
                sb.append("Background image loaded");
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
                            Log.println(3, str, "Background image loaded ");
                        }
                    }
                }
                HKWebCoreActivity.this.getBinding$hyperkyc_release().flContent.setBackground(new BitmapDrawable(HKWebCoreActivity.this.getResources(), bitmap));
            }

            /* JADX WARN: Code restructure failed: missing block: B:49:0x0122, code lost:
            
                if (r0 == null) goto L52;
             */
            @Override // com.squareup.picasso.Target
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                String canonicalName;
                Object m1202constructorimpl;
                String canonicalName2;
                String className;
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
                sb.append("Background image load failed");
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
                            Log.println(3, str, "Background image load failed ");
                        }
                    }
                }
                HKWebCoreActivity.this.getBinding$hyperkyc_release().flContent.setBackground(null);
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable placeHolderDrawable) {
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
                sb.append("Background image loading");
                sb.append(' ');
                sb.append("");
                companion.log(level, sb.toString());
                if (CoreExtsKt.isRelease()) {
                    return;
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
                        Log.println(3, str, "Background image loading ");
                    }
                }
            }
        };
        String str = url;
        Target target = null;
        if (!(str == null || str.length() == 0)) {
            FrameLayout frameLayout = getBinding$hyperkyc_release().flContent;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
            Target target2 = this.target;
            if (target2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(TypedValues.AttributesType.S_TARGET);
            } else {
                target = target2;
            }
            PicassoExtsKt.loadBackgroundImage(frameLayout, url, target);
            return;
        }
        getBinding$hyperkyc_release().flContent.setBackground(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.lang.Long] */
    private final String processBase64Strings(String inputString) {
        String str;
        while (true) {
            String str2 = inputString;
            for (MatchResult matchResult : Regex.findAll$default(new Regex("data:(.*?);(?:df:(.*?);)?base64,([A-Za-z0-9+/=]+)"), inputString, 0, 2, null)) {
                String str3 = matchResult.getGroupValues().get(1);
                String str4 = matchResult.getGroupValues().get(2);
                byte[] decode = Base64.decode(matchResult.getGroupValues().get(3), 0);
                switch (str3.hashCode()) {
                    case -1487394660:
                        if (str3.equals("image/jpeg")) {
                            str = "jpg";
                            break;
                        }
                        break;
                    case -1248334925:
                        if (str3.equals("application/pdf")) {
                            str = "pdf";
                            break;
                        }
                        break;
                    case -879258763:
                        if (str3.equals(MimeTypes.IMAGE_PNG)) {
                            str = "png";
                            break;
                        }
                        break;
                    case 1331848029:
                        if (str3.equals(MimeTypes.VIDEO_MP4)) {
                            str = "mp4";
                            break;
                        }
                        break;
                }
                str = "";
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    String str5 = str4;
                    if (str5.length() == 0) {
                        str5 = Long.valueOf(System.currentTimeMillis());
                    }
                    sb.append(str5);
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    sb.append(str);
                    String sb2 = sb.toString();
                    File file = new File(getFilesDir(), "hv");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, sb2);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        fileOutputStream.write(decode);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, null);
                        String value = matchResult.getValue();
                        String path = file2.getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "file.path");
                        inputString = StringsKt.replace$default(str2, value, path, false, 4, (Object) null);
                    } finally {
                    }
                }
            }
            return str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(27:1|(2:3|(22:5|6|(1:(1:9)(2:81|82))(14:83|(1:149)(1:87)|89|(1:91)(1:95)|(1:93)(1:94)|96|(1:98)|99|(1:148)(1:103)|104|(6:114|115|116|(1:118)|119|(2:121|(9:123|(3:139|(1:141)(1:144)|(1:143))|129|(1:131)|132|(1:138)(1:136)|137|107|(2:109|(1:111)(1:112))(3:113|73|74))))|106|107|(0)(0))|10|(1:80)(1:14)|(1:23)(1:19)|(1:21)(1:22)|24|(1:26)|27|(1:79)(1:31)|32|(1:34)|35|36|37|38|(1:40)|41|(2:43|(12:45|(1:72)(2:49|(8:51|52|(1:54)|55|(1:64)(1:59)|60|(1:62)|63))|(1:71)(1:68)|(1:70)|52|(0)|55|(1:57)|64|60|(0)|63))|73|74))|150|6|(0)(0)|10|(1:12)|80|(1:17)|23|(0)(0)|24|(0)|27|(1:29)|79|32|(0)|35|36|37|38|(0)|41|(0)|73|74) */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0263, code lost:
    
        if (r4 != null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0316, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0317, code lost:
    
        r3 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m1202constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0092, code lost:
    
        if (r3 != null) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003e  */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v7, types: [T] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r1v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v24, types: [T] */
    /* JADX WARN: Type inference failed for: r2v34, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v55, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v61 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v5, types: [T] */
    /* JADX WARN: Type inference failed for: r3v58, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v67 */
    /* JADX WARN: Type inference failed for: r4v13, types: [T] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initHyperSnap(Continuation<? super Unit> continuation) {
        HKWebCoreActivity$initHyperSnap$1 hKWebCoreActivity$initHyperSnap$1;
        int i;
        HKWebCoreActivity$initHyperSnap$1 hKWebCoreActivity$initHyperSnap$12;
        String str;
        String str2;
        String str3;
        Object m1202constructorimpl;
        String str4;
        ?? canonicalName;
        String str5;
        String className;
        boolean isInitialised;
        HKWebCoreActivity hKWebCoreActivity;
        String className2;
        Ref.ObjectRef objectRef;
        StackTraceElement stackTraceElement;
        String str6;
        CharSequence charSequence;
        String str7;
        String str8;
        Matcher matcher;
        String str9;
        String str10;
        Object m1202constructorimpl2;
        Object obj;
        ?? canonicalName2;
        Class<?> cls;
        Matcher matcher2;
        String str11;
        String className3;
        Class<?> cls2;
        String className4;
        if (continuation instanceof HKWebCoreActivity$initHyperSnap$1) {
            hKWebCoreActivity$initHyperSnap$1 = (HKWebCoreActivity$initHyperSnap$1) continuation;
            if ((hKWebCoreActivity$initHyperSnap$1.label & Integer.MIN_VALUE) != 0) {
                hKWebCoreActivity$initHyperSnap$1.label -= Integer.MIN_VALUE;
                Object obj2 = hKWebCoreActivity$initHyperSnap$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKWebCoreActivity$initHyperSnap$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement2 == null || (className2 = stackTraceElement2.getClassName()) == null) {
                        hKWebCoreActivity$initHyperSnap$12 = hKWebCoreActivity$initHyperSnap$1;
                        str = "Throwable().stackTrace";
                    } else {
                        hKWebCoreActivity$initHyperSnap$12 = hKWebCoreActivity$initHyperSnap$1;
                        str = "Throwable().stackTrace";
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        str2 = substringAfterLast$default;
                    }
                    Class<?> cls3 = getClass();
                    String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                    str2 = canonicalName3 == null ? "N/A" : canonicalName3;
                    objectRef2.element = str2;
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher3.find()) {
                        ?? replaceAll = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef2.element = replaceAll;
                    }
                    if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str3 = (String) objectRef2.element;
                    } else {
                        str3 = ((String) objectRef2.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str3);
                    sb.append(" - ");
                    sb.append("initHyperSnap() called");
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
                                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                str4 = str;
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, str4);
                                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                    Class<?> cls4 = getClass();
                                    canonicalName = cls4 != null ? cls4.getCanonicalName() : 0;
                                    if (canonicalName == 0) {
                                        canonicalName = "N/A";
                                    }
                                }
                                objectRef3.element = canonicalName;
                                Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                if (matcher4.find()) {
                                    ?? replaceAll2 = matcher4.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef3.element = replaceAll2;
                                }
                                if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str5 = (String) objectRef3.element;
                                } else {
                                    str5 = ((String) objectRef3.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, str5, "initHyperSnap() called ");
                                isInitialised = HyperSnapSDK.isInitialised();
                                if (isInitialised) {
                                    Context applicationContext = getApplicationContext();
                                    Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                                    HyperKycConfig hyperKycConfig$hyperkyc_release = getHyperKycConfig$hyperkyc_release();
                                    boolean useLocation = getHyperKycConfig$hyperkyc_release().getUseLocation();
                                    HKWebCoreActivity$initHyperSnap$1 hKWebCoreActivity$initHyperSnap$13 = hKWebCoreActivity$initHyperSnap$12;
                                    hKWebCoreActivity$initHyperSnap$13.L$0 = this;
                                    hKWebCoreActivity$initHyperSnap$13.label = 1;
                                    obj2 = HyperSnapBridgeKt.initHyperSnapSDK(applicationContext, hyperKycConfig$hyperkyc_release, useLocation, hKWebCoreActivity$initHyperSnap$13);
                                    if (obj2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    hKWebCoreActivity = this;
                                } else {
                                    HyperSnapBridgeKt.updateHyperSnapSDKConfig(getHyperKycConfig$hyperkyc_release());
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }
                    str4 = str;
                    isInitialised = HyperSnapSDK.isInitialised();
                    if (isInitialised) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    hKWebCoreActivity = (HKWebCoreActivity) hKWebCoreActivity$initHyperSnap$1.L$0;
                    ResultKt.throwOnFailure(obj2);
                    str4 = "Throwable().stackTrace";
                }
                HVError hVError = (HVError) obj2;
                HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                objectRef = new Ref.ObjectRef();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, str4);
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                if (stackTraceElement != null || (className4 = stackTraceElement.getClassName()) == null) {
                    str6 = str4;
                    charSequence = "co.hyperverge";
                    str7 = "packageName";
                } else {
                    str6 = str4;
                    charSequence = "co.hyperverge";
                    str7 = "packageName";
                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    str8 = substringAfterLast$default2;
                }
                String canonicalName4 = (hKWebCoreActivity != null || (cls2 = hKWebCoreActivity.getClass()) == null) ? null : cls2.getCanonicalName();
                str8 = canonicalName4 != null ? "N/A" : canonicalName4;
                objectRef.element = str8;
                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                if (matcher.find()) {
                    ?? replaceAll3 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                    objectRef.element = replaceAll3;
                }
                if (((String) objectRef.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                    str9 = (String) objectRef.element;
                } else {
                    str9 = ((String) objectRef.element).substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb2.append(str9);
                sb2.append(" - ");
                str10 = "initHyperSnap() hvError = " + hVError;
                if (str10 == null) {
                    str10 = "null ";
                }
                sb2.append(str10);
                sb2.append(' ');
                sb2.append("");
                companion4.log(level2, sb2.toString());
                CoreExtsKt.isRelease();
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String str12 = (String) m1202constructorimpl2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str12, str7);
                    if (StringsKt.contains$default((CharSequence) str12, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str6);
                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                        if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null) {
                            obj = null;
                        } else {
                            obj = null;
                            String substringAfterLast$default3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            if (substringAfterLast$default3 != null) {
                                canonicalName2 = substringAfterLast$default3;
                                objectRef4.element = canonicalName2;
                                matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                if (matcher2.find()) {
                                    ?? replaceAll4 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                    objectRef4.element = replaceAll4;
                                }
                                if (((String) objectRef4.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                    str11 = (String) objectRef4.element;
                                } else {
                                    str11 = ((String) objectRef4.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb3 = new StringBuilder();
                                String str13 = "initHyperSnap() hvError = " + hVError;
                                sb3.append(str13 != null ? str13 : "null ");
                                sb3.append(' ');
                                sb3.append("");
                                Log.println(6, str11, sb3.toString());
                            }
                        }
                        canonicalName2 = (hKWebCoreActivity == null || (cls = hKWebCoreActivity.getClass()) == null) ? obj : cls.getCanonicalName();
                        if (canonicalName2 == 0) {
                            canonicalName2 = "N/A";
                        }
                        objectRef4.element = canonicalName2;
                        matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                        if (matcher2.find()) {
                        }
                        if (((String) objectRef4.element).length() > 23) {
                        }
                        str11 = (String) objectRef4.element;
                        StringBuilder sb32 = new StringBuilder();
                        String str132 = "initHyperSnap() hvError = " + hVError;
                        sb32.append(str132 != null ? str132 : "null ");
                        sb32.append(' ');
                        sb32.append("");
                        Log.println(6, str11, sb32.toString());
                    }
                }
                return Unit.INSTANCE;
            }
        }
        hKWebCoreActivity$initHyperSnap$1 = new HKWebCoreActivity$initHyperSnap$1(this, continuation);
        Object obj22 = hKWebCoreActivity$initHyperSnap$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = hKWebCoreActivity$initHyperSnap$1.label;
        if (i != 0) {
        }
        HVError hVError2 = (HVError) obj22;
        HyperLogger.Level level22 = HyperLogger.Level.ERROR;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb22 = new StringBuilder();
        objectRef = new Ref.ObjectRef();
        StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace32, str4);
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
        if (stackTraceElement != null) {
        }
        str6 = str4;
        charSequence = "co.hyperverge";
        str7 = "packageName";
        if (hKWebCoreActivity != null) {
        }
        if (canonicalName4 != null) {
        }
        objectRef.element = str8;
        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
        if (matcher.find()) {
        }
        if (((String) objectRef.element).length() > 23) {
        }
        str9 = (String) objectRef.element;
        sb22.append(str9);
        sb22.append(" - ");
        str10 = "initHyperSnap() hvError = " + hVError2;
        if (str10 == null) {
        }
        sb22.append(str10);
        sb22.append(' ');
        sb22.append("");
        companion42.log(level22, sb22.toString());
        CoreExtsKt.isRelease();
        Result.Companion companion52 = Result.INSTANCE;
        Object invoke22 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
        Intrinsics.checkNotNull(invoke22, "null cannot be cast to non-null type android.app.Application");
        m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
        }
        String str122 = (String) m1202constructorimpl2;
        if (CoreExtsKt.isDebug()) {
        }
        return Unit.INSTANCE;
    }

    private final void handleFilePickerResult(int resultCode, Intent data) {
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
        String str2 = "handleFilePickerResult() called with: resultCode = " + resultCode + ", data = " + data;
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
                    String str3 = "handleFilePickerResult() called with: resultCode = " + resultCode + ", data = " + data;
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
        Uri[] parseResult = WebChromeClient.FileChooserParams.parseResult(resultCode, data);
        ValueCallback<Uri[]> valueCallback = this.filePathCallback;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(parseResult);
        }
        this.filePathCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void openFilePicker(String[] acceptTypes) {
        String canonicalName;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        String className;
        int length;
        int i;
        boolean z;
        String str2;
        String str3;
        Object m1202constructorimpl2;
        String str4;
        String str5;
        Matcher matcher;
        String className2;
        String className3;
        int i2;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
        String str6 = "openFilePicker() called with: acceptTypes = [" + acceptTypes + ']';
        if (str6 == null) {
            str6 = "null ";
        }
        sb.append(str6);
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
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str7 = "openFilePicker() called with: acceptTypes = [" + acceptTypes + ']';
                        if (str7 == null) {
                            str7 = "null ";
                        }
                        sb2.append(str7);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                    intent.addCategory("android.intent.category.OPENABLE");
                    length = acceptTypes.length;
                    i = 0;
                    while (true) {
                        if (i < length) {
                            z = false;
                            break;
                        }
                        i2 = length;
                        if (CollectionsKt.listOf((Object[]) new String[]{".jpg", ".jpeg", ".png"}).contains(acceptTypes[i])) {
                            z = true;
                            break;
                        } else {
                            i++;
                            length = i2;
                        }
                    }
                    if (z) {
                        str2 = ArraysKt.contains(acceptTypes, ".pdf") ? "application/pdf" : "*/*";
                    } else {
                        str2 = "image/*";
                    }
                    intent.setType(str2);
                    this.filePickerLauncher.launch(Intent.createChooser(intent, "Select File"));
                    return;
                }
            }
            this.filePickerLauncher.launch(Intent.createChooser(intent, "Select File"));
            return;
        } catch (ActivityNotFoundException unused) {
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = getClass();
                String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
                str3 = canonicalName3 == null ? str : canonicalName3;
            }
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher4.find()) {
                str3 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str3);
            sb3.append(" - ");
            sb3.append("openFilePicker(): No activity found to handle file picker intent");
            sb3.append(' ');
            sb3.append("");
            companion4.log(level2, sb3.toString());
            CoreExtsKt.isRelease();
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
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str4 = null;
                    } else {
                        str4 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str5 = substringAfterLast$default;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher.find()) {
                                str5 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                            }
                            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(6, str5, "openFilePicker(): No activity found to handle file picker intent ");
                            return;
                        }
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str4;
                    str5 = canonicalName4 == null ? str : canonicalName4;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                    if (matcher.find()) {
                    }
                    if (str5.length() > 23) {
                        str5 = str5.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(6, str5, "openFilePicker(): No activity found to handle file picker intent ");
                    return;
                }
                return;
            }
            return;
        }
        str = "N/A";
        Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent2.addCategory("android.intent.category.OPENABLE");
        length = acceptTypes.length;
        i = 0;
        while (true) {
            if (i < length) {
            }
            i++;
            length = i2;
        }
        if (z) {
        }
        intent2.setType(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle savedInstanceState) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        String str2 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "onCreate() called with: savedInstanceState = " + savedInstanceState;
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
        super.onCreate(savedInstanceState);
        setContentView(getBinding$hyperkyc_release().getRoot());
        getWebCoreVM().setHyperKycConfig$hyperkyc_release(getHyperKycConfig$hyperkyc_release());
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKWebCoreActivity$onCreate$2(this, null), 3, null);
        initWebView$hyperkyc_release();
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x01a9, code lost:
    
        if (r9 != null) goto L78;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02a9  */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBackPressed() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
        String str;
        String str2;
        Object m1202constructorimpl2;
        String str3;
        String str4;
        Matcher matcher;
        String className2;
        String className3;
        String className4;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
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
        sb.append("onBackPressed() called");
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
                    Log.println(3, canonicalName2, "onBackPressed() called ");
                }
            }
        }
        if (!getOnBackPressedDispatcher().getHasEnabledCallbacks()) {
            if (getWebCoreVM().getAllowNativeBackPress()) {
                showWebCoreFragment$hyperkyc_release(true);
                nativeBackPress();
                return;
            }
            return;
        }
        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb2 = new StringBuilder();
        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
        if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
            str = "N/A";
        } else {
            str = "N/A";
            str2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        Class<?> cls3 = getClass();
        String canonicalName3 = cls3 != null ? cls3.getCanonicalName() : null;
        str2 = canonicalName3 == null ? str : canonicalName3;
        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str2);
        if (matcher4.find()) {
            str2 = matcher4.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
        }
        if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str2 = str2.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb2.append(str2);
        sb2.append(" - ");
        sb2.append("onBackPressed() called has callbacks");
        sb2.append(' ');
        sb2.append("");
        companion4.log(level2, sb2.toString());
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
                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                    if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null) {
                        str3 = null;
                    } else {
                        str3 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str4 = substringAfterLast$default;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher.find()) {
                                str4 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                            }
                            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(4, str4, "onBackPressed() called has callbacks ");
                        }
                    }
                    Class<?> cls4 = getClass();
                    String canonicalName4 = cls4 != null ? cls4.getCanonicalName() : str3;
                    str4 = canonicalName4 == null ? str : canonicalName4;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                    if (matcher.find()) {
                    }
                    if (str4.length() > 23) {
                        str4 = str4.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    Log.println(4, str4, "onBackPressed() called has callbacks ");
                }
            }
        }
        getOnBackPressedDispatcher().onBackPressed();
    }

    public final void initWebView$hyperkyc_release() {
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
        sb.append("initWebView() called");
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
                    Log.println(3, str, "initWebView() called ");
                }
            }
        }
        WebView webView = getBinding$hyperkyc_release().webCoreWebView;
        WebSettings settings = webView.getSettings();
        webView.setWebChromeClient(new BrowserWebChromeClient());
        webView.setWebViewClient(new BrowserWebClient());
        webView.addJavascriptInterface(new JSInterface(), "JSInterface");
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(-1);
        webView.setBackgroundColor(0);
        WebCoreVM webCoreVM = getWebCoreVM();
        AssetManager assets = getAssets();
        Intrinsics.checkNotNullExpressionValue(assets, "assets");
        webView.loadDataWithBaseURL(WebCoreVM.ASSET_URL, webCoreVM.loadHtmlFromAssets$hyperkyc_release(assets, getRemoteConfig()), "text/html", "UTF-8", null);
    }

    private final void destroyWebView() {
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
        sb.append("destroyWebView() called");
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
                    Log.println(3, str, "destroyWebView() called ");
                }
            }
        }
        getBinding$hyperkyc_release().webCoreWebView.destroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0124, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void initWebSDK() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        sb.append("initWebSDK() called");
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
                    Log.println(3, str, "initWebSDK() called ");
                }
            }
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKWebCoreActivity$initWebSDK$2(this, null), 3, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        if (r0 == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void nativeBackPress() {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        sb.append("nativeBackPress() called");
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
                    Log.println(3, str, "nativeBackPress() called ");
                }
            }
        }
        getBinding$hyperkyc_release().webCoreWebView.evaluateJavascript(getWebCoreVM().backPressedJS$hyperkyc_release(), null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0142, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void sendNativeModuleData$hyperkyc_release(WebCoreNativeResponse webCoreNativeResponse) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        String str2 = "sendHyperSnapData() called with: webCoreNativeResponse = " + webCoreNativeResponse;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "sendHyperSnapData() called with: webCoreNativeResponse = " + webCoreNativeResponse;
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
        showWebCoreFragment$hyperkyc_release(true);
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenResumed(new HKWebCoreActivity$sendNativeModuleData$2(this, webCoreNativeResponse, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startWebViewModule(WorkflowUIState.WebView webViewUIState) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        Map emptyMap;
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
        String str2 = "startWebViewModule() called with: webViewUIState = " + webViewUIState;
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
                    String str3 = "startWebViewModule() called with: webViewUIState = " + webViewUIState;
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
        if (webViewUIState.getOpenInAppBrowser()) {
            Intent intent = new Intent(this, (Class<?>) HKBrowserActivity.class);
            intent.putExtra("data", webViewUIState.getData());
            intent.putExtra("url", webViewUIState.getUrl());
            intent.putExtra("moduleId", webViewUIState.getTag());
            this.browserLauncher.launch(intent);
            return;
        }
        HKWebCoreActivity hKWebCoreActivity = this;
        WebViewFragment webViewFragment = new WebViewFragment();
        Pair[] pairArr = new Pair[7];
        pairArr[0] = TuplesKt.to("moduleId", webViewUIState.getTag());
        pairArr[1] = TuplesKt.to(WebViewFragment.ARG_SUB_TYPE, webViewUIState.getSubType());
        pairArr[2] = TuplesKt.to("url", webViewUIState.getUrl());
        pairArr[3] = TuplesKt.to("data", webViewUIState.getData());
        pairArr[4] = TuplesKt.to("showBackButton", Boolean.valueOf(webViewUIState.getShowBackButton()));
        pairArr[5] = TuplesKt.to(WebViewFragment.ARG_RELOAD_ON_REDIRECT_FAILURE, Boolean.valueOf(webViewUIState.getReloadOnRedirectFailure()));
        Map<String, Object> textConfigs = webViewUIState.getTextConfigs();
        if (textConfigs == null || (emptyMap = MapsKt.toMap(textConfigs)) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        pairArr[6] = TuplesKt.to("textConfigs", emptyMap);
        ActivityExtsKt.replaceContent$default(hKWebCoreActivity, webViewFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNFCFlow(WorkflowUIState.NFCReader nfcFlowUIState) {
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
        String str2 = "startNFCFlow() called with: nfcUIState = " + nfcFlowUIState;
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
                    String str3 = "startNFCFlow() called with: nfcUIState = " + nfcFlowUIState;
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
        if (nfcFlowUIState.getShowInstruction()) {
            ActivityExtsKt.replaceContent$default(this, new NFCReaderInstructionFragment(), BundleKt.bundleOf(TuplesKt.to(NFCReaderInstructionFragment.ARG_KEY_NFC_READER_UI_STATE, nfcFlowUIState)), false, null, 0, 28, null);
        } else {
            ActivityExtsKt.replaceContent$default(this, new WebCoreNFCReaderFragment(), BundleKt.bundleOf(TuplesKt.to("nfcUIState", nfcFlowUIState)), false, "nfcFragment", 0, 20, null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void showWebCoreFragment$hyperkyc_release(boolean showWebCoreFragment) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        String str2 = "showWebCoreFragment() called with: showWebCoreFragment = " + showWebCoreFragment;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "showWebCoreFragment() called with: showWebCoreFragment = " + showWebCoreFragment;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKWebCoreActivity$showWebCoreFragment$2(this, showWebCoreFragment, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setSecureFlagForActivities(boolean secure) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        String str2 = "setSecureFlagForActivities() called with: secure = " + secure;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "setSecureFlagForActivities() called with: secure = " + secure;
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
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKWebCoreActivity$setSecureFlagForActivities$2(this, secure, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x013d, code lost:
    
        if (r0 == null) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void processFullResponse(String result) {
        String canonicalName;
        Object m1202constructorimpl;
        String canonicalName2;
        String className;
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
        String str2 = "Full response received: " + result;
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
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "Full response received: " + result;
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
        if (getWebCoreVM().getSecure()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new HKWebCoreActivity$processFullResponse$2(this, null), 3, null);
        }
        HyperKycResult hyperKycResult = (HyperKycResult) getWebCoreVM().getGson().fromJson(result, HyperKycResult.class);
        Map<String, String> details = hyperKycResult.getDetails();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(details.size()));
        Iterator<T> it = details.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), processBase64Strings((String) entry.getValue()));
        }
        hyperKycResult.setDetails(MapsKt.toMap(linkedHashMap));
        Intent intent = new Intent();
        String json = getWebCoreVM().getGson().toJson(hyperKycResult);
        Intrinsics.checkNotNullExpressionValue(json, "webCoreVM.gson.toJson(hyperKycResult)");
        String saveToCache = FileExtsKt.saveToCache(this, HyperKyc.RESULTS_CACHE_DIR, HyperKycResult.ARG_CACHE_FILE_PATH_KEY, json);
        if (saveToCache != null) {
            intent.putExtra(HyperKycResult.ARG_CACHE_FILE_PATH_KEY, saveToCache);
        } else {
            intent.putExtra(HyperKycResult.ARG_KEY, hyperKycResult);
        }
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void filePickerLauncher$lambda$1(HKWebCoreActivity this$0, ActivityResult activityResult) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = this$0.getClass();
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
        String str2 = "filePickerLauncher: result = " + activityResult;
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
                        Class<?> cls2 = this$0.getClass();
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
                    String str3 = "filePickerLauncher: result = " + activityResult;
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
        this$0.handleFilePickerResult(activityResult.getResultCode(), activityResult.getData());
    }
}
