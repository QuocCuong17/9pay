package vn.ai.faceauth.sdk.domain.model;

import kotlin.Metadata;
import vn.ai.faceauth.sdk.presentation.domain.model.LivenessCameraXError;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toPresentation", "Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessCameraXError;", "Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXErrorDomain;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessCameraXErrorDomainKt {
    public static final LivenessCameraXError toPresentation(LivenessCameraXErrorDomain livenessCameraXErrorDomain) {
        return new LivenessCameraXError(livenessCameraXErrorDomain.getMessage(), livenessCameraXErrorDomain.getCause(), livenessCameraXErrorDomain.getException());
    }
}
