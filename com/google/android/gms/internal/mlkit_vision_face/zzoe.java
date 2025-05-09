package com.google.android.gms.internal.mlkit_vision_face;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzoe {
    private final TelemetryLoggingClient zza;
    private final AtomicLong zzb = new AtomicLong(-1);

    zzoe(Context context, String str) {
        this.zza = TelemetryLogging.getClient(context, TelemetryLoggingOptions.builder().setApi("mlkit:vision").build());
    }

    public static zzoe zza(Context context) {
        return new zzoe(context, "mlkit:vision");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(long j, Exception exc) {
        this.zzb.set(j);
    }

    public final synchronized void zzc(int i, int i2, long j, long j2) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzb.get() != -1 && elapsedRealtime - this.zzb.get() <= TimeUnit.MINUTES.toMillis(30L)) {
            return;
        }
        this.zza.log(new TelemetryData(0, Arrays.asList(new MethodInvocation(i, i2, 0, j, j2, null, null, 0)))).addOnFailureListener(new OnFailureListener() { // from class: com.google.android.gms.internal.mlkit_vision_face.zzod
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                zzoe.this.zzb(elapsedRealtime, exc);
            }
        });
    }
}
