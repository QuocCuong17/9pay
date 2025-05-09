package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.browser.customtabs.EngagementSignalsCallback;
import androidx.media3.common.C;
import com.pichillilorenzo.flutter_inappwebview_android.R;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.CustomTabActivityHelper;
import com.pichillilorenzo.flutter_inappwebview_android.types.AndroidResource;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomTabsActionButton;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomTabsMenuItem;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomTabsSecondaryToolbar;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ChromeCustomTabsActivity extends Activity implements Disposable {
    public static final int CHROME_CUSTOM_TAB_REQUEST_CODE = 100;
    protected static final String LOG_TAG = "CustomTabsActivity";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_chromesafaribrowser_";
    public static final int NO_HISTORY_CHROME_CUSTOM_TAB_REQUEST_CODE = 101;
    public CustomTabsActionButton actionButton;
    public CustomTabsIntent.Builder builder;
    public ChromeCustomTabsChannelDelegate channelDelegate;
    public CustomTabsSession customTabsSession;

    /* renamed from: id, reason: collision with root package name */
    public String f95id;
    public Map<String, String> initialHeaders;
    public List<String> initialOtherLikelyURLs;
    public String initialReferrer;
    public String initialUrl;
    public ChromeSafariBrowserManager manager;
    public CustomTabsSecondaryToolbar secondaryToolbar;
    public ChromeCustomTabsSettings customSettings = new ChromeCustomTabsSettings();
    public CustomTabActivityHelper customTabActivityHelper = new CustomTabActivityHelper();
    protected boolean onOpened = false;
    protected boolean onCompletedInitialLoad = false;
    protected boolean isBindSuccess = false;
    public List<CustomTabsMenuItem> menuItems = new ArrayList();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.chrome_custom_tabs_layout);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        this.f95id = extras.getString("id");
        ChromeSafariBrowserManager chromeSafariBrowserManager = ChromeSafariBrowserManager.shared.get(extras.getString("managerId"));
        this.manager = chromeSafariBrowserManager;
        if (chromeSafariBrowserManager == null || chromeSafariBrowserManager.plugin == null || this.manager.plugin.messenger == null) {
            return;
        }
        this.manager.browsers.put(this.f95id, this);
        this.channelDelegate = new ChromeCustomTabsChannelDelegate(this, new MethodChannel(this.manager.plugin.messenger, METHOD_CHANNEL_NAME_PREFIX + this.f95id));
        this.initialUrl = extras.getString("url");
        this.initialHeaders = (Map) extras.getSerializable("headers");
        this.initialReferrer = extras.getString("referrer");
        this.initialOtherLikelyURLs = extras.getStringArrayList("otherLikelyURLs");
        ChromeCustomTabsSettings chromeCustomTabsSettings = new ChromeCustomTabsSettings();
        this.customSettings = chromeCustomTabsSettings;
        chromeCustomTabsSettings.parse2((Map<String, Object>) extras.getSerializable("settings"));
        this.actionButton = CustomTabsActionButton.fromMap((Map) extras.getSerializable("actionButton"));
        this.secondaryToolbar = CustomTabsSecondaryToolbar.fromMap((Map) extras.getSerializable("secondaryToolbar"));
        Iterator it = ((List) extras.getSerializable("menuItemList")).iterator();
        while (it.hasNext()) {
            this.menuItems.add(CustomTabsMenuItem.fromMap((Map) it.next()));
        }
        if (this.customSettings.noHistory.booleanValue() && this.manager.plugin.noHistoryCustomTabsActivityCallbacks != null) {
            Map<String, String> map = this.manager.plugin.noHistoryCustomTabsActivityCallbacks.noHistoryBrowserIDs;
            String str = this.f95id;
            map.put(str, str);
        }
        this.customTabActivityHelper.setConnectionCallback(new CustomTabActivityHelper.ConnectionCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeCustomTabsActivity.1
            @Override // com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.CustomTabActivityHelper.ConnectionCallback
            public void onCustomTabsConnected() {
                ChromeCustomTabsActivity.this.customTabsConnected();
                if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                    ChromeCustomTabsActivity.this.channelDelegate.onServiceConnected();
                }
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.CustomTabActivityHelper.ConnectionCallback
            public void onCustomTabsDisconnected() {
                this.close();
                ChromeCustomTabsActivity.this.dispose();
            }
        });
        this.customTabActivityHelper.setCustomTabsCallback(new CustomTabsCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeCustomTabsActivity.2
            @Override // androidx.browser.customtabs.CustomTabsCallback
            public void extraCallback(String str2, Bundle bundle2) {
            }

            @Override // androidx.browser.customtabs.CustomTabsCallback
            public void onNavigationEvent(int i, Bundle bundle2) {
                if (i == 5 && !ChromeCustomTabsActivity.this.onOpened) {
                    ChromeCustomTabsActivity.this.onOpened = true;
                    if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                        ChromeCustomTabsActivity.this.channelDelegate.onOpened();
                    }
                }
                if (i == 2 && !ChromeCustomTabsActivity.this.onCompletedInitialLoad) {
                    ChromeCustomTabsActivity.this.onCompletedInitialLoad = true;
                    if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                        ChromeCustomTabsActivity.this.channelDelegate.onCompletedInitialLoad();
                    }
                }
                if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                    ChromeCustomTabsActivity.this.channelDelegate.onNavigationEvent(i);
                }
            }

            @Override // androidx.browser.customtabs.CustomTabsCallback
            public void onMessageChannelReady(Bundle bundle2) {
                if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                    ChromeCustomTabsActivity.this.channelDelegate.onMessageChannelReady();
                }
            }

            @Override // androidx.browser.customtabs.CustomTabsCallback
            public void onPostMessage(String str2, Bundle bundle2) {
                if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                    ChromeCustomTabsActivity.this.channelDelegate.onPostMessage(str2);
                }
            }

            @Override // androidx.browser.customtabs.CustomTabsCallback
            public void onRelationshipValidationResult(int i, Uri uri, boolean z, Bundle bundle2) {
                if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                    ChromeCustomTabsActivity.this.channelDelegate.onRelationshipValidationResult(i, uri, z);
                }
            }
        });
    }

    public void launchUrl(String str, Map<String, String> map, String str2, List<String> list) {
        launchUrlWithSession(this.customTabsSession, str, map, str2, list);
    }

    public void launchUrlWithSession(CustomTabsSession customTabsSession, String str, Map<String, String> map, String str2, List<String> list) {
        mayLaunchUrl(str, list);
        this.builder = new CustomTabsIntent.Builder(customTabsSession);
        prepareCustomTabs();
        CustomTabsIntent build = this.builder.build();
        prepareCustomTabsIntent(build);
        CustomTabActivityHelper.openCustomTab(this, build, Uri.parse(str), map, str2 != null ? Uri.parse(str2) : null, 100);
    }

    public boolean mayLaunchUrl(String str, List<String> list) {
        Uri parse = str != null ? Uri.parse(str) : null;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Bundle bundle = new Bundle();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                bundle.putString(CustomTabsService.KEY_URL, it.next());
            }
        }
        return this.customTabActivityHelper.mayLaunchUrl(parse, null, arrayList);
    }

    public void customTabsConnected() {
        String str;
        CustomTabsSession session = this.customTabActivityHelper.getSession();
        this.customTabsSession = session;
        if (session != null) {
            try {
                Bundle bundle = new Bundle();
                if (this.customTabsSession.isEngagementSignalsApiAvailable(bundle)) {
                    this.customTabsSession.setEngagementSignalsCallback(new EngagementSignalsCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ChromeCustomTabsActivity.3
                        @Override // androidx.browser.customtabs.EngagementSignalsCallback
                        public void onVerticalScrollEvent(boolean z, Bundle bundle2) {
                            if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                                ChromeCustomTabsActivity.this.channelDelegate.onVerticalScrollEvent(z);
                            }
                        }

                        @Override // androidx.browser.customtabs.EngagementSignalsCallback
                        public void onGreatestScrollPercentageIncreased(int i, Bundle bundle2) {
                            if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                                ChromeCustomTabsActivity.this.channelDelegate.onGreatestScrollPercentageIncreased(i);
                            }
                        }

                        @Override // androidx.browser.customtabs.EngagementSignalsCallback
                        public void onSessionEnded(boolean z, Bundle bundle2) {
                            if (ChromeCustomTabsActivity.this.channelDelegate != null) {
                                ChromeCustomTabsActivity.this.channelDelegate.onSessionEnded(z);
                            }
                        }
                    }, bundle);
                }
            } catch (Throwable th) {
                Log.d(LOG_TAG, "Custom Tabs Engagement Signals API not supported", th);
            }
        }
        if (!this.isBindSuccess || (str = this.initialUrl) == null) {
            return;
        }
        launchUrl(str, this.initialHeaders, this.initialReferrer, this.initialOtherLikelyURLs);
    }

    private void prepareCustomTabs() {
        if (this.builder == null) {
            return;
        }
        if (this.customSettings.addDefaultShareMenuItem != null) {
            this.builder.setShareState(this.customSettings.addDefaultShareMenuItem.booleanValue() ? 1 : 2);
        } else {
            this.builder.setShareState(this.customSettings.shareState.intValue());
        }
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
        this.builder.setShowTitle(this.customSettings.showTitle.booleanValue());
        this.builder.setUrlBarHidingEnabled(this.customSettings.enableUrlBarHiding.booleanValue());
        this.builder.setInstantAppsEnabled(this.customSettings.instantAppsEnabled.booleanValue());
        if (this.customSettings.startAnimations.size() == 2) {
            this.builder.setStartAnimations(this, this.customSettings.startAnimations.get(0).getIdentifier(this), this.customSettings.startAnimations.get(1).getIdentifier(this));
        }
        if (this.customSettings.exitAnimations.size() == 2) {
            this.builder.setExitAnimations(this, this.customSettings.exitAnimations.get(0).getIdentifier(this), this.customSettings.exitAnimations.get(1).getIdentifier(this));
        }
        for (CustomTabsMenuItem customTabsMenuItem : this.menuItems) {
            this.builder.addMenuItem(customTabsMenuItem.getLabel(), createPendingIntent(customTabsMenuItem.getId()));
        }
        CustomTabsActionButton customTabsActionButton = this.actionButton;
        if (customTabsActionButton != null) {
            byte[] icon = customTabsActionButton.getIcon();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            this.builder.setActionButton(BitmapFactory.decodeByteArray(icon, 0, icon.length, options), this.actionButton.getDescription(), createPendingIntent(this.actionButton.getId()), this.actionButton.isShouldTint());
        }
        CustomTabsSecondaryToolbar customTabsSecondaryToolbar = this.secondaryToolbar;
        if (customTabsSecondaryToolbar != null) {
            AndroidResource layout = customTabsSecondaryToolbar.getLayout();
            RemoteViews remoteViews = new RemoteViews(layout.getDefPackage(), layout.getIdentifier(this));
            int[] iArr = new int[this.secondaryToolbar.getClickableIDs().size()];
            int size = this.secondaryToolbar.getClickableIDs().size();
            for (int i = 0; i < size; i++) {
                iArr[i] = this.secondaryToolbar.getClickableIDs().get(i).getIdentifier(this);
            }
            this.builder.setSecondaryToolbarViews(remoteViews, iArr, getSecondaryToolbarOnClickPendingIntent());
        }
    }

    public PendingIntent getSecondaryToolbarOnClickPendingIntent() {
        Intent intent = new Intent(this, (Class<?>) ActionBroadcastReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActionBroadcastReceiver.KEY_ACTION_VIEW_ID, this.f95id);
        ChromeSafariBrowserManager chromeSafariBrowserManager = this.manager;
        bundle.putString(ActionBroadcastReceiver.KEY_ACTION_MANAGER_ID, chromeSafariBrowserManager != null ? chromeSafariBrowserManager.f96id : null);
        intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getBroadcast(this, 0, intent, 167772160);
        }
        return PendingIntent.getBroadcast(this, 0, intent, C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    private void prepareCustomTabsIntent(CustomTabsIntent customTabsIntent) {
        if (this.customSettings.packageName != null) {
            customTabsIntent.intent.setPackage(this.customSettings.packageName);
        } else {
            customTabsIntent.intent.setPackage(CustomTabsHelper.getPackageNameToUse(this));
        }
        if (this.customSettings.keepAliveEnabled.booleanValue()) {
            CustomTabsHelper.addKeepAliveExtra(this, customTabsIntent.intent);
        }
        if (this.customSettings.alwaysUseBrowserUI.booleanValue()) {
            CustomTabsIntent.setAlwaysUseBrowserUI(customTabsIntent.intent);
        }
    }

    public void updateActionButton(byte[] bArr, String str) {
        if (this.customTabsSession == null || this.actionButton == null) {
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        this.customTabsSession.setActionButton(BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options), str);
        this.actionButton.setIcon(bArr);
        this.actionButton.setDescription(str);
    }

    public void updateSecondaryToolbar(CustomTabsSecondaryToolbar customTabsSecondaryToolbar) {
        if (this.customTabsSession == null) {
            return;
        }
        AndroidResource layout = customTabsSecondaryToolbar.getLayout();
        RemoteViews remoteViews = new RemoteViews(layout.getDefPackage(), layout.getIdentifier(this));
        int[] iArr = new int[customTabsSecondaryToolbar.getClickableIDs().size()];
        int size = customTabsSecondaryToolbar.getClickableIDs().size();
        for (int i = 0; i < size; i++) {
            iArr[i] = customTabsSecondaryToolbar.getClickableIDs().get(i).getIdentifier(this);
        }
        this.customTabsSession.setSecondaryToolbarViews(remoteViews, iArr, getSecondaryToolbarOnClickPendingIntent());
        this.secondaryToolbar = customTabsSecondaryToolbar;
    }

    @Override // android.app.Activity
    protected void onStart() {
        String str;
        super.onStart();
        boolean bindCustomTabsService = this.customTabActivityHelper.bindCustomTabsService(this);
        this.isBindSuccess = bindCustomTabsService;
        if (bindCustomTabsService || (str = this.initialUrl) == null) {
            return;
        }
        launchUrlWithSession(null, str, this.initialHeaders, this.initialReferrer, this.initialOtherLikelyURLs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.customTabActivityHelper.unbindCustomTabsService(this);
        this.isBindSuccess = false;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100) {
            close();
            dispose();
        }
    }

    private PendingIntent createPendingIntent(int i) {
        Intent intent = new Intent(this, (Class<?>) ActionBroadcastReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ActionBroadcastReceiver.KEY_ACTION_ID, i);
        bundle.putString(ActionBroadcastReceiver.KEY_ACTION_VIEW_ID, this.f95id);
        ChromeSafariBrowserManager chromeSafariBrowserManager = this.manager;
        bundle.putString(ActionBroadcastReceiver.KEY_ACTION_MANAGER_ID, chromeSafariBrowserManager != null ? chromeSafariBrowserManager.f96id : null);
        intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getBroadcast(this, i, intent, 167772160);
        }
        return PendingIntent.getBroadcast(this, i, intent, C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        onStop();
        onDestroy();
        ChromeCustomTabsChannelDelegate chromeCustomTabsChannelDelegate = this.channelDelegate;
        if (chromeCustomTabsChannelDelegate != null) {
            chromeCustomTabsChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        ChromeSafariBrowserManager chromeSafariBrowserManager = this.manager;
        if (chromeSafariBrowserManager != null && chromeSafariBrowserManager.browsers.containsKey(this.f95id)) {
            this.manager.browsers.put(this.f95id, null);
        }
        this.manager = null;
    }

    public void close() {
        onStop();
        onDestroy();
        this.customTabsSession = null;
        finish();
        ChromeCustomTabsChannelDelegate chromeCustomTabsChannelDelegate = this.channelDelegate;
        if (chromeCustomTabsChannelDelegate != null) {
            chromeCustomTabsChannelDelegate.onClosed();
        }
    }
}
