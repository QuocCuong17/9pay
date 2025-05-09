package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
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
import org.apache.commons.io.FilenameUtils;

/* compiled from: HVSessionRecorder.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$5", f = "HVSessionRecorder.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class HVSessionRecorder$viewToBitmap$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $view;
    int label;
    final /* synthetic */ HVSessionRecorder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVSessionRecorder$viewToBitmap$5(View view, HVSessionRecorder hVSessionRecorder, Continuation<? super HVSessionRecorder$viewToBitmap$5> continuation) {
        super(2, continuation);
        this.$view = view;
        this.this$0 = hVSessionRecorder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HVSessionRecorder$viewToBitmap$5(this.$view, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HVSessionRecorder$viewToBitmap$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HVSessionRecorder.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$5$1", f = "HVSessionRecorder.kt", i = {0}, l = {314}, m = "invokeSuspend", n = {"$this$onUI"}, s = {"L$0"})
    /* renamed from: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$5$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ View $view;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ HVSessionRecorder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(View view, HVSessionRecorder hVSessionRecorder, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$view = view;
            this.this$0 = hVSessionRecorder;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x01c1, code lost:
        
            if (r5 != null) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x01d3, code lost:
        
            if (r5 == null) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x01d7, code lost:
        
            r0.element = r12;
            r2 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher((java.lang.CharSequence) r0.element);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x01e9, code lost:
        
            if (r2.find() == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x01eb, code lost:
        
            r2 = r2.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "replaceAll(\"\")");
            r0.element = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x01fe, code lost:
        
            if (((java.lang.String) r0.element).length() <= 23) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0204, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0207, code lost:
        
            r0 = ((java.lang.String) r0.element).substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0217, code lost:
        
            r2 = new java.lang.StringBuilder();
            r3 = "viewToBitmap() error in Android 8-: " + r3.getClass() + ": " + r3.getMessage();
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0239, code lost:
        
            if (r3 != null) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x023c, code lost:
        
            r9 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x023d, code lost:
        
            r2.append(r9);
            r2.append(' ');
            r2.append("");
            android.util.Log.println(6, r0, r2.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0213, code lost:
        
            r0 = (java.lang.String) r0.element;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x01d6, code lost:
        
            r12 = r5;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v10, types: [T] */
        /* JADX WARN: Type inference failed for: r10v18, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r10v20, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r10v21 */
        /* JADX WARN: Type inference failed for: r10v5 */
        /* JADX WARN: Type inference failed for: r10v6 */
        /* JADX WARN: Type inference failed for: r10v7 */
        /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r12v1 */
        /* JADX WARN: Type inference failed for: r12v2, types: [T] */
        /* JADX WARN: Type inference failed for: r2v11, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
        /* JADX WARN: Type inference failed for: r2v18, types: [kotlinx.coroutines.CoroutineScope] */
        /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v21 */
        /* JADX WARN: Type inference failed for: r2v22 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object m1202constructorimpl;
            ?? r2;
            ?? canonicalName;
            Class<?> cls;
            String str;
            Object m1202constructorimpl2;
            String str2;
            String str3;
            Class<?> cls2;
            String className;
            String className2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ?? r22 = (CoroutineScope) this.L$0;
                    View view = this.$view;
                    HVSessionRecorder hVSessionRecorder = this.this$0;
                    Result.Companion companion = Result.INSTANCE;
                    view.setDrawingCacheEnabled(true);
                    view.setDrawingCacheBackgroundColor(-1);
                    Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
                    Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(view.drawingCache)");
                    view.setDrawingCacheEnabled(false);
                    HVSessionRecorder$viewToBitmap$5$1$1$1 hVSessionRecorder$viewToBitmap$5$1$1$1 = new HVSessionRecorder$viewToBitmap$5$1$1$1(createBitmap, view, hVSessionRecorder, null);
                    this.L$0 = r22;
                    this.label = 1;
                    i = r22;
                    if (CoroutineExtsKt.onDefault$default(null, hVSessionRecorder$viewToBitmap$5$1$1$1, this, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ?? r23 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    i = r23;
                }
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                r2 = i;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                r2 = i;
            }
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(m1202constructorimpl);
            if (m1205exceptionOrNullimpl != null) {
                HyperLogger.Level level = HyperLogger.Level.ERROR;
                HyperLogger companion3 = HyperLogger.INSTANCE.getInstance();
                StringBuilder sb = new StringBuilder();
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                ?? r12 = "N/A";
                if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                    canonicalName = (r2 == 0 || (cls = r2.getClass()) == null) ? 0 : cls.getCanonicalName();
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
                String str4 = "viewToBitmap() error in Android 8-: " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                String str5 = "null ";
                if (str4 == null) {
                    str4 = "null ";
                }
                sb.append(str4);
                sb.append(' ');
                sb.append("");
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
                        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null) {
                            str2 = null;
                        } else {
                            str2 = null;
                            str3 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        str3 = (r2 == 0 || (cls2 = r2.getClass()) == null) ? str2 : cls2.getCanonicalName();
                    }
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
            if (CoroutineExtsKt.onUI$default(null, new AnonymousClass1(this.$view, this.this$0, null), this, 1, null) == coroutine_suspended) {
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
