package com.google.android.gms.internal.mlkit_vision_face;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzbj extends zzaq implements Serializable {
    final Object zza;
    final Object zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbj(Object obj, Object obj2) {
        this.zza = obj;
        this.zzb = obj2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzaq, java.util.Map.Entry
    public final Object getKey() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzaq, java.util.Map.Entry
    public final Object getValue() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzaq, java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
