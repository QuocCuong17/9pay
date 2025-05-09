package com.otaliastudios.transcoder.source;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.common.TrackTypeKt;
import com.otaliastudios.transcoder.internal.utils.ISO6709LocationParser;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.MutableTrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import com.otaliastudios.transcoder.source.DataSource;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public abstract class DefaultDataSource implements DataSource {
    private static final AtomicInteger ID = new AtomicInteger(0);
    private final Logger LOG = new Logger("DefaultDataSource(" + ID.getAndIncrement() + ")");
    private final MutableTrackMap<MediaFormat> mFormat = TrackMapKt.mutableTrackMapOf(null);
    private final MutableTrackMap<Integer> mIndex = TrackMapKt.mutableTrackMapOf(null);
    private final HashSet<TrackType> mSelectedTracks = new HashSet<>();
    private final MutableTrackMap<Long> mLastTimestampUs = TrackMapKt.mutableTrackMapOf(0L, 0L);
    private MediaMetadataRetriever mMetadata = null;
    private MediaExtractor mExtractor = null;
    private long mOriginUs = Long.MIN_VALUE;
    private boolean mInitialized = false;
    private long mDontRenderRangeStart = -1;
    private long mDontRenderRangeEnd = -1;

    protected abstract void initializeExtractor(MediaExtractor mediaExtractor) throws IOException;

    protected abstract void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever);

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void initialize() {
        this.LOG.i("initialize(): initializing...");
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.mExtractor = mediaExtractor;
        try {
            initializeExtractor(mediaExtractor);
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            this.mMetadata = mediaMetadataRetriever;
            initializeRetriever(mediaMetadataRetriever);
            int trackCount = this.mExtractor.getTrackCount();
            for (int i = 0; i < trackCount; i++) {
                MediaFormat trackFormat = this.mExtractor.getTrackFormat(i);
                TrackType trackTypeOrNull = TrackTypeKt.getTrackTypeOrNull(trackFormat);
                if (trackTypeOrNull != null && !this.mIndex.has(trackTypeOrNull)) {
                    this.mIndex.set(trackTypeOrNull, Integer.valueOf(i));
                    this.mFormat.set(trackTypeOrNull, trackFormat);
                }
            }
            for (int i2 = 0; i2 < this.mExtractor.getTrackCount(); i2++) {
                this.mExtractor.selectTrack(i2);
            }
            this.mOriginUs = this.mExtractor.getSampleTime();
            this.LOG.v("initialize(): found origin=" + this.mOriginUs);
            for (int i3 = 0; i3 < this.mExtractor.getTrackCount(); i3++) {
                this.mExtractor.unselectTrack(i3);
            }
            this.mInitialized = true;
        } catch (IOException e) {
            this.LOG.e("Got IOException while trying to open MediaExtractor.", e);
            throw new RuntimeException(e);
        }
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void deinitialize() {
        this.LOG.i("deinitialize(): deinitializing...");
        try {
            this.mExtractor.release();
        } catch (Exception e) {
            this.LOG.w("Could not release extractor:", e);
        }
        try {
            this.mMetadata.release();
        } catch (Exception e2) {
            this.LOG.w("Could not release metadata:", e2);
        }
        this.mSelectedTracks.clear();
        this.mOriginUs = Long.MIN_VALUE;
        this.mLastTimestampUs.reset(0L, 0L);
        this.mFormat.reset(null, null);
        this.mIndex.reset(null, null);
        this.mDontRenderRangeStart = -1L;
        this.mDontRenderRangeEnd = -1L;
        this.mInitialized = false;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isInitialized() {
        return this.mInitialized;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void selectTrack(TrackType trackType) {
        this.LOG.i("selectTrack(" + trackType + ")");
        if (this.mSelectedTracks.contains(trackType)) {
            return;
        }
        this.mSelectedTracks.add(trackType);
        this.mExtractor.selectTrack(this.mIndex.get(trackType).intValue());
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void releaseTrack(TrackType trackType) {
        this.LOG.i("releaseTrack(" + trackType + ")");
        if (this.mSelectedTracks.contains(trackType)) {
            this.mSelectedTracks.remove(trackType);
            this.mExtractor.unselectTrack(this.mIndex.get(trackType).intValue());
        }
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long seekTo(long j) {
        boolean contains = this.mSelectedTracks.contains(TrackType.VIDEO);
        boolean contains2 = this.mSelectedTracks.contains(TrackType.AUDIO);
        this.LOG.i("seekTo(): seeking to " + (this.mOriginUs + j) + " originUs=" + this.mOriginUs + " extractorUs=" + this.mExtractor.getSampleTime() + " externalUs=" + j + " hasVideo=" + contains + " hasAudio=" + contains2);
        if (contains && contains2) {
            this.mExtractor.unselectTrack(this.mIndex.getAudio().intValue());
            this.LOG.v("seekTo(): unselected AUDIO, seeking to " + (this.mOriginUs + j) + " (extractorUs=" + this.mExtractor.getSampleTime() + ")");
            this.mExtractor.seekTo(this.mOriginUs + j, 0);
            this.LOG.v("seekTo(): unselected AUDIO and sought (extractorUs=" + this.mExtractor.getSampleTime() + ")");
            this.mExtractor.selectTrack(this.mIndex.getAudio().intValue());
            this.LOG.v("seekTo(): reselected AUDIO, seeking to extractorUs (extractorUs=" + this.mExtractor.getSampleTime() + ")");
            MediaExtractor mediaExtractor = this.mExtractor;
            mediaExtractor.seekTo(mediaExtractor.getSampleTime(), 2);
            this.LOG.v("seekTo(): seek workaround completed. (extractorUs=" + this.mExtractor.getSampleTime() + ")");
        } else {
            this.mExtractor.seekTo(this.mOriginUs + j, 0);
        }
        long sampleTime = this.mExtractor.getSampleTime();
        this.mDontRenderRangeStart = sampleTime;
        long j2 = this.mOriginUs + j;
        this.mDontRenderRangeEnd = j2;
        if (sampleTime > j2) {
            this.mDontRenderRangeStart = j2;
        }
        this.LOG.i("seekTo(): dontRenderRange=" + this.mDontRenderRangeStart + ".." + this.mDontRenderRangeEnd + " (" + (this.mDontRenderRangeEnd - this.mDontRenderRangeStart) + "us)");
        return this.mExtractor.getSampleTime() - this.mOriginUs;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isDrained() {
        return this.mExtractor.getSampleTrackIndex() < 0;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean canReadTrack(TrackType trackType) {
        return this.mExtractor.getSampleTrackIndex() == this.mIndex.get(trackType).intValue();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void readTrack(DataSource.Chunk chunk) {
        TrackType trackType;
        int sampleTrackIndex = this.mExtractor.getSampleTrackIndex();
        int position = chunk.buffer.position();
        int limit = chunk.buffer.limit();
        int readSampleData = this.mExtractor.readSampleData(chunk.buffer, position);
        if (readSampleData < 0) {
            throw new IllegalStateException("No samples available! Forgot to call canReadTrack / isDrained?");
        }
        int i = readSampleData + position;
        if (i > limit) {
            throw new IllegalStateException("MediaExtractor is not respecting the buffer limit. This might cause other issues down the pipeline.");
        }
        chunk.buffer.limit(i);
        chunk.buffer.position(position);
        chunk.keyframe = (this.mExtractor.getSampleFlags() & 1) != 0;
        chunk.timeUs = this.mExtractor.getSampleTime();
        chunk.render = chunk.timeUs < this.mDontRenderRangeStart || chunk.timeUs >= this.mDontRenderRangeEnd;
        this.LOG.v("readTrack(): time=" + chunk.timeUs + ", render=" + chunk.render + ", end=" + this.mDontRenderRangeEnd);
        if (this.mIndex.getHasAudio() && this.mIndex.getAudio().intValue() == sampleTrackIndex) {
            trackType = TrackType.AUDIO;
        } else {
            trackType = (this.mIndex.getHasVideo() && this.mIndex.getVideo().intValue() == sampleTrackIndex) ? TrackType.VIDEO : null;
        }
        if (trackType == null) {
            throw new RuntimeException("Unknown type: " + sampleTrackIndex);
        }
        this.mLastTimestampUs.set(trackType, Long.valueOf(chunk.timeUs));
        this.mExtractor.advance();
        if (chunk.render || !isDrained()) {
            return;
        }
        this.LOG.w("Force rendering the last frame. timeUs=" + chunk.timeUs);
        chunk.render = true;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getPositionUs() {
        if (isInitialized()) {
            return Math.max(this.mLastTimestampUs.getAudio().longValue(), this.mLastTimestampUs.getVideo().longValue()) - this.mOriginUs;
        }
        return 0L;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public double[] getLocation() {
        float[] parse;
        this.LOG.i("getLocation()");
        String extractMetadata = this.mMetadata.extractMetadata(23);
        if (extractMetadata == null || (parse = new ISO6709LocationParser().parse(extractMetadata)) == null) {
            return null;
        }
        return new double[]{parse[0], parse[1]};
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public int getOrientation() {
        this.LOG.i("getOrientation()");
        try {
            return Integer.parseInt(this.mMetadata.extractMetadata(24));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getDurationUs() {
        try {
            return Long.parseLong(this.mMetadata.extractMetadata(9)) * 1000;
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public MediaFormat getTrackFormat(TrackType trackType) {
        this.LOG.i("getTrackFormat(" + trackType + ")");
        return this.mFormat.getOrNull(trackType);
    }
}
