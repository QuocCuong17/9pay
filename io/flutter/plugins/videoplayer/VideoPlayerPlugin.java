package io.flutter.plugins.videoplayer;

import android.content.Context;
import android.util.LongSparseArray;
import io.flutter.FlutterInjector;
import io.flutter.Log;
import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.videoplayer.Messages;
import io.flutter.plugins.videoplayer.VideoAsset;
import io.flutter.view.TextureRegistry;
import java.util.Objects;

/* loaded from: classes5.dex */
public class VideoPlayerPlugin implements FlutterPlugin, Messages.AndroidVideoPlayerApi {
    private static final String TAG = "VideoPlayerPlugin";
    private FlutterState flutterState;
    private final LongSparseArray<VideoPlayer> videoPlayers = new LongSparseArray<>();
    private final VideoPlayerOptions options = new VideoPlayerOptions();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface KeyForAssetAndPackageName {
        String get(String str, String str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface KeyForAssetFn {
        String get(String str);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        FlutterInjector instance = FlutterInjector.instance();
        Context applicationContext = flutterPluginBinding.getApplicationContext();
        BinaryMessenger binaryMessenger = flutterPluginBinding.getBinaryMessenger();
        final FlutterLoader flutterLoader = instance.flutterLoader();
        Objects.requireNonNull(flutterLoader);
        KeyForAssetFn keyForAssetFn = new KeyForAssetFn() { // from class: io.flutter.plugins.videoplayer.VideoPlayerPlugin$$ExternalSyntheticLambda1
            @Override // io.flutter.plugins.videoplayer.VideoPlayerPlugin.KeyForAssetFn
            public final String get(String str) {
                return FlutterLoader.this.getLookupKeyForAsset(str);
            }
        };
        final FlutterLoader flutterLoader2 = instance.flutterLoader();
        Objects.requireNonNull(flutterLoader2);
        FlutterState flutterState = new FlutterState(applicationContext, binaryMessenger, keyForAssetFn, new KeyForAssetAndPackageName() { // from class: io.flutter.plugins.videoplayer.VideoPlayerPlugin$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.videoplayer.VideoPlayerPlugin.KeyForAssetAndPackageName
            public final String get(String str, String str2) {
                return FlutterLoader.this.getLookupKeyForAsset(str, str2);
            }
        }, flutterPluginBinding.getTextureRegistry());
        this.flutterState = flutterState;
        flutterState.startListening(this, flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        if (this.flutterState == null) {
            Log.wtf(TAG, "Detached from the engine before registering to it.");
        }
        this.flutterState.stopListening(flutterPluginBinding.getBinaryMessenger());
        this.flutterState = null;
        onDestroy();
    }

    private void disposeAllPlayers() {
        for (int i = 0; i < this.videoPlayers.size(); i++) {
            this.videoPlayers.valueAt(i).dispose();
        }
        this.videoPlayers.clear();
    }

    public void onDestroy() {
        disposeAllPlayers();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void initialize() {
        disposeAllPlayers();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public Long create(Messages.CreateMessage createMessage) {
        VideoAsset fromRemoteUrl;
        String str;
        TextureRegistry.SurfaceProducer createSurfaceProducer = this.flutterState.textureRegistry.createSurfaceProducer();
        EventChannel eventChannel = new EventChannel(this.flutterState.binaryMessenger, "flutter.io/videoPlayer/videoEvents" + createSurfaceProducer.id());
        if (createMessage.getAsset() != null) {
            if (createMessage.getPackageName() != null) {
                str = this.flutterState.keyForAssetAndPackageName.get(createMessage.getAsset(), createMessage.getPackageName());
            } else {
                str = this.flutterState.keyForAsset.get(createMessage.getAsset());
            }
            fromRemoteUrl = VideoAsset.fromAssetUrl("asset:///" + str);
        } else if (createMessage.getUri().startsWith("rtsp://")) {
            fromRemoteUrl = VideoAsset.fromRtspUrl(createMessage.getUri());
        } else {
            VideoAsset.StreamingFormat streamingFormat = VideoAsset.StreamingFormat.UNKNOWN;
            String formatHint = createMessage.getFormatHint();
            if (formatHint != null) {
                formatHint.hashCode();
                char c = 65535;
                switch (formatHint.hashCode()) {
                    case 3680:
                        if (formatHint.equals("ss")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 103407:
                        if (formatHint.equals("hls")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 3075986:
                        if (formatHint.equals("dash")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        streamingFormat = VideoAsset.StreamingFormat.SMOOTH;
                        break;
                    case 1:
                        streamingFormat = VideoAsset.StreamingFormat.HTTP_LIVE;
                        break;
                    case 2:
                        streamingFormat = VideoAsset.StreamingFormat.DYNAMIC_ADAPTIVE;
                        break;
                }
            }
            fromRemoteUrl = VideoAsset.fromRemoteUrl(createMessage.getUri(), streamingFormat, createMessage.getHttpHeaders());
        }
        this.videoPlayers.put(createSurfaceProducer.id(), VideoPlayer.create(this.flutterState.applicationContext, VideoPlayerEventCallbacks.bindTo(eventChannel), createSurfaceProducer, fromRemoteUrl, this.options));
        return Long.valueOf(createSurfaceProducer.id());
    }

    private VideoPlayer getPlayer(long j) {
        VideoPlayer videoPlayer = this.videoPlayers.get(j);
        if (videoPlayer != null) {
            return videoPlayer;
        }
        String str = "No player found with textureId <" + j + ">";
        if (this.videoPlayers.size() == 0) {
            str = str + " and no active players created by the plugin.";
        }
        throw new IllegalStateException(str);
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void dispose(Long l) {
        getPlayer(l.longValue()).dispose();
        this.videoPlayers.remove(l.longValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void setLooping(Long l, Boolean bool) {
        getPlayer(l.longValue()).setLooping(bool.booleanValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void setVolume(Long l, Double d) {
        getPlayer(l.longValue()).setVolume(d.doubleValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void setPlaybackSpeed(Long l, Double d) {
        getPlayer(l.longValue()).setPlaybackSpeed(d.doubleValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void play(Long l) {
        getPlayer(l.longValue()).play();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public Long position(Long l) {
        VideoPlayer player = getPlayer(l.longValue());
        long position = player.getPosition();
        player.sendBufferingUpdate();
        return Long.valueOf(position);
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void seekTo(Long l, Long l2) {
        getPlayer(l.longValue()).seekTo(l2.intValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void pause(Long l) {
        getPlayer(l.longValue()).pause();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.AndroidVideoPlayerApi
    public void setMixWithOthers(Boolean bool) {
        this.options.mixWithOthers = bool.booleanValue();
    }

    /* loaded from: classes5.dex */
    private static final class FlutterState {
        final Context applicationContext;
        final BinaryMessenger binaryMessenger;
        final KeyForAssetFn keyForAsset;
        final KeyForAssetAndPackageName keyForAssetAndPackageName;
        final TextureRegistry textureRegistry;

        FlutterState(Context context, BinaryMessenger binaryMessenger, KeyForAssetFn keyForAssetFn, KeyForAssetAndPackageName keyForAssetAndPackageName, TextureRegistry textureRegistry) {
            this.applicationContext = context;
            this.binaryMessenger = binaryMessenger;
            this.keyForAsset = keyForAssetFn;
            this.keyForAssetAndPackageName = keyForAssetAndPackageName;
            this.textureRegistry = textureRegistry;
        }

        void startListening(VideoPlayerPlugin videoPlayerPlugin, BinaryMessenger binaryMessenger) {
            Messages.AndroidVideoPlayerApi.CC.setUp(binaryMessenger, videoPlayerPlugin);
        }

        void stopListening(BinaryMessenger binaryMessenger) {
            Messages.AndroidVideoPlayerApi.CC.setUp(binaryMessenger, null);
        }
    }
}
