package io.flutter.plugins.videoplayer;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.exoplayer.ExoPlayer;

/* loaded from: classes5.dex */
final class ExoPlayerState {
    private final PlaybackParameters playbackParameters;
    private final long position;
    private final int repeatMode;
    private final float volume;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExoPlayerState save(ExoPlayer exoPlayer) {
        return new ExoPlayerState(exoPlayer.getCurrentPosition(), exoPlayer.getRepeatMode(), exoPlayer.getVolume(), exoPlayer.getPlaybackParameters());
    }

    private ExoPlayerState(long j, int i, float f, PlaybackParameters playbackParameters) {
        this.position = j;
        this.repeatMode = i;
        this.volume = f;
        this.playbackParameters = playbackParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restore(ExoPlayer exoPlayer) {
        exoPlayer.seekTo(this.position);
        exoPlayer.setRepeatMode(this.repeatMode);
        exoPlayer.setVolume(this.volume);
        exoPlayer.setPlaybackParameters(this.playbackParameters);
    }
}
