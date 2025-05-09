package com.otaliastudios.opengl.geometry;

import android.graphics.PointF;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.sentry.protocol.Device;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SegmentF.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0006B%\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0000H\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001b\u0010\u0012\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/otaliastudios/opengl/geometry/SegmentF;", "", CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT, "Landroid/graphics/PointF;", "Lcom/otaliastudios/opengl/geometry/PointF;", "j", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)V", "ix", "", "iy", "jx", "jy", "(FFFF)V", "getIx", "()F", "getIy", "getJx", "getJy", "length", "getLength", "length$delegate", "Lkotlin/Lazy;", "intersects", "", "other", Device.JsonKeys.ORIENTATION, "", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "library_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes4.dex */
public class SegmentF {
    private final float ix;
    private final float iy;
    private final float jx;
    private final float jy;

    /* renamed from: length$delegate, reason: from kotlin metadata */
    private final Lazy length;

    public SegmentF(float f, float f2, float f3, float f4) {
        this.ix = f;
        this.iy = f2;
        this.jx = f3;
        this.jy = f4;
        this.length = LazyKt.lazy(new Function0<Float>() { // from class: com.otaliastudios.opengl.geometry.SegmentF$length$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Float invoke() {
                return Float.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final float invoke2() {
                double d = 2;
                return (float) Math.sqrt(((float) Math.pow(SegmentF.this.getIx() - SegmentF.this.getJx(), d)) + ((float) Math.pow(SegmentF.this.getIy() - SegmentF.this.getJy(), d)));
            }
        });
    }

    public final float getIx() {
        return this.ix;
    }

    public final float getIy() {
        return this.iy;
    }

    public final float getJx() {
        return this.jx;
    }

    public final float getJy() {
        return this.jy;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SegmentF(PointF i, PointF j) {
        this(i.x, i.y, j.x, j.y);
        Intrinsics.checkNotNullParameter(i, "i");
        Intrinsics.checkNotNullParameter(j, "j");
    }

    public final float getLength() {
        return ((Number) this.length.getValue()).floatValue();
    }

    public boolean intersects(SegmentF other) {
        Intrinsics.checkNotNullParameter(other, "other");
        float min = Math.min(this.ix, this.jx);
        float max = Math.max(this.ix, this.jx);
        float min2 = Math.min(other.ix, other.jx);
        float max2 = Math.max(other.ix, other.jx);
        if (min > max2 || max < min2) {
            return false;
        }
        float min3 = Math.min(this.iy, this.jy);
        float max3 = Math.max(this.iy, this.jy);
        float min4 = Math.min(other.iy, other.jy);
        float max4 = Math.max(other.iy, other.jy);
        if (min3 > max4 || max3 < min4) {
            return false;
        }
        int orientation = orientation(other.ix, other.iy);
        int orientation2 = orientation(other.jx, other.jy);
        if (orientation > 0 && orientation2 > 0) {
            return false;
        }
        if (orientation < 0 && orientation2 < 0) {
            return false;
        }
        int orientation3 = other.orientation(this.ix, this.iy);
        int orientation4 = other.orientation(this.jx, this.jy);
        if (orientation3 > 0 && orientation4 > 0) {
            return false;
        }
        if (orientation3 < 0 && orientation4 < 0) {
            return false;
        }
        if (orientation == 0 && orientation2 == 0 && orientation3 == 0 && orientation4 == 0) {
            if (min == max2) {
                if (min3 == max4) {
                    return false;
                }
            }
            if (min == max2) {
                if (max3 == min4) {
                    return false;
                }
            }
            if (max == min2) {
                if (min3 == max4) {
                    return false;
                }
            }
            if (max == min2) {
                if (max3 == min4) {
                    return false;
                }
            }
            return true;
        }
        float f = this.ix;
        float f2 = other.ix;
        if (f == f2) {
            if (this.iy == other.iy) {
                return false;
            }
        }
        float f3 = this.jx;
        float f4 = other.jx;
        if (f3 == f4) {
            if (this.jy == other.jy) {
                return false;
            }
        }
        if (f == f4) {
            if (this.iy == other.jy) {
                return false;
            }
        }
        if (f3 == f2) {
            if (this.jy == other.iy) {
                return false;
            }
        }
        return true;
    }

    public final int orientation(float x, float y) {
        float f = this.jx;
        float f2 = f - this.ix;
        float f3 = this.jy;
        return (int) Math.signum((f2 * (y - f3)) - ((f3 - this.iy) * (x - f)));
    }
}
