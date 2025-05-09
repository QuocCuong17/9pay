package dev.fluttercommunity.plus.device_info;

import android.content.Context;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes5.dex */
public class DeviceInfoPlusPlugin implements FlutterPlugin {
    MethodChannel channel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        setupMethodChannel(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        tearDownChannel();
    }

    private void setupMethodChannel(BinaryMessenger binaryMessenger, Context context) {
        this.channel = new MethodChannel(binaryMessenger, "dev.fluttercommunity.plus/device_info");
        this.channel.setMethodCallHandler(new MethodCallHandlerImpl(context.getContentResolver(), context.getPackageManager()));
    }

    private void tearDownChannel() {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }
}
