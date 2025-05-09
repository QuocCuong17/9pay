package io.flutter.plugins.videoplayer;

import android.os.Build;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class ExoPlayerEventListener implements Player.Listener {
    private final VideoPlayerCallbacks events;
    private final ExoPlayer exoPlayer;
    private boolean isBuffering;
    private boolean isInitialized;

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onAudioSessionIdChanged(int i) {
        Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
        Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onCues(CueGroup cueGroup) {
        Player.Listener.CC.$default$onCues(this, cueGroup);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onCues(List list) {
        Player.Listener.CC.$default$onCues(this, list);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
        Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onEvents(Player player, Player.Events events) {
        Player.Listener.CC.$default$onEvents(this, player, events);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onIsLoadingChanged(boolean z) {
        Player.Listener.CC.$default$onIsLoadingChanged(this, z);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onLoadingChanged(boolean z) {
        Player.Listener.CC.$default$onLoadingChanged(this, z);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
        Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
        Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onMetadata(Metadata metadata) {
        Player.Listener.CC.$default$onMetadata(this, metadata);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
        Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
        Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
        Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
        Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
        Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPositionDiscontinuity(int i) {
        Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onRenderedFirstFrame() {
        Player.Listener.CC.$default$onRenderedFirstFrame(this);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onRepeatModeChanged(int i) {
        Player.Listener.CC.$default$onRepeatModeChanged(this, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onSeekBackIncrementChanged(long j) {
        Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
        Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
        Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
        Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
        Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
        Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onTracksChanged(Tracks tracks) {
        Player.Listener.CC.$default$onTracksChanged(this, tracks);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
        Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onVolumeChanged(float f) {
        Player.Listener.CC.$default$onVolumeChanged(this, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum RotationDegrees {
        ROTATE_0(0),
        ROTATE_90(90),
        ROTATE_180(180),
        ROTATE_270(270);

        private final int degrees;

        RotationDegrees(int i) {
            this.degrees = i;
        }

        public static RotationDegrees fromDegrees(int i) {
            for (RotationDegrees rotationDegrees : values()) {
                if (rotationDegrees.degrees == i) {
                    return rotationDegrees;
                }
            }
            throw new IllegalArgumentException("Invalid rotation degrees specified: " + i);
        }

        public int getDegrees() {
            return this.degrees;
        }
    }

    ExoPlayerEventListener(ExoPlayer exoPlayer, VideoPlayerCallbacks videoPlayerCallbacks) {
        this(exoPlayer, videoPlayerCallbacks, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExoPlayerEventListener(ExoPlayer exoPlayer, VideoPlayerCallbacks videoPlayerCallbacks, boolean z) {
        this.isBuffering = false;
        this.exoPlayer = exoPlayer;
        this.events = videoPlayerCallbacks;
        this.isInitialized = z;
    }

    private void setBuffering(boolean z) {
        if (this.isBuffering == z) {
            return;
        }
        this.isBuffering = z;
        if (z) {
            this.events.onBufferingStart();
        } else {
            this.events.onBufferingEnd();
        }
    }

    private void sendInitialized() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        VideoSize videoSize = this.exoPlayer.getVideoSize();
        int i = videoSize.width;
        int i2 = videoSize.height;
        int i3 = 0;
        if (i != 0 && i2 != 0) {
            RotationDegrees rotationDegrees = RotationDegrees.ROTATE_0;
            if (Build.VERSION.SDK_INT <= 21) {
                try {
                    rotationDegrees = RotationDegrees.fromDegrees(videoSize.unappliedRotationDegrees);
                    i3 = getRotationCorrectionFromUnappliedRotation(rotationDegrees);
                } catch (IllegalArgumentException unused) {
                    rotationDegrees = RotationDegrees.ROTATE_0;
                }
            } else if (Build.VERSION.SDK_INT >= 29) {
                int rotationCorrectionFromFormat = getRotationCorrectionFromFormat(this.exoPlayer);
                try {
                    rotationDegrees = RotationDegrees.fromDegrees(rotationCorrectionFromFormat);
                    i3 = rotationCorrectionFromFormat;
                } catch (IllegalArgumentException unused2) {
                    rotationDegrees = RotationDegrees.ROTATE_0;
                }
            }
            if (rotationDegrees == RotationDegrees.ROTATE_90 || rotationDegrees == RotationDegrees.ROTATE_270) {
                i = videoSize.height;
                i2 = videoSize.width;
            }
        }
        this.events.onInitialized(i, i2, this.exoPlayer.getDuration(), i3);
    }

    private int getRotationCorrectionFromUnappliedRotation(RotationDegrees rotationDegrees) {
        if (rotationDegrees == RotationDegrees.ROTATE_180) {
            return rotationDegrees.getDegrees();
        }
        return 0;
    }

    private int getRotationCorrectionFromFormat(ExoPlayer exoPlayer) {
        Format videoFormat = exoPlayer.getVideoFormat();
        Objects.requireNonNull(videoFormat);
        return videoFormat.rotationDegrees;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlaybackStateChanged(int i) {
        if (i == 2) {
            setBuffering(true);
            this.events.onBufferingUpdate(this.exoPlayer.getBufferedPosition());
        } else if (i == 3) {
            sendInitialized();
        } else if (i == 4) {
            this.events.onCompleted();
        }
        if (i != 2) {
            setBuffering(false);
        }
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlayerError(PlaybackException playbackException) {
        setBuffering(false);
        if (playbackException.errorCode == 1002) {
            this.exoPlayer.seekToDefaultPosition();
            this.exoPlayer.prepare();
            return;
        }
        this.events.onError("VideoError", "Video player had error " + playbackException, null);
    }

    @Override // androidx.media3.common.Player.Listener
    public void onIsPlayingChanged(boolean z) {
        this.events.onIsPlayingStateUpdate(z);
    }
}
