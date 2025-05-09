package com.otaliastudios.transcoder.thumbnail;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: UniformThumbnailRequest.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/otaliastudios/transcoder/thumbnail/UniformThumbnailRequest;", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "count", "", "(I)V", "locate", "", "", "durationUs", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class UniformThumbnailRequest implements ThumbnailRequest {
    private final int count;

    public UniformThumbnailRequest(int i) {
        this.count = i;
        if (!(i >= 2)) {
            throw new IllegalArgumentException("At least 2 thumbnails should be requested when using UniformThumbnailRequest.".toString());
        }
    }

    @Override // com.otaliastudios.transcoder.thumbnail.ThumbnailRequest
    public List<Long> locate(long durationUs) {
        ArrayList arrayList = new ArrayList();
        int i = this.count;
        long j = durationUs / (i - 1);
        long j2 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Long.valueOf(j2));
            j2 += j;
        }
        return arrayList;
    }
}
