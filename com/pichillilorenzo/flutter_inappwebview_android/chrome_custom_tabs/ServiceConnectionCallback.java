package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import androidx.browser.customtabs.CustomTabsClient;

/* loaded from: classes4.dex */
public interface ServiceConnectionCallback {
    void onServiceConnected(CustomTabsClient customTabsClient);

    void onServiceDisconnected();
}
