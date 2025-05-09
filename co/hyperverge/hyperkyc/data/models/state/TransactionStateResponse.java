package co.hyperverge.hyperkyc.data.models.state;

import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.tekartik.sqflite.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TransactionStateResponse.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lco/hyperverge/hyperkyc/data/models/state/TransactionStateResponse;", "", Keys.STATUS_CODE, "", "status", "", "errorCode", Constant.PARAM_RESULT, "Lco/hyperverge/hyperkyc/data/models/state/TransactionState;", "(ILjava/lang/String;Ljava/lang/String;Lco/hyperverge/hyperkyc/data/models/state/TransactionState;)V", "getErrorCode", "()Ljava/lang/String;", "getResult", "()Lco/hyperverge/hyperkyc/data/models/state/TransactionState;", "getStatus", "getStatusCode", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class TransactionStateResponse {
    private final String errorCode;
    private final TransactionState result;
    private final String status;
    private final int statusCode;

    public static /* synthetic */ TransactionStateResponse copy$default(TransactionStateResponse transactionStateResponse, int i, String str, String str2, TransactionState transactionState, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = transactionStateResponse.statusCode;
        }
        if ((i2 & 2) != 0) {
            str = transactionStateResponse.status;
        }
        if ((i2 & 4) != 0) {
            str2 = transactionStateResponse.errorCode;
        }
        if ((i2 & 8) != 0) {
            transactionState = transactionStateResponse.result;
        }
        return transactionStateResponse.copy(i, str, str2, transactionState);
    }

    /* renamed from: component1, reason: from getter */
    public final int getStatusCode() {
        return this.statusCode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getErrorCode() {
        return this.errorCode;
    }

    /* renamed from: component4, reason: from getter */
    public final TransactionState getResult() {
        return this.result;
    }

    public final TransactionStateResponse copy(int statusCode, String status, String errorCode, TransactionState result) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new TransactionStateResponse(statusCode, status, errorCode, result);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransactionStateResponse)) {
            return false;
        }
        TransactionStateResponse transactionStateResponse = (TransactionStateResponse) other;
        return this.statusCode == transactionStateResponse.statusCode && Intrinsics.areEqual(this.status, transactionStateResponse.status) && Intrinsics.areEqual(this.errorCode, transactionStateResponse.errorCode) && Intrinsics.areEqual(this.result, transactionStateResponse.result);
    }

    public int hashCode() {
        int hashCode = ((this.statusCode * 31) + this.status.hashCode()) * 31;
        String str = this.errorCode;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        TransactionState transactionState = this.result;
        return hashCode2 + (transactionState != null ? transactionState.hashCode() : 0);
    }

    public String toString() {
        return "TransactionStateResponse(statusCode=" + this.statusCode + ", status=" + this.status + ", errorCode=" + this.errorCode + ", result=" + this.result + ')';
    }

    public TransactionStateResponse(int i, String status, String str, TransactionState transactionState) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.statusCode = i;
        this.status = status;
        this.errorCode = str;
        this.result = transactionState;
    }

    public /* synthetic */ TransactionStateResponse(int i, String str, String str2, TransactionState transactionState, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : transactionState);
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final TransactionState getResult() {
        return this.result;
    }
}
