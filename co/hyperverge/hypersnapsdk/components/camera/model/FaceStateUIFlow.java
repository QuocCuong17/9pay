package co.hyperverge.hypersnapsdk.components.camera.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceStateUIFlow.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "", "()V", "CameraClosed", "Detected", "FaceCapture", "NotDetected", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$Detected;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$NotDetected;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$CameraClosed;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$FaceCapture;", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public abstract class FaceStateUIFlow {
    public /* synthetic */ FaceStateUIFlow(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: FaceStateUIFlow.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$Detected;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "()V", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Detected extends FaceStateUIFlow {
        public static final Detected INSTANCE = new Detected();

        private Detected() {
            super(null);
        }
    }

    private FaceStateUIFlow() {
    }

    /* compiled from: FaceStateUIFlow.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$NotDetected;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "()V", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class NotDetected extends FaceStateUIFlow {
        public static final NotDetected INSTANCE = new NotDetected();

        private NotDetected() {
            super(null);
        }
    }

    /* compiled from: FaceStateUIFlow.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$CameraClosed;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "()V", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class CameraClosed extends FaceStateUIFlow {
        public static final CameraClosed INSTANCE = new CameraClosed();

        private CameraClosed() {
            super(null);
        }
    }

    /* compiled from: FaceStateUIFlow.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow$FaceCapture;", "Lco/hyperverge/hypersnapsdk/components/camera/model/FaceStateUIFlow;", "fullImageUri", "", "cropImageUri", "(Ljava/lang/String;Ljava/lang/String;)V", "getCropImageUri", "()Ljava/lang/String;", "getFullImageUri", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final /* data */ class FaceCapture extends FaceStateUIFlow {
        private final String cropImageUri;
        private final String fullImageUri;

        public static /* synthetic */ FaceCapture copy$default(FaceCapture faceCapture, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = faceCapture.fullImageUri;
            }
            if ((i & 2) != 0) {
                str2 = faceCapture.cropImageUri;
            }
            return faceCapture.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFullImageUri() {
            return this.fullImageUri;
        }

        /* renamed from: component2, reason: from getter */
        public final String getCropImageUri() {
            return this.cropImageUri;
        }

        public final FaceCapture copy(String fullImageUri, String cropImageUri) {
            return new FaceCapture(fullImageUri, cropImageUri);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceCapture)) {
                return false;
            }
            FaceCapture faceCapture = (FaceCapture) other;
            return Intrinsics.areEqual(this.fullImageUri, faceCapture.fullImageUri) && Intrinsics.areEqual(this.cropImageUri, faceCapture.cropImageUri);
        }

        public int hashCode() {
            String str = this.fullImageUri;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.cropImageUri;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "FaceCapture(fullImageUri=" + ((Object) this.fullImageUri) + ", cropImageUri=" + ((Object) this.cropImageUri) + ')';
        }

        public final String getFullImageUri() {
            return this.fullImageUri;
        }

        public final String getCropImageUri() {
            return this.cropImageUri;
        }

        public FaceCapture(String str, String str2) {
            super(null);
            this.fullImageUri = str;
            this.cropImageUri = str2;
        }
    }
}
