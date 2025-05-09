package com.otaliastudios.transcoder.internal;

import android.media.MediaFormat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.pipeline.Pipeline;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.MutableTrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import com.otaliastudios.transcoder.source.DataSource;
import io.sentry.protocol.SentryThread;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.FilenameUtils;

/* compiled from: Segments.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012$\u0010\u0006\u001a \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0002\u0010\rJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001c\u001a\u00020\bJ\u0006\u0010\u001e\u001a\u00020\u0018J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010 \u001a\u00020\tH\u0002R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R,\u0010\u0006\u001a \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/otaliastudios/transcoder/internal/Segments;", "", "sources", "Lcom/otaliastudios/transcoder/internal/DataSources;", "tracks", "Lcom/otaliastudios/transcoder/internal/Tracks;", "factory", "Lkotlin/Function4;", "Lcom/otaliastudios/transcoder/common/TrackType;", "", "Lcom/otaliastudios/transcoder/common/TrackStatus;", "Landroid/media/MediaFormat;", "Lcom/otaliastudios/transcoder/internal/pipeline/Pipeline;", "(Lcom/otaliastudios/transcoder/internal/DataSources;Lcom/otaliastudios/transcoder/internal/Tracks;Lkotlin/jvm/functions/Function4;)V", SentryThread.JsonKeys.CURRENT, "Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "Lcom/otaliastudios/transcoder/internal/Segment;", "currentIndex", "getCurrentIndex", "()Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "requestedIndex", "destroySegment", "", "segment", "hasNext", "", "type", "next", "release", "tryCreateSegment", FirebaseAnalytics.Param.INDEX, "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Segments {
    private final MutableTrackMap<Segment> current;
    private final MutableTrackMap<Integer> currentIndex;
    private final Function4<TrackType, Integer, TrackStatus, MediaFormat, Pipeline> factory;
    private final Logger log;
    private final MutableTrackMap<Integer> requestedIndex;
    private final DataSources sources;
    private final Tracks tracks;

    /* compiled from: Segments.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TrackType.values().length];
            iArr[TrackType.AUDIO.ordinal()] = 1;
            iArr[TrackType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Segments(DataSources sources, Tracks tracks, Function4<? super TrackType, ? super Integer, ? super TrackStatus, ? super MediaFormat, Pipeline> factory) {
        Intrinsics.checkNotNullParameter(sources, "sources");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.sources = sources;
        this.tracks = tracks;
        this.factory = factory;
        this.log = new Logger("Segments");
        this.current = TrackMapKt.mutableTrackMapOf(null, null);
        this.currentIndex = TrackMapKt.mutableTrackMapOf(-1, -1);
        this.requestedIndex = TrackMapKt.mutableTrackMapOf(0, 0);
    }

    public final MutableTrackMap<Integer> getCurrentIndex() {
        return this.currentIndex;
    }

    public final boolean hasNext(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!this.sources.has(type)) {
            return false;
        }
        Logger logger = this.log;
        StringBuilder sb = new StringBuilder();
        sb.append("hasNext(");
        sb.append(type);
        sb.append("): segment=");
        sb.append(this.current.getOrNull(type));
        sb.append(" lastIndex=");
        List<? extends DataSource> orNull = this.sources.getOrNull(type);
        sb.append(orNull == null ? null : Integer.valueOf(CollectionsKt.getLastIndex(orNull)));
        sb.append(" canAdvance=");
        Segment orNull2 = this.current.getOrNull(type);
        sb.append(orNull2 == null ? null : Boolean.valueOf(orNull2.canAdvance()));
        logger.v(sb.toString());
        Segment orNull3 = this.current.getOrNull(type);
        if (orNull3 == null) {
            return true;
        }
        List<? extends DataSource> orNull4 = this.sources.getOrNull(type);
        Integer valueOf = orNull4 != null ? Integer.valueOf(CollectionsKt.getLastIndex(orNull4)) : null;
        if (valueOf == null) {
            return false;
        }
        return orNull3.canAdvance() || orNull3.getIndex() < valueOf.intValue();
    }

    public final boolean hasNext() {
        return hasNext(TrackType.VIDEO) || hasNext(TrackType.AUDIO);
    }

    public final Segment next(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        int intValue = this.currentIndex.get(type).intValue();
        int intValue2 = this.requestedIndex.get(type).intValue();
        if (intValue2 >= intValue) {
            if (intValue2 > intValue) {
                return tryCreateSegment(type, intValue2);
            }
            if (this.current.get(type).canAdvance()) {
                return this.current.get(type);
            }
            destroySegment(this.current.get(type));
            return next(type);
        }
        throw new IllegalStateException(("Requested index " + intValue2 + " smaller than " + intValue + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    public final void release() {
        Segment videoOrNull = this.current.videoOrNull();
        if (videoOrNull != null) {
            destroySegment(videoOrNull);
        }
        Segment audioOrNull = this.current.audioOrNull();
        if (audioOrNull == null) {
            return;
        }
        destroySegment(audioOrNull);
    }

    private final Segment tryCreateSegment(TrackType type, int index) {
        TrackType trackType;
        DataSource dataSource = (DataSource) CollectionsKt.getOrNull(this.sources.get(type), index);
        if (dataSource == null) {
            return null;
        }
        this.log.i("tryCreateSegment(" + type + ", " + index + "): created!");
        if (this.tracks.getActive().has(type)) {
            dataSource.selectTrack(type);
            int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            boolean z = true;
            if (i == 1) {
                trackType = TrackType.VIDEO;
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                trackType = TrackType.AUDIO;
            }
            if (this.tracks.getActive().has(trackType)) {
                List<? extends DataSource> list = this.sources.get(trackType);
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        if (((DataSource) it.next()) == dataSource) {
                            break;
                        }
                    }
                }
                z = false;
                if (z) {
                    dataSource.selectTrack(trackType);
                }
            }
        }
        this.currentIndex.set(type, Integer.valueOf(index));
        Segment segment = new Segment(type, index, this.factory.invoke(type, Integer.valueOf(index), this.tracks.getAll().get(type), this.tracks.getOutputFormats().get(type)));
        this.current.set(type, segment);
        return segment;
    }

    private final void destroySegment(Segment segment) {
        segment.release();
        DataSource dataSource = this.sources.get(segment.getType()).get(segment.getIndex());
        if (this.tracks.getActive().has(segment.getType())) {
            dataSource.releaseTrack(segment.getType());
        }
        this.requestedIndex.set(segment.getType(), Integer.valueOf(segment.getIndex() + 1));
    }
}
