package co.hyperverge.hyperkyc.ui.viewmodels;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.data.models.state.TransactionState;
import co.hyperverge.hyperkyc.data.models.state.TransactionStateResponse;
import co.hyperverge.hyperkyc.data.network.NetworkRepo;
import co.hyperverge.hyperkyc.ui.models.FinishWithResultEvent;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$pushTransactionState$3", f = "MainVM.kt", i = {}, l = {4013}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MainVM$pushTransactionState$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ Map<String, TransactionState.ModuleData> $tempModuleDataMap;
    final /* synthetic */ TransactionState $transactionState;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$pushTransactionState$3(TransactionState transactionState, Map<String, String> map, MainVM mainVM, Map<String, TransactionState.ModuleData> map2, Continuation<? super MainVM$pushTransactionState$3> continuation) {
        super(2, continuation);
        this.$transactionState = transactionState;
        this.$headers = map;
        this.this$0 = mainVM;
        this.$tempModuleDataMap = map2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainVM$pushTransactionState$3 mainVM$pushTransactionState$3 = new MainVM$pushTransactionState$3(this.$transactionState, this.$headers, this.this$0, this.$tempModuleDataMap, continuation);
        mainVM$pushTransactionState$3.L$0 = obj;
        return mainVM$pushTransactionState$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainVM$pushTransactionState$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Flow m2735catch = FlowKt.m2735catch(NetworkRepo.INSTANCE.pushTransactionState$hyperkyc_release(this.$transactionState, this.$headers), new AnonymousClass1(this.this$0, null));
            final MainVM mainVM = this.this$0;
            final Map<String, TransactionState.ModuleData> map = this.$tempModuleDataMap;
            this.label = 1;
            if (m2735catch.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$pushTransactionState$3.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetworkUIState<TransactionStateResponse>) obj2, (Continuation<? super Unit>) continuation);
                }

                /* JADX WARN: Code restructure failed: missing block: B:100:0x018e, code lost:
                
                    r0.append(r3);
                    r0.append(' ');
                    r0.append("");
                    android.util.Log.println(3, r8, r0.toString());
                 */
                /* JADX WARN: Code restructure failed: missing block: B:102:0x014b, code lost:
                
                    r8 = r0;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:83:0x0136, code lost:
                
                    if (r0 != null) goto L57;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:88:0x0148, code lost:
                
                    if (r0 == null) goto L58;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:89:0x014c, code lost:
                
                    r0 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher(r8);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:90:0x015b, code lost:
                
                    if (r0.find() == false) goto L61;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:91:0x015d, code lost:
                
                    r8 = r0.replaceAll("");
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "replaceAll(\"\")");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:93:0x0168, code lost:
                
                    if (r8.length() <= 23) goto L67;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:95:0x016c, code lost:
                
                    if (android.os.Build.VERSION.SDK_INT < 26) goto L66;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
                
                    r8 = r8.substring(0, 23);
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
                 */
                /* JADX WARN: Code restructure failed: missing block: B:97:0x0176, code lost:
                
                    r0 = new java.lang.StringBuilder();
                    r3 = "pushTransactionState() state: " + r22;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:98:0x018a, code lost:
                
                    if (r3 != null) goto L70;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:99:0x018c, code lost:
                
                    r3 = "null ";
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object emit(NetworkUIState<TransactionStateResponse> networkUIState, Continuation<? super Unit> continuation) {
                    String canonicalName;
                    Class<?> cls;
                    Object m1202constructorimpl;
                    String str;
                    String str2;
                    Class<?> cls2;
                    String className;
                    MutableStateFlow mutableStateFlow;
                    HashMap hashMap;
                    HashMap hashMap2;
                    String str3;
                    MutableStateFlow mutableStateFlow2;
                    Context context;
                    String className2;
                    CoroutineScope coroutineScope2 = CoroutineScope.this;
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    String str4 = "N/A";
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
                    String str5 = "pushTransactionState() state: " + networkUIState;
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
                                str2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? str : cls2.getCanonicalName();
                            }
                        }
                    }
                    str = null;
                    if (networkUIState instanceof NetworkUIState.Success) {
                        mainVM.setPushingState$hyperkyc_release(false);
                        NetworkUIState.Success success = (NetworkUIState.Success) networkUIState;
                        int statusCode = ((TransactionStateResponse) success.getData()).getStatusCode();
                        if (statusCode == 200) {
                            Set<String> keySet = map.keySet();
                            MainVM mainVM2 = mainVM;
                            for (String str6 : keySet) {
                                hashMap = mainVM2.moduleDataMap;
                                hashMap.remove(str6);
                                hashMap2 = mainVM2.moduleDataMap;
                                LinkedHashMap linkedHashMap = new LinkedHashMap();
                                for (Map.Entry entry : hashMap2.entrySet()) {
                                    if (!Intrinsics.areEqual(((TransactionState.ModuleData) entry.getValue()).getParentModuleId(), str6)) {
                                        linkedHashMap.put(entry.getKey(), entry.getValue());
                                    }
                                }
                                mainVM2.moduleDataMap = new HashMap(MapsKt.toMutableMap(linkedHashMap));
                            }
                        } else if (statusCode == 400 || statusCode == 404 || statusCode == 409) {
                            int statusCode2 = ((TransactionStateResponse) success.getData()).getStatusCode();
                            int i2 = (statusCode2 == 400 || statusCode2 == 409) ? 101 : 104;
                            String errorCode = ((TransactionStateResponse) success.getData()).getErrorCode();
                            if (errorCode != null) {
                                str3 = errorCode;
                            } else if (((TransactionStateResponse) success.getData()).getStatusCode() == 409) {
                                context = mainVM.getContext();
                                str3 = context.getString(R.string.hk_invalid_uniqueId_error);
                            } else {
                                str3 = str;
                            }
                            mutableStateFlow2 = mainVM.finishWorkflowEventFlow;
                            mutableStateFlow2.tryEmit(new FinishWithResultEvent("error", null, Boxing.boxInt(i2), str3, true, false, 2, null));
                        }
                    } else if (networkUIState instanceof NetworkUIState.Failed) {
                        mainVM.setPushingState$hyperkyc_release(false);
                        mutableStateFlow = mainVM.finishWorkflowEventFlow;
                        mutableStateFlow.tryEmit(new FinishWithResultEvent("error", null, Boxing.boxInt(111), ((NetworkUIState.Failed) networkUIState).getReason(), true, false, 2, null));
                    } else if (networkUIState instanceof NetworkUIState.NetworkFailure) {
                        mainVM.setPushingState$hyperkyc_release(false);
                    } else {
                        boolean z = networkUIState instanceof NetworkUIState.Loading;
                    }
                    mainVM.getSaveStateUIStateFlow$hyperkyc_release().tryEmit(networkUIState);
                    return Unit.INSTANCE;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MainVM.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "Lco/hyperverge/hyperkyc/ui/custom/SaveStateUIState;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$pushTransactionState$3$1", f = "MainVM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$pushTransactionState$3$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;
        final /* synthetic */ MainVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MainVM mainVM, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.this$0 = mainVM;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetworkUIState<? extends TransactionStateResponse>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetworkUIState<TransactionStateResponse>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetworkUIState<TransactionStateResponse>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = flowCollector;
            anonymousClass1.L$1 = th;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0191  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x01cc  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01d1  */
        /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r10v1 */
        /* JADX WARN: Type inference failed for: r10v2, types: [T] */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r2v15, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r8v10, types: [T] */
        /* JADX WARN: Type inference failed for: r8v27, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r8v29, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r8v30 */
        /* JADX WARN: Type inference failed for: r8v5 */
        /* JADX WARN: Type inference failed for: r8v6 */
        /* JADX WARN: Type inference failed for: r8v7 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ?? canonicalName;
            Class<?> cls;
            String str;
            String str2;
            Object m1202constructorimpl;
            String str3;
            Class<?> cls2;
            Matcher matcher;
            String str4;
            String localizedMessage;
            String className;
            String className2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Throwable th = (Throwable) this.L$1;
            HyperLogger.Level level = HyperLogger.Level.ERROR;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r10 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (flowCollector == null || (cls = flowCollector.getClass()) == null) ? 0 : cls.getCanonicalName();
                if (canonicalName == 0) {
                    canonicalName = "N/A";
                }
            }
            objectRef.element = canonicalName;
            Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
            String str5 = "";
            if (matcher2.find()) {
                ?? replaceAll = matcher2.replaceAll("");
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
            sb.append("pushTransactionState failed");
            sb.append(' ');
            String localizedMessage2 = th != null ? th.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str2 = '\n' + localizedMessage2;
            } else {
                str2 = "";
            }
            sb.append(str2);
            companion.log(level, sb.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th2));
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
                        str3 = null;
                    } else {
                        str3 = null;
                        String substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        if (substringAfterLast$default != null) {
                            r10 = substringAfterLast$default;
                            objectRef2.element = r10;
                            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                            if (matcher.find()) {
                                ?? replaceAll2 = matcher.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                objectRef2.element = replaceAll2;
                            }
                            if (((String) objectRef2.element).length() > 23 || Build.VERSION.SDK_INT >= 26) {
                                str4 = (String) objectRef2.element;
                            } else {
                                str4 = ((String) objectRef2.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("pushTransactionState failed");
                            sb2.append(' ');
                            localizedMessage = th == null ? th.getLocalizedMessage() : str3;
                            if (localizedMessage != null) {
                                str5 = '\n' + localizedMessage;
                            }
                            sb2.append(str5);
                            Log.println(6, str4, sb2.toString());
                        }
                    }
                    String canonicalName2 = (flowCollector == null || (cls2 = flowCollector.getClass()) == null) ? str3 : cls2.getCanonicalName();
                    if (canonicalName2 != null) {
                        r10 = canonicalName2;
                    }
                    objectRef2.element = r10;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                    if (matcher.find()) {
                    }
                    if (((String) objectRef2.element).length() > 23) {
                    }
                    str4 = (String) objectRef2.element;
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append("pushTransactionState failed");
                    sb22.append(' ');
                    if (th == null) {
                    }
                    if (localizedMessage != null) {
                    }
                    sb22.append(str5);
                    Log.println(6, str4, sb22.toString());
                }
            }
            this.this$0.setPushingState$hyperkyc_release(false);
            return Unit.INSTANCE;
        }
    }
}
