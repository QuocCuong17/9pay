package com.google.android.gms.internal.common;

import android.os.Build;
import androidx.core.view.accessibility.AccessibilityEventCompat;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes3.dex */
public final class zzd {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 23 ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 0;
    }
}
