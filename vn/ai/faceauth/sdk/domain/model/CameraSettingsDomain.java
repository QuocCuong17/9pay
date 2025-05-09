package vn.ai.faceauth.sdk.domain.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/CameraSettingsDomain;", "", "storageType", "Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "livenessStepList", "", "Lvn/ai/faceauth/sdk/domain/model/StepLivenessDomain;", "(Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;Ljava/util/List;)V", "getLivenessStepList", "()Ljava/util/List;", "getStorageType", "()Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class CameraSettingsDomain {
    private final List<StepLivenessDomain> livenessStepList;
    private final StorageTypeDomain storageType;

    /* JADX WARN: Multi-variable type inference failed */
    public CameraSettingsDomain(StorageTypeDomain storageTypeDomain, List<? extends StepLivenessDomain> list) {
        this.storageType = storageTypeDomain;
        this.livenessStepList = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CameraSettingsDomain copy$default(CameraSettingsDomain cameraSettingsDomain, StorageTypeDomain storageTypeDomain, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            storageTypeDomain = cameraSettingsDomain.storageType;
        }
        if ((i & 2) != 0) {
            list = cameraSettingsDomain.livenessStepList;
        }
        return cameraSettingsDomain.copy(storageTypeDomain, list);
    }

    /* renamed from: component1, reason: from getter */
    public final StorageTypeDomain getStorageType() {
        return this.storageType;
    }

    public final List<StepLivenessDomain> component2() {
        return this.livenessStepList;
    }

    public final CameraSettingsDomain copy(StorageTypeDomain storageType, List<? extends StepLivenessDomain> livenessStepList) {
        return new CameraSettingsDomain(storageType, livenessStepList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CameraSettingsDomain)) {
            return false;
        }
        CameraSettingsDomain cameraSettingsDomain = (CameraSettingsDomain) other;
        return this.storageType == cameraSettingsDomain.storageType && Intrinsics.areEqual(this.livenessStepList, cameraSettingsDomain.livenessStepList);
    }

    public final List<StepLivenessDomain> getLivenessStepList() {
        return this.livenessStepList;
    }

    public final StorageTypeDomain getStorageType() {
        return this.storageType;
    }

    public int hashCode() {
        return this.livenessStepList.hashCode() + (this.storageType.hashCode() * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_PRIMARY_CHROMATICITES) + this.storageType + tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_COLOR_MAP) + this.livenessStepList + ')';
    }
}
