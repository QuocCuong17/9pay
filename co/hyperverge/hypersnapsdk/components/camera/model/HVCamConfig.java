package co.hyperverge.hypersnapsdk.components.camera.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HVCamConfig.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\fHÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011¨\u0006'"}, d2 = {"Lco/hyperverge/hypersnapsdk/components/camera/model/HVCamConfig;", "", "useBackCamera", "", "shouldRandomize", "shouldZoomPreview", "previewMegaPixel", "", "pictureMegaPixel", "enableLookStraight", "shouldRecordVideo", "diameter", "", "(ZZZFFZZI)V", "getDiameter", "()I", "getEnableLookStraight", "()Z", "getPictureMegaPixel", "()F", "getPreviewMegaPixel", "getShouldRandomize", "getShouldRecordVideo", "getShouldZoomPreview", "getUseBackCamera", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class HVCamConfig {
    private final int diameter;
    private final boolean enableLookStraight;
    private final float pictureMegaPixel;
    private final float previewMegaPixel;
    private final boolean shouldRandomize;
    private final boolean shouldRecordVideo;
    private final boolean shouldZoomPreview;
    private final boolean useBackCamera;

    public HVCamConfig() {
        this(false, false, false, 0.0f, 0.0f, false, false, 0, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getUseBackCamera() {
        return this.useBackCamera;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getShouldRandomize() {
        return this.shouldRandomize;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getShouldZoomPreview() {
        return this.shouldZoomPreview;
    }

    /* renamed from: component4, reason: from getter */
    public final float getPreviewMegaPixel() {
        return this.previewMegaPixel;
    }

    /* renamed from: component5, reason: from getter */
    public final float getPictureMegaPixel() {
        return this.pictureMegaPixel;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getEnableLookStraight() {
        return this.enableLookStraight;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getShouldRecordVideo() {
        return this.shouldRecordVideo;
    }

    /* renamed from: component8, reason: from getter */
    public final int getDiameter() {
        return this.diameter;
    }

    public final HVCamConfig copy(boolean useBackCamera, boolean shouldRandomize, boolean shouldZoomPreview, float previewMegaPixel, float pictureMegaPixel, boolean enableLookStraight, boolean shouldRecordVideo, int diameter) {
        return new HVCamConfig(useBackCamera, shouldRandomize, shouldZoomPreview, previewMegaPixel, pictureMegaPixel, enableLookStraight, shouldRecordVideo, diameter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HVCamConfig)) {
            return false;
        }
        HVCamConfig hVCamConfig = (HVCamConfig) other;
        return this.useBackCamera == hVCamConfig.useBackCamera && this.shouldRandomize == hVCamConfig.shouldRandomize && this.shouldZoomPreview == hVCamConfig.shouldZoomPreview && Intrinsics.areEqual((Object) Float.valueOf(this.previewMegaPixel), (Object) Float.valueOf(hVCamConfig.previewMegaPixel)) && Intrinsics.areEqual((Object) Float.valueOf(this.pictureMegaPixel), (Object) Float.valueOf(hVCamConfig.pictureMegaPixel)) && this.enableLookStraight == hVCamConfig.enableLookStraight && this.shouldRecordVideo == hVCamConfig.shouldRecordVideo && this.diameter == hVCamConfig.diameter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v8, types: [boolean] */
    public int hashCode() {
        boolean z = this.useBackCamera;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        ?? r2 = this.shouldRandomize;
        int i2 = r2;
        if (r2 != 0) {
            i2 = 1;
        }
        int i3 = (i + i2) * 31;
        ?? r22 = this.shouldZoomPreview;
        int i4 = r22;
        if (r22 != 0) {
            i4 = 1;
        }
        int floatToIntBits = (((((i3 + i4) * 31) + Float.floatToIntBits(this.previewMegaPixel)) * 31) + Float.floatToIntBits(this.pictureMegaPixel)) * 31;
        ?? r23 = this.enableLookStraight;
        int i5 = r23;
        if (r23 != 0) {
            i5 = 1;
        }
        int i6 = (floatToIntBits + i5) * 31;
        boolean z2 = this.shouldRecordVideo;
        return ((i6 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.diameter;
    }

    public String toString() {
        return "HVCamConfig(useBackCamera=" + this.useBackCamera + ", shouldRandomize=" + this.shouldRandomize + ", shouldZoomPreview=" + this.shouldZoomPreview + ", previewMegaPixel=" + this.previewMegaPixel + ", pictureMegaPixel=" + this.pictureMegaPixel + ", enableLookStraight=" + this.enableLookStraight + ", shouldRecordVideo=" + this.shouldRecordVideo + ", diameter=" + this.diameter + ')';
    }

    public HVCamConfig(boolean z, boolean z2, boolean z3, float f, float f2, boolean z4, boolean z5, int i) {
        this.useBackCamera = z;
        this.shouldRandomize = z2;
        this.shouldZoomPreview = z3;
        this.previewMegaPixel = f;
        this.pictureMegaPixel = f2;
        this.enableLookStraight = z4;
        this.shouldRecordVideo = z5;
        this.diameter = i;
    }

    public /* synthetic */ HVCamConfig(boolean z, boolean z2, boolean z3, float f, float f2, boolean z4, boolean z5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? false : z2, (i2 & 4) != 0 ? false : z3, (i2 & 8) != 0 ? 0.3f : f, (i2 & 16) != 0 ? 1.3f : f2, (i2 & 32) != 0 ? false : z4, (i2 & 64) != 0 ? false : z5, (i2 & 128) == 0 ? i : 0);
    }

    public final boolean getUseBackCamera() {
        return this.useBackCamera;
    }

    public final boolean getShouldRandomize() {
        return this.shouldRandomize;
    }

    public final boolean getShouldZoomPreview() {
        return this.shouldZoomPreview;
    }

    public final float getPreviewMegaPixel() {
        return this.previewMegaPixel;
    }

    public final float getPictureMegaPixel() {
        return this.pictureMegaPixel;
    }

    public final boolean getEnableLookStraight() {
        return this.enableLookStraight;
    }

    public final boolean getShouldRecordVideo() {
        return this.shouldRecordVideo;
    }

    public final int getDiameter() {
        return this.diameter;
    }
}
