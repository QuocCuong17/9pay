package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class StorageRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-gcs";
    Qualified<Executor> blockingExecutor = Qualified.qualified(Blocking.class, Executor.class);
    Qualified<Executor> uiExecutor = Qualified.qualified(UiThread.class, Executor.class);

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseStorageComponent.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required(this.blockingExecutor)).add(Dependency.required(this.uiExecutor)).add(Dependency.optionalProvider((Class<?>) InternalAuthProvider.class)).add(Dependency.optionalProvider((Class<?>) InteropAppCheckTokenProvider.class)).factory(new ComponentFactory() { // from class: com.google.firebase.storage.StorageRegistrar$$ExternalSyntheticLambda0
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return StorageRegistrar.this.m912xcb247b6b(componentContainer);
            }
        }).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }

    /* renamed from: lambda$getComponents$0$com-google-firebase-storage-StorageRegistrar, reason: not valid java name */
    public /* synthetic */ FirebaseStorageComponent m912xcb247b6b(ComponentContainer componentContainer) {
        return new FirebaseStorageComponent((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(InternalAuthProvider.class), componentContainer.getProvider(InteropAppCheckTokenProvider.class), (Executor) componentContainer.get(this.blockingExecutor), (Executor) componentContainer.get(this.uiExecutor));
    }
}
