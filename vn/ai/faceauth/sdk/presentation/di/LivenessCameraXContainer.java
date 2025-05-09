package vn.ai.faceauth.sdk.presentation.di;

import android.app.Application;
import android.content.Context;
import androidx.media3.common.MimeTypes;
import kotlin.Metadata;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.camera.domain.repository.checkliveness.CheckLivenessRepositoryFactory;
import vn.ai.faceauth.sdk.core.resourceprovider.ResourcesProvider;
import vn.ai.faceauth.sdk.core.resourceprovider.ResourcesProviderFactory;
import vn.ai.faceauth.sdk.domain.repository.LivenessRepository;
import vn.ai.faceauth.sdk.presentation.domain.usecase.GetStatusMessageUseCase;
import vn.ai.faceauth.sdk.presentation.navigation.AuthenticationID;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0006\u0010\u0010\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/di/LivenessCameraXContainer;", "", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "(Landroid/app/Application;)V", "provideLivenessEntryPoint", "Lvn/ai/faceauth/sdk/presentation/navigation/AuthenticationID;", "provideContext", "Landroid/content/Context;", "provideGetStatusMessagesUseCase", "Lvn/ai/faceauth/sdk/presentation/domain/usecase/GetStatusMessageUseCase;", "resourcesProvider", "Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;", "provideLivenessRepository", "Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "provideResourceProvider", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessCameraXContainer {
    private final Application application;
    private final AuthenticationID provideLivenessEntryPoint = AuthenticationID.INSTANCE;

    public LivenessCameraXContainer(Application application) {
        this.application = application;
    }

    private final Context provideContext() {
        return this.application.getApplicationContext();
    }

    public static /* synthetic */ GetStatusMessageUseCase provideGetStatusMessagesUseCase$default(LivenessCameraXContainer livenessCameraXContainer, ResourcesProvider resourcesProvider, int i, Object obj) {
        if ((i & 1) != 0) {
            resourcesProvider = livenessCameraXContainer.provideResourceProvider();
        }
        return livenessCameraXContainer.provideGetStatusMessagesUseCase(resourcesProvider);
    }

    public final GetStatusMessageUseCase provideGetStatusMessagesUseCase(ResourcesProvider resourcesProvider) {
        return new GetStatusMessageUseCase(resourcesProvider);
    }

    public final LivenessRepository<FaceResult> provideLivenessRepository() {
        return CheckLivenessRepositoryFactory.INSTANCE.create();
    }

    public final ResourcesProvider provideResourceProvider() {
        return new ResourcesProviderFactory(provideContext()).create();
    }
}
