package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsIntent;

/* loaded from: classes4.dex */
public class ActionBroadcastReceiver extends BroadcastReceiver {
    public static final String KEY_ACTION_ID = "com.pichillilorenzo.flutter_inappwebview.ChromeCustomTabs.ACTION_ID";
    public static final String KEY_ACTION_MANAGER_ID = "com.pichillilorenzo.flutter_inappwebview.ChromeCustomTabs.ACTION_MANAGER_ID";
    public static final String KEY_ACTION_VIEW_ID = "com.pichillilorenzo.flutter_inappwebview.ChromeCustomTabs.ACTION_VIEW_ID";
    public static final String KEY_URL_TITLE = "android.intent.extra.SUBJECT";
    protected static final String LOG_TAG = "ActionBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ChromeSafariBrowserManager chromeSafariBrowserManager;
        int intExtra = intent.getIntExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_CLICKED_ID, -1);
        String dataString = intent.getDataString();
        if (dataString != null) {
            Bundle extras = intent.getExtras();
            String string = extras.getString(KEY_ACTION_VIEW_ID);
            String string2 = extras.getString(KEY_ACTION_MANAGER_ID);
            if (string2 == null || (chromeSafariBrowserManager = ChromeSafariBrowserManager.shared.get(string2)) == null) {
                return;
            }
            if (intExtra == -1) {
                int i = extras.getInt(KEY_ACTION_ID);
                String string3 = extras.getString(KEY_URL_TITLE);
                ChromeCustomTabsActivity chromeCustomTabsActivity = chromeSafariBrowserManager.browsers.get(string);
                if (chromeCustomTabsActivity == null || chromeCustomTabsActivity.channelDelegate == null) {
                    return;
                }
                chromeCustomTabsActivity.channelDelegate.onItemActionPerform(i, dataString, string3);
                return;
            }
            ChromeCustomTabsActivity chromeCustomTabsActivity2 = chromeSafariBrowserManager.browsers.get(string);
            if (chromeCustomTabsActivity2 == null || chromeCustomTabsActivity2.channelDelegate == null) {
                return;
            }
            chromeCustomTabsActivity2.channelDelegate.onSecondaryItemActionPerform(chromeCustomTabsActivity2.getResources().getResourceName(intExtra), dataString);
        }
    }
}
