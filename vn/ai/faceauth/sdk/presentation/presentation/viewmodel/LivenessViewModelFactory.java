package vn.ai.faceauth.sdk.presentation.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.core.resourceprovider.ResourcesProvider;
import vn.ai.faceauth.sdk.domain.repository.LivenessRepository;
import vn.ai.faceauth.sdk.presentation.di.LibraryModule;
import vn.ai.faceauth.sdk.presentation.di.LivenessCameraXContainer;
import vn.ai.faceauth.sdk.presentation.domain.usecase.GetStatusMessageUseCase;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/viewmodel/LivenessViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "resourcesProvider", "Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;", "livenessRepository", "Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "getStatusMessageUseCase", "Lvn/ai/faceauth/sdk/presentation/domain/usecase/GetStatusMessageUseCase;", "(Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;Lvn/ai/faceauth/sdk/domain/repository/LivenessRepository;Lvn/ai/faceauth/sdk/presentation/domain/usecase/GetStatusMessageUseCase;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessViewModelFactory implements ViewModelProvider.Factory {
    private final GetStatusMessageUseCase getStatusMessageUseCase;
    private final LivenessRepository<FaceResult> livenessRepository;
    private final ResourcesProvider resourcesProvider;

    public LivenessViewModelFactory() {
        this(null, null, null, 7, null);
    }

    public LivenessViewModelFactory(ResourcesProvider resourcesProvider, LivenessRepository<FaceResult> livenessRepository, GetStatusMessageUseCase getStatusMessageUseCase) {
        this.resourcesProvider = resourcesProvider;
        this.livenessRepository = livenessRepository;
        this.getStatusMessageUseCase = getStatusMessageUseCase;
    }

    public /* synthetic */ LivenessViewModelFactory(ResourcesProvider resourcesProvider, LivenessRepository livenessRepository, GetStatusMessageUseCase getStatusMessageUseCase, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? LibraryModule.INSTANCE.getContainer().provideResourceProvider() : resourcesProvider, (i & 2) != 0 ? LibraryModule.INSTANCE.getContainer().provideLivenessRepository() : livenessRepository, (i & 4) != 0 ? LivenessCameraXContainer.provideGetStatusMessagesUseCase$default(LibraryModule.INSTANCE.getContainer(), null, 1, null) : getStatusMessageUseCase);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LivenessViewModel.class)) {
            return new LivenessViewModel(this.resourcesProvider, this.livenessRepository, this.getStatusMessageUseCase);
        }
        throw new IllegalArgumentException(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP));
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public /* synthetic */ ViewModel create(Class cls, CreationExtras creationExtras) {
        return ViewModelProvider.Factory.CC.$default$create(this, cls, creationExtras);
    }
}
