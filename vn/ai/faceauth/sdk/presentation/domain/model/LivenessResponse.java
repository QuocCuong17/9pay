package vn.ai.faceauth.sdk.presentation.domain.model;

import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import lmlf.ayxnhy.tfwhgw;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001f\u001a\u00020 J\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004R,\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR,\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001c\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011¨\u0006\""}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/LivenessResponse;", "", "()V", "customerData", "", "", "getCustomerData", "()Ljava/util/Map;", "setCustomerData", "(Ljava/util/Map;)V", "data", "getData", "setData", "errorDetail", "getErrorDetail", "()Ljava/lang/String;", "setErrorDetail", "(Ljava/lang/String;)V", "personId", "getPersonId", "setPersonId", Keys.STATUS_CODE, "", "getStatusCode", "()Ljava/lang/Integer;", "setStatusCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "transId", "getTransId", "setTransId", "toJson", "Lorg/json/JSONObject;", "toMap", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LivenessResponse {

    @SerializedName("customerData")
    @Expose
    private Map<String, ? extends Object> customerData;

    @SerializedName("data")
    @Expose
    private Map<String, ? extends Object> data;

    @SerializedName("errorDetail")
    @Expose
    private String errorDetail;

    @SerializedName("personId")
    @Expose
    private String personId;

    @SerializedName(Keys.STATUS_CODE)
    @Expose
    private Integer statusCode = 0;

    @SerializedName("transId")
    @Expose
    private String transId;

    public final Map<String, Object> getCustomerData() {
        return this.customerData;
    }

    public final Map<String, Object> getData() {
        return this.data;
    }

    public final String getErrorDetail() {
        return this.errorDetail;
    }

    public final String getPersonId() {
        return this.personId;
    }

    public final Integer getStatusCode() {
        return this.statusCode;
    }

    public final String getTransId() {
        return this.transId;
    }

    public final void setCustomerData(Map<String, ? extends Object> map) {
        this.customerData = map;
    }

    public final void setData(Map<String, ? extends Object> map) {
        this.data = map;
    }

    public final void setErrorDetail(String str) {
        this.errorDetail = str;
    }

    public final void setPersonId(String str) {
        this.personId = str;
    }

    public final void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    public final void setTransId(String str) {
        this.transId = str;
    }

    public final JSONObject toJson() {
        return new JSONObject(toMap());
    }

    public final Map<String, Object> toMap() {
        Integer num = this.statusCode;
        String rnigpa = tfwhgw.rnigpa(479);
        return (num != null && num.intValue() == 1) ? MapsKt.mapOf(TuplesKt.to(rnigpa, this.statusCode), TuplesKt.to(tfwhgw.rnigpa(481), this.transId), TuplesKt.to(tfwhgw.rnigpa(482), this.personId), TuplesKt.to(tfwhgw.rnigpa(483), this.data), TuplesKt.to(tfwhgw.rnigpa(484), this.customerData)) : MapsKt.mapOf(TuplesKt.to(rnigpa, this.statusCode), TuplesKt.to(tfwhgw.rnigpa(480), this.errorDetail));
    }
}
