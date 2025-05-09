package com.otaliastudios.transcoder.internal.utils;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.otaliastudios.transcoder.common.TrackType;
import com.otaliastudios.transcoder.internal.utils.MutableTrackMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TrackMap.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0005J\u0016\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\bH\u0096\u0002¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\bH\u0016J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0011R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/otaliastudios/transcoder/internal/utils/DefaultTrackMap;", "T", "Lcom/otaliastudios/transcoder/internal/utils/MutableTrackMap;", "video", "audio", "(Ljava/lang/Object;Ljava/lang/Object;)V", "map", "", "Lcom/otaliastudios/transcoder/common/TrackType;", WorkflowRequestType.Method.GET, "type", "(Lcom/otaliastudios/transcoder/common/TrackType;)Ljava/lang/Object;", "has", "", "set", "", "value", "(Lcom/otaliastudios/transcoder/common/TrackType;Ljava/lang/Object;)V", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class DefaultTrackMap<T> implements MutableTrackMap<T> {
    private final Map<TrackType, T> map;

    public DefaultTrackMap(T t, T t2) {
        this.map = MapsKt.mutableMapOf(TuplesKt.to(TrackType.VIDEO, t), TuplesKt.to(TrackType.AUDIO, t2));
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public T audioOrNull() {
        return (T) MutableTrackMap.DefaultImpls.audioOrNull(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.MutableTrackMap, com.otaliastudios.transcoder.internal.utils.TrackMap
    public T getAudio() {
        return (T) MutableTrackMap.DefaultImpls.getAudio(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public boolean getHasAudio() {
        return MutableTrackMap.DefaultImpls.getHasAudio(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public boolean getHasVideo() {
        return MutableTrackMap.DefaultImpls.getHasVideo(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public T getOrNull(TrackType trackType) {
        return (T) MutableTrackMap.DefaultImpls.getOrNull(this, trackType);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public int getSize() {
        return MutableTrackMap.DefaultImpls.getSize(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.MutableTrackMap, com.otaliastudios.transcoder.internal.utils.TrackMap
    public T getVideo() {
        return (T) MutableTrackMap.DefaultImpls.getVideo(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap, java.lang.Iterable
    public Iterator<T> iterator() {
        return MutableTrackMap.DefaultImpls.iterator(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.MutableTrackMap
    public void reset(T t, T t2) {
        MutableTrackMap.DefaultImpls.reset(this, t, t2);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.MutableTrackMap
    public void setAudio(T t) {
        MutableTrackMap.DefaultImpls.setAudio(this, t);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.MutableTrackMap
    public void setVideo(T t) {
        MutableTrackMap.DefaultImpls.setVideo(this, t);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public T videoOrNull() {
        return (T) MutableTrackMap.DefaultImpls.videoOrNull(this);
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public T get(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        T t = this.map.get(type);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @Override // com.otaliastudios.transcoder.internal.utils.TrackMap
    public boolean has(TrackType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return this.map.get(type) != null;
    }

    @Override // com.otaliastudios.transcoder.internal.utils.MutableTrackMap
    public void set(TrackType type, T value) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.map.put(type, value);
    }
}
