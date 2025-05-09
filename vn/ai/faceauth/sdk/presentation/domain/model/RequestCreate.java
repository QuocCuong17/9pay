package vn.ai.faceauth.sdk.presentation.domain.model;

import com.facebook.gamingservices.cloudgaming.internal.SDKAnalyticsEvents;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tekartik.sqflite.Constant;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\"\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0013\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011¨\u0006\u0016"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/model/RequestCreate;", "", "()V", "clientId", "", "getClientId", "()Ljava/lang/String;", "setClientId", "(Ljava/lang/String;)V", "requestId", "getRequestId", "setRequestId", Constant.PARAM_RESULT, "", "getResult", "()Ljava/lang/Integer;", "setResult", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "timeUsed", "getTimeUsed", "setTimeUsed", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RequestCreate {

    @SerializedName("client_id")
    @Expose
    private String clientId;

    @SerializedName(SDKAnalyticsEvents.PARAMETER_REQUEST_ID)
    @Expose
    private String requestId;

    @SerializedName(Constant.PARAM_RESULT)
    @Expose
    private Integer result;

    @SerializedName("time_used")
    @Expose
    private Integer timeUsed;

    public final String getClientId() {
        return this.clientId;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final Integer getResult() {
        return this.result;
    }

    public final Integer getTimeUsed() {
        return this.timeUsed;
    }

    public final void setClientId(String str) {
        this.clientId = str;
    }

    public final void setRequestId(String str) {
        this.requestId = str;
    }

    public final void setResult(Integer num) {
        this.result = num;
    }

    public final void setTimeUsed(Integer num) {
        this.timeUsed = num;
    }
}
