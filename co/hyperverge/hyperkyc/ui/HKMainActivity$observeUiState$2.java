package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.KycDocument;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.ui.form.FormFragmentV2;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.utils.FormWebViewDriver;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.Map;
import java.util.regex.Matcher;
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
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$observeUiState$2", f = "HKMainActivity.kt", i = {}, l = {1125}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$observeUiState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HKMainActivity$observeUiState$2(HKMainActivity hKMainActivity, Continuation<? super HKMainActivity$observeUiState$2> continuation) {
        super(2, continuation);
        this.this$0 = hKMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HKMainActivity$observeUiState$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$observeUiState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HKMainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$observeUiState$2$1", f = "HKMainActivity.kt", i = {}, l = {1129}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$observeUiState$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ HKMainActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HKMainActivity hKMainActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = hKMainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                CoroutineScopeKt.ensureActive(coroutineScope);
                this.label = 1;
                if (FlowKt.filterNotNull(this.this$0.getMainVM().getUiStateFlow()).collect(new C00111(coroutineScope, this.this$0), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: HKMainActivity.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;", "emit", "(Lco/hyperverge/hyperkyc/ui/models/WorkflowUIState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: co.hyperverge.hyperkyc.ui.HKMainActivity$observeUiState$2$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C00111<T> implements FlowCollector {
            final /* synthetic */ CoroutineScope $$this$repeatOnLifecycle;
            final /* synthetic */ HKMainActivity this$0;

            C00111(CoroutineScope coroutineScope, HKMainActivity hKMainActivity) {
                this.$$this$repeatOnLifecycle = coroutineScope;
                this.this$0 = hKMainActivity;
            }

            /* JADX WARN: Removed duplicated region for block: B:151:0x01eb  */
            /* JADX WARN: Removed duplicated region for block: B:159:0x0236  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(WorkflowUIState workflowUIState, Continuation<? super Unit> continuation) {
                HKMainActivity$observeUiState$2$1$1$emit$1 hKMainActivity$observeUiState$2$1$1$emit$1;
                int i;
                T t;
                Class<?> cls;
                String str;
                Object m1202constructorimpl;
                T t2;
                T t3;
                Class<?> cls2;
                Matcher matcher;
                String str2;
                String className;
                Object m411startBarcodeFlowgIAlus;
                boolean z;
                Map emptyMap;
                Object m413startFaceFlowgIAlus;
                Object m412startDocFlowgIAlus;
                Map emptyMap2;
                Map emptyMap3;
                String className2;
                if (continuation instanceof HKMainActivity$observeUiState$2$1$1$emit$1) {
                    hKMainActivity$observeUiState$2$1$1$emit$1 = (HKMainActivity$observeUiState$2$1$1$emit$1) continuation;
                    if ((hKMainActivity$observeUiState$2$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        hKMainActivity$observeUiState$2$1$1$emit$1.label -= Integer.MIN_VALUE;
                        HKMainActivity$observeUiState$2$1$1$emit$1 hKMainActivity$observeUiState$2$1$1$emit$12 = hKMainActivity$observeUiState$2$1$1$emit$1;
                        Object obj = hKMainActivity$observeUiState$2$1$1$emit$12.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        i = hKMainActivity$observeUiState$2$1$1$emit$12.label;
                        if (i == 0) {
                            if (i == 1) {
                                ResultKt.throwOnFailure(obj);
                                ((Result) obj).getValue();
                                return Unit.INSTANCE;
                            }
                            if (i == 2) {
                                ResultKt.throwOnFailure(obj);
                                ((Result) obj).getValue();
                                return Unit.INSTANCE;
                            }
                            if (i == 3) {
                                ResultKt.throwOnFailure(obj);
                                ((Result) obj).getValue();
                                return Unit.INSTANCE;
                            }
                            if (i != 4) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            ((Result) obj).getValue();
                            return Unit.INSTANCE;
                        }
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.$$this$repeatOnLifecycle;
                        HyperLogger.Level level = HyperLogger.Level.DEBUG;
                        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb = new StringBuilder();
                        Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (t = (T) StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            t = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? null : (T) cls.getCanonicalName();
                            if (t == null) {
                                t = (T) "N/A";
                            }
                        }
                        objectRef.element = t;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                        if (matcher2.find()) {
                            T t4 = (T) matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(t4, "replaceAll(\"\")");
                            objectRef.element = t4;
                        }
                        if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str = (String) objectRef.element;
                        } else {
                            str = ((String) objectRef.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb.append(str);
                        sb.append(" - ");
                        String str3 = "observeUiState() collect called with " + workflowUIState.getClass().getSimpleName();
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
                                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                                        t2 = null;
                                    } else {
                                        t2 = null;
                                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                        if (substringAfterLast$default != null) {
                                            t3 = (T) substringAfterLast$default;
                                            objectRef2.element = t3;
                                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                            if (matcher.find()) {
                                                T t5 = (T) matcher.replaceAll("");
                                                Intrinsics.checkNotNullExpressionValue(t5, "replaceAll(\"\")");
                                                objectRef2.element = t5;
                                            }
                                            if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                                str2 = (String) objectRef2.element;
                                            } else {
                                                str2 = ((String) objectRef2.element).substring(0, 23);
                                                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                                            }
                                            StringBuilder sb2 = new StringBuilder();
                                            String str4 = "observeUiState() collect called with " + workflowUIState.getClass().getSimpleName();
                                            sb2.append(str4 != null ? str4 : "null ");
                                            sb2.append(' ');
                                            sb2.append("");
                                            Log.println(4, str2, sb2.toString());
                                        }
                                    }
                                    t3 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? t2 : (T) cls2.getCanonicalName();
                                    if (t3 == null) {
                                        t3 = (T) "N/A";
                                    }
                                    objectRef2.element = t3;
                                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                    if (matcher.find()) {
                                    }
                                    if (((String) objectRef2.element).length() > 23) {
                                    }
                                    str2 = (String) objectRef2.element;
                                    StringBuilder sb22 = new StringBuilder();
                                    String str42 = "observeUiState() collect called with " + workflowUIState.getClass().getSimpleName();
                                    sb22.append(str42 != null ? str42 : "null ");
                                    sb22.append(' ');
                                    sb22.append("");
                                    Log.println(4, str2, sb22.toString());
                                }
                            }
                        }
                        boolean z2 = workflowUIState instanceof WorkflowUIState.DocCapture;
                        if (!(z2 ? true : workflowUIState instanceof WorkflowUIState.FaceCapture ? true : workflowUIState instanceof WorkflowUIState.BarcodeCapture)) {
                            if (workflowUIState instanceof WorkflowUIState.WebView) {
                                if (!((WorkflowUIState.WebView) workflowUIState).getOpenInAppBrowser()) {
                                    this.this$0.getMainVM().getHsStateHandler().setActivityRecreated(false);
                                }
                            } else {
                                this.this$0.getMainVM().getHsStateHandler().setActivityRecreated(false);
                            }
                        }
                        this.this$0.getWindow().setSoftInputMode(32);
                        if (workflowUIState instanceof WorkflowUIState.PickCountry) {
                            HKMainActivity hKMainActivity = this.this$0;
                            CountryPickerFragment countryPickerFragment = new CountryPickerFragment();
                            Pair[] pairArr = new Pair[3];
                            WorkflowUIState.PickCountry pickCountry = (WorkflowUIState.PickCountry) workflowUIState;
                            pairArr[0] = TuplesKt.to("countries", pickCountry.getCountries().toArray(new KycCountry[0]));
                            Map<String, Object> textConfigs = pickCountry.getTextConfigs();
                            if (textConfigs == null || (emptyMap3 = MapsKt.toMap(textConfigs)) == null) {
                                emptyMap3 = MapsKt.emptyMap();
                            }
                            pairArr[1] = TuplesKt.to("textConfigs", emptyMap3);
                            pairArr[2] = TuplesKt.to("showBackButton", Boxing.boxBoolean(pickCountry.getShowBackButton()));
                            ActivityExtsKt.replaceContent$default(hKMainActivity, countryPickerFragment, BundleKt.bundleOf(pairArr), false, null, 0, 28, null);
                        } else if (workflowUIState instanceof WorkflowUIState.PickDocument) {
                            HKMainActivity hKMainActivity2 = this.this$0;
                            DocumentPickerFragment documentPickerFragment = new DocumentPickerFragment();
                            Pair[] pairArr2 = new Pair[3];
                            WorkflowUIState.PickDocument pickDocument = (WorkflowUIState.PickDocument) workflowUIState;
                            pairArr2[0] = TuplesKt.to("documents", pickDocument.getDocuments().toArray(new KycDocument[0]));
                            Map<String, Object> textConfigs2 = pickDocument.getTextConfigs();
                            if (textConfigs2 == null || (emptyMap2 = MapsKt.toMap(textConfigs2)) == null) {
                                emptyMap2 = MapsKt.emptyMap();
                            }
                            pairArr2[1] = TuplesKt.to("textConfigs", emptyMap2);
                            pairArr2[2] = TuplesKt.to("showBackButton", Boxing.boxBoolean(pickDocument.getShowBackButton()));
                            ActivityExtsKt.replaceContent$default(hKMainActivity2, documentPickerFragment, BundleKt.bundleOf(pairArr2), false, null, 0, 28, null);
                        } else if (workflowUIState instanceof WorkflowUIState.VideoStatementV2) {
                            this.this$0.startVideoStatementV2Flow((WorkflowUIState.VideoStatementV2) workflowUIState);
                        } else if (workflowUIState instanceof WorkflowUIState.VideoStatement) {
                            this.this$0.startVideoStatementFlow((WorkflowUIState.VideoStatement) workflowUIState);
                        } else if (workflowUIState instanceof WorkflowUIState.StartSessionRecording) {
                            this.this$0.startSessionFlow((WorkflowUIState.StartSessionRecording) workflowUIState);
                        } else if (workflowUIState instanceof WorkflowUIState.StopSessionRecording) {
                            this.this$0.stopSessionFlow((WorkflowUIState.StopSessionRecording) workflowUIState);
                        } else {
                            if (z2) {
                                hKMainActivity$observeUiState$2$1$1$emit$12.label = 1;
                                m412startDocFlowgIAlus = this.this$0.m412startDocFlowgIAlus((WorkflowUIState.DocCapture) workflowUIState, hKMainActivity$observeUiState$2$1$1$emit$12);
                                if (m412startDocFlowgIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                            if (workflowUIState instanceof WorkflowUIState.FaceCapture) {
                                hKMainActivity$observeUiState$2$1$1$emit$12.label = 2;
                                m413startFaceFlowgIAlus = this.this$0.m413startFaceFlowgIAlus((WorkflowUIState.FaceCapture) workflowUIState, hKMainActivity$observeUiState$2$1$1$emit$12);
                                if (m413startFaceFlowgIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                            if (workflowUIState instanceof WorkflowUIState.ApiCall) {
                                hKMainActivity$observeUiState$2$1$1$emit$12.label = 3;
                                if (HKMainActivity.m410startApiFlow0E7RQCE$default(this.this$0, (WorkflowUIState.ApiCall) workflowUIState, false, hKMainActivity$observeUiState$2$1$1$emit$12, 2, null) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                return Unit.INSTANCE;
                            }
                            if (workflowUIState instanceof WorkflowUIState.NFCReader) {
                                this.this$0.startNFCFlow((WorkflowUIState.NFCReader) workflowUIState);
                            } else if (workflowUIState instanceof WorkflowUIState.Form) {
                                z = this.this$0.useWebForm;
                                if (z) {
                                    WorkflowUIState.Form form = (WorkflowUIState.Form) workflowUIState;
                                    if (form.getUseWebForm()) {
                                        this.this$0.getWindow().setSoftInputMode(16);
                                        FormWebViewDriver formWebViewDriver$hyperkyc_release = this.this$0.getFormWebViewDriver$hyperkyc_release();
                                        boolean showBackButton = form.getShowBackButton();
                                        final HKMainActivity hKMainActivity3 = this.this$0;
                                        formWebViewDriver$hyperkyc_release.setAndLaunchWebSDKForm$hyperkyc_release(showBackButton, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity.observeUiState.2.1.1.2
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
                                                ActivityExtsKt.replaceContent$default(HKMainActivity.this, new FormFragmentV2(), null, false, null, 0, 30, null);
                                            }
                                        });
                                    }
                                }
                                HKMainActivity hKMainActivity4 = this.this$0;
                                FormFragment formFragment = new FormFragment();
                                Pair[] pairArr3 = new Pair[6];
                                pairArr3[0] = TuplesKt.to("moduleId", workflowUIState.getTag());
                                WorkflowUIState.Form form2 = (WorkflowUIState.Form) workflowUIState;
                                pairArr3[1] = TuplesKt.to("type", form2.getType());
                                pairArr3[2] = TuplesKt.to(FormFragment.ARG_SUBTYPE, workflowUIState.getSubType());
                                pairArr3[3] = TuplesKt.to(FormFragment.ARG_SECTIONS, form2.getSections());
                                Map<String, Object> textConfigs3 = form2.getTextConfigs();
                                if (textConfigs3 == null || (emptyMap = MapsKt.toMap(textConfigs3)) == null) {
                                    emptyMap = MapsKt.emptyMap();
                                }
                                pairArr3[4] = TuplesKt.to("textConfigs", emptyMap);
                                pairArr3[5] = TuplesKt.to("showBackButton", Boxing.boxBoolean(form2.getShowBackButton()));
                                ActivityExtsKt.replaceContent$default(hKMainActivity4, formFragment, BundleKt.bundleOf(pairArr3), false, null, 0, 28, null);
                            } else if (workflowUIState instanceof WorkflowUIState.WebView) {
                                this.this$0.startWebViewFlow((WorkflowUIState.WebView) workflowUIState);
                            } else {
                                if (workflowUIState instanceof WorkflowUIState.BarcodeCapture) {
                                    hKMainActivity$observeUiState$2$1$1$emit$12.label = 4;
                                    m411startBarcodeFlowgIAlus = this.this$0.m411startBarcodeFlowgIAlus((WorkflowUIState.BarcodeCapture) workflowUIState, hKMainActivity$observeUiState$2$1$1$emit$12);
                                    if (m411startBarcodeFlowgIAlus == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    return Unit.INSTANCE;
                                }
                                if (workflowUIState instanceof WorkflowUIState.End) {
                                    BaseActivity.finishWithResult$default(this.this$0, workflowUIState.getTag(), null, null, null, false, false, 62, null);
                                } else {
                                    throw new IllegalStateException(("Invalid workflow state: " + workflowUIState).toString());
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }
                hKMainActivity$observeUiState$2$1$1$emit$1 = new HKMainActivity$observeUiState$2$1$1$emit$1(this, continuation);
                HKMainActivity$observeUiState$2$1$1$emit$1 hKMainActivity$observeUiState$2$1$1$emit$122 = hKMainActivity$observeUiState$2$1$1$emit$1;
                Object obj2 = hKMainActivity$observeUiState$2$1$1$emit$122.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = hKMainActivity$observeUiState$2$1$1$emit$122.label;
                if (i == 0) {
                }
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((WorkflowUIState) obj, (Continuation<? super Unit>) continuation);
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(this.this$0, Lifecycle.State.CREATED, new AnonymousClass1(this.this$0, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
