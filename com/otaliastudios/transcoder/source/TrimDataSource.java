package com.otaliastudios.transcoder.source;

import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.Logger;

/* loaded from: classes4.dex */
public class TrimDataSource extends DataSourceWrapper {
    private static final Logger LOG = new Logger("TrimDataSource");
    private long extraDurationUs;
    private boolean trimDone;
    private long trimDurationUs;
    private final long trimEndUs;
    private final long trimStartUs;

    public TrimDataSource(DataSource dataSource, long j) {
        this(dataSource, j, 0L);
    }

    public TrimDataSource(DataSource dataSource, long j, long j2) {
        super(dataSource);
        this.extraDurationUs = 0L;
        this.trimDurationUs = Long.MIN_VALUE;
        this.trimDone = false;
        if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("Trim values cannot be negative.");
        }
        this.trimStartUs = j;
        this.trimEndUs = j2;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public boolean isInitialized() {
        return super.isInitialized() && this.trimDurationUs != Long.MIN_VALUE;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public void deinitialize() {
        super.deinitialize();
        this.trimDurationUs = Long.MIN_VALUE;
        this.trimDone = false;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public void initialize() {
        super.initialize();
        long durationUs = getSource().getDurationUs();
        if (this.trimStartUs + this.trimEndUs >= durationUs) {
            LOG.w("Trim values are too large! start=" + this.trimStartUs + ", end=" + this.trimEndUs + ", duration=" + durationUs);
            throw new IllegalArgumentException("Trim values cannot be greater than media duration.");
        }
        LOG.i("initialize(): duration=" + durationUs + " trimStart=" + this.trimStartUs + " trimEnd=" + this.trimEndUs + " trimDuration=" + ((durationUs - this.trimStartUs) - this.trimEndUs));
        this.trimDurationUs = (durationUs - this.trimStartUs) - this.trimEndUs;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public long getDurationUs() {
        return this.trimDurationUs + this.extraDurationUs;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public long getPositionUs() {
        return (super.getPositionUs() - this.trimStartUs) + this.extraDurationUs;
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public boolean canReadTrack(TrackType trackType) {
        if (!this.trimDone) {
            long j = this.trimStartUs;
            if (j > 0) {
                this.extraDurationUs = j - getSource().seekTo(this.trimStartUs);
                LOG.i("canReadTrack(): extraDurationUs=" + this.extraDurationUs + " trimStartUs=" + this.trimStartUs + " source.seekTo(trimStartUs)=" + (this.extraDurationUs - this.trimStartUs));
                this.trimDone = true;
            }
        }
        return super.canReadTrack(trackType);
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public boolean isDrained() {
        return super.isDrained() || getPositionUs() >= getDurationUs();
    }

    @Override // com.otaliastudios.transcoder.source.DataSourceWrapper, com.otaliastudios.transcoder.source.DataSource
    public long seekTo(long j) {
        return getSource().seekTo(this.trimStartUs + j) - this.trimStartUs;
    }
}
