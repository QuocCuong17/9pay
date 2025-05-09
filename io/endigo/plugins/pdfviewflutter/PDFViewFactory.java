package io.endigo.plugins.pdfviewflutter;

import android.content.Context;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;

/* loaded from: classes5.dex */
public class PDFViewFactory extends PlatformViewFactory {
    private final BinaryMessenger messenger;

    public PDFViewFactory(BinaryMessenger binaryMessenger) {
        super(StandardMessageCodec.INSTANCE);
        this.messenger = binaryMessenger;
    }

    @Override // io.flutter.plugin.platform.PlatformViewFactory
    public PlatformView create(Context context, int i, Object obj) {
        return new FlutterPDFView(context, this.messenger, i, (Map) obj);
    }
}
