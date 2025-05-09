package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzlf extends LazyInstanceMap {
    private zzlf() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzkr zzkrVar = (zzkr) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzkx(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzks(MlKitContext.getInstance().getApplicationContext(), zzkrVar), zzkrVar.zzb());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzlf(zzle zzleVar) {
    }
}
