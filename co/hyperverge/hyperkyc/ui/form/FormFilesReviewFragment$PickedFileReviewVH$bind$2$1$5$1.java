package co.hyperverge.hyperkyc.ui.form;

import co.hyperverge.hyperkyc.ui.custom.SimpleRvAdapter;
import co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment;
import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FormFilesReviewFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1", f = "FormFilesReviewFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PickedFile $pickedFile;
    final /* synthetic */ SimpleRvAdapter<PickedFile, FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH> $this_apply;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1(PickedFile pickedFile, SimpleRvAdapter<PickedFile, FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH> simpleRvAdapter, Continuation<? super FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1> continuation) {
        super(2, continuation);
        this.$pickedFile = pickedFile;
        this.$this_apply = simpleRvAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1(this.$pickedFile, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer pDFPageCount;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (Intrinsics.areEqual(this.$pickedFile.getExtension(), "pdf")) {
            String localPath = this.$pickedFile.getLocalPath();
            int intValue = (localPath == null || (pDFPageCount = FileExtsKt.getPDFPageCount(new File(localPath))) == null) ? -1 : pDFPageCount.intValue();
            if (intValue > 0) {
                SimpleRvAdapter<PickedFile, FormFilesReviewFragment.PickedFileReviewVH.DocumentPageVH> simpleRvAdapter = this.$this_apply;
                final PickedFile pickedFile = this.$pickedFile;
                SimpleRvAdapter.updateItems$default(simpleRvAdapter, SequencesKt.toList(SequencesKt.take(SequencesKt.generateSequence(new Function0<PickedFile>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFilesReviewFragment$PickedFileReviewVH$bind$2$1$5$1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final PickedFile invoke() {
                        return PickedFile.this;
                    }
                }), intValue)), null, 2, null);
            }
            return Unit.INSTANCE;
        }
        throw new NotImplementedError("An operation is not implemented: " + (this.$pickedFile.getExtension() + " is not supported yet"));
    }
}
