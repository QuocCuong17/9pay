package com.google.android.gms.internal.mlkit_vision_face;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzox implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Rect rect = null;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    rect = (Rect) SafeParcelReader.createParcelable(parcel, readHeader, Rect.CREATOR);
                    break;
                case 3:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 4:
                    f2 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 5:
                    f3 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 6:
                    f4 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 7:
                    f5 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    f6 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 9:
                    f7 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, zzpc.CREATOR);
                    break;
                case 11:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, zzos.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzow(i, rect, f, f2, f3, f4, f5, f6, f7, arrayList, arrayList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzow[i];
    }
}
