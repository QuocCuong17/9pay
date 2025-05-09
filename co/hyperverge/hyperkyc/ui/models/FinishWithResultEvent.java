package co.hyperverge.hyperkyc.ui.models;

import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FinishWithResultEvent.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0081\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003JP\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0007HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006$"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/FinishWithResultEvent;", "", "status", "", "data", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "errorCode", "", "errorMessage", "performReviewFinish", "", "performStatePush", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;Ljava/lang/Integer;Ljava/lang/String;ZZ)V", "getData", "()Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;", "getErrorCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getErrorMessage", "()Ljava/lang/String;", "getPerformReviewFinish", "()Z", "getPerformStatePush", "getStatus", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/result/HyperKycData;Ljava/lang/Integer;Ljava/lang/String;ZZ)Lco/hyperverge/hyperkyc/ui/models/FinishWithResultEvent;", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FinishWithResultEvent {
    private final HyperKycData data;
    private final Integer errorCode;
    private final String errorMessage;
    private final boolean performReviewFinish;
    private final boolean performStatePush;
    private final String status;

    public static /* synthetic */ FinishWithResultEvent copy$default(FinishWithResultEvent finishWithResultEvent, String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = finishWithResultEvent.status;
        }
        if ((i & 2) != 0) {
            hyperKycData = finishWithResultEvent.data;
        }
        HyperKycData hyperKycData2 = hyperKycData;
        if ((i & 4) != 0) {
            num = finishWithResultEvent.errorCode;
        }
        Integer num2 = num;
        if ((i & 8) != 0) {
            str2 = finishWithResultEvent.errorMessage;
        }
        String str3 = str2;
        if ((i & 16) != 0) {
            z = finishWithResultEvent.performReviewFinish;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = finishWithResultEvent.performStatePush;
        }
        return finishWithResultEvent.copy(str, hyperKycData2, num2, str3, z3, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component2, reason: from getter */
    public final HyperKycData getData() {
        return this.data;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getErrorCode() {
        return this.errorCode;
    }

    /* renamed from: component4, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getPerformReviewFinish() {
        return this.performReviewFinish;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getPerformStatePush() {
        return this.performStatePush;
    }

    public final FinishWithResultEvent copy(String status, HyperKycData data, Integer errorCode, String errorMessage, boolean performReviewFinish, boolean performStatePush) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new FinishWithResultEvent(status, data, errorCode, errorMessage, performReviewFinish, performStatePush);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FinishWithResultEvent)) {
            return false;
        }
        FinishWithResultEvent finishWithResultEvent = (FinishWithResultEvent) other;
        return Intrinsics.areEqual(this.status, finishWithResultEvent.status) && Intrinsics.areEqual(this.data, finishWithResultEvent.data) && Intrinsics.areEqual(this.errorCode, finishWithResultEvent.errorCode) && Intrinsics.areEqual(this.errorMessage, finishWithResultEvent.errorMessage) && this.performReviewFinish == finishWithResultEvent.performReviewFinish && this.performStatePush == finishWithResultEvent.performStatePush;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.status.hashCode() * 31;
        HyperKycData hyperKycData = this.data;
        int hashCode2 = (hashCode + (hyperKycData == null ? 0 : hyperKycData.hashCode())) * 31;
        Integer num = this.errorCode;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.errorMessage;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        boolean z = this.performReviewFinish;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode4 + i) * 31;
        boolean z2 = this.performStatePush;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "FinishWithResultEvent(status=" + this.status + ", data=" + this.data + ", errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + ", performReviewFinish=" + this.performReviewFinish + ", performStatePush=" + this.performStatePush + ')';
    }

    public FinishWithResultEvent(String status, HyperKycData hyperKycData, Integer num, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
        this.data = hyperKycData;
        this.errorCode = num;
        this.errorMessage = str;
        this.performReviewFinish = z;
        this.performStatePush = z2;
    }

    public /* synthetic */ FinishWithResultEvent(String str, HyperKycData hyperKycData, Integer num, String str2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : hyperKycData, (i & 4) != 0 ? null : num, (i & 8) == 0 ? str2 : null, (i & 16) != 0 ? true : z, (i & 32) == 0 ? z2 : true);
    }

    public final String getStatus() {
        return this.status;
    }

    public final HyperKycData getData() {
        return this.data;
    }

    public final Integer getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final boolean getPerformReviewFinish() {
        return this.performReviewFinish;
    }

    public final boolean getPerformStatePush() {
        return this.performStatePush;
    }
}
