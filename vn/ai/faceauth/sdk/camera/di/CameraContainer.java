package vn.ai.faceauth.sdk.camera.di;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;
import vn.ai.faceauth.sdk.camera.CameraX;
import vn.ai.faceauth.sdk.camera.CameraXImpl;
import vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback;
import vn.ai.faceauth.sdk.camera.core.detector.VisionFaceDetector;
import vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessor;
import vn.ai.faceauth.sdk.camera.core.processor.face.FaceFrameProcessorFactory;
import vn.ai.faceauth.sdk.camera.domain.repository.file.FileRepositoryFactory;
import vn.ai.faceauth.sdk.camera.domain.usecase.EditPhotoUseCaseFactory;
import vn.ai.faceauth.sdk.domain.EditPhotoUseCase;
import vn.ai.faceauth.sdk.domain.model.CameraSettingsDomain;
import vn.ai.faceauth.sdk.domain.model.StorageTypeDomain;
import vn.ai.faceauth.sdk.domain.repository.FileRepository;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u001a¨\u0006\u001b"}, d2 = {"Lvn/ai/faceauth/sdk/camera/di/CameraContainer;", "", "()V", "provideCameraX", "Lvn/ai/faceauth/sdk/camera/CameraX;", "cameraSettings", "Lvn/ai/faceauth/sdk/domain/model/CameraSettingsDomain;", "cameraXCallback", "Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "provideContext", "Landroid/content/Context;", "provideCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "provideEditPhotoUseCase", "Lvn/ai/faceauth/sdk/domain/EditPhotoUseCase;", "provideExecutorService", "Ljava/util/concurrent/ExecutorService;", "provideFaceFrameProcessor", "Lvn/ai/faceauth/sdk/camera/core/processor/face/FaceFrameProcessor;", "provideFileRepository", "Lvn/ai/faceauth/sdk/domain/repository/FileRepository;", "storageType", "Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "provideVisionFaceDetector", "Lvn/ai/faceauth/sdk/camera/core/detector/VisionFaceDetector;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraContainer {
    private final FileRepository provideFileRepository(StorageTypeDomain storageType) {
        FileRepositoryFactory fileRepositoryFactory = FileRepositoryFactory.INSTANCE;
        fileRepositoryFactory.setStorageType(storageType);
        return fileRepositoryFactory.create();
    }

    public final CameraX provideCameraX(CameraSettingsDomain cameraSettings, CameraXCallback cameraXCallback, LifecycleOwner lifecycleOwner) {
        return new CameraXImpl(cameraSettings, cameraXCallback, lifecycleOwner, provideFileRepository(cameraSettings.getStorageType()));
    }

    public final Context provideContext() {
        return CameraModule.INSTANCE.getApplication().getApplicationContext();
    }

    public final CoroutineScope provideCoroutineScope() {
        return LifecycleOwnerKt.getLifecycleScope(CameraModule.INSTANCE.getLifecycleOwner$trueface_release());
    }

    public final EditPhotoUseCase provideEditPhotoUseCase() {
        return EditPhotoUseCaseFactory.INSTANCE.create();
    }

    public final ExecutorService provideExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    public final FaceFrameProcessor provideFaceFrameProcessor() {
        return FaceFrameProcessorFactory.INSTANCE.create();
    }

    public final VisionFaceDetector provideVisionFaceDetector() {
        return new VisionFaceDetector();
    }
}
