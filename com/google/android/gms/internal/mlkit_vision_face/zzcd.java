package com.google.android.gms.internal.mlkit_vision_face;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzcd extends zzbn {
    final /* synthetic */ zzce zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcd(zzce zzceVar) {
        this.zza = zzceVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzab.zza(i, i2, FirebaseAnalytics.Param.INDEX);
        zzce zzceVar = this.zza;
        int i3 = i + i;
        objArr = zzceVar.zzb;
        Object obj = objArr[i3];
        obj.getClass();
        objArr2 = zzceVar.zzb;
        Object obj2 = objArr2[i3 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzc;
        return i;
    }
}
