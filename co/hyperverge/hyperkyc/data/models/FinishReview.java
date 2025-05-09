package co.hyperverge.hyperkyc.data.models;

import co.hyperverge.hyperkyc.data.models.result.HyperKycData;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import io.flutter.plugins.firebase.database.Constants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FinishReview.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003¢\u0006\u0002\u0010\u0013J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u0015\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u0015\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\fHÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J§\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015¨\u00066"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/FinishReview;", "", "transactionId", "", "requestIds", "", "status", "appId", "userDetails", "", "debugInfo", "failureReason", "Lco/hyperverge/hyperkyc/data/models/FailureReason;", "journeyId", "workflowId", "flags", "Lco/hyperverge/hyperkyc/data/models/result/HyperKycData$ApiFlags;", "isKYCGateway", Constants.EVENT_TYPE, "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lco/hyperverge/hyperkyc/data/models/FailureReason;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "getDebugInfo", "()Ljava/util/Map;", "getEventType", "getFailureReason", "()Lco/hyperverge/hyperkyc/data/models/FailureReason;", "getFlags", "()Ljava/util/List;", "getJourneyId", "getRequestIds", "getStatus", "getTransactionId", "getUserDetails", "getWorkflowId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FinishReview {
    private final String appId;
    private final Map<String, String> debugInfo;
    private final String eventType;
    private final FailureReason failureReason;
    private final List<HyperKycData.ApiFlags> flags;
    private final String isKYCGateway;
    private final String journeyId;
    private final List<String> requestIds;
    private final String status;
    private final String transactionId;
    private final Map<String, String> userDetails;
    private final String workflowId;

    /* renamed from: component1, reason: from getter */
    public final String getTransactionId() {
        return this.transactionId;
    }

    public final List<HyperKycData.ApiFlags> component10() {
        return this.flags;
    }

    /* renamed from: component11, reason: from getter */
    public final String getIsKYCGateway() {
        return this.isKYCGateway;
    }

    /* renamed from: component12, reason: from getter */
    public final String getEventType() {
        return this.eventType;
    }

    public final List<String> component2() {
        return this.requestIds;
    }

    /* renamed from: component3, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAppId() {
        return this.appId;
    }

    public final Map<String, String> component5() {
        return this.userDetails;
    }

    public final Map<String, String> component6() {
        return this.debugInfo;
    }

    /* renamed from: component7, reason: from getter */
    public final FailureReason getFailureReason() {
        return this.failureReason;
    }

    /* renamed from: component8, reason: from getter */
    public final String getJourneyId() {
        return this.journeyId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getWorkflowId() {
        return this.workflowId;
    }

    public final FinishReview copy(String transactionId, List<String> requestIds, String status, String appId, Map<String, String> userDetails, Map<String, String> debugInfo, FailureReason failureReason, String journeyId, String workflowId, List<HyperKycData.ApiFlags> flags, String isKYCGateway, String eventType) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(requestIds, "requestIds");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(userDetails, "userDetails");
        Intrinsics.checkNotNullParameter(debugInfo, "debugInfo");
        Intrinsics.checkNotNullParameter(journeyId, "journeyId");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(flags, "flags");
        Intrinsics.checkNotNullParameter(isKYCGateway, "isKYCGateway");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        return new FinishReview(transactionId, requestIds, status, appId, userDetails, debugInfo, failureReason, journeyId, workflowId, flags, isKYCGateway, eventType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FinishReview)) {
            return false;
        }
        FinishReview finishReview = (FinishReview) other;
        return Intrinsics.areEqual(this.transactionId, finishReview.transactionId) && Intrinsics.areEqual(this.requestIds, finishReview.requestIds) && Intrinsics.areEqual(this.status, finishReview.status) && Intrinsics.areEqual(this.appId, finishReview.appId) && Intrinsics.areEqual(this.userDetails, finishReview.userDetails) && Intrinsics.areEqual(this.debugInfo, finishReview.debugInfo) && Intrinsics.areEqual(this.failureReason, finishReview.failureReason) && Intrinsics.areEqual(this.journeyId, finishReview.journeyId) && Intrinsics.areEqual(this.workflowId, finishReview.workflowId) && Intrinsics.areEqual(this.flags, finishReview.flags) && Intrinsics.areEqual(this.isKYCGateway, finishReview.isKYCGateway) && Intrinsics.areEqual(this.eventType, finishReview.eventType);
    }

    public int hashCode() {
        int hashCode = ((((((((((this.transactionId.hashCode() * 31) + this.requestIds.hashCode()) * 31) + this.status.hashCode()) * 31) + this.appId.hashCode()) * 31) + this.userDetails.hashCode()) * 31) + this.debugInfo.hashCode()) * 31;
        FailureReason failureReason = this.failureReason;
        return ((((((((((hashCode + (failureReason == null ? 0 : failureReason.hashCode())) * 31) + this.journeyId.hashCode()) * 31) + this.workflowId.hashCode()) * 31) + this.flags.hashCode()) * 31) + this.isKYCGateway.hashCode()) * 31) + this.eventType.hashCode();
    }

    public String toString() {
        return "FinishReview(transactionId=" + this.transactionId + ", requestIds=" + this.requestIds + ", status=" + this.status + ", appId=" + this.appId + ", userDetails=" + this.userDetails + ", debugInfo=" + this.debugInfo + ", failureReason=" + this.failureReason + ", journeyId=" + this.journeyId + ", workflowId=" + this.workflowId + ", flags=" + this.flags + ", isKYCGateway=" + this.isKYCGateway + ", eventType=" + this.eventType + ')';
    }

    public FinishReview(String transactionId, List<String> requestIds, String status, String appId, Map<String, String> userDetails, Map<String, String> debugInfo, FailureReason failureReason, String journeyId, String workflowId, List<HyperKycData.ApiFlags> flags, String isKYCGateway, String eventType) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(requestIds, "requestIds");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(userDetails, "userDetails");
        Intrinsics.checkNotNullParameter(debugInfo, "debugInfo");
        Intrinsics.checkNotNullParameter(journeyId, "journeyId");
        Intrinsics.checkNotNullParameter(workflowId, "workflowId");
        Intrinsics.checkNotNullParameter(flags, "flags");
        Intrinsics.checkNotNullParameter(isKYCGateway, "isKYCGateway");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.transactionId = transactionId;
        this.requestIds = requestIds;
        this.status = status;
        this.appId = appId;
        this.userDetails = userDetails;
        this.debugInfo = debugInfo;
        this.failureReason = failureReason;
        this.journeyId = journeyId;
        this.workflowId = workflowId;
        this.flags = flags;
        this.isKYCGateway = isKYCGateway;
        this.eventType = eventType;
    }

    public /* synthetic */ FinishReview(String str, List list, String str2, String str3, Map map, Map map2, FailureReason failureReason, String str4, String str5, List list2, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, str2, str3, map, map2, failureReason, str4, str5, list2, (i & 1024) != 0 ? "no" : str6, (i & 2048) != 0 ? "FINISH_TRANSACTION_WEBHOOK" : str7);
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    public final List<String> getRequestIds() {
        return this.requestIds;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getAppId() {
        return this.appId;
    }

    public final Map<String, String> getUserDetails() {
        return this.userDetails;
    }

    public final Map<String, String> getDebugInfo() {
        return this.debugInfo;
    }

    public final FailureReason getFailureReason() {
        return this.failureReason;
    }

    public final String getJourneyId() {
        return this.journeyId;
    }

    public final String getWorkflowId() {
        return this.workflowId;
    }

    public final List<HyperKycData.ApiFlags> getFlags() {
        return this.flags;
    }

    public final String isKYCGateway() {
        return this.isKYCGateway;
    }

    public final String getEventType() {
        return this.eventType;
    }
}
