package com.google.firebase.firestore;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.firestore.remote.FirebaseClientGrpcMetadataProvider;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class FirestoreRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-fst";

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirestoreMultiDbComponent.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required((Class<?>) Context.class)).add(Dependency.optionalProvider((Class<?>) HeartBeatInfo.class)).add(Dependency.optionalProvider((Class<?>) UserAgentPublisher.class)).add(Dependency.deferred((Class<?>) InternalAuthProvider.class)).add(Dependency.deferred((Class<?>) InteropAppCheckTokenProvider.class)).add(Dependency.optional(FirebaseOptions.class)).factory(new ComponentFactory() { // from class: com.google.firebase.firestore.FirestoreRegistrar$$ExternalSyntheticLambda0
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return FirestoreRegistrar.lambda$getComponents$0(componentContainer);
            }
        }).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirestoreMultiDbComponent lambda$getComponents$0(ComponentContainer componentContainer) {
        return new FirestoreMultiDbComponent((Context) componentContainer.get(Context.class), (FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getDeferred(InternalAuthProvider.class), componentContainer.getDeferred(InteropAppCheckTokenProvider.class), new FirebaseClientGrpcMetadataProvider(componentContainer.getProvider(UserAgentPublisher.class), componentContainer.getProvider(HeartBeatInfo.class), (FirebaseOptions) componentContainer.get(FirebaseOptions.class)));
    }
}
