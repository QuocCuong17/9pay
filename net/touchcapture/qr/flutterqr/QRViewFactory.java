package net.touchcapture.qr.flutterqr;

import android.content.Context;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QRViewFactory.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lnet/touchcapture/qr/flutterqr/QRViewFactory;", "Lio/flutter/plugin/platform/PlatformViewFactory;", "messenger", "Lio/flutter/plugin/common/BinaryMessenger;", "(Lio/flutter/plugin/common/BinaryMessenger;)V", "create", "Lio/flutter/plugin/platform/PlatformView;", "context", "Landroid/content/Context;", "viewId", "", "args", "", "qr_code_scanner_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class QRViewFactory extends PlatformViewFactory {
    private final BinaryMessenger messenger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QRViewFactory(BinaryMessenger messenger) {
        super(StandardMessageCodec.INSTANCE);
        Intrinsics.checkNotNullParameter(messenger, "messenger");
        this.messenger = messenger;
    }

    @Override // io.flutter.plugin.platform.PlatformViewFactory
    public PlatformView create(Context context, int viewId, Object args) {
        Intrinsics.checkNotNull(args, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.Any>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.Any> }");
        HashMap hashMap = (HashMap) args;
        if (context != null) {
            return new QRView(context, this.messenger, viewId, hashMap);
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
