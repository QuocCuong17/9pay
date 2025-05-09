package xyz.luan.audioplayers.source;

import android.media.MediaPlayer;
import kotlin.Metadata;
import xyz.luan.audioplayers.player.SoundPoolPlayer;

/* compiled from: Source.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lxyz/luan/audioplayers/source/Source;", "", "setForMediaPlayer", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "setForSoundPool", "soundPoolPlayer", "Lxyz/luan/audioplayers/player/SoundPoolPlayer;", "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface Source {
    void setForMediaPlayer(MediaPlayer mediaPlayer);

    void setForSoundPool(SoundPoolPlayer soundPoolPlayer);
}
