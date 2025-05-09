package co.hyperverge.hyperkyc.ui.nfc;

import android.app.Application;
import android.nfc.NfcManager;
import android.os.Build;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import co.hyperverge.hvnfcsdk.adapters.HVNFCAdapter;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.nfc.NFCUIFlowModel;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NFCReaderFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$observeUIState$2", f = "NFCReaderFragment.kt", i = {}, l = {645}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class NFCReaderFragment$observeUIState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ NFCReaderFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NFCReaderFragment$observeUIState$2(NFCReaderFragment nFCReaderFragment, Continuation<? super NFCReaderFragment$observeUIState$2> continuation) {
        super(2, continuation);
        this.this$0 = nFCReaderFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NFCReaderFragment$observeUIState$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NFCReaderFragment$observeUIState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NFCReaderFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$observeUIState$2$1", f = "NFCReaderFragment.kt", i = {}, l = {647}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment$observeUIState$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ NFCReaderFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(NFCReaderFragment nFCReaderFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = nFCReaderFragment;
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
            NFCReaderVM nFCReaderVM;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                CoroutineScopeKt.ensureActive(coroutineScope);
                nFCReaderVM = this.this$0.nfcVM;
                if (nFCReaderVM == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
                    nFCReaderVM = null;
                }
                Flow filterNotNull = FlowKt.filterNotNull(nFCReaderVM.getCurrentUIModel());
                final NFCReaderFragment nFCReaderFragment = this.this$0;
                this.label = 1;
                if (filterNotNull.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.nfc.NFCReaderFragment.observeUIState.2.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((NFCUIFlowModel) obj2, (Continuation<? super Unit>) continuation);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:100:0x017f, code lost:
                    
                        r0 = new java.lang.StringBuilder();
                        r3 = "observeUiState() collect called with " + r18.getClass().getSimpleName();
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:101:0x019b, code lost:
                    
                        if (r3 != null) goto L70;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:102:0x019e, code lost:
                    
                        r9 = r3;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:103:0x019f, code lost:
                    
                        r0.append(r9);
                        r0.append(' ');
                        r0.append("");
                        android.util.Log.println(4, r8, r0.toString());
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:105:0x0152, code lost:
                    
                        r8 = r0;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:86:0x013d, code lost:
                    
                        if (r0 != null) goto L57;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:91:0x014f, code lost:
                    
                        if (r0 == null) goto L58;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:92:0x0153, code lost:
                    
                        r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:93:0x0162, code lost:
                    
                        if (r0.find() == false) goto L61;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:94:0x0164, code lost:
                    
                        r8 = r0.replaceAll("");
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
                    
                        if (r8.length() <= 23) goto L67;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:98:0x0175, code lost:
                    
                        if (android.os.Build.VERSION.SDK_INT < 26) goto L66;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:99:0x0178, code lost:
                    
                        r8 = r8.substring(0, 23);
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
                     */
                    /* JADX WARN: Multi-variable type inference failed */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object emit(NFCUIFlowModel nFCUIFlowModel, Continuation<? super Unit> continuation) {
                        String canonicalName;
                        Class<?> cls;
                        Object m1202constructorimpl;
                        String str;
                        String str2;
                        Class<?> cls2;
                        String className;
                        List<NFCUIStateView> list;
                        NFCReaderVM nFCReaderVM2;
                        NFCReaderVM nFCReaderVM3;
                        NfcManager nfcManager;
                        HVNFCAdapter hVNFCAdapter;
                        NFCReaderVM nFCReaderVM4;
                        NFCReaderVM nFCReaderVM5;
                        Timer timer;
                        String className2;
                        CoroutineScope coroutineScope2 = CoroutineScope.this;
                        HyperLogger.Level level = HyperLogger.Level.DEBUG;
                        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb = new StringBuilder();
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        String str3 = "N/A";
                        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName = (coroutineScope2 == null || (cls = coroutineScope2.getClass()) == null) ? null : cls.getCanonicalName();
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
                        String str4 = "observeUiState() collect called with " + nFCUIFlowModel.getClass().getSimpleName();
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
                                    str2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? str : cls2.getCanonicalName();
                                }
                            }
                        }
                        str = null;
                        list = nFCReaderFragment.uiStatesView;
                        for (NFCUIStateView nFCUIStateView : list) {
                            Object tag = nFCUIStateView.getTag();
                            NFCUIFlowModel.NFCUIState uiState = nFCUIFlowModel.getUiState();
                            if (Intrinsics.areEqual(tag, uiState != null ? uiState.getNfcStepId() : str)) {
                                NFCUIFlowModel.NFCUIState uiState2 = nFCUIFlowModel.getUiState();
                                if (Intrinsics.areEqual(uiState2, NFCUIFlowModel.NFCUIState.TurnOnNFC.INSTANCE)) {
                                    nfcManager = nFCReaderFragment.nfcMgr;
                                    NfcManager nfcManager2 = nfcManager;
                                    if (nfcManager == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("nfcMgr");
                                        nfcManager2 = str;
                                    }
                                    if (nfcManager2.getDefaultAdapter() != null) {
                                        hVNFCAdapter = nFCReaderFragment.nfcAdapter;
                                        HVNFCAdapter hVNFCAdapter2 = hVNFCAdapter;
                                        if (hVNFCAdapter == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("nfcAdapter");
                                            hVNFCAdapter2 = str;
                                        }
                                        if (hVNFCAdapter2.isEnabled()) {
                                            nFCUIFlowModel.setStatus(NFCUIFlowModel.NFCUIStatus.Success);
                                            nFCUIStateView.setUIModel(nFCUIFlowModel);
                                            nFCReaderVM4 = nFCReaderFragment.nfcVM;
                                            if (nFCReaderVM4 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
                                                nFCReaderVM5 = str;
                                            } else {
                                                nFCReaderVM5 = nFCReaderVM4;
                                            }
                                            nFCReaderVM5.updateStatus(NFCUIFlowModel.NFCUIState.TapChipCard.INSTANCE, NFCUIFlowModel.NFCUIStatus.Processing);
                                            timer = nFCReaderFragment.timer;
                                            if (timer == null) {
                                                nFCReaderFragment.startSkipButtonTimer();
                                            }
                                        } else {
                                            nFCUIStateView.setUIModel(nFCUIFlowModel);
                                        }
                                    } else {
                                        nFCUIFlowModel.setStatus(NFCUIFlowModel.NFCUIStatus.Failure);
                                        nFCUIStateView.setUIModel(nFCUIFlowModel);
                                        nFCReaderFragment.errorCode = 120;
                                        NFCReaderFragment nFCReaderFragment2 = nFCReaderFragment;
                                        nFCReaderFragment2.errorMessage = nFCReaderFragment2.getString(R.string.hk_error_unavailable);
                                        nFCReaderFragment.processErrorState();
                                    }
                                } else if (Intrinsics.areEqual(uiState2, NFCUIFlowModel.NFCUIState.TapChipCard.INSTANCE)) {
                                    nFCUIStateView.setUIModel(nFCUIFlowModel);
                                    if (nFCUIFlowModel.getStatus() == NFCUIFlowModel.NFCUIStatus.Success) {
                                        nFCReaderVM2 = nFCReaderFragment.nfcVM;
                                        if (nFCReaderVM2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("nfcVM");
                                            nFCReaderVM3 = str;
                                        } else {
                                            nFCReaderVM3 = nFCReaderVM2;
                                        }
                                        nFCReaderVM3.updateStatus(NFCUIFlowModel.NFCUIState.ConnectChip.INSTANCE, NFCUIFlowModel.NFCUIStatus.Processing);
                                    }
                                } else if (Intrinsics.areEqual(uiState2, NFCUIFlowModel.NFCUIState.ConnectChip.INSTANCE)) {
                                    nFCUIStateView.setUIModel(nFCUIFlowModel);
                                } else {
                                    nFCUIStateView.setUIModel(nFCUIFlowModel);
                                }
                                return Unit.INSTANCE;
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                }, this) == coroutine_suspended) {
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
}
