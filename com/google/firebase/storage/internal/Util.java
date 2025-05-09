package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes4.dex */
public class Util {
    public static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final int MAXIMUM_TOKEN_WAIT_TIME_MS = 30000;
    public static final int NETWORK_UNAVAILABLE = -2;
    private static final String TAG = "StorageUtil";

    public static long parseDateTime(String str) {
        if (str == null) {
            return 0L;
        }
        String replaceAll = str.replaceAll("Z$", "-0000");
        try {
            return new SimpleDateFormat(ISO_8601_FORMAT, Locale.getDefault()).parse(replaceAll).getTime();
        } catch (ParseException e) {
            Log.w(TAG, "unable to parse datetime:" + replaceAll, e);
            return 0L;
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        return Objects.equal(obj, obj2);
    }

    public static Uri normalize(FirebaseApp firebaseApp, String str) throws UnsupportedEncodingException {
        String substring;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri uri = NetworkRequest.PROD_BASE_URL;
        if (str.toLowerCase().startsWith("gs://")) {
            return Uri.parse("gs://" + Slashes.preserveSlashEncode(Slashes.normalizeSlashes(str.substring(5))));
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (scheme != null && (equals(scheme.toLowerCase(), ProxyConfig.MATCH_HTTP) || equals(scheme.toLowerCase(), "https"))) {
            int indexOf = parse.getAuthority().toLowerCase().indexOf(uri.getAuthority());
            String slashize = Slashes.slashize(parse.getEncodedPath());
            if (indexOf == 0 && slashize.startsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                int indexOf2 = slashize.indexOf("/b/", 0);
                int i = indexOf2 + 3;
                int indexOf3 = slashize.indexOf(RemoteSettings.FORWARD_SLASH_STRING, i);
                int indexOf4 = slashize.indexOf("/o/", 0);
                if (indexOf2 != -1 && indexOf3 != -1) {
                    substring = slashize.substring(i, indexOf3);
                    slashize = indexOf4 != -1 ? slashize.substring(indexOf4 + 3) : "";
                } else {
                    Log.w(TAG, "Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                    throw new IllegalArgumentException("Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                }
            } else if (indexOf > 1) {
                substring = parse.getAuthority().substring(0, indexOf - 1);
            } else {
                Log.w(TAG, "Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                throw new IllegalArgumentException("Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
            }
            Preconditions.checkNotEmpty(substring, "No bucket specified");
            return new Uri.Builder().scheme("gs").authority(substring).encodedPath(slashize).build();
        }
        Log.w(TAG, "FirebaseStorage is unable to support the scheme:" + scheme);
        throw new IllegalArgumentException("Uri scheme");
    }

    public static String getCurrentAuthToken(InternalAuthProvider internalAuthProvider) {
        String str;
        if (internalAuthProvider != null) {
            try {
                str = ((GetTokenResult) Tasks.await(internalAuthProvider.getAccessToken(false), 30000L, TimeUnit.MILLISECONDS)).getToken();
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e(TAG, "error getting token " + e);
            }
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        Log.w(TAG, "no auth token for request");
        return null;
    }

    public static String getCurrentAppCheckToken(InteropAppCheckTokenProvider interopAppCheckTokenProvider) {
        if (interopAppCheckTokenProvider == null) {
            return null;
        }
        try {
            AppCheckTokenResult appCheckTokenResult = (AppCheckTokenResult) Tasks.await(interopAppCheckTokenProvider.getToken(false), 30000L, TimeUnit.MILLISECONDS);
            if (appCheckTokenResult.getError() != null) {
                Log.w(TAG, "Error getting App Check token; using placeholder token instead. Error: " + appCheckTokenResult.getError());
            }
            return appCheckTokenResult.getToken();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(TAG, "Unexpected error getting App Check token: " + e);
            return null;
        }
    }
}
