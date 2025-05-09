package vn.ai.faceauth.sdk.camera.domain.repository.resultliveness;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import vn.ai.faceauth.sdk.core.factory.Factory;
import vn.ai.faceauth.sdk.domain.model.LivenessCameraXResultDomain;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;
import vn.ai.faceauth.sdk.domain.repository.ResultLivenessRepository;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/repository/resultliveness/ResultLivenessRepositoryFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/domain/repository/ResultLivenessRepository;", "Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "()V", "resultCallback", "Lkotlin/Function1;", "Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXResultDomain;", "", "getResultCallback", "()Lkotlin/jvm/functions/Function1;", "setResultCallback", "(Lkotlin/jvm/functions/Function1;)V", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ResultLivenessRepositoryFactory implements Factory<ResultLivenessRepository<PhotoResultDomain>> {
    public static final ResultLivenessRepositoryFactory INSTANCE = new ResultLivenessRepositoryFactory();
    private static Function1<? super LivenessCameraXResultDomain, Unit> resultCallback = new Function1<LivenessCameraXResultDomain, Unit>() { // from class: vn.ai.faceauth.sdk.camera.domain.repository.resultliveness.ResultLivenessRepositoryFactory$resultCallback$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(LivenessCameraXResultDomain livenessCameraXResultDomain) {
            invoke2(livenessCameraXResultDomain);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(LivenessCameraXResultDomain livenessCameraXResultDomain) {
        }
    };

    private ResultLivenessRepositoryFactory() {
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public ResultLivenessRepository<PhotoResultDomain> create() {
        return new ResultLivenessRepositoryImpl(resultCallback);
    }

    public final Function1<LivenessCameraXResultDomain, Unit> getResultCallback() {
        return resultCallback;
    }

    public final void setResultCallback(Function1<? super LivenessCameraXResultDomain, Unit> function1) {
        resultCallback = function1;
    }
}
