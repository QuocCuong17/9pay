package vn.ai.faceauth.sdk.camera.domain.repository.resultliveness;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import vn.ai.faceauth.sdk.domain.model.LivenessCameraXResultDomain;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;
import vn.ai.faceauth.sdk.domain.repository.ResultLivenessRepository;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0016J:\u0010\f\u001a\u00020\u00062\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/repository/resultliveness/ResultLivenessRepositoryImpl;", "Lvn/ai/faceauth/sdk/domain/repository/ResultLivenessRepository;", "Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "resultCallback", "Lkotlin/Function1;", "Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXResultDomain;", "", "(Lkotlin/jvm/functions/Function1;)V", "error", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "finished", "rawImage", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "successLivenessResponse", "", "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ResultLivenessRepositoryImpl implements ResultLivenessRepository<PhotoResultDomain> {
    private final Function1<LivenessCameraXResultDomain, Unit> resultCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public ResultLivenessRepositoryImpl(Function1<? super LivenessCameraXResultDomain, Unit> function1) {
        this.resultCallback = function1;
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.ResultLivenessRepository
    public void error(Exception exception) {
        this.resultCallback.invoke(new LivenessCameraXResultDomain(exception));
    }

    @Override // vn.ai.faceauth.sdk.domain.repository.ResultLivenessRepository
    public void finished(ArrayList<String> rawImage, Map<String, ? extends Object> successLivenessResponse) {
        this.resultCallback.invoke(new LivenessCameraXResultDomain(rawImage, successLivenessResponse, null, 4, null));
    }
}
