package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hyperkyc.core.hv.models.HSRemoteConfig;
import co.hyperverge.hyperkyc.ui.models.NetworkUIState;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainVM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.MainVM$pushLogsToRemote$4", f = "MainVM.kt", i = {}, l = {3845}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MainVM$pushLogsToRemote$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $baseS3Url;
    final /* synthetic */ File $logFile;
    int label;
    final /* synthetic */ MainVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainVM$pushLogsToRemote$4(MainVM mainVM, String str, File file, Continuation<? super MainVM$pushLogsToRemote$4> continuation) {
        super(2, continuation);
        this.this$0 = mainVM;
        this.$baseS3Url = str;
        this.$logFile = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainVM$pushLogsToRemote$4(this.this$0, this.$baseS3Url, this.$logFile, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainVM$pushLogsToRemote$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MainVM mainVM = this.this$0;
            Flow<NetworkUIState<HSRemoteConfig>> fetchRemoteConfig = mainVM.fetchRemoteConfig(mainVM.getHyperKycConfig$hyperkyc_release());
            final MainVM mainVM2 = this.this$0;
            final String str = this.$baseS3Url;
            final File file = this.$logFile;
            this.label = 1;
            if (fetchRemoteConfig.collect(new FlowCollector() { // from class: co.hyperverge.hyperkyc.ui.viewmodels.MainVM$pushLogsToRemote$4.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetworkUIState<HSRemoteConfig>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetworkUIState<HSRemoteConfig> networkUIState, Continuation<? super Unit> continuation) {
                    String logUploadBaseUrl;
                    if (!(networkUIState instanceof NetworkUIState.Loading)) {
                        boolean z = true;
                        if (!(networkUIState instanceof NetworkUIState.Failed) && networkUIState != null) {
                            z = false;
                        }
                        if (z) {
                            MainVM mainVM3 = MainVM.this;
                            logUploadBaseUrl = mainVM3.getLogUploadBaseUrl(str + "/default", file);
                            mainVM3.makeAPICallToPushLogsToRemote(logUploadBaseUrl, file);
                        } else if (networkUIState instanceof NetworkUIState.Success) {
                            MainVM.pushLogsToRemote$uploadLogsIfNeeded(MainVM.this, str, file, ((HSRemoteConfig) ((NetworkUIState.Success) networkUIState).getData()).getUploadLogs());
                        } else {
                            boolean z2 = networkUIState instanceof NetworkUIState.NetworkFailure;
                        }
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
        return Unit.INSTANCE;
    }
}
