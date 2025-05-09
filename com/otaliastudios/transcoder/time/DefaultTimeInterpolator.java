package com.otaliastudios.transcoder.time;

import com.otaliastudios.transcoder.common.TrackType;

/* loaded from: classes4.dex */
public class DefaultTimeInterpolator implements TimeInterpolator {
    @Override // com.otaliastudios.transcoder.time.TimeInterpolator
    public long interpolate(TrackType trackType, long j) {
        return j;
    }
}
