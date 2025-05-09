package co.hyperverge.hyperkyc.ui;

import android.text.Spanned;
import android.view.View;
import co.hyperverge.hyperkyc.databinding.HkFragmentVideoStatementV2Binding;
import co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment;
import co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$observeUIState$2;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.Shimmer;
import co.hyperverge.hyperkyc.ui.custom.shimmeringViews.ShimmerTextView;
import co.hyperverge.hypersnapsdk.components.camera.HVFacePreview;
import co.hyperverge.hypersnapsdk.components.camera.model.FaceStateUIFlow;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2Fragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$observeUIState$2", f = "VideoStatementV2Fragment.kt", i = {}, l = {351}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementV2Fragment$observeUIState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HVFacePreview $facePreview;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ VideoStatementV2Fragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2Fragment$observeUIState$2(HVFacePreview hVFacePreview, VideoStatementV2Fragment videoStatementV2Fragment, Continuation<? super VideoStatementV2Fragment$observeUIState$2> continuation) {
        super(2, continuation);
        this.$facePreview = hVFacePreview;
        this.this$0 = videoStatementV2Fragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoStatementV2Fragment$observeUIState$2 videoStatementV2Fragment$observeUIState$2 = new VideoStatementV2Fragment$observeUIState$2(this.$facePreview, this.this$0, continuation);
        videoStatementV2Fragment$observeUIState$2.L$0 = obj;
        return videoStatementV2Fragment$observeUIState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2Fragment$observeUIState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScopeKt.ensureActive((CoroutineScope) this.L$0);
            this.label = 1;
            if (FlowKt.filterNotNull(this.$facePreview.getUiStateFlow()).collect(new AnonymousClass1(this.this$0, this.$facePreview), this) == coroutine_suspended) {
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
    /* compiled from: VideoStatementV2Fragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "emit", "(Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$observeUIState$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ HVFacePreview $facePreview;
        final /* synthetic */ VideoStatementV2Fragment this$0;

        /* compiled from: VideoStatementV2Fragment.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* renamed from: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$observeUIState$2$1$WhenMappings */
        /* loaded from: classes2.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[VideoStatementV2Fragment.VideoStatementState.values().length];
                try {
                    iArr[VideoStatementV2Fragment.VideoStatementState.PRE_RECORDING.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[VideoStatementV2Fragment.VideoStatementState.RECORDING.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        AnonymousClass1(VideoStatementV2Fragment videoStatementV2Fragment, HVFacePreview hVFacePreview) {
            this.this$0 = videoStatementV2Fragment;
            this.$facePreview = hVFacePreview;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit((FaceStateUIFlow) obj, (Continuation<? super Unit>) continuation);
        }

        public final Object emit(FaceStateUIFlow faceStateUIFlow, Continuation<? super Unit> continuation) {
            Shimmer shimmer;
            HkFragmentVideoStatementV2Binding binding;
            Spanned spanned;
            VideoStatementV2Fragment.VideoStatementState videoStatementState;
            Shimmer shimmer2;
            Shimmer shimmer3;
            HkFragmentVideoStatementV2Binding binding2;
            Spanned spanned2;
            VideoStatementV2Fragment.VideoStatementState videoStatementState2;
            Shimmer shimmer4;
            Shimmer shimmer5;
            boolean z;
            Spanned spanned3 = null;
            if (faceStateUIFlow instanceof FaceStateUIFlow.Detected) {
                shimmer3 = this.this$0.shimmer;
                shimmer3.cancel();
                binding2 = this.this$0.getBinding();
                final VideoStatementV2Fragment videoStatementV2Fragment = this.this$0;
                HVFacePreview hVFacePreview = this.$facePreview;
                ShimmerTextView shimmerTextView = binding2.statusText;
                spanned2 = videoStatementV2Fragment.faceFoundText;
                if (spanned2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("faceFoundText");
                } else {
                    spanned3 = spanned2;
                }
                shimmerTextView.setText(spanned3);
                videoStatementState2 = videoStatementV2Fragment.currentState;
                int i = WhenMappings.$EnumSwitchMapping$0[videoStatementState2.ordinal()];
                if (i == 1) {
                    binding2.btnPrimary.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$observeUIState$2$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            VideoStatementV2Fragment$observeUIState$2.AnonymousClass1.emit$lambda$1$lambda$0(VideoStatementV2Fragment.this, view);
                        }
                    });
                    binding2.btnPrimary.setClickable(true);
                    binding2.btnPrimary.getBackground().setAlpha(255);
                    shimmer4 = videoStatementV2Fragment.shimmer;
                    shimmer4.start(binding2.btnPrimary, videoStatementV2Fragment.getLifecycleScope());
                } else if (i == 2) {
                    videoStatementV2Fragment.isFaceFoundOnce = true;
                    videoStatementV2Fragment.isFaceNotDetected = false;
                    videoStatementV2Fragment.timeOutOfFrameMs = 0;
                    shimmer5 = videoStatementV2Fragment.shimmer;
                    shimmer5.start(binding2.statementHelpText, videoStatementV2Fragment.getLifecycleScope());
                    binding2.statusText.setAlpha(0.4f);
                    z = videoStatementV2Fragment.isSelfieCaptured;
                    if (!z) {
                        hVFacePreview.clickPicture();
                    }
                }
            } else if (faceStateUIFlow instanceof FaceStateUIFlow.NotDetected) {
                shimmer = this.this$0.shimmer;
                shimmer.cancel();
                binding = this.this$0.getBinding();
                VideoStatementV2Fragment videoStatementV2Fragment2 = this.this$0;
                ShimmerTextView shimmerTextView2 = binding.statusText;
                spanned = videoStatementV2Fragment2.faceNotFoundText;
                if (spanned == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("faceNotFoundText");
                    spanned = null;
                }
                shimmerTextView2.setText(spanned);
                videoStatementState = videoStatementV2Fragment2.currentState;
                int i2 = WhenMappings.$EnumSwitchMapping$0[videoStatementState.ordinal()];
                if (i2 == 1) {
                    binding.btnPrimary.setOnClickListener(null);
                    binding.btnPrimary.setClickable(false);
                    binding.btnPrimary.getBackground().setAlpha(102);
                } else if (i2 == 2) {
                    videoStatementV2Fragment2.isFaceNotDetected = true;
                    binding.statusText.setAlpha(1.0f);
                }
                shimmer2 = videoStatementV2Fragment2.shimmer;
                shimmer2.start(binding.statusText, videoStatementV2Fragment2.getLifecycleScope());
            } else if (faceStateUIFlow instanceof FaceStateUIFlow.FaceCapture) {
                FaceStateUIFlow.FaceCapture faceCapture = (FaceStateUIFlow.FaceCapture) faceStateUIFlow;
                if (faceCapture.getFullImageUri() != null) {
                    this.this$0.isSelfieCaptured = true;
                    this.this$0.imageUri = faceCapture.getFullImageUri();
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void emit$lambda$1$lambda$0(VideoStatementV2Fragment this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.moveToRecordingScreen();
        }
    }
}
