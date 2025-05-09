package vn.ai.faceauth.sdk.presentation.domain.configs;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import vn.ai.faceauth.sdk.domain.model.CameraSettingsDomain;
import vn.ai.faceauth.sdk.domain.model.StorageTypeDomain;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLivenessKt;
import vn.ai.faceauth.sdk.presentation.domain.model.StorageTypeKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toDomain", "Lvn/ai/faceauth/sdk/domain/model/CameraSettingsDomain;", "Lvn/ai/faceauth/sdk/presentation/domain/configs/CameraSettings;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CameraSettingsKt {
    public static final CameraSettingsDomain toDomain(CameraSettings cameraSettings) {
        StorageTypeDomain domain = StorageTypeKt.toDomain(cameraSettings.getStorageType());
        ArrayList<StepLiveness> livenessStepList = cameraSettings.getLivenessStepList();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(livenessStepList, 10));
        Iterator<T> it = livenessStepList.iterator();
        while (it.hasNext()) {
            arrayList.add(StepLivenessKt.toDomain((StepLiveness) it.next()));
        }
        return new CameraSettingsDomain(domain, arrayList);
    }
}
