package io.flutter.plugins.videoplayer;

import android.content.Context;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.exoplayer.ExoPlayer;
import io.flutter.view.TextureRegistry;

/* loaded from: classes5.dex */
final class VideoPlayer implements TextureRegistry.SurfaceProducer.Callback {
    private ExoPlayer exoPlayer = createVideoPlayer();
    private final ExoPlayerProvider exoPlayerProvider;
    private final MediaItem mediaItem;
    private final VideoPlayerOptions options;
    private ExoPlayerState savedStateDuring;
    private final TextureRegistry.SurfaceProducer surfaceProducer;
    private final VideoPlayerCallbacks videoPlayerEvents;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public interface ExoPlayerProvider {
        ExoPlayer get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static VideoPlayer create(final Context context, VideoPlayerCallbacks videoPlayerCallbacks, TextureRegistry.SurfaceProducer surfaceProducer, final VideoAsset videoAsset, VideoPlayerOptions videoPlayerOptions) {
        return new VideoPlayer(new ExoPlayerProvider() { // from class: io.flutter.plugins.videoplayer.VideoPlayer$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.videoplayer.VideoPlayer.ExoPlayerProvider
            public final ExoPlayer get() {
                ExoPlayer build;
                build = new ExoPlayer.Builder(r0).setMediaSourceFactory(videoAsset.getMediaSourceFactory(context)).build();
                return build;
            }
        }, videoPlayerCallbacks, surfaceProducer, videoAsset.getMediaItem(), videoPlayerOptions);
    }

    VideoPlayer(ExoPlayerProvider exoPlayerProvider, VideoPlayerCallbacks videoPlayerCallbacks, TextureRegistry.SurfaceProducer surfaceProducer, MediaItem mediaItem, VideoPlayerOptions videoPlayerOptions) {
        this.exoPlayerProvider = exoPlayerProvider;
        this.videoPlayerEvents = videoPlayerCallbacks;
        this.surfaceProducer = surfaceProducer;
        this.mediaItem = mediaItem;
        this.options = videoPlayerOptions;
        surfaceProducer.setCallback(this);
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer.Callback
    public void onSurfaceCreated() {
        if (this.savedStateDuring != null) {
            ExoPlayer createVideoPlayer = createVideoPlayer();
            this.exoPlayer = createVideoPlayer;
            this.savedStateDuring.restore(createVideoPlayer);
            this.savedStateDuring = null;
        }
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer.Callback
    public void onSurfaceDestroyed() {
        this.savedStateDuring = ExoPlayerState.save(this.exoPlayer);
        this.exoPlayer.release();
    }

    private ExoPlayer createVideoPlayer() {
        ExoPlayer exoPlayer = this.exoPlayerProvider.get();
        exoPlayer.setMediaItem(this.mediaItem);
        exoPlayer.prepare();
        exoPlayer.setVideoSurface(this.surfaceProducer.getSurface());
        exoPlayer.addListener(new ExoPlayerEventListener(exoPlayer, this.videoPlayerEvents, this.savedStateDuring != null));
        setAudioAttributes(exoPlayer, this.options.mixWithOthers);
        return exoPlayer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendBufferingUpdate() {
        this.videoPlayerEvents.onBufferingUpdate(this.exoPlayer.getBufferedPosition());
    }

    private static void setAudioAttributes(ExoPlayer exoPlayer, boolean z) {
        exoPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(3).build(), !z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void play() {
        this.exoPlayer.play();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pause() {
        this.exoPlayer.pause();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLooping(boolean z) {
        this.exoPlayer.setRepeatMode(z ? 2 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVolume(double d) {
        this.exoPlayer.setVolume((float) Math.max(0.0d, Math.min(1.0d, d)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlaybackSpeed(double d) {
        this.exoPlayer.setPlaybackParameters(new PlaybackParameters((float) d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void seekTo(int i) {
        this.exoPlayer.seekTo(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getPosition() {
        return this.exoPlayer.getCurrentPosition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        this.exoPlayer.release();
        this.surfaceProducer.release();
        this.surfaceProducer.setCallback(null);
    }
}
