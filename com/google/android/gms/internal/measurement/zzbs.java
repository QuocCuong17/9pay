package com.google.android.gms.internal.measurement;

import android.os.Build;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzbs {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 31 ? PDButton.FLAG_RADIOS_IN_UNISON : 0;
    }
}
