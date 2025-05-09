package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import co.hyperverge.hyperkyc.databinding.HkFragmentLoadingBinding;
import co.hyperverge.hyperkyc.ui.models.LoadingUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Matcher;
import kotlin.KotlinNothingValueException;
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
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoadingFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.LoadingFragment$observeLoadingUIStates$2", f = "LoadingFragment.kt", i = {}, l = {98}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class LoadingFragment$observeLoadingUIStates$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ LoadingFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingFragment$observeLoadingUIStates$2(LoadingFragment loadingFragment, Continuation<? super LoadingFragment$observeLoadingUIStates$2> continuation) {
        super(2, continuation);
        this.this$0 = loadingFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LoadingFragment$observeLoadingUIStates$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoadingFragment$observeLoadingUIStates$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LoadingFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.LoadingFragment$observeLoadingUIStates$2$1", f = "LoadingFragment.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.LoadingFragment$observeLoadingUIStates$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ LoadingFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LoadingFragment loadingFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = loadingFragment;
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
            MainVM mainVM;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                mainVM = this.this$0.getMainVM();
                MutableStateFlow<LoadingUIState> loadingUIStateFlow = mainVM.getLoadingUIStateFlow();
                final LoadingFragment loadingFragment = this.this$0;
                this.label = 1;
                if (loadingUIStateFlow.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.LoadingFragment.observeLoadingUIStates.2.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((LoadingUIState) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(LoadingUIState loadingUIState, Continuation<? super Unit> continuation) {
                        String canonicalName;
                        Class<?> cls;
                        Object m1202constructorimpl;
                        Class<?> cls2;
                        String className;
                        String substringAfterLast$default;
                        HkFragmentLoadingBinding binding;
                        String className2;
                        CoroutineScope coroutineScope2 = CoroutineScope.this;
                        HyperLogger.Level level = HyperLogger.Level.DEBUG;
                        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb = new StringBuilder();
                        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                        String str = "N/A";
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
                        String str2 = "observeLoadingUIStates() called with " + loadingUIState;
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
                                        String canonicalName2 = (coroutineScope2 == null || (cls2 = coroutineScope2.getClass()) == null) ? null : cls2.getCanonicalName();
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
                                    String str3 = "observeLoadingUIStates() called with " + loadingUIState;
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
                        binding = loadingFragment.getBinding();
                        Object onUI$default = CoroutineExtsKt.onUI$default(null, new LoadingFragment$observeLoadingUIStates$2$1$1$2$1(loadingUIState, binding, loadingFragment, null), continuation, 1, null);
                        return onUI$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? onUI$default : Unit.INSTANCE;
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
            throw new KotlinNothingValueException();
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
