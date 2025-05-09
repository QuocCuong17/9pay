package vn.ai.faceauth.sdk.presentation.domain.mapper;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "R", "", "()V", InAppPurchaseConstants.METHOD_TO_STRING, "", "Error", "Success", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result$Success;", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result$Error;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class Result<R> {

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\r\u0010\u000e\u001a\u00060\u0006j\u0002`\u0007HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\b\u0002\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u0015\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result$Error;", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "", "message", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class Error extends Result {
        private final Exception exception;
        private final String message;

        public Error(String str, Exception exc) {
            super(null);
            this.message = str;
            this.exception = exc;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, Exception exc, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.message;
            }
            if ((i & 2) != 0) {
                exc = error.exception;
            }
            return error.copy(str, exc);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* renamed from: component2, reason: from getter */
        public final Exception getException() {
            return this.exception;
        }

        public final Error copy(String message, Exception exception) {
            return new Error(message, exception);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return Intrinsics.areEqual(this.message, error.message) && Intrinsics.areEqual(this.exception, error.exception);
        }

        public final Exception getException() {
            return this.exception;
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            String str = this.message;
            return this.exception.hashCode() + ((str == null ? 0 : str.hashCode()) * 31);
        }

        @Override // vn.ai.faceauth.sdk.presentation.domain.mapper.Result
        public String toString() {
            return tfwhgw.rnigpa(382) + this.message + tfwhgw.rnigpa(383) + this.exception + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result$Success;", "T", "Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lvn/ai/faceauth/sdk/presentation/domain/mapper/Result$Success;", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class Success<T> extends Result<T> {
        private final T data;

        public Success(T t) {
            super(null);
            this.data = t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Success copy$default(Success success, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = success.data;
            }
            return success.copy(obj);
        }

        public final T component1() {
            return this.data;
        }

        public final Success<T> copy(T data) {
            return new Success<>(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && Intrinsics.areEqual(this.data, ((Success) other).data);
        }

        public final T getData() {
            return this.data;
        }

        public int hashCode() {
            T t = this.data;
            if (t == null) {
                return 0;
            }
            return t.hashCode();
        }

        @Override // vn.ai.faceauth.sdk.presentation.domain.mapper.Result
        public String toString() {
            return tfwhgw.rnigpa(28) + this.data + ')';
        }
    }

    private Result() {
    }

    public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public String toString() {
        StringBuilder sb;
        Object exception;
        if (this instanceof Success) {
            sb = new StringBuilder(tfwhgw.rnigpa(487));
            exception = ((Success) this).getData();
        } else {
            if (!(this instanceof Error)) {
                throw new NoWhenBranchMatchedException();
            }
            sb = new StringBuilder(tfwhgw.rnigpa(488));
            exception = ((Error) this).getException();
        }
        sb.append(exception);
        sb.append(']');
        return sb.toString();
    }
}
