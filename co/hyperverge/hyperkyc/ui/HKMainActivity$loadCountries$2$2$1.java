package co.hyperverge.hyperkyc.ui;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.databinding.HkActivityMainBinding;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import com.airbnb.lottie.LottieAnimationView;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$loadCountries$2$2$1", f = "HKMainActivity.kt", i = {}, l = {718}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$loadCountries$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Continuation<List<KycCountry>> $continuation;
    final /* synthetic */ HkActivityMainBinding $this_with;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HKMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HKMainActivity$loadCountries$2$2$1(HKMainActivity hKMainActivity, HkActivityMainBinding hkActivityMainBinding, Continuation<? super List<KycCountry>> continuation, Continuation<? super HKMainActivity$loadCountries$2$2$1> continuation2) {
        super(2, continuation2);
        this.this$0 = hKMainActivity;
        this.$this_with = hkActivityMainBinding;
        this.$continuation = continuation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HKMainActivity$loadCountries$2$2$1 hKMainActivity$loadCountries$2$2$1 = new HKMainActivity$loadCountries$2$2$1(this.this$0, this.$this_with, this.$continuation, continuation);
        hKMainActivity$loadCountries$2$2$1.L$0 = obj;
        return hKMainActivity$loadCountries$2$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HKMainActivity$loadCountries$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Flow<NetworkUIState<List<KycCountry>>> fetchCountries = this.this$0.getMainVM().fetchCountries();
            final HKMainActivity hKMainActivity = this.this$0;
            final HkActivityMainBinding hkActivityMainBinding = this.$this_with;
            final Continuation<List<KycCountry>> continuation = this.$continuation;
            this.label = 1;
            if (fetchCountries.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.HKMainActivity$loadCountries$2$2$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation2) {
                    return emit((NetworkUIState<? extends List<KycCountry>>) obj2, (Continuation<? super Unit>) continuation2);
                }

                public final Object emit(NetworkUIState<? extends List<KycCountry>> networkUIState, Continuation<? super Unit> continuation2) {
                    String canonicalName;
                    Class<?> cls;
                    Object m1202constructorimpl;
                    Class<?> cls2;
                    String className;
                    String substringAfterLast$default;
                    String className2;
                    if (networkUIState instanceof NetworkUIState.Loading) {
                        HKMainActivity.showRetry$default(HKMainActivity.this, false, null, null, null, null, 30, null);
                        LottieAnimationView lavLoader = hkActivityMainBinding.lavLoader;
                        Intrinsics.checkNotNullExpressionValue(lavLoader, "lavLoader");
                        lavLoader.setVisibility(0);
                    } else {
                        if (networkUIState instanceof NetworkUIState.Failed ? true : networkUIState instanceof NetworkUIState.NetworkFailure) {
                            CoroutineScope coroutineScope2 = coroutineScope;
                            HyperLogger.Level level = HyperLogger.Level.ERROR;
                            HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb = new StringBuilder();
                            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                            String str = "N/A";
                            String str2 = null;
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
                            sb.append("came to failed");
                            sb.append(' ');
                            sb.append("");
                            companion.log(level, sb.toString());
                            CoreExtsKt.isRelease();
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
                                        if (coroutineScope2 != null && (cls2 = coroutineScope2.getClass()) != null) {
                                            str2 = cls2.getCanonicalName();
                                        }
                                        if (str2 != null) {
                                            str = str2;
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
                                    Log.println(6, str, "came to failed ");
                                }
                            }
                            Continuation<List<KycCountry>> continuation3 = continuation;
                            Result.Companion companion4 = Result.INSTANCE;
                            continuation3.resumeWith(Result.m1202constructorimpl(CollectionsKt.emptyList()));
                        } else if (networkUIState instanceof NetworkUIState.Success) {
                            Continuation<List<KycCountry>> continuation4 = continuation;
                            Result.Companion companion5 = Result.INSTANCE;
                            continuation4.resumeWith(Result.m1202constructorimpl(((NetworkUIState.Success) networkUIState).getData()));
                        }
                    }
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
}
