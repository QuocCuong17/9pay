package com.otaliastudios.transcoder.internal.utils;

import android.media.MediaFormat;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.source.DataSource;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: eos.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\tH\u0096\u0001J\t\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u0010\f\u001a\u00020\rH\u0096\u0001J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0097\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u0096\u0001J\t\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0001\u0010\b\u001a\u00020\tH\u0097\u0001J\t\u0010\u0015\u001a\u00020\u000bH\u0096\u0001J\b\u0010\u0016\u001a\u00020\u0005H\u0016J\t\u0010\u0017\u001a\u00020\u0005H\u0096\u0001J\u0013\u0010\u0018\u001a\u00020\u000b2\b\b\u0001\u0010\u0019\u001a\u00020\u001aH\u0096\u0001J\u0013\u0010\u001b\u001a\u00020\u000b2\b\b\u0001\u0010\b\u001a\u00020\tH\u0096\u0001J\u0011\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0096\u0001J\u0013\u0010\u001e\u001a\u00020\u000b2\b\b\u0001\u0010\b\u001a\u00020\tH\u0096\u0001R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/otaliastudios/transcoder/internal/utils/EosForcingDataSource;", "Lcom/otaliastudios/transcoder/source/DataSource;", "source", "force", "Lkotlin/Function0;", "", "(Lcom/otaliastudios/transcoder/source/DataSource;Lkotlin/jvm/functions/Function0;)V", "canReadTrack", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "deinitialize", "", "getDurationUs", "", "getLocation", "", "getOrientation", "", "getPositionUs", "getTrackFormat", "Landroid/media/MediaFormat;", "initialize", "isDrained", "isInitialized", "readTrack", "chunk", "Lcom/otaliastudios/transcoder/source/DataSource$Chunk;", "releaseTrack", "seekTo", "desiredPositionUs", "selectTrack", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
final class EosForcingDataSource implements DataSource {
    private final Function0<Boolean> force;
    private final DataSource source;

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean canReadTrack(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return this.source.canReadTrack(type);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void deinitialize() {
        this.source.deinitialize();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getDurationUs() {
        return this.source.getDurationUs();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public double[] getLocation() {
        return this.source.getLocation();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public int getOrientation() {
        return this.source.getOrientation();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long getPositionUs() {
        return this.source.getPositionUs();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public MediaFormat getTrackFormat(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return this.source.getTrackFormat(type);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void initialize() {
        this.source.initialize();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isInitialized() {
        return this.source.isInitialized();
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void readTrack(DataSource.Chunk chunk) {
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        this.source.readTrack(chunk);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void releaseTrack(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.source.releaseTrack(type);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public long seekTo(long desiredPositionUs) {
        return this.source.seekTo(desiredPositionUs);
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public void selectTrack(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.source.selectTrack(type);
    }

    public EosForcingDataSource(DataSource source, Function0<Boolean> force) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(force, "force");
        this.source = source;
        this.force = force;
    }

    @Override // com.otaliastudios.transcoder.source.DataSource
    public boolean isDrained() {
        return this.force.invoke().booleanValue() || this.source.isDrained();
    }
}
