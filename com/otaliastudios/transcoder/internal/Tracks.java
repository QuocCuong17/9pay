package com.otaliastudios.transcoder.internal;

import android.media.MediaFormat;
import androidx.constraintlayout.motion.widget.Key;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.media.MediaFormatProvider;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.internal.utils.TrackMapKt;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.strategy.TrackStrategy;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: Tracks.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0002J4\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\r0\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eH\u0002J \u0010 \u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010!\u001a\u00020\bH\u0002R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f¨\u0006\""}, d2 = {"Lcom/otaliastudios/transcoder/internal/Tracks;", "", "strategies", "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "Lcom/otaliastudios/transcoder/strategy/TrackStrategy;", "sources", "Lcom/otaliastudios/transcoder/internal/DataSources;", "videoRotation", "", "forceCompression", "", "(Lcom/otaliastudios/transcoder/internal/utils/TrackMap;Lcom/otaliastudios/transcoder/internal/DataSources;IZ)V", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "Lcom/otaliastudios/transcoder/common/TrackStatus;", "getActive", "()Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", TtmlNode.COMBINE_ALL, "getAll", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "outputFormats", "Landroid/media/MediaFormat;", "getOutputFormats", "resolveAudioStatus", "status", "resolveTrack", "Lkotlin/Pair;", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "strategy", "", "Lcom/otaliastudios/transcoder/source/DataSource;", "resolveVideoStatus", Key.ROTATION, "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Tracks {
    private final TrackMap<TrackStatus> active;
    private final TrackMap<TrackStatus> all;
    private final Logger log;
    private final TrackMap<MediaFormat> outputFormats;

    public Tracks(TrackMap<TrackStrategy> strategies, DataSources sources, int i, boolean z) {
        Intrinsics.checkNotNullParameter(strategies, "strategies");
        Intrinsics.checkNotNullParameter(sources, "sources");
        Logger logger = new Logger("Tracks");
        this.log = logger;
        Pair<MediaFormat, TrackStatus> resolveTrack = resolveTrack(TrackType.AUDIO, strategies.getAudio(), sources.audioOrNull());
        MediaFormat component1 = resolveTrack.component1();
        TrackStatus component2 = resolveTrack.component2();
        Pair<MediaFormat, TrackStatus> resolveTrack2 = resolveTrack(TrackType.VIDEO, strategies.getVideo(), sources.videoOrNull());
        MediaFormat component12 = resolveTrack2.component1();
        TrackStatus component22 = resolveTrack2.component2();
        TrackMap<TrackStatus> trackMapOf = TrackMapKt.trackMapOf(resolveVideoStatus(component22, z, i), resolveAudioStatus(component2, z));
        this.all = trackMapOf;
        this.outputFormats = TrackMapKt.trackMapOf(component12, component1);
        logger.i("init: videoStatus=" + component22 + ", resolvedVideoStatus=" + trackMapOf.getVideo() + ", videoFormat=" + component12);
        logger.i("init: audioStatus=" + component2 + ", resolvedAudioStatus=" + trackMapOf.getAudio() + ", audioFormat=" + component1);
        TrackStatus video = trackMapOf.getVideo();
        video = video.isTranscoding() ? video : null;
        TrackStatus audio = trackMapOf.getAudio();
        this.active = TrackMapKt.trackMapOf(video, audio.isTranscoding() ? audio : null);
    }

    public final TrackMap<TrackStatus> getAll() {
        return this.all;
    }

    public final TrackMap<MediaFormat> getOutputFormats() {
        return this.outputFormats;
    }

    public final TrackMap<TrackStatus> getActive() {
        return this.active;
    }

    private final TrackStatus resolveVideoStatus(TrackStatus status, boolean forceCompression, int rotation) {
        return ((status == TrackStatus.PASS_THROUGH) && (forceCompression || rotation != 0)) ? TrackStatus.COMPRESSING : status;
    }

    private final TrackStatus resolveAudioStatus(TrackStatus status, boolean forceCompression) {
        return ((status == TrackStatus.PASS_THROUGH) && forceCompression) ? TrackStatus.COMPRESSING : status;
    }

    private final Pair<MediaFormat, TrackStatus> resolveTrack(TrackType type, TrackStrategy strategy, List<? extends DataSource> sources) {
        Logger logger = this.log;
        StringBuilder sb = new StringBuilder();
        sb.append("resolveTrack(");
        sb.append(type);
        sb.append("), sources=");
        sb.append(sources == null ? null : Integer.valueOf(sources.size()));
        sb.append(", strategy=");
        sb.append((Object) Reflection.getOrCreateKotlinClass(strategy.getClass()).getSimpleName());
        logger.i(sb.toString());
        if (sources == null) {
            return TuplesKt.to(new MediaFormat(), TrackStatus.ABSENT);
        }
        MediaFormatProvider mediaFormatProvider = new MediaFormatProvider();
        ArrayList arrayList = new ArrayList();
        for (DataSource dataSource : sources) {
            MediaFormat trackFormat = dataSource.getTrackFormat(type);
            MediaFormat provideMediaFormat = trackFormat == null ? null : mediaFormatProvider.provideMediaFormat(dataSource, type, trackFormat);
            if (provideMediaFormat != null) {
                arrayList.add(provideMediaFormat);
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        if (size == 0) {
            return TuplesKt.to(new MediaFormat(), TrackStatus.ABSENT);
        }
        if (size == sources.size()) {
            MediaFormat mediaFormat = new MediaFormat();
            TrackStatus createOutputFormat = strategy.createOutputFormat(arrayList2, mediaFormat);
            Intrinsics.checkNotNullExpressionValue(createOutputFormat, "strategy.createOutputFormat(inputs, output)");
            return TuplesKt.to(mediaFormat, createOutputFormat);
        }
        throw new IllegalStateException(("Of all " + type + " sources, some have a " + type + " track, some don't.").toString());
    }
}
