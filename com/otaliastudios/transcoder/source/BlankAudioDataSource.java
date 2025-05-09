package com.otaliastudios.transcoder.source;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.audio.ConversionsKt;
import com.otaliastudios.transcoder.source.DataSource;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public class BlankAudioDataSource implements DataSource {
    private static final int CHANNEL_COUNT = 2;
    private static final int PERIOD_SIZE = ConversionsKt.samplesToBytes(2048, 2);
    private static final int SAMPLE_RATE = 44100;
    private MediaFormat audioFormat;
    private ByteBuffer byteBuffer;
    private final long durationUs;
    private long positionUs = 0;
    private boolean initialized = false;

    @Override // com.otaliastudios.transcoder.source.DataSource
    public double[] getLocation() {
        return null;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public int getOrientation() {
        return 0;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void releaseTrack(TrackType trackType) {
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void selectTrack(TrackType trackType) {
    }

    public BlankAudioDataSource(long j) {
        this.durationUs = j;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void initialize() {
        int i = PERIOD_SIZE;
        this.byteBuffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        MediaFormat mediaFormat = new MediaFormat();
        this.audioFormat = mediaFormat;
        mediaFormat.setString("mime", "audio/raw");
        this.audioFormat.setInteger("bitrate", ConversionsKt.bitRate(SAMPLE_RATE, 2));
        this.audioFormat.setInteger("channel-count", 2);
        this.audioFormat.setInteger("max-input-size", i);
        this.audioFormat.setInteger("sample-rate", SAMPLE_RATE);
        this.initialized = true;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void deinitialize() {
        this.positionUs = 0L;
        this.initialized = false;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isInitialized() {
        return this.initialized;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long seekTo(long j) {
        this.positionUs = j;
        return j;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public MediaFormat getTrackFormat(TrackType trackType) {
        if (trackType == TrackType.AUDIO) {
            return this.audioFormat;
        }
        return null;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean canReadTrack(TrackType trackType) {
        return trackType == TrackType.AUDIO;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void readTrack(DataSource.Chunk chunk) {
        int position = chunk.buffer.position();
        int min = Math.min(chunk.buffer.remaining(), PERIOD_SIZE);
        this.byteBuffer.clear();
        this.byteBuffer.limit(min);
        chunk.buffer.put(this.byteBuffer);
        chunk.buffer.position(position);
        chunk.buffer.limit(position + min);
        chunk.keyframe = true;
        chunk.timeUs = this.positionUs;
        chunk.render = true;
        this.positionUs += ConversionsKt.bytesToUs(min, SAMPLE_RATE, 2);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getPositionUs() {
        return this.positionUs;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isDrained() {
        return this.positionUs >= getDurationUs();
    }
}
