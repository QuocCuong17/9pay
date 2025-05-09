package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
final class zzam extends zzae {
    private final zzao zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzam(zzao zzaoVar, int i) {
        super(zzaoVar.size(), i);
        this.zza = zzaoVar;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzae
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
