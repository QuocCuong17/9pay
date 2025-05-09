package co.hyperverge.hyperkyc.ui.viewmodels;

import co.hyperverge.hyperkyc.data.models.KycCountry;
import co.hyperverge.hyperkyc.data.models.VideoStatementV2Config;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoStatementV2VM.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "co.hyperverge.hyperkyc.ui.viewmodels.VideoStatementV2VM$makeCheckCalls$2$sttCall$1", f = "VideoStatementV2VM.kt", i = {}, l = {BaselineTIFFTagSet.TAG_TRANSFER_RANGE}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class VideoStatementV2VM$makeCheckCalls$2$sttCall$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<String, Integer, String, Unit> $finishWithErrorCallback;
    int label;
    final /* synthetic */ VideoStatementV2VM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public VideoStatementV2VM$makeCheckCalls$2$sttCall$1(VideoStatementV2VM videoStatementV2VM, Function3<? super String, ? super Integer, ? super String, Unit> function3, Continuation<? super VideoStatementV2VM$makeCheckCalls$2$sttCall$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStatementV2VM;
        this.$finishWithErrorCallback = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStatementV2VM$makeCheckCalls$2$sttCall$1(this.this$0, this.$finishWithErrorCallback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStatementV2VM$makeCheckCalls$2$sttCall$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VideoStatementV2Config videoStatementV2Config;
        boolean asBoolean;
        VideoStatementV2Config videoStatementV2Config2;
        String str;
        VideoStatementV2Config videoStatementV2Config3;
        String str2;
        String base64;
        String str3;
        VideoStatementV2Config videoStatementV2Config4;
        Object validateCheck;
        MainVM mainVM;
        MainVM mainVM2;
        MainVM mainVM3;
        String str4;
        WorkflowModule.Properties.StatementV2.ChecksV2 checks;
        WorkflowModule.Properties.StatementV2.ChecksV2.CheckV2 speechToTextMatch;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoStatementV2VM videoStatementV2VM = this.this$0;
            videoStatementV2Config = videoStatementV2VM.vsConfigV2;
            WorkflowModule.Properties.StatementV2 statement = videoStatementV2Config.getStatement();
            asBoolean = videoStatementV2VM.asBoolean((statement == null || (checks = statement.getChecks()) == null || (speechToTextMatch = checks.getSpeechToTextMatch()) == null) ? null : speechToTextMatch.getEnable(), false);
            if (asBoolean) {
                videoStatementV2Config2 = this.this$0.vsConfigV2;
                if (videoStatementV2Config2.getSttAPI() != null) {
                    str = this.this$0.audioUri;
                    if (str != null) {
                        videoStatementV2Config3 = this.this$0.vsConfigV2;
                        List mutableList = CollectionsKt.toMutableList((Collection) videoStatementV2Config3.getSttAPI().getParameters());
                        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                        Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
                        Iterator it = mutableList.iterator();
                        while (it.hasNext()) {
                            String name = ((WorkflowModule.Properties.RequestParam) it.next()).getName();
                            int hashCode = name.hashCode();
                            if (hashCode != -1609445833) {
                                if (hashCode != -1477067101) {
                                    if (hashCode == 705922866 && name.equals(WorkflowModule.Properties.VideoStatementV2API.TEXT_LANGUAGE_CODE)) {
                                        booleanRef2.element = true;
                                    }
                                } else if (name.equals(WorkflowModule.Properties.VideoStatementV2API.COUNTRY_CODE)) {
                                    booleanRef.element = true;
                                }
                            } else if (name.equals(WorkflowModule.Properties.VideoStatementV2API.PERFORM_TEXT_MATCH)) {
                                booleanRef3.element = true;
                            }
                        }
                        if (!booleanRef.element) {
                            mainVM3 = this.this$0.mainVM;
                            KycCountry selectedCountry = mainVM3.getSelectedCountry();
                            if (selectedCountry == null || (str4 = selectedCountry.getId()) == null) {
                                str4 = "";
                            }
                            mutableList.add(new WorkflowModule.Properties.RequestParam(WorkflowModule.Properties.VideoStatementV2API.COUNTRY_CODE, str4, null, 4, null));
                        }
                        if (!booleanRef2.element) {
                            mainVM = this.this$0.mainVM;
                            mainVM2 = this.this$0.mainVM;
                            mutableList.add(new WorkflowModule.Properties.RequestParam(WorkflowModule.Properties.VideoStatementV2API.TEXT_LANGUAGE_CODE, mainVM.getLanguageToBeUsed$hyperkyc_release(mainVM2.getHyperKycConfig$hyperkyc_release()), null, 4, null));
                        }
                        if (!booleanRef3.element) {
                            mutableList.add(new WorkflowModule.Properties.RequestParam(WorkflowModule.Properties.VideoStatementV2API.PERFORM_TEXT_MATCH, "yes", null, 4, null));
                        }
                        VideoStatementV2VM videoStatementV2VM2 = this.this$0;
                        str2 = videoStatementV2VM2.audioUri;
                        Intrinsics.checkNotNull(str2);
                        base64 = videoStatementV2VM2.toBase64(str2);
                        mutableList.add(new WorkflowModule.Properties.RequestParam("audio", base64, null, 4, null));
                        str3 = this.this$0.statementText;
                        mutableList.add(new WorkflowModule.Properties.RequestParam("groundTruth", str3, null, 4, null));
                        VideoStatementV2VM videoStatementV2VM3 = this.this$0;
                        videoStatementV2Config4 = videoStatementV2VM3.vsConfigV2;
                        this.label = 1;
                        validateCheck = videoStatementV2VM3.validateCheck(VideoStatementV2VM.createAPICallState$default(videoStatementV2VM3, videoStatementV2Config4.getSttAPI(), WorkflowRequestType.JSON, null, mutableList, 4, null), VideoStatementV2VM.RequestType.SPEECH_TO_TEXT_MATCH, this.$finishWithErrorCallback, this);
                        if (validateCheck == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.this$0.isSTTCheckFailed = true;
                    }
                } else {
                    this.$finishWithErrorCallback.invoke("error", Boxing.boxInt(104), "API call failed!");
                }
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
