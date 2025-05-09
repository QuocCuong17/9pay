package com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs;

import android.content.Intent;
import androidx.browser.trusted.TrustedWebActivityDisplayMode;
import com.pichillilorenzo.flutter_inappwebview_android.ISettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.AndroidResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ChromeCustomTabsSettings implements ISettings<ChromeCustomTabsActivity> {
    static final String LOG_TAG = "ChromeCustomTabsSettings";

    @Deprecated
    public Boolean addDefaultShareMenuItem;
    public String navigationBarColor;
    public String navigationBarDividerColor;
    public String packageName;
    public String secondaryToolbarColor;
    public String toolbarBackgroundColor;
    public Integer shareState = 0;
    public Boolean showTitle = true;
    public Boolean enableUrlBarHiding = false;
    public Boolean instantAppsEnabled = false;
    public Boolean keepAliveEnabled = false;
    public Boolean isSingleInstance = false;
    public Boolean noHistory = false;
    public Boolean isTrustedWebActivity = false;
    public List<String> additionalTrustedOrigins = new ArrayList();
    public TrustedWebActivityDisplayMode displayMode = null;
    public Integer screenOrientation = 0;
    public List<AndroidResource> startAnimations = new ArrayList();
    public List<AndroidResource> exitAnimations = new ArrayList();
    public Boolean alwaysUseBrowserUI = false;

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public /* bridge */ /* synthetic */ ISettings<ChromeCustomTabsActivity> parse(Map map) {
        return parse2((Map<String, Object>) map);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0029. Please report as an issue. */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    /* renamed from: parse, reason: avoid collision after fix types in other method */
    public ISettings<ChromeCustomTabsActivity> parse2(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                key.hashCode();
                char c = 65535;
                switch (key.hashCode()) {
                    case -2112880751:
                        if (key.equals("addDefaultShareMenuItem")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -2080611471:
                        if (key.equals("startAnimations")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1913803429:
                        if (key.equals("showTitle")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1799055374:
                        if (key.equals("shareState")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -632275769:
                        if (key.equals("isSingleInstance")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -462720700:
                        if (key.equals("navigationBarColor")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -329366839:
                        if (key.equals("navigationBarDividerColor")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -227713574:
                        if (key.equals("toolbarBackgroundColor")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 137483238:
                        if (key.equals("enableUrlBarHiding")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case 227582404:
                        if (key.equals("screenOrientation")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case 317955373:
                        if (key.equals("exitAnimations")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 378069052:
                        if (key.equals("secondaryToolbarColor")) {
                            c = 11;
                            break;
                        }
                        break;
                    case 390710230:
                        if (key.equals("isTrustedWebActivity")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case 472764366:
                        if (key.equals("instantAppsEnabled")) {
                            c = '\r';
                            break;
                        }
                        break;
                    case 908759025:
                        if (key.equals("packageName")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 1518510995:
                        if (key.equals("noHistory")) {
                            c = 15;
                            break;
                        }
                        break;
                    case 1578203421:
                        if (key.equals("additionalTrustedOrigins")) {
                            c = 16;
                            break;
                        }
                        break;
                    case 1603728804:
                        if (key.equals("alwaysUseBrowserUI")) {
                            c = 17;
                            break;
                        }
                        break;
                    case 1714132357:
                        if (key.equals("displayMode")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 2126240217:
                        if (key.equals("keepAliveEnabled")) {
                            c = 19;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.addDefaultShareMenuItem = (Boolean) value;
                        break;
                    case 1:
                        for (Map map2 : (List) value) {
                            if (AndroidResource.fromMap(map2) != null) {
                                this.startAnimations.add(AndroidResource.fromMap(map2));
                            }
                        }
                        break;
                    case 2:
                        this.showTitle = (Boolean) value;
                        break;
                    case 3:
                        this.shareState = (Integer) value;
                        break;
                    case 4:
                        this.isSingleInstance = (Boolean) value;
                        break;
                    case 5:
                        this.navigationBarColor = (String) value;
                        break;
                    case 6:
                        this.navigationBarDividerColor = (String) value;
                        break;
                    case 7:
                        this.toolbarBackgroundColor = (String) value;
                        break;
                    case '\b':
                        this.enableUrlBarHiding = (Boolean) value;
                        break;
                    case '\t':
                        this.screenOrientation = (Integer) value;
                        break;
                    case '\n':
                        for (Map map3 : (List) value) {
                            if (AndroidResource.fromMap(map3) != null) {
                                this.exitAnimations.add(AndroidResource.fromMap(map3));
                            }
                        }
                        break;
                    case 11:
                        this.secondaryToolbarColor = (String) value;
                        break;
                    case '\f':
                        this.isTrustedWebActivity = (Boolean) value;
                        break;
                    case '\r':
                        this.instantAppsEnabled = (Boolean) value;
                        break;
                    case 14:
                        this.packageName = (String) value;
                        break;
                    case 15:
                        this.noHistory = (Boolean) value;
                        break;
                    case 16:
                        this.additionalTrustedOrigins = (List) value;
                        break;
                    case 17:
                        this.alwaysUseBrowserUI = (Boolean) value;
                        break;
                    case 18:
                        Map map4 = (Map) value;
                        String str = (String) map4.get("type");
                        if (str != null) {
                            str.hashCode();
                            if (str.equals("IMMERSIVE_MODE")) {
                                this.displayMode = new TrustedWebActivityDisplayMode.ImmersiveMode(((Boolean) map4.get("isSticky")).booleanValue(), ((Integer) map4.get("displayCutoutMode")).intValue());
                                break;
                            } else if (str.equals("DEFAULT_MODE")) {
                                this.displayMode = new TrustedWebActivityDisplayMode.DefaultMode();
                                break;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    case 19:
                        this.keepAliveEnabled = (Boolean) value;
                        break;
                }
            }
        }
        return this;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("addDefaultShareMenuItem", this.addDefaultShareMenuItem);
        hashMap.put("showTitle", this.showTitle);
        hashMap.put("toolbarBackgroundColor", this.toolbarBackgroundColor);
        hashMap.put("navigationBarColor", this.navigationBarColor);
        hashMap.put("navigationBarDividerColor", this.navigationBarDividerColor);
        hashMap.put("secondaryToolbarColor", this.secondaryToolbarColor);
        hashMap.put("enableUrlBarHiding", this.enableUrlBarHiding);
        hashMap.put("instantAppsEnabled", this.instantAppsEnabled);
        hashMap.put("packageName", this.packageName);
        hashMap.put("keepAliveEnabled", this.keepAliveEnabled);
        hashMap.put("isSingleInstance", this.isSingleInstance);
        hashMap.put("noHistory", this.noHistory);
        hashMap.put("isTrustedWebActivity", this.isTrustedWebActivity);
        hashMap.put("additionalTrustedOrigins", this.additionalTrustedOrigins);
        hashMap.put("screenOrientation", this.screenOrientation);
        hashMap.put("alwaysUseBrowserUI", this.alwaysUseBrowserUI);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.ISettings
    public Map<String, Object> getRealSettings(ChromeCustomTabsActivity chromeCustomTabsActivity) {
        Intent intent;
        Map<String, Object> map = toMap();
        if (chromeCustomTabsActivity != null && (intent = chromeCustomTabsActivity.getIntent()) != null) {
            map.put("packageName", intent.getPackage());
            map.put("keepAliveEnabled", Boolean.valueOf(intent.hasExtra("android.support.customtabs.extra.KEEP_ALIVE")));
        }
        return map;
    }
}
