package com.otaliastudios.transcoder.internal.utils;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.otaliastudios.transcoder.common.TrackType;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: TrackMap.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000f\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u0015H¦\u0002¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H&J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0002J\u000f\u0010\u001b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005¨\u0006\u001c"}, d2 = {"Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "T", "", "audio", "getAudio", "()Ljava/lang/Object;", "hasAudio", "", "getHasAudio", "()Z", "hasVideo", "getHasVideo", "size", "", "getSize", "()I", "video", "getVideo", "audioOrNull", WorkflowRequestType.Method.GET, "type", "Lcom/otaliastudios/transcoder/common/TrackType;", "(Lcom/otaliastudios/transcoder/common/TrackType;)Ljava/lang/Object;", "getOrNull", "has", "iterator", "", "videoOrNull", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface TrackMap<T> extends Iterable<T>, KMappedMarker {
    T audioOrNull();

    T get(TrackType type);

    T getAudio();

    boolean getHasAudio();

    boolean getHasVideo();

    T getOrNull(TrackType type);

    int getSize();

    T getVideo();

    boolean has(TrackType type);

    @Override // java.lang.Iterable
    Iterator<T> iterator();

    T videoOrNull();

    /* compiled from: TrackMap.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static <T> T getVideo(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return trackMap.get(TrackType.VIDEO);
        }

        public static <T> T getAudio(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return trackMap.get(TrackType.AUDIO);
        }

        public static <T> boolean getHasVideo(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return trackMap.has(TrackType.VIDEO);
        }

        public static <T> boolean getHasAudio(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return trackMap.has(TrackType.AUDIO);
        }

        public static <T> T getOrNull(TrackMap<T> trackMap, TrackType type) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            Intrinsics.checkNotNullParameter(type, "type");
            if (trackMap.has(type)) {
                return trackMap.get(type);
            }
            return null;
        }

        public static <T> T videoOrNull(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return trackMap.getOrNull(TrackType.VIDEO);
        }

        public static <T> T audioOrNull(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return trackMap.getOrNull(TrackType.AUDIO);
        }

        public static <T> int getSize(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return CollectionsKt.listOfNotNull(trackMap.videoOrNull(), trackMap.audioOrNull()).size();
        }

        public static <T> Iterator<T> iterator(TrackMap<T> trackMap) {
            Intrinsics.checkNotNullParameter(trackMap, "this");
            return CollectionsKt.listOfNotNull(trackMap.videoOrNull(), trackMap.audioOrNull()).iterator();
        }
    }
}
