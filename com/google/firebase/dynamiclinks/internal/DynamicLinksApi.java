package com.google.firebase.dynamiclinks.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
public class DynamicLinksApi extends GoogleApi<Api.ApiOptions.NoOptions> {
    static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.AbstractClientBuilder<DynamicLinksClient, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
    private static final Api.ClientKey<DynamicLinksClient> CLIENT_KEY;

    static {
        Api.ClientKey<DynamicLinksClient> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        Api.AbstractClientBuilder<DynamicLinksClient, Api.ApiOptions.NoOptions> abstractClientBuilder = new Api.AbstractClientBuilder<DynamicLinksClient, Api.ApiOptions.NoOptions>() { // from class: com.google.firebase.dynamiclinks.internal.DynamicLinksApi.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public DynamicLinksClient buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new DynamicLinksClient(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER = abstractClientBuilder;
        API = new Api<>("DynamicLinks.API", abstractClientBuilder, clientKey);
    }

    public DynamicLinksApi(Context context) {
        super(context, API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
