package com.example.video_compress;

import android.content.Context;
import android.graphics.Bitmap;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.database.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ThumbnailUtility.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ.\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/video_compress/ThumbnailUtility;", "", "channelName", "", "(Ljava/lang/String;)V", "utility", "Lcom/example/video_compress/Utility;", "getByteThumbnail", "", Constants.PATH, "quality", "", "position", "", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "getFileThumbnail", "context", "Landroid/content/Context;", "video_compress_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ThumbnailUtility {
    private final Utility utility;

    public ThumbnailUtility(String channelName) {
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        this.utility = new Utility(channelName);
    }

    public final void getByteThumbnail(String path, int quality, long position, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(result, "result");
        Bitmap bitmap = this.utility.getBitmap(path, position, result);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bitmap.recycle();
        Intrinsics.checkNotNullExpressionValue(byteArray, "byteArray");
        result.success(CollectionsKt.toByteArray(ArraysKt.toList(byteArray)));
    }

    public final void getFileThumbnail(Context context, String path, int quality, long position, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(result, "result");
        Bitmap bitmap = this.utility.getBitmap(path, position, result);
        File externalFilesDir = context.getExternalFilesDir("video_compress");
        if (externalFilesDir != null && !externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        String str = path;
        String substring = path.substring(StringsKt.lastIndexOf$default((CharSequence) str, '/', 0, false, 6, (Object) null), StringsKt.lastIndexOf$default((CharSequence) str, FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(substring);
        sb.append(".jpg");
        File file = new File(externalFilesDir, sb.toString());
        this.utility.deleteFile(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            file.createNewFile();
            Intrinsics.checkNotNullExpressionValue(byteArray, "byteArray");
            FilesKt.writeBytes(file, byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap.recycle();
        result.success(file.getAbsolutePath());
    }
}
