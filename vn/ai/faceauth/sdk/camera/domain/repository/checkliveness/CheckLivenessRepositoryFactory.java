package vn.ai.faceauth.sdk.camera.domain.repository.checkliveness;

import kotlin.Metadata;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.core.factory.Factory;
import vn.ai.faceauth.sdk.domain.repository.LivenessRepository;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\u0006"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/repository/checkliveness/CheckLivenessRepositoryFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "()V", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CheckLivenessRepositoryFactory implements Factory<LivenessRepository<FaceResult>> {
    public static final CheckLivenessRepositoryFactory INSTANCE = new CheckLivenessRepositoryFactory();

    private CheckLivenessRepositoryFactory() {
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public LivenessRepository<FaceResult> create() {
        return new LivenessRepositoryImpl();
    }
}
