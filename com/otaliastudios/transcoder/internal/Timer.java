package com.otaliastudios.transcoder.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import com.otaliastudios.transcoder.source.DataSource;
import com.otaliastudios.transcoder.time.TimeInterpolator;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Timer.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\nJ%\u0010 \u001a\u0004\u0018\u00010\r2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\r¢\u0006\u0002\u0010!J\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\b\u001a\u00020\nH\u0002J\u001a\u0010\u0016\u001a\u00020\r*\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\b\u001a\u00020\nH\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n0\u0012\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/otaliastudios/transcoder/internal/Timer;", "", "interpolator", "Lcom/otaliastudios/transcoder/time/TimeInterpolator;", "sources", "Lcom/otaliastudios/transcoder/internal/DataSources;", "tracks", "Lcom/otaliastudios/transcoder/internal/Tracks;", SentryThread.JsonKeys.CURRENT, "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "", "(Lcom/otaliastudios/transcoder/time/TimeInterpolator;Lcom/otaliastudios/transcoder/internal/DataSources;Lcom/otaliastudios/transcoder/internal/Tracks;Lcom/otaliastudios/transcoder/internal/utils/TrackMap;)V", "durationUs", "", "getDurationUs", "()Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "interpolators", "", "Lkotlin/Pair;", "Lcom/otaliastudios/transcoder/common/TrackType;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "positionUs", "getPositionUs", "progress", "", "getProgress", "totalDurationUs", "getTotalDurationUs", "()J", "type", FirebaseAnalytics.Param.INDEX, "localize", "(Lcom/otaliastudios/transcoder/common/TrackType;IJ)Ljava/lang/Long;", "", "Lcom/otaliastudios/transcoder/source/DataSource;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Timer {
    private final TrackMap<Integer> current;
    private final TrackMap<Long> durationUs;
    private final TimeInterpolator interpolator;
    private final Map<Pair<TrackType, Integer>, TimeInterpolator> interpolators;
    private final Logger log;
    private final TrackMap<Long> positionUs;
    private final TrackMap<Double> progress;
    private final DataSources sources;
    private final Tracks tracks;

    public Timer(TimeInterpolator interpolator, DataSources sources, Tracks tracks, TrackMap<Integer> current) {
        Intrinsics.checkNotNullParameter(interpolator, "interpolator");
        Intrinsics.checkNotNullParameter(sources, "sources");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(current, "current");
        this.interpolator = interpolator;
        this.sources = sources;
        this.tracks = tracks;
        this.current = current;
        this.log = new Logger("Timer");
        this.positionUs = new TrackMap<Long>() { // from class: com.otaliastudios.transcoder.internal.Timer$positionUs$2
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean has(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long audioOrNull() {
                return (Long) TrackMap.DefaultImpls.audioOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long getAudio() {
                return (Long) TrackMap.DefaultImpls.getAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasAudio() {
                return TrackMap.DefaultImpls.getHasAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasVideo() {
                return TrackMap.DefaultImpls.getHasVideo(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long getOrNull(TrackType trackType) {
                return (Long) TrackMap.DefaultImpls.getOrNull(this, trackType);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public int getSize() {
                return TrackMap.DefaultImpls.getSize(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long getVideo() {
                return (Long) TrackMap.DefaultImpls.getVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
            public Iterator<Long> iterator() {
                return TrackMap.DefaultImpls.iterator(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long videoOrNull() {
                return (Long) TrackMap.DefaultImpls.videoOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long get(TrackType type) {
                Tracks tracks2;
                DataSources dataSources;
                TrackMap trackMap;
                long positionUs;
                Intrinsics.checkNotNullParameter(type, "type");
                tracks2 = Timer.this.tracks;
                if (tracks2.getActive().has(type)) {
                    Timer timer = Timer.this;
                    dataSources = timer.sources;
                    List<? extends DataSource> list = dataSources.get(type);
                    trackMap = Timer.this.current;
                    positionUs = timer.positionUs(list, ((Number) trackMap.get(type)).intValue());
                } else {
                    positionUs = 0;
                }
                return Long.valueOf(positionUs);
            }
        };
        this.durationUs = new TrackMap<Long>() { // from class: com.otaliastudios.transcoder.internal.Timer$durationUs$2
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean has(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long audioOrNull() {
                return (Long) TrackMap.DefaultImpls.audioOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long getAudio() {
                return (Long) TrackMap.DefaultImpls.getAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasAudio() {
                return TrackMap.DefaultImpls.getHasAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasVideo() {
                return TrackMap.DefaultImpls.getHasVideo(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long getOrNull(TrackType trackType) {
                return (Long) TrackMap.DefaultImpls.getOrNull(this, trackType);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public int getSize() {
                return TrackMap.DefaultImpls.getSize(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long getVideo() {
                return (Long) TrackMap.DefaultImpls.getVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
            public Iterator<Long> iterator() {
                return TrackMap.DefaultImpls.iterator(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long videoOrNull() {
                return (Long) TrackMap.DefaultImpls.videoOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Long get(TrackType type) {
                Tracks tracks2;
                DataSources dataSources;
                TrackMap trackMap;
                long durationUs;
                Intrinsics.checkNotNullParameter(type, "type");
                tracks2 = Timer.this.tracks;
                if (tracks2.getActive().has(type)) {
                    Timer timer = Timer.this;
                    dataSources = timer.sources;
                    List<? extends DataSource> list = dataSources.get(type);
                    trackMap = Timer.this.current;
                    durationUs = timer.durationUs(list, ((Number) trackMap.get(type)).intValue());
                } else {
                    durationUs = 0;
                }
                return Long.valueOf(durationUs);
            }
        };
        this.progress = new TrackMap<Double>() { // from class: com.otaliastudios.transcoder.internal.Timer$progress$1
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean has(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Double audioOrNull() {
                return (Double) TrackMap.DefaultImpls.audioOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Double getAudio() {
                return (Double) TrackMap.DefaultImpls.getAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasAudio() {
                return TrackMap.DefaultImpls.getHasAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasVideo() {
                return TrackMap.DefaultImpls.getHasVideo(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Double getOrNull(TrackType trackType) {
                return (Double) TrackMap.DefaultImpls.getOrNull(this, trackType);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public int getSize() {
                return TrackMap.DefaultImpls.getSize(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Double getVideo() {
                return (Double) TrackMap.DefaultImpls.getVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
            public Iterator<Double> iterator() {
                return TrackMap.DefaultImpls.iterator(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Double videoOrNull() {
                return (Double) TrackMap.DefaultImpls.videoOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Double get(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                long longValue = Timer.this.getPositionUs().get(type).longValue();
                long longValue2 = Timer.this.getDurationUs().get(type).longValue();
                return Double.valueOf(longValue2 == 0 ? 0.0d : longValue / longValue2);
            }
        };
        this.interpolators = new LinkedHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long durationUs(List<? extends DataSource> list, int i) {
        long j = 0;
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DataSource dataSource = (DataSource) obj;
            j += i2 < i ? dataSource.getPositionUs() : dataSource.getDurationUs();
            i2 = i3;
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long positionUs(List<? extends DataSource> list, int i) {
        long j = 0;
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DataSource dataSource = (DataSource) obj;
            if (i2 <= i) {
                j += dataSource.getPositionUs();
            }
            i2 = i3;
        }
        return j;
    }

    public final TrackMap<Long> getPositionUs() {
        return this.positionUs;
    }

    public final TrackMap<Long> getDurationUs() {
        return this.durationUs;
    }

    public final long getTotalDurationUs() {
        return Math.min(this.tracks.getActive().getHasVideo() ? this.durationUs.getVideo().longValue() : Long.MAX_VALUE, this.tracks.getActive().getHasAudio() ? this.durationUs.getAudio().longValue() : Long.MAX_VALUE);
    }

    public final TrackMap<Double> getProgress() {
        return this.progress;
    }

    public final Long localize(TrackType type, int index, long positionUs) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!this.tracks.getActive().has(type)) {
            return null;
        }
        List<? extends DataSource> list = this.sources.get(type);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (i < index) {
                arrayList.add(obj);
            }
            i = i2;
        }
        long durationUs = positionUs - durationUs(arrayList, -1);
        if (durationUs >= 0 && durationUs <= this.sources.get(type).get(index).getDurationUs()) {
            return Long.valueOf(durationUs);
        }
        return null;
    }

    public final TimeInterpolator interpolator(final TrackType type, final int index) {
        Intrinsics.checkNotNullParameter(type, "type");
        Map<Pair<TrackType, Integer>, TimeInterpolator> map = this.interpolators;
        Pair<TrackType, Integer> pair = TuplesKt.to(type, Integer.valueOf(index));
        TimeInterpolator timeInterpolator = map.get(pair);
        if (timeInterpolator == null) {
            timeInterpolator = new TimeInterpolator(index, this, type) { // from class: com.otaliastudios.transcoder.internal.Timer$interpolator$1$1
                final /* synthetic */ int $index;
                final /* synthetic */ TrackType $type;
                private long firstIn = Long.MAX_VALUE;
                private final long firstOut;
                private long lastOut;
                final /* synthetic */ Timer this$0;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    Map map2;
                    long interpolate;
                    this.$index = index;
                    this.this$0 = this;
                    this.$type = type;
                    if (index == 0) {
                        interpolate = 0;
                    } else {
                        map2 = this.interpolators;
                        Object obj = map2.get(TuplesKt.to(type, Integer.valueOf(index - 1)));
                        Intrinsics.checkNotNull(obj);
                        interpolate = ((TimeInterpolator) obj).interpolate(type, Long.MAX_VALUE) + 10;
                    }
                    this.firstOut = interpolate;
                }

                @Override // com.otaliastudios.transcoder.time.TimeInterpolator
                public long interpolate(TrackType type2, long time) {
                    TimeInterpolator timeInterpolator2;
                    Intrinsics.checkNotNullParameter(type2, "type");
                    if (time == Long.MAX_VALUE) {
                        return this.lastOut;
                    }
                    if (this.firstIn == Long.MAX_VALUE) {
                        this.firstIn = time;
                    }
                    this.lastOut = this.firstOut + (time - this.firstIn);
                    timeInterpolator2 = this.this$0.interpolator;
                    return timeInterpolator2.interpolate(type2, this.lastOut);
                }
            };
            map.put(pair, timeInterpolator);
        }
        return timeInterpolator;
    }
}
