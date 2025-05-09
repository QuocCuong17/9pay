package io.flutter.plugins.videoplayer;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class VideoAsset {
    protected final String assetUrl;

    /* loaded from: classes5.dex */
    enum StreamingFormat {
        UNKNOWN,
        SMOOTH,
        DYNAMIC_ADAPTIVE,
        HTTP_LIVE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract MediaItem getMediaItem();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract MediaSource.Factory getMediaSourceFactory(Context context);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static VideoAsset fromAssetUrl(String str) {
        if (!str.startsWith("asset:///")) {
            throw new IllegalArgumentException("assetUrl must start with 'asset:///'");
        }
        return new LocalVideoAsset(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static VideoAsset fromRemoteUrl(String str, StreamingFormat streamingFormat, Map<String, String> map) {
        return new HttpVideoAsset(str, streamingFormat, new HashMap(map));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static VideoAsset fromRtspUrl(String str) {
        if (!str.startsWith("rtsp://")) {
            throw new IllegalArgumentException("rtspUrl must start with 'rtsp://'");
        }
        return new RtspVideoAsset(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoAsset(String str) {
        this.assetUrl = str;
    }
}
