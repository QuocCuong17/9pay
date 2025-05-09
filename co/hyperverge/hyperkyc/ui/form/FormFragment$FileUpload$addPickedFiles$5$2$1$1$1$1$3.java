package co.hyperverge.hyperkyc.ui.form;

import android.net.Uri;
import co.hyperverge.hyperkyc.ui.form.models.PickedFile;
import co.hyperverge.hyperkyc.utils.extensions.FileExtsKt;
import java.io.File;
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

/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3", f = "FormFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $formFilesDir;
    final /* synthetic */ PickedFile $pickedFile;
    int label;
    final /* synthetic */ FormFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3(File file, PickedFile pickedFile, FormFragment formFragment, Continuation<? super FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3> continuation) {
        super(2, continuation);
        this.$formFilesDir = file;
        this.$pickedFile = pickedFile;
        this.this$0 = formFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3(this.$formFilesDir, this.$pickedFile, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$FileUpload$addPickedFiles$5$2$1$1$1$1$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        File file = this.$formFilesDir;
        String name = this.$pickedFile.getName();
        Intrinsics.checkNotNull(name);
        File file2 = new File(file, name);
        if (!file2.exists()) {
            Uri uri = this.$pickedFile.getUri();
            Intrinsics.checkNotNull(uri);
            FileExtsKt.copyFileTo(uri, this.this$0.getContentResolver$hyperkyc_release(), file2);
        }
        this.$pickedFile.setLocalPath(file2.getPath());
        return Unit.INSTANCE;
    }
}
