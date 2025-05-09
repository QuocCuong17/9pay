package vn.ai.faceauth.sdk.camera.core.callback;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.core.extensions.PrimitiveExtensionsKt;
import vn.ai.faceauth.sdk.domain.EditPhotoUseCase;
import vn.ai.faceauth.sdk.domain.model.PhotoResultDomain;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BK\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0016\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u00050\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00052\n\u0010\u000f\u001a\u00060\u0007j\u0002`\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallbackImpl;", "Lvn/ai/faceauth/sdk/camera/core/callback/CameraXCallback;", "onImageSavedAction", "Lkotlin/Function1;", "Lvn/ai/faceauth/sdk/domain/model/PhotoResultDomain;", "", "onErrorAction", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccessFinished", "Lkotlin/Function0;", "editPhotoUseCase", "Lvn/ai/faceauth/sdk/domain/EditPhotoUseCase;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lvn/ai/faceauth/sdk/domain/EditPhotoUseCase;)V", "onError", "exception", "onSuccess", "photoFile", "Ljava/io/File;", "onSuccessFinish", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraXCallbackImpl implements CameraXCallback {
    private final EditPhotoUseCase editPhotoUseCase;
    private final Function1<Exception, Unit> onErrorAction;
    private final Function1<PhotoResultDomain, Unit> onImageSavedAction;
    private final Function0<Unit> onSuccessFinished;

    /* JADX WARN: Multi-variable type inference failed */
    public CameraXCallbackImpl(Function1<? super PhotoResultDomain, Unit> function1, Function1<? super Exception, Unit> function12, Function0<Unit> function0, EditPhotoUseCase editPhotoUseCase) {
        this.onImageSavedAction = function1;
        this.onErrorAction = function12;
        this.onSuccessFinished = function0;
        this.editPhotoUseCase = editPhotoUseCase;
    }

    public /* synthetic */ CameraXCallbackImpl(Function1 function1, Function1 function12, Function0 function0, EditPhotoUseCase editPhotoUseCase, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, function12, function0, (i & 8) != 0 ? CameraModule.INSTANCE.getContainer$trueface_release().provideEditPhotoUseCase() : editPhotoUseCase);
    }

    @Override // vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback
    public void onError(Exception exception) {
        this.onErrorAction.invoke(exception);
    }

    @Override // vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback
    public void onSuccess(File photoFile) {
        try {
            if (photoFile.exists()) {
                this.editPhotoUseCase.editPhotoFile(photoFile);
                this.onImageSavedAction.invoke(new PhotoResultDomain(photoFile.getName(), PrimitiveExtensionsKt.encoderFilePath(photoFile.getPath()), PrimitiveExtensionsKt.toBitmap(photoFile.getPath())));
            } else {
                this.onImageSavedAction.invoke(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.onImageSavedAction.invoke(null);
        }
    }

    @Override // vn.ai.faceauth.sdk.camera.core.callback.CameraXCallback
    public void onSuccessFinish() {
        this.onSuccessFinished.invoke();
    }
}
