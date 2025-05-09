package vn.ai.faceauth.sdk.presentation.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/Constants;", "", "()V", "Companion", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class Constants {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static float EYE_OPENED_PROBABILITY = 0.2f;
    private static float SMILE_PROBABILITY = 0.3f;
    private static float BLUR_PROBABILITY = 0.8f;
    private static float LUMINOSITY_PROBABILITY = 0.4f;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/Constants$Companion;", "", "()V", "BLUR_PROBABILITY", "", "getBLUR_PROBABILITY", "()F", "setBLUR_PROBABILITY", "(F)V", "EYE_OPENED_PROBABILITY", "getEYE_OPENED_PROBABILITY", "setEYE_OPENED_PROBABILITY", "LUMINOSITY_PROBABILITY", "getLUMINOSITY_PROBABILITY", "setLUMINOSITY_PROBABILITY", "SMILE_PROBABILITY", "getSMILE_PROBABILITY", "setSMILE_PROBABILITY", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float getBLUR_PROBABILITY() {
            return Constants.BLUR_PROBABILITY;
        }

        public final float getEYE_OPENED_PROBABILITY() {
            return Constants.EYE_OPENED_PROBABILITY;
        }

        public final float getLUMINOSITY_PROBABILITY() {
            return Constants.LUMINOSITY_PROBABILITY;
        }

        public final float getSMILE_PROBABILITY() {
            return Constants.SMILE_PROBABILITY;
        }

        public final void setBLUR_PROBABILITY(float f) {
            Constants.BLUR_PROBABILITY = f;
        }

        public final void setEYE_OPENED_PROBABILITY(float f) {
            Constants.EYE_OPENED_PROBABILITY = f;
        }

        public final void setLUMINOSITY_PROBABILITY(float f) {
            Constants.LUMINOSITY_PROBABILITY = f;
        }

        public final void setSMILE_PROBABILITY(float f) {
            Constants.SMILE_PROBABILITY = f;
        }
    }
}
