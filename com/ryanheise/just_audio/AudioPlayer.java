package com.ryanheise.just_audio;

import android.content.Context;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.Equalizer;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LivePlaybackSpeedControl;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.ConcatenatingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.SilenceMediaSource;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.media3.extractor.metadata.icy.IcyInfo;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.flutter.Log;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.editing.SpellCheckPlugin;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* loaded from: classes5.dex */
public class AudioPlayer implements MethodChannel.MethodCallHandler, Player.Listener, MetadataOutput {
    static final String TAG = "AudioPlayer";
    private static Random random = new Random();
    private Integer audioSessionId;
    private long bufferedPosition;
    private final Context context;
    private Integer currentIndex;
    private final BetterEventChannel dataEventChannel;
    private int errorCount;
    private final BetterEventChannel eventChannel;
    private IcyHeaders icyHeaders;
    private IcyInfo icyInfo;
    private Integer initialIndex;
    private long initialPos;
    private LivePlaybackSpeedControl livePlaybackSpeedControl;
    private LoadControl loadControl;
    private MediaSource mediaSource;
    private final MethodChannel methodChannel;
    private boolean offloadSchedulingEnabled;
    private AudioAttributes pendingAudioAttributes;
    private Map<String, Object> pendingPlaybackEvent;
    private MethodChannel.Result playResult;
    private ExoPlayer player;
    private MethodChannel.Result prepareResult;
    private ProcessingState processingState;
    private List<Object> rawAudioEffects;
    private Long seekPos;
    private MethodChannel.Result seekResult;
    private long updatePosition;
    private long updateTime;
    private Map<String, MediaSource> mediaSources = new HashMap();
    private List<AudioEffect> audioEffects = new ArrayList();
    private Map<String, AudioEffect> audioEffectsMap = new HashMap();
    private int lastPlaylistLength = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable bufferWatcher = new Runnable() { // from class: com.ryanheise.just_audio.AudioPlayer.1
        @Override // java.lang.Runnable
        public void run() {
            if (AudioPlayer.this.player == null) {
                return;
            }
            if (AudioPlayer.this.player.getBufferedPosition() != AudioPlayer.this.bufferedPosition) {
                AudioPlayer.this.broadcastImmediatePlaybackEvent();
            }
            int playbackState = AudioPlayer.this.player.getPlaybackState();
            if (playbackState == 2) {
                AudioPlayer.this.handler.postDelayed(this, 200L);
            } else {
                if (playbackState != 3) {
                    return;
                }
                if (AudioPlayer.this.player.getPlayWhenReady()) {
                    AudioPlayer.this.handler.postDelayed(this, 500L);
                } else {
                    AudioPlayer.this.handler.postDelayed(this, 1000L);
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum ProcessingState {
        none,
        loading,
        buffering,
        ready,
        completed
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
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
    public /* synthetic */ void onIsPlayingChanged(boolean z) {
        Player.Listener.CC.$default$onIsPlayingChanged(this, z);
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
    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
        Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
    }

    @Override // androidx.media3.common.Player.Listener
    public /* synthetic */ void onVolumeChanged(float f) {
        Player.Listener.CC.$default$onVolumeChanged(this, f);
    }

    public AudioPlayer(Context context, BinaryMessenger binaryMessenger, String str, Map<?, ?> map, List<Object> list, Boolean bool) {
        this.context = context;
        this.rawAudioEffects = list;
        this.offloadSchedulingEnabled = bool != null ? bool.booleanValue() : false;
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "com.ryanheise.just_audio.methods." + str);
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        this.eventChannel = new BetterEventChannel(binaryMessenger, "com.ryanheise.just_audio.events." + str);
        this.dataEventChannel = new BetterEventChannel(binaryMessenger, "com.ryanheise.just_audio.data." + str);
        this.processingState = ProcessingState.none;
        if (map != null) {
            Map map2 = (Map) map.get("androidLoadControl");
            if (map2 != null) {
                DefaultLoadControl.Builder backBuffer = new DefaultLoadControl.Builder().setBufferDurationsMs((int) (getLong(map2.get("minBufferDuration")).longValue() / 1000), (int) (getLong(map2.get("maxBufferDuration")).longValue() / 1000), (int) (getLong(map2.get("bufferForPlaybackDuration")).longValue() / 1000), (int) (getLong(map2.get("bufferForPlaybackAfterRebufferDuration")).longValue() / 1000)).setPrioritizeTimeOverSizeThresholds(((Boolean) map2.get("prioritizeTimeOverSizeThresholds")).booleanValue()).setBackBuffer((int) (getLong(map2.get("backBufferDuration")).longValue() / 1000), false);
                if (map2.get("targetBufferBytes") != null) {
                    backBuffer.setTargetBufferBytes(((Integer) map2.get("targetBufferBytes")).intValue());
                }
                this.loadControl = backBuffer.build();
            }
            Map map3 = (Map) map.get("androidLivePlaybackSpeedControl");
            if (map3 != null) {
                this.livePlaybackSpeedControl = new DefaultLivePlaybackSpeedControl.Builder().setFallbackMinPlaybackSpeed((float) ((Double) map3.get("fallbackMinPlaybackSpeed")).doubleValue()).setFallbackMaxPlaybackSpeed((float) ((Double) map3.get("fallbackMaxPlaybackSpeed")).doubleValue()).setMinUpdateIntervalMs(getLong(map3.get("minUpdateInterval")).longValue() / 1000).setProportionalControlFactor((float) ((Double) map3.get("proportionalControlFactor")).doubleValue()).setMaxLiveOffsetErrorMsForUnitSpeed(getLong(map3.get("maxLiveOffsetErrorForUnitSpeed")).longValue() / 1000).setTargetLiveOffsetIncrementOnRebufferMs(getLong(map3.get("targetLiveOffsetIncrementOnRebuffer")).longValue() / 1000).setMinPossibleLiveOffsetSmoothingFactor((float) ((Double) map3.get("minPossibleLiveOffsetSmoothingFactor")).doubleValue()).build();
            }
        }
    }

    private void startWatchingBuffer() {
        this.handler.removeCallbacks(this.bufferWatcher);
        this.handler.post(this.bufferWatcher);
    }

    private void setAudioSessionId(int i) {
        if (i == 0) {
            this.audioSessionId = null;
        } else {
            this.audioSessionId = Integer.valueOf(i);
        }
        clearAudioEffects();
        if (this.audioSessionId != null) {
            for (Object obj : this.rawAudioEffects) {
                Map map = (Map) obj;
                AudioEffect decodeAudioEffect = decodeAudioEffect(obj, this.audioSessionId.intValue());
                if (((Boolean) map.get("enabled")).booleanValue()) {
                    decodeAudioEffect.setEnabled(true);
                }
                this.audioEffects.add(decodeAudioEffect);
                this.audioEffectsMap.put((String) map.get("type"), decodeAudioEffect);
            }
        }
        enqueuePlaybackEvent();
    }

    @Override // androidx.media3.common.Player.Listener
    public void onAudioSessionIdChanged(int i) {
        setAudioSessionId(i);
        broadcastPendingPlaybackEvent();
    }

    @Override // androidx.media3.common.Player.Listener
    public void onMetadata(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof IcyInfo) {
                this.icyInfo = (IcyInfo) entry;
                broadcastImmediatePlaybackEvent();
            }
        }
    }

    @Override // androidx.media3.common.Player.Listener
    public void onTracksChanged(Tracks tracks) {
        for (int i = 0; i < tracks.getGroups().size(); i++) {
            TrackGroup mediaTrackGroup = tracks.getGroups().get(i).getMediaTrackGroup();
            for (int i2 = 0; i2 < mediaTrackGroup.length; i2++) {
                Metadata metadata = mediaTrackGroup.getFormat(i2).metadata;
                if (metadata != null) {
                    for (int i3 = 0; i3 < metadata.length(); i3++) {
                        Metadata.Entry entry = metadata.get(i3);
                        if (entry instanceof IcyHeaders) {
                            this.icyHeaders = (IcyHeaders) entry;
                            broadcastImmediatePlaybackEvent();
                        }
                    }
                }
            }
        }
    }

    private boolean updatePositionIfChanged() {
        if (getCurrentPosition() == this.updatePosition) {
            return false;
        }
        this.updatePosition = getCurrentPosition();
        this.updateTime = System.currentTimeMillis();
        return true;
    }

    private void updatePosition() {
        this.updatePosition = getCurrentPosition();
        this.updateTime = System.currentTimeMillis();
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        updatePosition();
        if (i == 0 || i == 1) {
            updateCurrentIndex();
        }
        broadcastImmediatePlaybackEvent();
    }

    @Override // androidx.media3.common.Player.Listener
    public void onTimelineChanged(Timeline timeline, int i) {
        if (this.initialPos != C.TIME_UNSET || this.initialIndex != null) {
            Integer num = this.initialIndex;
            this.player.seekTo(num != null ? num.intValue() : 0, this.initialPos);
            this.initialIndex = null;
            this.initialPos = C.TIME_UNSET;
        }
        if (updateCurrentIndex()) {
            broadcastImmediatePlaybackEvent();
        }
        if (this.player.getPlaybackState() == 4) {
            try {
                if (this.player.getPlayWhenReady()) {
                    if (this.lastPlaylistLength == 0 && this.player.getMediaItemCount() > 0) {
                        this.player.seekTo(0, 0L);
                    } else if (this.player.hasNextMediaItem()) {
                        this.player.seekToNextMediaItem();
                    }
                } else if (this.player.getCurrentMediaItemIndex() < this.player.getMediaItemCount()) {
                    ExoPlayer exoPlayer = this.player;
                    exoPlayer.seekTo(exoPlayer.getCurrentMediaItemIndex(), 0L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.lastPlaylistLength = this.player.getMediaItemCount();
    }

    private boolean updateCurrentIndex() {
        Integer valueOf = Integer.valueOf(this.player.getCurrentMediaItemIndex());
        if (valueOf.equals(this.currentIndex)) {
            return false;
        }
        this.currentIndex = valueOf;
        return true;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlaybackStateChanged(int i) {
        if (i == 2) {
            updatePositionIfChanged();
            if (this.processingState != ProcessingState.buffering && this.processingState != ProcessingState.loading) {
                this.processingState = ProcessingState.buffering;
                broadcastImmediatePlaybackEvent();
            }
            startWatchingBuffer();
            return;
        }
        if (i == 3) {
            if (this.player.getPlayWhenReady()) {
                updatePosition();
            }
            this.processingState = ProcessingState.ready;
            broadcastImmediatePlaybackEvent();
            if (this.prepareResult != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("duration", getDuration() == C.TIME_UNSET ? null : Long.valueOf(getDuration() * 1000));
                this.prepareResult.success(hashMap);
                this.prepareResult = null;
                AudioAttributes audioAttributes = this.pendingAudioAttributes;
                if (audioAttributes != null) {
                    this.player.setAudioAttributes(audioAttributes, false);
                    this.pendingAudioAttributes = null;
                }
            }
            if (this.seekResult != null) {
                completeSeek();
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        if (this.processingState != ProcessingState.completed) {
            updatePosition();
            this.processingState = ProcessingState.completed;
            broadcastImmediatePlaybackEvent();
        }
        if (this.prepareResult != null) {
            this.prepareResult.success(new HashMap());
            this.prepareResult = null;
            AudioAttributes audioAttributes2 = this.pendingAudioAttributes;
            if (audioAttributes2 != null) {
                this.player.setAudioAttributes(audioAttributes2, false);
                this.pendingAudioAttributes = null;
            }
        }
        MethodChannel.Result result = this.playResult;
        if (result != null) {
            result.success(new HashMap());
            this.playResult = null;
        }
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlayerError(PlaybackException playbackException) {
        Integer num;
        int intValue;
        if (playbackException instanceof ExoPlaybackException) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) playbackException;
            int i = exoPlaybackException.type;
            if (i == 0) {
                Log.e(TAG, "TYPE_SOURCE: " + exoPlaybackException.getSourceException().getMessage());
            } else if (i == 1) {
                Log.e(TAG, "TYPE_RENDERER: " + exoPlaybackException.getRendererException().getMessage());
            } else if (i == 2) {
                Log.e(TAG, "TYPE_UNEXPECTED: " + exoPlaybackException.getUnexpectedException().getMessage());
            } else {
                Log.e(TAG, "default ExoPlaybackException: " + exoPlaybackException.getUnexpectedException().getMessage());
            }
            sendError(String.valueOf(exoPlaybackException.type), exoPlaybackException.getMessage(), mapOf(FirebaseAnalytics.Param.INDEX, this.currentIndex));
        } else {
            Log.e(TAG, "default PlaybackException: " + playbackException.getMessage());
            sendError(String.valueOf(playbackException.errorCode), playbackException.getMessage(), mapOf(FirebaseAnalytics.Param.INDEX, this.currentIndex));
        }
        this.errorCount++;
        if (!this.player.hasNextMediaItem() || (num = this.currentIndex) == null || this.errorCount > 5 || (intValue = num.intValue() + 1) >= this.player.getCurrentTimeline().getWindowCount()) {
            return;
        }
        this.player.setMediaSource(this.mediaSource);
        this.player.prepare();
        this.player.seekTo(intValue, 0L);
    }

    private void completeSeek() {
        this.seekPos = null;
        this.seekResult.success(new HashMap());
        this.seekResult = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        ensurePlayerInitialized();
        try {
            try {
                try {
                    String str = methodCall.method;
                    char c = 65535;
                    switch (str.hashCode()) {
                        case -2058172951:
                            if (str.equals("androidEqualizerBandSetGain")) {
                                c = 21;
                                break;
                            }
                            break;
                        case -1987605894:
                            if (str.equals("setShuffleMode")) {
                                c = '\b';
                                break;
                            }
                            break;
                        case -1875704736:
                            if (str.equals("setSkipSilence")) {
                                c = 6;
                                break;
                            }
                            break;
                        case -1540835818:
                            if (str.equals("concatenatingInsertAll")) {
                                c = 14;
                                break;
                            }
                            break;
                        case -1484304041:
                            if (str.equals("setShuffleOrder")) {
                                c = '\t';
                                break;
                            }
                            break;
                        case -704119678:
                            if (str.equals("setCanUseNetworkResourcesForLiveStreamingWhilePaused")) {
                                c = 11;
                                break;
                            }
                            break;
                        case -345307082:
                            if (str.equals("androidLoudnessEnhancerSetTargetGain")) {
                                c = 19;
                                break;
                            }
                            break;
                        case -104999328:
                            if (str.equals("setAndroidAudioAttributes")) {
                                c = 17;
                                break;
                            }
                            break;
                        case -48357143:
                            if (str.equals("setLoopMode")) {
                                c = 7;
                                break;
                            }
                            break;
                        case 3327206:
                            if (str.equals("load")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 3443508:
                            if (str.equals("play")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 3526264:
                            if (str.equals("seek")) {
                                c = '\r';
                                break;
                            }
                            break;
                        case 106440182:
                            if (str.equals("pause")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 670514716:
                            if (str.equals("setVolume")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 845471111:
                            if (str.equals("concatenatingRemoveRange")) {
                                c = 15;
                                break;
                            }
                            break;
                        case 986980643:
                            if (str.equals("concatenatingMove")) {
                                c = 16;
                                break;
                            }
                            break;
                        case 1401390078:
                            if (str.equals("setPitch")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 1404354821:
                            if (str.equals("setSpeed")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1454606831:
                            if (str.equals("setPreferredPeakBitRate")) {
                                c = '\f';
                                break;
                            }
                            break;
                        case 1624925565:
                            if (str.equals("androidEqualizerGetParameters")) {
                                c = 20;
                                break;
                            }
                            break;
                        case 1631191096:
                            if (str.equals("setAutomaticallyWaitsToMinimizeStalling")) {
                                c = '\n';
                                break;
                            }
                            break;
                        case 2117606630:
                            if (str.equals("audioEffectSetEnabled")) {
                                c = 18;
                                break;
                            }
                            break;
                    }
                    long j = C.TIME_UNSET;
                    switch (c) {
                        case 0:
                            Long l = getLong(methodCall.argument("initialPosition"));
                            Integer num = (Integer) methodCall.argument("initialIndex");
                            MediaSource audioSource = getAudioSource(methodCall.argument("audioSource"));
                            if (l != null) {
                                j = l.longValue() / 1000;
                            }
                            load(audioSource, j, num, result);
                            break;
                        case 1:
                            play(result);
                            break;
                        case 2:
                            pause();
                            result.success(new HashMap());
                            break;
                        case 3:
                            setVolume((float) ((Double) methodCall.argument("volume")).doubleValue());
                            result.success(new HashMap());
                            break;
                        case 4:
                            setSpeed((float) ((Double) methodCall.argument("speed")).doubleValue());
                            result.success(new HashMap());
                            break;
                        case 5:
                            setPitch((float) ((Double) methodCall.argument("pitch")).doubleValue());
                            result.success(new HashMap());
                            break;
                        case 6:
                            setSkipSilenceEnabled(((Boolean) methodCall.argument("enabled")).booleanValue());
                            result.success(new HashMap());
                            break;
                        case 7:
                            setLoopMode(((Integer) methodCall.argument("loopMode")).intValue());
                            result.success(new HashMap());
                            break;
                        case '\b':
                            setShuffleModeEnabled(((Integer) methodCall.argument("shuffleMode")).intValue() == 1);
                            result.success(new HashMap());
                            break;
                        case '\t':
                            setShuffleOrder(methodCall.argument("audioSource"));
                            result.success(new HashMap());
                            break;
                        case '\n':
                            result.success(new HashMap());
                            break;
                        case 11:
                            result.success(new HashMap());
                            break;
                        case '\f':
                            result.success(new HashMap());
                            break;
                        case '\r':
                            Long l2 = getLong(methodCall.argument("position"));
                            Integer num2 = (Integer) methodCall.argument(FirebaseAnalytics.Param.INDEX);
                            if (l2 != null) {
                                j = l2.longValue() / 1000;
                            }
                            seek(j, num2, result);
                            break;
                        case 14:
                            concatenating(methodCall.argument("id")).addMediaSources(((Integer) methodCall.argument(FirebaseAnalytics.Param.INDEX)).intValue(), getAudioSources(methodCall.argument(ViewHierarchyNode.JsonKeys.CHILDREN)), this.handler, new Runnable() { // from class: com.ryanheise.just_audio.AudioPlayer$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    MethodChannel.Result.this.success(new HashMap());
                                }
                            });
                            concatenating(methodCall.argument("id")).setShuffleOrder(decodeShuffleOrder((List) methodCall.argument("shuffleOrder")));
                            break;
                        case 15:
                            concatenating(methodCall.argument("id")).removeMediaSourceRange(((Integer) methodCall.argument(SpellCheckPlugin.START_INDEX_KEY)).intValue(), ((Integer) methodCall.argument(SpellCheckPlugin.END_INDEX_KEY)).intValue(), this.handler, new Runnable() { // from class: com.ryanheise.just_audio.AudioPlayer$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    MethodChannel.Result.this.success(new HashMap());
                                }
                            });
                            concatenating(methodCall.argument("id")).setShuffleOrder(decodeShuffleOrder((List) methodCall.argument("shuffleOrder")));
                            break;
                        case 16:
                            concatenating(methodCall.argument("id")).moveMediaSource(((Integer) methodCall.argument("currentIndex")).intValue(), ((Integer) methodCall.argument("newIndex")).intValue(), this.handler, new Runnable() { // from class: com.ryanheise.just_audio.AudioPlayer$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    MethodChannel.Result.this.success(new HashMap());
                                }
                            });
                            concatenating(methodCall.argument("id")).setShuffleOrder(decodeShuffleOrder((List) methodCall.argument("shuffleOrder")));
                            break;
                        case 17:
                            setAudioAttributes(((Integer) methodCall.argument("contentType")).intValue(), ((Integer) methodCall.argument("flags")).intValue(), ((Integer) methodCall.argument("usage")).intValue());
                            result.success(new HashMap());
                            break;
                        case 18:
                            audioEffectSetEnabled((String) methodCall.argument("type"), ((Boolean) methodCall.argument("enabled")).booleanValue());
                            result.success(new HashMap());
                            break;
                        case 19:
                            loudnessEnhancerSetTargetGain(((Double) methodCall.argument("targetGain")).doubleValue());
                            result.success(new HashMap());
                            break;
                        case 20:
                            result.success(equalizerAudioEffectGetParameters());
                            break;
                        case 21:
                            equalizerBandSetGain(((Integer) methodCall.argument("bandIndex")).intValue(), ((Double) methodCall.argument("gain")).doubleValue());
                            result.success(new HashMap());
                            break;
                        default:
                            result.notImplemented();
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.error("Error: " + e, e.toString(), null);
                }
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                result.error("Illegal state: " + e2.getMessage(), e2.toString(), null);
            }
            broadcastPendingPlaybackEvent();
        } catch (Throwable th) {
            broadcastPendingPlaybackEvent();
            throw th;
        }
    }

    private ShuffleOrder decodeShuffleOrder(List<Integer> list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return new ShuffleOrder.DefaultShuffleOrder(iArr, random.nextLong());
    }

    private static int[] shuffle(int i, Integer num) {
        int[] iArr = new int[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int nextInt = random.nextInt(i3);
            iArr[i2] = iArr[nextInt];
            iArr[nextInt] = i2;
            i2 = i3;
        }
        if (num != null) {
            int i4 = 1;
            while (true) {
                if (i4 >= i) {
                    break;
                }
                if (iArr[i4] == num.intValue()) {
                    int i5 = iArr[0];
                    iArr[0] = iArr[i4];
                    iArr[i4] = i5;
                    break;
                }
                i4++;
            }
        }
        return iArr;
    }

    private ShuffleOrder createShuffleOrder(int i, Integer num) {
        return new ShuffleOrder.DefaultShuffleOrder(shuffle(i, num), random.nextLong());
    }

    private ConcatenatingMediaSource concatenating(Object obj) {
        return (ConcatenatingMediaSource) this.mediaSources.get((String) obj);
    }

    private void setShuffleOrder(Object obj) {
        Map map = (Map) obj;
        MediaSource mediaSource = this.mediaSources.get((String) mapGet(map, "id"));
        if (mediaSource == null) {
            return;
        }
        String str = (String) mapGet(map, "type");
        str.hashCode();
        if (!str.equals("concatenating")) {
            if (str.equals("looping")) {
                setShuffleOrder(mapGet(map, "child"));
            }
        } else {
            ((ConcatenatingMediaSource) mediaSource).setShuffleOrder(decodeShuffleOrder((List) mapGet(map, "shuffleOrder")));
            Iterator it = ((List) mapGet(map, ViewHierarchyNode.JsonKeys.CHILDREN)).iterator();
            while (it.hasNext()) {
                setShuffleOrder(it.next());
            }
        }
    }

    private MediaSource getAudioSource(Object obj) {
        Map map = (Map) obj;
        String str = (String) map.get("id");
        MediaSource mediaSource = this.mediaSources.get(str);
        if (mediaSource != null) {
            return mediaSource;
        }
        MediaSource decodeAudioSource = decodeAudioSource(map);
        this.mediaSources.put(str, decodeAudioSource);
        return decodeAudioSource;
    }

    private DefaultExtractorsFactory buildExtractorsFactory(Map<?, ?> map) {
        boolean z;
        boolean z2;
        int i;
        Map map2;
        DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
        if (map == null || (map2 = (Map) map.get("androidExtractorOptions")) == null) {
            z = false;
            z2 = true;
            i = 0;
        } else {
            z2 = ((Boolean) map2.get("constantBitrateSeekingEnabled")).booleanValue();
            z = ((Boolean) map2.get("constantBitrateSeekingAlwaysEnabled")).booleanValue();
            i = ((Integer) map2.get("mp3Flags")).intValue();
        }
        defaultExtractorsFactory.setConstantBitrateSeekingEnabled(z2);
        defaultExtractorsFactory.setConstantBitrateSeekingAlwaysEnabled(z);
        defaultExtractorsFactory.setMp3ExtractorFlags(i);
        return defaultExtractorsFactory;
    }

    private MediaSource decodeAudioSource(Object obj) {
        Map map = (Map) obj;
        String str = (String) map.get("id");
        String str2 = (String) map.get("type");
        str2.hashCode();
        char c = 65535;
        switch (str2.hashCode()) {
            case -445916622:
                if (str2.equals("concatenating")) {
                    c = 0;
                    break;
                }
                break;
            case 103407:
                if (str2.equals("hls")) {
                    c = 1;
                    break;
                }
                break;
            case 3075986:
                if (str2.equals("dash")) {
                    c = 2;
                    break;
                }
                break;
            case 349937342:
                if (str2.equals("looping")) {
                    c = 3;
                    break;
                }
                break;
            case 918617282:
                if (str2.equals("clipping")) {
                    c = 4;
                    break;
                }
                break;
            case 1131547531:
                if (str2.equals("progressive")) {
                    c = 5;
                    break;
                }
                break;
            case 2092627105:
                if (str2.equals("silence")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new ConcatenatingMediaSource(false, ((Boolean) map.get("useLazyPreparation")).booleanValue(), decodeShuffleOrder((List) mapGet(map, "shuffleOrder")), getAudioSourcesArray(map.get(ViewHierarchyNode.JsonKeys.CHILDREN)));
            case 1:
                return new HlsMediaSource.Factory(buildDataSourceFactory((Map) mapGet(map, "headers"))).createMediaSource(new MediaItem.Builder().setUri(Uri.parse((String) map.get(ShareConstants.MEDIA_URI))).setMimeType(MimeTypes.APPLICATION_M3U8).build());
            case 2:
                return new DashMediaSource.Factory(buildDataSourceFactory((Map) mapGet(map, "headers"))).createMediaSource(new MediaItem.Builder().setUri(Uri.parse((String) map.get(ShareConstants.MEDIA_URI))).setMimeType(MimeTypes.APPLICATION_MPD).setTag(str).build());
            case 3:
                Integer num = (Integer) map.get("count");
                MediaSource audioSource = getAudioSource(map.get("child"));
                int intValue = num.intValue();
                MediaSource[] mediaSourceArr = new MediaSource[intValue];
                for (int i = 0; i < intValue; i++) {
                    mediaSourceArr[i] = audioSource;
                }
                return new ConcatenatingMediaSource(mediaSourceArr);
            case 4:
                Long l = getLong(map.get("start"));
                Long l2 = getLong(map.get(TtmlNode.END));
                return new ClippingMediaSource(getAudioSource(map.get("child")), l != null ? l.longValue() : 0L, l2 != null ? l2.longValue() : Long.MIN_VALUE);
            case 5:
                return new ProgressiveMediaSource.Factory(buildDataSourceFactory((Map) mapGet(map, "headers")), buildExtractorsFactory((Map) mapGet(map, "options"))).createMediaSource(new MediaItem.Builder().setUri(Uri.parse((String) map.get(ShareConstants.MEDIA_URI))).setTag(str).build());
            case 6:
                return new SilenceMediaSource.Factory().setDurationUs(getLong(map.get("duration")).longValue()).setTag(str).createMediaSource();
            default:
                throw new IllegalArgumentException("Unknown AudioSource type: " + map.get("type"));
        }
    }

    private MediaSource[] getAudioSourcesArray(Object obj) {
        List<MediaSource> audioSources = getAudioSources(obj);
        MediaSource[] mediaSourceArr = new MediaSource[audioSources.size()];
        audioSources.toArray(mediaSourceArr);
        return mediaSourceArr;
    }

    private List<MediaSource> getAudioSources(Object obj) {
        if (!(obj instanceof List)) {
            throw new RuntimeException("List expected: " + obj);
        }
        List list = (List) obj;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(getAudioSource(list.get(i)));
        }
        return arrayList;
    }

    private AudioEffect decodeAudioEffect(Object obj, int i) {
        Map map = (Map) obj;
        String str = (String) map.get("type");
        str.hashCode();
        if (str.equals("AndroidEqualizer")) {
            return new Equalizer(0, i);
        }
        if (str.equals("AndroidLoudnessEnhancer")) {
            if (Build.VERSION.SDK_INT < 19) {
                throw new RuntimeException("AndroidLoudnessEnhancer requires minSdkVersion >= 19");
            }
            int round = (int) Math.round(((Double) map.get("targetGain")).doubleValue() * 1000.0d);
            LoudnessEnhancer loudnessEnhancer = new LoudnessEnhancer(i);
            loudnessEnhancer.setTargetGain(round);
            return loudnessEnhancer;
        }
        throw new IllegalArgumentException("Unknown AudioEffect type: " + map.get("type"));
    }

    private void clearAudioEffects() {
        Iterator<AudioEffect> it = this.audioEffects.iterator();
        while (it.hasNext()) {
            it.next().release();
            it.remove();
        }
        this.audioEffectsMap.clear();
    }

    private DataSource.Factory buildDataSourceFactory(Map<?, ?> map) {
        String str;
        Map<String, String> castToStringMap = castToStringMap(map);
        if (castToStringMap != null) {
            str = castToStringMap.remove("User-Agent");
            if (str == null) {
                str = castToStringMap.remove("user-agent");
            }
        } else {
            str = null;
        }
        if (str == null) {
            str = Util.getUserAgent(this.context, "just_audio");
        }
        DefaultHttpDataSource.Factory allowCrossProtocolRedirects = new DefaultHttpDataSource.Factory().setUserAgent(str).setAllowCrossProtocolRedirects(true);
        if (castToStringMap != null && castToStringMap.size() > 0) {
            allowCrossProtocolRedirects.setDefaultRequestProperties(castToStringMap);
        }
        return new DefaultDataSource.Factory(this.context, allowCrossProtocolRedirects);
    }

    private void load(MediaSource mediaSource, long j, Integer num, MethodChannel.Result result) {
        this.initialPos = j;
        this.initialIndex = num;
        this.currentIndex = Integer.valueOf(num != null ? num.intValue() : 0);
        int i = AnonymousClass2.$SwitchMap$com$ryanheise$just_audio$AudioPlayer$ProcessingState[this.processingState.ordinal()];
        if (i != 1) {
            if (i == 2) {
                abortExistingConnection();
                this.player.stop();
            } else {
                this.player.stop();
            }
        }
        this.errorCount = 0;
        this.prepareResult = result;
        updatePosition();
        this.processingState = ProcessingState.loading;
        enqueuePlaybackEvent();
        this.mediaSource = mediaSource;
        this.player.setMediaSource(mediaSource);
        this.player.prepare();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ryanheise.just_audio.AudioPlayer$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$ryanheise$just_audio$AudioPlayer$ProcessingState;

        static {
            int[] iArr = new int[ProcessingState.values().length];
            $SwitchMap$com$ryanheise$just_audio$AudioPlayer$ProcessingState = iArr;
            try {
                iArr[ProcessingState.none.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ryanheise$just_audio$AudioPlayer$ProcessingState[ProcessingState.loading.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void ensurePlayerInitialized() {
        if (this.player == null) {
            ExoPlayer.Builder builder = new ExoPlayer.Builder(this.context);
            LoadControl loadControl = this.loadControl;
            if (loadControl != null) {
                builder.setLoadControl(loadControl);
            }
            LivePlaybackSpeedControl livePlaybackSpeedControl = this.livePlaybackSpeedControl;
            if (livePlaybackSpeedControl != null) {
                builder.setLivePlaybackSpeedControl(livePlaybackSpeedControl);
            }
            ExoPlayer build = builder.build();
            this.player = build;
            build.setTrackSelectionParameters(build.getTrackSelectionParameters().buildUpon().setAudioOffloadPreferences(new TrackSelectionParameters.AudioOffloadPreferences.Builder().setIsGaplessSupportRequired(!this.offloadSchedulingEnabled).setIsSpeedChangeSupportRequired(!this.offloadSchedulingEnabled).setAudioOffloadMode(1).build()).build());
            setAudioSessionId(this.player.getAudioSessionId());
            this.player.addListener(this);
        }
    }

    private void setAudioAttributes(int i, int i2, int i3) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setContentType(i);
        builder.setFlags(i2);
        builder.setUsage(i3);
        AudioAttributes build = builder.build();
        if (this.processingState == ProcessingState.loading) {
            this.pendingAudioAttributes = build;
        } else {
            this.player.setAudioAttributes(build, false);
        }
    }

    private void audioEffectSetEnabled(String str, boolean z) {
        this.audioEffectsMap.get(str).setEnabled(z);
    }

    private void loudnessEnhancerSetTargetGain(double d) {
        ((LoudnessEnhancer) this.audioEffectsMap.get("AndroidLoudnessEnhancer")).setTargetGain((int) Math.round(d * 1000.0d));
    }

    private Map<String, Object> equalizerAudioEffectGetParameters() {
        Equalizer equalizer = (Equalizer) this.audioEffectsMap.get("AndroidEqualizer");
        ArrayList arrayList = new ArrayList();
        for (short s = 0; s < equalizer.getNumberOfBands(); s = (short) (s + 1)) {
            arrayList.add(mapOf(FirebaseAnalytics.Param.INDEX, Short.valueOf(s), "lowerFrequency", Double.valueOf(equalizer.getBandFreqRange(s)[0] / 1000.0d), "upperFrequency", Double.valueOf(equalizer.getBandFreqRange(s)[1] / 1000.0d), "centerFrequency", Double.valueOf(equalizer.getCenterFreq(s) / 1000.0d), "gain", Double.valueOf(equalizer.getBandLevel(s) / 1000.0d)));
        }
        return mapOf("parameters", mapOf("minDecibels", Double.valueOf(equalizer.getBandLevelRange()[0] / 1000.0d), "maxDecibels", Double.valueOf(equalizer.getBandLevelRange()[1] / 1000.0d), "bands", arrayList));
    }

    private void equalizerBandSetGain(int i, double d) {
        ((Equalizer) this.audioEffectsMap.get("AndroidEqualizer")).setBandLevel((short) i, (short) Math.round(d * 1000.0d));
    }

    private Map<String, Object> createPlaybackEvent() {
        HashMap hashMap = new HashMap();
        Long valueOf = getDuration() == C.TIME_UNSET ? null : Long.valueOf(getDuration() * 1000);
        ExoPlayer exoPlayer = this.player;
        this.bufferedPosition = exoPlayer != null ? exoPlayer.getBufferedPosition() : 0L;
        hashMap.put("processingState", Integer.valueOf(this.processingState.ordinal()));
        hashMap.put("updatePosition", Long.valueOf(this.updatePosition * 1000));
        hashMap.put("updateTime", Long.valueOf(this.updateTime));
        hashMap.put("bufferedPosition", Long.valueOf(Math.max(this.updatePosition, this.bufferedPosition) * 1000));
        hashMap.put("icyMetadata", collectIcyMetadata());
        hashMap.put("duration", valueOf);
        hashMap.put("currentIndex", this.currentIndex);
        hashMap.put("androidAudioSessionId", this.audioSessionId);
        return hashMap;
    }

    private void broadcastPendingPlaybackEvent() {
        Map<String, Object> map = this.pendingPlaybackEvent;
        if (map != null) {
            this.eventChannel.success(map);
            this.pendingPlaybackEvent = null;
        }
    }

    private void enqueuePlaybackEvent() {
        new HashMap();
        this.pendingPlaybackEvent = createPlaybackEvent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastImmediatePlaybackEvent() {
        enqueuePlaybackEvent();
        broadcastPendingPlaybackEvent();
    }

    private Map<String, Object> collectIcyMetadata() {
        HashMap hashMap = new HashMap();
        if (this.icyInfo != null) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("title", this.icyInfo.title);
            hashMap2.put("url", this.icyInfo.url);
            hashMap.put("info", hashMap2);
        }
        if (this.icyHeaders != null) {
            HashMap hashMap3 = new HashMap();
            hashMap3.put("bitrate", Integer.valueOf(this.icyHeaders.bitrate));
            hashMap3.put("genre", this.icyHeaders.genre);
            hashMap3.put("name", this.icyHeaders.name);
            hashMap3.put("metadataInterval", Integer.valueOf(this.icyHeaders.metadataInterval));
            hashMap3.put("url", this.icyHeaders.url);
            hashMap3.put("isPublic", Boolean.valueOf(this.icyHeaders.isPublic));
            hashMap.put("headers", hashMap3);
        }
        return hashMap;
    }

    private long getCurrentPosition() {
        long j = this.initialPos;
        if (j != C.TIME_UNSET) {
            return j;
        }
        if (this.processingState == ProcessingState.none || this.processingState == ProcessingState.loading) {
            long currentPosition = this.player.getCurrentPosition();
            if (currentPosition < 0) {
                return 0L;
            }
            return currentPosition;
        }
        Long l = this.seekPos;
        if (l != null && l.longValue() != C.TIME_UNSET) {
            return this.seekPos.longValue();
        }
        return this.player.getCurrentPosition();
    }

    private long getDuration() {
        ExoPlayer exoPlayer;
        return (this.processingState == ProcessingState.none || this.processingState == ProcessingState.loading || (exoPlayer = this.player) == null) ? C.TIME_UNSET : exoPlayer.getDuration();
    }

    private void sendError(String str, String str2) {
        sendError(str, str2, null);
    }

    private void sendError(String str, String str2, Object obj) {
        MethodChannel.Result result = this.prepareResult;
        if (result != null) {
            result.error(str, str2, obj);
            this.prepareResult = null;
        }
        this.eventChannel.error(str, str2, obj);
    }

    private String getLowerCaseExtension(Uri uri) {
        String fragment = uri.getFragment();
        if (fragment == null || !fragment.contains(".")) {
            fragment = uri.getPath();
        }
        return fragment.replaceAll("^.*\\.", "").toLowerCase();
    }

    public void play(MethodChannel.Result result) {
        MethodChannel.Result result2;
        if (this.player.getPlayWhenReady()) {
            result.success(new HashMap());
            return;
        }
        MethodChannel.Result result3 = this.playResult;
        if (result3 != null) {
            result3.success(new HashMap());
        }
        this.playResult = result;
        this.player.setPlayWhenReady(true);
        updatePosition();
        if (this.processingState != ProcessingState.completed || (result2 = this.playResult) == null) {
            return;
        }
        result2.success(new HashMap());
        this.playResult = null;
    }

    public void pause() {
        if (this.player.getPlayWhenReady()) {
            this.player.setPlayWhenReady(false);
            updatePosition();
            MethodChannel.Result result = this.playResult;
            if (result != null) {
                result.success(new HashMap());
                this.playResult = null;
            }
        }
    }

    public void setVolume(float f) {
        this.player.setVolume(f);
    }

    public void setSpeed(float f) {
        PlaybackParameters playbackParameters = this.player.getPlaybackParameters();
        if (playbackParameters.speed == f) {
            return;
        }
        this.player.setPlaybackParameters(new PlaybackParameters(f, playbackParameters.pitch));
        if (this.player.getPlayWhenReady()) {
            updatePosition();
        }
        enqueuePlaybackEvent();
    }

    public void setPitch(float f) {
        PlaybackParameters playbackParameters = this.player.getPlaybackParameters();
        if (playbackParameters.pitch == f) {
            return;
        }
        this.player.setPlaybackParameters(new PlaybackParameters(playbackParameters.speed, f));
        enqueuePlaybackEvent();
    }

    public void setSkipSilenceEnabled(boolean z) {
        this.player.setSkipSilenceEnabled(z);
    }

    public void setLoopMode(int i) {
        this.player.setRepeatMode(i);
    }

    public void setShuffleModeEnabled(boolean z) {
        this.player.setShuffleModeEnabled(z);
    }

    public void seek(long j, Integer num, MethodChannel.Result result) {
        if (this.processingState == ProcessingState.none || this.processingState == ProcessingState.loading) {
            result.success(new HashMap());
            return;
        }
        abortSeek();
        this.seekPos = Long.valueOf(j);
        this.seekResult = result;
        try {
            this.player.seekTo(num != null ? num.intValue() : this.player.getCurrentMediaItemIndex(), j);
        } catch (RuntimeException e) {
            this.seekResult = null;
            this.seekPos = null;
            throw e;
        }
    }

    public void dispose() {
        if (this.processingState == ProcessingState.loading) {
            abortExistingConnection();
        }
        MethodChannel.Result result = this.playResult;
        if (result != null) {
            result.success(new HashMap());
            this.playResult = null;
        }
        this.mediaSources.clear();
        this.mediaSource = null;
        clearAudioEffects();
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.release();
            this.player = null;
            this.processingState = ProcessingState.none;
            broadcastImmediatePlaybackEvent();
        }
        this.eventChannel.endOfStream();
        this.dataEventChannel.endOfStream();
    }

    private void abortSeek() {
        MethodChannel.Result result = this.seekResult;
        if (result != null) {
            try {
                result.success(new HashMap());
            } catch (RuntimeException unused) {
            }
            this.seekResult = null;
            this.seekPos = null;
        }
    }

    private void abortExistingConnection() {
        sendError("abort", "Connection aborted");
    }

    public static Long getLong(Object obj) {
        return (obj == null || (obj instanceof Long)) ? (Long) obj : Long.valueOf(((Integer) obj).intValue());
    }

    static <T> T mapGet(Object obj, String str) {
        if (obj instanceof Map) {
            return (T) ((Map) obj).get(str);
        }
        return null;
    }

    static Map<String, Object> mapOf(Object... objArr) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < objArr.length; i += 2) {
            hashMap.put((String) objArr[i], objArr[i + 1]);
        }
        return hashMap;
    }

    static Map<String, String> castToStringMap(Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Object obj : map.keySet()) {
            hashMap.put((String) obj, (String) map.get(obj));
        }
        return hashMap;
    }
}
