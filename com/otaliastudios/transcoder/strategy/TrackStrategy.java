package com.otaliastudios.transcoder.strategy;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackStatus;
import java.util.List;

/* loaded from: classes4.dex */
public interface TrackStrategy {
    TrackStatus createOutputFormat(List<MediaFormat> list, MediaFormat mediaFormat);
}
