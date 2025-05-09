package com.heqingbao.flutter.plugin.email_launcher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.net.MailTo;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ActionBroadcastReceiver;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* compiled from: EmailLauncherPlugin.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J+\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0012j\b\u0012\u0004\u0012\u00020\u0010`\u0013H\u0002¢\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u00020\t2\b\b\u0001\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\t2\b\b\u0001\u0010\u0019\u001a\u00020\u0017H\u0016J\u001c\u0010\u001a\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/heqingbao/flutter/plugin/email_launcher/EmailLauncherPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "()V", "channel", "Lio/flutter/plugin/common/MethodChannel;", "context", "Landroid/content/Context;", "launchEmail", "", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "listArrayToArray", "", "", PDPageLabelRange.STYLE_ROMAN_LOWER, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)[Ljava/lang/String;", "onAttachedToEngine", "flutterPluginBinding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "binding", "onMethodCall", "email_launcher_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class EmailLauncherPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    private MethodChannel channel;
    private Context context;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Intrinsics.checkNotNullParameter(flutterPluginBinding, "flutterPluginBinding");
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "email_launcher");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        this.context = flutterPluginBinding.getApplicationContext();
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        if (Intrinsics.areEqual(call.method, "launch")) {
            launchEmail(call, result);
        } else {
            result.notImplemented();
        }
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

    private final void launchEmail(MethodCall call, MethodChannel.Result result) {
        String str;
        String str2;
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2;
        ArrayList<String> arrayList3;
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(MailTo.MAILTO_SCHEME));
        intent.setFlags(268435456);
        if (call.hasArgument("to") && (arrayList3 = (ArrayList) call.argument("to")) != null) {
            intent.putExtra("android.intent.extra.EMAIL", listArrayToArray(arrayList3));
        }
        if (call.hasArgument("cc") && (arrayList2 = (ArrayList) call.argument("cc")) != null) {
            intent.putExtra("android.intent.extra.CC", listArrayToArray(arrayList2));
        }
        if (call.hasArgument("bcc") && (arrayList = (ArrayList) call.argument("bcc")) != null) {
            intent.putExtra("android.intent.extra.BCC", listArrayToArray(arrayList));
        }
        if (call.hasArgument("subject") && (str2 = (String) call.argument("subject")) != null) {
            intent.putExtra(ActionBroadcastReceiver.KEY_URL_TITLE, str2);
        }
        if (call.hasArgument("body") && (str = (String) call.argument("body")) != null) {
            intent.putExtra("android.intent.extra.TEXT", str);
        }
        Context context = this.context;
        if (context != null) {
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                result.success(true);
            } else {
                result.error("1", "No mail client or no mail configuration", null);
            }
        }
    }

    private final String[] listArrayToArray(ArrayList<String> r) {
        Object[] array = r.toArray(new String[r.size()]);
        Intrinsics.checkNotNullExpressionValue(array, "r.toArray(arrayOfNulls<String>(r.size))");
        return (String[]) array;
    }
}
