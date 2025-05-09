package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.trusted.TrustedWebActivityIntent;
import androidx.browser.trusted.TrustedWebActivityIntentBuilder;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class TrustedWebActivity extends ChromeCustomTabsActivity {
    protected static final String LOG_TAG = "TrustedWebActivity";
    public TrustedWebActivityIntentBuilder builder;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeCustomTabsActivity
    public void launchUrl(String str, Map<String, String> map, String str2, List<String> list) {
        if (this.customTabsSession == null) {
            return;
        }
        Uri parse = Uri.parse(str);
        mayLaunchUrl(str, list);
        this.builder = new TrustedWebActivityIntentBuilder(parse);
        prepareCustomTabs();
        TrustedWebActivityIntent build = this.builder.build(this.customTabsSession);
        prepareCustomTabsIntent(build);
        CustomTabActivityHelper.openTrustedWebActivity(this, build, parse, map, str2 != null ? Uri.parse(str2) : null, 100);
    }

    private void prepareCustomTabs() {
        CustomTabColorSchemeParams.Builder builder = new CustomTabColorSchemeParams.Builder();
        if (this.customSettings.toolbarBackgroundColor != null && !this.customSettings.toolbarBackgroundColor.isEmpty()) {
            builder.setToolbarColor(Color.parseColor(this.customSettings.toolbarBackgroundColor));
        }
        if (this.customSettings.navigationBarColor != null && !this.customSettings.navigationBarColor.isEmpty()) {
            builder.setNavigationBarColor(Color.parseColor(this.customSettings.navigationBarColor));
        }
        if (this.customSettings.navigationBarDividerColor != null && !this.customSettings.navigationBarDividerColor.isEmpty()) {
            builder.setNavigationBarDividerColor(Color.parseColor(this.customSettings.navigationBarDividerColor));
        }
        if (this.customSettings.secondaryToolbarColor != null && !this.customSettings.secondaryToolbarColor.isEmpty()) {
            builder.setSecondaryToolbarColor(Color.parseColor(this.customSettings.secondaryToolbarColor));
        }
        this.builder.setDefaultColorSchemeParams(builder.build());
        if (this.customSettings.additionalTrustedOrigins != null && !this.customSettings.additionalTrustedOrigins.isEmpty()) {
            this.builder.setAdditionalTrustedOrigins(this.customSettings.additionalTrustedOrigins);
        }
        if (this.customSettings.displayMode != null) {
            this.builder.setDisplayMode(this.customSettings.displayMode);
        }
        this.builder.setScreenOrientation(this.customSettings.screenOrientation.intValue());
    }

    private void prepareCustomTabsIntent(TrustedWebActivityIntent trustedWebActivityIntent) {
        Intent intent = trustedWebActivityIntent.getIntent();
        if (this.customSettings.packageName != null) {
            intent.setPackage(this.customSettings.packageName);
        } else {
            intent.setPackage(CustomTabsHelper.getPackageNameToUse(this));
        }
        if (this.customSettings.keepAliveEnabled.booleanValue()) {
            CustomTabsHelper.addKeepAliveExtra(this, intent);
        }
        if (this.customSettings.alwaysUseBrowserUI.booleanValue()) {
            CustomTabsIntent.setAlwaysUseBrowserUI(intent);
        }
    }
}
