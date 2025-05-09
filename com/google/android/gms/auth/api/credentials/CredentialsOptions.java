package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.auth.api.Auth;

/* loaded from: classes3.dex */
public final class CredentialsOptions extends Auth.AuthCredentialsOptions {
    public static final CredentialsOptions DEFAULT = (CredentialsOptions) new Builder().zzc();

    /* loaded from: classes3.dex */
    public static final class Builder extends Auth.AuthCredentialsOptions.Builder {
        @Override // com.google.android.gms.auth.api.Auth.AuthCredentialsOptions.Builder
        public final Builder forceEnableSaveDialog() {
            this.zzn = true;
            return this;
        }

        @Override // com.google.android.gms.auth.api.Auth.AuthCredentialsOptions.Builder
        /* renamed from: build, reason: merged with bridge method [inline-methods] */
        public final CredentialsOptions zzc() {
            return new CredentialsOptions(this);
        }
    }

    private CredentialsOptions(Builder builder) {
        super(builder);
    }
}
