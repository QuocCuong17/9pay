package com.example.video_compress;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.database.Constants;
import io.sentry.protocol.Device;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

/* compiled from: Utility.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0003J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/example/video_compress/Utility;", "", "channelName", "", "(Ljava/lang/String;)V", "deleteAllCache", "", "context", "Landroid/content/Context;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "deleteFile", "file", "Ljava/io/File;", "getBitmap", "Landroid/graphics/Bitmap;", Constants.PATH, "position", "", "getFileNameWithGifExtension", "getMediaInfoJson", "Lorg/json/JSONObject;", "isLandscapeImage", "", Device.JsonKeys.ORIENTATION, "", "timeStrToTimestamp", "time", "video_compress_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Utility {
    private final String channelName;

    public final boolean isLandscapeImage(int orientation) {
        return (orientation == 90 || orientation == 270) ? false : true;
    }

    public Utility(String channelName) {
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        this.channelName = channelName;
    }

    public final void deleteFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            file.delete();
        }
    }

    public final long timeStrToTimestamp(String time) {
        Intrinsics.checkNotNullParameter(time, "time");
        List split$default = StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null);
        int parseInt = Integer.parseInt((String) split$default.get(0));
        int parseInt2 = Integer.parseInt((String) split$default.get(1));
        List split$default2 = StringsKt.split$default((CharSequence) split$default.get(2), new String[]{"."}, false, 0, 6, (Object) null);
        return (((parseInt * 3600) + (parseInt2 * 60) + Integer.parseInt((String) split$default2.get(0))) * 1000) + Integer.parseInt((String) split$default2.get(1));
    }

    public final JSONObject getMediaInfoJson(Context context, String path) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(path, "path");
        File file = new File(path);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, Uri.fromFile(file));
        String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(7);
        if (extractMetadata2 == null) {
            extractMetadata2 = "";
        }
        String extractMetadata3 = mediaMetadataRetriever.extractMetadata(3);
        String str = extractMetadata3 != null ? extractMetadata3 : "";
        String extractMetadata4 = mediaMetadataRetriever.extractMetadata(18);
        String extractMetadata5 = mediaMetadataRetriever.extractMetadata(19);
        long parseLong = Long.parseLong(extractMetadata);
        long parseLong2 = Long.parseLong(extractMetadata4);
        long parseLong3 = Long.parseLong(extractMetadata5);
        long length = file.length();
        String extractMetadata6 = Build.VERSION.SDK_INT >= 17 ? mediaMetadataRetriever.extractMetadata(24) : null;
        Integer intOrNull = extractMetadata6 != null ? StringsKt.toIntOrNull(extractMetadata6) : null;
        if (intOrNull != null && isLandscapeImage(intOrNull.intValue())) {
            parseLong3 = parseLong2;
            parseLong2 = parseLong3;
        }
        mediaMetadataRetriever.release();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.PATH, path);
        jSONObject.put("title", extractMetadata2);
        jSONObject.put("author", str);
        jSONObject.put("width", parseLong2);
        jSONObject.put("height", parseLong3);
        jSONObject.put("duration", parseLong);
        jSONObject.put("filesize", length);
        if (intOrNull != null) {
            jSONObject.put(Device.JsonKeys.ORIENTATION, intOrNull.intValue());
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bitmap getBitmap(String path, long position, MethodChannel.Result result) {
        int max;
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(result, "result");
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        Bitmap bitmap = null;
        try {
            try {
                try {
                    mediaMetadataRetriever.setDataSource(path);
                    Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(position, 2);
                    try {
                        mediaMetadataRetriever.release();
                    } catch (RuntimeException unused) {
                        result.error(this.channelName, "Ignore failures while cleaning up", null);
                    }
                    bitmap = frameAtTime;
                } catch (Throwable th) {
                    try {
                        mediaMetadataRetriever.release();
                    } catch (RuntimeException unused2) {
                        result.error(this.channelName, "Ignore failures while cleaning up", null);
                    }
                    throw th;
                }
            } catch (RuntimeException unused3) {
                result.error(this.channelName, "Assume this is a corrupt video file", null);
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException unused4) {
                    result.error(this.channelName, "Ignore failures while cleaning up", null);
                }
                if (bitmap == null) {
                }
                Intrinsics.checkNotNull(bitmap);
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                max = Math.max(width, height);
                if (max > 512) {
                }
                Intrinsics.checkNotNull(bitmap);
                return bitmap;
            }
        } catch (IllegalArgumentException unused5) {
            result.error(this.channelName, "Assume this is a corrupt video file", null);
            mediaMetadataRetriever.release();
            if (bitmap == null) {
            }
            Intrinsics.checkNotNull(bitmap);
            int width2 = bitmap.getWidth();
            int height2 = bitmap.getHeight();
            max = Math.max(width2, height2);
            if (max > 512) {
            }
            Intrinsics.checkNotNull(bitmap);
            return bitmap;
        }
        if (bitmap == null) {
            result.success(new Integer[0]);
        }
        Intrinsics.checkNotNull(bitmap);
        int width22 = bitmap.getWidth();
        int height22 = bitmap.getHeight();
        max = Math.max(width22, height22);
        if (max > 512) {
            float f = 512.0f / max;
            bitmap = Bitmap.createScaledBitmap(bitmap, Math.round(width22 * f), Math.round(f * height22), true);
        }
        Intrinsics.checkNotNull(bitmap);
        return bitmap;
    }

    public final String getFileNameWithGifExtension(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        File file = new File(path);
        String str = FilenameUtils.EXTENSION_SEPARATOR + "gif";
        if (!file.exists()) {
            return "";
        }
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        String replaceAfterLast$default = StringsKt.replaceAfterLast$default(name, ".", "gif", (String) null, 4, (Object) null);
        if (StringsKt.endsWith$default(replaceAfterLast$default, str, false, 2, (Object) null)) {
            return replaceAfterLast$default;
        }
        return replaceAfterLast$default + str;
    }

    public final void deleteAllCache(Context context, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(result, "result");
        File externalFilesDir = context.getExternalFilesDir("video_compress");
        result.success(externalFilesDir != null ? Boolean.valueOf(FilesKt.deleteRecursively(externalFilesDir)) : null);
    }
}
