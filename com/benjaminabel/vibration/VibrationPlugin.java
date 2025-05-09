package com.benjaminabel.vibration;

import android.content.Context;
import android.os.Vibrator;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/* loaded from: classes2.dex */
public class VibrationPlugin implements FlutterPlugin {
    private static final String CHANNEL = "vibration";
    private MethodChannel methodChannel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        teardownChannels();
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new VibrationPlugin().setupChannels(registrar.messenger(), registrar.context());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        setupChannels(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    }

    private void setupChannels(BinaryMessenger binaryMessenger, Context context) {
        VibrationMethodChannelHandler vibrationMethodChannelHandler = new VibrationMethodChannelHandler(new Vibration((Vibrator) context.getSystemService("vibrator")));
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, CHANNEL);
        this.methodChannel = methodChannel;
        methodChannel.setMethodCallHandler(vibrationMethodChannelHandler);
    }

    private void teardownChannels() {
        this.methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
    }
}
