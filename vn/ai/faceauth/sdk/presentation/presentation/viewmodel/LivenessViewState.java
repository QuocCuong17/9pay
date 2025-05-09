package vn.ai.faceauth.sdk.presentation.presentation.viewmodel;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;
import vn.ai.faceauth.sdk.core.viewmodel.UIState;
import vn.ai.faceauth.sdk.presentation.domain.model.StepLiveness;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0003J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0003J\u0016\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/presentation/viewmodel/LivenessViewState;", "Lvn/ai/faceauth/sdk/core/viewmodel/UIState;", "messageLiveness", "", "isCompleted", "", "stepLiveness", "Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "(Ljava/lang/String;ZLvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;)V", "()Z", "getMessageLiveness", "()Ljava/lang/String;", "getStepLiveness", "()Lvn/ai/faceauth/sdk/presentation/domain/model/StepLiveness;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "livenessError", "message", "livenessMessage", "stepsCompleted", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LivenessViewState implements UIState {
    private final boolean isCompleted;
    private final String messageLiveness;
    private final StepLiveness stepLiveness;

    public LivenessViewState() {
        this(null, false, null, 7, null);
    }

    public LivenessViewState(String str, boolean z, StepLiveness stepLiveness) {
        this.messageLiveness = str;
        this.isCompleted = z;
        this.stepLiveness = stepLiveness;
    }

    public /* synthetic */ LivenessViewState(String str, boolean z, StepLiveness stepLiveness, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? StepLiveness.STEP_FIRST_CHECK : stepLiveness);
    }

    public static /* synthetic */ LivenessViewState copy$default(LivenessViewState livenessViewState, String str, boolean z, StepLiveness stepLiveness, int i, Object obj) {
        if ((i & 1) != 0) {
            str = livenessViewState.messageLiveness;
        }
        if ((i & 2) != 0) {
            z = livenessViewState.isCompleted;
        }
        if ((i & 4) != 0) {
            stepLiveness = livenessViewState.stepLiveness;
        }
        return livenessViewState.copy(str, z, stepLiveness);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMessageLiveness() {
        return this.messageLiveness;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsCompleted() {
        return this.isCompleted;
    }

    /* renamed from: component3, reason: from getter */
    public final StepLiveness getStepLiveness() {
        return this.stepLiveness;
    }

    public final LivenessViewState copy(String messageLiveness, boolean isCompleted, StepLiveness stepLiveness) {
        return new LivenessViewState(messageLiveness, isCompleted, stepLiveness);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessViewState)) {
            return false;
        }
        LivenessViewState livenessViewState = (LivenessViewState) other;
        return Intrinsics.areEqual(this.messageLiveness, livenessViewState.messageLiveness) && this.isCompleted == livenessViewState.isCompleted && this.stepLiveness == livenessViewState.stepLiveness;
    }

    public final String getMessageLiveness() {
        return this.messageLiveness;
    }

    public final StepLiveness getStepLiveness() {
        return this.stepLiveness;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.messageLiveness.hashCode();
        boolean z = this.isCompleted;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        StepLiveness stepLiveness = this.stepLiveness;
        return (((hashCode * 31) + i) * 31) + (stepLiveness == null ? 0 : stepLiveness.hashCode());
    }

    public final boolean isCompleted() {
        return this.isCompleted;
    }

    public final LivenessViewState livenessError(String message) {
        return copy$default(this, message, false, null, 4, null);
    }

    public final LivenessViewState livenessMessage(String message) {
        return copy$default(this, message, false, null, 6, null);
    }

    public final LivenessViewState livenessMessage(String message, StepLiveness stepLiveness) {
        return copy$default(this, message, false, stepLiveness, 2, null);
    }

    public final LivenessViewState stepsCompleted(String message) {
        return copy$default(this, message, true, null, 4, null);
    }

    public String toString() {
        return tfwhgw.rnigpa(189) + this.messageLiveness + tfwhgw.rnigpa(190) + this.isCompleted + tfwhgw.rnigpa(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256) + this.stepLiveness + ')';
    }
}
