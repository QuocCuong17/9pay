package com.example.video_compress;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.otaliastudios.transcoder.Transcoder;
import com.otaliastudios.transcoder.TranscoderListener;
import com.otaliastudios.transcoder.internal.utils.Logger;
import com.otaliastudios.transcoder.source.TrimDataSource;
import com.otaliastudios.transcoder.source.UriDataSource;
import com.otaliastudios.transcoder.strategy.DefaultAudioStrategy;
import com.otaliastudios.transcoder.strategy.DefaultVideoStrategy;
import com.otaliastudios.transcoder.strategy.RemoveTrackStrategy;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformPlugin;
import io.flutter.plugins.firebase.database.Constants;
import io.sentry.Session;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: VideoCompressPlugin.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0004\n\u0002\b\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/example/video_compress/VideoCompressPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "()V", "LOG", "Lcom/otaliastudios/transcoder/internal/utils/Logger;", "TAG", "", "TAG$1", "_channel", "Lio/flutter/plugin/common/MethodChannel;", "_context", "Landroid/content/Context;", "channelName", "getChannelName", "()Ljava/lang/String;", "setChannelName", "(Ljava/lang/String;)V", "transcodeFuture", "Ljava/util/concurrent/Future;", "Ljava/lang/Void;", Session.JsonKeys.INIT, "", "context", "messenger", "Lio/flutter/plugin/common/BinaryMessenger;", "onAttachedToEngine", "binding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "Companion", "video_compress_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoCompressPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin {
    private static final String TAG = "video_compress";
    private MethodChannel _channel;
    private Context _context;
    private Future<Void> transcodeFuture;

    /* renamed from: TAG$1, reason: from kotlin metadata */
    private final String TAG = "VideoCompressPlugin";
    private final Logger LOG = new Logger("VideoCompressPlugin");
    private String channelName = TAG;

    public final String getChannelName() {
        return this.channelName;
    }

    public final void setChannelName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.channelName = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, final MethodChannel.Result result) {
        RemoveTrackStrategy removeTrackStrategy;
        String str;
        TrimDataSource trimDataSource;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        final Context context = this._context;
        final MethodChannel methodChannel = this._channel;
        if (context == null || methodChannel == null) {
            Log.w(this.TAG, "Calling VideoCompress plugin before initialization");
            return;
        }
        String str2 = call.method;
        if (str2 != null) {
            switch (str2.hashCode()) {
                case -1335238004:
                    if (str2.equals("cancelCompression")) {
                        Future<Void> future = this.transcodeFuture;
                        if (future != null) {
                            future.cancel(true);
                        }
                        result.success(false);
                        return;
                    }
                    break;
                case -442064102:
                    if (str2.equals("getFileThumbnail")) {
                        String str3 = (String) call.argument(Constants.PATH);
                        Object argument = call.argument("quality");
                        Intrinsics.checkNotNull(argument);
                        int intValue = ((Number) argument).intValue();
                        Object argument2 = call.argument("position");
                        Intrinsics.checkNotNull(argument2);
                        int intValue2 = ((Number) argument2).intValue();
                        ThumbnailUtility thumbnailUtility = new ThumbnailUtility(TAG);
                        Intrinsics.checkNotNull(str3);
                        thumbnailUtility.getFileThumbnail(context, str3, intValue, intValue2, result);
                        return;
                    }
                    break;
                case -309915358:
                    if (str2.equals("setLogLevel")) {
                        Object argument3 = call.argument("logLevel");
                        Intrinsics.checkNotNull(argument3);
                        Logger.setLogLevel(((Number) argument3).intValue());
                        result.success(true);
                        return;
                    }
                    break;
                case -281136852:
                    if (str2.equals("deleteAllCache")) {
                        new Utility(this.channelName).deleteAllCache(context, result);
                        result.success(Unit.INSTANCE);
                        return;
                    }
                    break;
                case 1306162446:
                    if (str2.equals("getByteThumbnail")) {
                        String str4 = (String) call.argument(Constants.PATH);
                        Object argument4 = call.argument("quality");
                        Intrinsics.checkNotNull(argument4);
                        int intValue3 = ((Number) argument4).intValue();
                        Object argument5 = call.argument("position");
                        Intrinsics.checkNotNull(argument5);
                        int intValue4 = ((Number) argument5).intValue();
                        ThumbnailUtility thumbnailUtility2 = new ThumbnailUtility(this.channelName);
                        Intrinsics.checkNotNull(str4);
                        thumbnailUtility2.getByteThumbnail(str4, intValue3, intValue4, result);
                        return;
                    }
                    break;
                case 1729824313:
                    if (str2.equals("compressVideo")) {
                        Object argument6 = call.argument(Constants.PATH);
                        Intrinsics.checkNotNull(argument6);
                        String str5 = (String) argument6;
                        Object argument7 = call.argument("quality");
                        Intrinsics.checkNotNull(argument7);
                        int intValue5 = ((Number) argument7).intValue();
                        Object argument8 = call.argument("deleteOrigin");
                        Intrinsics.checkNotNull(argument8);
                        final boolean booleanValue = ((Boolean) argument8).booleanValue();
                        Integer num = (Integer) call.argument("startTime");
                        Integer num2 = (Integer) call.argument("duration");
                        Boolean bool = (Boolean) call.argument("includeAudio");
                        if (bool == null) {
                            bool = true;
                        }
                        boolean booleanValue2 = bool.booleanValue();
                        Integer num3 = call.argument("frameRate") == null ? 30 : (Integer) call.argument("frameRate");
                        File externalFilesDir = context.getExternalFilesDir(TAG);
                        Intrinsics.checkNotNull(externalFilesDir);
                        String absolutePath = externalFilesDir.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "context.getExternalFiles…compress\")!!.absolutePath");
                        final String str6 = absolutePath + File.separator + "VID_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + str5.hashCode() + ".mp4";
                        DefaultVideoStrategy build = DefaultVideoStrategy.atMost(BaselineTIFFTagSet.TAG_S_MIN_SAMPLE_VALUE).build();
                        Intrinsics.checkNotNullExpressionValue(build, "atMost(340).build()");
                        DefaultVideoStrategy defaultVideoStrategy = build;
                        switch (intValue5) {
                            case 0:
                                DefaultVideoStrategy build2 = DefaultVideoStrategy.atMost(720).build();
                                Intrinsics.checkNotNullExpressionValue(build2, "atMost(720).build()");
                                defaultVideoStrategy = build2;
                                break;
                            case 1:
                                DefaultVideoStrategy build3 = DefaultVideoStrategy.atMost(360).build();
                                Intrinsics.checkNotNullExpressionValue(build3, "atMost(360).build()");
                                defaultVideoStrategy = build3;
                                break;
                            case 2:
                                DefaultVideoStrategy build4 = DefaultVideoStrategy.atMost(640).build();
                                Intrinsics.checkNotNullExpressionValue(build4, "atMost(640).build()");
                                defaultVideoStrategy = build4;
                                break;
                            case 3:
                                DefaultVideoStrategy.Builder bitRate = new DefaultVideoStrategy.Builder().keyFrameInterval(3.0f).bitRate(3686400L);
                                Intrinsics.checkNotNull(num3);
                                DefaultVideoStrategy build5 = bitRate.frameRate(num3.intValue()).build();
                                Intrinsics.checkNotNullExpressionValue(build5, "Builder()\n              …                 .build()");
                                defaultVideoStrategy = build5;
                                break;
                            case 4:
                                DefaultVideoStrategy build6 = DefaultVideoStrategy.atMost(480, 640).build();
                                Intrinsics.checkNotNullExpressionValue(build6, "atMost(480, 640).build()");
                                defaultVideoStrategy = build6;
                                break;
                            case 5:
                                DefaultVideoStrategy build7 = DefaultVideoStrategy.atMost(540, 960).build();
                                Intrinsics.checkNotNullExpressionValue(build7, "atMost(540, 960).build()");
                                defaultVideoStrategy = build7;
                                break;
                            case 6:
                                DefaultVideoStrategy build8 = DefaultVideoStrategy.atMost(720, PlatformPlugin.DEFAULT_SYSTEM_UI).build();
                                Intrinsics.checkNotNullExpressionValue(build8, "atMost(720, 1280).build()");
                                defaultVideoStrategy = build8;
                                break;
                            case 7:
                                DefaultVideoStrategy build9 = DefaultVideoStrategy.atMost(1080, 1920).build();
                                Intrinsics.checkNotNullExpressionValue(build9, "atMost(1080, 1920).build()");
                                defaultVideoStrategy = build9;
                                break;
                        }
                        if (booleanValue2) {
                            DefaultAudioStrategy build10 = DefaultAudioStrategy.builder().channels(-1).sampleRate(-1).build();
                            Intrinsics.checkNotNullExpressionValue(build10, "{\n                    va…build()\n                }");
                            removeTrackStrategy = build10;
                        } else {
                            removeTrackStrategy = new RemoveTrackStrategy();
                        }
                        if (num == null && num2 == null) {
                            trimDataSource = new UriDataSource(context, Uri.parse(str5));
                            str = str5;
                        } else {
                            UriDataSource uriDataSource = new UriDataSource(context, Uri.parse(str5));
                            long intValue6 = (num != null ? num.intValue() : 0) * 1000000;
                            int intValue7 = num2 != null ? num2.intValue() : 0;
                            str = str5;
                            trimDataSource = new TrimDataSource(uriDataSource, intValue6, intValue7 * 1000000);
                        }
                        Intrinsics.checkNotNull(str6);
                        final String str7 = str;
                        this.transcodeFuture = Transcoder.into(str6).addDataSource(trimDataSource).setAudioTrackStrategy(removeTrackStrategy).setVideoTrackStrategy(defaultVideoStrategy).setListener(new TranscoderListener() { // from class: com.example.video_compress.VideoCompressPlugin$onMethodCall$1
                            @Override // com.otaliastudios.transcoder.TranscoderListener
                            public void onTranscodeProgress(double progress) {
                                MethodChannel.this.invokeMethod("updateProgress", Double.valueOf(progress * 100.0d));
                            }

                            @Override // com.otaliastudios.transcoder.TranscoderListener
                            public void onTranscodeCompleted(int successCode) {
                                MethodChannel.this.invokeMethod("updateProgress", Double.valueOf(100.0d));
                                JSONObject mediaInfoJson = new Utility(this.getChannelName()).getMediaInfoJson(context, str6);
                                mediaInfoJson.put("isCancel", false);
                                result.success(mediaInfoJson.toString());
                                if (booleanValue) {
                                    new File(str7).delete();
                                }
                            }

                            @Override // com.otaliastudios.transcoder.TranscoderListener
                            public void onTranscodeCanceled() {
                                result.success(null);
                            }

                            @Override // com.otaliastudios.transcoder.TranscoderListener
                            public void onTranscodeFailed(Throwable exception) {
                                Intrinsics.checkNotNullParameter(exception, "exception");
                                result.success(null);
                            }
                        }).transcode();
                        return;
                    }
                    break;
                case 2130520060:
                    if (str2.equals("getMediaInfo")) {
                        String str8 = (String) call.argument(Constants.PATH);
                        Utility utility = new Utility(this.channelName);
                        Intrinsics.checkNotNull(str8);
                        result.success(utility.getMediaInfoJson(context, str8).toString());
                        return;
                    }
                    break;
            }
        }
        result.notImplemented();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Context applicationContext = binding.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "binding.applicationContext");
        BinaryMessenger binaryMessenger = binding.getBinaryMessenger();
        Intrinsics.checkNotNullExpressionValue(binaryMessenger, "binding.binaryMessenger");
        init(applicationContext, binaryMessenger);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        MethodChannel methodChannel = this._channel;
        if (methodChannel != null) {
            methodChannel.setMethodCallHandler(null);
        }
        this._context = null;
        this._channel = null;
    }

    private final void init(Context context, BinaryMessenger messenger) {
        MethodChannel methodChannel = new MethodChannel(messenger, this.channelName);
        methodChannel.setMethodCallHandler(this);
        this._context = context;
        this._channel = methodChannel;
    }
}
