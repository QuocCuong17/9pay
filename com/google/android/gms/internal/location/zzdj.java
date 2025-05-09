package com.google.android.gms.internal.location;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.beust.jcommander.Parameters;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-location@@21.0.1 */
/* loaded from: classes3.dex */
public final class zzdj {
    private static final SimpleDateFormat zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.ROOT);
    private static final SimpleDateFormat zzb = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.ROOT);
    private static final StringBuilder zzc = new StringBuilder(33);

    public static String zza(long j) {
        String sb;
        StringBuilder sb2 = zzc;
        synchronized (sb2) {
            sb2.setLength(0);
            zzb(j, sb2);
            sb = sb2.toString();
        }
        return sb;
    }

    public static void zzb(long j, StringBuilder sb) {
        if (j == 0) {
            sb.append("0s");
            return;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z = false;
        if (j < 0) {
            sb.append(Parameters.DEFAULT_OPTION_PREFIXES);
            if (j != Long.MIN_VALUE) {
                j = -j;
            } else {
                j = Long.MAX_VALUE;
                z = true;
            }
        }
        if (j >= 86400000) {
            sb.append(j / 86400000);
            sb.append("d");
            j %= 86400000;
        }
        if (true == z) {
            j = 25975808;
        }
        if (j >= 3600000) {
            sb.append(j / 3600000);
            sb.append(CmcdData.Factory.STREAMING_FORMAT_HLS);
            j %= 3600000;
        }
        if (j >= 60000) {
            sb.append(j / 60000);
            sb.append("m");
            j %= 60000;
        }
        if (j >= 1000) {
            sb.append(j / 1000);
            sb.append(CmcdData.Factory.STREAMING_FORMAT_SS);
            j %= 1000;
        }
        if (j > 0) {
            sb.append(j);
            sb.append("ms");
        }
    }
}
