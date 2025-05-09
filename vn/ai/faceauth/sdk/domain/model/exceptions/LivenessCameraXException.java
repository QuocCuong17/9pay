package vn.ai.faceauth.sdk.domain.model.exceptions;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.internal.NativeProtocol;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\t\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\t\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "ContextSwitchException", "MaxLimitTryAgain", "NetworkErrorException", NativeProtocol.ERROR_PERMISSION_DENIED, "PermissionUnknown", "StartCameraException", "TimeOutFace", "UserCanceledException", "UserRunEmulatorException", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$StartCameraException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$ContextSwitchException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$UserRunEmulatorException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$NetworkErrorException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$UserCanceledException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$PermissionDenied;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$PermissionUnknown;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$MaxLimitTryAgain;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$TimeOutFace;", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class LivenessCameraXException extends Exception {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$ContextSwitchException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class ContextSwitchException extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public ContextSwitchException() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public ContextSwitchException(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ ContextSwitchException(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.CONTEXT_SWITCHED.name() : str);
        }

        public static /* synthetic */ ContextSwitchException copy$default(ContextSwitchException contextSwitchException, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = contextSwitchException.getMessage();
            }
            return contextSwitchException.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final ContextSwitchException copy(String message) {
            return new ContextSwitchException(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ContextSwitchException) && Intrinsics.areEqual(getMessage(), ((ContextSwitchException) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$MaxLimitTryAgain;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class MaxLimitTryAgain extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public MaxLimitTryAgain() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public MaxLimitTryAgain(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ MaxLimitTryAgain(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.MAX_RETRY.name() : str);
        }

        public static /* synthetic */ MaxLimitTryAgain copy$default(MaxLimitTryAgain maxLimitTryAgain, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = maxLimitTryAgain.getMessage();
            }
            return maxLimitTryAgain.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final MaxLimitTryAgain copy(String message) {
            return new MaxLimitTryAgain(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MaxLimitTryAgain) && Intrinsics.areEqual(getMessage(), ((MaxLimitTryAgain) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(314) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$NetworkErrorException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class NetworkErrorException extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public NetworkErrorException() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public NetworkErrorException(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ NetworkErrorException(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.NETWORK_ERROR.name() : str);
        }

        public static /* synthetic */ NetworkErrorException copy$default(NetworkErrorException networkErrorException, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = networkErrorException.getMessage();
            }
            return networkErrorException.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final NetworkErrorException copy(String message) {
            return new NetworkErrorException(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NetworkErrorException) && Intrinsics.areEqual(getMessage(), ((NetworkErrorException) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(380) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$PermissionDenied;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class PermissionDenied extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public PermissionDenied() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public PermissionDenied(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ PermissionDenied(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.PERMISSION_DENIED.name() : str);
        }

        public static /* synthetic */ PermissionDenied copy$default(PermissionDenied permissionDenied, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = permissionDenied.getMessage();
            }
            return permissionDenied.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final PermissionDenied copy(String message) {
            return new PermissionDenied(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PermissionDenied) && Intrinsics.areEqual(getMessage(), ((PermissionDenied) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(29) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$PermissionUnknown;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class PermissionUnknown extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public PermissionUnknown() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public PermissionUnknown(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ PermissionUnknown(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.PERMISSION_UNKNOWN.name() : str);
        }

        public static /* synthetic */ PermissionUnknown copy$default(PermissionUnknown permissionUnknown, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = permissionUnknown.getMessage();
            }
            return permissionUnknown.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final PermissionUnknown copy(String message) {
            return new PermissionUnknown(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PermissionUnknown) && Intrinsics.areEqual(getMessage(), ((PermissionUnknown) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(373) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$StartCameraException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class StartCameraException extends LivenessCameraXException {
        private final Throwable cause;
        private final String message;

        public StartCameraException(String str, Throwable th) {
            super(null);
            this.message = str;
            this.cause = th;
        }

        public static /* synthetic */ StartCameraException copy$default(StartCameraException startCameraException, String str, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = startCameraException.getMessage();
            }
            if ((i & 2) != 0) {
                th = startCameraException.getCause();
            }
            return startCameraException.copy(str, th);
        }

        public final String component1() {
            return getMessage();
        }

        public final Throwable component2() {
            return getCause();
        }

        public final StartCameraException copy(String message, Throwable cause) {
            return new StartCameraException(message, cause);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StartCameraException)) {
                return false;
            }
            StartCameraException startCameraException = (StartCameraException) other;
            return Intrinsics.areEqual(getMessage(), startCameraException.getMessage()) && Intrinsics.areEqual(getCause(), startCameraException.getCause());
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return ((getMessage() == null ? 0 : getMessage().hashCode()) * 31) + (getCause() != null ? getCause().hashCode() : 0);
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(268) + getMessage() + tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_DOCUMENT_NAME) + getCause() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$TimeOutFace;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class TimeOutFace extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public TimeOutFace() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public TimeOutFace(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ TimeOutFace(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.TIME_OUT.name() : str);
        }

        public static /* synthetic */ TimeOutFace copy$default(TimeOutFace timeOutFace, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = timeOutFace.getMessage();
            }
            return timeOutFace.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final TimeOutFace copy(String message) {
            return new TimeOutFace(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TimeOutFace) && Intrinsics.areEqual(getMessage(), ((TimeOutFace) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(351) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$UserCanceledException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class UserCanceledException extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public UserCanceledException() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public UserCanceledException(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ UserCanceledException(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.USER_CANCELED.name() : str);
        }

        public static /* synthetic */ UserCanceledException copy$default(UserCanceledException userCanceledException, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = userCanceledException.getMessage();
            }
            return userCanceledException.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final UserCanceledException copy(String message) {
            return new UserCanceledException(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UserCanceledException) && Intrinsics.areEqual(getMessage(), ((UserCanceledException) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(192) + getMessage() + ')';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException$UserRunEmulatorException;", "Lvn/ai/faceauth/sdk/domain/model/exceptions/LivenessCameraXException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final /* data */ class UserRunEmulatorException extends LivenessCameraXException {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public UserRunEmulatorException() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public UserRunEmulatorException(String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ UserRunEmulatorException(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? ErrorsTypes.EMULATOR.name() : str);
        }

        public static /* synthetic */ UserRunEmulatorException copy$default(UserRunEmulatorException userRunEmulatorException, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = userRunEmulatorException.getMessage();
            }
            return userRunEmulatorException.copy(str);
        }

        public final String component1() {
            return getMessage();
        }

        public final UserRunEmulatorException copy(String message) {
            return new UserRunEmulatorException(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UserRunEmulatorException) && Intrinsics.areEqual(getMessage(), ((UserRunEmulatorException) other).getMessage());
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public int hashCode() {
            if (getMessage() == null) {
                return 0;
            }
            return getMessage().hashCode();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return tfwhgw.rnigpa(357) + getMessage() + ')';
        }
    }

    private LivenessCameraXException() {
    }

    public /* synthetic */ LivenessCameraXException(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
