package co.hyperverge.hyperkyc.core.hv;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.crashguard.CrashGuard;
import co.hyperverge.hvqrmodule.objects.HVQRConfig;
import co.hyperverge.hyperkyc.BuildConfig;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.core.hv.AnalyticsLogger;
import co.hyperverge.hyperkyc.core.hv.models.HSUIConfig;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.KycDocument;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.UIExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.activities.HVDocsActivity;
import co.hyperverge.hypersnapsdk.activities.HVFaceActivity;
import co.hyperverge.hypersnapsdk.activities.HVQrScannerActivity;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import co.hyperverge.hypersnapsdk.helpers.CustomTextStringConst;
import co.hyperverge.hypersnapsdk.listeners.DocCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.FaceCaptureCompletionHandler;
import co.hyperverge.hypersnapsdk.listeners.InitializerCallback;
import co.hyperverge.hypersnapsdk.listeners.QRScannerCompletionHandler;
import co.hyperverge.hypersnapsdk.objects.ExternalConfigs;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.objects.SentryConfig;
import co.hyperverge.hypersnapsdk.utils.HyperSnapUIConfigUtil;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: HyperSnapBridge.kt */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002\u001a\b\u0010\r\u001a\u00020\u000eH\u0000\u001a+\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\b\u0010\u0017\u001a\u00020\bH\u0000\u001a\u001a\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0000\u001a\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000\u001a\u001c\u0010\u001f\u001a\u00020\b*\u0004\u0018\u00010\f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0000\u001a\u001d\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020'H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010(\u001a%\u0010)\u001a\u00020\f*\u00020%2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010*\u001a\u00020+H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010,\u001a%\u0010-\u001a\u00020\f*\u00020%2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010.\u001a\u00020/H\u0080@ø\u0001\u0000¢\u0006\u0002\u00100\u001au\u00101\u001a\u000202*\u00020\n2\b\b\u0002\u00103\u001a\u00020\b2\u0010\b\u0002\u00104\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010!2\b\b\u0002\u00105\u001a\u00020\b2\b\b\u0002\u00106\u001a\u00020\b2\b\b\u0002\u00107\u001a\u00020\b2\b\b\u0002\u00108\u001a\u00020\b2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010:\u001a\u00020\b2\b\b\u0002\u0010;\u001a\u00020\"H\u0000¢\u0006\u0002\u0010<\"\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u00028@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004*\f\b\u0000\u0010=\"\u00020\u00012\u00020\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, d2 = {"uiConfigUtil", "Lco/hyperverge/hypersnapsdk/utils/HyperSnapUIConfigUtil;", "Lco/hyperverge/hyperkyc/core/hv/UIConfigUtil;", "getUiConfigUtil", "()Lco/hyperverge/hypersnapsdk/utils/HyperSnapUIConfigUtil;", "uiConfigUtil$delegate", "Lkotlin/Lazy;", "debugCrashIfTransactionIdMismatch", "", "transactionId", "", "hvResponse", "Lco/hyperverge/hypersnapsdk/objects/HVResponse;", "endUserSession", "", "initHyperSnapSDK", "Lco/hyperverge/hypersnapsdk/objects/HVError;", "appContext", "Landroid/content/Context;", HyperKycConfig.ARG_KEY, "Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;", "useLocation", "(Landroid/content/Context;Lco/hyperverge/hyperkyc/data/models/HyperKycConfig;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isHyperSnapSDKInitialised", "setUIConfig", "context", "uiConfig", "Lco/hyperverge/hyperkyc/core/hv/models/HSUIConfig;", "startUserSession", "updateExternalConfigs", "updateHyperSnapSDKConfig", "isSuccess", "allowedStatusCodes", "", "", "performBarcodeCapture", "Lorg/json/JSONObject;", "Landroid/app/Activity;", "barcodeFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;", "(Landroid/app/Activity;Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$BarcodeCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performDocCapture", "docCaptureUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;", "(Landroid/app/Activity;Ljava/lang/String;Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$DocCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performFaceCapture", "faceFlowUIState", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;", "(Landroid/app/Activity;Ljava/lang/String;Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState$FaceCapture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sideToDocConfig", "Lco/hyperverge/hypersnapsdk/objects/HVDocConfig;", "enableUpload", "supportedUploadFileTypes", "showInstruction", "showReview", "readBarcode", "disableBarcodeSkip", "barcodeSkipDelay", "shouldAutoCapture", "autoCaptureDurationInMS", "(Ljava/lang/String;ZLjava/util/List;ZZZZLjava/lang/Integer;ZI)Lco/hyperverge/hypersnapsdk/objects/HVDocConfig;", "UIConfigUtil", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperSnapBridgeKt {
    private static final Lazy uiConfigUtil$delegate = LazyKt.lazy(new Function0<HyperSnapUIConfigUtil>() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$uiConfigUtil$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HyperSnapUIConfigUtil invoke() {
            return HyperSnapUIConfigUtil.getInstance();
        }
    });

    public static final HyperSnapUIConfigUtil getUiConfigUtil() {
        Object value = uiConfigUtil$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-uiConfigUtil>(...)");
        return (HyperSnapUIConfigUtil) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateExternalConfigs(HyperKycConfig hyperKycConfig) {
        HyperSnapSDKConfig hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
        ExternalConfigs.ExternalConfigsBuilder builder = ExternalConfigs.builder();
        HashMap<String, String> hashMapOf = MapsKt.hashMapOf(TuplesKt.to("hv_hk_sdk_version", BuildConfig.HYPERKYC_VERSION_NAME), TuplesKt.to("release", BuildConfig.HYPERKYC_VERSION_NAME));
        hashMapOf.putAll(hyperKycConfig.getMetadataMap());
        Unit unit = Unit.INSTANCE;
        hyperSnapSDKConfig.setExternalConfigs(builder.metadataMap(hashMapOf).sentryConfig(SentryConfig.builder().build()).build());
    }

    public static final /* synthetic */ void updateHyperSnapSDKConfig(HyperKycConfig hyperKycConfig) {
        Intrinsics.checkNotNullParameter(hyperKycConfig, "hyperKycConfig");
        updateExternalConfigs(hyperKycConfig);
        if (hyperKycConfig.getIsAppIdAppKeyInit()) {
            HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setAppId(hyperKycConfig.getAppId());
            HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setAppKey(hyperKycConfig.getAppKey());
        } else {
            HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setAppId(hyperKycConfig.getAppId());
            HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setAccessToken(hyperKycConfig.getAccessToken());
        }
    }

    public static final /* synthetic */ void startUserSession(String transactionId) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        HyperSnapSDK.startUserSession(transactionId);
    }

    public static final /* synthetic */ void endUserSession() {
        if (HyperSnapSDK.isUserSessionActive()) {
            HyperSnapSDK.endUserSession();
        }
    }

    public static final /* synthetic */ void setUIConfig(Context context, HSUIConfig hSUIConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        HyperSnapSDK.getInstance().setUiConfig(context, hSUIConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean debugCrashIfTransactionIdMismatch(final String str, final HVResponse hVResponse) {
        return CoreExtsKt.ifDebug(new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$debugCrashIfTransactionIdMismatch$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:50:0x0160, code lost:
            
                if (r0 != null) goto L69;
             */
            /* JADX WARN: Code restructure failed: missing block: B:55:0x0172, code lost:
            
                if (r0 == null) goto L70;
             */
            /* JADX WARN: Code restructure failed: missing block: B:56:0x0176, code lost:
            
                r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r10);
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x0185, code lost:
            
                if (r0.find() == false) goto L73;
             */
            /* JADX WARN: Code restructure failed: missing block: B:58:0x0187, code lost:
            
                r10 = r0.replaceAll("");
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, "replaceAll(\"\")");
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x0192, code lost:
            
                if (r10.length() <= 23) goto L79;
             */
            /* JADX WARN: Code restructure failed: missing block: B:62:0x0198, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 26) goto L78;
             */
            /* JADX WARN: Code restructure failed: missing block: B:63:0x019b, code lost:
            
                r10 = r10.substring(0, 23);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, "this as java.lang.String…ing(startIndex, endIndex)");
             */
            /* JADX WARN: Code restructure failed: missing block: B:64:0x01a2, code lost:
            
                r0 = new java.lang.StringBuilder();
                r2 = "debugCrashIfTransactionIdMismatch() called with: transactionId = " + r4 + ", responseTId = " + r3;
             */
            /* JADX WARN: Code restructure failed: missing block: B:65:0x01bc, code lost:
            
                if (r2 != null) goto L82;
             */
            /* JADX WARN: Code restructure failed: missing block: B:66:0x01be, code lost:
            
                r2 = "null ";
             */
            /* JADX WARN: Code restructure failed: missing block: B:67:0x01c0, code lost:
            
                r0.append(r2);
                r0.append(' ');
                r0.append("");
                android.util.Log.println(3, r10, r0.toString());
             */
            /* JADX WARN: Code restructure failed: missing block: B:69:0x0175, code lost:
            
                r10 = r0;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                String str2;
                String canonicalName;
                Class<?> cls;
                Object m1202constructorimpl;
                String str3;
                String str4;
                Class<?> cls2;
                String className;
                String className2;
                JSONObject apiResult;
                HVResponse hVResponse2 = HVResponse.this;
                if (hVResponse2 == null || (apiResult = hVResponse2.getApiResult()) == null) {
                    str2 = null;
                } else {
                    JSONObject optJSONObject = apiResult.optJSONObject(TtmlNode.TAG_METADATA);
                    if (optJSONObject == null) {
                        optJSONObject = apiResult.optJSONObject("metaData");
                    }
                    str2 = optJSONObject != null ? optJSONObject.getString("transactionId") : null;
                }
                String str5 = str;
                HyperLogger.Level level = HyperLogger.Level.DEBUG;
                HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String str6 = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName = (str5 == null || (cls = str5.getClass()) == null) ? null : cls.getCanonicalName();
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
                String str7 = "debugCrashIfTransactionIdMismatch() called with: transactionId = " + str5 + ", responseTId = " + str2;
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
                        if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                            if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                str3 = null;
                            } else {
                                str3 = null;
                                str4 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            str4 = (str5 == null || (cls2 = str5.getClass()) == null) ? str3 : cls2.getCanonicalName();
                        }
                    }
                }
                if (Intrinsics.areEqual(str2, str)) {
                    return;
                }
                throw new IllegalStateException(("transactionId mismatch, responseTId: " + str2 + ", transactionId: " + str).toString());
            }
        });
    }

    public static final /* synthetic */ boolean isSuccess(HVResponse hVResponse, List allowedStatusCodes) {
        JSONObject apiResult;
        String optString;
        String nullIfBlank;
        Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
        return CollectionsKt.contains(allowedStatusCodes, (hVResponse == null || (apiResult = hVResponse.getApiResult()) == null || (optString = apiResult.optString(Keys.STATUS_CODE)) == null || (nullIfBlank = CoreExtsKt.nullIfBlank(optString)) == null) ? null : Integer.valueOf(Integer.parseInt(nullIfBlank)));
    }

    public static /* synthetic */ HVDocConfig sideToDocConfig$default(String str, boolean z, List list, boolean z2, boolean z3, boolean z4, boolean z5, Integer num, boolean z6, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        if ((i2 & 2) != 0) {
            list = null;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = true;
        }
        if ((i2 & 16) != 0) {
            z4 = false;
        }
        if ((i2 & 32) != 0) {
            z5 = false;
        }
        if ((i2 & 64) != 0) {
            num = null;
        }
        if ((i2 & 128) != 0) {
            z6 = false;
        }
        if ((i2 & 256) != 0) {
            i = 0;
        }
        return sideToDocConfig(str, z, list, z2, z3, z4, z5, num, z6, i);
    }

    public static final /* synthetic */ HVDocConfig sideToDocConfig(String str, boolean z, List list, boolean z2, boolean z3, boolean z4, boolean z5, Integer num, boolean z6, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        HVDocConfig hVDocConfig = new HVDocConfig();
        hVDocConfig.setEnableDocumentUpload(z);
        hVDocConfig.setUploadFileTypes(list);
        hVDocConfig.setShouldShowInstructionPage(z2);
        hVDocConfig.setShouldShowReviewScreen(z3);
        hVDocConfig.setShouldReadBarcode(z4);
        hVDocConfig.setDisableBarcodeSkip(z5);
        if (num != null) {
            hVDocConfig.setReadBarcodeTimeout(num.intValue());
        }
        hVDocConfig.setShouldAutoCapture(z6);
        hVDocConfig.setAutoCaptureDuration(i);
        return hVDocConfig;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x015b, code lost:
    
        if (r0 != null) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object initHyperSnapSDK(Context context, HyperKycConfig hyperKycConfig, boolean z, Continuation continuation) {
        String canonicalName;
        Object m1202constructorimpl;
        CancellableContinuationImpl cancellableContinuationImpl;
        String str;
        String str2;
        String className;
        Object result;
        String className2;
        CancellableContinuationImpl cancellableContinuationImpl2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl2.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl3 = cancellableContinuationImpl2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = cancellableContinuationImpl3.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
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
        String str3 = "initHyperSnapSDK() called with context = " + context + ", hyperKycConfig = " + hyperKycConfig + ", useLocation = " + z;
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
                cancellableContinuationImpl = cancellableContinuationImpl2;
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
                    Class<?> cls2 = cancellableContinuationImpl3.getClass();
                    str2 = cls2 != null ? cls2.getCanonicalName() : str;
                    if (str2 == null) {
                        str2 = "N/A";
                    }
                    Matcher matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str2);
                    if (matcher2.find()) {
                        str2 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str2, "replaceAll(\"\")");
                    }
                    if (str2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str2 = str2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = "initHyperSnapSDK() called with context = " + context + ", hyperKycConfig = " + hyperKycConfig + ", useLocation = " + z;
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str2, sb2.toString());
                }
                HyperSnapSDK.setShouldUseLocation(context, z);
                updateExternalConfigs(hyperKycConfig);
                InitializerCallback initializerCallback = new InitializerCallback() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$initHyperSnapSDK$2$initCallback$1
                    /* JADX WARN: Code restructure failed: missing block: B:51:0x0124, code lost:
                    
                        if (r0 == null) goto L52;
                     */
                    @Override // co.hyperverge.hypersnapsdk.listeners.InitializerCallback
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public void onSuccess() {
                        String canonicalName2;
                        Object m1202constructorimpl2;
                        String canonicalName3;
                        String className3;
                        String className4;
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        String str5 = "N/A";
                        if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
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
                        sb3.append(canonicalName2);
                        sb3.append(" - ");
                        sb3.append("initHyperSnapSDK() onSuccess() called");
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
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls4 = getClass();
                                        canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                    }
                                    str5 = canonicalName3;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                    if (matcher4.find()) {
                                        str5 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                    }
                                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str5 = str5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    Log.println(3, str5, "initHyperSnapSDK() onSuccess() called ");
                                }
                            }
                        }
                        if (cancellableContinuationImpl3.isActive()) {
                            CancellableContinuation<HVError> cancellableContinuation = cancellableContinuationImpl3;
                            Result.Companion companion7 = Result.INSTANCE;
                            cancellableContinuation.resumeWith(Result.m1202constructorimpl(null));
                        }
                    }

                    @Override // co.hyperverge.hypersnapsdk.listeners.InitializerCallback
                    public void onError(HVError hvError) {
                        String canonicalName2;
                        Object m1202constructorimpl2;
                        String className3;
                        String substringAfterLast$default;
                        String className4;
                        HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        String str5 = "N/A";
                        if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls3 = getClass();
                            canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
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
                        sb3.append(canonicalName2);
                        sb3.append(" - ");
                        String str6 = "initHyperSnapSDK() onError() called with: hvError = " + hvError;
                        if (str6 == null) {
                            str6 = "null ";
                        }
                        sb3.append(str6);
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
                                    Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                    if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                        Class<?> cls4 = getClass();
                                        String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                        if (canonicalName3 != null) {
                                            str5 = canonicalName3;
                                        }
                                    } else {
                                        str5 = substringAfterLast$default;
                                    }
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                                    if (matcher4.find()) {
                                        str5 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                                    }
                                    if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str5 = str5.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb4 = new StringBuilder();
                                    String str7 = "initHyperSnapSDK() onError() called with: hvError = " + hvError;
                                    if (str7 == null) {
                                        str7 = "null ";
                                    }
                                    sb4.append(str7);
                                    sb4.append(' ');
                                    sb4.append("");
                                    Log.println(3, str5, sb4.toString());
                                }
                            }
                        }
                        if (cancellableContinuationImpl3.isActive()) {
                            CancellableContinuation<HVError> cancellableContinuation = cancellableContinuationImpl3;
                            Result.Companion companion7 = Result.INSTANCE;
                            cancellableContinuation.resumeWith(Result.m1202constructorimpl(hvError));
                        }
                    }
                };
                if (!hyperKycConfig.getIsAppIdAppKeyInit()) {
                    String appId = hyperKycConfig.getAppId();
                    Intrinsics.checkNotNull(appId);
                    String appKey = hyperKycConfig.getAppKey();
                    Intrinsics.checkNotNull(appKey);
                    HyperSnapSDK.init(context, appId, appKey, HyperSnapParams.Region.INDIA, initializerCallback);
                } else {
                    String appId2 = hyperKycConfig.getAppId();
                    Intrinsics.checkNotNull(appId2);
                    String accessToken = hyperKycConfig.getAccessToken();
                    Intrinsics.checkNotNull(accessToken);
                    HyperSnapSDK.initWithToken(context, appId2, accessToken, HyperSnapParams.Region.INDIA, initializerCallback);
                }
                CrashGuard.INSTANCE.getInstance().startSession();
                result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result;
            }
        }
        cancellableContinuationImpl = cancellableContinuationImpl2;
        HyperSnapSDK.setShouldUseLocation(context, z);
        updateExternalConfigs(hyperKycConfig);
        InitializerCallback initializerCallback2 = new InitializerCallback() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$initHyperSnapSDK$2$initCallback$1
            /* JADX WARN: Code restructure failed: missing block: B:51:0x0124, code lost:
            
                if (r0 == null) goto L52;
             */
            @Override // co.hyperverge.hypersnapsdk.listeners.InitializerCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onSuccess() {
                String canonicalName2;
                Object m1202constructorimpl2;
                String canonicalName3;
                String className3;
                String className4;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str5 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
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
                sb3.append(canonicalName2);
                sb3.append(" - ");
                sb3.append("initHyperSnapSDK() onSuccess() called");
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
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                            }
                            str5 = canonicalName3;
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher4.find()) {
                                str5 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                            }
                            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            Log.println(3, str5, "initHyperSnapSDK() onSuccess() called ");
                        }
                    }
                }
                if (cancellableContinuationImpl3.isActive()) {
                    CancellableContinuation<HVError> cancellableContinuation = cancellableContinuationImpl3;
                    Result.Companion companion7 = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m1202constructorimpl(null));
                }
            }

            @Override // co.hyperverge.hypersnapsdk.listeners.InitializerCallback
            public void onError(HVError hvError) {
                String canonicalName2;
                Object m1202constructorimpl2;
                String className3;
                String substringAfterLast$default;
                String className4;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb3 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str5 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    Class<?> cls3 = getClass();
                    canonicalName2 = cls3 != null ? cls3.getCanonicalName() : null;
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
                sb3.append(canonicalName2);
                sb3.append(" - ");
                String str6 = "initHyperSnapSDK() onError() called with: hvError = " + hvError;
                if (str6 == null) {
                    str6 = "null ";
                }
                sb3.append(str6);
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
                            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                Class<?> cls4 = getClass();
                                String canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                                if (canonicalName3 != null) {
                                    str5 = canonicalName3;
                                }
                            } else {
                                str5 = substringAfterLast$default;
                            }
                            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
                            if (matcher4.find()) {
                                str5 = matcher4.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
                            }
                            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str5 = str5.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb4 = new StringBuilder();
                            String str7 = "initHyperSnapSDK() onError() called with: hvError = " + hvError;
                            if (str7 == null) {
                                str7 = "null ";
                            }
                            sb4.append(str7);
                            sb4.append(' ');
                            sb4.append("");
                            Log.println(3, str5, sb4.toString());
                        }
                    }
                }
                if (cancellableContinuationImpl3.isActive()) {
                    CancellableContinuation<HVError> cancellableContinuation = cancellableContinuationImpl3;
                    Result.Companion companion7 = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m1202constructorimpl(hvError));
                }
            }
        };
        if (!hyperKycConfig.getIsAppIdAppKeyInit()) {
        }
        CrashGuard.INSTANCE.getInstance().startSession();
        result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        }
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0236, code lost:
    
        if (r3 == null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x024c, code lost:
    
        if (r3 == null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0380, code lost:
    
        if (r14 != null) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x06f2, code lost:
    
        if (r13 != null) goto L266;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x071c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x077a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0816  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0857  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x085a  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x070a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object performDocCapture(final Activity activity, final String str, WorkflowUIState.DocCapture docCapture, Continuation continuation) {
        String canonicalName;
        Class<?> cls;
        String str2;
        String str3;
        Object m1202constructorimpl;
        CancellableContinuationImpl cancellableContinuationImpl;
        CancellableContinuationImpl cancellableContinuationImpl2;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String side;
        final boolean disableOCR;
        Map<String, Object> textConfigs;
        List<Integer> allowedStatusCodes;
        String stringValue;
        String str4;
        JSONObject jSONObject;
        String str5;
        String canonicalName3;
        Class<?> cls3;
        Object m1202constructorimpl2;
        String canonicalName4;
        Class<?> cls4;
        String className2;
        String className3;
        StackTraceElement stackTraceElement;
        List<Integer> list;
        String str6;
        Matcher matcher;
        String str7;
        Object m1202constructorimpl3;
        String str8;
        String str9;
        Class<?> cls5;
        Matcher matcher2;
        String className4;
        Object result;
        Class<?> cls6;
        String className5;
        String str10;
        String str11;
        Object m1202constructorimpl4;
        String canonicalName5;
        Class<?> cls7;
        String className6;
        Class<?> cls8;
        String className7;
        String className8;
        CancellableContinuationImpl cancellableContinuationImpl3 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl3.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl4 = cancellableContinuationImpl3;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className8 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (activity == null || (cls = activity.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
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
        String str12 = "performDocCapture() called with: transactionId = " + str + ", docCaptureUIState = " + docCapture;
        if (str12 == null) {
            str12 = "null ";
        }
        sb.append(str12);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (CoreExtsKt.isRelease()) {
            cancellableContinuationImpl = cancellableContinuationImpl4;
            cancellableContinuationImpl2 = cancellableContinuationImpl3;
            str2 = "N/A";
            str3 = "null ";
        } else {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                str2 = "N/A";
                str3 = "null ";
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
                    HVDocConfig docConfig = docCapture.getDocConfig();
                    KycDocument document = docCapture.getDocument();
                    String tag = docCapture.getTag();
                    side = docCapture.getSide();
                    String countryId = docCapture.getCountryId();
                    String url = docCapture.getUrl();
                    disableOCR = docCapture.getDisableOCR();
                    Map<String, String> ocrParams = docCapture.getOcrParams();
                    Map<String, String> ocrHeaders = docCapture.getOcrHeaders();
                    textConfigs = docCapture.getTextConfigs();
                    allowedStatusCodes = docCapture.getAllowedStatusCodes();
                    boolean enableOverlay = docCapture.getEnableOverlay();
                    boolean validateSignature = docCapture.getValidateSignature();
                    docConfig.setModuleId(tag);
                    if (!StringsKt.equals(side, "back", true)) {
                    }
                    if (textConfigs == null) {
                    }
                    docConfig.setCustomUIStrings(jSONObject);
                    String upperCase = document.getType().toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    docConfig.setDocumentType(HVDocConfig.Document.valueOf(upperCase));
                    docConfig.setShouldEnableRetries(true);
                    docConfig.setShouldAddPadding(true);
                    if (disableOCR) {
                    }
                    docConfig.setAllowedStatusCodes(allowedStatusCodes);
                    Unit unit2 = Unit.INSTANCE;
                    HyperSnapSDK.setShouldUseSignature(validateSignature);
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement != null) {
                    }
                    list = allowedStatusCodes;
                    if (activity != null) {
                    }
                    if (r3 != null) {
                    }
                    matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str6);
                    if (matcher.find()) {
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (str6.length() > 23) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb2.append(str6);
                    sb2.append(" - ");
                    str7 = "hvDocConfig: " + new Gson().toJson(docConfig);
                    if (str7 == null) {
                    }
                    sb2.append(str7);
                    sb2.append(' ');
                    sb2.append("");
                    companion4.log(level2, sb2.toString());
                    if (!CoreExtsKt.isRelease()) {
                    }
                    final List<Integer> list2 = list;
                    final CancellableContinuationImpl cancellableContinuationImpl5 = cancellableContinuationImpl;
                    HVDocsActivity.start(activity, docConfig, new DocCaptureCompletionHandler() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performDocCapture$2$4
                        /* JADX WARN: Removed duplicated region for block: B:58:0x0169  */
                        /* JADX WARN: Removed duplicated region for block: B:66:0x01a3  */
                        @Override // co.hyperverge.hypersnapsdk.listeners.CaptureCompletionHandler
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public final void onResult(HVError hVError, HVResponse hVResponse) {
                            String canonicalName6;
                            Class<?> cls9;
                            Object m1202constructorimpl5;
                            String str13;
                            Class<?> cls10;
                            Matcher matcher4;
                            String str14;
                            String className9;
                            String className10;
                            Activity activity2 = activity;
                            HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                            HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb3 = new StringBuilder();
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            String str15 = "N/A";
                            if (stackTraceElement3 == null || (className10 = stackTraceElement3.getClassName()) == null || (canonicalName6 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                canonicalName6 = (activity2 == null || (cls9 = activity2.getClass()) == null) ? null : cls9.getCanonicalName();
                                if (canonicalName6 == null) {
                                    canonicalName6 = "N/A";
                                }
                            }
                            Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName6);
                            if (matcher5.find()) {
                                canonicalName6 = matcher5.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(canonicalName6, "replaceAll(\"\")");
                            }
                            if (canonicalName6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                canonicalName6 = canonicalName6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(canonicalName6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb3.append(canonicalName6);
                            sb3.append(" - ");
                            String str16 = "performDocCapture() finished with: hvError = " + hVError + ", hvResponse = " + hVResponse;
                            if (str16 == null) {
                                str16 = "null ";
                            }
                            sb3.append(str16);
                            sb3.append(' ');
                            sb3.append("");
                            companion5.log(level3, sb3.toString());
                            if (!CoreExtsKt.isRelease()) {
                                try {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                    m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th2) {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                    m1202constructorimpl5 = "";
                                }
                                String packageName2 = (String) m1202constructorimpl5;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className9 = stackTraceElement4.getClassName()) == null) {
                                            str13 = null;
                                        } else {
                                            str13 = null;
                                            String substringAfterLast$default = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                            if (substringAfterLast$default != null) {
                                                str15 = substringAfterLast$default;
                                                matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str15);
                                                if (matcher4.find()) {
                                                    str15 = matcher4.replaceAll("");
                                                    Intrinsics.checkNotNullExpressionValue(str15, "replaceAll(\"\")");
                                                }
                                                if (str15.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                                    str15 = str15.substring(0, 23);
                                                    Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
                                                }
                                                StringBuilder sb4 = new StringBuilder();
                                                str14 = "performDocCapture() finished with: hvError = " + hVError + ", hvResponse = " + hVResponse;
                                                if (str14 == null) {
                                                    str14 = "null ";
                                                }
                                                sb4.append(str14);
                                                sb4.append(' ');
                                                sb4.append("");
                                                Log.println(3, str15, sb4.toString());
                                            }
                                        }
                                        String canonicalName7 = (activity2 == null || (cls10 = activity2.getClass()) == null) ? str13 : cls10.getCanonicalName();
                                        if (canonicalName7 != null) {
                                            str15 = canonicalName7;
                                        }
                                        matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str15);
                                        if (matcher4.find()) {
                                        }
                                        if (str15.length() > 23) {
                                            str15 = str15.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str15, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb42 = new StringBuilder();
                                        str14 = "performDocCapture() finished with: hvError = " + hVError + ", hvResponse = " + hVResponse;
                                        if (str14 == null) {
                                        }
                                        sb42.append(str14);
                                        sb42.append(' ');
                                        sb42.append("");
                                        Log.println(3, str15, sb42.toString());
                                    }
                                }
                            }
                            if (hVError == null && hVResponse != null && (disableOCR || HyperSnapBridgeKt.isSuccess(hVResponse, list2))) {
                                if (!disableOCR) {
                                    HyperSnapBridgeKt.debugCrashIfTransactionIdMismatch(str, hVResponse);
                                }
                                if (cancellableContinuationImpl5.isActive()) {
                                    CancellableContinuation<HVResponse> cancellableContinuation = cancellableContinuationImpl5;
                                    Result.Companion companion8 = Result.INSTANCE;
                                    cancellableContinuation.resumeWith(Result.m1202constructorimpl(hVResponse));
                                    return;
                                }
                                return;
                            }
                            if (cancellableContinuationImpl5.isActive()) {
                                CancellableContinuation<HVResponse> cancellableContinuation2 = cancellableContinuationImpl5;
                                Result.Companion companion9 = Result.INSTANCE;
                                cancellableContinuation2.resumeWith(Result.m1202constructorimpl(ResultKt.createFailure(new HSBridgeException(hVError, hVResponse))));
                            }
                        }
                    });
                    result = cancellableContinuationImpl2.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    }
                    return result;
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = "N/A";
                str3 = "null ";
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName2 = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                cancellableContinuationImpl = cancellableContinuationImpl4;
                cancellableContinuationImpl2 = cancellableContinuationImpl3;
            } else {
                Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                cancellableContinuationImpl = cancellableContinuationImpl4;
                cancellableContinuationImpl2 = cancellableContinuationImpl3;
                if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (activity == null || (cls2 = activity.getClass()) == null) ? null : cls2.getCanonicalName();
                        if (canonicalName2 == null) {
                            canonicalName2 = str2;
                        }
                    }
                    Matcher matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName2);
                    if (matcher4.find()) {
                        canonicalName2 = matcher4.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    Unit unit4 = Unit.INSTANCE;
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb3 = new StringBuilder();
                    String str13 = "performDocCapture() called with: transactionId = " + str + ", docCaptureUIState = " + docCapture;
                    if (str13 == null) {
                        str13 = str3;
                    }
                    sb3.append(str13);
                    sb3.append(' ');
                    sb3.append("");
                    Log.println(3, canonicalName2, sb3.toString());
                }
            }
        }
        HVDocConfig docConfig2 = docCapture.getDocConfig();
        KycDocument document2 = docCapture.getDocument();
        String tag2 = docCapture.getTag();
        side = docCapture.getSide();
        String countryId2 = docCapture.getCountryId();
        String url2 = docCapture.getUrl();
        disableOCR = docCapture.getDisableOCR();
        Map<String, String> ocrParams2 = docCapture.getOcrParams();
        Map<String, String> ocrHeaders2 = docCapture.getOcrHeaders();
        textConfigs = docCapture.getTextConfigs();
        allowedStatusCodes = docCapture.getAllowedStatusCodes();
        boolean enableOverlay2 = docCapture.getEnableOverlay();
        boolean validateSignature2 = docCapture.getValidateSignature();
        docConfig2.setModuleId(tag2);
        if (!StringsKt.equals(side, "back", true)) {
            stringValue = textConfigs != null ? CoreExtsKt.getStringValue(textConfigs, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_BACK_SUB_TEXT) : null;
        } else {
            if (StringsKt.equals(side, "front", true)) {
                stringValue = textConfigs != null ? CoreExtsKt.getStringValue(textConfigs, CustomTextStringConst.DocCaptureTextConfigs.TEXT_CONFIG_DOC_CAPTURE_FRONT_SUB_TEXT) : null;
            }
            stringValue = "";
        }
        if (textConfigs == null) {
            str4 = side;
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(textConfigs.size()));
            for (Iterator it = textConfigs.entrySet().iterator(); it.hasNext(); it = it) {
                Map.Entry entry = (Map.Entry) it.next();
                linkedHashMap.put(entry.getKey(), StringsKt.replace$default(StringsKt.replace$default(entry.getValue().toString(), "{{side}}", stringValue, false, 4, (Object) null), "{{docName}}", document2.getName(), false, 4, (Object) null));
            }
            jSONObject = JSONExtsKt.toJSONObject(linkedHashMap);
        } else {
            str4 = side;
            jSONObject = null;
        }
        docConfig2.setCustomUIStrings(jSONObject);
        String upperCase2 = document2.getType().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        docConfig2.setDocumentType(HVDocConfig.Document.valueOf(upperCase2));
        docConfig2.setShouldEnableRetries(true);
        docConfig2.setShouldAddPadding(true);
        if (disableOCR) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AnalyticsLogger.Keys.COUNTRY_ID, countryId2);
            jSONObject2.put(AnalyticsLogger.Keys.DOCUMENT_ID, document2.getId());
            for (Map.Entry<String, String> entry2 : ocrParams2.entrySet()) {
                jSONObject2.put(entry2.getKey(), entry2.getValue());
            }
            String jSONObject3 = jSONObject2.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject3, "JSONObject().apply {\n   …\n            }.toString()");
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("transactionId", str);
            jSONObject4.put("moduleId", tag2);
            for (Map.Entry<String, String> entry3 : ocrHeaders2.entrySet()) {
                jSONObject4.put(entry3.getKey(), entry3.getValue());
            }
            String jSONObject5 = jSONObject4.toString();
            HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
            HyperLogger companion5 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb4 = new StringBuilder();
            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
            if (stackTraceElement4 == null || (className7 = stackTraceElement4.getClassName()) == null) {
                str10 = jSONObject3;
            } else {
                str10 = jSONObject3;
                str11 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            String canonicalName6 = (docConfig2 == null || (cls8 = docConfig2.getClass()) == null) ? null : cls8.getCanonicalName();
            str11 = canonicalName6 == null ? str2 : canonicalName6;
            Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str11);
            if (matcher5.find()) {
                str11 = matcher5.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str11, "replaceAll(\"\")");
            }
            Unit unit5 = Unit.INSTANCE;
            if (str11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str11 = str11.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb4.append(str11);
            sb4.append(" - ");
            String str14 = "ocr headers: " + jSONObject5;
            if (str14 == null) {
                str14 = str3;
            }
            sb4.append(str14);
            sb4.append(' ');
            sb4.append("");
            companion5.log(level3, sb4.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion6 = Result.INSTANCE;
                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                } catch (Throwable th3) {
                    Result.Companion companion7 = Result.INSTANCE;
                    m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                    m1202constructorimpl4 = "";
                }
                String str15 = (String) m1202constructorimpl4;
                if (CoreExtsKt.isDebug()) {
                    str5 = "packageName";
                    Intrinsics.checkNotNullExpressionValue(str15, str5);
                    if (StringsKt.contains$default((CharSequence) str15, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null || (canonicalName5 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName5 = (docConfig2 == null || (cls7 = docConfig2.getClass()) == null) ? null : cls7.getCanonicalName();
                            if (canonicalName5 == null) {
                                canonicalName5 = str2;
                            }
                        }
                        Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName5);
                        if (matcher6.find()) {
                            canonicalName5 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName5, "replaceAll(\"\")");
                        }
                        Unit unit6 = Unit.INSTANCE;
                        if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName5 = canonicalName5.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb5 = new StringBuilder();
                        String str16 = "ocr headers: " + jSONObject5;
                        if (str16 == null) {
                            str16 = str3;
                        }
                        sb5.append(str16);
                        sb5.append(' ');
                        sb5.append("");
                        Log.println(4, canonicalName5, sb5.toString());
                    }
                    Intrinsics.checkNotNullExpressionValue(jSONObject5, "JSONObject().apply {\n   …ers: $it\" }\n            }");
                    String upperCase3 = str4.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    HVDocConfig.DocumentSide valueOf = HVDocConfig.DocumentSide.valueOf(upperCase3);
                    if (url2 == null) {
                        docConfig2.setOCRDetails(url2, valueOf, str10, jSONObject5);
                        if (valueOf == HVDocConfig.DocumentSide.BACK && docConfig2.isShouldReadBarcode()) {
                            Context applicationContext = activity.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                            Drawable drawableOf = UIExtsKt.drawableOf(applicationContext, R.drawable.hk_ic_barcode_overlay);
                            docConfig2.setDocumentCaptureOverlay(drawableOf != null ? DrawableKt.toBitmap$default(drawableOf, 0, 0, null, 7, null) : null);
                        }
                        docConfig2.setShouldEnableOverlay(enableOverlay2);
                    } else {
                        throw new IllegalStateException("url cannot be null".toString());
                    }
                }
            }
            str5 = "packageName";
            Intrinsics.checkNotNullExpressionValue(jSONObject5, "JSONObject().apply {\n   …ers: $it\" }\n            }");
            String upperCase32 = str4.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase32, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            HVDocConfig.DocumentSide valueOf2 = HVDocConfig.DocumentSide.valueOf(upperCase32);
            if (url2 == null) {
            }
        } else {
            str5 = "packageName";
            HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
            HyperLogger companion8 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb6 = new StringBuilder();
            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace6, "Throwable().stackTrace");
            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
            if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName3 = (docConfig2 == null || (cls3 = docConfig2.getClass()) == null) ? null : cls3.getCanonicalName();
                if (canonicalName3 == null) {
                    canonicalName3 = str2;
                }
            }
            Matcher matcher7 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName3);
            if (matcher7.find()) {
                canonicalName3 = matcher7.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
            }
            Unit unit7 = Unit.INSTANCE;
            if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                canonicalName3 = canonicalName3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb6.append(canonicalName3);
            sb6.append(" - ");
            sb6.append("performDocCapture() skipping OCR as disableOCR=true");
            sb6.append(' ');
            sb6.append("");
            companion8.log(level4, sb6.toString());
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion9 = Result.INSTANCE;
                    Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                } catch (Throwable th4) {
                    Result.Companion companion10 = Result.INSTANCE;
                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                    m1202constructorimpl2 = "";
                }
                String str17 = (String) m1202constructorimpl2;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(str17, str5);
                    if (StringsKt.contains$default((CharSequence) str17, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace7, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                        if (stackTraceElement7 == null || (className2 = stackTraceElement7.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName4 = (docConfig2 == null || (cls4 = docConfig2.getClass()) == null) ? null : cls4.getCanonicalName();
                            if (canonicalName4 == null) {
                                canonicalName4 = str2;
                            }
                        }
                        Matcher matcher8 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName4);
                        if (matcher8.find()) {
                            canonicalName4 = matcher8.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                        }
                        Unit unit8 = Unit.INSTANCE;
                        if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName4 = canonicalName4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, canonicalName4, "performDocCapture() skipping OCR as disableOCR=true ");
                    }
                }
            }
        }
        docConfig2.setAllowedStatusCodes(allowedStatusCodes);
        Unit unit22 = Unit.INSTANCE;
        HyperSnapSDK.setShouldUseSignature(validateSignature2);
        HyperLogger.Level level22 = HyperLogger.Level.DEBUG;
        HyperLogger companion42 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb22 = new StringBuilder();
        StackTraceElement[] stackTrace22 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace22, "Throwable().stackTrace");
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace22);
        if (stackTraceElement != null || (className5 = stackTraceElement.getClassName()) == null) {
            list = allowedStatusCodes;
        } else {
            list = allowedStatusCodes;
            str6 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
        }
        String canonicalName7 = (activity != null || (cls6 = activity.getClass()) == null) ? null : cls6.getCanonicalName();
        str6 = canonicalName7 != null ? str2 : canonicalName7;
        matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str6);
        if (matcher.find()) {
            str6 = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
        }
        Unit unit32 = Unit.INSTANCE;
        if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
            str6 = str6.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb22.append(str6);
        sb22.append(" - ");
        str7 = "hvDocConfig: " + new Gson().toJson(docConfig2);
        if (str7 == null) {
            str7 = str3;
        }
        sb22.append(str7);
        sb22.append(' ');
        sb22.append("");
        companion42.log(level22, sb22.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion11 = Result.INSTANCE;
                Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke4, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
            } catch (Throwable th5) {
                Result.Companion companion12 = Result.INSTANCE;
                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                m1202constructorimpl3 = "";
            }
            String str18 = (String) m1202constructorimpl3;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(str18, str5);
                if (StringsKt.contains$default((CharSequence) str18, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace8, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                    if (stackTraceElement8 == null || (className4 = stackTraceElement8.getClassName()) == null) {
                        str8 = null;
                    } else {
                        str8 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            str9 = substringAfterLast$default;
                            matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str9);
                            if (matcher2.find()) {
                                str9 = matcher2.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                            }
                            Unit unit9 = Unit.INSTANCE;
                            if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str9 = str9.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb7 = new StringBuilder();
                            String str19 = "hvDocConfig: " + new Gson().toJson(docConfig2);
                            sb7.append(str19 != null ? str3 : str19);
                            sb7.append(' ');
                            sb7.append("");
                            Log.println(4, str9, sb7.toString());
                        }
                    }
                    String canonicalName8 = (activity == null || (cls5 = activity.getClass()) == null) ? str8 : cls5.getCanonicalName();
                    str9 = canonicalName8 == null ? str2 : canonicalName8;
                    matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str9);
                    if (matcher2.find()) {
                    }
                    Unit unit92 = Unit.INSTANCE;
                    if (str9.length() > 23) {
                        str9 = str9.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb72 = new StringBuilder();
                    String str192 = "hvDocConfig: " + new Gson().toJson(docConfig2);
                    sb72.append(str192 != null ? str3 : str192);
                    sb72.append(' ');
                    sb72.append("");
                    Log.println(4, str9, sb72.toString());
                }
            }
        }
        final List<Integer> list22 = list;
        final CancellableContinuation<? super HVResponse> cancellableContinuationImpl52 = cancellableContinuationImpl;
        HVDocsActivity.start(activity, docConfig2, new DocCaptureCompletionHandler() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performDocCapture$2$4
            /* JADX WARN: Removed duplicated region for block: B:58:0x0169  */
            /* JADX WARN: Removed duplicated region for block: B:66:0x01a3  */
            @Override // co.hyperverge.hypersnapsdk.listeners.CaptureCompletionHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onResult(HVError hVError, HVResponse hVResponse) {
                String canonicalName62;
                Class<?> cls9;
                Object m1202constructorimpl5;
                String str132;
                Class<?> cls10;
                Matcher matcher42;
                String str142;
                String className9;
                String className10;
                Activity activity2 = activity;
                HyperLogger.Level level32 = HyperLogger.Level.DEBUG;
                HyperLogger companion52 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb32 = new StringBuilder();
                StackTraceElement[] stackTrace32 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace32, "Throwable().stackTrace");
                StackTraceElement stackTraceElement32 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace32);
                String str152 = "N/A";
                if (stackTraceElement32 == null || (className10 = stackTraceElement32.getClassName()) == null || (canonicalName62 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName62 = (activity2 == null || (cls9 = activity2.getClass()) == null) ? null : cls9.getCanonicalName();
                    if (canonicalName62 == null) {
                        canonicalName62 = "N/A";
                    }
                }
                Matcher matcher52 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName62);
                if (matcher52.find()) {
                    canonicalName62 = matcher52.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName62, "replaceAll(\"\")");
                }
                if (canonicalName62.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName62 = canonicalName62.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName62, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb32.append(canonicalName62);
                sb32.append(" - ");
                String str162 = "performDocCapture() finished with: hvError = " + hVError + ", hvResponse = " + hVResponse;
                if (str162 == null) {
                    str162 = "null ";
                }
                sb32.append(str162);
                sb32.append(' ');
                sb32.append("");
                companion52.log(level32, sb32.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion62 = Result.INSTANCE;
                        Object invoke22 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke22, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke22).getPackageName());
                    } catch (Throwable th22) {
                        Result.Companion companion72 = Result.INSTANCE;
                        m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th22));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                        m1202constructorimpl5 = "";
                    }
                    String packageName22 = (String) m1202constructorimpl5;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName22, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName22, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace42 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace42, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement42 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace42);
                            if (stackTraceElement42 == null || (className9 = stackTraceElement42.getClassName()) == null) {
                                str132 = null;
                            } else {
                                str132 = null;
                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default2 != null) {
                                    str152 = substringAfterLast$default2;
                                    matcher42 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str152);
                                    if (matcher42.find()) {
                                        str152 = matcher42.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str152, "replaceAll(\"\")");
                                    }
                                    if (str152.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str152 = str152.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str152, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb42 = new StringBuilder();
                                    str142 = "performDocCapture() finished with: hvError = " + hVError + ", hvResponse = " + hVResponse;
                                    if (str142 == null) {
                                        str142 = "null ";
                                    }
                                    sb42.append(str142);
                                    sb42.append(' ');
                                    sb42.append("");
                                    Log.println(3, str152, sb42.toString());
                                }
                            }
                            String canonicalName72 = (activity2 == null || (cls10 = activity2.getClass()) == null) ? str132 : cls10.getCanonicalName();
                            if (canonicalName72 != null) {
                                str152 = canonicalName72;
                            }
                            matcher42 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str152);
                            if (matcher42.find()) {
                            }
                            if (str152.length() > 23) {
                                str152 = str152.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str152, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb422 = new StringBuilder();
                            str142 = "performDocCapture() finished with: hvError = " + hVError + ", hvResponse = " + hVResponse;
                            if (str142 == null) {
                            }
                            sb422.append(str142);
                            sb422.append(' ');
                            sb422.append("");
                            Log.println(3, str152, sb422.toString());
                        }
                    }
                }
                if (hVError == null && hVResponse != null && (disableOCR || HyperSnapBridgeKt.isSuccess(hVResponse, list22))) {
                    if (!disableOCR) {
                        HyperSnapBridgeKt.debugCrashIfTransactionIdMismatch(str, hVResponse);
                    }
                    if (cancellableContinuationImpl52.isActive()) {
                        CancellableContinuation<HVResponse> cancellableContinuation = cancellableContinuationImpl52;
                        Result.Companion companion82 = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m1202constructorimpl(hVResponse));
                        return;
                    }
                    return;
                }
                if (cancellableContinuationImpl52.isActive()) {
                    CancellableContinuation<HVResponse> cancellableContinuation2 = cancellableContinuationImpl52;
                    Result.Companion companion92 = Result.INSTANCE;
                    cancellableContinuation2.resumeWith(Result.m1202constructorimpl(ResultKt.createFailure(new HSBridgeException(hVError, hVResponse))));
                }
            }
        });
        result = cancellableContinuationImpl2.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0145, code lost:
    
        if (r0 != 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0157, code lost:
    
        if (r0 == 0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x015b, code lost:
    
        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x016a, code lost:
    
        if (r0.find() == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x016c, code lost:
    
        r9 = r0.replaceAll("");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "replaceAll(\"\")");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0179, code lost:
    
        if (r9.length() <= 23) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x017f, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 26) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0182, code lost:
    
        r9 = r9.substring(0, 23);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x018a, code lost:
    
        r0 = new java.lang.StringBuilder();
        r5 = "performBarcodeCapture() called with: hvQRConfig = " + r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x019e, code lost:
    
        if (r5 != null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01a1, code lost:
    
        r13 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01a2, code lost:
    
        r0.append(r13);
        r0.append(' ');
        r0.append("");
        android.util.Log.println(3, r9, r0.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x015a, code lost:
    
        r9 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v42 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object performBarcodeCapture(final Activity activity, WorkflowUIState.BarcodeCapture barcodeCapture, Continuation continuation) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        JSONObject jSONObject;
        ?? r0;
        Class<?> cls2;
        String className;
        String className2;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (activity == null || (cls = activity.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
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
        String str2 = "performBarcodeCapture() called with: hvQRConfig = " + barcodeCapture;
        String str3 = "null ";
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
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                        jSONObject = null;
                    } else {
                        jSONObject = null;
                        r0 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    r0 = (activity == null || (cls2 = activity.getClass()) == null) ? jSONObject : cls2.getCanonicalName();
                }
            }
        }
        jSONObject = null;
        String tag = barcodeCapture.getTag();
        HVQRConfig qrConfig = barcodeCapture.getQrConfig();
        Map<String, Object> textConfigs = barcodeCapture.getTextConfigs();
        qrConfig.setModuleId(tag);
        qrConfig.setCustomUIStrings(textConfigs != null ? new JSONObject(textConfigs) : jSONObject);
        HVQrScannerActivity.start(activity, qrConfig, new QRScannerCompletionHandler() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performBarcodeCapture$2$3
            @Override // co.hyperverge.hypersnapsdk.listeners.QRCompletionHandler
            public final void onResult(HVError hVError, JSONObject jSONObject2) {
                String canonicalName2;
                Class<?> cls3;
                Object m1202constructorimpl2;
                Class<?> cls4;
                String className3;
                String substringAfterLast$default;
                String className4;
                Activity activity2 = activity;
                HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb2 = new StringBuilder();
                StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                String str4 = "N/A";
                if (stackTraceElement3 == null || (className4 = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName2 = (activity2 == null || (cls3 = activity2.getClass()) == null) ? null : cls3.getCanonicalName();
                    if (canonicalName2 == null) {
                        canonicalName2 = "N/A";
                    }
                }
                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                if (matcher2.find()) {
                    canonicalName2 = matcher2.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                }
                if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName2 = canonicalName2.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb2.append(canonicalName2);
                sb2.append(" - ");
                StringBuilder sb3 = new StringBuilder();
                sb3.append("performBarcodeCapture() finished with: hvError = [");
                sb3.append(hVError != null ? hVError.getErrorMessage() : null);
                sb3.append("], hvResult = [");
                sb3.append(jSONObject2);
                sb3.append(']');
                String sb4 = sb3.toString();
                if (sb4 == null) {
                    sb4 = "null ";
                }
                sb2.append(sb4);
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
                            if (stackTraceElement4 == null || (className3 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                String canonicalName3 = (activity2 == null || (cls4 = activity2.getClass()) == null) ? null : cls4.getCanonicalName();
                                if (canonicalName3 != null) {
                                    str4 = canonicalName3;
                                }
                            } else {
                                str4 = substringAfterLast$default;
                            }
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                            if (matcher3.find()) {
                                str4 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                            }
                            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                str4 = str4.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb5 = new StringBuilder();
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append("performBarcodeCapture() finished with: hvError = [");
                            sb6.append(hVError != null ? hVError.getErrorMessage() : null);
                            sb6.append("], hvResult = [");
                            sb6.append(jSONObject2);
                            sb6.append(']');
                            String sb7 = sb6.toString();
                            if (sb7 == null) {
                                sb7 = "null ";
                            }
                            sb5.append(sb7);
                            sb5.append(' ');
                            sb5.append("");
                            Log.println(4, str4, sb5.toString());
                        }
                    }
                }
                if (hVError == null && jSONObject2 != null) {
                    if (cancellableContinuationImpl2.isActive()) {
                        CancellableContinuation<JSONObject> cancellableContinuation = cancellableContinuationImpl2;
                        Result.Companion companion7 = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m1202constructorimpl(jSONObject2));
                        return;
                    }
                    return;
                }
                if (cancellableContinuationImpl2.isActive()) {
                    CancellableContinuation<JSONObject> cancellableContinuation2 = cancellableContinuationImpl2;
                    Result.Companion companion8 = Result.INSTANCE;
                    cancellableContinuation2.resumeWith(Result.m1202constructorimpl(ResultKt.createFailure(new HSBridgeException(hVError, null))));
                }
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x02b4, code lost:
    
        if (r6 != null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x05e8, code lost:
    
        if (r10 != null) goto L233;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0667 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x073b  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x05fd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0600  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0612  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x064b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0773  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object performFaceCapture(final Activity activity, final String str, WorkflowUIState.FaceCapture faceCapture, Continuation continuation) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str2;
        String canonicalName2;
        Class<?> cls2;
        String className;
        boolean disableLiveness;
        List<Integer> allowedStatusCodes;
        String defaultCamera;
        boolean z;
        String canonicalName3;
        Class<?> cls3;
        Object m1202constructorimpl2;
        String canonicalName4;
        Class<?> cls4;
        String className2;
        String className3;
        StackTraceElement stackTraceElement;
        List<Integer> list;
        String str3;
        Matcher matcher;
        String str4;
        Object m1202constructorimpl3;
        String str5;
        String str6;
        Class<?> cls5;
        Matcher matcher2;
        String str7;
        String className4;
        Object result;
        Class<?> cls6;
        String className5;
        String str8;
        String str9;
        Object m1202constructorimpl4;
        String canonicalName5;
        Class<?> cls7;
        String className6;
        Class<?> cls8;
        String className7;
        String className8;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String str10 = "Throwable().stackTrace";
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement2 == null || (className8 = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (activity == null || (cls = activity.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher3 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName);
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
        String str11 = "performFaceCapture() called with: continuation = " + cancellableContinuationImpl2;
        if (str11 == null) {
            str11 = "null ";
        }
        sb.append(str11);
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
                str2 = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement3 == null || (className = stackTraceElement3.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (activity == null || (cls2 = activity.getClass()) == null) ? null : cls2.getCanonicalName();
                        if (canonicalName2 == null) {
                            canonicalName2 = str2;
                        }
                    }
                    Matcher matcher4 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName2);
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
                    String str12 = "performFaceCapture() called with: continuation = " + cancellableContinuationImpl2;
                    if (str12 == null) {
                        str12 = "null ";
                    }
                    sb2.append(str12);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                String tag = faceCapture.getTag();
                String url = faceCapture.getUrl();
                disableLiveness = faceCapture.getDisableLiveness();
                HVFaceConfig faceConfig = faceCapture.getFaceConfig();
                Map<String, String> livenessParams = faceCapture.getLivenessParams();
                Map<String, String> livenessHeaders = faceCapture.getLivenessHeaders();
                Map<String, Object> textConfigs = faceCapture.getTextConfigs();
                allowedStatusCodes = faceCapture.getAllowedStatusCodes();
                defaultCamera = faceCapture.getDefaultCamera();
                boolean enableOverlay = faceCapture.getEnableOverlay();
                boolean validateSignature = faceCapture.getValidateSignature();
                boolean enableLookStraight = faceCapture.getEnableLookStraight();
                boolean zoomByDefault = faceCapture.getZoomByDefault();
                faceConfig.setModuleId(tag);
                faceConfig.setShouldUseDefaultZoom(zoomByDefault);
                faceConfig.setShouldReturnFullImageUrl(true);
                faceConfig.setAllowFaceTilt(!enableLookStraight);
                faceConfig.setCustomUIStrings(textConfigs == null ? new JSONObject(textConfigs) : null);
                CoreExtsKt.ifDebug(new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performFaceCapture$2$2$2
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                });
                if (disableLiveness) {
                    faceConfig.setLivenessMode(HVFaceConfig.LivenessMode.TEXTURELIVENESS);
                    faceConfig.setLivenessEndpoint(url);
                    JSONObject jSONObject = new JSONObject();
                    for (Map.Entry<String, String> entry : livenessParams.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                    faceConfig.setLivenessAPIParameters(jSONObject);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("transactionId", str);
                    jSONObject2.put("moduleId", tag);
                    for (Map.Entry<String, String> entry2 : livenessHeaders.entrySet()) {
                        jSONObject2.put(entry2.getKey(), entry2.getValue());
                    }
                    HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                    HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb3 = new StringBuilder();
                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                    if (stackTraceElement4 == null || (className7 = stackTraceElement4.getClassName()) == null) {
                        str8 = "Throwable().stackTrace";
                        z = disableLiveness;
                    } else {
                        str8 = "Throwable().stackTrace";
                        z = disableLiveness;
                        str9 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                    }
                    String canonicalName6 = (faceConfig == null || (cls8 = faceConfig.getClass()) == null) ? null : cls8.getCanonicalName();
                    str9 = canonicalName6 == null ? str2 : canonicalName6;
                    Matcher matcher5 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str9);
                    if (matcher5.find()) {
                        str9 = matcher5.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str9 = str9.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb3.append(str9);
                    sb3.append(" - ");
                    String str13 = "liveness headers: " + jSONObject2;
                    if (str13 == null) {
                        str13 = "null ";
                    }
                    sb3.append(str13);
                    sb3.append(' ');
                    sb3.append("");
                    companion4.log(level2, sb3.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                            m1202constructorimpl4 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl4;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                str10 = str8;
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str10);
                                StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement5 == null || (className6 = stackTraceElement5.getClassName()) == null || (canonicalName5 = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    canonicalName5 = (faceConfig == null || (cls7 = faceConfig.getClass()) == null) ? null : cls7.getCanonicalName();
                                    if (canonicalName5 == null) {
                                        canonicalName5 = str2;
                                    }
                                }
                                Matcher matcher6 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName5);
                                if (matcher6.find()) {
                                    canonicalName5 = matcher6.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName5, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (canonicalName5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName5 = canonicalName5.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName5, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str14 = "liveness headers: " + jSONObject2;
                                if (str14 == null) {
                                    str14 = "null ";
                                }
                                sb4.append(str14);
                                sb4.append(' ');
                                sb4.append("");
                                Log.println(4, canonicalName5, sb4.toString());
                                faceConfig.setLivenessAPIHeaders(jSONObject2);
                            }
                        }
                    }
                    str10 = str8;
                    faceConfig.setLivenessAPIHeaders(jSONObject2);
                } else {
                    z = disableLiveness;
                    faceConfig.setLivenessMode(HVFaceConfig.LivenessMode.NONE);
                    HyperLogger.Level level3 = HyperLogger.Level.DEBUG;
                    HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb5 = new StringBuilder();
                    StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                    if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName3 = (faceConfig == null || (cls3 = faceConfig.getClass()) == null) ? null : cls3.getCanonicalName();
                        if (canonicalName3 == null) {
                            canonicalName3 = str2;
                        }
                    }
                    Matcher matcher7 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName3);
                    if (matcher7.find()) {
                        canonicalName3 = matcher7.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "replaceAll(\"\")");
                    }
                    Unit unit5 = Unit.INSTANCE;
                    if (canonicalName3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName3 = canonicalName3.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName3, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb5.append(canonicalName3);
                    sb5.append(" - ");
                    sb5.append("performFaceCapture() skipping liveness as disableLiveness=true");
                    sb5.append(' ');
                    sb5.append("");
                    companion7.log(level3, sb5.toString());
                    if (!CoreExtsKt.isRelease()) {
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
                                StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement7 == null || (className2 = stackTraceElement7.getClassName()) == null || (canonicalName4 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    canonicalName4 = (faceConfig == null || (cls4 = faceConfig.getClass()) == null) ? null : cls4.getCanonicalName();
                                    if (canonicalName4 == null) {
                                        canonicalName4 = str2;
                                    }
                                }
                                Matcher matcher8 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(canonicalName4);
                                if (matcher8.find()) {
                                    canonicalName4 = matcher8.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(canonicalName4, "replaceAll(\"\")");
                                }
                                Unit unit6 = Unit.INSTANCE;
                                if (canonicalName4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    canonicalName4 = canonicalName4.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(canonicalName4, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                Log.println(3, canonicalName4, "performFaceCapture() skipping liveness as disableLiveness=true ");
                            }
                        }
                    }
                }
                if (Intrinsics.areEqual(defaultCamera, "back")) {
                    faceConfig.setShouldUseBackCamera(true);
                    faceConfig.setShouldUseDefaultZoom(true);
                }
                faceConfig.setShouldEnableOverlay(enableOverlay);
                faceConfig.setAllowedStatusCodes(allowedStatusCodes);
                Unit unit7 = Unit.INSTANCE;
                HyperSnapSDK.setShouldUseSignature(validateSignature);
                HyperLogger.Level level4 = HyperLogger.Level.DEBUG;
                HyperLogger companion10 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb6 = new StringBuilder();
                StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace7, str10);
                stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                if (stackTraceElement != null || (className5 = stackTraceElement.getClassName()) == null) {
                    list = allowedStatusCodes;
                } else {
                    list = allowedStatusCodes;
                    str3 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                }
                String canonicalName7 = (activity != null || (cls6 = activity.getClass()) == null) ? null : cls6.getCanonicalName();
                str3 = canonicalName7 != null ? str2 : canonicalName7;
                matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str3);
                if (matcher.find()) {
                    str3 = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
                }
                Unit unit8 = Unit.INSTANCE;
                if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    str3 = str3.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb6.append(str3);
                sb6.append(" - ");
                str4 = "hvFaceConfig: " + faceConfig;
                if (str4 == null) {
                    str4 = "null ";
                }
                sb6.append(str4);
                sb6.append(' ');
                sb6.append("");
                companion10.log(level4, sb6.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion11 = Result.INSTANCE;
                        Object invoke4 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke4, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                    } catch (Throwable th4) {
                        Result.Companion companion12 = Result.INSTANCE;
                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                        m1202constructorimpl3 = "";
                    }
                    String packageName4 = (String) m1202constructorimpl3;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName4, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName4, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace8, str10);
                            StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                            if (stackTraceElement8 == null || (className4 = stackTraceElement8.getClassName()) == null) {
                                str5 = null;
                            } else {
                                str5 = null;
                                String substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                if (substringAfterLast$default != null) {
                                    str6 = substringAfterLast$default;
                                    matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str6);
                                    if (matcher2.find()) {
                                        str6 = matcher2.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                                    }
                                    Unit unit9 = Unit.INSTANCE;
                                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                        str6 = str6.substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    StringBuilder sb7 = new StringBuilder();
                                    str7 = "hvFaceConfig: " + faceConfig;
                                    if (str7 == null) {
                                        str7 = "null ";
                                    }
                                    sb7.append(str7);
                                    sb7.append(' ');
                                    sb7.append("");
                                    Log.println(4, str6, sb7.toString());
                                }
                            }
                            String canonicalName8 = (activity == null || (cls5 = activity.getClass()) == null) ? str5 : cls5.getCanonicalName();
                            str6 = canonicalName8 == null ? str2 : canonicalName8;
                            matcher2 = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str6);
                            if (matcher2.find()) {
                            }
                            Unit unit92 = Unit.INSTANCE;
                            if (str6.length() > 23) {
                                str6 = str6.substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb72 = new StringBuilder();
                            str7 = "hvFaceConfig: " + faceConfig;
                            if (str7 == null) {
                            }
                            sb72.append(str7);
                            sb72.append(' ');
                            sb72.append("");
                            Log.println(4, str6, sb72.toString());
                        }
                    }
                }
                final boolean z2 = z;
                final List<Integer> list2 = list;
                HVFaceActivity.start(activity, faceConfig, new FaceCaptureCompletionHandler() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performFaceCapture$2$4
                    /* JADX WARN: Code restructure failed: missing block: B:57:0x014e, code lost:
                    
                        if (r0 != null) goto L61;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:62:0x0160, code lost:
                    
                        if (r0 == null) goto L62;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:63:0x0164, code lost:
                    
                        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r9);
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:64:0x0173, code lost:
                    
                        if (r0.find() == false) goto L65;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:65:0x0175, code lost:
                    
                        r9 = r0.replaceAll("");
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "replaceAll(\"\")");
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:67:0x0182, code lost:
                    
                        if (r9.length() <= 23) goto L71;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:69:0x0188, code lost:
                    
                        if (android.os.Build.VERSION.SDK_INT < 26) goto L70;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:70:0x018b, code lost:
                    
                        r9 = r9.substring(0, 23);
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String…ing(startIndex, endIndex)");
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:71:0x0193, code lost:
                    
                        r0 = new java.lang.StringBuilder();
                        r4 = new java.lang.StringBuilder();
                        r4.append("performFaceCapture() finished with: hvError = [");
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:72:0x01a0, code lost:
                    
                        if (r18 == null) goto L74;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:73:0x01a2, code lost:
                    
                        r12 = r18.getErrorMessage();
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:74:0x01a8, code lost:
                    
                        r4.append(r12);
                        r4.append("], hvResponse = [");
                        r4.append(r19);
                        r4.append(']');
                        r4 = r4.toString();
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ba, code lost:
                    
                        if (r4 != null) goto L78;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:76:0x01bc, code lost:
                    
                        r4 = "null ";
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:77:0x01be, code lost:
                    
                        r0.append(r4);
                        r0.append(' ');
                        r0.append("");
                        android.util.Log.println(3, r9, r0.toString());
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:78:0x01a7, code lost:
                    
                        r12 = r6;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:80:0x0163, code lost:
                    
                        r9 = r0;
                     */
                    @Override // co.hyperverge.hypersnapsdk.listeners.CaptureCompletionHandler
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final void onResult(HVError hVError, HVResponse hVResponse) {
                        String canonicalName9;
                        Class<?> cls9;
                        Object m1202constructorimpl5;
                        String str15;
                        String str16;
                        Class<?> cls10;
                        String className9;
                        String className10;
                        Activity activity2 = activity;
                        HyperLogger.Level level5 = HyperLogger.Level.DEBUG;
                        HyperLogger companion13 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb8 = new StringBuilder();
                        StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace9, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                        String str17 = "N/A";
                        if (stackTraceElement9 == null || (className10 = stackTraceElement9.getClassName()) == null || (canonicalName9 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName9 = (activity2 == null || (cls9 = activity2.getClass()) == null) ? null : cls9.getCanonicalName();
                            if (canonicalName9 == null) {
                                canonicalName9 = "N/A";
                            }
                        }
                        Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName9);
                        if (matcher9.find()) {
                            canonicalName9 = matcher9.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName9, "replaceAll(\"\")");
                        }
                        if (canonicalName9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName9 = canonicalName9.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName9, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb8.append(canonicalName9);
                        sb8.append(" - ");
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append("performFaceCapture() finished with: hvError = [");
                        sb9.append(hVError != null ? hVError.getErrorMessage() : null);
                        sb9.append("], hvResponse = [");
                        sb9.append(hVResponse);
                        sb9.append(']');
                        String sb10 = sb9.toString();
                        if (sb10 == null) {
                            sb10 = "null ";
                        }
                        sb8.append(sb10);
                        sb8.append(' ');
                        sb8.append("");
                        companion13.log(level5, sb8.toString());
                        if (!CoreExtsKt.isRelease()) {
                            try {
                                Result.Companion companion14 = Result.INSTANCE;
                                Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                Intrinsics.checkNotNull(invoke5, "null cannot be cast to non-null type android.app.Application");
                                m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                            } catch (Throwable th5) {
                                Result.Companion companion15 = Result.INSTANCE;
                                m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                            }
                            if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                                m1202constructorimpl5 = "";
                            }
                            String packageName5 = (String) m1202constructorimpl5;
                            if (CoreExtsKt.isDebug()) {
                                Intrinsics.checkNotNullExpressionValue(packageName5, "packageName");
                                if (StringsKt.contains$default((CharSequence) packageName5, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                    StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace10, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                                    if (stackTraceElement10 == null || (className9 = stackTraceElement10.getClassName()) == null) {
                                        str15 = null;
                                    } else {
                                        str15 = null;
                                        str16 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    }
                                    str16 = (activity2 == null || (cls10 = activity2.getClass()) == null) ? str15 : cls10.getCanonicalName();
                                }
                            }
                        }
                        if (hVError == null && hVResponse != null && (z2 || HyperSnapBridgeKt.isSuccess(hVResponse, list2))) {
                            if (!z2) {
                                HyperSnapBridgeKt.debugCrashIfTransactionIdMismatch(str, hVResponse);
                            }
                            if (cancellableContinuationImpl2.isActive()) {
                                CancellableContinuation<HVResponse> cancellableContinuation = cancellableContinuationImpl2;
                                Result.Companion companion16 = Result.INSTANCE;
                                cancellableContinuation.resumeWith(Result.m1202constructorimpl(hVResponse));
                                return;
                            }
                            return;
                        }
                        if (cancellableContinuationImpl2.isActive()) {
                            CancellableContinuation<HVResponse> cancellableContinuation2 = cancellableContinuationImpl2;
                            Result.Companion companion17 = Result.INSTANCE;
                            cancellableContinuation2.resumeWith(Result.m1202constructorimpl(ResultKt.createFailure(new HSBridgeException(hVError, hVResponse))));
                        }
                    }
                });
                result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result;
            }
        }
        str2 = "N/A";
        String tag2 = faceCapture.getTag();
        String url2 = faceCapture.getUrl();
        disableLiveness = faceCapture.getDisableLiveness();
        HVFaceConfig faceConfig2 = faceCapture.getFaceConfig();
        Map<String, String> livenessParams2 = faceCapture.getLivenessParams();
        Map<String, String> livenessHeaders2 = faceCapture.getLivenessHeaders();
        Map<String, Object> textConfigs2 = faceCapture.getTextConfigs();
        allowedStatusCodes = faceCapture.getAllowedStatusCodes();
        defaultCamera = faceCapture.getDefaultCamera();
        boolean enableOverlay2 = faceCapture.getEnableOverlay();
        boolean validateSignature2 = faceCapture.getValidateSignature();
        boolean enableLookStraight2 = faceCapture.getEnableLookStraight();
        boolean zoomByDefault2 = faceCapture.getZoomByDefault();
        faceConfig2.setModuleId(tag2);
        faceConfig2.setShouldUseDefaultZoom(zoomByDefault2);
        faceConfig2.setShouldReturnFullImageUrl(true);
        faceConfig2.setAllowFaceTilt(!enableLookStraight2);
        faceConfig2.setCustomUIStrings(textConfigs2 == null ? new JSONObject(textConfigs2) : null);
        CoreExtsKt.ifDebug(new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performFaceCapture$2$2$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
        if (disableLiveness) {
        }
        if (Intrinsics.areEqual(defaultCamera, "back")) {
        }
        faceConfig2.setShouldEnableOverlay(enableOverlay2);
        faceConfig2.setAllowedStatusCodes(allowedStatusCodes);
        Unit unit72 = Unit.INSTANCE;
        HyperSnapSDK.setShouldUseSignature(validateSignature2);
        HyperLogger.Level level42 = HyperLogger.Level.DEBUG;
        HyperLogger companion102 = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb62 = new StringBuilder();
        StackTraceElement[] stackTrace72 = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace72, str10);
        stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace72);
        if (stackTraceElement != null) {
        }
        list = allowedStatusCodes;
        if (activity != null) {
        }
        if (canonicalName7 != null) {
        }
        matcher = LogExtsKt.access$getANON_CLASS_PATTERN$p().matcher(str3);
        if (matcher.find()) {
        }
        Unit unit82 = Unit.INSTANCE;
        if (str3.length() > 23) {
            str3 = str3.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb62.append(str3);
        sb62.append(" - ");
        str4 = "hvFaceConfig: " + faceConfig2;
        if (str4 == null) {
        }
        sb62.append(str4);
        sb62.append(' ');
        sb62.append("");
        companion102.log(level42, sb62.toString());
        if (!CoreExtsKt.isRelease()) {
        }
        final boolean z22 = z;
        final List<Integer> list22 = list;
        HVFaceActivity.start(activity, faceConfig2, new FaceCaptureCompletionHandler() { // from class: co.hyperverge.hyperkyc.core.hv.HyperSnapBridgeKt$performFaceCapture$2$4
            /* JADX WARN: Code restructure failed: missing block: B:57:0x014e, code lost:
            
                if (r0 != null) goto L61;
             */
            /* JADX WARN: Code restructure failed: missing block: B:62:0x0160, code lost:
            
                if (r0 == null) goto L62;
             */
            /* JADX WARN: Code restructure failed: missing block: B:63:0x0164, code lost:
            
                r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r9);
             */
            /* JADX WARN: Code restructure failed: missing block: B:64:0x0173, code lost:
            
                if (r0.find() == false) goto L65;
             */
            /* JADX WARN: Code restructure failed: missing block: B:65:0x0175, code lost:
            
                r9 = r0.replaceAll("");
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "replaceAll(\"\")");
             */
            /* JADX WARN: Code restructure failed: missing block: B:67:0x0182, code lost:
            
                if (r9.length() <= 23) goto L71;
             */
            /* JADX WARN: Code restructure failed: missing block: B:69:0x0188, code lost:
            
                if (android.os.Build.VERSION.SDK_INT < 26) goto L70;
             */
            /* JADX WARN: Code restructure failed: missing block: B:70:0x018b, code lost:
            
                r9 = r9.substring(0, 23);
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String…ing(startIndex, endIndex)");
             */
            /* JADX WARN: Code restructure failed: missing block: B:71:0x0193, code lost:
            
                r0 = new java.lang.StringBuilder();
                r4 = new java.lang.StringBuilder();
                r4.append("performFaceCapture() finished with: hvError = [");
             */
            /* JADX WARN: Code restructure failed: missing block: B:72:0x01a0, code lost:
            
                if (r18 == null) goto L74;
             */
            /* JADX WARN: Code restructure failed: missing block: B:73:0x01a2, code lost:
            
                r12 = r18.getErrorMessage();
             */
            /* JADX WARN: Code restructure failed: missing block: B:74:0x01a8, code lost:
            
                r4.append(r12);
                r4.append("], hvResponse = [");
                r4.append(r19);
                r4.append(']');
                r4 = r4.toString();
             */
            /* JADX WARN: Code restructure failed: missing block: B:75:0x01ba, code lost:
            
                if (r4 != null) goto L78;
             */
            /* JADX WARN: Code restructure failed: missing block: B:76:0x01bc, code lost:
            
                r4 = "null ";
             */
            /* JADX WARN: Code restructure failed: missing block: B:77:0x01be, code lost:
            
                r0.append(r4);
                r0.append(' ');
                r0.append("");
                android.util.Log.println(3, r9, r0.toString());
             */
            /* JADX WARN: Code restructure failed: missing block: B:78:0x01a7, code lost:
            
                r12 = r6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:80:0x0163, code lost:
            
                r9 = r0;
             */
            @Override // co.hyperverge.hypersnapsdk.listeners.CaptureCompletionHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void onResult(HVError hVError, HVResponse hVResponse) {
                String canonicalName9;
                Class<?> cls9;
                Object m1202constructorimpl5;
                String str15;
                String str16;
                Class<?> cls10;
                String className9;
                String className10;
                Activity activity2 = activity;
                HyperLogger.Level level5 = HyperLogger.Level.DEBUG;
                HyperLogger companion13 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb8 = new StringBuilder();
                StackTraceElement[] stackTrace9 = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace9, "Throwable().stackTrace");
                StackTraceElement stackTraceElement9 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace9);
                String str17 = "N/A";
                if (stackTraceElement9 == null || (className10 = stackTraceElement9.getClassName()) == null || (canonicalName9 = StringsKt.substringAfterLast$default(className10, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                    canonicalName9 = (activity2 == null || (cls9 = activity2.getClass()) == null) ? null : cls9.getCanonicalName();
                    if (canonicalName9 == null) {
                        canonicalName9 = "N/A";
                    }
                }
                Matcher matcher9 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName9);
                if (matcher9.find()) {
                    canonicalName9 = matcher9.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(canonicalName9, "replaceAll(\"\")");
                }
                if (canonicalName9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    canonicalName9 = canonicalName9.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(canonicalName9, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                sb8.append(canonicalName9);
                sb8.append(" - ");
                StringBuilder sb9 = new StringBuilder();
                sb9.append("performFaceCapture() finished with: hvError = [");
                sb9.append(hVError != null ? hVError.getErrorMessage() : null);
                sb9.append("], hvResponse = [");
                sb9.append(hVResponse);
                sb9.append(']');
                String sb10 = sb9.toString();
                if (sb10 == null) {
                    sb10 = "null ";
                }
                sb8.append(sb10);
                sb8.append(' ');
                sb8.append("");
                companion13.log(level5, sb8.toString());
                if (!CoreExtsKt.isRelease()) {
                    try {
                        Result.Companion companion14 = Result.INSTANCE;
                        Object invoke5 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke5, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl5 = Result.m1202constructorimpl(((Application) invoke5).getPackageName());
                    } catch (Throwable th5) {
                        Result.Companion companion15 = Result.INSTANCE;
                        m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th5));
                    }
                    if (Result.m1208isFailureimpl(m1202constructorimpl5)) {
                        m1202constructorimpl5 = "";
                    }
                    String packageName5 = (String) m1202constructorimpl5;
                    if (CoreExtsKt.isDebug()) {
                        Intrinsics.checkNotNullExpressionValue(packageName5, "packageName");
                        if (StringsKt.contains$default((CharSequence) packageName5, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                            StackTraceElement[] stackTrace10 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace10, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement10 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace10);
                            if (stackTraceElement10 == null || (className9 = stackTraceElement10.getClassName()) == null) {
                                str15 = null;
                            } else {
                                str15 = null;
                                str16 = StringsKt.substringAfterLast$default(className9, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                            }
                            str16 = (activity2 == null || (cls10 = activity2.getClass()) == null) ? str15 : cls10.getCanonicalName();
                        }
                    }
                }
                if (hVError == null && hVResponse != null && (z22 || HyperSnapBridgeKt.isSuccess(hVResponse, list22))) {
                    if (!z22) {
                        HyperSnapBridgeKt.debugCrashIfTransactionIdMismatch(str, hVResponse);
                    }
                    if (cancellableContinuationImpl2.isActive()) {
                        CancellableContinuation<HVResponse> cancellableContinuation = cancellableContinuationImpl2;
                        Result.Companion companion16 = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m1202constructorimpl(hVResponse));
                        return;
                    }
                    return;
                }
                if (cancellableContinuationImpl2.isActive()) {
                    CancellableContinuation<HVResponse> cancellableContinuation2 = cancellableContinuationImpl2;
                    Result.Companion companion17 = Result.INSTANCE;
                    cancellableContinuation2.resumeWith(Result.m1202constructorimpl(ResultKt.createFailure(new HSBridgeException(hVError, hVResponse))));
                }
            }
        });
        result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        }
        return result;
    }
}
