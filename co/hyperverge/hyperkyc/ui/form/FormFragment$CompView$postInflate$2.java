package co.hyperverge.hyperkyc.ui.form;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
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
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0005*\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "ViewType", "Landroid/view/View;", "ValueType", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$CompView$postInflate$2", f = "FormFragment.kt", i = {}, l = {TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FormFragment$CompView$postInflate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FormFragment.CompView<ViewType, ValueType> this$0;
    final /* synthetic */ FormFragment this$1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$CompView$postInflate$2(FormFragment.CompView<ViewType, ValueType> compView, FormFragment formFragment, Continuation<? super FormFragment$CompView$postInflate$2> continuation) {
        super(2, continuation);
        this.this$0 = compView;
        this.this$1 = formFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FormFragment$CompView$postInflate$2 formFragment$CompView$postInflate$2 = new FormFragment$CompView$postInflate$2(this.this$0, this.this$1, continuation);
        formFragment$CompView$postInflate$2.L$0 = obj;
        return formFragment$CompView$postInflate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$CompView$postInflate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            mutableStateFlow = ((FormFragment.CompView) this.this$0).onChangeFlow;
            this.label = 1;
            if (FlowKt.collectLatest(FlowKt.filterNotNull(invokeSuspend$debounceInjectable(mutableStateFlow, this.this$1, this.this$0.getComponent().getOnChange())), new AnonymousClass1(coroutineScope, this.this$0, null), this) == coroutine_suspended) {
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

    private static final <T> Flow<T> invokeSuspend$debounceInjectable(MutableStateFlow<T> mutableStateFlow, FormFragment formFragment, WorkflowModule.Properties.Section.Component.Handler handler) {
        String debounce;
        Long longOrNull;
        if (handler != null && (debounce = handler.getDebounce()) != null) {
            String stringInjectFromVariables$hyperkyc_release$default = FormFragment.stringInjectFromVariables$hyperkyc_release$default(formFragment, debounce, false, 1, null);
            if (stringInjectFromVariables$hyperkyc_release$default != null && (longOrNull = StringsKt.toLongOrNull(stringInjectFromVariables$hyperkyc_release$default)) != null) {
                Long l = longOrNull.longValue() > 0 ? longOrNull : null;
                if (l != null) {
                    return FlowKt.debounce(mutableStateFlow, l.longValue());
                }
            }
        }
        return mutableStateFlow;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [ValueType] */
    /* compiled from: FormFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0004H\u008a@"}, d2 = {"<anonymous>", "", "ViewType", "Landroid/view/View;", "ValueType", "", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$CompView$postInflate$2$1", f = "FormFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.ui.form.FormFragment$CompView$postInflate$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1<ValueType> extends SuspendLambda implements Function2<ValueType, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineScope $$this$launch;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ FormFragment.CompView<ViewType, ValueType> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CoroutineScope coroutineScope, FormFragment.CompView<ViewType, ValueType> compView, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$launch = coroutineScope;
            this.this$0 = compView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$launch, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return invoke2((AnonymousClass1<ValueType>) obj, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(ValueType valuetype, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(valuetype, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r11v1 */
        /* JADX WARN: Type inference failed for: r11v2, types: [T] */
        /* JADX WARN: Type inference failed for: r11v3 */
        /* JADX WARN: Type inference failed for: r3v11, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r9v10, types: [T] */
        /* JADX WARN: Type inference failed for: r9v20, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r9v22, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r9v23 */
        /* JADX WARN: Type inference failed for: r9v5 */
        /* JADX WARN: Type inference failed for: r9v6 */
        /* JADX WARN: Type inference failed for: r9v7 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ?? canonicalName;
            Class<?> cls;
            String str;
            Object m1202constructorimpl;
            Class<?> cls2;
            String str2;
            String className;
            String substringAfterLast$default;
            String className2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            CoroutineScope coroutineScope = this.$$this$launch;
            FormFragment.CompView compView = this.this$0;
            HyperLogger.Level level = HyperLogger.Level.DEBUG;
            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb = new StringBuilder();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
            ?? r11 = "N/A";
            if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                canonicalName = (coroutineScope == null || (cls = coroutineScope.getClass()) == null) ? 0 : cls.getCanonicalName();
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
            String str3 = compView.getCompLogTag() + " postInflate: onChangeFlow called, oldValue: " + compView.getValue() + ", newValue: " + obj2;
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
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            String canonicalName2 = (coroutineScope == null || (cls2 = coroutineScope.getClass()) == null) ? null : cls2.getCanonicalName();
                            if (canonicalName2 != null) {
                                r11 = canonicalName2;
                            }
                        } else {
                            r11 = substringAfterLast$default;
                        }
                        objectRef2.element = r11;
                        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                        if (matcher2.find()) {
                            ?? replaceAll2 = matcher2.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                            objectRef2.element = replaceAll2;
                        }
                        if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                            str2 = (String) objectRef2.element;
                        } else {
                            str2 = ((String) objectRef2.element).substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str4 = compView.getCompLogTag() + " postInflate: onChangeFlow called, oldValue: " + compView.getValue() + ", newValue: " + obj2;
                        if (str4 == null) {
                            str4 = "null ";
                        }
                        sb2.append(str4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, str2, sb2.toString());
                    }
                }
            }
            WorkflowModule.Properties.Section.Component.Handler onChange = this.this$0.getComponent().getOnChange();
            if (onChange != null) {
                FormFragment.CompView.processHandler$hyperkyc_release$default(this.this$0, onChange, false, 2, null);
            }
            return Unit.INSTANCE;
        }
    }
}
