package com.otaliastudios.transcoder.thumbnail;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Thumbnail.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/otaliastudios/transcoder/thumbnail/Thumbnail;", "", "request", "Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "positionUs", "", "bitmap", "Landroid/graphics/Bitmap;", "(Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;JLandroid/graphics/Bitmap;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getPositionUs", "()J", "getRequest", "()Lcom/otaliastudios/transcoder/thumbnail/ThumbnailRequest;", "lib_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class Thumbnail {
    private final Bitmap bitmap;
    private final long positionUs;
    private final ThumbnailRequest request;

    public Thumbnail(ThumbnailRequest request, long j, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.request = request;
        this.positionUs = j;
        this.bitmap = bitmap;
    }

    public final ThumbnailRequest getRequest() {
        return this.request;
    }

    public final long getPositionUs() {
        return this.positionUs;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }
}
