package com.otaliastudios.transcoder.strategy;

import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import io.flutter.plugin.platform.PlatformPlugin;

/* loaded from: classes4.dex */
public class DefaultVideoStrategies {
    private DefaultVideoStrategies() {
    }

    public static DefaultVideoStrategy for720x1280() {
        return DefaultVideoStrategy.exact(720, PlatformPlugin.DEFAULT_SYSTEM_UI).bitRate(SilenceSkippingAudioProcessor.DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US).frameRate(30).keyFrameInterval(3.0f).build();
    }

    public static DefaultVideoStrategy for360x480() {
        return DefaultVideoStrategy.exact(360, 480).bitRate(500000L).frameRate(30).keyFrameInterval(3.0f).build();
    }
}
