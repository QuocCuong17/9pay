package co.hyperverge.hyperkyc.utils.extensions;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ViewExts.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$textChangesFlow$1", f = "ViewExts.kt", i = {}, l = {CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class ViewExtsKt$textChangesFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super CharSequence>, Continuation<? super Unit>, Object> {
    final /* synthetic */ EditText $this_textChangesFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewExtsKt$textChangesFlow$1(EditText editText, Continuation<? super ViewExtsKt$textChangesFlow$1> continuation) {
        super(2, continuation);
        this.$this_textChangesFlow = editText;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ViewExtsKt$textChangesFlow$1 viewExtsKt$textChangesFlow$1 = new ViewExtsKt$textChangesFlow$1(this.$this_textChangesFlow, continuation);
        viewExtsKt$textChangesFlow$1.L$0 = obj;
        return viewExtsKt$textChangesFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super CharSequence> producerScope, Continuation<? super Unit> continuation) {
        return ((ViewExtsKt$textChangesFlow$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            EditText editText = this.$this_textChangesFlow;
            final TextWatcher textWatcher = new TextWatcher() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$textChangesFlow$1$invokeSuspend$$inlined$doOnTextChanged$1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence text, int start, int before, int count) {
                    ProducerScope.this.mo2706trySendJP2dKIU(text);
                }
            };
            editText.addTextChangedListener(textWatcher);
            final EditText editText2 = this.$this_textChangesFlow;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$textChangesFlow$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    editText2.removeTextChangedListener(textWatcher);
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
        return Unit.INSTANCE;
    }
}
