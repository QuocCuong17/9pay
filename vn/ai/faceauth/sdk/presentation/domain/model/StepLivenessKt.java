package vn.ai.faceauth.sdk.presentation.domain.model;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import vn.ai.faceauth.sdk.domain.model.StepLivenessDomain;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toDomain", "Lvn/ai/faceauth/sdk/domain/model/StepLivenessDomain;", "Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "trueface_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class StepLivenessKt {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StepLiveness.values().length];
            iArr[StepLiveness.STEP_ZOOM_IN.ordinal()] = 1;
            iArr[StepLiveness.STEP_FIRST_CHECK.ordinal()] = 2;
            iArr[StepLiveness.STEP_ZOOM_OUT.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final StepLivenessDomain toDomain(StepLiveness stepLiveness) {
        int i = WhenMappings.$EnumSwitchMapping$0[stepLiveness.ordinal()];
        if (i == 1 || i == 2) {
            return StepLivenessDomain.STEP_ZOOM_IN;
        }
        if (i == 3) {
            return StepLivenessDomain.STEP_ZOOM_OUT;
        }
        throw new NoWhenBranchMatchedException();
    }
}
