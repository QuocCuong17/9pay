package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes3.dex */
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CredentialPickerConfig> CREATOR = new zze();
    private final boolean mShowCancelButton;
    private final int zzu;
    private final boolean zzv;

    @Deprecated
    private final boolean zzw;
    private final int zzx;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CredentialPickerConfig(int i, boolean z, boolean z2, boolean z3, int i2) {
        this.zzu = i;
        this.zzv = z;
        this.mShowCancelButton = z2;
        if (i < 2) {
            this.zzw = z3;
            this.zzx = z3 ? 3 : 1;
        } else {
            this.zzw = i2 == 3;
            this.zzx = i2;
        }
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private boolean zzv = false;
        private boolean mShowCancelButton = true;
        private int zzy = 1;

        public Builder setShowAddAccountButton(boolean z) {
            this.zzv = z;
            return this;
        }

        public Builder setShowCancelButton(boolean z) {
            this.mShowCancelButton = z;
            return this;
        }

        public Builder setPrompt(int i) {
            this.zzy = i;
            return this;
        }

        @Deprecated
        public Builder setForNewAccount(boolean z) {
            this.zzy = z ? 3 : 1;
            return this;
        }

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this);
        }
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzv, builder.mShowCancelButton, false, builder.zzy);
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzv;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzx == 3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzx);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
