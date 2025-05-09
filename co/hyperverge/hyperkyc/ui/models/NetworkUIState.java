package co.hyperverge.hyperkyc.ui.models;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.internal.AnalyticsEvents;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkUIState.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b1\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0004\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "T", "", "()V", AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED, "Loading", "NetworkFailure", "Success", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Failed;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Loading;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$NetworkFailure;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Success;", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class NetworkUIState<T> {
    public /* synthetic */ NetworkUIState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private NetworkUIState() {
    }

    /* compiled from: NetworkUIState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Loading;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Loading extends NetworkUIState {
        public static final Loading INSTANCE = new Loading();

        private Loading() {
            super(null);
        }
    }

    /* compiled from: NetworkUIState.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Success;", "T", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Success;", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Success<T> extends NetworkUIState<T> {
        private final T data;

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

        public int hashCode() {
            T t = this.data;
            if (t == null) {
                return 0;
            }
            return t.hashCode();
        }

        public String toString() {
            return "Success(data=" + this.data + ')';
        }

        public Success(T t) {
            super(null);
            this.data = t;
        }

        public final T getData() {
            return this.data;
        }
    }

    /* compiled from: NetworkUIState.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$Failed;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "", "reason", "", "t", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getReason", "()Ljava/lang/String;", "getT", "()Ljava/lang/Throwable;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class Failed extends NetworkUIState {
        private final String reason;
        private final Throwable t;

        /* JADX WARN: Multi-variable type inference failed */
        public Failed() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Failed copy$default(Failed failed, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = failed.reason;
            }
            if ((i & 2) != 0) {
                th = failed.t;
            }
            return failed.copy(str, th);
        }

        /* renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        /* renamed from: component2, reason: from getter */
        public final Throwable getT() {
            return this.t;
        }

        public final Failed copy(String reason, Throwable t) {
            return new Failed(reason, t);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Failed)) {
                return false;
            }
            Failed failed = (Failed) other;
            return Intrinsics.areEqual(this.reason, failed.reason) && Intrinsics.areEqual(this.t, failed.t);
        }

        public int hashCode() {
            String str = this.reason;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            Throwable th = this.t;
            return hashCode + (th != null ? th.hashCode() : 0);
        }

        public String toString() {
            return "Failed(reason=" + this.reason + ", t=" + this.t + ')';
        }

        public /* synthetic */ Failed(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : th);
        }

        public final String getReason() {
            return this.reason;
        }

        public final Throwable getT() {
            return this.t;
        }

        public Failed(String str, Throwable th) {
            super(null);
            this.reason = str;
            this.t = th;
        }
    }

    /* compiled from: NetworkUIState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lco/hyperverge/hyperkyc/ui/models/NetworkUIState$NetworkFailure;", "Lco/hyperverge/hyperkyc/ui/models/NetworkUIState;", "", "()V", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class NetworkFailure extends NetworkUIState {
        public static final NetworkFailure INSTANCE = new NetworkFailure();

        private NetworkFailure() {
            super(null);
        }
    }
}
