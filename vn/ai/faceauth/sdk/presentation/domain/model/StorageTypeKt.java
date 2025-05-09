package vn.ai.faceauth.sdk.presentation.domain.model;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import vn.ai.faceauth.sdk.domain.model.StorageTypeDomain;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toDomain", "Lvn/ai/faceauth/sdk/domain/model/StorageTypeDomain;", "Lvn/ai/faceauth/sdk/presentation/domain/model/StorageType;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class StorageTypeKt {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StorageType.values().length];
            iArr[StorageType.INTERNAL.ordinal()] = 1;
            iArr[StorageType.EXTERNAL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final StorageTypeDomain toDomain(StorageType storageType) {
        int i = WhenMappings.$EnumSwitchMapping$0[storageType.ordinal()];
        if (i == 1) {
            return StorageTypeDomain.INTERNAL;
        }
        if (i == 2) {
            return StorageTypeDomain.EXTERNAL;
        }
        throw new NoWhenBranchMatchedException();
    }
}
