package io.flutter.plugins.videoplayer;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import io.flutter.plugins.videoplayer.VideoAsset;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class HttpVideoAsset extends VideoAsset {
    private static final String DEFAULT_USER_AGENT = "ExoPlayer";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private final Map<String, String> httpHeaders;
    private final VideoAsset.StreamingFormat streamingFormat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpVideoAsset(String str, VideoAsset.StreamingFormat streamingFormat, Map<String, String> map) {
        super(str);
        this.streamingFormat = streamingFormat;
        this.httpHeaders = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.flutter.plugins.videoplayer.VideoAsset
    public MediaItem getMediaItem() {
        MediaItem.Builder uri = new MediaItem.Builder().setUri(this.assetUrl);
        int i = AnonymousClass1.$SwitchMap$io$flutter$plugins$videoplayer$VideoAsset$StreamingFormat[this.streamingFormat.ordinal()];
        String str = i != 1 ? i != 2 ? i != 3 ? null : MimeTypes.APPLICATION_M3U8 : MimeTypes.APPLICATION_MPD : MimeTypes.APPLICATION_SS;
        if (str != null) {
            uri.setMimeType(str);
        }
        return uri.build();
    }

    /* renamed from: io.flutter.plugins.videoplayer.HttpVideoAsset$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$videoplayer$VideoAsset$StreamingFormat;

        static {
            int[] iArr = new int[VideoAsset.StreamingFormat.values().length];
            $SwitchMap$io$flutter$plugins$videoplayer$VideoAsset$StreamingFormat = iArr;
            try {
                iArr[VideoAsset.StreamingFormat.SMOOTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$plugins$videoplayer$VideoAsset$StreamingFormat[VideoAsset.StreamingFormat.DYNAMIC_ADAPTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$flutter$plugins$videoplayer$VideoAsset$StreamingFormat[VideoAsset.StreamingFormat.HTTP_LIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.flutter.plugins.videoplayer.VideoAsset
    public MediaSource.Factory getMediaSourceFactory(Context context) {
        return getMediaSourceFactory(context, new DefaultHttpDataSource.Factory());
    }

    MediaSource.Factory getMediaSourceFactory(Context context, DefaultHttpDataSource.Factory factory) {
        unstableUpdateDataSourceFactory(factory, this.httpHeaders, (this.httpHeaders.isEmpty() || !this.httpHeaders.containsKey("User-Agent")) ? "ExoPlayer" : this.httpHeaders.get("User-Agent"));
        return new DefaultMediaSourceFactory(context).setDataSourceFactory(new DefaultDataSource.Factory(context, factory));
    }

    private static void unstableUpdateDataSourceFactory(DefaultHttpDataSource.Factory factory, Map<String, String> map, String str) {
        factory.setUserAgent(str).setAllowCrossProtocolRedirects(true);
        if (map.isEmpty()) {
            return;
        }
        factory.setDefaultRequestProperties(map);
    }
}
