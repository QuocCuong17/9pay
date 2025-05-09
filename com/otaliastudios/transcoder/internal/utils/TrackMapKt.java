package com.otaliastudios.transcoder.internal.utils;

import kotlin.Metadata;

/* compiled from: TrackMap.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\u0004\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u0001H\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\u0007\u001a!\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\n\u001a+\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u0001H\u00022\b\u0010\u0006\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"mutableTrackMapOf", "Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "T", "default", "(Ljava/lang/Object;)Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "video", "audio", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "trackMapOf", "Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "(Ljava/lang/Object;)Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/otaliastudios/transcoder/internal/utils/TrackMap;", "lib_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class TrackMapKt {
    public static final <T> TrackMap<T> trackMapOf(T t) {
        return trackMapOf(t, t);
    }

    public static final <T> TrackMap<T> trackMapOf(T t, T t2) {
        return new DefaultTrackMap(t, t2);
    }

    public static final <T> MutableTrackMap<T> mutableTrackMapOf(T t) {
        return mutableTrackMapOf(t, t);
    }

    public static final <T> MutableTrackMap<T> mutableTrackMapOf(T t, T t2) {
        return new DefaultTrackMap(t, t2);
    }

    public static /* synthetic */ MutableTrackMap mutableTrackMapOf$default(Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 2) != 0) {
            obj2 = null;
        }
        return mutableTrackMapOf(obj, obj2);
    }
}
