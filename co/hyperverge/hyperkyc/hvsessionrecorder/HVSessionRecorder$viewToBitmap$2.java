package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import co.hyperverge.hyperkyc.utils.extensions.CoreExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.LogExtsKt;
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
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HVSessionRecorder.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$2", f = "HVSessionRecorder.kt", i = {}, l = {245}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HVSessionRecorder$viewToBitmap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $view;
    int label;
    final /* synthetic */ HVSessionRecorder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVSessionRecorder$viewToBitmap$2(View view, HVSessionRecorder hVSessionRecorder, Continuation<? super HVSessionRecorder$viewToBitmap$2> continuation) {
        super(2, continuation);
        this.$view = view;
        this.this$0 = hVSessionRecorder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HVSessionRecorder$viewToBitmap$2(this.$view, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HVSessionRecorder$viewToBitmap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HVSessionRecorder.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$2$1", f = "HVSessionRecorder.kt", i = {0}, l = {268}, m = "invokeSuspend", n = {"$this$onDefault"}, s = {"L$0"})
    /* renamed from: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$2$1, reason: invalid class name */
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

        /* JADX WARN: Code restructure failed: missing block: B:42:0x01e2, code lost:
        
            if (r6 != null) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x01f4, code lost:
        
            if (r6 == null) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x01f8, code lost:
        
            r0.element = r13;
            r2 = co.hyperverge.hyperkyc.utils.extensions.LogExtsKt.ANON_CLASS_PATTERN.matcher((java.lang.CharSequence) r0.element);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x020a, code lost:
        
            if (r2.find() == false) goto L82;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x020c, code lost:
        
            r2 = r2.replaceAll("");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "replaceAll(\"\")");
            r0.element = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x021f, code lost:
        
            if (((java.lang.String) r0.element).length() <= 23) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0225, code lost:
        
            if (android.os.Build.VERSION.SDK_INT < 26) goto L87;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0228, code lost:
        
            r0 = ((java.lang.String) r0.element).substring(0, 23);
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "this as java.lang.String…ing(startIndex, endIndex)");
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0238, code lost:
        
            r2 = new java.lang.StringBuilder();
            r3 = "viewToBitmap() error in shouldRecordPreviewOnly: " + r4.getClass() + ": " + r4.getMessage();
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x025a, code lost:
        
            if (r3 != null) goto L92;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x025d, code lost:
        
            r10 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x025e, code lost:
        
            r2.append(r10);
            r2.append(' ');
            r2.append("");
            android.util.Log.println(6, r0, r2.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0234, code lost:
        
            r0 = (java.lang.String) r0.element;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x01f7, code lost:
        
            r13 = r6;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v10, types: [T] */
        /* JADX WARN: Type inference failed for: r11v18, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r11v20, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r11v21 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v6 */
        /* JADX WARN: Type inference failed for: r11v7 */
        /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v2, types: [T] */
        /* JADX WARN: Type inference failed for: r2v11, types: [T, java.lang.Object, java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
        /* JADX WARN: Type inference failed for: r2v18, types: [kotlinx.coroutines.CoroutineScope] */
        /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v23 */
        /* JADX WARN: Type inference failed for: r2v24 */
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
            String str4;
            int i;
            int i2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            try {
                if (i3 == 0) {
                    ResultKt.throwOnFailure(obj);
                    ?? r22 = (CoroutineScope) this.L$0;
                    View view = this.$view;
                    HVSessionRecorder hVSessionRecorder = this.this$0;
                    Result.Companion companion = Result.INSTANCE;
                    ArrayList<View> arrayList = new ArrayList<>();
                    str4 = hVSessionRecorder.cameraPreviewTag;
                    view.findViewsWithText(arrayList, str4, 2);
                    i3 = r22;
                    if (!arrayList.isEmpty()) {
                        View view2 = arrayList.get(0);
                        Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.view.TextureView");
                        Bitmap bitmap = ((TextureView) view2).getBitmap();
                        i3 = r22;
                        if (bitmap != null) {
                            i = hVSessionRecorder.bitmapWidth;
                            i2 = hVSessionRecorder.bitmapHeight;
                            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, false);
                            bitmap.recycle();
                            HVSessionRecorder$viewToBitmap$2$1$1$1 hVSessionRecorder$viewToBitmap$2$1$1$1 = new HVSessionRecorder$viewToBitmap$2$1$1$1(hVSessionRecorder, createScaledBitmap, null);
                            this.L$0 = r22;
                            this.label = 1;
                            i3 = r22;
                            if (CoroutineExtsKt.onUI$default(null, hVSessionRecorder$viewToBitmap$2$1$1$1, this, 1, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    }
                } else {
                    if (i3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ?? r23 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    i3 = r23;
                }
                m1202constructorimpl = Result.m1202constructorimpl(Unit.INSTANCE);
                r2 = i3;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                r2 = i3;
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
                ?? r13 = "N/A";
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
                String str5 = "viewToBitmap() error in shouldRecordPreviewOnly: " + m1205exceptionOrNullimpl.getClass() + ": " + m1205exceptionOrNullimpl.getMessage();
                String str6 = "null ";
                if (str5 == null) {
                    str5 = "null ";
                }
                sb.append(str5);
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
            if (CoroutineExtsKt.onDefault$default(null, new AnonymousClass1(this.$view, this.this$0, null), this, 1, null) == coroutine_suspended) {
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
