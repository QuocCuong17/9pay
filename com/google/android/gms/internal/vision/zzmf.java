package com.google.android.gms.internal.vision;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import net.sf.scuba.smartcards.ISO7816;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes3.dex */
final class zzmf {
    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzd(byte b) {
        return b >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zze(byte b) {
        return b < -32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzf(byte b) {
        return b < -16;
    }

    private static boolean zzg(byte b) {
        return b > -65;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(byte b, char[] cArr, int i) {
        cArr[i] = (char) b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(byte b, byte b2, char[] cArr, int i) throws zzjk {
        if (b < -62 || zzg(b2)) {
            throw zzjk.zzh();
        }
        cArr[i] = (char) (((b & Ascii.US) << 6) | (b2 & Utf8.REPLACEMENT_BYTE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(byte b, byte b2, byte b3, char[] cArr, int i) throws zzjk {
        if (zzg(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || zzg(b3)))) {
            throw zzjk.zzh();
        }
        cArr[i] = (char) (((b & 15) << 12) | ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (b3 & Utf8.REPLACEMENT_BYTE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws zzjk {
        if (zzg(b2) || (((b << 28) + (b2 + ISO7816.INS_MANAGE_CHANNEL)) >> 30) != 0 || zzg(b3) || zzg(b4)) {
            throw zzjk.zzh();
        }
        int i2 = ((b & 7) << 18) | ((b2 & Utf8.REPLACEMENT_BYTE) << 12) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
        cArr[i] = (char) ((i2 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
        cArr[i + 1] = (char) ((i2 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
    }
}
