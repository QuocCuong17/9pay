package vn.ai.faceauth.sdk.presentation.domain.configs;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;
import vn.ai.faceauth.sdk.presentation.domain.model.StorageType;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0011HÖ\u0001R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/configs/CameraSettings;", "Landroid/os/Parcelable;", "storageType", "Lvn/ai/faceauth/sdk/presentation/domain/model/StorageType;", "livenessStepList", "Ljava/util/ArrayList;", "Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "Lkotlin/collections/ArrayList;", "(Lvn/ai/faceauth/sdk/presentation/domain/model/StorageType;Ljava/util/ArrayList;)V", "getLivenessStepList", "()Ljava/util/ArrayList;", "getStorageType", "()Lvn/ai/faceauth/sdk/presentation/domain/model/StorageType;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class CameraSettings implements Parcelable {
    public static final Parcelable.Creator<CameraSettings> CREATOR = new Creator();
    private final ArrayList<StepLiveness> livenessStepList;
    private final StorageType storageType;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Creator implements Parcelable.Creator<CameraSettings> {
        @Override // android.os.Parcelable.Creator
        public final CameraSettings createFromParcel(Parcel parcel) {
            StorageType valueOf = StorageType.valueOf(parcel.readString());
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i = 0; i != readInt; i++) {
                arrayList.add(StepLiveness.CREATOR.createFromParcel(parcel));
            }
            return new CameraSettings(valueOf, arrayList);
        }

        @Override // android.os.Parcelable.Creator
        public final CameraSettings[] newArray(int i) {
            return new CameraSettings[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CameraSettings() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public CameraSettings(StorageType storageType, ArrayList<StepLiveness> arrayList) {
        this.storageType = storageType;
        this.livenessStepList = arrayList;
    }

    public /* synthetic */ CameraSettings(StorageType storageType, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? StorageType.INTERNAL : storageType, (i & 2) != 0 ? CollectionsKt.arrayListOf(StepLiveness.STEP_FIRST_CHECK, StepLiveness.STEP_ZOOM_IN, StepLiveness.STEP_ZOOM_OUT) : arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CameraSettings copy$default(CameraSettings cameraSettings, StorageType storageType, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            storageType = cameraSettings.storageType;
        }
        if ((i & 2) != 0) {
            arrayList = cameraSettings.livenessStepList;
        }
        return cameraSettings.copy(storageType, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final StorageType getStorageType() {
        return this.storageType;
    }

    public final ArrayList<StepLiveness> component2() {
        return this.livenessStepList;
    }

    public final CameraSettings copy(StorageType storageType, ArrayList<StepLiveness> livenessStepList) {
        return new CameraSettings(storageType, livenessStepList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CameraSettings)) {
            return false;
        }
        CameraSettings cameraSettings = (CameraSettings) other;
        return this.storageType == cameraSettings.storageType && Intrinsics.areEqual(this.livenessStepList, cameraSettings.livenessStepList);
    }

    public final ArrayList<StepLiveness> getLivenessStepList() {
        return this.livenessStepList;
    }

    public final StorageType getStorageType() {
        return this.storageType;
    }

    public int hashCode() {
        return this.livenessStepList.hashCode() + (this.storageType.hashCode() * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(22) + this.storageType + tfwhgw.rnigpa(23) + this.livenessStepList + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.storageType.name());
        ArrayList<StepLiveness> arrayList = this.livenessStepList;
        parcel.writeInt(arrayList.size());
        Iterator<StepLiveness> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, flags);
        }
    }
}
