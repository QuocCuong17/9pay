package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzmk implements zzmp {
    final List zza;

    public zzmk(Context context, zzmj zzmjVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzmjVar.zzc()) {
            arrayList.add(new zzmy(context, zzmjVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzmp
    public final void zza(zzmh zzmhVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzmp) it.next()).zza(zzmhVar);
        }
    }
}
