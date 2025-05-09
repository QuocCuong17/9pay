package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes3.dex */
public final class HintRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<HintRequest> CREATOR = new zzj();
    private final String[] zzaa;
    private final boolean zzad;
    private final String zzae;
    private final String zzaf;
    private final CredentialPickerConfig zzah;
    private final boolean zzai;
    private final boolean zzaj;
    private final int zzu;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HintRequest(int i, CredentialPickerConfig credentialPickerConfig, boolean z, boolean z2, String[] strArr, boolean z3, String str, String str2) {
        this.zzu = i;
        this.zzah = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
        this.zzai = z;
        this.zzaj = z2;
        this.zzaa = (String[]) Preconditions.checkNotNull(strArr);
        if (i < 2) {
            this.zzad = true;
            this.zzae = null;
            this.zzaf = null;
        } else {
            this.zzad = z3;
            this.zzae = str;
            this.zzaf = str2;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private String[] zzaa;
        private String zzae;
        private String zzaf;
        private boolean zzai;
        private boolean zzaj;
        private CredentialPickerConfig zzah = new CredentialPickerConfig.Builder().build();
        private boolean zzad = false;

        public final Builder setEmailAddressIdentifierSupported(boolean z) {
            this.zzai = z;
            return this;
        }

        public final Builder setPhoneNumberIdentifierSupported(boolean z) {
            this.zzaj = z;
            return this;
        }

        public final Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzaa = strArr;
            return this;
        }

        public final Builder setHintPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzah = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
            return this;
        }

        public final Builder setIdTokenRequested(boolean z) {
            this.zzad = z;
            return this;
        }

        public final Builder setServerClientId(String str) {
            this.zzae = str;
            return this;
        }

        public final Builder setIdTokenNonce(String str) {
            this.zzaf = str;
            return this;
        }

        public final HintRequest build() {
            if (this.zzaa == null) {
                this.zzaa = new String[0];
            }
            if (!this.zzai && !this.zzaj && this.zzaa.length == 0) {
                throw new IllegalStateException("At least one authentication method must be specified");
            }
            return new HintRequest(this);
        }
    }

    private HintRequest(Builder builder) {
        this(2, builder.zzah, builder.zzai, builder.zzaj, builder.zzaa, builder.zzad, builder.zzae, builder.zzaf);
    }

    public final CredentialPickerConfig getHintPickerConfig() {
        return this.zzah;
    }

    public final boolean isEmailAddressIdentifierSupported() {
        return this.zzai;
    }

    public final String[] getAccountTypes() {
        return this.zzaa;
    }

    public final boolean isIdTokenRequested() {
        return this.zzad;
    }

    public final String getServerClientId() {
        return this.zzae;
    }

    public final String getIdTokenNonce() {
        return this.zzaf;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, isEmailAddressIdentifierSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzaj);
        SafeParcelWriter.writeStringArray(parcel, 4, getAccountTypes(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
