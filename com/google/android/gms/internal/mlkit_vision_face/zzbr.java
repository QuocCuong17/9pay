package com.google.android.gms.internal.mlkit_vision_face;

import java.util.Iterator;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzbr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Iterator it) {
        Objects.requireNonNull(it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}
