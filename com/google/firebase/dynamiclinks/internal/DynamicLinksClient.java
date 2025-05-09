package com.google.firebase.dynamiclinks.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks;
import com.google.firebase.dynamiclinks.internal.IDynamicLinksService;

/* loaded from: classes4.dex */
public class DynamicLinksClient extends GmsClient<IDynamicLinksService> {
    public static final String ACTION_START_SERVICE = "com.google.firebase.dynamiclinks.service.START";
    private static final int DYNAMIC_LINKS_API_VALUE = 131;
    public static final String SERVICE_DESCRIPTOR = "com.google.firebase.dynamiclinks.internal.IDynamicLinksService";
    private static final int V17 = 12451000;

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return 12451000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return ACTION_START_SERVICE;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public boolean usesClientTelemetry() {
        return true;
    }

    public DynamicLinksClient(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 131, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public IDynamicLinksService createServiceInterface(IBinder iBinder) {
        return IDynamicLinksService.Stub.asInterface(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getDynamicLink(IDynamicLinksCallbacks.Stub stub, String str) {
        try {
            ((IDynamicLinksService) getService()).getDynamicLink(stub, str);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createShortDynamicLink(IDynamicLinksCallbacks.Stub stub, Bundle bundle) {
        try {
            ((IDynamicLinksService) getService()).createShortDynamicLink(stub, bundle);
        } catch (RemoteException unused) {
        }
    }
}
