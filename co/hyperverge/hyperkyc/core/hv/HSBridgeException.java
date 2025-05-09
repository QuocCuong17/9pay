package co.hyperverge.hyperkyc.core.hv;

import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: HSBridgeException.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lco/hyperverge/hyperkyc/core/hv/HSBridgeException;", "", "hvError", "Lco/hyperverge/hypersnapsdk/objects/HVError;", "hvResponse", "Lco/hyperverge/hypersnapsdk/objects/HVResponse;", "(Lco/hyperverge/hypersnapsdk/objects/HVError;Lco/hyperverge/hypersnapsdk/objects/HVResponse;)V", "getHvError", "()Lco/hyperverge/hypersnapsdk/objects/HVError;", "getHvResponse", "()Lco/hyperverge/hypersnapsdk/objects/HVResponse;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HSBridgeException extends Throwable {
    private final HVError hvError;
    private final HVResponse hvResponse;

    public /* synthetic */ HSBridgeException(HVError hVError, HVResponse hVResponse, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(hVError, (i & 2) != 0 ? null : hVResponse);
    }

    public final HVError getHvError() {
        return this.hvError;
    }

    public final HVResponse getHvResponse() {
        return this.hvResponse;
    }

    public HSBridgeException(HVError hVError, HVResponse hVResponse) {
        this.hvError = hVError;
        this.hvResponse = hVResponse;
    }
}
