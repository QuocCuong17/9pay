package co.hyperverge.hyperkyc.data.models.result;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HyperKycStatus.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0010R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048@X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/result/HyperKycStatus;", "", "()V", "ALL_STATUSES_LIST", "", "", "getALL_STATUSES_LIST$hyperkyc_release", "()Ljava/util/List;", "AUTO_APPROVED", "AUTO_DECLINED", "ERROR", "NEEDS_REVIEW", "STARTED", "USER_CANCELLED", "fromLegacyStatus", "legacyStatus", "fromLegacyStatus$hyperkyc_release", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HyperKycStatus {
    public static final String ERROR = "error";
    public static final String STARTED = "started";
    public static final HyperKycStatus INSTANCE = new HyperKycStatus();
    public static final String AUTO_APPROVED = "auto_approved";
    public static final String AUTO_DECLINED = "auto_declined";
    public static final String NEEDS_REVIEW = "needs_review";
    public static final String USER_CANCELLED = "user_cancelled";
    private static final List<String> ALL_STATUSES_LIST = CollectionsKt.listOf((Object[]) new String[]{"started", AUTO_APPROVED, AUTO_DECLINED, NEEDS_REVIEW, USER_CANCELLED, "error"});

    private HyperKycStatus() {
    }

    public final /* synthetic */ String fromLegacyStatus$hyperkyc_release(String legacyStatus) {
        Intrinsics.checkNotNullParameter(legacyStatus, "legacyStatus");
        int hashCode = legacyStatus.hashCode();
        return hashCode != -1421386050 ? hashCode != -793050291 ? (hashCode == 1542349558 && legacyStatus.equals(WorkflowModule.END_STATE_DECLINE)) ? AUTO_DECLINED : legacyStatus : !legacyStatus.equals(WorkflowModule.END_STATE_APPROVE) ? legacyStatus : AUTO_APPROVED : !legacyStatus.equals("manualReview") ? legacyStatus : NEEDS_REVIEW;
    }

    public final /* synthetic */ List getALL_STATUSES_LIST$hyperkyc_release() {
        return ALL_STATUSES_LIST;
    }
}
