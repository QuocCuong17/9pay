package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes3.dex */
public final class zzh implements CredentialRequestResult {
    private final Status mStatus;
    private final Credential zzal;

    public zzh(Status status, Credential credential) {
        this.mStatus = status;
        this.zzal = credential;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }

    @Override // com.google.android.gms.auth.api.credentials.CredentialRequestResult
    public final Credential getCredential() {
        return this.zzal;
    }

    public static zzh zzd(Status status) {
        return new zzh(status, null);
    }
}
