package vn.ai.faceauth.sdk.camera.domain.model;

import android.graphics.Rect;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.mlkit.vision.face.FaceLandmark;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lmlf.ayxnhy.tfwhgw;
import org.bouncycastle.crypto.tls.CipherSuite;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bw\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0012J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010/\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00100\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00101\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0096\u0001\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u00103J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u0003HÖ\u0001J\t\u00108\u001a\u000209HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0015\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b!\u0010\u001eR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\"\u0010\u001eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$¨\u0006:"}, d2 = {"Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "", "trackingId", "", "bounds", "Landroid/graphics/Rect;", "headEulerAngleX", "", "headEulerAngleY", "headEulerAngleZ", "smilingProbability", "rightEyeOpenProbability", "leftEyeOpenProbability", "luminosity", "imageRect", "landmarkLeftEye", "Lcom/google/mlkit/vision/face/FaceLandmark;", "landmarkRightEye", "(Ljava/lang/Integer;Landroid/graphics/Rect;FFFLjava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Landroid/graphics/Rect;Lcom/google/mlkit/vision/face/FaceLandmark;Lcom/google/mlkit/vision/face/FaceLandmark;)V", "getBounds", "()Landroid/graphics/Rect;", "getHeadEulerAngleX", "()F", "getHeadEulerAngleY", "getHeadEulerAngleZ", "getImageRect", "getLandmarkLeftEye", "()Lcom/google/mlkit/vision/face/FaceLandmark;", "getLandmarkRightEye", "getLeftEyeOpenProbability", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getLuminosity", "getRightEyeOpenProbability", "getSmilingProbability", "getTrackingId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Landroid/graphics/Rect;FFFLjava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Landroid/graphics/Rect;Lcom/google/mlkit/vision/face/FaceLandmark;Lcom/google/mlkit/vision/face/FaceLandmark;)Lvn/ai/faceauth/sdk/camera/domain/model/FaceResult;", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class FaceResult {
    private final Rect bounds;
    private final float headEulerAngleX;
    private final float headEulerAngleY;
    private final float headEulerAngleZ;
    private final Rect imageRect;
    private final FaceLandmark landmarkLeftEye;
    private final FaceLandmark landmarkRightEye;
    private final Float leftEyeOpenProbability;
    private final Float luminosity;
    private final Float rightEyeOpenProbability;
    private final Float smilingProbability;
    private final Integer trackingId;

    public FaceResult(Integer num, Rect rect, float f, float f2, float f3, Float f4, Float f5, Float f6, Float f7, Rect rect2, FaceLandmark faceLandmark, FaceLandmark faceLandmark2) {
        this.trackingId = num;
        this.bounds = rect;
        this.headEulerAngleX = f;
        this.headEulerAngleY = f2;
        this.headEulerAngleZ = f3;
        this.smilingProbability = f4;
        this.rightEyeOpenProbability = f5;
        this.leftEyeOpenProbability = f6;
        this.luminosity = f7;
        this.imageRect = rect2;
        this.landmarkLeftEye = faceLandmark;
        this.landmarkRightEye = faceLandmark2;
    }

    public /* synthetic */ FaceResult(Integer num, Rect rect, float f, float f2, float f3, Float f4, Float f5, Float f6, Float f7, Rect rect2, FaceLandmark faceLandmark, FaceLandmark faceLandmark2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, rect, f, f2, f3, f4, f5, f6, (i & 256) != 0 ? Float.valueOf(0.0f) : f7, rect2, faceLandmark, faceLandmark2);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getTrackingId() {
        return this.trackingId;
    }

    /* renamed from: component10, reason: from getter */
    public final Rect getImageRect() {
        return this.imageRect;
    }

    /* renamed from: component11, reason: from getter */
    public final FaceLandmark getLandmarkLeftEye() {
        return this.landmarkLeftEye;
    }

    /* renamed from: component12, reason: from getter */
    public final FaceLandmark getLandmarkRightEye() {
        return this.landmarkRightEye;
    }

    /* renamed from: component2, reason: from getter */
    public final Rect getBounds() {
        return this.bounds;
    }

    /* renamed from: component3, reason: from getter */
    public final float getHeadEulerAngleX() {
        return this.headEulerAngleX;
    }

    /* renamed from: component4, reason: from getter */
    public final float getHeadEulerAngleY() {
        return this.headEulerAngleY;
    }

    /* renamed from: component5, reason: from getter */
    public final float getHeadEulerAngleZ() {
        return this.headEulerAngleZ;
    }

    /* renamed from: component6, reason: from getter */
    public final Float getSmilingProbability() {
        return this.smilingProbability;
    }

    /* renamed from: component7, reason: from getter */
    public final Float getRightEyeOpenProbability() {
        return this.rightEyeOpenProbability;
    }

    /* renamed from: component8, reason: from getter */
    public final Float getLeftEyeOpenProbability() {
        return this.leftEyeOpenProbability;
    }

    /* renamed from: component9, reason: from getter */
    public final Float getLuminosity() {
        return this.luminosity;
    }

    public final FaceResult copy(Integer trackingId, Rect bounds, float headEulerAngleX, float headEulerAngleY, float headEulerAngleZ, Float smilingProbability, Float rightEyeOpenProbability, Float leftEyeOpenProbability, Float luminosity, Rect imageRect, FaceLandmark landmarkLeftEye, FaceLandmark landmarkRightEye) {
        return new FaceResult(trackingId, bounds, headEulerAngleX, headEulerAngleY, headEulerAngleZ, smilingProbability, rightEyeOpenProbability, leftEyeOpenProbability, luminosity, imageRect, landmarkLeftEye, landmarkRightEye);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceResult)) {
            return false;
        }
        FaceResult faceResult = (FaceResult) other;
        return Intrinsics.areEqual(this.trackingId, faceResult.trackingId) && Intrinsics.areEqual(this.bounds, faceResult.bounds) && Intrinsics.areEqual((Object) Float.valueOf(this.headEulerAngleX), (Object) Float.valueOf(faceResult.headEulerAngleX)) && Intrinsics.areEqual((Object) Float.valueOf(this.headEulerAngleY), (Object) Float.valueOf(faceResult.headEulerAngleY)) && Intrinsics.areEqual((Object) Float.valueOf(this.headEulerAngleZ), (Object) Float.valueOf(faceResult.headEulerAngleZ)) && Intrinsics.areEqual((Object) this.smilingProbability, (Object) faceResult.smilingProbability) && Intrinsics.areEqual((Object) this.rightEyeOpenProbability, (Object) faceResult.rightEyeOpenProbability) && Intrinsics.areEqual((Object) this.leftEyeOpenProbability, (Object) faceResult.leftEyeOpenProbability) && Intrinsics.areEqual((Object) this.luminosity, (Object) faceResult.luminosity) && Intrinsics.areEqual(this.imageRect, faceResult.imageRect) && Intrinsics.areEqual(this.landmarkLeftEye, faceResult.landmarkLeftEye) && Intrinsics.areEqual(this.landmarkRightEye, faceResult.landmarkRightEye);
    }

    public final Rect getBounds() {
        return this.bounds;
    }

    public final float getHeadEulerAngleX() {
        return this.headEulerAngleX;
    }

    public final float getHeadEulerAngleY() {
        return this.headEulerAngleY;
    }

    public final float getHeadEulerAngleZ() {
        return this.headEulerAngleZ;
    }

    public final Rect getImageRect() {
        return this.imageRect;
    }

    public final FaceLandmark getLandmarkLeftEye() {
        return this.landmarkLeftEye;
    }

    public final FaceLandmark getLandmarkRightEye() {
        return this.landmarkRightEye;
    }

    public final Float getLeftEyeOpenProbability() {
        return this.leftEyeOpenProbability;
    }

    public final Float getLuminosity() {
        return this.luminosity;
    }

    public final Float getRightEyeOpenProbability() {
        return this.rightEyeOpenProbability;
    }

    public final Float getSmilingProbability() {
        return this.smilingProbability;
    }

    public final Integer getTrackingId() {
        return this.trackingId;
    }

    public int hashCode() {
        Integer num = this.trackingId;
        int hashCode = num == null ? 0 : num.hashCode();
        int hashCode2 = this.bounds.hashCode();
        int floatToIntBits = Float.floatToIntBits(this.headEulerAngleX);
        int floatToIntBits2 = Float.floatToIntBits(this.headEulerAngleY);
        int floatToIntBits3 = Float.floatToIntBits(this.headEulerAngleZ);
        Float f = this.smilingProbability;
        int hashCode3 = f == null ? 0 : f.hashCode();
        Float f2 = this.rightEyeOpenProbability;
        int hashCode4 = f2 == null ? 0 : f2.hashCode();
        Float f3 = this.leftEyeOpenProbability;
        int hashCode5 = f3 == null ? 0 : f3.hashCode();
        Float f4 = this.luminosity;
        int hashCode6 = f4 == null ? 0 : f4.hashCode();
        Rect rect = this.imageRect;
        int hashCode7 = rect == null ? 0 : rect.hashCode();
        FaceLandmark faceLandmark = this.landmarkLeftEye;
        int hashCode8 = faceLandmark == null ? 0 : faceLandmark.hashCode();
        FaceLandmark faceLandmark2 = this.landmarkRightEye;
        return ((((((((((((((floatToIntBits3 + ((floatToIntBits2 + ((floatToIntBits + ((hashCode2 + (hashCode * 31)) * 31)) * 31)) * 31)) * 31) + hashCode3) * 31) + hashCode4) * 31) + hashCode5) * 31) + hashCode6) * 31) + hashCode7) * 31) + hashCode8) * 31) + (faceLandmark2 != null ? faceLandmark2.hashCode() : 0);
    }

    public String toString() {
        return tfwhgw.rnigpa(CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA) + this.trackingId + tfwhgw.rnigpa(CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256) + this.bounds + tfwhgw.rnigpa(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384) + this.headEulerAngleX + tfwhgw.rnigpa(158) + this.headEulerAngleY + tfwhgw.rnigpa(CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384) + this.headEulerAngleZ + tfwhgw.rnigpa(160) + this.smilingProbability + tfwhgw.rnigpa(CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384) + this.rightEyeOpenProbability + tfwhgw.rnigpa(CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256) + this.leftEyeOpenProbability + tfwhgw.rnigpa(CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384) + this.luminosity + tfwhgw.rnigpa(CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256) + this.imageRect + tfwhgw.rnigpa(CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384) + this.landmarkLeftEye + tfwhgw.rnigpa(CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256) + this.landmarkRightEye + ')';
    }
}
