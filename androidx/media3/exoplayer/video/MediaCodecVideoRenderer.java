package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.video.CompositingVideoSinkProvider;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.MoreExecutors;
import com.otaliastudios.transcoder.internal.media.MediaFormatConstants;
import io.flutter.plugin.platform.PlatformPlugin;
import io.sentry.protocol.ViewHierarchyNode;
import java.nio.ByteBuffer;
import java.util.List;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.jmrtd.cbeff.ISO781611;

/* loaded from: classes.dex */
public class MediaCodecVideoRenderer extends MediaCodecRenderer implements VideoFrameReleaseControl.FrameTimingEvaluator {
    private static final int HEVC_MAX_INPUT_SIZE_THRESHOLD = 2097152;
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final long MIN_EARLY_US_LATE_THRESHOLD = -30000;
    private static final long MIN_EARLY_US_VERY_LATE_THRESHOLD = -500000;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, PlatformPlugin.DEFAULT_SYSTEM_UI, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static final long TUNNELING_EOS_PRESENTATION_TIME_US = Long.MAX_VALUE;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private int buffersInCodecCount;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private VideoSize decodedVideoSize;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private Surface displaySurface;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private boolean hasSetVideoSink;
    private boolean haveReportedFirstFrameRenderedForCurrentSurface;
    private long lastFrameReleaseTimeNs;
    private final int maxDroppedFramesToNotify;
    private Size outputResolution;
    private final boolean ownsVideoSink;
    private PlaceholderSurface placeholderSurface;
    private int rendererPriority;
    private VideoSize reportedVideoSize;
    private int scalingMode;
    private long totalVideoFrameProcessingOffsetUs;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    private List<Effect> videoEffects;
    private int videoFrameProcessingOffsetCount;
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo;
    private VideoSink videoSink;
    private final VideoSinkProvider videoSinkProvider;

    protected long getBufferTimestampAdjustmentUs() {
        return 0L;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return TAG;
    }

    protected void onReadyToRegisterVideoSinkInputStream() {
    }

    protected boolean shouldDropBuffersToKeyframe(long j, long j2, boolean z) {
        return j < MIN_EARLY_US_VERY_LATE_THRESHOLD && !z;
    }

    protected boolean shouldDropOutputBuffer(long j, long j2, boolean z) {
        return j < MIN_EARLY_US_LATE_THRESHOLD && !z;
    }

    protected boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return j < MIN_EARLY_US_LATE_THRESHOLD && j2 > SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
    }

    protected boolean shouldSkipBuffersWithIdenticalReleaseTime() {
        return true;
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector) {
        this(context, mediaCodecSelector, 0L);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j) {
        this(context, mediaCodecSelector, j, null, null, 0);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, MediaCodecAdapter.Factory.CC.getDefault(context), mediaCodecSelector, j, false, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, MediaCodecAdapter.Factory.CC.getDefault(context), mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, float f) {
        this(context, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, f, null);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, float f, VideoSinkProvider videoSinkProvider) {
        super(2, factory, mediaCodecSelector, z, f);
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.maxDroppedFramesToNotify = i;
        this.videoSinkProvider = videoSinkProvider;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.ownsVideoSink = videoSinkProvider == null;
        if (videoSinkProvider == null) {
            this.videoFrameReleaseControl = new VideoFrameReleaseControl(applicationContext, this, j);
        } else {
            this.videoFrameReleaseControl = videoSinkProvider.getVideoFrameReleaseControl();
        }
        this.videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.outputResolution = Size.UNKNOWN;
        this.scalingMode = 1;
        this.decodedVideoSize = VideoSize.UNKNOWN;
        this.tunnelingAudioSessionId = 0;
        this.reportedVideoSize = null;
        this.rendererPriority = -1000;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldForceReleaseFrame(long j, long j2) {
        return shouldForceRenderOutputBuffer(j, j2);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldDropFrame(long j, long j2, boolean z) {
        return shouldDropOutputBuffer(j, j2, z);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldIgnoreFrame(long j, long j2, long j3, boolean z, boolean z2) throws ExoPlaybackException {
        return shouldDropBuffersToKeyframe(j, j3, z) && maybeDropBuffersToKeyframe(j2, z2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return RendererCapabilities.CC.create(0);
        }
        boolean z2 = format.drmInitData != null;
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(this.context, mediaCodecSelector, format, z2, false);
        if (z2 && decoderInfos.isEmpty()) {
            decoderInfos = getDecoderInfos(this.context, mediaCodecSelector, format, false, false);
        }
        if (decoderInfos.isEmpty()) {
            return RendererCapabilities.CC.create(1);
        }
        if (!supportsFormatDrm(format)) {
            return RendererCapabilities.CC.create(2);
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
        if (!isFormatSupported) {
            for (int i2 = 1; i2 < decoderInfos.size(); i2++) {
                MediaCodecInfo mediaCodecInfo2 = decoderInfos.get(i2);
                if (mediaCodecInfo2.isFormatSupported(format)) {
                    z = false;
                    isFormatSupported = true;
                    mediaCodecInfo = mediaCodecInfo2;
                    break;
                }
            }
        }
        z = true;
        int i3 = isFormatSupported ? 4 : 3;
        int i4 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
        int i5 = mediaCodecInfo.hardwareAccelerated ? 64 : 0;
        int i6 = z ? 128 : 0;
        if (Util.SDK_INT >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && !Api26.doesDisplaySupportDolbyVision(this.context)) {
            i6 = 256;
        }
        if (isFormatSupported) {
            List<MediaCodecInfo> decoderInfos2 = getDecoderInfos(this.context, mediaCodecSelector, format, z2, true);
            if (!decoderInfos2.isEmpty()) {
                MediaCodecInfo mediaCodecInfo3 = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(decoderInfos2, format).get(0);
                if (mediaCodecInfo3.isFormatSupported(format) && mediaCodecInfo3.isSeamlessAdaptationSupported(format)) {
                    i = 32;
                }
            }
        }
        return RendererCapabilities.CC.create(i3, i4, i, i5, i6);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(this.context, mediaCodecSelector, format, z, this.tunneling), format);
    }

    private static List<MediaCodecInfo> getDecoderInfos(Context context, MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        if (format.sampleMimeType == null) {
            return ImmutableList.of();
        }
        if (Util.SDK_INT >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && !Api26.doesDisplaySupportDolbyVision(context)) {
            List<MediaCodecInfo> alternativeDecoderInfos = MediaCodecUtil.getAlternativeDecoderInfos(mediaCodecSelector, format, z, z2);
            if (!alternativeDecoderInfos.isEmpty()) {
                return alternativeDecoderInfos;
            }
        }
        return MediaCodecUtil.getDecoderInfosSoftMatch(mediaCodecSelector, format, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Api26 {
        private Api26() {
        }

        public static boolean doesDisplaySupportDolbyVision(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Display display = displayManager != null ? displayManager.getDisplay(0) : null;
            if (display == null || !display.isHdr()) {
                return false;
            }
            for (int i : display.getHdrCapabilities().getSupportedHdrTypes()) {
                if (i == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onInit() {
        super.onInit();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        super.onEnabled(z, z2);
        boolean z3 = getConfiguration().tunneling;
        Assertions.checkState((z3 && this.tunnelingAudioSessionId == 0) ? false : true);
        if (this.tunneling != z3) {
            this.tunneling = z3;
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        if (!this.hasSetVideoSink) {
            if ((this.videoEffects != null || !this.ownsVideoSink) && this.videoSink == null) {
                VideoSinkProvider videoSinkProvider = this.videoSinkProvider;
                if (videoSinkProvider == null) {
                    videoSinkProvider = new CompositingVideoSinkProvider.Builder(this.context, this.videoFrameReleaseControl).setClock(getClock()).build();
                }
                this.videoSink = videoSinkProvider.getSink();
            }
            this.hasSetVideoSink = true;
        }
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.setListener(new VideoSink.Listener() { // from class: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.1
                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onVideoSizeChanged(VideoSink videoSink2, VideoSize videoSize) {
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onFirstFrameRendered(VideoSink videoSink2) {
                    Assertions.checkStateNotNull(MediaCodecVideoRenderer.this.displaySurface);
                    MediaCodecVideoRenderer.this.notifyRenderedFirstFrame();
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onFrameDropped(VideoSink videoSink2) {
                    MediaCodecVideoRenderer.this.updateDroppedBufferCounters(0, 1);
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onError(VideoSink videoSink2, VideoSink.VideoSinkException videoSinkException) {
                    MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
                    mediaCodecVideoRenderer.setPendingPlaybackException(mediaCodecVideoRenderer.createRendererException(videoSinkException, videoSinkException.format, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED));
                }
            }, MoreExecutors.directExecutor());
            VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
            if (videoFrameMetadataListener != null) {
                this.videoSink.setVideoFrameMetadataListener(videoFrameMetadataListener);
            }
            if (this.displaySurface != null && !this.outputResolution.equals(Size.UNKNOWN)) {
                this.videoSink.setOutputSurfaceInfo(this.displaySurface, this.outputResolution);
            }
            this.videoSink.setPlaybackSpeed(getPlaybackSpeed());
            List<Effect> list = this.videoEffects;
            if (list != null) {
                this.videoSink.setVideoEffects(list);
            }
            this.videoSink.onRendererEnabled(z2);
            return;
        }
        this.videoFrameReleaseControl.setClock(getClock());
        this.videoFrameReleaseControl.onEnabled(z2);
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public void enableMayRenderStartOfStream() {
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.enableMayRenderStartOfStream();
        } else {
            this.videoFrameReleaseControl.allowReleaseFirstFrameBeforeStarted();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.flush(true);
            this.videoSink.setStreamOffsetAndAdjustmentUs(getOutputStreamOffsetUs(), getBufferTimestampAdjustmentUs());
        }
        super.onPositionReset(j, z);
        if (this.videoSink == null) {
            this.videoFrameReleaseControl.reset();
        }
        if (z) {
            this.videoFrameReleaseControl.join(false);
        }
        maybeSetupTunnelingForFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        VideoSink videoSink;
        return super.isEnded() && ((videoSink = this.videoSink) == null || videoSink.isEnded());
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        PlaceholderSurface placeholderSurface;
        VideoSink videoSink;
        boolean z = super.isReady() && ((videoSink = this.videoSink) == null || videoSink.isReady());
        if (z && (((placeholderSurface = this.placeholderSurface) != null && this.displaySurface == placeholderSurface) || getCodec() == null || this.tunneling)) {
            return true;
        }
        return this.videoFrameReleaseControl.isReady(z);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = getClock().elapsedRealtime();
        this.totalVideoFrameProcessingOffsetUs = 0L;
        this.videoFrameProcessingOffsetCount = 0;
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.onRendererStarted();
        } else {
            this.videoFrameReleaseControl.onStarted();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onStopped() {
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.onRendererStopped();
        } else {
            this.videoFrameReleaseControl.onStopped();
        }
        super.onStopped();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onDisabled() {
        this.reportedVideoSize = null;
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.onRendererDisabled();
        } else {
            this.videoFrameReleaseControl.onDisabled();
        }
        maybeSetupTunnelingForFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
            this.eventDispatcher.videoSizeChanged(VideoSize.UNKNOWN);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onReset() {
        try {
            super.onReset();
        } finally {
            this.hasSetVideoSink = false;
            if (this.placeholderSurface != null) {
                releasePlaceholderSurface();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        super.onRelease();
        VideoSink videoSink = this.videoSink;
        if (videoSink == null || !this.ownsVideoSink) {
            return;
        }
        videoSink.release();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setOutput(obj);
            return;
        }
        if (i == 7) {
            VideoFrameMetadataListener videoFrameMetadataListener = (VideoFrameMetadataListener) Assertions.checkNotNull(obj);
            this.frameMetadataListener = videoFrameMetadataListener;
            VideoSink videoSink = this.videoSink;
            if (videoSink != null) {
                videoSink.setVideoFrameMetadataListener(videoFrameMetadataListener);
                return;
            }
            return;
        }
        if (i == 10) {
            int intValue = ((Integer) Assertions.checkNotNull(obj)).intValue();
            if (this.tunnelingAudioSessionId != intValue) {
                this.tunnelingAudioSessionId = intValue;
                if (this.tunneling) {
                    releaseCodec();
                    return;
                }
                return;
            }
            return;
        }
        if (i == 16) {
            this.rendererPriority = ((Integer) Assertions.checkNotNull(obj)).intValue();
            updateCodecImportance();
            return;
        }
        if (i == 4) {
            this.scalingMode = ((Integer) Assertions.checkNotNull(obj)).intValue();
            MediaCodecAdapter codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
                return;
            }
            return;
        }
        if (i == 5) {
            this.videoFrameReleaseControl.setChangeFrameRateStrategy(((Integer) Assertions.checkNotNull(obj)).intValue());
            return;
        }
        if (i == 13) {
            setVideoEffects((List) Assertions.checkNotNull(obj));
            return;
        }
        if (i == 14) {
            Size size = (Size) Assertions.checkNotNull(obj);
            if (size.getWidth() == 0 || size.getHeight() == 0) {
                return;
            }
            this.outputResolution = size;
            VideoSink videoSink2 = this.videoSink;
            if (videoSink2 != null) {
                videoSink2.setOutputSurfaceInfo((Surface) Assertions.checkStateNotNull(this.displaySurface), size);
                return;
            }
            return;
        }
        super.handleMessage(i, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.media3.exoplayer.video.MediaCodecVideoRenderer] */
    /* JADX WARN: Type inference failed for: r6v10, types: [android.view.Surface] */
    private void setOutput(Object obj) throws ExoPlaybackException {
        PlaceholderSurface placeholderSurface = obj instanceof Surface ? (Surface) obj : null;
        if (placeholderSurface == null) {
            PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
            if (placeholderSurface2 != null) {
                placeholderSurface = placeholderSurface2;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUsePlaceholderSurface(codecInfo)) {
                    placeholderSurface = PlaceholderSurface.newInstance(this.context, codecInfo.secure);
                    this.placeholderSurface = placeholderSurface;
                }
            }
        }
        if (this.displaySurface != placeholderSurface) {
            this.displaySurface = placeholderSurface;
            if (this.videoSink == null) {
                this.videoFrameReleaseControl.setOutputSurface(placeholderSurface);
            }
            this.haveReportedFirstFrameRenderedForCurrentSurface = false;
            int state = getState();
            MediaCodecAdapter codec = getCodec();
            if (codec != null && this.videoSink == null) {
                if (Util.SDK_INT >= 23 && placeholderSurface != null && !this.codecNeedsSetOutputSurfaceWorkaround) {
                    setOutputSurfaceV23(codec, placeholderSurface);
                } else {
                    releaseCodec();
                    maybeInitCodecOrBypass();
                }
            }
            if (placeholderSurface != null && placeholderSurface != this.placeholderSurface) {
                maybeRenotifyVideoSizeChanged();
                if (state == 2) {
                    this.videoFrameReleaseControl.join(true);
                }
            } else {
                this.reportedVideoSize = null;
                VideoSink videoSink = this.videoSink;
                if (videoSink != null) {
                    videoSink.clearOutputSurfaceInfo();
                }
            }
            maybeSetupTunnelingForFirstFrame();
            return;
        }
        if (placeholderSurface == null || placeholderSurface == this.placeholderSurface) {
            return;
        }
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.displaySurface != null || shouldUsePlaceholderSurface(mediaCodecInfo);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f) {
        PlaceholderSurface placeholderSurface = this.placeholderSurface;
        if (placeholderSurface != null && placeholderSurface.secure != mediaCodecInfo.secure) {
            releasePlaceholderSurface();
        }
        String str = mediaCodecInfo.codecMimeType;
        CodecMaxValues codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        this.codecMaxValues = codecMaxValues;
        MediaFormat mediaFormat = getMediaFormat(format, str, codecMaxValues, f, this.deviceNeedsNoPostProcessWorkaround, this.tunneling ? this.tunnelingAudioSessionId : 0);
        if (this.displaySurface == null) {
            if (!shouldUsePlaceholderSurface(mediaCodecInfo)) {
                throw new IllegalStateException();
            }
            if (this.placeholderSurface == null) {
                this.placeholderSurface = PlaceholderSurface.newInstance(this.context, mediaCodecInfo.secure);
            }
            this.displaySurface = this.placeholderSurface;
        }
        maybeSetKeyAllowFrameDrop(mediaFormat);
        VideoSink videoSink = this.videoSink;
        return MediaCodecAdapter.Configuration.createForVideoDecoding(mediaCodecInfo, mediaFormat, format, videoSink != null ? videoSink.getInputSurface() : this.displaySurface, mediaCrypto);
    }

    private void maybeSetKeyAllowFrameDrop(MediaFormat mediaFormat) {
        VideoSink videoSink = this.videoSink;
        if (videoSink == null || videoSink.isFrameDropAllowedOnInput()) {
            return;
        }
        mediaFormat.setInteger("allow-frame-drop", 0);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation canReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i = canReuseCodec.discardReasons;
        CodecMaxValues codecMaxValues = (CodecMaxValues) Assertions.checkNotNull(this.codecMaxValues);
        if (format2.width > codecMaxValues.width || format2.height > codecMaxValues.height) {
            i |= 256;
        }
        if (getMaxInputSize(mediaCodecInfo, format2) > codecMaxValues.inputSize) {
            i |= 64;
        }
        int i2 = i;
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, i2 != 0 ? 0 : canReuseCodec.result, i2);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        super.render(j, j2);
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            try {
                videoSink.render(j, j2);
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, e.format, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.buffersInCodecCount = 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.Renderer
    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        super.setPlaybackSpeed(f, f2);
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.setPlaybackSpeed(f);
        } else {
            this.videoFrameReleaseControl.setPlaybackSpeed(f);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0087, code lost:
    
        if (r3.equals(androidx.media3.common.MimeTypes.VIDEO_AV1) == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        int intValue;
        int i = format.width;
        int i2 = format.height;
        if (i == -1 || i2 == -1) {
            return -1;
        }
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        char c = 1;
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(str)) {
            Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
            str = (codecProfileAndLevel == null || !((intValue = ((Integer) codecProfileAndLevel.first).intValue()) == 512 || intValue == 1 || intValue == 2)) ? MimeTypes.VIDEO_H265 : "video/avc";
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals("video/3gpp")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1662735862:
                break;
            case -1662541442:
                if (str.equals(MimeTypes.VIDEO_H265)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1187890754:
                if (str.equals(MimeTypes.VIDEO_MP4V)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1331836730:
                if (str.equals("video/avc")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1599127256:
                if (str.equals("video/x-vnd.on2.vp8")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1599127257:
                if (str.equals(MimeTypes.VIDEO_VP9)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 3:
            case 5:
                return getMaxSampleSize(i * i2, 2);
            case 2:
                return Math.max(2097152, getMaxSampleSize(i * i2, 2));
            case 4:
                if ("BRAVIA 4K 2015".equals(Util.MODEL) || ("Amazon".equals(Util.MANUFACTURER) && ("KFSOWI".equals(Util.MODEL) || ("AFTS".equals(Util.MODEL) && mediaCodecInfo.secure)))) {
                    return -1;
                }
                return getMaxSampleSize(Util.ceilDivide(i, 16) * Util.ceilDivide(i2, 16) * 16 * 16, 2);
            case 6:
                return getMaxSampleSize(i * i2, 4);
            default:
                return -1;
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onReadyToInitializeCodec(Format format) throws ExoPlaybackException {
        VideoSink videoSink = this.videoSink;
        if (videoSink == null || videoSink.isInitialized()) {
            return;
        }
        try {
            this.videoSink.initialize(format);
        } catch (VideoSink.VideoSinkException e) {
            throw createRendererException(e, format, 7000);
        }
    }

    public void setVideoEffects(List<Effect> list) {
        this.videoEffects = list;
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.setVideoEffects(list);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
        maybeSetupTunnelingForFirstFrame();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onCodecError(Exception exc) {
        Log.e(TAG, "Video codec error", exc);
        this.eventDispatcher.videoCodecError(exc);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation onInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(formatHolder.format), onInputFormatChanged);
        return onInputFormatChanged;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (!this.tunneling) {
            this.buffersInCodecCount++;
        }
        if (Util.SDK_INT >= 23 || !this.tunneling) {
            return;
        }
        onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected int getCodecBufferFlags(DecoderInputBuffer decoderInputBuffer) {
        return (Util.SDK_INT < 34 || !this.tunneling || decoderInputBuffer.timeUs >= getLastResetPositionUs()) ? 0 : 32;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onOutputFormatChanged(Format format, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        int i;
        int i2;
        MediaCodecAdapter codec = getCodec();
        if (codec != null) {
            codec.setVideoScalingMode(this.scalingMode);
        }
        int i3 = 0;
        if (this.tunneling) {
            i2 = format.width;
            i = format.height;
        } else {
            Assertions.checkNotNull(mediaFormat);
            boolean z = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
            if (z) {
                integer = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
            } else {
                integer = mediaFormat.getInteger("width");
            }
            if (z) {
                integer2 = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
            } else {
                integer2 = mediaFormat.getInteger("height");
            }
            int i4 = integer;
            i = integer2;
            i2 = i4;
        }
        float f = format.pixelWidthHeightRatio;
        if (codecAppliesRotation()) {
            if (format.rotationDegrees == 90 || format.rotationDegrees == 270) {
                f = 1.0f / f;
                int i5 = i;
                i = i2;
                i2 = i5;
            }
        } else if (this.videoSink == null) {
            i3 = format.rotationDegrees;
        }
        this.decodedVideoSize = new VideoSize(i2, i, i3, f);
        if (this.videoSink != null) {
            onReadyToRegisterVideoSinkInputStream();
            this.videoSink.registerInputStream(1, format.buildUpon().setWidth(i2).setHeight(i).setRotationDegrees(i3).setPixelWidthHeightRatio(f).build());
        } else {
            this.videoFrameReleaseControl.setFrameRate(format.frameRate);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.supplementalData);
            if (byteBuffer.remaining() >= 7) {
                byte b = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                byteBuffer.position(0);
                if (b == -75 && s == 60 && s2 == 1 && b2 == 4) {
                    if (b3 == 0 || b3 == 1) {
                        byte[] bArr = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bArr);
                        byteBuffer.position(0);
                        setHdr10PlusInfoV29((MediaCodecAdapter) Assertions.checkNotNull(getCodec()), bArr);
                    }
                }
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        Assertions.checkNotNull(mediaCodecAdapter);
        long outputStreamOffsetUs = j3 - getOutputStreamOffsetUs();
        int frameReleaseAction = this.videoFrameReleaseControl.getFrameReleaseAction(j3, j, j2, getOutputStreamStartPositionUs(), z2, this.videoFrameReleaseInfo);
        if (frameReleaseAction == 4) {
            return false;
        }
        if (z && !z2) {
            skipOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs);
            return true;
        }
        if (this.displaySurface == this.placeholderSurface && this.videoSink == null) {
            if (this.videoFrameReleaseInfo.getEarlyUs() >= 30000) {
                return false;
            }
            skipOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs);
            updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
            return true;
        }
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            try {
                videoSink.render(j, j2);
                long registerInputFrame = this.videoSink.registerInputFrame(j3 + getBufferTimestampAdjustmentUs(), z2);
                if (registerInputFrame == C.TIME_UNSET) {
                    return false;
                }
                renderOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs, registerInputFrame);
                return true;
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, e.format, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
            }
        }
        if (frameReleaseAction == 0) {
            long nanoTime = getClock().nanoTime();
            notifyFrameMetadataListener(outputStreamOffsetUs, nanoTime, format);
            renderOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs, nanoTime);
            updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
            return true;
        }
        if (frameReleaseAction == 1) {
            return maybeReleaseFrame((MediaCodecAdapter) Assertions.checkStateNotNull(mediaCodecAdapter), i, outputStreamOffsetUs, format);
        }
        if (frameReleaseAction == 2) {
            dropOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs);
            updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
            return true;
        }
        if (frameReleaseAction != 3) {
            if (frameReleaseAction == 5) {
                return false;
            }
            throw new IllegalStateException(String.valueOf(frameReleaseAction));
        }
        skipOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs);
        updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
        return true;
    }

    private boolean maybeReleaseFrame(MediaCodecAdapter mediaCodecAdapter, int i, long j, Format format) {
        long releaseTimeNs = this.videoFrameReleaseInfo.getReleaseTimeNs();
        long earlyUs = this.videoFrameReleaseInfo.getEarlyUs();
        if (Util.SDK_INT >= 21) {
            if (shouldSkipBuffersWithIdenticalReleaseTime() && releaseTimeNs == this.lastFrameReleaseTimeNs) {
                skipOutputBuffer(mediaCodecAdapter, i, j);
            } else {
                notifyFrameMetadataListener(j, releaseTimeNs, format);
                renderOutputBufferV21(mediaCodecAdapter, i, j, releaseTimeNs);
            }
            updateVideoFrameProcessingOffsetCounters(earlyUs);
            this.lastFrameReleaseTimeNs = releaseTimeNs;
            return true;
        }
        if (earlyUs >= 30000) {
            return false;
        }
        if (earlyUs > 11000) {
            try {
                Thread.sleep((earlyUs - Renderer.DEFAULT_DURATION_TO_PROGRESS_US) / 1000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        notifyFrameMetadataListener(j, releaseTimeNs, format);
        renderOutputBuffer(mediaCodecAdapter, i, j);
        updateVideoFrameProcessingOffsetCounters(earlyUs);
        return true;
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format, getCodecOutputMediaFormat());
        }
    }

    protected void onProcessedTunneledBuffer(long j) throws ExoPlaybackException {
        updateOutputFormatForTime(j);
        maybeNotifyVideoSizeChanged(this.decodedVideoSize);
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onProcessedOutputBuffer(long j) {
        super.onProcessedOutputBuffer(j);
        if (this.tunneling) {
            return;
        }
        this.buffersInCodecCount--;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.setStreamOffsetAndAdjustmentUs(getOutputStreamOffsetUs(), getBufferTimestampAdjustmentUs());
        } else {
            this.videoFrameReleaseControl.onProcessedStreamChange();
        }
        maybeSetupTunnelingForFirstFrame();
    }

    protected void skipOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    protected void dropOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    protected boolean maybeDropBuffersToKeyframe(long j, boolean z) throws ExoPlaybackException {
        int skipSource = skipSource(j);
        if (skipSource == 0) {
            return false;
        }
        if (z) {
            this.decoderCounters.skippedInputBufferCount += skipSource;
            this.decoderCounters.skippedOutputBufferCount += this.buffersInCodecCount;
        } else {
            this.decoderCounters.droppedToKeyframeCount++;
            updateDroppedBufferCounters(skipSource, this.buffersInCodecCount);
        }
        flushOrReinitializeCodec();
        VideoSink videoSink = this.videoSink;
        if (videoSink != null) {
            videoSink.flush(false);
        }
        return true;
    }

    protected void updateDroppedBufferCounters(int i, int i2) {
        this.decoderCounters.droppedInputBufferCount += i;
        int i3 = i + i2;
        this.decoderCounters.droppedBufferCount += i3;
        this.droppedFrames += i3;
        this.consecutiveDroppedFrameCount += i3;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        int i4 = this.maxDroppedFramesToNotify;
        if (i4 <= 0 || this.droppedFrames < i4) {
            return;
        }
        maybeNotifyDroppedFrames();
    }

    protected void updateVideoFrameProcessingOffsetCounters(long j) {
        this.decoderCounters.addVideoFrameProcessingOffset(j);
        this.totalVideoFrameProcessingOffsetUs += j;
        this.videoFrameProcessingOffsetCount++;
    }

    private void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        if (Util.SDK_INT >= 21) {
            renderOutputBufferV21(mediaCodecAdapter, i, j, j2);
        } else {
            renderOutputBuffer(mediaCodecAdapter, i, j);
        }
    }

    protected void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    protected void renderOutputBufferV21(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    private boolean shouldUsePlaceholderSurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || PlaceholderSurface.isSecureSupported(this.context));
    }

    private void releasePlaceholderSurface() {
        Surface surface = this.displaySurface;
        PlaceholderSurface placeholderSurface = this.placeholderSurface;
        if (surface == placeholderSurface) {
            this.displaySurface = null;
        }
        if (placeholderSurface != null) {
            placeholderSurface.release();
            this.placeholderSurface = null;
        }
    }

    private void maybeSetupTunnelingForFirstFrame() {
        MediaCodecAdapter codec;
        if (!this.tunneling || Util.SDK_INT < 23 || (codec = getCodec()) == null) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        if (Util.SDK_INT >= 33) {
            Bundle bundle = new Bundle();
            bundle.putInt("tunnel-peek", 1);
            codec.setParameters(bundle);
        }
    }

    private void updateCodecImportance() {
        MediaCodecAdapter codec = getCodec();
        if (codec != null && Util.SDK_INT >= 35) {
            Bundle bundle = new Bundle();
            bundle.putInt("importance", Math.max(0, -this.rendererPriority));
            codec.setParameters(bundle);
        }
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (!this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame() || this.displaySurface == null) {
            return;
        }
        notifyRenderedFirstFrame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"displaySurface"})
    public void notifyRenderedFirstFrame() {
        this.eventDispatcher.renderedFirstFrame(this.displaySurface);
        this.haveReportedFirstFrameRenderedForCurrentSurface = true;
    }

    private void maybeRenotifyRenderedFirstFrame() {
        Surface surface = this.displaySurface;
        if (surface == null || !this.haveReportedFirstFrameRenderedForCurrentSurface) {
            return;
        }
        this.eventDispatcher.renderedFirstFrame(surface);
    }

    private void maybeNotifyVideoSizeChanged(VideoSize videoSize) {
        if (videoSize.equals(VideoSize.UNKNOWN) || videoSize.equals(this.reportedVideoSize)) {
            return;
        }
        this.reportedVideoSize = videoSize;
        this.eventDispatcher.videoSizeChanged(videoSize);
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null) {
            this.eventDispatcher.videoSizeChanged(videoSize);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = getClock().elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private void maybeNotifyVideoFrameProcessingOffset() {
        int i = this.videoFrameProcessingOffsetCount;
        if (i != 0) {
            this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, i);
            this.totalVideoFrameProcessingOffsetUs = 0L;
            this.videoFrameProcessingOffsetCount = 0;
        }
    }

    private static void setHdr10PlusInfoV29(MediaCodecAdapter mediaCodecAdapter, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("hdr10-plus-info", bArr);
        mediaCodecAdapter.setParameters(bundle);
    }

    protected void setOutputSurfaceV23(MediaCodecAdapter mediaCodecAdapter, Surface surface) {
        mediaCodecAdapter.setOutputSurface(surface);
    }

    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    protected MediaFormat getMediaFormat(Format format, String str, CodecMaxValues codecMaxValues, float f, boolean z, int i) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, MediaFormatConstants.KEY_ROTATION_DEGREES, format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, "profile", ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues.width);
        mediaFormat.setInteger("max-height", codecMaxValues.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        if (Util.SDK_INT >= 35) {
            mediaFormat.setInteger("importance", Math.max(0, -this.rendererPriority));
        }
        return mediaFormat;
    }

    protected CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize;
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (maxInputSize != -1 && (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format)) != -1) {
                maxInputSize = Math.min((int) (maxInputSize * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR), codecMaxInputSize);
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int length = formatArr.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            Format format2 = formatArr[i3];
            if (format.colorInfo != null && format2.colorInfo == null) {
                format2 = format2.buildUpon().setColorInfo(format.colorInfo).build();
            }
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                z |= format2.width == -1 || format2.height == -1;
                i = Math.max(i, format2.width);
                i2 = Math.max(i2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + i + ViewHierarchyNode.JsonKeys.X + i2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i = Math.max(i, codecMaxSize.x);
                i2 = Math.max(i2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, format.buildUpon().setWidth(i).setHeight(i2).build()));
                Log.w(TAG, "Codec max resolution adjusted to: " + i + ViewHierarchyNode.JsonKeys.X + i2);
            }
        }
        return new CodecMaxValues(i, i2, maxInputSize);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected MediaCodecDecoderException createDecoderException(Throwable th, MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecVideoDecoderException(th, mediaCodecInfo, this.displaySurface);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = i2 / i;
        for (int i3 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i4 = (int) (i3 * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                float f2 = format.frameRate;
                if (alignVideoSizeV21 != null && mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, f2)) {
                    return alignVideoSizeV21;
                }
            } else {
                try {
                    int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                    int ceilDivide2 = Util.ceilDivide(i4, 16) * 16;
                    if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                        int i6 = z ? ceilDivide2 : ceilDivide;
                        if (!z) {
                            ceilDivide = ceilDivide2;
                        }
                        return new Point(i6, ceilDivide);
                    }
                } catch (MediaCodecUtil.DecoderQueryException unused) {
                }
            }
        }
        return null;
    }

    protected static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize != -1) {
            int size = format.initializationData.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += format.initializationData.get(i2).length;
            }
            return format.maxInputSize + i;
        }
        return getCodecMaxInputSize(mediaCodecInfo, format);
    }

    private static boolean codecAppliesRotation() {
        return Util.SDK_INT >= 21;
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    protected boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        if (str.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();
                evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    protected Surface getSurface() {
        return this.displaySurface;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    private static int getMaxSampleSize(int i, int i2) {
        return (i * 3) / (i2 * 2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x072a, code lost:
    
        if (r0.equals("ELUGA_Ray_X") == false) goto L91;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:47:0x08a3. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        boolean z;
        boolean z2;
        char c = 28;
        if (Util.SDK_INT <= 28) {
            String str = Util.DEVICE;
            str.hashCode();
            switch (str.hashCode()) {
                case -1339091551:
                    if (str.equals("dangal")) {
                        z2 = false;
                        break;
                    }
                    z2 = -1;
                    break;
                case -1220081023:
                    if (str.equals("dangalFHD")) {
                        z2 = true;
                        break;
                    }
                    z2 = -1;
                    break;
                case -1220066608:
                    if (str.equals("dangalUHD")) {
                        z2 = 2;
                        break;
                    }
                    z2 = -1;
                    break;
                case -1012436106:
                    if (str.equals("oneday")) {
                        z2 = 3;
                        break;
                    }
                    z2 = -1;
                    break;
                case -760312546:
                    if (str.equals("aquaman")) {
                        z2 = 4;
                        break;
                    }
                    z2 = -1;
                    break;
                case -64886864:
                    if (str.equals("magnolia")) {
                        z2 = 5;
                        break;
                    }
                    z2 = -1;
                    break;
                case 3415681:
                    if (str.equals("once")) {
                        z2 = 6;
                        break;
                    }
                    z2 = -1;
                    break;
                case 825323514:
                    if (str.equals("machuca")) {
                        z2 = 7;
                        break;
                    }
                    z2 = -1;
                    break;
                default:
                    z2 = -1;
                    break;
            }
            switch (z2) {
                case false:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                    return true;
            }
        }
        if (Util.SDK_INT <= 27 && "HWEML".equals(Util.DEVICE)) {
            return true;
        }
        String str2 = Util.MODEL;
        str2.hashCode();
        switch (str2.hashCode()) {
            case -349662828:
                if (str2.equals("AFTJMST12")) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            case -321033677:
                if (str2.equals("AFTKMST12")) {
                    z = true;
                    break;
                }
                z = -1;
                break;
            case 2006354:
                if (str2.equals("AFTA")) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            case 2006367:
                if (str2.equals("AFTN")) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 2006371:
                if (str2.equals("AFTR")) {
                    z = 4;
                    break;
                }
                z = -1;
                break;
            case 1785421873:
                if (str2.equals("AFTEU011")) {
                    z = 5;
                    break;
                }
                z = -1;
                break;
            case 1785421876:
                if (str2.equals("AFTEU014")) {
                    z = 6;
                    break;
                }
                z = -1;
                break;
            case 1798172390:
                if (str2.equals("AFTSO001")) {
                    z = 7;
                    break;
                }
                z = -1;
                break;
            case 2119412532:
                if (str2.equals("AFTEUFF014")) {
                    z = 8;
                    break;
                }
                z = -1;
                break;
            default:
                z = -1;
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
                return true;
            default:
                if (Util.SDK_INT <= 26) {
                    String str3 = Util.DEVICE;
                    str3.hashCode();
                    switch (str3.hashCode()) {
                        case -2144781245:
                            if (str3.equals("GIONEE_SWW1609")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -2144781185:
                            if (str3.equals("GIONEE_SWW1627")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case -2144781160:
                            if (str3.equals("GIONEE_SWW1631")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case -2097309513:
                            if (str3.equals("K50a40")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -2022874474:
                            if (str3.equals("CP8676_I02")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1978993182:
                            if (str3.equals("NX541J")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1978990237:
                            if (str3.equals("NX573J")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1936688988:
                            if (str3.equals("PGN528")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1936688066:
                            if (str3.equals("PGN610")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1936688065:
                            if (str3.equals("PGN611")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1931988508:
                            if (str3.equals("AquaPowerM")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1885099851:
                            if (str3.equals("RAIJIN")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1696512866:
                            if (str3.equals("XT1663")) {
                                c = '\f';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1680025915:
                            if (str3.equals("ComioS1")) {
                                c = '\r';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1615810839:
                            if (str3.equals("Phantom6")) {
                                c = 14;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1600724499:
                            if (str3.equals("pacificrim")) {
                                c = 15;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1554255044:
                            if (str3.equals("vernee_M5")) {
                                c = 16;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1481772737:
                            if (str3.equals("panell_dl")) {
                                c = 17;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1481772730:
                            if (str3.equals("panell_ds")) {
                                c = 18;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1481772729:
                            if (str3.equals("panell_dt")) {
                                c = 19;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1320080169:
                            if (str3.equals("GiONEE_GBL7319")) {
                                c = 20;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1217592143:
                            if (str3.equals("BRAVIA_ATV2")) {
                                c = 21;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1180384755:
                            if (str3.equals("iris60")) {
                                c = 22;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1139198265:
                            if (str3.equals("Slate_Pro")) {
                                c = 23;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1052835013:
                            if (str3.equals("namath")) {
                                c = 24;
                                break;
                            }
                            c = 65535;
                            break;
                        case -993250464:
                            if (str3.equals("A10-70F")) {
                                c = 25;
                                break;
                            }
                            c = 65535;
                            break;
                        case -993250458:
                            if (str3.equals("A10-70L")) {
                                c = 26;
                                break;
                            }
                            c = 65535;
                            break;
                        case -965403638:
                            if (str3.equals("s905x018")) {
                                c = 27;
                                break;
                            }
                            c = 65535;
                            break;
                        case -958336948:
                            break;
                        case -879245230:
                            if (str3.equals("tcl_eu")) {
                                c = 29;
                                break;
                            }
                            c = 65535;
                            break;
                        case -842500323:
                            if (str3.equals("nicklaus_f")) {
                                c = 30;
                                break;
                            }
                            c = 65535;
                            break;
                        case -821392978:
                            if (str3.equals("A7000-a")) {
                                c = 31;
                                break;
                            }
                            c = 65535;
                            break;
                        case -797483286:
                            if (str3.equals("SVP-DTV15")) {
                                c = ' ';
                                break;
                            }
                            c = 65535;
                            break;
                        case -794946968:
                            if (str3.equals("watson")) {
                                c = '!';
                                break;
                            }
                            c = 65535;
                            break;
                        case -788334647:
                            if (str3.equals("whyred")) {
                                c = '\"';
                                break;
                            }
                            c = 65535;
                            break;
                        case -782144577:
                            if (str3.equals("OnePlus5T")) {
                                c = '#';
                                break;
                            }
                            c = 65535;
                            break;
                        case -575125681:
                            if (str3.equals("GiONEE_CBL7513")) {
                                c = Typography.dollar;
                                break;
                            }
                            c = 65535;
                            break;
                        case -521118391:
                            if (str3.equals("GIONEE_GBL7360")) {
                                c = '%';
                                break;
                            }
                            c = 65535;
                            break;
                        case -430914369:
                            if (str3.equals("Pixi4-7_3G")) {
                                c = Typography.amp;
                                break;
                            }
                            c = 65535;
                            break;
                        case -290434366:
                            if (str3.equals("taido_row")) {
                                c = '\'';
                                break;
                            }
                            c = 65535;
                            break;
                        case -282781963:
                            if (str3.equals("BLACK-1X")) {
                                c = '(';
                                break;
                            }
                            c = 65535;
                            break;
                        case -277133239:
                            if (str3.equals("Z12_PRO")) {
                                c = ')';
                                break;
                            }
                            c = 65535;
                            break;
                        case -173639913:
                            if (str3.equals("ELUGA_A3_Pro")) {
                                c = '*';
                                break;
                            }
                            c = 65535;
                            break;
                        case -56598463:
                            if (str3.equals("woods_fn")) {
                                c = '+';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2126:
                            if (str3.equals("C1")) {
                                c = ',';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2564:
                            if (str3.equals("Q5")) {
                                c = '-';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2715:
                            if (str3.equals("V1")) {
                                c = FilenameUtils.EXTENSION_SEPARATOR;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2719:
                            if (str3.equals("V5")) {
                                c = '/';
                                break;
                            }
                            c = 65535;
                            break;
                        case 3091:
                            if (str3.equals("b5")) {
                                c = '0';
                                break;
                            }
                            c = 65535;
                            break;
                        case 3483:
                            if (str3.equals("mh")) {
                                c = '1';
                                break;
                            }
                            c = 65535;
                            break;
                        case 73405:
                            if (str3.equals("JGZ")) {
                                c = '2';
                                break;
                            }
                            c = 65535;
                            break;
                        case 75537:
                            if (str3.equals("M04")) {
                                c = '3';
                                break;
                            }
                            c = 65535;
                            break;
                        case 75739:
                            if (str3.equals("M5c")) {
                                c = '4';
                                break;
                            }
                            c = 65535;
                            break;
                        case 76779:
                            if (str3.equals("MX6")) {
                                c = '5';
                                break;
                            }
                            c = 65535;
                            break;
                        case 78669:
                            if (str3.equals("P85")) {
                                c = '6';
                                break;
                            }
                            c = 65535;
                            break;
                        case 79305:
                            if (str3.equals("PLE")) {
                                c = '7';
                                break;
                            }
                            c = 65535;
                            break;
                        case 80618:
                            if (str3.equals("QX1")) {
                                c = '8';
                                break;
                            }
                            c = 65535;
                            break;
                        case 88274:
                            if (str3.equals("Z80")) {
                                c = '9';
                                break;
                            }
                            c = 65535;
                            break;
                        case 98846:
                            if (str3.equals("cv1")) {
                                c = ':';
                                break;
                            }
                            c = 65535;
                            break;
                        case 98848:
                            if (str3.equals("cv3")) {
                                c = ';';
                                break;
                            }
                            c = 65535;
                            break;
                        case 99329:
                            if (str3.equals("deb")) {
                                c = Typography.less;
                                break;
                            }
                            c = 65535;
                            break;
                        case 101481:
                            if (str3.equals("flo")) {
                                c = '=';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1513190:
                            if (str3.equals("1601")) {
                                c = Typography.greater;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1514184:
                            if (str3.equals("1713")) {
                                c = '?';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1514185:
                            if (str3.equals("1714")) {
                                c = '@';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2133089:
                            if (str3.equals("F01H")) {
                                c = 'A';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2133091:
                            if (str3.equals("F01J")) {
                                c = 'B';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2133120:
                            if (str3.equals("F02H")) {
                                c = 'C';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2133151:
                            if (str3.equals("F03H")) {
                                c = 'D';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2133182:
                            if (str3.equals("F04H")) {
                                c = 'E';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2133184:
                            if (str3.equals("F04J")) {
                                c = 'F';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2436959:
                            if (str3.equals("P681")) {
                                c = 'G';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2463773:
                            if (str3.equals("Q350")) {
                                c = 'H';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2464648:
                            if (str3.equals("Q427")) {
                                c = 'I';
                                break;
                            }
                            c = 65535;
                            break;
                        case 2689555:
                            if (str3.equals("XE2X")) {
                                c = 'J';
                                break;
                            }
                            c = 65535;
                            break;
                        case 3154429:
                            if (str3.equals("fugu")) {
                                c = 'K';
                                break;
                            }
                            c = 65535;
                            break;
                        case 3284551:
                            if (str3.equals("kate")) {
                                c = Matrix.MATRIX_TYPE_RANDOM_LT;
                                break;
                            }
                            c = 65535;
                            break;
                        case 3351335:
                            if (str3.equals("mido")) {
                                c = 'M';
                                break;
                            }
                            c = 65535;
                            break;
                        case 3386211:
                            if (str3.equals("p212")) {
                                c = 'N';
                                break;
                            }
                            c = 65535;
                            break;
                        case 41325051:
                            if (str3.equals("MEIZU_M5")) {
                                c = 'O';
                                break;
                            }
                            c = 65535;
                            break;
                        case 51349633:
                            if (str3.equals("601LV")) {
                                c = 'P';
                                break;
                            }
                            c = 65535;
                            break;
                        case 51350594:
                            if (str3.equals("602LV")) {
                                c = 'Q';
                                break;
                            }
                            c = 65535;
                            break;
                        case 55178625:
                            if (str3.equals("Aura_Note_2")) {
                                c = Matrix.MATRIX_TYPE_RANDOM_REGULAR;
                                break;
                            }
                            c = 65535;
                            break;
                        case 61542055:
                            if (str3.equals("A1601")) {
                                c = 'S';
                                break;
                            }
                            c = 65535;
                            break;
                        case 65355429:
                            if (str3.equals("E5643")) {
                                c = 'T';
                                break;
                            }
                            c = 65535;
                            break;
                        case 66214468:
                            if (str3.equals("F3111")) {
                                c = Matrix.MATRIX_TYPE_RANDOM_UT;
                                break;
                            }
                            c = 65535;
                            break;
                        case 66214470:
                            if (str3.equals("F3113")) {
                                c = 'V';
                                break;
                            }
                            c = 65535;
                            break;
                        case 66214473:
                            if (str3.equals("F3116")) {
                                c = 'W';
                                break;
                            }
                            c = 65535;
                            break;
                        case 66215429:
                            if (str3.equals("F3211")) {
                                c = 'X';
                                break;
                            }
                            c = 65535;
                            break;
                        case 66215431:
                            if (str3.equals("F3213")) {
                                c = 'Y';
                                break;
                            }
                            c = 65535;
                            break;
                        case 66215433:
                            if (str3.equals("F3215")) {
                                c = Matrix.MATRIX_TYPE_ZERO;
                                break;
                            }
                            c = 65535;
                            break;
                        case 66216390:
                            if (str3.equals("F3311")) {
                                c = '[';
                                break;
                            }
                            c = 65535;
                            break;
                        case 76402249:
                            if (str3.equals("PRO7S")) {
                                c = IOUtils.DIR_SEPARATOR_WINDOWS;
                                break;
                            }
                            c = 65535;
                            break;
                        case 76404105:
                            if (str3.equals("Q4260")) {
                                c = ']';
                                break;
                            }
                            c = 65535;
                            break;
                        case 76404911:
                            if (str3.equals("Q4310")) {
                                c = '^';
                                break;
                            }
                            c = 65535;
                            break;
                        case 80963634:
                            if (str3.equals("V23GB")) {
                                c = '_';
                                break;
                            }
                            c = 65535;
                            break;
                        case 82882791:
                            if (str3.equals("X3_HK")) {
                                c = '`';
                                break;
                            }
                            c = 65535;
                            break;
                        case 98715550:
                            if (str3.equals("i9031")) {
                                c = 'a';
                                break;
                            }
                            c = 65535;
                            break;
                        case 101370885:
                            if (str3.equals("l5460")) {
                                c = 'b';
                                break;
                            }
                            c = 65535;
                            break;
                        case 102844228:
                            if (str3.equals("le_x6")) {
                                c = 'c';
                                break;
                            }
                            c = 65535;
                            break;
                        case 165221241:
                            if (str3.equals("A2016a40")) {
                                c = 'd';
                                break;
                            }
                            c = 65535;
                            break;
                        case 182191441:
                            if (str3.equals("CPY83_I00")) {
                                c = 'e';
                                break;
                            }
                            c = 65535;
                            break;
                        case 245388979:
                            if (str3.equals("marino_f")) {
                                c = 'f';
                                break;
                            }
                            c = 65535;
                            break;
                        case 287431619:
                            if (str3.equals("griffin")) {
                                c = 'g';
                                break;
                            }
                            c = 65535;
                            break;
                        case 307593612:
                            if (str3.equals("A7010a48")) {
                                c = 'h';
                                break;
                            }
                            c = 65535;
                            break;
                        case 308517133:
                            if (str3.equals("A7020a48")) {
                                c = 'i';
                                break;
                            }
                            c = 65535;
                            break;
                        case 316215098:
                            if (str3.equals("TB3-730F")) {
                                c = 'j';
                                break;
                            }
                            c = 65535;
                            break;
                        case 316215116:
                            if (str3.equals("TB3-730X")) {
                                c = 'k';
                                break;
                            }
                            c = 65535;
                            break;
                        case 316246811:
                            if (str3.equals("TB3-850F")) {
                                c = 'l';
                                break;
                            }
                            c = 65535;
                            break;
                        case 316246818:
                            if (str3.equals("TB3-850M")) {
                                c = 'm';
                                break;
                            }
                            c = 65535;
                            break;
                        case 407160593:
                            if (str3.equals("Pixi5-10_4G")) {
                                c = 'n';
                                break;
                            }
                            c = 65535;
                            break;
                        case 507412548:
                            if (str3.equals("QM16XE_U")) {
                                c = 'o';
                                break;
                            }
                            c = 65535;
                            break;
                        case 793982701:
                            if (str3.equals("GIONEE_WBL5708")) {
                                c = 'p';
                                break;
                            }
                            c = 65535;
                            break;
                        case 794038622:
                            if (str3.equals("GIONEE_WBL7365")) {
                                c = 'q';
                                break;
                            }
                            c = 65535;
                            break;
                        case 794040393:
                            if (str3.equals("GIONEE_WBL7519")) {
                                c = 'r';
                                break;
                            }
                            c = 65535;
                            break;
                        case 835649806:
                            if (str3.equals("manning")) {
                                c = 's';
                                break;
                            }
                            c = 65535;
                            break;
                        case 917340916:
                            if (str3.equals("A7000plus")) {
                                c = 't';
                                break;
                            }
                            c = 65535;
                            break;
                        case 958008161:
                            if (str3.equals("j2xlteins")) {
                                c = 'u';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1060579533:
                            if (str3.equals("panell_d")) {
                                c = 'v';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1150207623:
                            if (str3.equals("LS-5017")) {
                                c = 'w';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1176899427:
                            if (str3.equals("itel_S41")) {
                                c = 'x';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1280332038:
                            if (str3.equals("hwALE-H")) {
                                c = 'y';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1306947716:
                            if (str3.equals("EverStar_S")) {
                                c = 'z';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1349174697:
                            if (str3.equals("htc_e56ml_dtul")) {
                                c = '{';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1522194893:
                            if (str3.equals("woods_f")) {
                                c = '|';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1691543273:
                            if (str3.equals("CPH1609")) {
                                c = '}';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1691544261:
                            if (str3.equals("CPH1715")) {
                                c = '~';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1709443163:
                            if (str3.equals("iball8735_9806")) {
                                c = Ascii.MAX;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1865889110:
                            if (str3.equals("santoni")) {
                                c = 128;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1906253259:
                            if (str3.equals("PB2-670M")) {
                                c = 129;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1977196784:
                            if (str3.equals("Infinix-X572")) {
                                c = 130;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2006372676:
                            if (str3.equals("BRAVIA_ATV3_4K")) {
                                c = 131;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2019281702:
                            if (str3.equals("DM-01K")) {
                                c = 132;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2029784656:
                            if (str3.equals("HWBLN-H")) {
                                c = 133;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2030379515:
                            if (str3.equals("HWCAM-H")) {
                                c = 134;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2033393791:
                            if (str3.equals("ASUS_X00AD_2")) {
                                c = 135;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2047190025:
                            if (str3.equals("ELUGA_Note")) {
                                c = 136;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2047252157:
                            if (str3.equals("ELUGA_Prim")) {
                                c = 137;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2048319463:
                            if (str3.equals("HWVNS-H")) {
                                c = 138;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2048855701:
                            if (str3.equals("HWWAS-H")) {
                                c = 139;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        default:
                            String str4 = Util.MODEL;
                            str4.hashCode();
                            if (!str4.equals("JSN-L21")) {
                            }
                            break;
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case '\b':
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case ' ':
                        case '!':
                        case '\"':
                        case '#':
                        case '$':
                        case '%':
                        case '&':
                        case '\'':
                        case '(':
                        case ')':
                        case '*':
                        case '+':
                        case ',':
                        case '-':
                        case '.':
                        case '/':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case ':':
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                        case '?':
                        case '@':
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                        case '[':
                        case '\\':
                        case ']':
                        case '^':
                        case '_':
                        case '`':
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                        case '{':
                        case '|':
                        case '}':
                        case '~':
                        case 127:
                        case 128:
                        case 129:
                        case 130:
                        case ISO781611.CREATION_DATE_AND_TIME_TAG /* 131 */:
                        case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                        case 133:
                        case 134:
                        case 135:
                        case 136:
                        case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                        case 138:
                        case 139:
                            return true;
                    }
                }
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        private static final int HANDLE_FRAME_RENDERED = 0;
        private final Handler handler;

        public OnFrameRenderedListenerV23(MediaCodecAdapter mediaCodecAdapter) {
            Handler createHandlerForCurrentLooper = Util.createHandlerForCurrentLooper(this);
            this.handler = createHandlerForCurrentLooper;
            mediaCodecAdapter.setOnFrameRenderedListener(this, createHandlerForCurrentLooper);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.OnFrameRenderedListener
        public void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j, long j2) {
            if (Util.SDK_INT < 30) {
                this.handler.sendMessageAtFrontOfQueue(Message.obtain(this.handler, 0, (int) (j >> 32), (int) j));
            } else {
                handleFrameRendered(j);
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            handleFrameRendered(Util.toLong(message.arg1, message.arg2));
            return true;
        }

        private void handleFrameRendered(long j) {
            if (this != MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener || MediaCodecVideoRenderer.this.getCodec() == null) {
                return;
            }
            if (j == Long.MAX_VALUE) {
                MediaCodecVideoRenderer.this.onProcessedTunneledEndOfStream();
                return;
            }
            try {
                MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
            } catch (ExoPlaybackException e) {
                MediaCodecVideoRenderer.this.setPendingPlaybackException(e);
            }
        }
    }
}
