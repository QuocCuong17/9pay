package com.otaliastudios.transcoder.source;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public interface DataSource {

    /* loaded from: classes4.dex */
    public static class Chunk {
        public ByteBuffer buffer;
        public boolean keyframe;
        public boolean render;
        public long timeUs;
    }

    boolean canReadTrack(TrackType trackType);

    void deinitialize();

    long getDurationUs();

    double[] getLocation();

    int getOrientation();

    long getPositionUs();

    MediaFormat getTrackFormat(TrackType trackType);

    void initialize();

    boolean isDrained();

    boolean isInitialized();

    void readTrack(Chunk chunk);

    void releaseTrack(TrackType trackType);

    long seekTo(long j);

    void selectTrack(TrackType trackType);
}
