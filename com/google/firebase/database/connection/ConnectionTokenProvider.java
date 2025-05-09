package com.google.firebase.database.connection;

/* loaded from: classes4.dex */
public interface ConnectionTokenProvider {

    /* loaded from: classes4.dex */
    public interface GetTokenCallback {
        void onError(String str);

        void onSuccess(String str);
    }

    void getToken(boolean z, GetTokenCallback getTokenCallback);
}
