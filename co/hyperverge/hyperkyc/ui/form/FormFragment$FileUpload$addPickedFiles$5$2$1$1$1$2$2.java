package co.hyperverge.hyperkyc.ui.form;

import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2", f = "FormFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Throwable $it;
    final /* synthetic */ PickedFile $pickedFile;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2(PickedFile pickedFile, Throwable th, Continuation<? super FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2> continuation) {
        super(2, continuation);
        this.$pickedFile = pickedFile;
        this.$it = th;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2(this.$pickedFile, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        PickedFile pickedFile = this.$pickedFile;
        String message = this.$it.getMessage();
        if (message == null) {
            if (this.$pickedFile.getUri() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error loading file: ");
                Comparable name = this.$pickedFile.getName();
                if (name == null) {
                    name = this.$pickedFile.getUri();
                }
                sb.append(name);
                message = sb.toString();
            } else {
                message = null;
            }
        }
        pickedFile.setState(new PickedFile.State.Error(message));
        return Unit.INSTANCE;
    }
}
