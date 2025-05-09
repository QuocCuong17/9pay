package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.Logger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import io.flutter.plugins.firebase.analytics.Constants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FacebookSDKJSInterface.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J&\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00020\u00068GX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/facebook/appevents/FacebookSDKJSInterface;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PROTOCOL, "", "getProtocol", "()Ljava/lang/String;", "sendEvent", "", "pixelId", Constants.EVENT_NAME, "jsonString", "Companion", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FacebookSDKJSInterface {
    private static final String PARAMETER_FBSDK_PIXEL_REFERRAL = "_fb_pixel_referral_id";
    private final Context context;
    private final String protocol = "fbmq-0.1";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "FacebookSDKJSInterface";

    public FacebookSDKJSInterface(Context context) {
        this.context = context;
    }

    public static final /* synthetic */ String access$getTAG$cp() {
        if (CrashShieldHandler.isObjectCrashing(FacebookSDKJSInterface.class)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, FacebookSDKJSInterface.class);
            return null;
        }
    }

    @JavascriptInterface
    public final void sendEvent(String pixelId, String eventName, String jsonString) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            if (pixelId == null) {
                Logger.Companion companion = Logger.INSTANCE;
                LoggingBehavior loggingBehavior = LoggingBehavior.DEVELOPER_ERRORS;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                companion.log(loggingBehavior, TAG2, "Can't bridge an event without a referral Pixel ID. Check your webview Pixel configuration");
                return;
            }
            InternalAppEventsLogger createInstance$default = InternalAppEventsLogger.Companion.createInstance$default(InternalAppEventsLogger.INSTANCE, this.context, null, 2, null);
            Bundle jsonStringToBundle = INSTANCE.jsonStringToBundle(jsonString);
            jsonStringToBundle.putString(PARAMETER_FBSDK_PIXEL_REFERRAL, pixelId);
            createInstance$default.logEvent(eventName, jsonStringToBundle);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    @JavascriptInterface
    public final String getProtocol() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.protocol;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* compiled from: FacebookSDKJSInterface.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/facebook/appevents/FacebookSDKJSInterface$Companion;", "", "()V", "PARAMETER_FBSDK_PIXEL_REFERRAL", "", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "jsonStringToBundle", "Landroid/os/Bundle;", "jsonString", "jsonToBundle", "jsonObject", "Lorg/json/JSONObject;", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return FacebookSDKJSInterface.access$getTAG$cp();
        }

        private final Bundle jsonToBundle(JSONObject jsonObject) throws JSONException {
            Bundle bundle = new Bundle();
            Iterator<String> keys = jsonObject.keys();
            Intrinsics.checkNotNullExpressionValue(keys, "jsonObject.keys()");
            while (keys.hasNext()) {
                String next = keys.next();
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.String");
                String str = next;
                bundle.putString(str, jsonObject.getString(str));
            }
            return bundle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Bundle jsonStringToBundle(String jsonString) {
            try {
                return jsonToBundle(new JSONObject(jsonString));
            } catch (JSONException unused) {
                return new Bundle();
            }
        }
    }
}
