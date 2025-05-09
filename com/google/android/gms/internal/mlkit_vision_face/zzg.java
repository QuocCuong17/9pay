package com.google.android.gms.internal.mlkit_vision_face;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzn[] zznVarArr = null;
        zzd[] zzdVarArr = null;
        int i = 0;
        int i2 = 0;
        float f = Float.MAX_VALUE;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = -1.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    f4 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 4:
                    f5 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 5:
                    f6 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 6:
                    f7 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    f2 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 9:
                    zznVarArr = (zzn[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzn.CREATOR);
                    break;
                case 10:
                    f8 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 11:
                    f9 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 12:
                    f10 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 13:
                    zzdVarArr = (zzd[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzd.CREATOR);
                    break;
                case 14:
                    f3 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 15:
                    f11 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzf(i, i2, f4, f5, f6, f7, f, f2, f3, zznVarArr, f8, f9, f10, zzdVarArr, f11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzf[i];
    }
}
