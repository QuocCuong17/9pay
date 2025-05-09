package com.otaliastudios.transcoder.internal;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.otaliastudios.transcoder.common.TrackStatus;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import io.sentry.protocol.SentryThread;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Codecs.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\n\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/otaliastudios/transcoder/internal/Codecs;", "", "sources", "Lcom/otaliastudios/transcoder/internal/DataSources;", "tracks", "Lcom/otaliastudios/transcoder/internal/Tracks;", SentryThread.JsonKeys.CURRENT, "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "", "(Lcom/otaliastudios/transcoder/internal/DataSources;Lcom/otaliastudios/transcoder/internal/Tracks;Lcom/otaliastudios/transcoder/internal/utils/TrackMap;)V", "encoders", "Lkotlin/Pair;", "Landroid/media/MediaCodec;", "Landroid/view/Surface;", "getEncoders", "()Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "log", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "ownsEncoderStart", "", "getOwnsEncoderStart", "ownsEncoderStop", "getOwnsEncoderStop", "release", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Codecs {
    private final TrackMap<Integer> current;
    private final TrackMap<Pair<MediaCodec, Surface>> encoders;
    private final Logger log;
    private final TrackMap<Boolean> ownsEncoderStart;
    private final TrackMap<Boolean> ownsEncoderStop;
    private final DataSources sources;
    private final Tracks tracks;

    public Codecs(DataSources sources, Tracks tracks, TrackMap<Integer> current) {
        Intrinsics.checkNotNullParameter(sources, "sources");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(current, "current");
        this.sources = sources;
        this.tracks = tracks;
        this.current = current;
        this.log = new Logger("Codecs");
        this.encoders = (TrackMap) new TrackMap<Pair<? extends MediaCodec, ? extends Surface>>() { // from class: com.otaliastudios.transcoder.internal.Codecs$encoders$1

            /* renamed from: lazyAudio$delegate, reason: from kotlin metadata */
            private final Lazy lazyAudio;

            /* renamed from: lazyVideo$delegate, reason: from kotlin metadata */
            private final Lazy lazyVideo;

            /* compiled from: Codecs.kt */
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

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.lazyAudio = LazyKt.lazy(new Function0<Pair>() { // from class: com.otaliastudios.transcoder.internal.Codecs$encoders$1$lazyAudio$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Pair invoke() {
                        Tracks tracks2;
                        tracks2 = Codecs.this.tracks;
                        MediaFormat audio = tracks2.getOutputFormats().getAudio();
                        String string = audio.getString("mime");
                        Intrinsics.checkNotNull(string);
                        MediaCodec createEncoderByType = MediaCodec.createEncoderByType(string);
                        Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(form…(MediaFormat.KEY_MIME)!!)");
                        createEncoderByType.configure(audio, (Surface) null, (MediaCrypto) null, 1);
                        return TuplesKt.to(createEncoderByType, null);
                    }
                });
                this.lazyVideo = LazyKt.lazy(new Function0<Pair<? extends MediaCodec, ? extends Surface>>() { // from class: com.otaliastudios.transcoder.internal.Codecs$encoders$1$lazyVideo$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Pair<? extends MediaCodec, ? extends Surface> invoke() {
                        Tracks tracks2;
                        tracks2 = Codecs.this.tracks;
                        MediaFormat video = tracks2.getOutputFormats().getVideo();
                        String string = video.getString("mime");
                        Intrinsics.checkNotNull(string);
                        MediaCodec createEncoderByType = MediaCodec.createEncoderByType(string);
                        Intrinsics.checkNotNullExpressionValue(createEncoderByType, "createEncoderByType(form…(MediaFormat.KEY_MIME)!!)");
                        createEncoderByType.configure(video, (Surface) null, (MediaCrypto) null, 1);
                        return TuplesKt.to(createEncoderByType, createEncoderByType.createInputSurface());
                    }
                });
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Pair<? extends MediaCodec, ? extends Surface> audioOrNull() {
                return (Pair) TrackMap.DefaultImpls.audioOrNull(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Pair<? extends MediaCodec, ? extends Surface> getAudio() {
                return (Pair) TrackMap.DefaultImpls.getAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasAudio() {
                return TrackMap.DefaultImpls.getHasAudio(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean getHasVideo() {
                return TrackMap.DefaultImpls.getHasVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Pair<? extends MediaCodec, ? extends Surface> getOrNull(TrackType trackType) {
                return (Pair) TrackMap.DefaultImpls.getOrNull(this, trackType);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public int getSize() {
                return TrackMap.DefaultImpls.getSize(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Pair<? extends MediaCodec, ? extends Surface> getVideo() {
                return (Pair) TrackMap.DefaultImpls.getVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
            public Iterator<Pair<MediaCodec, Surface>> iterator() {
                return TrackMap.DefaultImpls.iterator(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Pair<? extends MediaCodec, ? extends Surface> videoOrNull() {
                return (Pair) TrackMap.DefaultImpls.videoOrNull(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean has(TrackType type) {
                Tracks tracks2;
                Intrinsics.checkNotNullParameter(type, "type");
                tracks2 = Codecs.this.tracks;
                return tracks2.getAll().get(type) == TrackStatus.COMPRESSING;
            }

            private final Pair getLazyAudio() {
                return (Pair) this.lazyAudio.getValue();
            }

            private final Pair<MediaCodec, Surface> getLazyVideo() {
                return (Pair) this.lazyVideo.getValue();
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Pair<? extends MediaCodec, ? extends Surface> get(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                if (i == 1) {
                    return getLazyAudio();
                }
                if (i == 2) {
                    return getLazyVideo();
                }
                throw new NoWhenBranchMatchedException();
            }
        };
        this.ownsEncoderStart = new TrackMap<Boolean>() { // from class: com.otaliastudios.transcoder.internal.Codecs$ownsEncoderStart$1
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean has(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean audioOrNull() {
                return (Boolean) TrackMap.DefaultImpls.audioOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean getAudio() {
                return (Boolean) TrackMap.DefaultImpls.getAudio(this);
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
            public Boolean getOrNull(TrackType trackType) {
                return (Boolean) TrackMap.DefaultImpls.getOrNull(this, trackType);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public int getSize() {
                return TrackMap.DefaultImpls.getSize(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean getVideo() {
                return (Boolean) TrackMap.DefaultImpls.getVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
            public Iterator<Boolean> iterator() {
                return TrackMap.DefaultImpls.iterator(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean videoOrNull() {
                return (Boolean) TrackMap.DefaultImpls.videoOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean get(TrackType type) {
                TrackMap trackMap;
                Intrinsics.checkNotNullParameter(type, "type");
                trackMap = Codecs.this.current;
                return Boolean.valueOf(((Number) trackMap.get(type)).intValue() == 0);
            }
        };
        this.ownsEncoderStop = new TrackMap<Boolean>() { // from class: com.otaliastudios.transcoder.internal.Codecs$ownsEncoderStop$1
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public boolean has(TrackType type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean audioOrNull() {
                return (Boolean) TrackMap.DefaultImpls.audioOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean getAudio() {
                return (Boolean) TrackMap.DefaultImpls.getAudio(this);
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
            public Boolean getOrNull(TrackType trackType) {
                return (Boolean) TrackMap.DefaultImpls.getOrNull(this, trackType);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public int getSize() {
                return TrackMap.DefaultImpls.getSize(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean getVideo() {
                return (Boolean) TrackMap.DefaultImpls.getVideo(this);
            }

            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
            public Iterator<Boolean> iterator() {
                return TrackMap.DefaultImpls.iterator(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean videoOrNull() {
                return (Boolean) TrackMap.DefaultImpls.videoOrNull(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
            public Boolean get(TrackType type) {
                TrackMap trackMap;
                DataSources dataSources;
                Intrinsics.checkNotNullParameter(type, "type");
                trackMap = Codecs.this.current;
                int intValue = ((Number) trackMap.get(type)).intValue();
                dataSources = Codecs.this.sources;
                return Boolean.valueOf(intValue == CollectionsKt.getLastIndex(dataSources.get(type)));
            }
        };
    }

    public final TrackMap<Pair<MediaCodec, Surface>> getEncoders() {
        return this.encoders;
    }

    public final TrackMap<Boolean> getOwnsEncoderStart() {
        return this.ownsEncoderStart;
    }

    public final TrackMap<Boolean> getOwnsEncoderStop() {
        return this.ownsEncoderStop;
    }

    public final void release() {
        Iterator<Pair<MediaCodec, Surface>> it = this.encoders.iterator();
        while (it.hasNext()) {
            it.next().getFirst().release();
        }
    }
}
