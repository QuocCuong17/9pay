package vn.ai.faceauth.sdk.domain.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\r\u0010\u0010\u001a\u00060\u0006j\u0002`\u0007HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\f\b\u0002\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/LivenessCameraXErrorDomain;", "", "message", "", "cause", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V", "getCause", "()Ljava/lang/String;", "getException", "()Ljava/lang/Exception;", "getMessage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class LivenessCameraXErrorDomain {
    private final String cause;
    private final Exception exception;
    private final String message;

    public LivenessCameraXErrorDomain(String str, String str2, Exception exc) {
        this.message = str;
        this.cause = str2;
        this.exception = exc;
    }

    public static /* synthetic */ LivenessCameraXErrorDomain copy$default(LivenessCameraXErrorDomain livenessCameraXErrorDomain, String str, String str2, Exception exc, int i, Object obj) {
        if ((i & 1) != 0) {
            str = livenessCameraXErrorDomain.message;
        }
        if ((i & 2) != 0) {
            str2 = livenessCameraXErrorDomain.cause;
        }
        if ((i & 4) != 0) {
            exc = livenessCameraXErrorDomain.exception;
        }
        return livenessCameraXErrorDomain.copy(str, str2, exc);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCause() {
        return this.cause;
    }

    /* renamed from: component3, reason: from getter */
    public final Exception getException() {
        return this.exception;
    }

    public final LivenessCameraXErrorDomain copy(String message, String cause, Exception exception) {
        return new LivenessCameraXErrorDomain(message, cause, exception);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LivenessCameraXErrorDomain)) {
            return false;
        }
        LivenessCameraXErrorDomain livenessCameraXErrorDomain = (LivenessCameraXErrorDomain) other;
        return Intrinsics.areEqual(this.message, livenessCameraXErrorDomain.message) && Intrinsics.areEqual(this.cause, livenessCameraXErrorDomain.cause) && Intrinsics.areEqual(this.exception, livenessCameraXErrorDomain.exception);
    }

    public final String getCause() {
        return this.cause;
    }

    public final Exception getException() {
        return this.exception;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        int hashCode = this.message.hashCode();
        return this.exception.hashCode() + ((this.cause.hashCode() + (hashCode * 31)) * 31);
    }

    public String toString() {
        return tfwhgw.rnigpa(1) + this.message + tfwhgw.rnigpa(2) + this.cause + tfwhgw.rnigpa(3) + this.exception + ')';
    }
}
