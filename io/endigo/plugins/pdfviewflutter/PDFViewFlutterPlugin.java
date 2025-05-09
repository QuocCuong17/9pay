package io.endigo.plugins.pdfviewflutter;

import io.flutter.embedding.engine.plugins.FlutterPlugin;

/* loaded from: classes5.dex */
public class PDFViewFlutterPlugin implements FlutterPlugin {
    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        flutterPluginBinding.getPlatformViewRegistry().registerViewFactory("plugins.endigo.io/pdfview", new PDFViewFactory(flutterPluginBinding.getBinaryMessenger()));
    }
}
