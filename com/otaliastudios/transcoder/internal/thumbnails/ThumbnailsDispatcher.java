package com.otaliastudios.transcoder.internal.thumbnails;

import android.os.Handler;
import com.otaliastudios.transcoder.ThumbnailerListener;
import com.otaliastudios.transcoder.ThumbnailerOptions;
import com.otaliastudios.transcoder.thumbnail.Thumbnail;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ThumbnailsDispatcher {
    private final Handler mHandler;
    private final ThumbnailerListener mListener;
    private final List<Thumbnail> mResults = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThumbnailsDispatcher(ThumbnailerOptions thumbnailerOptions) {
        this.mHandler = thumbnailerOptions.getListenerHandler();
        this.mListener = thumbnailerOptions.getListener();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCancel() {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                ThumbnailsDispatcher.this.mListener.onThumbnailsCanceled();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCompletion() {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsDispatcher.2
            @Override // java.lang.Runnable
            public void run() {
                ThumbnailsDispatcher.this.mListener.onThumbnailsCompleted(ThumbnailsDispatcher.this.mResults);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchFailure(final Throwable th) {
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsDispatcher.3
            @Override // java.lang.Runnable
            public void run() {
                ThumbnailsDispatcher.this.mListener.onThumbnailsFailed(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchThumbnail(final Thumbnail thumbnail) {
        this.mResults.add(thumbnail);
        this.mHandler.post(new Runnable() { // from class: com.otaliastudios.transcoder.internal.thumbnails.ThumbnailsDispatcher.4
            @Override // java.lang.Runnable
            public void run() {
                ThumbnailsDispatcher.this.mListener.onThumbnail(thumbnail);
            }
        });
    }
}
