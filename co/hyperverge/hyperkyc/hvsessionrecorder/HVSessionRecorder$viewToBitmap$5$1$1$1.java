package co.hyperverge.hyperkyc.hvsessionrecorder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.TextureView;
import android.view.View;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HVSessionRecorder.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$5$1$1$1", f = "HVSessionRecorder.kt", i = {}, l = {373}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HVSessionRecorder$viewToBitmap$5$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $view;
    final /* synthetic */ Bitmap $viewBitmap;
    int label;
    final /* synthetic */ HVSessionRecorder this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HVSessionRecorder$viewToBitmap$5$1$1$1(Bitmap bitmap, View view, HVSessionRecorder hVSessionRecorder, Continuation<? super HVSessionRecorder$viewToBitmap$5$1$1$1> continuation) {
        super(2, continuation);
        this.$viewBitmap = bitmap;
        this.$view = view;
        this.this$0 = hVSessionRecorder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HVSessionRecorder$viewToBitmap$5$1$1$1(this.$viewBitmap, this.$view, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HVSessionRecorder$viewToBitmap$5$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        int i;
        int i2;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            this.$viewBitmap.setHasAlpha(true);
            Bitmap createBitmap = Bitmap.createBitmap(this.$view.getWidth(), this.$view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            str = this.this$0.cameraPreviewTag;
            if (str != null) {
                ArrayList<View> arrayList = new ArrayList<>();
                View view = this.$view;
                str2 = this.this$0.cameraPreviewTag;
                view.findViewsWithText(arrayList, str2, 2);
                if (!arrayList.isEmpty()) {
                    View view2 = arrayList.get(0);
                    Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.view.TextureView");
                    TextureView textureView = (TextureView) view2;
                    Bitmap bitmap = textureView.getBitmap();
                    if (bitmap != null) {
                        textureView.getLocationOnScreen(new int[]{0, 0});
                        canvas.drawBitmap(bitmap, r7[0], r7[1], (Paint) null);
                        bitmap.recycle();
                    }
                }
            } else {
                canvas.drawColor(-1);
            }
            canvas.drawBitmap(this.$viewBitmap, 0.0f, 0.0f, (Paint) null);
            i = this.this$0.bitmapWidth;
            i2 = this.this$0.bitmapHeight;
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(createBitmap, i, i2, true);
            this.$viewBitmap.recycle();
            createBitmap.recycle();
            this.label = 1;
            if (CoroutineExtsKt.onUI$default(null, new AnonymousClass1(this.this$0, createScaledBitmap, null), this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HVSessionRecorder.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$5$1$1$1$1", f = "HVSessionRecorder.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder$viewToBitmap$5$1$1$1$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bitmap $scaledBitmap;
        int label;
        final /* synthetic */ HVSessionRecorder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(HVSessionRecorder hVSessionRecorder, Bitmap bitmap, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = hVSessionRecorder;
            this.$scaledBitmap = bitmap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$scaledBitmap, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HVSessionRecorder hVSessionRecorder = this.this$0;
            Bitmap scaledBitmap = this.$scaledBitmap;
            Intrinsics.checkNotNullExpressionValue(scaledBitmap, "scaledBitmap");
            hVSessionRecorder.queueBitmap(scaledBitmap);
            return Unit.INSTANCE;
        }
    }
}
