package co.hyperverge.hyperkyc.ui;

import android.view.View;
import android.widget.VideoView;
import co.hyperverge.hyperkyc.databinding.HkFragmentSessionReviewBinding;
import co.hyperverge.hyperkyc.hvsessionrecorder.HVSessionRecorder;
import co.hyperverge.hyperkyc.ui.SessionReviewFragment$initViews$2$3;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import co.hyperverge.hyperkyc.ui.viewmodels.MainVM;
import com.google.android.material.button.MaterialButton;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: SessionReviewFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.SessionReviewFragment$initViews$2$3", f = "SessionReviewFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class SessionReviewFragment$initViews$2$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HkFragmentSessionReviewBinding $this_with;
    final /* synthetic */ boolean $uploadSession;
    int label;
    final /* synthetic */ SessionReviewFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionReviewFragment$initViews$2$3(SessionReviewFragment sessionReviewFragment, HkFragmentSessionReviewBinding hkFragmentSessionReviewBinding, boolean z, Continuation<? super SessionReviewFragment$initViews$2$3> continuation) {
        super(2, continuation);
        this.this$0 = sessionReviewFragment;
        this.$this_with = hkFragmentSessionReviewBinding;
        this.$uploadSession = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionReviewFragment$initViews$2$3(this.this$0, this.$this_with, this.$uploadSession, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionReviewFragment$initViews$2$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        HVSessionRecorder companion = HVSessionRecorder.INSTANCE.getInstance();
        final SessionReviewFragment sessionReviewFragment = this.this$0;
        final HkFragmentSessionReviewBinding hkFragmentSessionReviewBinding = this.$this_with;
        final boolean z = this.$uploadSession;
        companion.stop$hyperkyc_release(new Function1<File, Unit>() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$initViews$2$3.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: SessionReviewFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "co.hyperverge.hyperkyc.ui.SessionReviewFragment$initViews$2$3$1$1", f = "SessionReviewFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: co.hyperverge.hyperkyc.ui.SessionReviewFragment$initViews$2$3$1$1, reason: invalid class name and collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C00131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ File $result;
                final /* synthetic */ HkFragmentSessionReviewBinding $this_with;
                final /* synthetic */ boolean $uploadSession;
                int label;
                final /* synthetic */ SessionReviewFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00131(File file, SessionReviewFragment sessionReviewFragment, HkFragmentSessionReviewBinding hkFragmentSessionReviewBinding, boolean z, Continuation<? super C00131> continuation) {
                    super(2, continuation);
                    this.$result = file;
                    this.this$0 = sessionReviewFragment;
                    this.$this_with = hkFragmentSessionReviewBinding;
                    this.$uploadSession = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00131(this.$result, this.this$0, this.$this_with, this.$uploadSession, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C00131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    String filePath;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (this.$result != null) {
                        SessionReviewFragment sessionReviewFragment = this.this$0;
                        VideoView vvReview = this.$this_with.vvReview;
                        Intrinsics.checkNotNullExpressionValue(vvReview, "vvReview");
                        filePath = this.this$0.getFilePath();
                        sessionReviewFragment.initVideoPlayer(vvReview, filePath);
                    }
                    MaterialButton materialButton = this.$this_with.btnConfirm;
                    final boolean z = this.$uploadSession;
                    final SessionReviewFragment sessionReviewFragment2 = this.this$0;
                    materialButton.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.ui.SessionReviewFragment$initViews$2$3$1$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            SessionReviewFragment$initViews$2$3.AnonymousClass1.C00131.invokeSuspend$lambda$0(z, sessionReviewFragment2, view);
                        }
                    });
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invokeSuspend$lambda$0(boolean z, SessionReviewFragment sessionReviewFragment, View view) {
                    WorkflowUIState workflowUIState;
                    String filePath;
                    MainVM mainVM;
                    if (z) {
                        sessionReviewFragment.proceedToUpload();
                        return;
                    }
                    workflowUIState = sessionReviewFragment.uiState;
                    filePath = sessionReviewFragment.getFilePath();
                    sessionReviewFragment.addSessionRecordingVideoUriToResult(workflowUIState, filePath, "yes");
                    mainVM = sessionReviewFragment.getMainVM();
                    mainVM.flowForward();
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(File file) {
                invoke2(file);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(File file) {
                BuildersKt__Builders_commonKt.launch$default(SessionReviewFragment.this.getLifecycleScope(), Dispatchers.getMain(), null, new C00131(file, SessionReviewFragment.this, hkFragmentSessionReviewBinding, z, null), 2, null);
            }
        });
        return Unit.INSTANCE;
    }
}
