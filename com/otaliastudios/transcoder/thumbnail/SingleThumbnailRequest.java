package com.otaliastudios.transcoder.thumbnail;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.LongRange;

/* compiled from: SingleThumbnailRequest.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/otaliastudios/transcoder/thumbnail/SingleThumbnailRequest;", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "positionUs", "", "(J)V", "locate", "", "durationUs", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class SingleThumbnailRequest implements ThumbnailRequest {
    private final long positionUs;

    public SingleThumbnailRequest(long j) {
        this.positionUs = j;
    }

    @Override // com.otaliastudios.transcoder.thumbnail.ThumbnailRequest
    public List<Long> locate(long durationUs) {
        long j = this.positionUs;
        boolean z = false;
        if (0 <= j && j <= durationUs) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Thumbnail position is out of range. position=" + this.positionUs + " range=" + new LongRange(0L, durationUs)).toString());
        }
        return CollectionsKt.listOf(Long.valueOf(j));
    }
}
