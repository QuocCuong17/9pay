package dev.knottx.flutter_image_gallery_saver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import androidx.core.app.NotificationCompat;
import com.facebook.share.internal.ShareConstants;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FlutterImageGallerySaverPlugin.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\tH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Ldev/knottx/flutter_image_gallery_saver/FlutterImageGallerySaverPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "()V", "applicationContext", "Landroid/content/Context;", "channel", "Lio/flutter/plugin/common/MethodChannel;", "generateUri", "Landroid/net/Uri;", ShareConstants.MEDIA_EXTENSION, "", "getMIMEType", "onAttachedToEngine", "", "flutterPluginBinding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "binding", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "saveFile", "filePath", "saveImage", "bmp", "Landroid/graphics/Bitmap;", "sendBroadcast", "context", ShareConstants.MEDIA_URI, "flutter_image_gallery_saver_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FlutterImageGallerySaverPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    private Context applicationContext;
    private MethodChannel channel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Intrinsics.checkNotNullParameter(flutterPluginBinding, "flutterPluginBinding");
        this.applicationContext = flutterPluginBinding.getApplicationContext();
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), BuildConfig.LIBRARY_PACKAGE_NAME);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = call.method;
        if (Intrinsics.areEqual(str, "save_image")) {
            byte[] bArr = (byte[]) call.argument("image_bytes");
            if (bArr == null) {
                result.error("INVALID_ARGUMENTS", "No image bytes provided", null);
                return;
            }
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (decodeByteArray == null) {
                result.error("INVALID_ARGUMENTS", "Failed to decode image bytes", null);
                return;
            }
            try {
                saveImage(decodeByteArray);
                result.success(null);
                return;
            } catch (Exception e) {
                result.error("SAVE_FAILED", e.getMessage(), null);
                return;
            }
        }
        if (Intrinsics.areEqual(str, "save_file")) {
            String str2 = (String) call.argument("file_path");
            if (str2 == null) {
                result.error("INVALID_ARGUMENTS", "No file path provided", null);
                return;
            }
            try {
                saveFile(str2);
                result.success(null);
                return;
            } catch (Exception e2) {
                result.error("SAVE_FAILED", e2.getMessage(), null);
                return;
            }
        }
        result.notImplemented();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        MethodChannel methodChannel = this.channel;
        if (methodChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channel");
            methodChannel = null;
        }
        methodChannel.setMethodCallHandler(null);
    }

    private final void saveImage(Bitmap bmp) {
        ContentResolver contentResolver;
        Uri generateUri = generateUri("jpg");
        if (generateUri == null) {
            throw new Exception("Failed to generate file URI");
        }
        Context context = this.applicationContext;
        OutputStream openOutputStream = (context == null || (contentResolver = context.getContentResolver()) == null) ? null : contentResolver.openOutputStream(generateUri);
        try {
            if (openOutputStream == null) {
                throw new Exception("Failed to open output stream");
            }
            try {
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 100, openOutputStream)) {
                    throw new Exception("Bitmap compression failed");
                }
                openOutputStream.flush();
                Context context2 = this.applicationContext;
                Intrinsics.checkNotNull(context2);
                sendBroadcast(context2, generateUri);
            } catch (IOException e) {
                throw e;
            }
        } finally {
            openOutputStream.close();
            bmp.recycle();
        }
    }

    private final void saveFile(String filePath) {
        Unit unit;
        Context context = this.applicationContext;
        if (context == null) {
            throw new Exception("Application context is null");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception(filePath + " does not exist");
        }
        Uri generateUri = generateUri(FilesKt.getExtension(file));
        if (generateUri == null) {
            throw new Exception("Failed to generate file URI");
        }
        OutputStream fileInputStream = new FileInputStream(file);
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(generateUri);
            if (openOutputStream != null) {
                fileInputStream = openOutputStream;
                try {
                    OutputStream outputStream = fileInputStream;
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read <= 0) {
                            break;
                        } else {
                            outputStream.write(bArr, 0, read);
                        }
                    }
                    outputStream.flush();
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    unit = Unit.INSTANCE;
                } finally {
                }
            } else {
                unit = null;
            }
            if (unit == null) {
                throw new Exception("Failed to open output stream");
            }
            Unit unit3 = Unit.INSTANCE;
            CloseableKt.closeFinally(fileInputStream, null);
        } finally {
        }
    }

    static /* synthetic */ Uri generateUri$default(FlutterImageGallerySaverPlugin flutterImageGallerySaverPlugin, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        return flutterImageGallerySaverPlugin.generateUri(str);
    }

    private final Uri generateUri(String extension) {
        File externalStoragePublicDirectory;
        File file;
        Uri uri;
        ContentResolver contentResolver;
        String valueOf = String.valueOf(System.currentTimeMillis());
        String mIMEType = getMIMEType(extension);
        boolean z = false;
        if (mIMEType != null && StringsKt.startsWith$default(mIMEType, "video", false, 2, (Object) null)) {
            z = true;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (z) {
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else {
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }
            String str = z ? Environment.DIRECTORY_MOVIES : Environment.DIRECTORY_PICTURES;
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", valueOf);
            contentValues.put("relative_path", str);
            if (mIMEType != null) {
                contentValues.put("mime_type", mIMEType);
            }
            Context context = this.applicationContext;
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return null;
            }
            return contentResolver.insert(uri, contentValues);
        }
        if (z) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        } else {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }
        File file2 = new File(externalStoragePublicDirectory.getAbsolutePath());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!StringsKt.isBlank(extension)) {
            String lowerCase = extension.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            file = new File(file2, valueOf + "." + lowerCase);
        } else {
            file = new File(file2, valueOf);
        }
        return Uri.fromFile(file);
    }

    private final String getMIMEType(String extension) {
        if (!(!StringsKt.isBlank(extension))) {
            extension = null;
        }
        if (extension == null) {
            return null;
        }
        String lowerCase = extension.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (lowerCase != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase);
        }
        return null;
    }

    private final void sendBroadcast(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
}
