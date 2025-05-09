package androidx.window.area;

import androidx.window.extensions.area.ExtensionWindowAreaStatus;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.extensions.core.util.function.Consumer;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WindowAreaControllerImpl.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "Landroidx/window/area/WindowAreaInfo;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1", f = "WindowAreaControllerImpl.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class WindowAreaControllerImpl$windowAreaInfos$1 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends WindowAreaInfo>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WindowAreaControllerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowAreaControllerImpl$windowAreaInfos$1(WindowAreaControllerImpl windowAreaControllerImpl, Continuation<? super WindowAreaControllerImpl$windowAreaInfos$1> continuation) {
        super(2, continuation);
        this.this$0 = windowAreaControllerImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WindowAreaControllerImpl$windowAreaInfos$1 windowAreaControllerImpl$windowAreaInfos$1 = new WindowAreaControllerImpl$windowAreaInfos$1(this.this$0, continuation);
        windowAreaControllerImpl$windowAreaInfos$1.L$0 = obj;
        return windowAreaControllerImpl$windowAreaInfos$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super List<? extends WindowAreaInfo>> producerScope, Continuation<? super Unit> continuation) {
        return invoke2((ProducerScope<? super List<WindowAreaInfo>>) producerScope, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(ProducerScope<? super List<WindowAreaInfo>> producerScope, Continuation<? super Unit> continuation) {
        return ((WindowAreaControllerImpl$windowAreaInfos$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        WindowAreaComponent windowAreaComponent;
        int i;
        WindowAreaComponent windowAreaComponent2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WindowAreaControllerImpl windowAreaControllerImpl = this.this$0;
            final Consumer consumer = new Consumer() { // from class: androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1$$ExternalSyntheticLambda1
                @Override // androidx.window.extensions.core.util.function.Consumer
                public final void accept(Object obj2) {
                    WindowAreaControllerImpl$windowAreaInfos$1.invokeSuspend$lambda$0(WindowAreaControllerImpl.this, producerScope, (Integer) obj2);
                }
            };
            final WindowAreaControllerImpl windowAreaControllerImpl2 = this.this$0;
            final Consumer consumer2 = new Consumer() { // from class: androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1$$ExternalSyntheticLambda0
                @Override // androidx.window.extensions.core.util.function.Consumer
                public final void accept(Object obj2) {
                    WindowAreaControllerImpl$windowAreaInfos$1.invokeSuspend$lambda$1(WindowAreaControllerImpl.this, producerScope, (ExtensionWindowAreaStatus) obj2);
                }
            };
            windowAreaComponent = this.this$0.windowAreaComponent;
            windowAreaComponent.addRearDisplayStatusListener(consumer);
            i = this.this$0.vendorApiLevel;
            if (i > 2) {
                windowAreaComponent2 = this.this$0.windowAreaComponent;
                windowAreaComponent2.addRearDisplayPresentationStatusListener(consumer2);
            }
            final WindowAreaControllerImpl windowAreaControllerImpl3 = this.this$0;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() { // from class: androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1.1
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
                    WindowAreaComponent windowAreaComponent3;
                    int i3;
                    WindowAreaComponent windowAreaComponent4;
                    windowAreaComponent3 = WindowAreaControllerImpl.this.windowAreaComponent;
                    windowAreaComponent3.removeRearDisplayStatusListener(consumer);
                    i3 = WindowAreaControllerImpl.this.vendorApiLevel;
                    if (i3 > 2) {
                        windowAreaComponent4 = WindowAreaControllerImpl.this.windowAreaComponent;
                        windowAreaComponent4.removeRearDisplayPresentationStatusListener(consumer2);
                    }
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope, Integer status) {
        HashMap hashMap;
        Intrinsics.checkNotNullExpressionValue(status, "status");
        windowAreaControllerImpl.updateRearDisplayAvailability(status.intValue());
        SendChannel channel = producerScope.getChannel();
        hashMap = windowAreaControllerImpl.currentWindowAreaInfoMap;
        Collection values = hashMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "currentWindowAreaInfoMap.values");
        channel.mo2706trySendJP2dKIU(CollectionsKt.toList(values));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope, ExtensionWindowAreaStatus extensionWindowAreaStatus) {
        HashMap hashMap;
        Intrinsics.checkNotNullExpressionValue(extensionWindowAreaStatus, "extensionWindowAreaStatus");
        windowAreaControllerImpl.updateRearDisplayPresentationAvailability(extensionWindowAreaStatus);
        SendChannel channel = producerScope.getChannel();
        hashMap = windowAreaControllerImpl.currentWindowAreaInfoMap;
        Collection values = hashMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "currentWindowAreaInfoMap.values");
        channel.mo2706trySendJP2dKIU(CollectionsKt.toList(values));
    }
}
