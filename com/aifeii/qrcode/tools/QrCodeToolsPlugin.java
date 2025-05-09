package com.aifeii.qrcode.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QrCodeToolsPlugin.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\tH\u0016J\u001c\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/aifeii/qrcode/tools/QrCodeToolsPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "()V", "channel", "Lio/flutter/plugin/common/MethodChannel;", "onAttachedToEngine", "", "flutterPluginBinding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "binding", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "qr_code_tools_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class QrCodeToolsPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    private MethodChannel channel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Intrinsics.checkNotNullParameter(flutterPluginBinding, "flutterPluginBinding");
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "qr_code_tools");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        if (Intrinsics.areEqual(call.method, "decoder")) {
            String str = (String) call.argument("file");
            File file = new File(str);
            if (!file.exists()) {
                result.error("File not found. filePath: " + str, null, null);
                return;
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(file));
            int width = decodeStream.getWidth();
            int height = decodeStream.getHeight();
            int[] iArr = new int[width * height];
            decodeStream.getPixels(iArr, 0, width, 0, 0, width, height);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(decodeStream.getWidth(), decodeStream.getHeight(), iArr)));
            Hashtable hashtable = new Hashtable();
            ArrayList arrayList = new ArrayList();
            arrayList.add(BarcodeFormat.QR_CODE);
            Hashtable hashtable2 = hashtable;
            hashtable2.put(DecodeHintType.POSSIBLE_FORMATS, arrayList);
            hashtable2.put(DecodeHintType.CHARACTER_SET, "utf-8");
            hashtable2.put(DecodeHintType.TRY_HARDER, true);
            try {
                result.success(new MultiFormatReader().decode(binaryBitmap, hashtable).getText());
                return;
            } catch (NotFoundException unused) {
                result.error("Not found data", null, null);
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
}
