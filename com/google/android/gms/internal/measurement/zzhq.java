package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzhq {
    private static final ArrayMap zza = new ArrayMap();

    /* JADX WARN: Multi-variable type inference failed */
    public static synchronized Uri zza(String str) {
        synchronized (zzhq.class) {
            ArrayMap arrayMap = zza;
            Uri uri = (Uri) arrayMap.get("com.google.android.gms.measurement");
            if (uri != null) {
                return uri;
            }
            Uri parse = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(Uri.encode("com.google.android.gms.measurement"))));
            arrayMap.put("com.google.android.gms.measurement", parse);
            return parse;
        }
    }
}
