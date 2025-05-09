package vn.ai.faceauth.sdk.camera.domain.usecase;

import android.content.Context;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.core.factory.Factory;
import vn.ai.faceauth.sdk.domain.EditPhotoUseCase;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u0002H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/usecase/EditPhotoUseCaseFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/domain/EditPhotoUseCase;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class EditPhotoUseCaseFactory implements Factory<EditPhotoUseCase> {
    public static final EditPhotoUseCaseFactory INSTANCE = new EditPhotoUseCaseFactory();

    /* renamed from: context$delegate, reason: from kotlin metadata */
    private static final Lazy context = LazyKt.lazy(new Function0<Context>() { // from class: vn.ai.faceauth.sdk.camera.domain.usecase.EditPhotoUseCaseFactory$context$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Context invoke() {
            return CameraModule.INSTANCE.getContainer$trueface_release().provideContext();
        }
    });

    private EditPhotoUseCaseFactory() {
    }

    private final Context getContext() {
        return (Context) context.getValue();
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public EditPhotoUseCase create() {
        return new EditPhotoUseCaseImpl(getContext());
    }
}
