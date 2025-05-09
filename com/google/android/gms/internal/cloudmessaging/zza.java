package com.google.android.gms.internal.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
/* loaded from: classes3.dex */
public final class zza {
    public static final int zza;

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = PDButton.FLAG_RADIOS_IN_UNISON;
        if (i < 31 && (Build.VERSION.SDK_INT < 30 || Build.VERSION.CODENAME.length() != 1 || Build.VERSION.CODENAME.charAt(0) < 'S' || Build.VERSION.CODENAME.charAt(0) > 'Z')) {
            i2 = 0;
        }
        zza = i2;
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, 0, intent, i2);
    }
}
