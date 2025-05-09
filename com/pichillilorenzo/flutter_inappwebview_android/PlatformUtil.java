package com.pichillilorenzo.flutter_inappwebview_android;

import android.os.Build;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.sentry.protocol.Device;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class PlatformUtil extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "PlatformUtil";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_platformutil";
    public InAppWebViewFlutterPlugin plugin;

    public PlatformUtil(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        if (!str.equals("formatDate")) {
            if (str.equals("getSystemVersion")) {
                result.success(String.valueOf(Build.VERSION.SDK_INT));
                return;
            } else {
                result.notImplemented();
                return;
            }
        }
        long longValue = ((Long) methodCall.argument(WorkflowModule.Properties.Section.Component.Type.DATE)).longValue();
        String str2 = (String) methodCall.argument("format");
        Locale localeFromString = getLocaleFromString((String) methodCall.argument(Device.JsonKeys.LOCALE));
        String str3 = (String) methodCall.argument("timezone");
        if (str3 == null) {
            str3 = "UTC";
        }
        result.success(formatDate(longValue, str2, localeFromString, TimeZone.getTimeZone(str3)));
    }

    public static Locale getLocaleFromString(String str) {
        if (str == null) {
            return Locale.US;
        }
        String[] split = str.split("_");
        return new Locale(split[0], split.length > 1 ? split[1] : "", split.length > 2 ? split[2] : "");
    }

    public static String formatDate(long j, String str, Locale locale, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date(j));
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }
}
