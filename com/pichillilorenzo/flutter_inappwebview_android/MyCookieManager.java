package com.pichillilorenzo.flutter_inappwebview_android;

import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import androidx.webkit.CookieManagerCompat;
import androidx.webkit.WebViewFeature;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.database.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class MyCookieManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "MyCookieManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_cookiemanager";
    public static CookieManager cookieManager;
    public InAppWebViewFlutterPlugin plugin;

    public MyCookieManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static void init() {
        if (cookieManager == null) {
            cookieManager = getCookieManager();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        init();
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1222815761:
                if (str.equals("deleteCookie")) {
                    c = 0;
                    break;
                }
                break;
            case -913968963:
                if (str.equals("removeSessionCookies")) {
                    c = 1;
                    break;
                }
                break;
            case 126640486:
                if (str.equals("setCookie")) {
                    c = 2;
                    break;
                }
                break;
            case 747417188:
                if (str.equals("deleteCookies")) {
                    c = 3;
                    break;
                }
                break;
            case 822411705:
                if (str.equals("deleteAllCookies")) {
                    c = 4;
                    break;
                }
                break;
            case 1989049945:
                if (str.equals("getCookies")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                deleteCookie((String) methodCall.argument("url"), (String) methodCall.argument("name"), (String) methodCall.argument(DynamicLink.Builder.KEY_DOMAIN), (String) methodCall.argument(Constants.PATH), result);
                return;
            case 1:
                removeSessionCookies(result);
                return;
            case 2:
                String str2 = (String) methodCall.argument("url");
                String str3 = (String) methodCall.argument("name");
                String str4 = (String) methodCall.argument("value");
                String str5 = (String) methodCall.argument(DynamicLink.Builder.KEY_DOMAIN);
                String str6 = (String) methodCall.argument(Constants.PATH);
                String str7 = (String) methodCall.argument("expiresDate");
                setCookie(str2, str3, str4, str5, str6, str7 != null ? new Long(str7) : null, (Integer) methodCall.argument("maxAge"), (Boolean) methodCall.argument("isSecure"), (Boolean) methodCall.argument("isHttpOnly"), (String) methodCall.argument("sameSite"), result);
                return;
            case 3:
                deleteCookies((String) methodCall.argument("url"), (String) methodCall.argument(DynamicLink.Builder.KEY_DOMAIN), (String) methodCall.argument(Constants.PATH), result);
                return;
            case 4:
                deleteAllCookies(result);
                return;
            case 5:
                result.success(getCookies((String) methodCall.argument("url")));
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    private static CookieManager getCookieManager() {
        if (cookieManager == null) {
            try {
                cookieManager = CookieManager.getInstance();
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Exception e) {
                if (e.getMessage() == null || !e.getClass().getCanonicalName().equals("android.webkit.WebViewFactory.MissingWebViewPackageException")) {
                    throw e;
                }
                return null;
            }
        }
        return cookieManager;
    }

    public void setCookie(String str, String str2, String str3, String str4, String str5, Long l, Integer num, Boolean bool, Boolean bool2, String str6, final MethodChannel.Result result) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            result.success(false);
            return;
        }
        String str7 = str2 + "=" + str3 + "; Path=" + str5;
        if (str4 != null) {
            str7 = str7 + "; Domain=" + str4;
        }
        if (l != null) {
            str7 = str7 + "; Expires=" + getCookieExpirationDate(l);
        }
        if (num != null) {
            str7 = str7 + "; Max-Age=" + num.toString();
        }
        if (bool != null && bool.booleanValue()) {
            str7 = str7 + "; Secure";
        }
        if (bool2 != null && bool2.booleanValue()) {
            str7 = str7 + "; HttpOnly";
        }
        if (str6 != null) {
            str7 = str7 + "; SameSite=" + str6;
        }
        String str8 = str7 + ";";
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.setCookie(str, str8, new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.1
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool3) {
                    result.success(bool3);
                }
            });
            cookieManager.flush();
            return;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            CookieSyncManager createInstance = CookieSyncManager.createInstance(inAppWebViewFlutterPlugin.applicationContext);
            createInstance.startSync();
            cookieManager.setCookie(str, str8);
            createInstance.stopSync();
            createInstance.sync();
            result.success(true);
            return;
        }
        cookieManager.setCookie(str, str8);
        result.success(true);
    }

    public List<Map<String, Object>> getCookies(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            return arrayList;
        }
        List arrayList2 = new ArrayList();
        String str3 = "GET_COOKIE_INFO";
        String str4 = ";";
        if (WebViewFeature.isFeatureSupported("GET_COOKIE_INFO")) {
            arrayList2 = CookieManagerCompat.getCookieInfo(cookieManager, str);
        } else {
            String cookie = cookieManager.getCookie(str);
            if (cookie != null) {
                arrayList2 = Arrays.asList(cookie.split(";"));
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            String[] split = ((String) it.next()).split(str4);
            if (split.length != 0) {
                String[] split2 = split[0].split("=", 2);
                String trim = split2[0].trim();
                String trim2 = split2.length > 1 ? split2[1].trim() : "";
                HashMap hashMap = new HashMap();
                hashMap.put("name", trim);
                hashMap.put("value", trim2);
                hashMap.put("expiresDate", null);
                hashMap.put("isSessionOnly", null);
                hashMap.put(DynamicLink.Builder.KEY_DOMAIN, null);
                hashMap.put("sameSite", null);
                hashMap.put("isSecure", null);
                hashMap.put("isHttpOnly", null);
                hashMap.put(Constants.PATH, null);
                if (WebViewFeature.isFeatureSupported(str3)) {
                    hashMap.put("isSecure", false);
                    hashMap.put("isHttpOnly", false);
                    int i = 1;
                    while (i < split.length) {
                        Iterator it2 = it;
                        String[] split3 = split[i].split("=", 2);
                        String trim3 = split3[0].trim();
                        String str5 = str3;
                        String[] strArr = split;
                        String trim4 = split3.length > 1 ? split3[1].trim() : "";
                        if (trim3.equalsIgnoreCase("Expires")) {
                            try {
                                str2 = str4;
                                try {
                                    Date parse = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.US).parse(trim4);
                                    if (parse != null) {
                                        hashMap.put("expiresDate", Long.valueOf(parse.getTime()));
                                    }
                                } catch (ParseException e) {
                                    e = e;
                                    Log.e(LOG_TAG, "", e);
                                    i++;
                                    str3 = str5;
                                    it = it2;
                                    split = strArr;
                                    str4 = str2;
                                }
                            } catch (ParseException e2) {
                                e = e2;
                                str2 = str4;
                            }
                        } else {
                            str2 = str4;
                            if (trim3.equalsIgnoreCase("Max-Age")) {
                                try {
                                    hashMap.put("expiresDate", Long.valueOf(System.currentTimeMillis() + Long.parseLong(trim4)));
                                } catch (NumberFormatException e3) {
                                    Log.e(LOG_TAG, "", e3);
                                }
                            } else if (trim3.equalsIgnoreCase("Domain")) {
                                hashMap.put(DynamicLink.Builder.KEY_DOMAIN, trim4);
                            } else if (trim3.equalsIgnoreCase("SameSite")) {
                                hashMap.put("sameSite", trim4);
                            } else {
                                if (trim3.equalsIgnoreCase("Secure")) {
                                    hashMap.put("isSecure", true);
                                } else if (trim3.equalsIgnoreCase("HttpOnly")) {
                                    hashMap.put("isHttpOnly", true);
                                } else if (trim3.equalsIgnoreCase("Path")) {
                                    hashMap.put(Constants.PATH, trim4);
                                }
                                i++;
                                str3 = str5;
                                it = it2;
                                split = strArr;
                                str4 = str2;
                            }
                        }
                        i++;
                        str3 = str5;
                        it = it2;
                        split = strArr;
                        str4 = str2;
                    }
                }
                arrayList.add(hashMap);
                str3 = str3;
                it = it;
                str4 = str4;
            }
        }
        return arrayList;
    }

    public void deleteCookie(String str, String str2, String str3, String str4, final MethodChannel.Result result) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            result.success(false);
            return;
        }
        String str5 = str2 + "=; Path=" + str4 + "; Max-Age=-1";
        if (str3 != null) {
            str5 = str5 + "; Domain=" + str3;
        }
        String str6 = str5 + ";";
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.setCookie(str, str6, new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool) {
                    result.success(bool);
                }
            });
            cookieManager.flush();
            return;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            CookieSyncManager createInstance = CookieSyncManager.createInstance(inAppWebViewFlutterPlugin.applicationContext);
            createInstance.startSync();
            cookieManager.setCookie(str, str6);
            createInstance.stopSync();
            createInstance.sync();
            result.success(true);
            return;
        }
        cookieManager.setCookie(str, str6);
        result.success(true);
    }

    public void deleteCookies(String str, String str2, String str3, MethodChannel.Result result) {
        CookieSyncManager cookieSyncManager;
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin;
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            result.success(false);
            return;
        }
        String cookie = cookieManager2.getCookie(str);
        if (cookie != null) {
            if (Build.VERSION.SDK_INT >= 21 || (inAppWebViewFlutterPlugin = this.plugin) == null) {
                cookieSyncManager = null;
            } else {
                cookieSyncManager = CookieSyncManager.createInstance(inAppWebViewFlutterPlugin.applicationContext);
                cookieSyncManager.startSync();
            }
            for (String str4 : cookie.split(";")) {
                String str5 = str4.split("=", 2)[0].trim() + "=; Path=" + str3 + "; Max-Age=-1";
                if (str2 != null) {
                    str5 = str5 + "; Domain=" + str2;
                }
                String str6 = str5 + ";";
                if (Build.VERSION.SDK_INT >= 21) {
                    cookieManager.setCookie(str, str6, null);
                } else {
                    cookieManager.setCookie(str, str6);
                }
            }
            if (cookieSyncManager != null) {
                cookieSyncManager.stopSync();
                cookieSyncManager.sync();
            } else if (Build.VERSION.SDK_INT >= 21) {
                cookieManager.flush();
            }
        }
        result.success(true);
    }

    public void deleteAllCookies(final MethodChannel.Result result) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            result.success(false);
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.removeAllCookies(new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.3
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool) {
                    result.success(bool);
                }
            });
            cookieManager.flush();
            return;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            CookieSyncManager createInstance = CookieSyncManager.createInstance(inAppWebViewFlutterPlugin.applicationContext);
            createInstance.startSync();
            cookieManager.removeAllCookie();
            createInstance.stopSync();
            createInstance.sync();
            result.success(true);
            return;
        }
        cookieManager.removeAllCookie();
        result.success(true);
    }

    public void removeSessionCookies(final MethodChannel.Result result) {
        CookieManager cookieManager2 = getCookieManager();
        cookieManager = cookieManager2;
        if (cookieManager2 == null) {
            result.success(false);
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.removeSessionCookies(new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.MyCookieManager.4
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool) {
                    result.success(bool);
                }
            });
            cookieManager.flush();
            return;
        }
        InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
        if (inAppWebViewFlutterPlugin != null) {
            CookieSyncManager createInstance = CookieSyncManager.createInstance(inAppWebViewFlutterPlugin.applicationContext);
            createInstance.startSync();
            cookieManager.removeSessionCookie();
            createInstance.stopSync();
            createInstance.sync();
            result.success(true);
            return;
        }
        cookieManager.removeSessionCookie();
        result.success(true);
    }

    public static String getCookieExpirationDate(Long l) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(new Date(l.longValue()));
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }
}
