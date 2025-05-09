package com.google.android.gms.internal.mlkit_vision_face;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzom extends LazyInstanceMap {
    private zzom() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zznt zzntVar = (zznt) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzoc(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zznu(MlKitContext.getInstance().getApplicationContext(), zzntVar), zzntVar.zzb());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzom(zzol zzolVar) {
    }
}
