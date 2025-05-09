package vn.ai.faceauth.sdk.camera;

import android.util.Size;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.List;
import java.util.concurrent.Future;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import vn.ai.faceauth.sdk.camera.core.analyzer.AnalyzeProvider;
import vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.camera.domain.model.FaceResult;
import vn.ai.faceauth.sdk.domain.model.CameraSettingsDomain;
import vn.ai.faceauth.sdk.domain.model.exceptions.LivenessCameraXException;
import vn.ai.faceauth.sdk.domain.repository.FileRepository;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\b\u0010 \u001a\u00020\u0000H\u0016J\u0014\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001e0\"H\u0016J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020\bH\u0016J&\u0010&\u001a\u00020\u001c2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020+H\u0016J\u0018\u0010.\u001a\u00020\u001c2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u000100H\u0016R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lvn/ai/faceauth/sdk/camera/CameraXImpl;", "Lvn/ai/faceauth/sdk/camera/CameraX;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "settings", "Lvn/ai/faceauth/sdk/domain/model/CameraSettingsDomain;", "cameraXCallback", "Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "fileRepository", "Lvn/ai/faceauth/sdk/domain/repository/FileRepository;", "(Lvn/ai/faceauth/sdk/domain/model/CameraSettingsDomain;Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;Landroidx/lifecycle/LifecycleOwner;Lvn/ai/faceauth/sdk/domain/repository/FileRepository;)V", "analyzerProvider", "Lvn/ai/faceauth/sdk/camera/core/analyzer/AnalyzeProvider$Builder;", "getAnalyzerProvider", "()Lvn/ai/faceauth/sdk/camera/core/analyzer/AnalyzeProvider$Builder;", "analyzerProvider$delegate", "Lkotlin/Lazy;", "camera", "Landroidx/camera/core/Camera;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "getImageCapture", "()Landroidx/camera/core/ImageCapture;", "imageCapture$delegate", "deleteAllPictures", "", "finishStep", "", "getAllPictures", "", "", "getLifecycleObserver", "observeFaceList", "Lkotlinx/coroutines/flow/Flow;", "Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "onDestroy", "owner", "setupCamera", "cameraProviderFuture", "Ljava/util/concurrent/Future;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "previewView", "Landroidx/camera/view/PreviewView;", "startCamera", "cameraPreviewView", "takePicture", "myCallback", "Lkotlin/Function0;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXImpl implements CameraX, DefaultLifecycleObserver {
    private Camera camera;
    private final CameraXCallback cameraXCallback;
    private final FileRepository fileRepository;
    private final LifecycleOwner lifecycleOwner;
    private final CameraSettingsDomain settings;

    /* renamed from: imageCapture$delegate, reason: from kotlin metadata */
    private final Lazy imageCapture = LazyKt.lazy(new Function0<ImageCapture>() { // from class: vn.ai.faceauth.sdk.camera.CameraXImpl$imageCapture$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageCapture invoke() {
            ImageCapture build = new ImageCapture.Builder().setTargetResolution(new Size(600, 800)).build();
            build.setTargetRotation(0);
            return build;
        }
    });

    /* renamed from: analyzerProvider$delegate, reason: from kotlin metadata */
    private final Lazy analyzerProvider = LazyKt.lazy(new Function0<AnalyzeProvider.Builder>() { // from class: vn.ai.faceauth.sdk.camera.CameraXImpl$analyzerProvider$2
        @Override // kotlin.jvm.functions.Function0
        public final AnalyzeProvider.Builder invoke() {
            return new AnalyzeProvider.Builder();
        }
    });

    public CameraXImpl(CameraSettingsDomain cameraSettingsDomain, CameraXCallback cameraXCallback, LifecycleOwner lifecycleOwner, FileRepository fileRepository) {
        this.settings = cameraSettingsDomain;
        this.cameraXCallback = cameraXCallback;
        this.lifecycleOwner = lifecycleOwner;
        this.fileRepository = fileRepository;
    }

    private final AnalyzeProvider.Builder getAnalyzerProvider() {
        return (AnalyzeProvider.Builder) this.analyzerProvider.getValue();
    }

    private final ImageCapture getImageCapture() {
        return (ImageCapture) this.imageCapture.getValue();
    }

    private final void setupCamera(Future<ProcessCameraProvider> cameraProviderFuture, PreviewView previewView, LifecycleOwner lifecycleOwner) {
        this.camera = null;
        ProcessCameraProvider processCameraProvider = cameraProviderFuture.get();
        Preview build = new Preview.Builder().build();
        build.setSurfaceProvider(previewView.getSurfaceProvider());
        ImageAnalysis build2 = getAnalyzerProvider().build();
        try {
            processCameraProvider.unbindAll();
            this.camera = processCameraProvider.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_FRONT_CAMERA, build, getImageCapture(), build2);
        } catch (Exception e) {
            this.cameraXCallback.onError(new LivenessCameraXException.StartCameraException(e.getMessage(), e.getCause()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startCamera$lambda-0, reason: not valid java name */
    public static final void m2944startCamera$lambda0(CameraXImpl cameraXImpl, ListenableFuture listenableFuture, PreviewView previewView) {
        cameraXImpl.setupCamera(listenableFuture, previewView, cameraXImpl.lifecycleOwner);
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public boolean deleteAllPictures() {
        return this.fileRepository.deleteStorageFiles();
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public void finishStep() {
        this.cameraXCallback.onSuccessFinish();
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public List<String> getAllPictures() {
        return this.fileRepository.getPathOfAllPhotos();
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public CameraXImpl getLifecycleObserver() {
        return this;
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public Flow<List<FaceResult>> observeFaceList() {
        return getAnalyzerProvider().getFaceFrameProcessor().observeFaceList();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        getAnalyzerProvider().getCameraExecutors().shutdown();
        this.fileRepository.deleteStorageFiles();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public void startCamera(final PreviewView cameraPreviewView) {
        CameraModule cameraModule = CameraModule.INSTANCE;
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(cameraModule.getApplication());
        processCameraProvider.addListener(new Runnable() { // from class: vn.ai.faceauth.sdk.camera.CameraXImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CameraXImpl.m2944startCamera$lambda0(CameraXImpl.this, processCameraProvider, cameraPreviewView);
            }
        }, ContextCompat.getMainExecutor(cameraModule.getApplication()));
    }

    @Override // vn.ai.faceauth.sdk.camera.CameraX
    public void takePicture(final Function0<Unit> myCallback) {
        final File photoFile = this.fileRepository.getPhotoFile();
        getImageCapture().m129lambda$takePicture$4$androidxcameracoreImageCapture(new ImageCapture.OutputFileOptions.Builder(photoFile).build(), ContextCompat.getMainExecutor(CameraModule.INSTANCE.getApplication()), new ImageCapture.OnImageSavedCallback() { // from class: vn.ai.faceauth.sdk.camera.CameraXImpl$takePicture$1
            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onError(ImageCaptureException exception) {
                CameraXCallback cameraXCallback;
                cameraXCallback = CameraXImpl.this.cameraXCallback;
                cameraXCallback.onError(exception);
            }

            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                CameraXCallback cameraXCallback;
                cameraXCallback = CameraXImpl.this.cameraXCallback;
                cameraXCallback.onSuccess(photoFile);
                Function0<Unit> function0 = myCallback;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        });
    }
}
