package com.otaliastudios.transcoder.source;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.source.DataSource;
import java.util.Objects;

/* loaded from: classes4.dex */
public class DataSourceWrapper implements DataSource {
    private DataSource mSource;

    /* JADX INFO: Access modifiers changed from: protected */
    public DataSourceWrapper(DataSource dataSource) {
        this.mSource = dataSource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DataSourceWrapper() {
        this.mSource = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DataSource getSource() {
        return this.mSource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSource(DataSource dataSource) {
        this.mSource = dataSource;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public int getOrientation() {
        return this.mSource.getOrientation();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public double[] getLocation() {
        return this.mSource.getLocation();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getDurationUs() {
        return this.mSource.getDurationUs();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public MediaFormat getTrackFormat(TrackType trackType) {
        return this.mSource.getTrackFormat(trackType);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void selectTrack(TrackType trackType) {
        this.mSource.selectTrack(trackType);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long seekTo(long j) {
        return this.mSource.seekTo(j);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean canReadTrack(TrackType trackType) {
        return this.mSource.canReadTrack(trackType);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void readTrack(DataSource.Chunk chunk) {
        this.mSource.readTrack(chunk);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getPositionUs() {
        return this.mSource.getPositionUs();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isDrained() {
        return this.mSource.isDrained();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void releaseTrack(TrackType trackType) {
        this.mSource.releaseTrack(trackType);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isInitialized() {
        DataSource dataSource = this.mSource;
        return dataSource != null && dataSource.isInitialized();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void initialize() {
        if (isInitialized()) {
            return;
        }
        DataSource dataSource = this.mSource;
        Objects.requireNonNull(dataSource, "DataSourceWrapper's source is not set!");
        dataSource.initialize();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void deinitialize() {
        this.mSource.deinitialize();
    }
}
