package io.flutter.plugins.videoplayer;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.rtsp.RtspMediaSource;
import androidx.media3.exoplayer.source.MediaSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class RtspVideoAsset extends VideoAsset {
    /* JADX INFO: Access modifiers changed from: package-private */
    public RtspVideoAsset(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.flutter.plugins.videoplayer.VideoAsset
    public MediaItem getMediaItem() {
        return new MediaItem.Builder().setUri(this.assetUrl).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.flutter.plugins.videoplayer.VideoAsset
    public MediaSource.Factory getMediaSourceFactory(Context context) {
        return new RtspMediaSource.Factory();
    }
}
