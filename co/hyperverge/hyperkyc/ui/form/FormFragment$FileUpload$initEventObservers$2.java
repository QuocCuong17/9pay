package co.hyperverge.hyperkyc.ui.form;

import android.net.Uri;
import co.hyperverge.hyperkyc.ui.form.FormFragment;
import co.hyperverge.hyperkyc.ui.form.models.FormFilePickUIEvent;
import java.util.List;
import kotlin.KotlinNothingValueException;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FormFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$initEventObservers$2", f = "FormFragment.kt", i = {}, l = {2010}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FormFragment$FileUpload$initEventObservers$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FormFragment this$0;
    final /* synthetic */ FormFragment.FileUpload this$1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormFragment$FileUpload$initEventObservers$2(FormFragment formFragment, FormFragment.FileUpload fileUpload, Continuation<? super FormFragment$FileUpload$initEventObservers$2> continuation) {
        super(2, continuation);
        this.this$0 = formFragment;
        this.this$1 = fileUpload;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormFragment$FileUpload$initEventObservers$2(this.this$0, this.this$1, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormFragment$FileUpload$initEventObservers$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow<FormFilePickUIEvent> fileTypePickUiEvent = this.this$0.getFormVM$hyperkyc_release().getFileTypePickUiEvent();
            final FormFragment.FileUpload fileUpload = this.this$1;
            final FormFragment formFragment = this.this$0;
            this.label = 1;
            if (fileTypePickUiEvent.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment$FileUpload$initEventObservers$2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((FormFilePickUIEvent) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(FormFilePickUIEvent formFilePickUIEvent, Continuation<? super Unit> continuation) {
                    if (Intrinsics.areEqual(formFilePickUIEvent.getComponentId(), FormFragment.FileUpload.this.getComponent().getId())) {
                        FormFragment formFragment2 = formFragment;
                        final FormFragment.FileUpload fileUpload2 = FormFragment.FileUpload.this;
                        formFragment2.filePickedCallback = new Function1<List<? extends Uri>, Unit>() { // from class: co.hyperverge.hyperkyc.ui.form.FormFragment.FileUpload.initEventObservers.2.1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(List<? extends Uri> list) {
                                invoke2(list);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(List<? extends Uri> uris) {
                                Intrinsics.checkNotNullParameter(uris, "uris");
                                FormFragment.FileUpload.this.addPickedFiles$hyperkyc_release(uris);
                            }
                        };
                        FormFragment.FileUpload.this.pickFiles(formFilePickUIEvent);
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
