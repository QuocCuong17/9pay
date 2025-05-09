package com.otaliastudios.transcoder.strategy;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import java.util.List;

/* loaded from: classes4.dex */
public class RemoveTrackStrategy implements TrackStrategy {
    @Override // com.otaliastudios.transcoder.strategy.TrackStrategy
    public TrackStatus createOutputFormat(List<MediaFormat> list, MediaFormat mediaFormat) {
        return TrackStatus.REMOVING;
    }
}
