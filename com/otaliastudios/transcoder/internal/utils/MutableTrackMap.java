package com.otaliastudios.transcoder.internal.utils;

import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.TrackMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackMap.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J!\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00018\u00002\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u000eJ \u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H¦\u0002¢\u0006\u0002\u0010\u0012R$\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "T", "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "value", "audio", "getAudio", "()Ljava/lang/Object;", "setAudio", "(Ljava/lang/Object;)V", "video", "getVideo", "setVideo", "reset", "", "(Ljava/lang/Object;Ljava/lang/Object;)V", "set", "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "(Lcom/otaliastudios/transcoder/common/TrackType;Ljava/lang/Object;)V", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface MutableTrackMap<T> extends TrackMap<T> {
    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    T getAudio();

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    T getVideo();

    void reset(T video, T audio);

    void set(TrackType type, T value);

    void setAudio(T t);

    void setVideo(T t);

    /* compiled from: TrackMap.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static <T> T audioOrNull(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return (T) TrackMap.DefaultImpls.audioOrNull(mutableTrackMap);
        }

        public static <T> boolean getHasAudio(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return TrackMap.DefaultImpls.getHasAudio(mutableTrackMap);
        }

        public static <T> boolean getHasVideo(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return TrackMap.DefaultImpls.getHasVideo(mutableTrackMap);
        }

        public static <T> T getOrNull(MutableTrackMap<T> mutableTrackMap, TrackType type) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            Intrinsics.checkNotNullParameter(type, "type");
            return (T) TrackMap.DefaultImpls.getOrNull(mutableTrackMap, type);
        }

        public static <T> int getSize(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return TrackMap.DefaultImpls.getSize(mutableTrackMap);
        }

        public static <T> Iterator<T> iterator(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return TrackMap.DefaultImpls.iterator(mutableTrackMap);
        }

        public static <T> T videoOrNull(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return (T) TrackMap.DefaultImpls.videoOrNull(mutableTrackMap);
        }

        public static <T> void reset(MutableTrackMap<T> mutableTrackMap, T t, T t2) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            mutableTrackMap.set(TrackType.VIDEO, t);
            mutableTrackMap.set(TrackType.AUDIO, t2);
        }

        public static <T> T getAudio(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return (T) TrackMap.DefaultImpls.getAudio(mutableTrackMap);
        }

        public static <T> void setAudio(MutableTrackMap<T> mutableTrackMap, T t) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            mutableTrackMap.set(TrackType.AUDIO, t);
        }

        public static <T> T getVideo(MutableTrackMap<T> mutableTrackMap) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            return (T) TrackMap.DefaultImpls.getVideo(mutableTrackMap);
        }

        public static <T> void setVideo(MutableTrackMap<T> mutableTrackMap, T t) {
            Intrinsics.checkNotNullParameter(mutableTrackMap, "this");
            mutableTrackMap.set(TrackType.VIDEO, t);
        }
    }
}
