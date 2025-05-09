package vn.ai.faceauth.sdk.presentation.domain.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessCameraXResult;", "", "rawImage", "", "successLivenessResponse", "Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessResponse;", "error", "Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessCameraXError;", "(Ljava/lang/String;Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessResponse;Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessCameraXError;)V", "getError", "()Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessCameraXError;", "getRawImage", "()Ljava/lang/String;", "getSuccessLivenessResponse", "()Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessResponse;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LivenessCameraXResult {
    private final LivenessCameraXError error;
    private final String rawImage;
    private final LivenessResponse successLivenessResponse;

    public LivenessCameraXResult() {
        this(null, null, null, 7, null);
    }

    public LivenessCameraXResult(String str, LivenessResponse livenessResponse, LivenessCameraXError livenessCameraXError) {
        this.rawImage = str;
        this.successLivenessResponse = livenessResponse;
        this.error = livenessCameraXError;
    }

    public /* synthetic */ LivenessCameraXResult(String str, LivenessResponse livenessResponse, LivenessCameraXError livenessCameraXError, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? null : livenessResponse, (i & 4) != 0 ? null : livenessCameraXError);
    }

    public static /* synthetic */ LivenessCameraXResult copy$default(LivenessCameraXResult livenessCameraXResult, String str, LivenessResponse livenessResponse, LivenessCameraXError livenessCameraXError, int i, Object obj) {
        if ((i & 1) != 0) {
            str = livenessCameraXResult.rawImage;
        }
        if ((i & 2) != 0) {
            livenessResponse = livenessCameraXResult.successLivenessResponse;
        }
        if ((i & 4) != 0) {
            livenessCameraXError = livenessCameraXResult.error;
        }
        return livenessCameraXResult.copy(str, livenessResponse, livenessCameraXError);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRawImage() {
        return this.rawImage;
    }

    /* renamed from: component2, reason: from getter */
    public final LivenessResponse getSuccessLivenessResponse() {
        return this.successLivenessResponse;
    }

    /* renamed from: component3, reason: from getter */
    public final LivenessCameraXError getError() {
        return this.error;
    }

    public final LivenessCameraXResult copy(String rawImage, LivenessResponse successLivenessResponse, LivenessCameraXError error) {
        return new LivenessCameraXResult(rawImage, successLivenessResponse, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessCameraXResult)) {
            return false;
        }
        LivenessCameraXResult livenessCameraXResult = (LivenessCameraXResult) other;
        return Intrinsics.areEqual(this.rawImage, livenessCameraXResult.rawImage) && Intrinsics.areEqual(this.successLivenessResponse, livenessCameraXResult.successLivenessResponse) && Intrinsics.areEqual(this.error, livenessCameraXResult.error);
    }

    public final LivenessCameraXError getError() {
        return this.error;
    }

    public final String getRawImage() {
        return this.rawImage;
    }

    public final LivenessResponse getSuccessLivenessResponse() {
        return this.successLivenessResponse;
    }

    public int hashCode() {
        int hashCode = this.rawImage.hashCode();
        LivenessResponse livenessResponse = this.successLivenessResponse;
        int hashCode2 = livenessResponse == null ? 0 : livenessResponse.hashCode();
        LivenessCameraXError livenessCameraXError = this.error;
        return (((hashCode * 31) + hashCode2) * 31) + (livenessCameraXError != null ? livenessCameraXError.hashCode() : 0);
    }

    public String toString() {
        return tfwhgw.rnigpa(368) + this.rawImage + tfwhgw.rnigpa(369) + this.successLivenessResponse + tfwhgw.rnigpa(370) + this.error + ')';
    }
}
