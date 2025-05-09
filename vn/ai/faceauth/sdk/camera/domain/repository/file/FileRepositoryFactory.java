package vn.ai.faceauth.sdk.camera.domain.repository.file;

import android.content.Context;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import vn.ai.faceauth.sdk.camera.di.CameraModule;
import vn.ai.faceauth.sdk.core.factory.Factory;
import vn.ai.faceauth.sdk.domain.model.StorageTypeDomain;
import vn.ai.faceauth.sdk.domain.repository.FileRepository;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0002H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/repository/file/FileRepositoryFactory;", "Lvn/ai/faceauth/sdk/core/factory/Factory;", "Lvn/ai/faceauth/sdk/domain/repository/FileRepository;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "storageType", "Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "getStorageType", "()Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "setStorageType", "(Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;)V", "create", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FileRepositoryFactory implements Factory<FileRepository> {
    public static final FileRepositoryFactory INSTANCE = new FileRepositoryFactory();

    /* renamed from: context$delegate, reason: from kotlin metadata */
    private static final Lazy context = LazyKt.lazy(new Function0<Context>() { // from class: vn.ai.faceauth.sdk.camera.domain.repository.file.FileRepositoryFactory$context$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Context invoke() {
            return CameraModule.INSTANCE.getContainer$trueface_release().provideContext();
        }
    });
    private static StorageTypeDomain storageType = StorageTypeDomain.INTERNAL;

    private FileRepositoryFactory() {
    }

    private final Context getContext() {
        return (Context) context.getValue();
    }

    @Override // vn.ai.faceauth.sdk.core.factory.Factory
    public FileRepository create() {
        return new FileRepositoryImpl(storageType, getContext());
    }

    public final StorageTypeDomain getStorageType() {
        return storageType;
    }

    public final void setStorageType(StorageTypeDomain storageTypeDomain) {
        storageType = storageTypeDomain;
    }
}
