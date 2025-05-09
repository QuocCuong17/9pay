package xyz.luan.audioplayers.player;

import android.media.SoundPool;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import xyz.luan.audioplayers.source.UrlSource;

/* compiled from: SoundPoolPlayer.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR#\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\n¨\u0006\u0013"}, d2 = {"Lxyz/luan/audioplayers/player/SoundPoolWrapper;", "", "soundPool", "Landroid/media/SoundPool;", "(Landroid/media/SoundPool;)V", "soundIdToPlayer", "", "", "Lxyz/luan/audioplayers/player/SoundPoolPlayer;", "getSoundIdToPlayer", "()Ljava/util/Map;", "getSoundPool", "()Landroid/media/SoundPool;", "urlToPlayers", "Lxyz/luan/audioplayers/source/UrlSource;", "", "getUrlToPlayers", "dispose", "", "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SoundPoolWrapper {
    private final Map<Integer, SoundPoolPlayer> soundIdToPlayer;
    private final SoundPool soundPool;
    private final Map<UrlSource, List<SoundPoolPlayer>> urlToPlayers;

    public SoundPoolWrapper(SoundPool soundPool) {
        Intrinsics.checkNotNullParameter(soundPool, "soundPool");
        this.soundPool = soundPool;
        Map<Integer, SoundPoolPlayer> synchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(synchronizedMap, "synchronizedMap(mutableM…<Int, SoundPoolPlayer>())");
        this.soundIdToPlayer = synchronizedMap;
        Map<UrlSource, List<SoundPoolPlayer>> synchronizedMap2 = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(synchronizedMap2, "synchronizedMap(mutableM…List<SoundPoolPlayer>>())");
        this.urlToPlayers = synchronizedMap2;
    }

    public final SoundPool getSoundPool() {
        return this.soundPool;
    }

    public final Map<Integer, SoundPoolPlayer> getSoundIdToPlayer() {
        return this.soundIdToPlayer;
    }

    public final Map<UrlSource, List<SoundPoolPlayer>> getUrlToPlayers() {
        return this.urlToPlayers;
    }

    public final void dispose() {
        this.soundPool.release();
        this.soundIdToPlayer.clear();
        this.urlToPlayers.clear();
    }
}
