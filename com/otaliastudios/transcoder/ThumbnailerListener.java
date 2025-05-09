package com.otaliastudios.transcoder;

import com.otaliastudios.transcoder.thumbnail.Thumbnail;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThumbnailerListener.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lcom/otaliastudios/transcoder/ThumbnailerListener;", "", "onThumbnail", "", "thumbnail", "Lcom/otaliastudios/transcoder/thumbnail/Thumbnail;", "onThumbnailsCanceled", "onThumbnailsCompleted", "thumbnails", "", "onThumbnailsFailed", "exception", "", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public interface ThumbnailerListener {

    /* compiled from: ThumbnailerListener.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static void onThumbnailsCanceled(ThumbnailerListener thumbnailerListener) {
            Intrinsics.checkNotNullParameter(thumbnailerListener, "this");
        }

        public static void onThumbnailsCompleted(ThumbnailerListener thumbnailerListener, List<Thumbnail> thumbnails) {
            Intrinsics.checkNotNullParameter(thumbnailerListener, "this");
            Intrinsics.checkNotNullParameter(thumbnails, "thumbnails");
        }
    }

    void onThumbnail(Thumbnail thumbnail);

    void onThumbnailsCanceled();

    void onThumbnailsCompleted(List<Thumbnail> thumbnails);

    void onThumbnailsFailed(Throwable exception);
}
