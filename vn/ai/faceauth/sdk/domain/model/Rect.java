package vn.ai.faceauth.sdk.domain.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import lmlf.ayxnhy.tfwhgw;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001b"}, d2 = {"Lvn/ai/faceauth/sdk/domain/model/Rect;", "", "left", "", ViewHierarchyConstants.DIMENSION_TOP_KEY, TtmlNode.RIGHT, "bottom", "(FFFF)V", "getBottom", "()F", "getLeft", "getRight", "getTop", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toAndroidRect", "Landroid/graphics/Rect;", InAppPurchaseConstants.METHOD_TO_STRING, "", "trueface_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class Rect {
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    public Rect(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
    }

    public static /* synthetic */ Rect copy$default(Rect rect, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = rect.left;
        }
        if ((i & 2) != 0) {
            f2 = rect.top;
        }
        if ((i & 4) != 0) {
            f3 = rect.right;
        }
        if ((i & 8) != 0) {
            f4 = rect.bottom;
        }
        return rect.copy(f, f2, f3, f4);
    }

    /* renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    public final Rect copy(float left, float top, float right, float bottom) {
        return new Rect(left, top, right, bottom);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Rect)) {
            return false;
        }
        Rect rect = (Rect) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this.left), (Object) Float.valueOf(rect.left)) && Intrinsics.areEqual((Object) Float.valueOf(this.top), (Object) Float.valueOf(rect.top)) && Intrinsics.areEqual((Object) Float.valueOf(this.right), (Object) Float.valueOf(rect.right)) && Intrinsics.areEqual((Object) Float.valueOf(this.bottom), (Object) Float.valueOf(rect.bottom));
    }

    public final float getBottom() {
        return this.bottom;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getTop() {
        return this.top;
    }

    public int hashCode() {
        int floatToIntBits = Float.floatToIntBits(this.left);
        int floatToIntBits2 = Float.floatToIntBits(this.top);
        return Float.floatToIntBits(this.bottom) + ((Float.floatToIntBits(this.right) + ((floatToIntBits2 + (floatToIntBits * 31)) * 31)) * 31);
    }

    public final android.graphics.Rect toAndroidRect() {
        return new android.graphics.Rect(MathKt.roundToInt(this.left), MathKt.roundToInt(this.top), MathKt.roundToInt(this.right), MathKt.roundToInt(this.bottom));
    }

    public String toString() {
        return tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_JPEG_TABLES) + this.left + tfwhgw.rnigpa(348) + this.top + tfwhgw.rnigpa(349) + this.right + tfwhgw.rnigpa(350) + this.bottom + ')';
    }
}
