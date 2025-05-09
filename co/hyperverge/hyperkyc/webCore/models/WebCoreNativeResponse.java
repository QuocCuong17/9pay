package co.hyperverge.hyperkyc.webCore.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: WebCoreNativeResponse.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\n\u0082\u0001\u0005\u0010\u0011\u0012\u0013\u0014¨\u0006\u0015"}, d2 = {"Lco/hyperverge/hyperkyc/webCore/models/WebCoreNativeResponse;", "", "()V", "errorCode", "", "getErrorCode", "()Ljava/lang/Integer;", "errorMessage", "", "getErrorMessage", "()Ljava/lang/String;", "isBackPressed", "", "()Z", "response", "getResponse", "Lco/hyperverge/hyperkyc/webCore/models/BarcodeNativeResponse;", "Lco/hyperverge/hyperkyc/webCore/models/DocumentNativeResponse;", "Lco/hyperverge/hyperkyc/webCore/models/FaceNativeResponse;", "Lco/hyperverge/hyperkyc/webCore/models/NFCNativeResponse;", "Lco/hyperverge/hyperkyc/webCore/models/WebViewNativeResponse;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class WebCoreNativeResponse {
    public /* synthetic */ WebCoreNativeResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Integer getErrorCode();

    public abstract String getErrorMessage();

    public abstract String getResponse();

    public abstract boolean isBackPressed();

    private WebCoreNativeResponse() {
    }
}
