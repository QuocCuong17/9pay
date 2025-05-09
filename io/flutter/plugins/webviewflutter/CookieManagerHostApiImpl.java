package io.flutter.plugins.webviewflutter;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import java.util.Objects;

/* loaded from: classes5.dex */
class CookieManagerHostApiImpl implements GeneratedAndroidWebView.CookieManagerHostApi {
    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.CookieManagerHostApi
    public void clearCookies(final GeneratedAndroidWebView.Result<Boolean> result) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= 21) {
            Objects.requireNonNull(result);
            cookieManager.removeAllCookies(new ValueCallback() { // from class: io.flutter.plugins.webviewflutter.CookieManagerHostApiImpl$$ExternalSyntheticLambda0
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    GeneratedAndroidWebView.Result.this.success((Boolean) obj);
                }
            });
        } else {
            boolean hasCookies = cookieManager.hasCookies();
            if (hasCookies) {
                cookieManager.removeAllCookie();
            }
            result.success(Boolean.valueOf(hasCookies));
        }
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.CookieManagerHostApi
    public void setCookie(String str, String str2) {
        CookieManager.getInstance().setCookie(str, str2);
    }
}
