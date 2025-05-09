package vn.ai.faceauth.sdk.presentation.domain.usecase;

import kotlin.Metadata;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.core.resourceprovider.ResourcesProvider;
import vn.ai.faceauth.sdk.domain.model.StateFaceWithOval;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/usecase/GetStatusMessageUseCase;", "", "resourcesProvider", "Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;", "(Lvn/ai/faceauth/sdk/core/resourceprovider/ResourcesProvider;)V", "invoke", "", "stateFaceWithOval", "Lvn/ai/faceauth/sdk/domain/model/StateFaceWithOval;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GetStatusMessageUseCase {
    private final ResourcesProvider resourcesProvider;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StateFaceWithOval.values().length];
            iArr[StateFaceWithOval.WITHIN.ordinal()] = 1;
            iArr[StateFaceWithOval.MOVE_AWAY.ordinal()] = 2;
            iArr[StateFaceWithOval.MOVE_CLOSER.ordinal()] = 3;
            iArr[StateFaceWithOval.NO_SMILE.ordinal()] = 4;
            iArr[StateFaceWithOval.FRAME_YOUR_FACE_IN_OVAL.ordinal()] = 5;
            iArr[StateFaceWithOval.EYES_CLOSED.ordinal()] = 6;
            iArr[StateFaceWithOval.KEEP_HOLD_ON.ordinal()] = 7;
            iArr[StateFaceWithOval.FACE_NOT_ALIGN.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public GetStatusMessageUseCase(ResourcesProvider resourcesProvider) {
        this.resourcesProvider = resourcesProvider;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    public final String invoke(StateFaceWithOval stateFaceWithOval) {
        ResourcesProvider resourcesProvider;
        int i;
        switch (WhenMappings.$EnumSwitchMapping$0[stateFaceWithOval.ordinal()]) {
            case 1:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_holdon_processing;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 2:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_frame_face_away;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 3:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_frame_face_closer;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 4:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_expression_no_smiling;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 5:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_frame_face_inside;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 6:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_eye_closed;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 7:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_keep_holdon_processing;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            case 8:
                resourcesProvider = this.resourcesProvider;
                i = R.string.facesdk_expression_too_tilted;
                return ResourcesProvider.DefaultImpls.getString$default(resourcesProvider, i, null, 2, null);
            default:
                return String.valueOf(stateFaceWithOval);
        }
    }
}
