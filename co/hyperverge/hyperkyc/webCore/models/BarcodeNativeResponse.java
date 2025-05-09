package co.hyperverge.hyperkyc.webCore.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebCoreNativeResponse.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\bHÆ\u0003J<\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001c"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/models/BarcodeNativeResponse;", "Lco/hyperverge/hyperkyc/webCore/models/WebCoreNativeResponse;", "response", "", "errorMessage", "errorCode", "", "isBackPressed", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Z)V", "getErrorCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getErrorMessage", "()Ljava/lang/String;", "()Z", "getResponse", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Z)Lco/hyperverge/hyperkyc/webCore/models/BarcodeNativeResponse;", "equals", "other", "", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BarcodeNativeResponse extends WebCoreNativeResponse {
    private final Integer errorCode;
    private final String errorMessage;
    private final boolean isBackPressed;
    private final String response;

    public BarcodeNativeResponse() {
        this(null, null, null, false, 15, null);
    }

    public static /* synthetic */ BarcodeNativeResponse copy$default(BarcodeNativeResponse barcodeNativeResponse, String str, String str2, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = barcodeNativeResponse.getResponse();
        }
        if ((i & 2) != 0) {
            str2 = barcodeNativeResponse.getErrorMessage();
        }
        if ((i & 4) != 0) {
            num = barcodeNativeResponse.getErrorCode();
        }
        if ((i & 8) != 0) {
            z = barcodeNativeResponse.getIsBackPressed();
        }
        return barcodeNativeResponse.copy(str, str2, num, z);
    }

    public final String component1() {
        return getResponse();
    }

    public final String component2() {
        return getErrorMessage();
    }

    public final Integer component3() {
        return getErrorCode();
    }

    public final boolean component4() {
        return getIsBackPressed();
    }

    public final BarcodeNativeResponse copy(String response, String errorMessage, Integer errorCode, boolean isBackPressed) {
        return new BarcodeNativeResponse(response, errorMessage, errorCode, isBackPressed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BarcodeNativeResponse)) {
            return false;
        }
        BarcodeNativeResponse barcodeNativeResponse = (BarcodeNativeResponse) other;
        return Intrinsics.areEqual(getResponse(), barcodeNativeResponse.getResponse()) && Intrinsics.areEqual(getErrorMessage(), barcodeNativeResponse.getErrorMessage()) && Intrinsics.areEqual(getErrorCode(), barcodeNativeResponse.getErrorCode()) && getIsBackPressed() == barcodeNativeResponse.getIsBackPressed();
    }

    public int hashCode() {
        int hashCode = (((((getResponse() == null ? 0 : getResponse().hashCode()) * 31) + (getErrorMessage() == null ? 0 : getErrorMessage().hashCode())) * 31) + (getErrorCode() != null ? getErrorCode().hashCode() : 0)) * 31;
        boolean isBackPressed = getIsBackPressed();
        int i = isBackPressed;
        if (isBackPressed) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "BarcodeNativeResponse(response=" + getResponse() + ", errorMessage=" + getErrorMessage() + ", errorCode=" + getErrorCode() + ", isBackPressed=" + getIsBackPressed() + ')';
    }

    public /* synthetic */ BarcodeNativeResponse(String str, String str2, Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? false : z);
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    public String getResponse() {
        return this.response;
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    public Integer getErrorCode() {
        return this.errorCode;
    }

    @Override // co.hyperverge.hyperkyc.webCore.models.WebCoreNativeResponse
    /* renamed from: isBackPressed, reason: from getter */
    public boolean getIsBackPressed() {
        return this.isBackPressed;
    }

    public BarcodeNativeResponse(String str, String str2, Integer num, boolean z) {
        super(null);
        this.response = str;
        this.errorMessage = str2;
        this.errorCode = num;
        this.isBackPressed = z;
    }
}
