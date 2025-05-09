package com.google.android.gms.internal.p000authapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes3.dex */
public final class zzt implements Parcelable.Creator<zzs> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzs[] newArray(int i) {
        return new zzs[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzs createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Credential credential = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) == 1) {
                credential = (Credential) SafeParcelReader.createParcelable(parcel, readHeader, Credential.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzs(credential);
    }
}
