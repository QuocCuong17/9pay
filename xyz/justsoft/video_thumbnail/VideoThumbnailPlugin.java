package xyz.justsoft.video_thumbnail;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.database.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class VideoThumbnailPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    private static final int HIGH_QUALITY_MIN_VAL = 70;
    private static String TAG = "ThumbnailPlugin";
    private MethodChannel channel;
    private Context context;
    private ExecutorService executor;

    private static String formatExt(int i) {
        return i != 1 ? i != 2 ? "jpg" : "webp" : "png";
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.context = flutterPluginBinding.getApplicationContext();
        this.executor = Executors.newCachedThreadPool();
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "plugins.justsoft.xyz/video_thumbnail");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
        this.executor.shutdown();
        this.executor = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        final Map map = (Map) methodCall.arguments();
        final String str = (String) map.get("video");
        final HashMap hashMap = (HashMap) map.get("headers");
        final int intValue = ((Integer) map.get("format")).intValue();
        final int intValue2 = ((Integer) map.get("maxh")).intValue();
        final int intValue3 = ((Integer) map.get("maxw")).intValue();
        final int intValue4 = ((Integer) map.get("timeMs")).intValue();
        final int intValue5 = ((Integer) map.get("quality")).intValue();
        final String str2 = methodCall.method;
        this.executor.execute(new Runnable() { // from class: xyz.justsoft.video_thumbnail.VideoThumbnailPlugin.1
            @Override // java.lang.Runnable
            public void run() {
                Object obj;
                Object obj2 = null;
                boolean z = false;
                try {
                    boolean z2 = true;
                    if (str2.equals("file")) {
                        obj = VideoThumbnailPlugin.this.buildThumbnailFile(str, hashMap, (String) map.get(Constants.PATH), intValue, intValue2, intValue3, intValue4, intValue5);
                    } else if (str2.equals("data")) {
                        obj = VideoThumbnailPlugin.this.buildThumbnailData(str, hashMap, intValue, intValue2, intValue3, intValue4, intValue5);
                    } else {
                        z2 = false;
                        obj = null;
                    }
                    e = null;
                    obj2 = obj;
                    z = z2;
                } catch (Exception e) {
                    e = e;
                }
                VideoThumbnailPlugin.this.onResult(result, obj2, z, e);
            }
        });
    }

    private static Bitmap.CompressFormat intToFormat(int i) {
        if (i == 1) {
            return Bitmap.CompressFormat.PNG;
        }
        if (i != 2) {
            return Bitmap.CompressFormat.JPEG;
        }
        return Bitmap.CompressFormat.WEBP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] buildThumbnailData(String str, HashMap<String, String> hashMap, int i, int i2, int i3, int i4, int i5) {
        Bitmap createVideoThumbnail = createVideoThumbnail(str, hashMap, i2, i3, i4);
        Objects.requireNonNull(createVideoThumbnail);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createVideoThumbnail.compress(intToFormat(i), i5, byteArrayOutputStream);
        createVideoThumbnail.recycle();
        Objects.requireNonNull(createVideoThumbnail);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildThumbnailFile(String str, HashMap<String, String> hashMap, String str2, int i, int i2, int i3, int i4, int i5) {
        byte[] buildThumbnailData = buildThumbnailData(str, hashMap, i, i2, i3, i4, i5);
        String formatExt = formatExt(i);
        String str3 = str.substring(0, str.lastIndexOf(".") + 1) + formatExt;
        String absolutePath = (str2 != null || (str.startsWith(RemoteSettings.FORWARD_SLASH_STRING) || str.startsWith("file://"))) ? str2 : this.context.getCacheDir().getAbsolutePath();
        if (absolutePath != null) {
            if (absolutePath.endsWith(formatExt)) {
                str3 = absolutePath;
            } else {
                int lastIndexOf = str3.lastIndexOf(RemoteSettings.FORWARD_SLASH_STRING);
                if (absolutePath.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                    str3 = absolutePath + str3.substring(lastIndexOf + 1);
                } else {
                    str3 = absolutePath + str3.substring(lastIndexOf);
                }
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            fileOutputStream.write(buildThumbnailData);
            fileOutputStream.close();
            Log.d(TAG, String.format("buildThumbnailFile( written:%d )", Integer.valueOf(buildThumbnailData.length)));
            return str3;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResult(final MethodChannel.Result result, final Object obj, final boolean z, final Exception exc) {
        runOnUiThread(new Runnable() { // from class: xyz.justsoft.video_thumbnail.VideoThumbnailPlugin.2
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    result.notImplemented();
                    return;
                }
                Exception exc2 = exc;
                if (exc2 != null) {
                    exc2.printStackTrace();
                    result.error("exception", exc.getMessage(), null);
                } else {
                    result.success(obj);
                }
            }
        });
    }

    private static void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public Bitmap createVideoThumbnail(String str, HashMap<String, String> hashMap, int i, int i2, int i3) {
        Bitmap bitmap;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                if (str.startsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                    setDataSource(str, mediaMetadataRetriever);
                } else if (str.startsWith("file://")) {
                    setDataSource(str.substring(7), mediaMetadataRetriever);
                } else {
                    if (hashMap == null) {
                        hashMap = new HashMap<>();
                    }
                    mediaMetadataRetriever.setDataSource(str, hashMap);
                }
                try {
                    if (i == 0 && i2 == 0) {
                        bitmap = mediaMetadataRetriever.getFrameAtTime(i3 * 1000, 3);
                        mediaMetadataRetriever.release();
                        return bitmap;
                    }
                    mediaMetadataRetriever.release();
                    return bitmap;
                } catch (IOException | RuntimeException e) {
                    e.printStackTrace();
                    return bitmap;
                }
                if (Build.VERSION.SDK_INT < 27 || i == 0 || i2 == 0) {
                    Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(i3 * 1000, 3);
                    if (frameAtTime != null) {
                        int width = frameAtTime.getWidth();
                        int height = frameAtTime.getHeight();
                        if (i2 == 0) {
                            i2 = Math.round((i / height) * width);
                        }
                        if (i == 0) {
                            i = Math.round((i2 / width) * height);
                        }
                        Log.d(TAG, String.format("original w:%d, h:%d => %d, %d", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(i2), Integer.valueOf(i)));
                        bitmap = Bitmap.createScaledBitmap(frameAtTime, i2, i, true);
                    } else {
                        bitmap = frameAtTime;
                    }
                } else {
                    bitmap = mediaMetadataRetriever.getScaledFrameAtTime(i3 * 1000, 3, i2, i);
                }
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException | RuntimeException e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                return null;
            } catch (RuntimeException e5) {
                e = e5;
                e.printStackTrace();
                return null;
            }
            return null;
        } catch (IllegalArgumentException e6) {
            e6.printStackTrace();
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e7) {
                e = e7;
                e.printStackTrace();
                return null;
            } catch (RuntimeException e8) {
                e = e8;
                e.printStackTrace();
                return null;
            }
            return null;
        } catch (RuntimeException e9) {
            e9.printStackTrace();
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e10) {
                e = e10;
                e.printStackTrace();
                return null;
            } catch (RuntimeException e11) {
                e = e11;
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }

    private static void setDataSource(String str, MediaMetadataRetriever mediaMetadataRetriever) throws IOException {
        mediaMetadataRetriever.setDataSource(new FileInputStream(new File(str).getAbsolutePath()).getFD());
    }
}
