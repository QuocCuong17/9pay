package com.google.mlkit.vision.face.internal;

import com.google.android.gms.internal.mlkit_vision_face.zzbn;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes4.dex */
public class FaceRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzbn.zzi(Component.builder(zze.class).add(Dependency.required((Class<?>) MlKitContext.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.vision.face.internal.zzk
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = FaceRegistrar.zza;
                return new zze((MlKitContext) componentContainer.get(MlKitContext.class));
            }
        }).build(), Component.builder(zzc.class).add(Dependency.required((Class<?>) zze.class)).add(Dependency.required((Class<?>) ExecutorSelector.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.vision.face.internal.zzl
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzc((zze) componentContainer.get(zze.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class));
            }
        }).build());
    }
}
