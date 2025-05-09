package com.otaliastudios.transcoder.time;

import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;

/* loaded from: classes4.dex */
public class SpeedTimeInterpolator implements TimeInterpolator {
    private static final Logger LOG = new Logger("SpeedTimeInterpolator");
    private final double mFactor;
    private final TrackMap<TrackData> mTrackData;

    public SpeedTimeInterpolator(float f) {
        this.mTrackData = TrackMapKt.trackMapOf(new TrackData(), new TrackData());
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Invalid speed factor: " + f);
        }
        this.mFactor = f;
    }

    public float getFactor() {
        return (float) this.mFactor;
    }

    @Override // com.otaliastudios.transcoder.time.TimeInterpolator
    public long interpolate(TrackType trackType, long j) {
        TrackData trackData = this.mTrackData.get(trackType);
        if (trackData.lastRealTime != Long.MIN_VALUE) {
            long j2 = (long) ((j - trackData.lastRealTime) / this.mFactor);
            trackData.lastRealTime = j;
            TrackData.access$214(trackData, j2);
        } else {
            trackData.lastRealTime = j;
            trackData.lastCorrectedTime = j;
        }
        LOG.v("Track:" + trackType + " inputTime:" + j + " outputTime:" + trackData.lastCorrectedTime);
        return trackData.lastCorrectedTime;
    }

    /* loaded from: classes4.dex */
    private static class TrackData {
        private long lastCorrectedTime;
        private long lastRealTime;

        private TrackData() {
            this.lastRealTime = Long.MIN_VALUE;
            this.lastCorrectedTime = Long.MIN_VALUE;
        }

        static /* synthetic */ long access$214(TrackData trackData, long j) {
            long j2 = trackData.lastCorrectedTime + j;
            trackData.lastCorrectedTime = j2;
            return j2;
        }
    }
}
