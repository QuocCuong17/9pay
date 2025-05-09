package com.otaliastudios.transcoder.thumbnail;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CoverThumbnailRequest.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/otaliastudios/transcoder/thumbnail/CoverThumbnailRequest;", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "()V", "locate", "", "", "durationUs", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class CoverThumbnailRequest implements ThumbnailRequest {
    @Override // com.otaliastudios.transcoder.thumbnail.ThumbnailRequest
    public List<Long> locate(long durationUs) {
        return CollectionsKt.listOf(0L);
    }
}
