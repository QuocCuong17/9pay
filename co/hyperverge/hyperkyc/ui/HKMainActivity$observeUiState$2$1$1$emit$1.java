package co.hyperverge.hyperkyc.ui;

import co.hyperverge.hyperkyc.ui.HKMainActivity$observeUiState$2;
import co.hyperverge.hyperkyc.ui.models.WorkflowUIState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HKMainActivity.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.HKMainActivity$observeUiState$2$1$1", f = "HKMainActivity.kt", i = {}, l = {1170, 1171, 1172, 1197}, m = "emit", n = {}, s = {})
/* loaded from: classes2.dex */
public final class HKMainActivity$observeUiState$2$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HKMainActivity$observeUiState$2.AnonymousClass1.C00111<T> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HKMainActivity$observeUiState$2$1$1$emit$1(HKMainActivity$observeUiState$2.AnonymousClass1.C00111<? super T> c00111, Continuation<? super HKMainActivity$observeUiState$2$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c00111;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((WorkflowUIState) null, (Continuation<? super Unit>) this);
    }
}
