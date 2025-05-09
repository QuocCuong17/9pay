package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.widget.FrameLayout;
import androidx.core.os.BundleKt;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.JSONExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import co.hyperverge.hypersnapsdk.service.security.GKYCSignatureVerify;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Result;", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$startApiFlow$2", f = "HKMainActivity.kt", i = {0, 1, 1, 1, 1}, l = {1704, 1714}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$coroutineScope", "response", "isNetworkError", "code"}, s = {"L$0", "L$0", "L$4", "L$5", "I$0"})
/* loaded from: classes2.dex */
public final class HKMainActivity$startApiFlow$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Response>>, Object> {
    final /* synthetic */ WorkflowUIState.ApiCall $apiFlowUIState;
    final /* synthetic */ boolean $isRetry;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$startApiFlow$2(boolean z, WorkflowUIState.ApiCall apiCall, HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$startApiFlow$2> continuation) {
        super(2, continuation);
        this.$isRetry = z;
        this.$apiFlowUIState = apiCall;
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$startApiFlow$2 hKMainActivity$startApiFlow$2 = new HKMainActivity$startApiFlow$2(this.$isRetry, this.$apiFlowUIState, this.this$0, continuation);
        hKMainActivity$startApiFlow$2.L$0 = obj;
        return hKMainActivity$startApiFlow$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Response>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Result<Response>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Result<Response>> continuation) {
        return ((HKMainActivity$startApiFlow$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x06dd, code lost:
    
        if (r15 != null) goto L261;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x09d6, code lost:
    
        if (r4 != 0) goto L374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0485, code lost:
    
        if (r13 != null) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x00d3, code lost:
    
        if (r1 != 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x028c, code lost:
    
        if (r0.equals("none") == false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0afe, code lost:
    
        if (r1 != null) goto L421;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0bde  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0939  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0949  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x07ac  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0996  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0883  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0b2a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0b73  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0b7f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0b87  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0b84  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0395  */
    /* JADX WARN: Type inference failed for: r13v18, types: [T] */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v43 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v23, types: [T] */
    /* JADX WARN: Type inference failed for: r15v25 */
    /* JADX WARN: Type inference failed for: r15v26 */
    /* JADX WARN: Type inference failed for: r15v30 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9, types: [T] */
    /* JADX WARN: Type inference failed for: r1v123 */
    /* JADX WARN: Type inference failed for: r1v124 */
    /* JADX WARN: Type inference failed for: r1v125 */
    /* JADX WARN: Type inference failed for: r1v128, types: [T] */
    /* JADX WARN: Type inference failed for: r1v138, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v140, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v156 */
    /* JADX WARN: Type inference failed for: r1v157 */
    /* JADX WARN: Type inference failed for: r1v158 */
    /* JADX WARN: Type inference failed for: r1v161, types: [T] */
    /* JADX WARN: Type inference failed for: r1v171, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v173, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v181 */
    /* JADX WARN: Type inference failed for: r1v182 */
    /* JADX WARN: Type inference failed for: r1v183 */
    /* JADX WARN: Type inference failed for: r1v32, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v39, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T] */
    /* JADX WARN: Type inference failed for: r1v84, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v14, types: [T] */
    /* JADX WARN: Type inference failed for: r2v24, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v88 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v30, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v49 */
    /* JADX WARN: Type inference failed for: r4v7, types: [T] */
    /* JADX WARN: Type inference failed for: r5v50, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v75, types: [T, java.lang.Object, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        CharSequence charSequence;
        String str;
        String str2;
        ?? r1;
        String str3;
        Object m1202constructorimpl;
        String str4;
        String str5;
        HKMainActivity$startApiFlow$2 hKMainActivity$startApiFlow$2;
        ?? canonicalName;
        Class<?> cls;
        String str6;
        String className;
        Object m396customApiCalleH_QyT8$hyperkyc_release$default;
        Object obj3;
        Class<?> cls2;
        String className2;
        HKMainActivity hKMainActivity;
        WorkflowUIState.ApiCall apiCall;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        HKMainActivity$startApiFlow$2 hKMainActivity$startApiFlow$22;
        Object obj4;
        Response response;
        String str12;
        String str13;
        Ref.BooleanRef booleanRef;
        String str14;
        Object onIO$default;
        int i;
        Object obj5;
        Throwable m1205exceptionOrNullimpl;
        Object obj6;
        String str15;
        WorkflowUIState.ApiCall apiCall2;
        String str16;
        ?? r4;
        String str17;
        String str18;
        Object m1202constructorimpl2;
        String str19;
        String str20;
        ?? r15;
        Matcher matcher;
        String str21;
        String str22;
        String localizedMessage;
        Class<?> cls3;
        String className3;
        Class<?> cls4;
        String className4;
        Object value;
        final HyperKycData.APIData aPIData;
        Object obj7;
        WorkflowUIState.ApiCall apiCall3;
        int i2;
        HKMainActivity hKMainActivity2;
        WorkflowUIState.ApiCall apiCall4;
        boolean z;
        CoroutineScope coroutineScope2;
        Object m1202constructorimpl3;
        Throwable m1205exceptionOrNullimpl2;
        Response response2;
        String str23;
        String str24;
        JSONObject jSONObject;
        int i3;
        HKMainActivity hKMainActivity3;
        Response response3;
        ?? r13;
        String str25;
        String str26;
        String str27;
        Object m1202constructorimpl4;
        ?? canonicalName2;
        Class<?> cls5;
        String str28;
        String str29;
        String className5;
        Class<?> cls6;
        String className6;
        Object m1202constructorimpl5;
        JSONObject jSONObject2;
        String str30;
        String str31;
        String str32;
        ?? r152;
        String str33;
        String str34;
        Object m1202constructorimpl6;
        ?? canonicalName3;
        Class<?> cls7;
        String str35;
        String str36;
        String className7;
        Class<?> cls8;
        String className8;
        List list;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        String str37 = "";
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            obj2 = "N/A";
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null) {
                charSequence = "co.hyperverge";
                str = "packageName";
                str2 = "Throwable().stackTrace";
            } else {
                charSequence = "co.hyperverge";
                str = "packageName";
                str2 = "Throwable().stackTrace";
                r1 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            r1 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? 0 : cls2.getCanonicalName();
            if (r1 == 0) {
                r1 = obj2;
            }
            objectRef.element = r1;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                objectRef.element = replaceAll;
            }
            if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                str3 = (String) objectRef.element;
            } else {
                str3 = ((String) objectRef.element).substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb.append(str3);
            sb.append(" - ");
            sb.append("startApiFlow() called");
            sb.append(' ');
            sb.append("");
            companion.log(level, sb.toString());
            if (CoreExtsKt.isRelease()) {
                hKMainActivity$startApiFlow$2 = this;
                str4 = str2;
                str5 = str;
            } else {
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
                String str38 = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    str5 = str;
                    Intrinsics.checkNotNullExpressionValue(str38, str5);
                    if (StringsKt.contains$default((CharSequence) str38, charSequence, false, 2, (Object) null)) {
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        str4 = str2;
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, str4);
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                            canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
                            if (canonicalName == 0) {
                                canonicalName = obj2;
                            }
                        }
                        objectRef2.element = canonicalName;
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher3.find()) {
                            ?? replaceAll2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str6 = (String) objectRef2.element;
                        } else {
                            str6 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        Log.println(3, str6, "startApiFlow() called ");
                    } else {
                        str4 = str2;
                    }
                } else {
                    str4 = str2;
                    str5 = str;
                }
                hKMainActivity$startApiFlow$2 = this;
            }
            if (!hKMainActivity$startApiFlow$2.$isRetry) {
                String uiStyle = hKMainActivity$startApiFlow$2.$apiFlowUIState.getUiStyle();
                if (uiStyle != null) {
                    int hashCode = uiStyle.hashCode();
                    if (hashCode != 3387192) {
                        if (hashCode == 106852524 ? uiStyle.equals(WorkflowModule.UI_STYLE_POPUP) : hashCode == 1572298953 && uiStyle.equals(WorkflowModule.UI_STYLE_ACTION_SHEET)) {
                            throw new NotImplementedError(null, 1, null);
                        }
                    }
                }
                HKMainActivity hKMainActivity4 = hKMainActivity$startApiFlow$2.this$0;
                LoadingFragment loadingFragment = new LoadingFragment();
                Pair[] pairArr = new Pair[1];
                Map<String, Object> textConfigs = hKMainActivity$startApiFlow$2.$apiFlowUIState.getTextConfigs();
                if (!(textConfigs instanceof Map)) {
                    textConfigs = null;
                }
                pairArr[0] = TuplesKt.to("textConfigs", textConfigs);
                ActivityExtsKt.replaceContent$default(hKMainActivity4, loadingFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
            } else {
                FrameLayout frameLayout = hKMainActivity$startApiFlow$2.this$0.getBinding$hyperkyc_release().flContent;
                Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.flContent");
                frameLayout.setVisibility(0);
            }
            HKMainActivity.updateEndState$hyperkyc_release$default(hKMainActivity$startApiFlow$2.this$0, true, null, false, null, 14, null);
            MainVM.updateApiCallData$hyperkyc_release$default(hKMainActivity$startApiFlow$2.this$0.getMainVM(), hKMainActivity$startApiFlow$2.$apiFlowUIState, null, HyperKycData.APIResult.STATE_PROCESSING, false, 10, null);
            NetworkRepo networkRepo = NetworkRepo.INSTANCE;
            String url = hKMainActivity$startApiFlow$2.$apiFlowUIState.getUrl();
            String method = hKMainActivity$startApiFlow$2.$apiFlowUIState.getMethod();
            HashMap<String, String> headers = hKMainActivity$startApiFlow$2.$apiFlowUIState.getHeaders();
            WorkflowUIState.ApiCall apiCall5 = hKMainActivity$startApiFlow$2.$apiFlowUIState;
            final HKMainActivity hKMainActivity5 = hKMainActivity$startApiFlow$2.this$0;
            hKMainActivity$startApiFlow$2.L$0 = coroutineScope;
            hKMainActivity$startApiFlow$2.label = 1;
            m396customApiCalleH_QyT8$hyperkyc_release$default = NetworkRepo.m396customApiCalleH_QyT8$hyperkyc_release$default(networkRepo, url, method, headers, apiCall5.getRequestBody(new Function1<String, Object>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$startApiFlow$2.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(String getRequestBody) {
                    Intrinsics.checkNotNullParameter(getRequestBody, "$this$getRequestBody");
                    return HKMainActivity.this.getMainVM().injectFromVariables$hyperkyc_release(getRequestBody);
                }
            }), 0L, null, 0, hKMainActivity$startApiFlow$2, 112, null);
            obj3 = coroutine_suspended;
            if (m396customApiCalleH_QyT8$hyperkyc_release$default == obj3) {
                return obj3;
            }
        } else {
            if (i4 != 1) {
                if (i4 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i5 = this.I$0;
                Ref.BooleanRef booleanRef2 = (Ref.BooleanRef) this.L$5;
                Response response4 = (Response) this.L$4;
                apiCall = (WorkflowUIState.ApiCall) this.L$3;
                HKMainActivity hKMainActivity6 = (HKMainActivity) this.L$2;
                Object obj8 = this.L$1;
                CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                charSequence = "co.hyperverge";
                str12 = "packageName";
                str10 = "android.app.AppGlobals";
                str4 = "Throwable().stackTrace";
                i = i5;
                hKMainActivity = hKMainActivity6;
                str13 = "null cannot be cast to non-null type android.app.Application";
                obj2 = "N/A";
                booleanRef = booleanRef2;
                response = response4;
                coroutineScope = coroutineScope3;
                obj5 = obj8;
                str14 = "getInitialApplication";
                onIO$default = obj;
                value = ((Result) onIO$default).getValue();
                if (Result.m1208isFailureimpl(value)) {
                    value = null;
                }
                aPIData = (HyperKycData.APIData) value;
                if (aPIData != null) {
                    hKMainActivity.handleCustomApiExceptions(apiCall, Boxing.boxInt(i), response.message(), booleanRef.element);
                    obj7 = obj5;
                    str8 = str4;
                    str11 = str13;
                    str9 = str14;
                    str7 = str12;
                } else {
                    WorkflowUIState.ApiCall apiCall6 = apiCall;
                    MainVM.updateApiCallData$hyperkyc_release$default(hKMainActivity.getMainVM(), apiCall6, aPIData, null, false, 12, null);
                    Map responseHeaders = aPIData.getResponseHeaders();
                    String str39 = (responseHeaders == null || (list = (List) responseHeaders.get("x-hv-signature")) == null) ? null : (String) CollectionsKt.first(list);
                    if (apiCall.getValidateSignature()) {
                        obj7 = obj5;
                        if (apiCall.getAllowedStatusCodes().contains(Boxing.boxInt(i))) {
                            GKYCSignatureVerify.GKYCSignatureVerifyBuilder requestHeaders = GKYCSignatureVerify.builder().requestQuery(apiCall.getRequestQueryJSON()).requestBody(apiCall.getRequestBodyJSON()).requestHeaders(JSONExtsKt.toJSONObject(apiCall.getHeaders()));
                            String responseBodyRaw$hyperkyc_release = aPIData.getResponseBodyRaw$hyperkyc_release();
                            if (responseBodyRaw$hyperkyc_release != null) {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    apiCall3 = apiCall6;
                                    try {
                                        m1202constructorimpl3 = Result.m1202constructorimpl(new JSONObject(responseBodyRaw$hyperkyc_release));
                                    } catch (Throwable th2) {
                                        th = th2;
                                        Result.Companion companion5 = Result.INSTANCE;
                                        m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                        m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                                        if (m1205exceptionOrNullimpl2 != null) {
                                        }
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    apiCall3 = apiCall6;
                                }
                                m1205exceptionOrNullimpl2 = Result.m1205exceptionOrNullimpl(m1202constructorimpl3);
                                if (m1205exceptionOrNullimpl2 != null) {
                                    i2 = i;
                                    hKMainActivity2 = hKMainActivity;
                                    apiCall4 = apiCall;
                                    response2 = response;
                                    str23 = str13;
                                    str9 = str14;
                                    str24 = str12;
                                    if (Result.m1205exceptionOrNullimpl(m1202constructorimpl3) != null) {
                                        m1202constructorimpl3 = new JSONObject();
                                    }
                                    jSONObject = (JSONObject) m1202constructorimpl3;
                                } else {
                                    HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                                    HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb2 = new StringBuilder();
                                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                    WorkflowUIState.ApiCall apiCall7 = apiCall;
                                    StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace3, str4);
                                    StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                                    if (stackTraceElement3 == null || (className6 = stackTraceElement3.getClassName()) == null) {
                                        i3 = i;
                                        hKMainActivity3 = hKMainActivity;
                                        response3 = response;
                                    } else {
                                        i3 = i;
                                        hKMainActivity3 = hKMainActivity;
                                        response3 = response;
                                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        r13 = substringAfterLast$default;
                                    }
                                    String canonicalName4 = (coroutineScope == null || (cls6 = coroutineScope.getClass()) == null) ? null : cls6.getCanonicalName();
                                    r13 = canonicalName4 == null ? obj2 : canonicalName4;
                                    objectRef3.element = r13;
                                    Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                                    if (matcher4.find()) {
                                        ?? replaceAll3 = matcher4.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                                        objectRef3.element = replaceAll3;
                                    }
                                    if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str25 = (String) objectRef3.element;
                                    } else {
                                        str25 = ((String) objectRef3.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str25, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb2.append(str25);
                                    sb2.append(" - ");
                                    sb2.append("Unable to convert string to JSONObject");
                                    sb2.append(' ');
                                    String localizedMessage2 = m1205exceptionOrNullimpl2 != null ? m1205exceptionOrNullimpl2.getLocalizedMessage() : null;
                                    if (localizedMessage2 != null) {
                                        str26 = '\n' + localizedMessage2;
                                    } else {
                                        str26 = "";
                                    }
                                    sb2.append(str26);
                                    companion6.log(level2, sb2.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion7 = Result.INSTANCE;
                                        str9 = str14;
                                        try {
                                            Object invoke2 = Class.forName(str10).getMethod(str9, new Class[0]).invoke(null, new Object[0]);
                                            str27 = str13;
                                            try {
                                                Intrinsics.checkNotNull(invoke2, str27);
                                                m1202constructorimpl4 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                            } catch (Throwable th4) {
                                                th = th4;
                                                Result.Companion companion8 = Result.INSTANCE;
                                                m1202constructorimpl4 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                                if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                                }
                                                String str40 = (String) m1202constructorimpl4;
                                                String str41 = str12;
                                                if (CoreExtsKt.isDebug()) {
                                                }
                                                HKMainActivity.handleCustomApiExceptions$default(hKMainActivity3, apiCall7, Boxing.boxInt(i3), response3.message(), false, 8, null);
                                                coroutineScope2 = coroutineScope;
                                                str8 = str4;
                                                str11 = str27;
                                                str7 = str41;
                                                hKMainActivity$startApiFlow$22 = this;
                                                obj4 = obj7;
                                                coroutineScope = coroutineScope2;
                                                HKMainActivity hKMainActivity7 = hKMainActivity$startApiFlow$22.this$0;
                                                WorkflowUIState.ApiCall apiCall8 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
                                                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                                                if (m1205exceptionOrNullimpl == null) {
                                                }
                                                return Result.m1201boximpl(obj6);
                                            }
                                        } catch (Throwable th5) {
                                            th = th5;
                                            str27 = str13;
                                        }
                                    } catch (Throwable th6) {
                                        th = th6;
                                        str27 = str13;
                                        str9 = str14;
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl4)) {
                                        m1202constructorimpl4 = "";
                                    }
                                    String str402 = (String) m1202constructorimpl4;
                                    String str412 = str12;
                                    if (CoreExtsKt.isDebug()) {
                                        Intrinsics.checkNotNullExpressionValue(str402, str412);
                                        if (StringsKt.contains$default((CharSequence) str402, charSequence, false, 2, (Object) null)) {
                                            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                            Intrinsics.checkNotNullExpressionValue(stackTrace4, str4);
                                            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                            if (stackTraceElement4 == null || (className5 = stackTraceElement4.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                canonicalName2 = (coroutineScope == null || (cls5 = coroutineScope.getClass()) == null) ? 0 : cls5.getCanonicalName();
                                                if (canonicalName2 == 0) {
                                                    canonicalName2 = obj2;
                                                }
                                            }
                                            objectRef4.element = canonicalName2;
                                            Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                            if (matcher5.find()) {
                                                ?? replaceAll4 = matcher5.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                                objectRef4.element = replaceAll4;
                                            }
                                            if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                str28 = (String) objectRef4.element;
                                            } else {
                                                str28 = ((String) objectRef4.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str28, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append("Unable to convert string to JSONObject");
                                            sb3.append(' ');
                                            String localizedMessage3 = m1205exceptionOrNullimpl2 != null ? m1205exceptionOrNullimpl2.getLocalizedMessage() : null;
                                            if (localizedMessage3 != null) {
                                                str29 = '\n' + localizedMessage3;
                                            } else {
                                                str29 = "";
                                            }
                                            sb3.append(str29);
                                            Log.println(6, str28, sb3.toString());
                                        }
                                    }
                                    HKMainActivity.handleCustomApiExceptions$default(hKMainActivity3, apiCall7, Boxing.boxInt(i3), response3.message(), false, 8, null);
                                    coroutineScope2 = coroutineScope;
                                    str8 = str4;
                                    str11 = str27;
                                    str7 = str412;
                                    hKMainActivity$startApiFlow$22 = this;
                                    obj4 = obj7;
                                    coroutineScope = coroutineScope2;
                                    HKMainActivity hKMainActivity72 = hKMainActivity$startApiFlow$22.this$0;
                                    WorkflowUIState.ApiCall apiCall82 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
                                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                                    if (m1205exceptionOrNullimpl == null) {
                                        HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                                        HyperLogger companion9 = HyperLogger.INSTANCE.getInstance();
                                        StringBuilder sb4 = new StringBuilder();
                                        obj6 = obj4;
                                        Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace5, str8);
                                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                                        if (stackTraceElement5 == null || (className4 = stackTraceElement5.getClassName()) == null) {
                                            str15 = str7;
                                            apiCall2 = apiCall82;
                                            str16 = str8;
                                        } else {
                                            str15 = str7;
                                            apiCall2 = apiCall82;
                                            str16 = str8;
                                            r4 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        }
                                        r4 = (coroutineScope == null || (cls4 = coroutineScope.getClass()) == null) ? 0 : cls4.getCanonicalName();
                                        if (r4 == 0) {
                                            r4 = obj2;
                                        }
                                        objectRef5.element = r4;
                                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef5.element);
                                        if (matcher6.find()) {
                                            ?? replaceAll5 = matcher6.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll5, "replaceAll(\"\")");
                                            objectRef5.element = replaceAll5;
                                        }
                                        if (((String) objectRef5.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                            str17 = (String) objectRef5.element;
                                        } else {
                                            str17 = ((String) objectRef5.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str17, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        sb4.append(str17);
                                        sb4.append(" - ");
                                        String str42 = "processHVBridgeError: startApiFlow failed " + m1205exceptionOrNullimpl.getMessage();
                                        if (str42 == null) {
                                            str42 = "null ";
                                        }
                                        sb4.append(str42);
                                        sb4.append(' ');
                                        String localizedMessage4 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                                        if (localizedMessage4 != null) {
                                            str18 = '\n' + localizedMessage4;
                                        } else {
                                            str18 = "";
                                        }
                                        sb4.append(str18);
                                        companion9.log(level3, sb4.toString());
                                        CoreExtsKt.isRelease();
                                        try {
                                            Result.Companion companion10 = Result.INSTANCE;
                                            Object invoke3 = Class.forName(str10).getMethod(str9, new Class[0]).invoke(null, new Object[0]);
                                            Intrinsics.checkNotNull(invoke3, str11);
                                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                                        } catch (Throwable th7) {
                                            Result.Companion companion11 = Result.INSTANCE;
                                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th7));
                                        }
                                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                            m1202constructorimpl2 = "";
                                        }
                                        String str43 = (String) m1202constructorimpl2;
                                        if (CoreExtsKt.isDebug()) {
                                            Intrinsics.checkNotNullExpressionValue(str43, str15);
                                            if (StringsKt.contains$default((CharSequence) str43, charSequence, false, 2, (Object) null)) {
                                                Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str16);
                                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                                if (stackTraceElement6 == null || (className3 = stackTraceElement6.getClassName()) == null) {
                                                    str19 = null;
                                                } else {
                                                    str19 = null;
                                                    str20 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                                }
                                                str20 = (coroutineScope == null || (cls3 = coroutineScope.getClass()) == null) ? str19 : cls3.getCanonicalName();
                                                if (str20 == null) {
                                                    r15 = obj2;
                                                    objectRef6.element = r15;
                                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                                    if (matcher.find()) {
                                                        ?? replaceAll6 = matcher.replaceAll("");
                                                        Intrinsics.checkNotNullExpressionValue(replaceAll6, "replaceAll(\"\")");
                                                        objectRef6.element = replaceAll6;
                                                    }
                                                    if (((String) objectRef6.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                                        str21 = (String) objectRef6.element;
                                                    } else {
                                                        str21 = ((String) objectRef6.element).substring(0, 23);
                                                        Intrinsics.checkNotNullExpressionValue(str21, "this as java.lang.String…ing(startIndex, endIndex)");
                                                    }
                                                    StringBuilder sb5 = new StringBuilder();
                                                    str22 = "processHVBridgeError: startApiFlow failed " + m1205exceptionOrNullimpl.getMessage();
                                                    if (str22 == null) {
                                                        str22 = "null ";
                                                    }
                                                    sb5.append(str22);
                                                    sb5.append(' ');
                                                    localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : str19;
                                                    if (localizedMessage != null) {
                                                        str37 = '\n' + localizedMessage;
                                                    }
                                                    sb5.append(str37);
                                                    Log.println(6, str21, sb5.toString());
                                                }
                                                r15 = str20;
                                                objectRef6.element = r15;
                                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef6.element);
                                                if (matcher.find()) {
                                                }
                                                if (((String) objectRef6.element).length() > 23) {
                                                }
                                                str21 = (String) objectRef6.element;
                                                StringBuilder sb52 = new StringBuilder();
                                                str22 = "processHVBridgeError: startApiFlow failed " + m1205exceptionOrNullimpl.getMessage();
                                                if (str22 == null) {
                                                }
                                                sb52.append(str22);
                                                sb52.append(' ');
                                                if (m1205exceptionOrNullimpl == null) {
                                                }
                                                if (localizedMessage != null) {
                                                }
                                                sb52.append(str37);
                                                Log.println(6, str21, sb52.toString());
                                            }
                                        }
                                        if (CoreExtsKt.isNetworkError(m1205exceptionOrNullimpl)) {
                                            hKMainActivity72.handleAPIModuleFailure(apiCall2);
                                        } else {
                                            BaseActivity.finishWithResult$default(hKMainActivity72, "error", null, Boxing.boxInt(104), "API call failed! - " + m1205exceptionOrNullimpl.getMessage(), false, false, 50, null);
                                        }
                                    } else {
                                        obj6 = obj4;
                                    }
                                    return Result.m1201boximpl(obj6);
                                }
                            } else {
                                apiCall3 = apiCall6;
                                i2 = i;
                                hKMainActivity2 = hKMainActivity;
                                apiCall4 = apiCall;
                                response2 = response;
                                str23 = str13;
                                str9 = str14;
                                str24 = str12;
                                jSONObject = null;
                            }
                            GKYCSignatureVerify.GKYCSignatureVerifyBuilder responseBody = requestHeaders.responseBody(jSONObject);
                            Map responseHeaders2 = aPIData.getResponseHeaders();
                            if (responseHeaders2 != null) {
                                try {
                                    Result.Companion companion12 = Result.INSTANCE;
                                    m1202constructorimpl5 = Result.m1202constructorimpl(new JSONObject(responseHeaders2));
                                } catch (Throwable th8) {
                                    Result.Companion companion13 = Result.INSTANCE;
                                    m1202constructorimpl5 = Result.m1202constructorimpl(ResultKt.createFailure(th8));
                                }
                                Throwable m1205exceptionOrNullimpl3 = Result.m1205exceptionOrNullimpl(m1202constructorimpl5);
                                if (m1205exceptionOrNullimpl3 == null) {
                                    String str44 = str24;
                                    str8 = str4;
                                    str11 = str23;
                                    str7 = str44;
                                    if (Result.m1205exceptionOrNullimpl(m1202constructorimpl5) != null) {
                                        m1202constructorimpl5 = new JSONObject();
                                    }
                                    jSONObject2 = (JSONObject) m1202constructorimpl5;
                                } else {
                                    HyperLogger.Level level4 = HyperLogger.Level.ERROR;
                                    HyperLogger companion14 = HyperLogger.INSTANCE.getInstance();
                                    StringBuilder sb6 = new StringBuilder();
                                    Ref.ObjectRef objectRef7 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace7 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace7, str4);
                                    StackTraceElement stackTraceElement7 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace7);
                                    if (stackTraceElement7 == null || (className8 = stackTraceElement7.getClassName()) == null) {
                                        str30 = str4;
                                        str31 = str23;
                                        str32 = str24;
                                    } else {
                                        str30 = str4;
                                        str31 = str23;
                                        str32 = str24;
                                        String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className8, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        r152 = substringAfterLast$default2;
                                    }
                                    String canonicalName5 = (coroutineScope == null || (cls8 = coroutineScope.getClass()) == null) ? null : cls8.getCanonicalName();
                                    r152 = canonicalName5 == null ? obj2 : canonicalName5;
                                    objectRef7.element = r152;
                                    Matcher matcher7 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef7.element);
                                    if (matcher7.find()) {
                                        ?? replaceAll7 = matcher7.replaceAll("");
                                        Intrinsics.checkNotNullExpressionValue(replaceAll7, "replaceAll(\"\")");
                                        objectRef7.element = replaceAll7;
                                    }
                                    if (((String) objectRef7.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                        str33 = (String) objectRef7.element;
                                    } else {
                                        str33 = ((String) objectRef7.element).substring(0, 23);
                                        Intrinsics.checkNotNullExpressionValue(str33, "this as java.lang.String…ing(startIndex, endIndex)");
                                    }
                                    sb6.append(str33);
                                    sb6.append(" - ");
                                    sb6.append("Unable to convert string to JSONObject");
                                    sb6.append(' ');
                                    String localizedMessage5 = m1205exceptionOrNullimpl3 != null ? m1205exceptionOrNullimpl3.getLocalizedMessage() : null;
                                    if (localizedMessage5 != null) {
                                        str34 = '\n' + localizedMessage5;
                                    } else {
                                        str34 = "";
                                    }
                                    sb6.append(str34);
                                    companion14.log(level4, sb6.toString());
                                    CoreExtsKt.isRelease();
                                    try {
                                        Result.Companion companion15 = Result.INSTANCE;
                                        Object invoke4 = Class.forName(str10).getMethod(str9, new Class[0]).invoke(null, new Object[0]);
                                        str11 = str31;
                                        try {
                                            Intrinsics.checkNotNull(invoke4, str11);
                                            m1202constructorimpl6 = Result.m1202constructorimpl(((Application) invoke4).getPackageName());
                                        } catch (Throwable th9) {
                                            th = th9;
                                            Result.Companion companion16 = Result.INSTANCE;
                                            m1202constructorimpl6 = Result.m1202constructorimpl(ResultKt.createFailure(th));
                                            if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                            }
                                            String str45 = (String) m1202constructorimpl6;
                                            if (CoreExtsKt.isDebug()) {
                                            }
                                            HKMainActivity.handleCustomApiExceptions$default(hKMainActivity2, apiCall4, Boxing.boxInt(i2), response2.message(), false, 8, null);
                                            coroutineScope2 = coroutineScope;
                                            hKMainActivity$startApiFlow$22 = this;
                                            obj4 = obj7;
                                            coroutineScope = coroutineScope2;
                                            HKMainActivity hKMainActivity722 = hKMainActivity$startApiFlow$22.this$0;
                                            WorkflowUIState.ApiCall apiCall822 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
                                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                                            if (m1205exceptionOrNullimpl == null) {
                                            }
                                            return Result.m1201boximpl(obj6);
                                        }
                                    } catch (Throwable th10) {
                                        th = th10;
                                        str11 = str31;
                                    }
                                    if (Result.m1208isFailureimpl(m1202constructorimpl6)) {
                                        m1202constructorimpl6 = "";
                                    }
                                    String str452 = (String) m1202constructorimpl6;
                                    if (CoreExtsKt.isDebug()) {
                                        str8 = str30;
                                        str7 = str32;
                                    } else {
                                        str7 = str32;
                                        Intrinsics.checkNotNullExpressionValue(str452, str7);
                                        if (StringsKt.contains$default((CharSequence) str452, charSequence, false, 2, (Object) null)) {
                                            Ref.ObjectRef objectRef8 = new Ref.ObjectRef();
                                            StackTraceElement[] stackTrace8 = new Throwable().getStackTrace();
                                            str8 = str30;
                                            Intrinsics.checkNotNullExpressionValue(stackTrace8, str8);
                                            StackTraceElement stackTraceElement8 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace8);
                                            if (stackTraceElement8 == null || (className7 = stackTraceElement8.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className7, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                                canonicalName3 = (coroutineScope == null || (cls7 = coroutineScope.getClass()) == null) ? 0 : cls7.getCanonicalName();
                                                if (canonicalName3 == 0) {
                                                    canonicalName3 = obj2;
                                                }
                                            }
                                            objectRef8.element = canonicalName3;
                                            Matcher matcher8 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef8.element);
                                            if (matcher8.find()) {
                                                ?? replaceAll8 = matcher8.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(replaceAll8, "replaceAll(\"\")");
                                                objectRef8.element = replaceAll8;
                                            }
                                            if (((String) objectRef8.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                                str35 = (String) objectRef8.element;
                                            } else {
                                                str35 = ((String) objectRef8.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str35, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb7 = new StringBuilder();
                                            sb7.append("Unable to convert string to JSONObject");
                                            sb7.append(' ');
                                            String localizedMessage6 = m1205exceptionOrNullimpl3 != null ? m1205exceptionOrNullimpl3.getLocalizedMessage() : null;
                                            if (localizedMessage6 != null) {
                                                str36 = '\n' + localizedMessage6;
                                            } else {
                                                str36 = "";
                                            }
                                            sb7.append(str36);
                                            Log.println(6, str35, sb7.toString());
                                        } else {
                                            str8 = str30;
                                        }
                                    }
                                    HKMainActivity.handleCustomApiExceptions$default(hKMainActivity2, apiCall4, Boxing.boxInt(i2), response2.message(), false, 8, null);
                                }
                            } else {
                                String str46 = str24;
                                str8 = str4;
                                str11 = str23;
                                str7 = str46;
                                jSONObject2 = null;
                            }
                            z = responseBody.responseHeaders(jSONObject2).tagFileUriMap(apiCall4.getTagFileUriMap()).build().verify(str39);
                            boolean areEqual = Intrinsics.areEqual(hKMainActivity2.getMainVM().asBoolean$hyperkyc_release(apiCall4.getShowEndState(), Boxing.boxBoolean(false)), Boxing.boxBoolean(true));
                            Boolean asBoolean$hyperkyc_release = hKMainActivity2.getMainVM().asBoolean$hyperkyc_release(apiCall4.isSuccess(), null);
                            coroutineScope2 = coroutineScope;
                            boolean isSuccess$default = HyperKycData.APIData.isSuccess$default(aPIData, apiCall4.getAllowedStatusCodes(), false, 2, null);
                            MainVM.updateApiCallData$hyperkyc_release$default(hKMainActivity2.getMainVM(), apiCall3, aPIData, (!isSuccess$default || Intrinsics.areEqual(asBoolean$hyperkyc_release, Boxing.boxBoolean(true))) ? "success" : "failure", false, 8, null);
                            if (z) {
                                invokeSuspend$lambda$11$failWithError(i2, hKMainActivity2, apiCall4, aPIData, 112, AppConstants.SIGNATURE_ERROR);
                            } else if (areEqual) {
                                final WorkflowUIState.ApiCall apiCall9 = apiCall4;
                                final HKMainActivity hKMainActivity8 = hKMainActivity2;
                                final int i6 = i2;
                                hKMainActivity8.updateEndState$hyperkyc_release(false, asBoolean$hyperkyc_release, isSuccess$default, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$startApiFlow$2$3$3
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

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        HKMainActivity$startApiFlow$2.invokeSuspend$lambda$11$failWithError$default(i6, hKMainActivity8, apiCall9, aPIData, 0, null, 48, null);
                                    }
                                });
                            } else {
                                WorkflowUIState.ApiCall apiCall10 = apiCall4;
                                HKMainActivity hKMainActivity9 = hKMainActivity2;
                                int i7 = i2;
                                if (isSuccess$default) {
                                    hKMainActivity9.flowForwardOrFinish();
                                } else {
                                    invokeSuspend$lambda$11$failWithError$default(i7, hKMainActivity9, apiCall10, aPIData, 0, null, 48, null);
                                }
                            }
                            hKMainActivity$startApiFlow$22 = this;
                            obj4 = obj7;
                            coroutineScope = coroutineScope2;
                            HKMainActivity hKMainActivity7222 = hKMainActivity$startApiFlow$22.this$0;
                            WorkflowUIState.ApiCall apiCall8222 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
                            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                            if (m1205exceptionOrNullimpl == null) {
                            }
                            return Result.m1201boximpl(obj6);
                        }
                    } else {
                        obj7 = obj5;
                    }
                    apiCall3 = apiCall6;
                    i2 = i;
                    hKMainActivity2 = hKMainActivity;
                    apiCall4 = apiCall;
                    str9 = str14;
                    str7 = str12;
                    str8 = str4;
                    str11 = str13;
                    z = true;
                    boolean areEqual2 = Intrinsics.areEqual(hKMainActivity2.getMainVM().asBoolean$hyperkyc_release(apiCall4.getShowEndState(), Boxing.boxBoolean(false)), Boxing.boxBoolean(true));
                    Boolean asBoolean$hyperkyc_release2 = hKMainActivity2.getMainVM().asBoolean$hyperkyc_release(apiCall4.isSuccess(), null);
                    coroutineScope2 = coroutineScope;
                    boolean isSuccess$default2 = HyperKycData.APIData.isSuccess$default(aPIData, apiCall4.getAllowedStatusCodes(), false, 2, null);
                    MainVM.updateApiCallData$hyperkyc_release$default(hKMainActivity2.getMainVM(), apiCall3, aPIData, (!isSuccess$default2 || Intrinsics.areEqual(asBoolean$hyperkyc_release2, Boxing.boxBoolean(true))) ? "success" : "failure", false, 8, null);
                    if (z) {
                    }
                    hKMainActivity$startApiFlow$22 = this;
                    obj4 = obj7;
                    coroutineScope = coroutineScope2;
                    HKMainActivity hKMainActivity72222 = hKMainActivity$startApiFlow$22.this$0;
                    WorkflowUIState.ApiCall apiCall82222 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                    if (m1205exceptionOrNullimpl == null) {
                    }
                    return Result.m1201boximpl(obj6);
                }
                coroutineScope2 = coroutineScope;
                hKMainActivity$startApiFlow$22 = this;
                obj4 = obj7;
                coroutineScope = coroutineScope2;
                HKMainActivity hKMainActivity722222 = hKMainActivity$startApiFlow$22.this$0;
                WorkflowUIState.ApiCall apiCall822222 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
                m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
                if (m1205exceptionOrNullimpl == null) {
                }
                return Result.m1201boximpl(obj6);
            }
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            charSequence = "co.hyperverge";
            str4 = "Throwable().stackTrace";
            obj2 = "N/A";
            coroutineScope = coroutineScope4;
            m396customApiCalleH_QyT8$hyperkyc_release$default = ((Result) obj).getValue();
            hKMainActivity$startApiFlow$2 = this;
            str5 = "packageName";
            obj3 = coroutine_suspended;
        }
        hKMainActivity = hKMainActivity$startApiFlow$2.this$0;
        apiCall = hKMainActivity$startApiFlow$2.$apiFlowUIState;
        if (Result.m1209isSuccessimpl(m396customApiCalleH_QyT8$hyperkyc_release$default)) {
            response = (Response) m396customApiCalleH_QyT8$hyperkyc_release$default;
            str12 = str5;
            int code = response.code();
            str13 = "null cannot be cast to non-null type android.app.Application";
            booleanRef = new Ref.BooleanRef();
            str14 = "getInitialApplication";
            str10 = "android.app.AppGlobals";
            HKMainActivity$startApiFlow$2$3$apiData$1 hKMainActivity$startApiFlow$2$3$apiData$1 = new HKMainActivity$startApiFlow$2$3$apiData$1(response, booleanRef, null);
            hKMainActivity$startApiFlow$2.L$0 = coroutineScope;
            hKMainActivity$startApiFlow$2.L$1 = m396customApiCalleH_QyT8$hyperkyc_release$default;
            hKMainActivity$startApiFlow$2.L$2 = hKMainActivity;
            hKMainActivity$startApiFlow$2.L$3 = apiCall;
            hKMainActivity$startApiFlow$2.L$4 = response;
            hKMainActivity$startApiFlow$2.L$5 = booleanRef;
            hKMainActivity$startApiFlow$2.I$0 = code;
            hKMainActivity$startApiFlow$2.label = 2;
            onIO$default = CoroutineExtsKt.onIO$default(null, hKMainActivity$startApiFlow$2$3$apiData$1, hKMainActivity$startApiFlow$2, 1, null);
            if (onIO$default == obj3) {
                return obj3;
            }
            i = code;
            obj5 = m396customApiCalleH_QyT8$hyperkyc_release$default;
            value = ((Result) onIO$default).getValue();
            if (Result.m1208isFailureimpl(value)) {
            }
            aPIData = (HyperKycData.APIData) value;
            if (aPIData != null) {
            }
            coroutineScope2 = coroutineScope;
            hKMainActivity$startApiFlow$22 = this;
            obj4 = obj7;
            coroutineScope = coroutineScope2;
            HKMainActivity hKMainActivity7222222 = hKMainActivity$startApiFlow$22.this$0;
            WorkflowUIState.ApiCall apiCall8222222 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
            if (m1205exceptionOrNullimpl == null) {
            }
            return Result.m1201boximpl(obj6);
        }
        str7 = str5;
        str8 = str4;
        str9 = "getInitialApplication";
        str10 = "android.app.AppGlobals";
        str11 = "null cannot be cast to non-null type android.app.Application";
        hKMainActivity$startApiFlow$22 = this;
        obj4 = m396customApiCalleH_QyT8$hyperkyc_release$default;
        HKMainActivity hKMainActivity72222222 = hKMainActivity$startApiFlow$22.this$0;
        WorkflowUIState.ApiCall apiCall82222222 = hKMainActivity$startApiFlow$22.$apiFlowUIState;
        m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(obj4);
        if (m1205exceptionOrNullimpl == null) {
        }
        return Result.m1201boximpl(obj6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void invokeSuspend$lambda$11$failWithError$default(int i, HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, HyperKycData.APIData aPIData, int i2, String str, int i3, Object obj) {
        int i4 = (i3 & 16) != 0 ? i : i2;
        if ((i3 & 32) != 0) {
            str = "API call failed!";
        }
        invokeSuspend$lambda$11$failWithError(i, hKMainActivity, apiCall, aPIData, i4, str);
    }

    private static final void invokeSuspend$lambda$11$failWithError(int i, HKMainActivity hKMainActivity, WorkflowUIState.ApiCall apiCall, HyperKycData.APIData aPIData, int i2, String str) {
        BaseActivity.finishWithResult$default(hKMainActivity, "error", null, Integer.valueOf(i2), str, false, false, 50, null);
        MainVM.updateApiCallData$hyperkyc_release$default(hKMainActivity.getMainVM(), apiCall, aPIData, "failure", false, 8, null);
    }
}
