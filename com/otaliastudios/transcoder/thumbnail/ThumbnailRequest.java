package com.otaliastudios.transcoder.thumbnail;

import java.util.List;
import kotlin.Metadata;

/* compiled from: ThumbnailRequest.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\u0006"}, d2 = {"Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "", "locate", "", "", "durationUs", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface ThumbnailRequest {
    List<Long> locate(long durationUs);
}
