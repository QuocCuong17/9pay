package com.llfbandit.app_links;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import org.microg.safeparcel.SafeParcelReader;

/* loaded from: classes4.dex */
public class AppLinksHelper {
    private static final String FIREBASE_DYNAMIC_LINKS_DATA = "com.google.firebase.dynamiclinks.DYNAMIC_LINK_DATA";
    private static final String TAG = "com.llfbandit.app_links";

    public static String getDeepLinkFromIntent(Intent intent) {
        String shortDeepLink = getShortDeepLink(intent);
        if (shortDeepLink != null) {
            Log.d("com.llfbandit.app_links", "handleIntent: (Data) (short deep link)" + shortDeepLink);
            return shortDeepLink;
        }
        return getUrl(intent);
    }

    private static String getShortDeepLink(Intent intent) {
        byte[] byteArrayExtra = intent.getByteArrayExtra("com.google.firebase.dynamiclinks.DYNAMIC_LINK_DATA");
        if (byteArrayExtra == null || byteArrayExtra.length == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(byteArrayExtra, 0, byteArrayExtra.length);
        obtain.setDataPosition(0);
        return SafeParcelReader.readString(obtain, obtain.readInt());
    }

    private static String getUrl(Intent intent) {
        Bundle extras;
        Uri uri;
        String action = intent.getAction();
        String dataString = intent.getDataString();
        if ("android.intent.action.SEND".equals(action) && (extras = intent.getExtras()) != null) {
            if (extras.containsKey("android.intent.extra.TEXT")) {
                CharSequence charSequence = extras.getCharSequence("android.intent.extra.TEXT");
                if (charSequence != null) {
                    dataString = charSequence.toString();
                }
            } else if (extras.containsKey("android.intent.extra.STREAM") && (uri = (Uri) extras.getParcelable("android.intent.extra.STREAM")) != null) {
                dataString = uri.toString();
            }
        }
        Log.d("com.llfbandit.app_links", "handleIntent: (Action) " + action);
        Log.d("com.llfbandit.app_links", "handleIntent: (Data) " + dataString);
        return dataString;
    }
}
