package dev.fluttercommunity.plus.share;

import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.facebook.share.internal.ShareConstants;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MethodCallHandler.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Ldev/fluttercommunity/plus/share/MethodCallHandler;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "share", "Ldev/fluttercommunity/plus/share/Share;", "manager", "Ldev/fluttercommunity/plus/share/ShareSuccessManager;", "(Ldev/fluttercommunity/plus/share/Share;Ldev/fluttercommunity/plus/share/ShareSuccessManager;)V", "expectMapArguments", "", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", "onMethodCall", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "share_plus_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MethodCallHandler implements MethodChannel.MethodCallHandler {
    private final ShareSuccessManager manager;
    private final Share share;

    public MethodCallHandler(Share share, ShareSuccessManager manager) {
        Intrinsics.checkNotNullParameter(share, "share");
        Intrinsics.checkNotNullParameter(manager, "manager");
        this.share = share;
        this.manager = manager;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0040, code lost:
    
        if (r3.equals("share") == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x007d, code lost:
    
        expectMapArguments(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0080, code lost:
    
        if (r1 == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0088, code lost:
    
        if (r12.manager.setCallback(r14) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008b, code lost:
    
        r2 = r12.share;
        r3 = r13.argument("text");
        kotlin.jvm.internal.Intrinsics.checkNotNull(r3, "null cannot be cast to non-null type kotlin.String");
        r2.share((java.lang.String) r3, (java.lang.String) r13.argument("subject"), r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x009f, code lost:
    
        if (r1 != false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a1, code lost:
    
        if (r0 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a3, code lost:
    
        r14.success(dev.fluttercommunity.plus.share.ShareSuccessManager.RESULT_UNAVAILABLE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a7, code lost:
    
        r14.success(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
    
        if (r3.equals("shareFilesWithResult") == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
    
        expectMapArguments(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b7, code lost:
    
        if (r1 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bf, code lost:
    
        if (r12.manager.setCallback(r14) != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c1, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c2, code lost:
    
        r5 = r12.share;
        r2 = r13.argument("paths");
        kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
        r5.shareFiles((java.util.List) r2, (java.util.List) r13.argument("mimeTypes"), (java.lang.String) r13.argument("text"), (java.lang.String) r13.argument("subject"), r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00eb, code lost:
    
        if (r1 != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ed, code lost:
    
        if (r0 == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ef, code lost:
    
        r14.success(dev.fluttercommunity.plus.share.ShareSuccessManager.RESULT_UNAVAILABLE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00f3, code lost:
    
        r14.success(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f7, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f8, code lost:
    
        r14.error("Share failed", r13.getMessage(), null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0079, code lost:
    
        if (r3.equals("shareWithResult") == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b1, code lost:
    
        if (r3.equals("shareFiles") == false) goto L54;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0035. Please report as an issue. */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00f8 -> B:42:0x0105). Please report as a decompilation issue!!! */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        String str = call.method;
        Intrinsics.checkNotNullExpressionValue(str, "call.method");
        boolean endsWith$default = StringsKt.endsWith$default(str, "WithResult", false, 2, (Object) null);
        boolean z = endsWith$default && Build.VERSION.SDK_INT >= 22;
        String str2 = call.method;
        if (str2 != null) {
            switch (str2.hashCode()) {
                case -1811378728:
                    break;
                case -1594861118:
                    break;
                case -1212337029:
                    break;
                case -743768819:
                    if (str2.equals("shareUri")) {
                        expectMapArguments(call);
                        Share share = this.share;
                        Object argument = call.argument(ShareConstants.MEDIA_URI);
                        Intrinsics.checkNotNull(argument, "null cannot be cast to non-null type kotlin.String");
                        share.share((String) argument, null, false);
                        if (z) {
                            return;
                        }
                        result.success(null);
                        return;
                    }
                    break;
                case 109400031:
                    break;
            }
        }
        result.notImplemented();
    }

    private final void expectMapArguments(MethodCall call) throws IllegalArgumentException {
        if (!(call.arguments instanceof Map)) {
            throw new IllegalArgumentException("Map arguments expected".toString());
        }
    }
}
