package co.hyperverge.hyperkyc.ui;

import androidx.core.os.BundleKt;
import androidx.fragment.app.FragmentActivity;
import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM;
import co.hyperverge.hyperkyc.utils.extensions.ActivityExtsKt;
import co.hyperverge.hyperkyc.utils.extensions.CoroutineExtsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2Fragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2", f = "VideoStatementV2Fragment.kt", i = {}, l = {699}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementV2Fragment$startProcessing$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoStatementV2Fragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStatementV2Fragment$startProcessing$2(VideoStatementV2Fragment videoStatementV2Fragment, Continuation<? super VideoStatementV2Fragment$startProcessing$2> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2Fragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementV2Fragment$startProcessing$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2Fragment$startProcessing$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VideoStatementV2Fragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1", f = "VideoStatementV2Fragment.kt", i = {0}, l = {731, 742}, m = "invokeSuspend", n = {"isError"}, s = {"L$0"})
    /* renamed from: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ VideoStatementV2Fragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(VideoStatementV2Fragment videoStatementV2Fragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = videoStatementV2Fragment;
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
            Ref.BooleanRef booleanRef;
            Deferred async$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                booleanRef = new Ref.BooleanRef();
                async$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoStatementV2Fragment$startProcessing$2$1$failedCheck$1(this.this$0, booleanRef, null), 3, null);
                this.L$0 = booleanRef;
                this.label = 1;
                obj = async$default.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                booleanRef = (Ref.BooleanRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            VideoStatementV2VM.Check check = (VideoStatementV2VM.Check) obj;
            if (!ActivityExtsKt.isFragmentAliveAndAttached(this.this$0) || booleanRef.element) {
                return Unit.INSTANCE;
            }
            this.L$0 = null;
            this.label = 2;
            if (CoroutineExtsKt.onUI$default(null, new C00141(check, this.this$0, null), this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: VideoStatementV2Fragment.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$1", f = "VideoStatementV2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment$startProcessing$2$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C00141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ VideoStatementV2VM.Check $failedCheck;
            int label;
            final /* synthetic */ VideoStatementV2Fragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00141(VideoStatementV2VM.Check check, VideoStatementV2Fragment videoStatementV2Fragment, Continuation<? super C00141> continuation) {
                super(2, continuation);
                this.$failedCheck = check;
                this.this$0 = videoStatementV2Fragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00141(this.$failedCheck, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                VideoStatementV2VM videoStatementV2VM;
                VideoStatementV2Config videoStatementV2Config;
                HKMainActivity hKMainActivity;
                MainVM mainVM;
                VideoStatementV2Config videoStatementV2Config2;
                MainVM mainVM2;
                MainVM mainVM3;
                VideoStatementV2Config videoStatementV2Config3;
                Map textConfigs;
                MainVM mainVM4;
                VideoStatementV2Config videoStatementV2Config4;
                MainVM mainVM5;
                MainVM mainVM6;
                VideoStatementV2Config videoStatementV2Config5;
                Map textConfigs2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (this.$failedCheck == VideoStatementV2VM.Check.NONE) {
                    mainVM4 = this.this$0.getMainVM();
                    videoStatementV2Config4 = this.this$0.vsConfigV2;
                    if (!Intrinsics.areEqual(mainVM4.asBoolean$hyperkyc_release(videoStatementV2Config4.getShowEndState(), Boxing.boxBoolean(false)), Boxing.boxBoolean(true))) {
                        mainVM5 = this.this$0.getMainVM();
                        mainVM5.flowForward();
                    } else {
                        FragmentActivity activity = this.this$0.getActivity();
                        hKMainActivity = activity instanceof HKMainActivity ? (HKMainActivity) activity : null;
                        if (hKMainActivity != null) {
                            LoadingFragment loadingFragment = new LoadingFragment();
                            textConfigs2 = this.this$0.getTextConfigs();
                            ActivityExtsKt.replaceContent$default(hKMainActivity, loadingFragment, BundleKt.bundleOf(TuplesKt.to("textConfigs", textConfigs2)), false, null, 0, 28, null);
                        }
                        VideoStatementV2Fragment videoStatementV2Fragment = this.this$0;
                        VideoStatementV2Fragment videoStatementV2Fragment2 = videoStatementV2Fragment;
                        mainVM6 = videoStatementV2Fragment.getMainVM();
                        videoStatementV2Config5 = this.this$0.vsConfigV2;
                        Boolean asBoolean$hyperkyc_release = mainVM6.asBoolean$hyperkyc_release(videoStatementV2Config5.getIsSuccess(), Boxing.boxBoolean(true));
                        final VideoStatementV2Fragment videoStatementV2Fragment3 = this.this$0;
                        BaseFragment.updateEndState$hyperkyc_release$default(videoStatementV2Fragment2, false, asBoolean$hyperkyc_release, false, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment.startProcessing.2.1.1.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                MainVM mainVM7;
                                mainVM7 = VideoStatementV2Fragment.this.getMainVM();
                                mainVM7.flowForward();
                            }
                        }, 4, null);
                    }
                } else {
                    videoStatementV2VM = this.this$0.vsV2VM;
                    if (videoStatementV2VM == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vsV2VM");
                        videoStatementV2VM = null;
                    }
                    int attemptsCount$hyperkyc_release = videoStatementV2VM.getAttemptsCount$hyperkyc_release();
                    videoStatementV2Config = this.this$0.vsConfigV2;
                    if (attemptsCount$hyperkyc_release < videoStatementV2Config.getAllowedAttempts()) {
                        this.this$0.moveToReRecordScreen(this.$failedCheck);
                    } else {
                        FragmentActivity activity2 = this.this$0.getActivity();
                        hKMainActivity = activity2 instanceof HKMainActivity ? (HKMainActivity) activity2 : null;
                        if (hKMainActivity != null) {
                            LoadingFragment loadingFragment2 = new LoadingFragment();
                            textConfigs = this.this$0.getTextConfigs();
                            ActivityExtsKt.replaceContent$default(hKMainActivity, loadingFragment2, BundleKt.bundleOf(TuplesKt.to("textConfigs", textConfigs)), false, null, 0, 28, null);
                        }
                        mainVM = this.this$0.getMainVM();
                        videoStatementV2Config2 = this.this$0.vsConfigV2;
                        if (!Intrinsics.areEqual(mainVM.asBoolean$hyperkyc_release(videoStatementV2Config2.getShowEndState(), Boxing.boxBoolean(false)), Boxing.boxBoolean(true))) {
                            mainVM2 = this.this$0.getMainVM();
                            mainVM2.flowForward();
                        } else {
                            VideoStatementV2Fragment videoStatementV2Fragment4 = this.this$0;
                            VideoStatementV2Fragment videoStatementV2Fragment5 = videoStatementV2Fragment4;
                            mainVM3 = videoStatementV2Fragment4.getMainVM();
                            videoStatementV2Config3 = this.this$0.vsConfigV2;
                            Boolean asBoolean$hyperkyc_release2 = mainVM3.asBoolean$hyperkyc_release(videoStatementV2Config3.getIsSuccess(), Boxing.boxBoolean(true));
                            final VideoStatementV2Fragment videoStatementV2Fragment6 = this.this$0;
                            BaseFragment.updateEndState$hyperkyc_release$default(videoStatementV2Fragment5, false, asBoolean$hyperkyc_release2, false, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.ui.VideoStatementV2Fragment.startProcessing.2.1.1.2
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    MainVM mainVM7;
                                    mainVM7 = VideoStatementV2Fragment.this.getMainVM();
                                    mainVM7.flowForward();
                                }
                            }, 4, null);
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (CoroutineExtsKt.onIO$default(null, new AnonymousClass1(this.this$0, null), this, 1, null) == coroutine_suspended) {
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
