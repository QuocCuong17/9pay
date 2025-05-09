package io.flutter.plugins.urllauncher;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import io.flutter.plugins.urllauncher.Messages;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
final class UrlLauncher implements Messages.UrlLauncherApi {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "UrlLauncher";
    private Activity activity;
    private final Context applicationContext;
    private final IntentResolver intentResolver;

    /* loaded from: classes5.dex */
    interface IntentResolver {
        String getHandlerComponentName(Intent intent);
    }

    UrlLauncher(Context context, IntentResolver intentResolver) {
        this.applicationContext = context;
        this.intentResolver = intentResolver;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UrlLauncher(final Context context) {
        this(context, new IntentResolver() { // from class: io.flutter.plugins.urllauncher.UrlLauncher$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.urllauncher.UrlLauncher.IntentResolver
            public final String getHandlerComponentName(Intent intent) {
                return UrlLauncher.lambda$new$0(context, intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$new$0(Context context, Intent intent) {
        ComponentName resolveActivity = intent.resolveActivity(context.getPackageManager());
        if (resolveActivity == null) {
            return null;
        }
        return resolveActivity.toShortString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override // io.flutter.plugins.urllauncher.Messages.UrlLauncherApi
    public Boolean canLaunchUrl(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        if (this.intentResolver.getHandlerComponentName(intent) == null) {
            return false;
        }
        return Boolean.valueOf(!"{com.android.fallback/com.android.fallback.Fallback}".equals(r3));
    }

    @Override // io.flutter.plugins.urllauncher.Messages.UrlLauncherApi
    public Boolean launchUrl(String str, Map<String, String> map) {
        ensureActivity();
        try {
            this.activity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(str)).putExtra("com.android.browser.headers", extractBundle(map)));
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    @Override // io.flutter.plugins.urllauncher.Messages.UrlLauncherApi
    public Boolean openUrlInApp(String str, Boolean bool, Messages.WebViewOptions webViewOptions, Messages.BrowserOptions browserOptions) {
        ensureActivity();
        Bundle extractBundle = extractBundle(webViewOptions.getHeaders());
        if (bool.booleanValue() && !containsRestrictedHeader(webViewOptions.getHeaders())) {
            if (openCustomTab(this.activity, Uri.parse(str), extractBundle, browserOptions)) {
                return true;
            }
        }
        try {
            this.activity.startActivity(WebViewActivity.createIntent(this.activity, str, webViewOptions.getEnableJavaScript().booleanValue(), webViewOptions.getEnableDomStorage().booleanValue(), extractBundle));
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    @Override // io.flutter.plugins.urllauncher.Messages.UrlLauncherApi
    public void closeWebView() {
        this.applicationContext.sendBroadcast(new Intent(WebViewActivity.ACTION_CLOSE));
    }

    @Override // io.flutter.plugins.urllauncher.Messages.UrlLauncherApi
    public Boolean supportsCustomTabs() {
        return Boolean.valueOf(CustomTabsClient.getPackageName(this.applicationContext, Collections.emptyList()) != null);
    }

    private static boolean openCustomTab(Context context, Uri uri, Bundle bundle, Messages.BrowserOptions browserOptions) {
        CustomTabsIntent build = new CustomTabsIntent.Builder().setShowTitle(browserOptions.getShowTitle().booleanValue()).build();
        build.intent.putExtra("com.android.browser.headers", bundle);
        try {
            build.launchUrl(context, uri);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0024. Please report as an issue. */
    private static boolean containsRestrictedHeader(Map<String, String> map) {
        Iterator<String> it = map.keySet().iterator();
        while (true) {
            char c = 0;
            if (!it.hasNext()) {
                return false;
            }
            String lowerCase = it.next().toLowerCase(Locale.US);
            lowerCase.hashCode();
            switch (lowerCase.hashCode()) {
                case -1423461112:
                    if (lowerCase.equals("accept")) {
                        break;
                    }
                    c = 65535;
                    break;
                case -1229727188:
                    if (lowerCase.equals("content-language")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 785670158:
                    if (lowerCase.equals(Keys.CONTENT_TYPE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 802785917:
                    if (lowerCase.equals("accept-language")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                default:
                    return true;
            }
        }
    }

    private static Bundle extractBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            bundle.putString(str, map.get(str));
        }
        return bundle;
    }

    private void ensureActivity() {
        if (this.activity == null) {
            throw new Messages.FlutterError("NO_ACTIVITY", "Launching a URL requires a foreground activity.", null);
        }
    }
}
