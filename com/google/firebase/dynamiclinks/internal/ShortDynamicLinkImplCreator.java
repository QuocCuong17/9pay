package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.dynamiclinks.internal.ShortDynamicLinkImpl;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ShortDynamicLinkImplCreator implements Parcelable.Creator<ShortDynamicLinkImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ShortDynamicLinkImpl createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Uri uri = null;
        Uri uri2 = null;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
            } else if (fieldId == 2) {
                uri2 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
            } else if (fieldId == 3) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, ShortDynamicLinkImpl.WarningImpl.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ShortDynamicLinkImpl(uri, uri2, arrayList);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ShortDynamicLinkImpl[] newArray(int i) {
        return new ShortDynamicLinkImpl[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(ShortDynamicLinkImpl shortDynamicLinkImpl, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, shortDynamicLinkImpl.getShortLink(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, shortDynamicLinkImpl.getPreviewLink(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, shortDynamicLinkImpl.getWarnings(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
