package xyz.luan.audioplayers.player;

import android.media.MediaPlayer;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import xyz.luan.audioplayers.AudioContextAndroid;
import xyz.luan.audioplayers.source.Source;

/* compiled from: MediaPlayerPlayer.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\r\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010\nJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\tH\u0016J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020\u000fH\u0016J\b\u0010!\u001a\u00020\u000fH\u0016J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020$H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lxyz/luan/audioplayers/player/MediaPlayerPlayer;", "Lxyz/luan/audioplayers/player/Player;", "wrappedPlayer", "Lxyz/luan/audioplayers/player/WrappedPlayer;", "(Lxyz/luan/audioplayers/player/WrappedPlayer;)V", "mediaPlayer", "Landroid/media/MediaPlayer;", "createMediaPlayer", "getCurrentPosition", "", "()Ljava/lang/Integer;", "getDuration", "isLiveStream", "", "pause", "", "prepare", "release", "reset", "seekTo", "position", "setLooping", "looping", "setRate", "rate", "", "setSource", "source", "Lxyz/luan/audioplayers/source/Source;", "setVolume", "leftVolume", "rightVolume", "start", "stop", "updateContext", "context", "Lxyz/luan/audioplayers/AudioContextAndroid;", "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class MediaPlayerPlayer implements Player {
    private final MediaPlayer mediaPlayer;
    private final WrappedPlayer wrappedPlayer;

    public MediaPlayerPlayer(WrappedPlayer wrappedPlayer) {
        Intrinsics.checkNotNullParameter(wrappedPlayer, "wrappedPlayer");
        this.wrappedPlayer = wrappedPlayer;
        this.mediaPlayer = createMediaPlayer(wrappedPlayer);
    }

    private final MediaPlayer createMediaPlayer(final WrappedPlayer wrappedPlayer) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: xyz.luan.audioplayers.player.MediaPlayerPlayer$$ExternalSyntheticLambda3
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer2) {
                MediaPlayerPlayer.createMediaPlayer$lambda$5$lambda$0(WrappedPlayer.this, mediaPlayer2);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: xyz.luan.audioplayers.player.MediaPlayerPlayer$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) {
                MediaPlayerPlayer.createMediaPlayer$lambda$5$lambda$1(WrappedPlayer.this, mediaPlayer2);
            }
        });
        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: xyz.luan.audioplayers.player.MediaPlayerPlayer$$ExternalSyntheticLambda4
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public final void onSeekComplete(MediaPlayer mediaPlayer2) {
                MediaPlayerPlayer.createMediaPlayer$lambda$5$lambda$2(WrappedPlayer.this, mediaPlayer2);
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: xyz.luan.audioplayers.player.MediaPlayerPlayer$$ExternalSyntheticLambda2
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                boolean createMediaPlayer$lambda$5$lambda$3;
                createMediaPlayer$lambda$5$lambda$3 = MediaPlayerPlayer.createMediaPlayer$lambda$5$lambda$3(WrappedPlayer.this, mediaPlayer2, i, i2);
                return createMediaPlayer$lambda$5$lambda$3;
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: xyz.luan.audioplayers.player.MediaPlayerPlayer$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public final void onBufferingUpdate(MediaPlayer mediaPlayer2, int i) {
                MediaPlayerPlayer.createMediaPlayer$lambda$5$lambda$4(WrappedPlayer.this, mediaPlayer2, i);
            }
        });
        wrappedPlayer.getContext().setAttributesOnPlayer(mediaPlayer);
        return mediaPlayer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createMediaPlayer$lambda$5$lambda$0(WrappedPlayer wrappedPlayer, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(wrappedPlayer, "$wrappedPlayer");
        wrappedPlayer.onPrepared();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createMediaPlayer$lambda$5$lambda$1(WrappedPlayer wrappedPlayer, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(wrappedPlayer, "$wrappedPlayer");
        wrappedPlayer.onCompletion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createMediaPlayer$lambda$5$lambda$2(WrappedPlayer wrappedPlayer, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(wrappedPlayer, "$wrappedPlayer");
        wrappedPlayer.onSeekComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean createMediaPlayer$lambda$5$lambda$3(WrappedPlayer wrappedPlayer, MediaPlayer mediaPlayer, int i, int i2) {
        Intrinsics.checkNotNullParameter(wrappedPlayer, "$wrappedPlayer");
        return wrappedPlayer.onError(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createMediaPlayer$lambda$5$lambda$4(WrappedPlayer wrappedPlayer, MediaPlayer mediaPlayer, int i) {
        Intrinsics.checkNotNullParameter(wrappedPlayer, "$wrappedPlayer");
        wrappedPlayer.onBuffering(i);
    }

    @Override // xyz.luan.audioplayers.player.Player
    public Integer getDuration() {
        Integer valueOf = Integer.valueOf(this.mediaPlayer.getDuration());
        if (valueOf.intValue() == -1) {
            return null;
        }
        return valueOf;
    }

    @Override // xyz.luan.audioplayers.player.Player
    public Integer getCurrentPosition() {
        return Integer.valueOf(this.mediaPlayer.getCurrentPosition());
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void setVolume(float leftVolume, float rightVolume) {
        this.mediaPlayer.setVolume(leftVolume, rightVolume);
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void setRate(float rate) {
        if (Build.VERSION.SDK_INT >= 23) {
            MediaPlayer mediaPlayer = this.mediaPlayer;
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(rate));
        } else {
            if (rate == 1.0f) {
                this.mediaPlayer.start();
                return;
            }
            throw new IllegalStateException("Changing the playback rate is only available for Android M/23+ or using LOW_LATENCY mode.".toString());
        }
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void setSource(Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        reset();
        source.setForMediaPlayer(this.mediaPlayer);
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void setLooping(boolean looping) {
        this.mediaPlayer.setLooping(looping);
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void start() {
        setRate(this.wrappedPlayer.getRate());
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void pause() {
        this.mediaPlayer.pause();
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void stop() {
        this.mediaPlayer.stop();
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void release() {
        this.mediaPlayer.reset();
        this.mediaPlayer.release();
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void seekTo(int position) {
        this.mediaPlayer.seekTo(position);
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void updateContext(AudioContextAndroid context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.setAttributesOnPlayer(this.mediaPlayer);
        if (context.getStayAwake()) {
            this.mediaPlayer.setWakeMode(this.wrappedPlayer.getApplicationContext(), 1);
        }
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void prepare() {
        this.mediaPlayer.prepareAsync();
    }

    @Override // xyz.luan.audioplayers.player.Player
    public void reset() {
        this.mediaPlayer.reset();
    }

    @Override // xyz.luan.audioplayers.player.Player
    public boolean isLiveStream() {
        Integer duration = getDuration();
        return duration == null || duration.intValue() == 0;
    }
}
