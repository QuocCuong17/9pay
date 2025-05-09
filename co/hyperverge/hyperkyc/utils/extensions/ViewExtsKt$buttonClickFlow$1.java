package co.hyperverge.hyperkyc.utils.extensions;

import android.view.View;
import android.widget.Button;
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
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroid/widget/Button;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$buttonClickFlow$1", f = "ViewExts.kt", i = {}, l = {CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class ViewExtsKt$buttonClickFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super Button>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Button $this_buttonClickFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewExtsKt$buttonClickFlow$1(Button button, Continuation<? super ViewExtsKt$buttonClickFlow$1> continuation) {
        super(2, continuation);
        this.$this_buttonClickFlow = button;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ViewExtsKt$buttonClickFlow$1 viewExtsKt$buttonClickFlow$1 = new ViewExtsKt$buttonClickFlow$1(this.$this_buttonClickFlow, continuation);
        viewExtsKt$buttonClickFlow$1.L$0 = obj;
        return viewExtsKt$buttonClickFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super Button> producerScope, Continuation<? super Unit> continuation) {
        return ((ViewExtsKt$buttonClickFlow$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final Button button = this.$this_buttonClickFlow;
            this.$this_buttonClickFlow.setOnClickListener(new View.OnClickListener() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$buttonClickFlow$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProducerScope.this.mo2706trySendJP2dKIU(button);
                }
            });
            final Button button2 = this.$this_buttonClickFlow;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: co.hyperverge.hyperkyc.utils.extensions.ViewExtsKt$buttonClickFlow$1.1
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
                    button2.setOnClickListener(null);
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
