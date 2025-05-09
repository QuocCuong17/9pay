package vn.ai.faceauth.sdk.presentation.domain.network;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00060\u0001j\u0002`\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003J+\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/network/ApiException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "code", "", "message", "", "response", "", "(ILjava/lang/String;Ljava/lang/Object;)V", "getCode", "()I", "setCode", "(I)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "getResponse", "()Ljava/lang/Object;", "setResponse", "(Ljava/lang/Object;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ApiException extends Exception {
    private int code;
    private String message;
    private Object response;

    public ApiException(int i, String str, Object obj) {
        super(str);
        this.code = i;
        this.message = str;
        this.response = obj;
    }

    public static /* synthetic */ ApiException copy$default(ApiException apiException, int i, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = apiException.code;
        }
        if ((i2 & 2) != 0) {
            str = apiException.getMessage();
        }
        if ((i2 & 4) != 0) {
            obj = apiException.response;
        }
        return apiException.copy(i, str, obj);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    public final String component2() {
        return getMessage();
    }

    /* renamed from: component3, reason: from getter */
    public final Object getResponse() {
        return this.response;
    }

    public final ApiException copy(int code, String message, Object response) {
        return new ApiException(code, message, response);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApiException)) {
            return false;
        }
        ApiException apiException = (ApiException) other;
        return this.code == apiException.code && Intrinsics.areEqual(getMessage(), apiException.getMessage()) && Intrinsics.areEqual(this.response, apiException.response);
    }

    public final int getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public final Object getResponse() {
        return this.response;
    }

    public int hashCode() {
        int i = this.code;
        int hashCode = getMessage() == null ? 0 : getMessage().hashCode();
        Object obj = this.response;
        return (((i * 31) + hashCode) * 31) + (obj != null ? obj.hashCode() : 0);
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public final void setResponse(Object obj) {
        this.response = obj;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return tfwhgw.rnigpa(256) + this.code + tfwhgw.rnigpa(257) + getMessage() + tfwhgw.rnigpa(258) + this.response + ')';
    }
}
