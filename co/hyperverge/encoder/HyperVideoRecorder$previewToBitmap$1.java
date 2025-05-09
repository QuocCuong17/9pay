package co.hyperverge.encoder;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import co.hyperverge.encoder.utils.extensions.CoroutineExtsKt;
import co.hyperverge.encoder.utils.extensions.LogExtsKt;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.ArrayList;
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
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HyperVideoRecorder.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "co.hyperverge.encoder.HyperVideoRecorder$previewToBitmap$1", f = "HyperVideoRecorder.kt", i = {}, l = {126}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HyperVideoRecorder$previewToBitmap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $view;
    int label;
    final /* synthetic */ HyperVideoRecorder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HyperVideoRecorder$previewToBitmap$1(HyperVideoRecorder hyperVideoRecorder, View view, Continuation<? super HyperVideoRecorder$previewToBitmap$1> continuation) {
        super(2, continuation);
        this.this$0 = hyperVideoRecorder;
        this.$view = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HyperVideoRecorder$previewToBitmap$1(this.this$0, this.$view, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HyperVideoRecorder$previewToBitmap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HyperVideoRecorder.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.encoder.HyperVideoRecorder$previewToBitmap$1$1", f = "HyperVideoRecorder.kt", i = {0}, l = {CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA}, m = "invokeSuspend", n = {"$this$onDefault"}, s = {"L$0"})
    /* renamed from: co.hyperverge.encoder.HyperVideoRecorder$previewToBitmap$1$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ View $view;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ HyperVideoRecorder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HyperVideoRecorder hyperVideoRecorder, View view, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = hyperVideoRecorder;
            this.$view = view;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$view, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0085  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Throwable th;
            String str;
            String str2;
            Bitmap bitmap;
            Object m1202constructorimpl;
            Throwable m1205exceptionOrNullimpl;
            String className;
            Object obj2;
            Object invoke;
            Class<?> cls;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                HyperVideoRecorder hyperVideoRecorder = this.this$0;
                View view = this.$view;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    str = hyperVideoRecorder.cameraPreviewTag;
                    if (str != null) {
                        ArrayList<View> arrayList = new ArrayList<>();
                        str2 = hyperVideoRecorder.cameraPreviewTag;
                        view.findViewsWithText(arrayList, str2, 2);
                        if ((!arrayList.isEmpty()) && (bitmap = ((TextureView) arrayList.get(0)).getBitmap()) != null) {
                            HyperVideoRecorder$previewToBitmap$1$1$1$1 hyperVideoRecorder$previewToBitmap$1$1$1$1 = new HyperVideoRecorder$previewToBitmap$1$1$1$1(hyperVideoRecorder, bitmap, null);
                            this.L$0 = coroutineScope2;
                            this.label = 1;
                            if (CoroutineExtsKt.onUI$default(null, hyperVideoRecorder$previewToBitmap$1$1$1$1, this, 1, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                    coroutineScope = coroutineScope2;
                } catch (Throwable th2) {
                    coroutineScope = coroutineScope2;
                    th = th2;
                    Result.Companion companion2 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
            } else if (i == 1) {
                coroutineScope = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion22 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                    m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
                    if (m1205exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
            m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
            if (m1205exceptionOrNullimpl != null) {
                HyperLogger.Level level = HyperLogger.Level.ERROR;
                HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                String substringAfterLast$default = (stackTraceElement == null || (className = stackTraceElement.getClassName()) == null) ? null : StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                if (substringAfterLast$default == null && (coroutineScope == null || (cls = coroutineScope.getClass()) == null || (substringAfterLast$default = cls.getCanonicalName()) == null)) {
                    substringAfterLast$default = "N/A";
                }
                Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(substringAfterLast$default);
                if (matcher.find()) {
                    substringAfterLast$default = matcher.replaceAll("");
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "replaceAll(\"\")");
                }
                if (substringAfterLast$default.length() > 23 && Build.VERSION.SDK_INT < 26) {
                    substringAfterLast$default = substringAfterLast$default.substring(0, 23);
                    Intrinsics.checkNotNullExpressionValue(substringAfterLast$default, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                sb.append(substringAfterLast$default);
                sb.append(" - ");
                String str3 = "previewToBitmap() " + m1205exceptionOrNullimpl.getClass() + ": " + ((Object) m1205exceptionOrNullimpl.getMessage());
                if (str3 == null) {
                    str3 = "null ";
                }
                sb.append(str3);
                sb.append(' ');
                sb.append("");
                companion3.log(level, sb.toString());
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                } catch (Throwable th4) {
                    Result.Companion companion5 = Result.INSTANCE;
                    obj2 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
                }
                if (invoke != null) {
                    obj2 = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (CoroutineExtsKt.onDefault$default(null, new AnonymousClass1(this.this$0, this.$view, null), this, 1, null) == coroutine_suspended) {
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
