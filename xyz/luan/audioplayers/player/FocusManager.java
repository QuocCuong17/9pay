package xyz.luan.audioplayers.player;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import com.tekartik.sqflite.Constant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import xyz.luan.audioplayers.AudioContextAndroid;

/* compiled from: FocusManager.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B>\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\u0010\rJ\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u0006J\b\u0010\u001c\u001a\u00020\tH\u0002J\u0006\u0010\u001d\u001a\u00020\u0006J\b\u0010\u001e\u001a\u00020\u0006H\u0002R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lxyz/luan/audioplayers/player/FocusManager;", "", "player", "Lxyz/luan/audioplayers/player/WrappedPlayer;", "onGranted", "Lkotlin/Function0;", "", "onLoss", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isTransient", "(Lxyz/luan/audioplayers/player/WrappedPlayer;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "audioFocusChangeListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "audioManager", "Landroid/media/AudioManager;", "getAudioManager", "()Landroid/media/AudioManager;", "context", "Lxyz/luan/audioplayers/AudioContextAndroid;", "handleFocusResult", Constant.PARAM_RESULT, "", "handleStop", "hasAudioFocusRequest", "maybeRequestAudioFocus", "updateAudioFocusRequest", "audioplayers_android_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FocusManager {
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;
    private AudioContextAndroid context;
    private final Function0<Unit> onGranted;
    private final Function1<Boolean, Unit> onLoss;
    private final WrappedPlayer player;

    /* JADX WARN: Multi-variable type inference failed */
    public FocusManager(WrappedPlayer player, Function0<Unit> onGranted, Function1<? super Boolean, Unit> onLoss) {
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(onGranted, "onGranted");
        Intrinsics.checkNotNullParameter(onLoss, "onLoss");
        this.player = player;
        this.onGranted = onGranted;
        this.onLoss = onLoss;
        this.context = player.getContext();
        updateAudioFocusRequest();
    }

    private final boolean hasAudioFocusRequest() {
        return (this.audioFocusRequest == null && this.audioFocusChangeListener == null) ? false : true;
    }

    private final void updateAudioFocusRequest() {
        if (this.context.getAudioFocus() == 0) {
            this.audioFocusRequest = null;
            this.audioFocusChangeListener = null;
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.audioFocusRequest = new AudioFocusRequest.Builder(this.context.getAudioFocus()).setAudioAttributes(this.context.buildAttributes()).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: xyz.luan.audioplayers.player.FocusManager$$ExternalSyntheticLambda0
                @Override // android.media.AudioManager.OnAudioFocusChangeListener
                public final void onAudioFocusChange(int i) {
                    FocusManager.updateAudioFocusRequest$lambda$0(FocusManager.this, i);
                }
            }).build();
        } else {
            this.audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: xyz.luan.audioplayers.player.FocusManager$$ExternalSyntheticLambda1
                @Override // android.media.AudioManager.OnAudioFocusChangeListener
                public final void onAudioFocusChange(int i) {
                    FocusManager.updateAudioFocusRequest$lambda$1(FocusManager.this, i);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateAudioFocusRequest$lambda$0(FocusManager this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleFocusResult(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateAudioFocusRequest$lambda$1(FocusManager this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleFocusResult(i);
    }

    private final AudioManager getAudioManager() {
        return this.player.getAudioManager();
    }

    public final void maybeRequestAudioFocus() {
        if (!Intrinsics.areEqual(this.context, this.player.getContext())) {
            this.context = this.player.getContext();
            updateAudioFocusRequest();
        }
        if (!hasAudioFocusRequest()) {
            this.onGranted.invoke();
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            AudioManager audioManager = getAudioManager();
            AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
            Intrinsics.checkNotNull(audioFocusRequest);
            handleFocusResult(audioManager.requestAudioFocus(audioFocusRequest));
            return;
        }
        handleFocusResult(getAudioManager().requestAudioFocus(this.audioFocusChangeListener, 3, this.context.getAudioFocus()));
    }

    public final void handleStop() {
        if (hasAudioFocusRequest()) {
            if (Build.VERSION.SDK_INT >= 26) {
                AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
                if (audioFocusRequest != null) {
                    getAudioManager().abandonAudioFocusRequest(audioFocusRequest);
                    return;
                }
                return;
            }
            getAudioManager().abandonAudioFocus(this.audioFocusChangeListener);
        }
    }

    private final void handleFocusResult(int result) {
        if (result == -2) {
            this.onLoss.invoke(true);
        } else if (result == -1) {
            this.onLoss.invoke(false);
        } else {
            if (result != 1) {
                return;
            }
            this.onGranted.invoke();
        }
    }
}
