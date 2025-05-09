package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public enum zziu implements zzbj {
    UNKNOWN(0),
    TRANSLATE(1);

    private final int zzd;

    zziu(int i) {
        this.zzd = i;
    }

    public static zziu zzb(int i) {
        for (zziu zziuVar : values()) {
            if (zziuVar.zzd == i) {
                return zziuVar;
            }
        }
        return UNKNOWN;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbj
    public final int zza() {
        return this.zzd;
    }
}
