package androidx.media3.common.util;

import androidx.media3.common.C;
import androidx.media3.common.audio.SpeedProvider;

/* loaded from: classes.dex */
public class SpeedProviderUtil {
    private SpeedProviderUtil() {
    }

    public static long getDurationAfterSpeedProviderApplied(SpeedProvider speedProvider, long j) {
        long j2 = 0;
        double d = 0.0d;
        while (j2 < j) {
            long nextSpeedChangeTimeUs = speedProvider.getNextSpeedChangeTimeUs(j2);
            if (nextSpeedChangeTimeUs == C.TIME_UNSET) {
                nextSpeedChangeTimeUs = Long.MAX_VALUE;
            }
            d += (Math.min(nextSpeedChangeTimeUs, j) - j2) / speedProvider.getSpeed(j2);
            j2 = nextSpeedChangeTimeUs;
        }
        return Math.round(d);
    }
}
