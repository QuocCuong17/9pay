package xyz.luan.audioplayers.player;

import android.content.Context;
import android.media.AudioManager;
import io.sentry.SentryBaseEvent;
import io.sentry.profilemeasurements.ProfileMeasurement;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import xyz.luan.audioplayers.AudioContextAndroid;
import xyz.luan.audioplayers.AudioplayersPlugin;
import xyz.luan.audioplayers.EventHandler;
import xyz.luan.audioplayers.PlayerMode;
import xyz.luan.audioplayers.ReleaseMode;
import xyz.luan.audioplayers.source.Source;

/* compiled from: WrappedPlayer.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u001a\u0018\u00002\u00020\u0001B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010O\u001a\u00020&H\u0002J\u0006\u0010P\u001a\u00020QJ\r\u0010R\u001a\u0004\u0018\u00010A¢\u0006\u0002\u0010SJ\r\u0010T\u001a\u0004\u0018\u00010A¢\u0006\u0002\u0010SJ\b\u0010U\u001a\u00020&H\u0002J$\u0010V\u001a\u00020Q2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010Y\u001a\u0004\u0018\u00010X2\b\u0010Z\u001a\u0004\u0018\u00010\u0001J\u000e\u0010[\u001a\u00020Q2\u0006\u0010\\\u001a\u00020XJ\b\u0010]\u001a\u00020QH\u0002J\b\u0010^\u001a\u00020AH\u0002J\u000e\u0010_\u001a\u00020Q2\u0006\u0010`\u001a\u00020AJ\u0006\u0010a\u001a\u00020QJ\u0016\u0010b\u001a\u00020#2\u0006\u0010c\u001a\u00020A2\u0006\u0010d\u001a\u00020AJ\u0006\u0010e\u001a\u00020QJ\u0006\u0010f\u001a\u00020QJ\u0006\u0010g\u001a\u00020QJ\u0006\u0010h\u001a\u00020QJ\u0006\u0010i\u001a\u00020QJ\b\u0010j\u001a\u00020QH\u0002J\u000e\u0010k\u001a\u00020Q2\u0006\u0010l\u001a\u00020AJ\u0006\u0010m\u001a\u00020QJ\u000e\u0010n\u001a\u00020Q2\u0006\u0010o\u001a\u00020\u0007J\f\u0010p\u001a\u00020Q*\u00020&H\u0002J\u001c\u0010q\u001a\u00020Q*\u00020&2\u0006\u0010L\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b\"\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010(\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020'@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010$\"\u0004\b/\u00100R$\u00101\u001a\u00020#2\u0006\u0010\u0013\u001a\u00020#@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010$\"\u0004\b3\u00100R$\u00104\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u00108\u001a\u0002072\u0006\u0010\u0013\u001a\u000207@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010=\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010$\"\u0004\b?\u00100R\u001a\u0010@\u001a\u00020AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010G\u001a\u0004\u0018\u00010F2\b\u0010\u0013\u001a\u0004\u0018\u00010F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR$\u0010L\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0017\"\u0004\bN\u0010\u0019¨\u0006r"}, d2 = {"Lxyz/luan/audioplayers/player/WrappedPlayer;", "", "ref", "Lxyz/luan/audioplayers/AudioplayersPlugin;", "eventHandler", "Lxyz/luan/audioplayers/EventHandler;", "context", "Lxyz/luan/audioplayers/AudioContextAndroid;", "soundPoolManager", "Lxyz/luan/audioplayers/player/SoundPoolManager;", "(Lxyz/luan/audioplayers/AudioplayersPlugin;Lxyz/luan/audioplayers/EventHandler;Lxyz/luan/audioplayers/AudioContextAndroid;Lxyz/luan/audioplayers/player/SoundPoolManager;)V", "applicationContext", "Landroid/content/Context;", "getApplicationContext", "()Landroid/content/Context;", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "()Landroid/media/AudioManager;", "value", "", "balance", "getBalance", "()F", "setBalance", "(F)V", "getContext", "()Lxyz/luan/audioplayers/AudioContextAndroid;", "setContext", "(Lxyz/luan/audioplayers/AudioContextAndroid;)V", "getEventHandler", "()Lxyz/luan/audioplayers/EventHandler;", "focusManager", "Lxyz/luan/audioplayers/player/FocusManager;", "isLooping", "", "()Z", "player", "Lxyz/luan/audioplayers/player/Player;", "Lxyz/luan/audioplayers/PlayerMode;", "playerMode", "getPlayerMode", "()Lxyz/luan/audioplayers/PlayerMode;", "setPlayerMode", "(Lxyz/luan/audioplayers/PlayerMode;)V", "playing", "getPlaying", "setPlaying", "(Z)V", "prepared", "getPrepared", "setPrepared", "rate", "getRate", "setRate", "Lxyz/luan/audioplayers/ReleaseMode;", "releaseMode", "getReleaseMode", "()Lxyz/luan/audioplayers/ReleaseMode;", "setReleaseMode", "(Lxyz/luan/audioplayers/ReleaseMode;)V", "released", "getReleased", "setReleased", "shouldSeekTo", "", "getShouldSeekTo", "()I", "setShouldSeekTo", "(I)V", "Lxyz/luan/audioplayers/source/Source;", "source", "getSource", "()Lxyz/luan/audioplayers/source/Source;", "setSource", "(Lxyz/luan/audioplayers/source/Source;)V", "volume", "getVolume", "setVolume", "createPlayer", "dispose", "", "getCurrentPosition", "()Ljava/lang/Integer;", "getDuration", "getOrCreatePlayer", "handleError", "errorCode", "", "errorMessage", "errorDetails", "handleLog", "message", "initPlayer", "maybeGetCurrentPosition", "onBuffering", ProfileMeasurement.UNIT_PERCENT, "onCompletion", "onError", "what", SentryBaseEvent.JsonKeys.EXTRA, "onPrepared", "onSeekComplete", "pause", "play", "release", "requestFocusAndStart", "seek", "position", "stop", "updateAudioContext", "audioContext", "configAndPrepare", "setVolumeAndBalance", "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WrappedPlayer {
    private float balance;
    private AudioContextAndroid context;
    private final EventHandler eventHandler;
    private final FocusManager focusManager;
    private Player player;
    private PlayerMode playerMode;
    private boolean playing;
    private boolean prepared;
    private float rate;
    private final AudioplayersPlugin ref;
    private ReleaseMode releaseMode;
    private boolean released;
    private int shouldSeekTo;
    private final SoundPoolManager soundPoolManager;
    private Source source;
    private float volume;

    /* compiled from: WrappedPlayer.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerMode.values().length];
            try {
                iArr[PlayerMode.MEDIA_PLAYER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlayerMode.LOW_LATENCY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final void onBuffering(int percent) {
    }

    public WrappedPlayer(AudioplayersPlugin ref, EventHandler eventHandler, AudioContextAndroid context, SoundPoolManager soundPoolManager) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(soundPoolManager, "soundPoolManager");
        this.ref = ref;
        this.eventHandler = eventHandler;
        this.context = context;
        this.soundPoolManager = soundPoolManager;
        this.volume = 1.0f;
        this.rate = 1.0f;
        this.releaseMode = ReleaseMode.RELEASE;
        this.playerMode = PlayerMode.MEDIA_PLAYER;
        this.released = true;
        this.shouldSeekTo = -1;
        this.focusManager = new FocusManager(this, new Function0<Unit>() { // from class: xyz.luan.audioplayers.player.WrappedPlayer$focusManager$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:3:0x0008, code lost:
            
                r0 = r1.this$0.player;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                Player player;
                if (!WrappedPlayer.this.getPlaying() || player == null) {
                    return;
                }
                player.start();
            }
        }, new Function1<Boolean, Unit>() { // from class: xyz.luan.audioplayers.player.WrappedPlayer$focusManager$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Player player;
                if (z) {
                    player = WrappedPlayer.this.player;
                    if (player != null) {
                        player.pause();
                        return;
                    }
                    return;
                }
                WrappedPlayer.this.pause();
            }
        });
    }

    public final EventHandler getEventHandler() {
        return this.eventHandler;
    }

    public final AudioContextAndroid getContext() {
        return this.context;
    }

    public final void setContext(AudioContextAndroid audioContextAndroid) {
        Intrinsics.checkNotNullParameter(audioContextAndroid, "<set-?>");
        this.context = audioContextAndroid;
    }

    public final Source getSource() {
        return this.source;
    }

    public final void setSource(Source source) {
        if (!Intrinsics.areEqual(this.source, source)) {
            if (source != null) {
                Player orCreatePlayer = getOrCreatePlayer();
                orCreatePlayer.setSource(source);
                configAndPrepare(orCreatePlayer);
            } else {
                this.released = true;
                setPrepared(false);
                this.playing = false;
                Player player = this.player;
                if (player != null) {
                    player.release();
                }
            }
            this.source = source;
            return;
        }
        this.ref.handlePrepared(this, true);
    }

    public final float getVolume() {
        return this.volume;
    }

    public final void setVolume(float f) {
        Player player;
        if (this.volume == f) {
            return;
        }
        this.volume = f;
        if (this.released || (player = this.player) == null) {
            return;
        }
        setVolumeAndBalance(player, f, this.balance);
    }

    public final float getBalance() {
        return this.balance;
    }

    public final void setBalance(float f) {
        Player player;
        if (this.balance == f) {
            return;
        }
        this.balance = f;
        if (this.released || (player = this.player) == null) {
            return;
        }
        setVolumeAndBalance(player, this.volume, f);
    }

    public final float getRate() {
        return this.rate;
    }

    public final void setRate(float f) {
        Player player;
        if (this.rate == f) {
            return;
        }
        this.rate = f;
        if (!this.playing || (player = this.player) == null) {
            return;
        }
        player.setRate(f);
    }

    public final ReleaseMode getReleaseMode() {
        return this.releaseMode;
    }

    public final void setReleaseMode(ReleaseMode value) {
        Player player;
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.releaseMode != value) {
            this.releaseMode = value;
            if (this.released || (player = this.player) == null) {
                return;
            }
            player.setLooping(isLooping());
        }
    }

    public final boolean isLooping() {
        return this.releaseMode == ReleaseMode.LOOP;
    }

    public final PlayerMode getPlayerMode() {
        return this.playerMode;
    }

    public final void setPlayerMode(PlayerMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.playerMode != value) {
            this.playerMode = value;
            Player player = this.player;
            if (player != null) {
                this.shouldSeekTo = maybeGetCurrentPosition();
                setPrepared(false);
                player.release();
            }
            initPlayer();
        }
    }

    public final boolean getReleased() {
        return this.released;
    }

    public final void setReleased(boolean z) {
        this.released = z;
    }

    public final boolean getPrepared() {
        return this.prepared;
    }

    public final void setPrepared(boolean z) {
        if (this.prepared != z) {
            this.prepared = z;
            this.ref.handlePrepared(this, z);
        }
    }

    public final boolean getPlaying() {
        return this.playing;
    }

    public final void setPlaying(boolean z) {
        this.playing = z;
    }

    public final int getShouldSeekTo() {
        return this.shouldSeekTo;
    }

    public final void setShouldSeekTo(int i) {
        this.shouldSeekTo = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int maybeGetCurrentPosition() {
        Object m1202constructorimpl;
        Integer num;
        Integer currentPosition;
        boolean z;
        try {
            Result.Companion companion = Result.INSTANCE;
            WrappedPlayer wrappedPlayer = this;
            Player player = this.player;
            currentPosition = player != null ? player.getCurrentPosition() : null;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
        }
        if (currentPosition != null && currentPosition.intValue() == 0) {
            z = true;
            if (!z) {
                currentPosition = null;
            }
            m1202constructorimpl = Result.m1202constructorimpl(currentPosition);
            num = (Integer) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
            if (num == null) {
                return num.intValue();
            }
            return -1;
        }
        z = false;
        if (!z) {
        }
        m1202constructorimpl = Result.m1202constructorimpl(currentPosition);
        num = (Integer) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        if (num == null) {
        }
    }

    private final Player getOrCreatePlayer() {
        Player player = this.player;
        if (this.released || player == null) {
            Player createPlayer = createPlayer();
            this.player = createPlayer;
            this.released = false;
            return createPlayer;
        }
        if (!this.prepared) {
            return player;
        }
        player.reset();
        setPrepared(false);
        return player;
    }

    public final void updateAudioContext(AudioContextAndroid audioContext) {
        Intrinsics.checkNotNullParameter(audioContext, "audioContext");
        if (Intrinsics.areEqual(this.context, audioContext)) {
            return;
        }
        if (this.context.getAudioFocus() != 0 && audioContext.getAudioFocus() == 0) {
            this.focusManager.handleStop();
        }
        this.context = AudioContextAndroid.copy$default(audioContext, false, false, 0, 0, 0, 0, 63, null);
        getAudioManager().setMode(this.context.getAudioMode());
        getAudioManager().setSpeakerphoneOn(this.context.isSpeakerphoneOn());
        Player player = this.player;
        if (player != null) {
            player.stop();
            setPrepared(false);
            player.updateContext(this.context);
            Source source = this.source;
            if (source != null) {
                player.setSource(source);
                configAndPrepare(player);
            }
        }
    }

    public final Integer getDuration() {
        Player player;
        if (!this.prepared || (player = this.player) == null) {
            return null;
        }
        return player.getDuration();
    }

    public final Integer getCurrentPosition() {
        Player player;
        if (!this.prepared || (player = this.player) == null) {
            return null;
        }
        return player.getCurrentPosition();
    }

    public final Context getApplicationContext() {
        return this.ref.getApplicationContext();
    }

    public final AudioManager getAudioManager() {
        return this.ref.getAudioManager();
    }

    public final void play() {
        if (this.playing || this.released) {
            return;
        }
        this.playing = true;
        if (this.player == null) {
            initPlayer();
        } else if (this.prepared) {
            requestFocusAndStart();
        }
    }

    private final void requestFocusAndStart() {
        this.focusManager.maybeRequestAudioFocus();
    }

    public final void stop() {
        this.focusManager.handleStop();
        if (this.released) {
            return;
        }
        if (this.releaseMode != ReleaseMode.RELEASE) {
            pause();
            if (this.prepared) {
                Player player = this.player;
                if (player != null && player.isLiveStream()) {
                    Player player2 = this.player;
                    if (player2 != null) {
                        player2.stop();
                    }
                    setPrepared(false);
                    Player player3 = this.player;
                    if (player3 != null) {
                        player3.prepare();
                        return;
                    }
                    return;
                }
                seek(0);
                return;
            }
            return;
        }
        release();
    }

    public final void release() {
        Player player;
        this.focusManager.handleStop();
        if (this.released) {
            return;
        }
        if (this.playing && (player = this.player) != null) {
            player.stop();
        }
        setSource(null);
        this.player = null;
    }

    public final void pause() {
        Player player;
        if (this.playing) {
            this.playing = false;
            if (!this.prepared || (player = this.player) == null) {
                return;
            }
            player.pause();
        }
    }

    public final void seek(int position) {
        if (this.prepared) {
            Player player = this.player;
            if (!(player != null && player.isLiveStream())) {
                Player player2 = this.player;
                if (player2 != null) {
                    player2.seekTo(position);
                }
                position = -1;
            }
        }
        this.shouldSeekTo = position;
    }

    public final void onPrepared() {
        Player player;
        setPrepared(true);
        this.ref.handleDuration(this);
        if (this.playing) {
            requestFocusAndStart();
        }
        if (this.shouldSeekTo >= 0) {
            Player player2 = this.player;
            if ((player2 != null && player2.isLiveStream()) || (player = this.player) == null) {
                return;
            }
            player.seekTo(this.shouldSeekTo);
        }
    }

    public final void onCompletion() {
        if (this.releaseMode != ReleaseMode.LOOP) {
            stop();
        }
        this.ref.handleComplete(this);
    }

    public final void onSeekComplete() {
        this.ref.handleSeekComplete(this);
    }

    public final void handleLog(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.ref.handleLog(this, message);
    }

    public final void handleError(String errorCode, String errorMessage, Object errorDetails) {
        this.ref.handleError(this, errorCode, errorMessage, errorDetails);
    }

    public final boolean onError(int what, int extra) {
        String str;
        String str2;
        if (what == 100) {
            str = "MEDIA_ERROR_SERVER_DIED";
        } else {
            str = "MEDIA_ERROR_UNKNOWN {what:" + what + '}';
        }
        if (extra == Integer.MIN_VALUE) {
            str2 = "MEDIA_ERROR_SYSTEM";
        } else if (extra == -1010) {
            str2 = "MEDIA_ERROR_UNSUPPORTED";
        } else if (extra == -1007) {
            str2 = "MEDIA_ERROR_MALFORMED";
        } else if (extra == -1004) {
            str2 = "MEDIA_ERROR_IO";
        } else if (extra != -110) {
            str2 = "MEDIA_ERROR_UNKNOWN {extra:" + extra + '}';
        } else {
            str2 = "MEDIA_ERROR_TIMED_OUT";
        }
        if (!this.prepared && Intrinsics.areEqual(str2, "MEDIA_ERROR_SYSTEM")) {
            handleError("AndroidAudioError", "Failed to set source. For troubleshooting, see: https://github.com/bluefireteam/audioplayers/blob/main/troubleshooting.md", str + ", " + str2);
        } else {
            setPrepared(false);
            handleError("AndroidAudioError", str, str2);
        }
        return false;
    }

    private final Player createPlayer() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.playerMode.ordinal()];
        if (i == 1) {
            return new MediaPlayerPlayer(this);
        }
        if (i == 2) {
            return new SoundPoolPlayer(this, this.soundPoolManager);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void initPlayer() {
        Player createPlayer = createPlayer();
        this.player = createPlayer;
        Source source = this.source;
        if (source != null) {
            createPlayer.setSource(source);
            configAndPrepare(createPlayer);
        }
    }

    private final void configAndPrepare(Player player) {
        setVolumeAndBalance(player, this.volume, this.balance);
        player.setLooping(isLooping());
        player.prepare();
    }

    private final void setVolumeAndBalance(Player player, float f, float f2) {
        player.setVolume(Math.min(1.0f, 1.0f - f2) * f, Math.min(1.0f, f2 + 1.0f) * f);
    }

    public final void dispose() {
        release();
        this.eventHandler.dispose();
    }
}
