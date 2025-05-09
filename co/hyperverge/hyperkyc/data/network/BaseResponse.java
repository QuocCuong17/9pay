package co.hyperverge.hyperkyc.data.network;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.gson.annotations.JsonAdapter;
import com.tekartik.sqflite.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: responses.kt */
@kotlin.Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002)*B3\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\nJ\b\u0010\u0012\u001a\u0004\u0018\u00010\bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003JE\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\b\u0010\u001e\u001a\u0004\u0018\u00010\bJ\t\u0010\u001f\u001a\u00020\u0019HÖ\u0001J\u0014\u0010 \u001a\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\"J\t\u0010#\u001a\u00020\bHÖ\u0001J\u0019\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006+"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/BaseResponse;", "T", "Landroid/os/Parcelable;", TtmlNode.TAG_METADATA, "Lco/hyperverge/hyperkyc/data/network/BaseResponse$Metadata;", Constant.PARAM_RESULT, "Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result;", "status", "", Keys.STATUS_CODE, "(Lco/hyperverge/hyperkyc/data/network/BaseResponse$Metadata;Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result;Ljava/lang/String;Ljava/lang/String;)V", "getMetadata", "()Lco/hyperverge/hyperkyc/data/network/BaseResponse$Metadata;", "getResult", "()Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result;", "getStatus", "()Ljava/lang/String;", "getStatusCode", "action", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "errorMessage", "hashCode", "isSuccess", "allowedStatusCodes", "", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Metadata", "Result", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BaseResponse<T extends Parcelable> implements Parcelable {
    public static final Parcelable.Creator<BaseResponse<?>> CREATOR = new Creator();
    private final Metadata metadata;
    private final Result<T> result;
    private final String status;
    private final String statusCode;

    /* compiled from: responses.kt */
    @kotlin.Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Creator implements Parcelable.Creator<BaseResponse<?>> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final BaseResponse<?> createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new BaseResponse<>(parcel.readInt() == 0 ? null : Metadata.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? Result.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final BaseResponse<?>[] newArray(int i) {
            return new BaseResponse[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BaseResponse copy$default(BaseResponse baseResponse, Metadata metadata, Result result, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            metadata = baseResponse.metadata;
        }
        if ((i & 2) != 0) {
            result = baseResponse.result;
        }
        if ((i & 4) != 0) {
            str = baseResponse.status;
        }
        if ((i & 8) != 0) {
            str2 = baseResponse.statusCode;
        }
        return baseResponse.copy(metadata, result, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final Metadata getMetadata() {
        return this.metadata;
    }

    public final Result<T> component2() {
        return this.result;
    }

    /* renamed from: component3, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component4, reason: from getter */
    public final String getStatusCode() {
        return this.statusCode;
    }

    public final BaseResponse<T> copy(Metadata metadata, Result<T> result, String status, String statusCode) {
        return new BaseResponse<>(metadata, result, status, statusCode);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseResponse)) {
            return false;
        }
        BaseResponse baseResponse = (BaseResponse) other;
        return Intrinsics.areEqual(this.metadata, baseResponse.metadata) && Intrinsics.areEqual(this.result, baseResponse.result) && Intrinsics.areEqual(this.status, baseResponse.status) && Intrinsics.areEqual(this.statusCode, baseResponse.statusCode);
    }

    public int hashCode() {
        Metadata metadata = this.metadata;
        int hashCode = (metadata == null ? 0 : metadata.hashCode()) * 31;
        Result<T> result = this.result;
        int hashCode2 = (hashCode + (result == null ? 0 : result.hashCode())) * 31;
        String str = this.status;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.statusCode;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "BaseResponse(metadata=" + this.metadata + ", result=" + this.result + ", status=" + this.status + ", statusCode=" + this.statusCode + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        Metadata metadata = this.metadata;
        if (metadata == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            metadata.writeToParcel(parcel, flags);
        }
        Result<T> result = this.result;
        if (result == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            result.writeToParcel(parcel, flags);
        }
        parcel.writeString(this.status);
        parcel.writeString(this.statusCode);
    }

    public BaseResponse(Metadata metadata, Result<T> result, String str, String str2) {
        this.metadata = metadata;
        this.result = result;
        this.status = str;
        this.statusCode = str2;
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public final Result<T> getResult() {
        return this.result;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getStatusCode() {
        return this.statusCode;
    }

    public final String action() {
        Result.Summary summary;
        Result<T> result = this.result;
        if (result == null || (summary = result.getSummary()) == null) {
            return null;
        }
        return summary.getAction();
    }

    public final boolean isSuccess(List<Integer> allowedStatusCodes) {
        Intrinsics.checkNotNullParameter(allowedStatusCodes, "allowedStatusCodes");
        if (action() == null) {
            List<Integer> list = allowedStatusCodes;
            String str = this.statusCode;
            if (!CollectionsKt.contains(list, str != null ? Integer.valueOf(Integer.parseInt(str)) : null)) {
                return false;
            }
        }
        return true;
    }

    public final String errorMessage() {
        Result.Summary summary;
        List<Result.Summary.Detail> details;
        Result.Summary.Detail detail;
        String error;
        Result<T> result = this.result;
        if (result != null && (error = result.getError()) != null) {
            return error;
        }
        Result<T> result2 = this.result;
        if (result2 == null || (summary = result2.getSummary()) == null || (details = summary.getDetails()) == null || (detail = (Result.Summary.Detail) CollectionsKt.firstOrNull((List) details)) == null) {
            return null;
        }
        return detail.getMessage();
    }

    /* compiled from: responses.kt */
    @kotlin.Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000bJJ\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\t\u0010\u0019\u001a\u00020\bHÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\bHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\bHÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006%"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/BaseResponse$Metadata;", "Landroid/os/Parcelable;", "data", "", "customerId", "requestId", "transactionId", "attemptCount", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAttemptCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCustomerId", "()Ljava/lang/String;", "getData", "getRequestId", "getTransactionId", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lco/hyperverge/hyperkyc/data/network/BaseResponse$Metadata;", "describeContents", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Metadata implements Parcelable {
        public static final Parcelable.Creator<Metadata> CREATOR = new Creator();
        private final Integer attemptCount;
        private final String customerId;
        private final String data;
        private final String requestId;
        private final String transactionId;

        /* compiled from: responses.kt */
        @kotlin.Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<Metadata> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Metadata createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Metadata(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Metadata[] newArray(int i) {
                return new Metadata[i];
            }
        }

        public static /* synthetic */ Metadata copy$default(Metadata metadata, String str, String str2, String str3, String str4, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                str = metadata.data;
            }
            if ((i & 2) != 0) {
                str2 = metadata.customerId;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                str3 = metadata.requestId;
            }
            String str6 = str3;
            if ((i & 8) != 0) {
                str4 = metadata.transactionId;
            }
            String str7 = str4;
            if ((i & 16) != 0) {
                num = metadata.attemptCount;
            }
            return metadata.copy(str, str5, str6, str7, num);
        }

        /* renamed from: component1, reason: from getter */
        public final String getData() {
            return this.data;
        }

        /* renamed from: component2, reason: from getter */
        public final String getCustomerId() {
            return this.customerId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getRequestId() {
            return this.requestId;
        }

        /* renamed from: component4, reason: from getter */
        public final String getTransactionId() {
            return this.transactionId;
        }

        /* renamed from: component5, reason: from getter */
        public final Integer getAttemptCount() {
            return this.attemptCount;
        }

        public final Metadata copy(String data, String customerId, String requestId, String transactionId, Integer attemptCount) {
            return new Metadata(data, customerId, requestId, transactionId, attemptCount);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Metadata)) {
                return false;
            }
            Metadata metadata = (Metadata) other;
            return Intrinsics.areEqual(this.data, metadata.data) && Intrinsics.areEqual(this.customerId, metadata.customerId) && Intrinsics.areEqual(this.requestId, metadata.requestId) && Intrinsics.areEqual(this.transactionId, metadata.transactionId) && Intrinsics.areEqual(this.attemptCount, metadata.attemptCount);
        }

        public int hashCode() {
            String str = this.data;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.customerId;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.requestId;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.transactionId;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num = this.attemptCount;
            return hashCode4 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "Metadata(data=" + this.data + ", customerId=" + this.customerId + ", requestId=" + this.requestId + ", transactionId=" + this.transactionId + ", attemptCount=" + this.attemptCount + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            int intValue;
            Intrinsics.checkNotNullParameter(parcel, "out");
            parcel.writeString(this.data);
            parcel.writeString(this.customerId);
            parcel.writeString(this.requestId);
            parcel.writeString(this.transactionId);
            Integer num = this.attemptCount;
            if (num == null) {
                intValue = 0;
            } else {
                parcel.writeInt(1);
                intValue = num.intValue();
            }
            parcel.writeInt(intValue);
        }

        public Metadata(String str, String str2, String str3, String str4, Integer num) {
            this.data = str;
            this.customerId = str2;
            this.requestId = str3;
            this.transactionId = str4;
            this.attemptCount = num;
        }

        public final String getData() {
            return this.data;
        }

        public final String getCustomerId() {
            return this.customerId;
        }

        public final String getRequestId() {
            return this.requestId;
        }

        public final String getTransactionId() {
            return this.transactionId;
        }

        public final Integer getAttemptCount() {
            return this.attemptCount;
        }
    }

    /* compiled from: responses.kt */
    @kotlin.Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001!B)\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J9\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015HÖ\u0001R\u001e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result;", "T", "Landroid/os/Parcelable;", "details", "", "summary", "Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result$Summary;", "error", "", "(Ljava/util/List;Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result$Summary;Ljava/lang/String;)V", "getDetails", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "getSummary", "()Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result$Summary;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Summary", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Result<T extends Parcelable> implements Parcelable {
        public static final Parcelable.Creator<Result<?>> CREATOR = new Creator();

        @JsonAdapter(AlwaysListTypeAdapterFactory.class)
        private final List<T> details;
        private final String error;
        private final Summary summary;

        /* compiled from: responses.kt */
        @kotlin.Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Creator implements Parcelable.Creator<Result<?>> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Result<?> createFromParcel(Parcel parcel) {
                ArrayList arrayList;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                if (parcel.readInt() == 0) {
                    arrayList = null;
                } else {
                    int readInt = parcel.readInt();
                    arrayList = new ArrayList(readInt);
                    for (int i = 0; i != readInt; i++) {
                        arrayList.add(parcel.readParcelable(Result.class.getClassLoader()));
                    }
                }
                return new Result<>(arrayList, parcel.readInt() != 0 ? Summary.CREATOR.createFromParcel(parcel) : null, parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final Result<?>[] newArray(int i) {
                return new Result[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Result copy$default(Result result, List list, Summary summary, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = result.details;
            }
            if ((i & 2) != 0) {
                summary = result.summary;
            }
            if ((i & 4) != 0) {
                str = result.error;
            }
            return result.copy(list, summary, str);
        }

        public final List<T> component1() {
            return this.details;
        }

        /* renamed from: component2, reason: from getter */
        public final Summary getSummary() {
            return this.summary;
        }

        /* renamed from: component3, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final Result<T> copy(List<? extends T> details, Summary summary, String error) {
            return new Result<>(details, summary, error);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.details, result.details) && Intrinsics.areEqual(this.summary, result.summary) && Intrinsics.areEqual(this.error, result.error);
        }

        public int hashCode() {
            List<T> list = this.details;
            int hashCode = (list == null ? 0 : list.hashCode()) * 31;
            Summary summary = this.summary;
            int hashCode2 = (hashCode + (summary == null ? 0 : summary.hashCode())) * 31;
            String str = this.error;
            return hashCode2 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(details=" + this.details + ", summary=" + this.summary + ", error=" + this.error + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            Intrinsics.checkNotNullParameter(parcel, "out");
            List<T> list = this.details;
            if (list == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcel.writeInt(list.size());
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    parcel.writeParcelable(it.next(), flags);
                }
            }
            Summary summary = this.summary;
            if (summary == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                summary.writeToParcel(parcel, flags);
            }
            parcel.writeString(this.error);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Result(List<? extends T> list, Summary summary, String str) {
            this.details = list;
            this.summary = summary;
            this.error = str;
        }

        public final List<T> getDetails() {
            return this.details;
        }

        public final Summary getSummary() {
            return this.summary;
        }

        public final String getError() {
            return this.error;
        }

        /* compiled from: responses.kt */
        @kotlin.Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001cB\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001d"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result$Summary;", "Landroid/os/Parcelable;", "action", "", "details", "", "Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result$Summary$Detail;", "(Ljava/lang/String;Ljava/util/List;)V", "getAction", "()Ljava/lang/String;", "getDetails", "()Ljava/util/List;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Detail", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final /* data */ class Summary implements Parcelable {
            public static final Parcelable.Creator<Summary> CREATOR = new Creator();
            private final String action;
            private final List<Detail> details;

            /* compiled from: responses.kt */
            @kotlin.Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final class Creator implements Parcelable.Creator<Summary> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Summary createFromParcel(Parcel parcel) {
                    ArrayList arrayList;
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    String readString = parcel.readString();
                    if (parcel.readInt() == 0) {
                        arrayList = null;
                    } else {
                        int readInt = parcel.readInt();
                        ArrayList arrayList2 = new ArrayList(readInt);
                        for (int i = 0; i != readInt; i++) {
                            arrayList2.add(Detail.CREATOR.createFromParcel(parcel));
                        }
                        arrayList = arrayList2;
                    }
                    return new Summary(readString, arrayList);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Summary[] newArray(int i) {
                    return new Summary[i];
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Summary copy$default(Summary summary, String str, List list, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = summary.action;
                }
                if ((i & 2) != 0) {
                    list = summary.details;
                }
                return summary.copy(str, list);
            }

            /* renamed from: component1, reason: from getter */
            public final String getAction() {
                return this.action;
            }

            public final List<Detail> component2() {
                return this.details;
            }

            public final Summary copy(String action, List<Detail> details) {
                return new Summary(action, details);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Summary)) {
                    return false;
                }
                Summary summary = (Summary) other;
                return Intrinsics.areEqual(this.action, summary.action) && Intrinsics.areEqual(this.details, summary.details);
            }

            public int hashCode() {
                String str = this.action;
                int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                List<Detail> list = this.details;
                return hashCode + (list != null ? list.hashCode() : 0);
            }

            public String toString() {
                return "Summary(action=" + this.action + ", details=" + this.details + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.action);
                List<Detail> list = this.details;
                if (list == null) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(1);
                parcel.writeInt(list.size());
                Iterator<Detail> it = list.iterator();
                while (it.hasNext()) {
                    it.next().writeToParcel(parcel, flags);
                }
            }

            public Summary(String str, List<Detail> list) {
                this.action = str;
                this.details = list;
            }

            public final String getAction() {
                return this.action;
            }

            public final List<Detail> getDetails() {
                return this.details;
            }

            /* compiled from: responses.kt */
            @kotlin.Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lco/hyperverge/hyperkyc/data/network/BaseResponse$Result$Summary$Detail;", "Landroid/os/Parcelable;", "code", "", "message", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
            /* loaded from: classes2.dex */
            public static final /* data */ class Detail implements Parcelable {
                public static final Parcelable.Creator<Detail> CREATOR = new Creator();
                private final String code;
                private final String message;

                /* compiled from: responses.kt */
                @kotlin.Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                /* loaded from: classes2.dex */
                public static final class Creator implements Parcelable.Creator<Detail> {
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final Detail createFromParcel(Parcel parcel) {
                        Intrinsics.checkNotNullParameter(parcel, "parcel");
                        return new Detail(parcel.readString(), parcel.readString());
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public final Detail[] newArray(int i) {
                        return new Detail[i];
                    }
                }

                public static /* synthetic */ Detail copy$default(Detail detail, String str, String str2, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = detail.code;
                    }
                    if ((i & 2) != 0) {
                        str2 = detail.message;
                    }
                    return detail.copy(str, str2);
                }

                /* renamed from: component1, reason: from getter */
                public final String getCode() {
                    return this.code;
                }

                /* renamed from: component2, reason: from getter */
                public final String getMessage() {
                    return this.message;
                }

                public final Detail copy(String code, String message) {
                    return new Detail(code, message);
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof Detail)) {
                        return false;
                    }
                    Detail detail = (Detail) other;
                    return Intrinsics.areEqual(this.code, detail.code) && Intrinsics.areEqual(this.message, detail.message);
                }

                public int hashCode() {
                    String str = this.code;
                    int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                    String str2 = this.message;
                    return hashCode + (str2 != null ? str2.hashCode() : 0);
                }

                public String toString() {
                    return "Detail(code=" + this.code + ", message=" + this.message + ')';
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int flags) {
                    Intrinsics.checkNotNullParameter(parcel, "out");
                    parcel.writeString(this.code);
                    parcel.writeString(this.message);
                }

                public Detail(String str, String str2) {
                    this.code = str;
                    this.message = str2;
                }

                public final String getCode() {
                    return this.code;
                }

                public final String getMessage() {
                    return this.message;
                }
            }
        }
    }
}
