package com.google.firebase.dynamiclinks;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.DynamicLink;

/* loaded from: classes4.dex */
public abstract class FirebaseDynamicLinks {
    public abstract DynamicLink.Builder createDynamicLink();

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Intent intent);

    public abstract Task<PendingDynamicLinkData> getDynamicLink(Uri uri);

    public static synchronized FirebaseDynamicLinks getInstance() {
        FirebaseDynamicLinks firebaseDynamicLinks;
        synchronized (FirebaseDynamicLinks.class) {
            firebaseDynamicLinks = getInstance(FirebaseApp.getInstance());
        }
        return firebaseDynamicLinks;
    }

    public static synchronized FirebaseDynamicLinks getInstance(FirebaseApp firebaseApp) {
        FirebaseDynamicLinks firebaseDynamicLinks;
        synchronized (FirebaseDynamicLinks.class) {
            firebaseDynamicLinks = (FirebaseDynamicLinks) firebaseApp.get(FirebaseDynamicLinks.class);
        }
        return firebaseDynamicLinks;
    }
}
