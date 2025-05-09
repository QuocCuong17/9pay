package xyz.luan.audioplayers.player;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import xyz.luan.audioplayers.AudioContextAndroid;
import xyz.luan.audioplayers.AudioplayersPlugin;
import xyz.luan.audioplayers.source.UrlSource;

/* compiled from: SoundPoolPlayer.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\fJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0006`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lxyz/luan/audioplayers/player/SoundPoolManager;", "", "ref", "Lxyz/luan/audioplayers/AudioplayersPlugin;", "(Lxyz/luan/audioplayers/AudioplayersPlugin;)V", "legacySoundPoolWrapper", "Lxyz/luan/audioplayers/player/SoundPoolWrapper;", "soundPoolWrappers", "Ljava/util/HashMap;", "Landroid/media/AudioAttributes;", "Lkotlin/collections/HashMap;", "createSoundPoolWrapper", "", "maxStreams", "", "audioContext", "Lxyz/luan/audioplayers/AudioContextAndroid;", "dispose", "getSoundPoolWrapper", "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SoundPoolManager {
    private SoundPoolWrapper legacySoundPoolWrapper;
    private final AudioplayersPlugin ref;
    private final HashMap<AudioAttributes, SoundPoolWrapper> soundPoolWrappers;

    public SoundPoolManager(AudioplayersPlugin ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        this.ref = ref;
        this.soundPoolWrappers = new HashMap<>();
    }

    public final void createSoundPoolWrapper(int maxStreams, AudioContextAndroid audioContext) {
        Intrinsics.checkNotNullParameter(audioContext, "audioContext");
        if (Build.VERSION.SDK_INT >= 21) {
            AudioAttributes buildAttributes = audioContext.buildAttributes();
            if (this.soundPoolWrappers.containsKey(buildAttributes)) {
                return;
            }
            SoundPool soundPool = new SoundPool.Builder().setAudioAttributes(buildAttributes).setMaxStreams(maxStreams).build();
            this.ref.handleGlobalLog("Create SoundPool with " + buildAttributes);
            Intrinsics.checkNotNullExpressionValue(soundPool, "soundPool");
            final SoundPoolWrapper soundPoolWrapper = new SoundPoolWrapper(soundPool);
            soundPoolWrapper.getSoundPool().setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: xyz.luan.audioplayers.player.SoundPoolManager$$ExternalSyntheticLambda0
                @Override // android.media.SoundPool.OnLoadCompleteListener
                public final void onLoadComplete(SoundPool soundPool2, int i, int i2) {
                    SoundPoolManager.createSoundPoolWrapper$lambda$1(SoundPoolManager.this, soundPoolWrapper, soundPool2, i, i2);
                }
            });
            this.soundPoolWrappers.put(buildAttributes, soundPoolWrapper);
            return;
        }
        if (this.legacySoundPoolWrapper == null) {
            SoundPool soundPool2 = new SoundPool(maxStreams, 3, 0);
            this.ref.handleGlobalLog("Create legacy SoundPool");
            this.legacySoundPoolWrapper = new SoundPoolWrapper(soundPool2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createSoundPoolWrapper$lambda$1(SoundPoolManager this$0, SoundPoolWrapper soundPoolWrapper, SoundPool soundPool, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(soundPoolWrapper, "$soundPoolWrapper");
        this$0.ref.handleGlobalLog("Loaded " + i);
        SoundPoolPlayer soundPoolPlayer = soundPoolWrapper.getSoundIdToPlayer().get(Integer.valueOf(i));
        UrlSource urlSource = soundPoolPlayer != null ? soundPoolPlayer.getUrlSource() : null;
        if (urlSource != null) {
            TypeIntrinsics.asMutableMap(soundPoolWrapper.getSoundIdToPlayer()).remove(soundPoolPlayer.getSoundId());
            synchronized (soundPoolWrapper.getUrlToPlayers()) {
                List<SoundPoolPlayer> list = soundPoolWrapper.getUrlToPlayers().get(urlSource);
                if (list == null) {
                    list = CollectionsKt.emptyList();
                }
                for (SoundPoolPlayer soundPoolPlayer2 : list) {
                    soundPoolPlayer2.getWrappedPlayer().handleLog("Marking " + soundPoolPlayer2 + " as loaded");
                    soundPoolPlayer2.getWrappedPlayer().setPrepared(true);
                    if (soundPoolPlayer2.getWrappedPlayer().getPlaying()) {
                        soundPoolPlayer2.getWrappedPlayer().handleLog("Delayed start of " + soundPoolPlayer2);
                        soundPoolPlayer2.start();
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final SoundPoolWrapper getSoundPoolWrapper(AudioContextAndroid audioContext) {
        Intrinsics.checkNotNullParameter(audioContext, "audioContext");
        if (Build.VERSION.SDK_INT >= 21) {
            return this.soundPoolWrappers.get(audioContext.buildAttributes());
        }
        return this.legacySoundPoolWrapper;
    }

    public final void dispose() {
        Iterator<Map.Entry<AudioAttributes, SoundPoolWrapper>> it = this.soundPoolWrappers.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().dispose();
        }
        this.soundPoolWrappers.clear();
    }
}
