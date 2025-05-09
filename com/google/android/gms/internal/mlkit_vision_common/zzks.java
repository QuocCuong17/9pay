package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzks implements zzkw {
    final List zza;

    public zzks(Context context, zzkr zzkrVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzkrVar.zzc()) {
            arrayList.add(new zzld(context, zzkrVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzkw
    public final void zza(zzkp zzkpVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzkw) it.next()).zza(zzkpVar);
        }
    }
}
