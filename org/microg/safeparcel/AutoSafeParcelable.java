package org.microg.safeparcel;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Array;

/* loaded from: classes6.dex */
public abstract class AutoSafeParcelable implements SafeParcelable {
    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SafeParcelUtil.writeObject(this, parcel, i);
    }

    /* loaded from: classes6.dex */
    public static class AutoCreator<T extends SafeParcelable> implements Parcelable.Creator<T> {
        private final Class<T> tClass;

        public AutoCreator(Class<T> cls) {
            this.tClass = cls;
        }

        @Override // android.os.Parcelable.Creator
        public T createFromParcel(Parcel parcel) {
            return (T) SafeParcelUtil.createObject(this.tClass, parcel);
        }

        @Override // android.os.Parcelable.Creator
        public T[] newArray(int i) {
            return (T[]) ((SafeParcelable[]) Array.newInstance((Class<?>) this.tClass, i));
        }
    }
}
